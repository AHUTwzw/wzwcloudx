package com.wzw.springcloud.dao;

import com.wzw.springcloud.model.Tickets64;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wuzhiwei
 * @create 2020-12-13 20:52
 */
@Mapper
public interface FlickerIdDao {
    Long getIdByStub(Tickets64 tickets64);
}
