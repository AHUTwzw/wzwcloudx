package com.wzw.springcloud.response;

import com.wzw.springcloud.constant.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wuzhiwei
 * @create 2020-11-28 23:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;
    private String msg;
    private T data;

    public CommonResult(Result result) {
        this(result.getCode(), result.getMsg(), null);
    }

    public CommonResult(Integer code, String msg) {
        this(code, msg, null);
    }
}
