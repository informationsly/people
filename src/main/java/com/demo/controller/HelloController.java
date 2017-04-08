package com.demo.controller;

import com.demo.properties.PeopleProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * Created by liyong on 2017/4/7.
 * 演示配置内容的注入
 *
 *
 * @Controller 处理http请求
 * 单独使用Controller需要添加模版引擎，结合模版使用，模版放在templates文件夹下，返回模版名称，但现在讲究前后端分离，
 * 模版技术了解就可以，不推荐使用
 *
 *
 * @RestController
 * Spring4之后新加的注解，效果上等同于@Controller和@ResponseBody组合使用
 *
 * @RequestMapping
 * 实现url映射,该注解既可以放在类上，也可以放在方法上，url访问时做相应的拼接
 *
 *
 *
 */
@RestController
public class HelloController{

    //演示配置分组
    @Autowired
    PeopleProperties peopleProperties;

    //从配置文件中获取值，赋予类属性
    @Value("${firstPeople}")
    private String firstPeople;

    //配置文件中的属性，使用配置文件中其他属性的值
    @Value("${content}")
    private String content;

    @RequestMapping(value={"/hello", "/hi"}, method = RequestMethod.GET)
    public String say(){
        return firstPeople;
    }

    @RequestMapping(value = "/origin", method = RequestMethod.GET)
    public String info(){
        return content;
    }

    @RequestMapping(value = "/asian", method = RequestMethod.GET)
    public String asianPeople(){
        return "asian skin color: " + peopleProperties.getSkinColor() + ", average height: " + peopleProperties.getAverageHeight() + "cm";
    }

    /**
     *
     * 演示获取url中的参数
     *
     */

    /**
     * 1.形如：http://localhost:8080/people/find/75727
     * 使用@GetMapping或@PostMapping注解相比@RequestMapping方便，可以省点代码
     */
    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public String withId(@PathVariable Integer id){
        return "url with id: " + id;
    }


    //2.形如：http://localhost:8080/people/find?75727
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public String withQuestionMark(@RequestParam(value = "id", required = false, defaultValue = "666") Integer id){
        return "Parameters with the question mark: " + id;
    }


}
