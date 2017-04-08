package com.demo.exception;

import com.demo.enums.ResultEnum;

/**
 * spring框架对继承RuntimeException的异常才会进行事务回滚
 * Created by liyong on 2017/4/8.
 */
public class PeopleException extends RuntimeException {

    private Integer code;

    public PeopleException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
