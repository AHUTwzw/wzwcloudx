package com.wzw.springcloud.service.impl;

import cn.hutool.core.lang.Validator;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wzw.springcloud.dao.FlickerIdDao;
import com.wzw.springcloud.model.Tickets64;
import com.wzw.springcloud.service.DIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuzhiwei
 * @create 2020-12-13 17:01
 * 采用了MySQL自增长ID的机制(auto_increment + replace into)
 */
@Service
public class FlickerIdGenerator implements DIdGenerator {
    @Autowired
    private FlickerIdDao flickerIdDao;

    @Override
    public List<Long> getId(String strategy, int num) {
        List<Long> idList = new ArrayList<>();
        Validator.isNotNull(strategy);
        Tickets64 tickets64 = JSONObject.parseObject(strategy, Tickets64.class);
        if(flickerIdDao.getIdByStub(tickets64) > 0) {
            idList.add(tickets64.getId());
        }
        return idList;
    }
}
