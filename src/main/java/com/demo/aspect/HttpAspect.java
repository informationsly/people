package com.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liyong on 2017/4/8.
 * AOP处理请求事例
 *
 *
 * @Component 把类引入的spring容器里，在其他类中才可以使用IOC注入
 *
 */

@Aspect
@Component
public class HttpAspect {

    /**
     * 1.假设只拦截com.demo.controller.PeopleController类中的peopleList方法：
     * @Before("execution(public * com.demo.controller.PeopleController.peopleList(..))")
     *
     * 2.希望拦截com.demo.controller.PeopleController类中所有的方法
     * @Before("execution(public * com.demo.controller.PeopleController.*(..))")
     *
     * 3.如果请求前和请求后的拦截处理都是相似的，应该对代码进行抽取
     *  @Pointcut("execution(public * com.demo.controller.PeopleController.*(..))")
     *
     * 4.对请求的url,method(Get,Post...),ip,类方法，参数，进行记录
     *
     * 5.如何获取返回的内容
     * @AfterReturning(returning = "object", pointcut = "log()")
     *
     *
     */

    //org.slg4j.Logger相比System.out.println能显示时间戳，在那个类中打印等有利于调试的信息
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    //切PeopleController中的所有方法
    @Pointcut("execution(public * com.demo.controller.PeopleController.*(..))")
    public void log(){

    }

    //对请求的url,method(Get,Post...),ip,类方法，参数，进行记录
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("url={}", request.getRequestURL());
        logger.info("method={}", request.getMethod());
        logger.info("ip={}", request.getRemoteAddr());
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("args={}", joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter(){
        logger.info("2222222222222222\n");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void getReturnning(Object object){
        logger.info("response={}", object);
    }
}
