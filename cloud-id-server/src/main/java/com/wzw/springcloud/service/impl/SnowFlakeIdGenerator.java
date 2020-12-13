package com.wzw.springcloud.service.impl;

import cn.hutool.core.date.SystemClock;
import cn.hutool.core.lang.Validator;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wzw.springcloud.constant.BaseConstant;
import com.wzw.springcloud.exception.DidServiceException;
import com.wzw.springcloud.service.DIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * * <p>
 *  * 高并发场景下System.currentTimeMillis()的性能问题的优化,使用hutool工具包SystemClock.now()
 *  * </p>
 *  * <p>
 *  * System.currentTimeMillis()的调用比new一个普通对象要耗时的多（具体耗时高出多少我还没测试过，有人说是100倍左右）<br>
 *  * System.currentTimeMillis()之所以慢是因为去跟系统打了一次交道<br>
 *  * 后台定时更新时钟，JVM退出时，线程自动回收<br>
 *  * </p>
 * @author wuzhiwei
 * @create 2020-12-13 17:01
 */
@Slf4j
@Service
public class SnowFlakeIdGenerator implements DIdGenerator {
    //初始时间截 (2017-01-01)
    private static final long INITIAL_TIME_STAMP = 1483200000000L;

    //机器id所占的位数
    private static final long WORKER_ID_BITS = 5L;

    //数据标识id所占的位数
    private static final long DATACENTER_ID_BITS = 5L;

    //支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);

    //支持的最大数据标识id，结果是31
    private static final long MAX_DATACENTER_ID = ~(-1L << DATACENTER_ID_BITS);

    //序列在id中占的位数
    private final long SEQUENCE_BITS = 12L;

    //机器ID的偏移量(12)
    private final long WORKERID_OFFSET = SEQUENCE_BITS;

    //数据中心ID的偏移量(12+5)
    private final long DATACENTERID_OFFSET = SEQUENCE_BITS + WORKER_ID_BITS;

    //时间截的偏移量(5+5+12)
    private final long TIMESTAMP_OFFSET = DATACENTER_ID_BITS + WORKER_ID_BITS + SEQUENCE_BITS;

    //生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
    private final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    //数据中心ID(0~31)
    private long datacenterId;

    //工作节点ID(0~31)
    private long workerId;

    //毫秒内序列(0~4095)
    private long sequence = 0L;

    //上次生成ID的时间截
    private long lastTimestamp = -1L;

    public SnowFlakeIdGenerator() {}

    /**
     * 构造函数
     *
     * @param datacenterId 数据中心ID (0~31)
     * @param workerId     工作ID (0~31)
     */
    public void SnowFlakeIdGeneratorInit(long datacenterId, long workerId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(String.format("WorkerID 不能大于 %d 或小于 0", MAX_WORKER_ID));
        }
        if (datacenterId > MAX_DATACENTER_ID || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("DataCenterID 不能大于 %d 或小于 0", MAX_DATACENTER_ID));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * 获得下一个ID (用同步锁保证线程安全)
     *
     * @return SnowflakeId
     */
    public synchronized long nextId() {

        // 毫秒时间戳（13位）
        long timestamp = SystemClock.now();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException("当前时间小于上一次记录的时间戳！");
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;

            //sequence等于0说明毫秒内序列已经增长到最大值
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            //时间戳改变，毫秒内序列重置
            sequence = 0L;
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - INITIAL_TIME_STAMP) << TIMESTAMP_OFFSET)
                | (datacenterId << DATACENTERID_OFFSET)
                | (workerId << WORKERID_OFFSET)
                | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = SystemClock.now();
        while (timestamp <= lastTimestamp) {
            timestamp = SystemClock.now();
        }
        return timestamp;
    }

    @Override
    public List<Long> getId(String strategy, int num) throws DidServiceException {
        Validator.isNotNull(strategy);
        JSONObject jsonObject = JSON.parseObject(strategy);
        long datacenterId = jsonObject.getLongValue("datacenterId");
        long workerId = jsonObject.getLongValue("workerId");
        this.SnowFlakeIdGeneratorInit(datacenterId, workerId);

        List<Long> idList = new ArrayList<>();
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(5, 100, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
        for (int i = 0; i < num; i++) {
            Future future = threadPoolExecutor.submit(() -> this.nextId());
            try {
                idList.add((Long) future.get());
            } catch (InterruptedException | ExecutionException e) {
                log.error(e.getMessage());
                throw new DidServiceException(BaseConstant.resultConstant.SYS_INNOR_ERROR_);
            }
        }
        return idList;
    }

}
