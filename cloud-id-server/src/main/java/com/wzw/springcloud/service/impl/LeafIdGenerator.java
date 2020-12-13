package com.wzw.springcloud.service.impl;

import com.wzw.springcloud.service.DIdGenerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuzhiwei
 * @create 2020-12-13 17:01
 */
@Service
public class LeafIdGenerator implements DIdGenerator {

    @Override
    public List<String> getId(String strategy, int num) {
        List<String> idList = new ArrayList<>();
        return idList;
    }
}
