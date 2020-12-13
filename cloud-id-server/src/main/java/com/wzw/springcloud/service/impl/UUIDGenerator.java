package com.wzw.springcloud.service.impl;

import com.wzw.springcloud.constant.BaseConstant;
import com.wzw.springcloud.exception.DidServiceException;
import com.wzw.springcloud.service.DIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * @author wuzhiwei
 * @create 2020-12-13 17:28
 */
@Slf4j
@Service
public class UUIDGenerator implements DIdGenerator {
    @Override
    public List<UUID> getId(String strategy, int num) throws DidServiceException {
        List<UUID> didList = new ArrayList<>();
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(5, 100, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
        for (int i = 0; i < num; i++) {
            Future future = threadPoolExecutor.submit(() -> UUID.randomUUID());
            try {
                didList.add((UUID) future.get());
            } catch (InterruptedException | ExecutionException e) {
                log.error(e.getMessage());
                throw new DidServiceException(BaseConstant.resultConstant.SYS_INNOR_ERROR_);
            }
        }
        return didList;
    }
}
