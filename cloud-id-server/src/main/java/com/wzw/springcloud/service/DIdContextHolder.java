package com.wzw.springcloud.service;

import com.wzw.springcloud.exception.DidServiceException;
import com.wzw.springcloud.exception.IllegalDIPTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wuzhiwei
 * @create 2020-12-13 17:30
 */
@Component
public class DIdContextHolder {
    private DIdGenerator didGenerator;

    @Autowired
    private DIdGeneratorFactory dIdGeneratorFactory;

    public List getDidList(String type, String strategy, int num) throws DidServiceException {
        didGenerator = dIdGeneratorFactory.creator(type);
        if (didGenerator == null) {
            throw new IllegalDIPTypeException("DId type is not exist");
        }
        return didGenerator.getId(strategy, num);
    }

    public DIdGenerator getDIdGenerator() {
        return didGenerator;
    }

    public void setDIPGenerator(DIdGenerator dipGenerator) {
        this.didGenerator = dipGenerator;
    }
}
