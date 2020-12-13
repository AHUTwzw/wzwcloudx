package com.wzw.springcloud.service;

import com.wzw.springcloud.constant.IdTypeConstant;
import com.wzw.springcloud.service.impl.FlickerIdGenerator;
import com.wzw.springcloud.service.impl.LeafIdGenerator;
import com.wzw.springcloud.service.impl.SnowFlakeIdGenerator;
import com.wzw.springcloud.service.impl.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuzhiwei
 * @create 2020-12-13 17:33
 */
@Component
public class DIdGeneratorFactory {
    @Autowired
    private UUIDGenerator uuidGenerator;

    @Autowired
    private FlickerIdGenerator flickerIdGenerator;

    @Autowired
    private SnowFlakeIdGenerator snowFlakeIdGenerator;

    @Autowired
    private LeafIdGenerator leafIdGenerator;

    private static Map<String, DIdGenerator> dIdGeneratorMap = new HashMap<>();

    @PostConstruct
    public void init() {
        dIdGeneratorMap.put(IdTypeConstant.UUID, uuidGenerator);
        dIdGeneratorMap.put(IdTypeConstant.FLICKER, flickerIdGenerator);
        dIdGeneratorMap.put(IdTypeConstant.SNOWFLAKE, snowFlakeIdGenerator);
        dIdGeneratorMap.put(IdTypeConstant.LEAF, leafIdGenerator);
    }
    public DIdGenerator creator(String type){
        return dIdGeneratorMap.get(type);
    }
}
