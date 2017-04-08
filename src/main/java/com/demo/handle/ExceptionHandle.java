package com.demo.handle;

import com.demo.domain.Result;
import com.demo.enums.ResultEnum;
import com.demo.exception.PeopleException;
import com.demo.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by liyong on 2017/4/8.
 */

@ControllerAdvice
public class ExceptionHandle {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if(e instanceof PeopleException){
            PeopleException peopleException = (PeopleException)e;
            return ResultUtil.error(peopleException.getCode(), peopleException.getMessage());
        }else{
            logger.error("系统异常{}", e);//方便后台定位错误
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMsg());
        }
    }
}
