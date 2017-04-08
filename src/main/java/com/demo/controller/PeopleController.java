package com.demo.controller;

import com.demo.domain.People;
import com.demo.domain.Result;
import com.demo.repository.PeopleRepository;
import com.demo.service.PeopleService;
import com.demo.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by liyong on 2017/4/8.
 * 处理url请求
 */

@RestController
public class PeopleController {

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private PeopleService peopleService;

    /**
     * 查询people表中的所有数据
     * @return
     */
    @GetMapping(value = "/peoples")
    public List<People> peopleList(){
        return peopleRepository.findAll();
    }

    /**
     * 向people表中添加一个人
     * 第一版：People属性单个分别接收
     * 第二版：直接接收people这个类对象，方便对类字段进行校验
     * 对people中的数据进行校验，校验结果绑定在bindingResult上
     *
     * 1.异常返回或正常返回都做统一的处理
     * 2.代码写重复了，要做工具类（ResultUtil）进行抽取
     *
     */
    @PostMapping(value = "/peoples")
    public Result<People> peopleAdd(@Valid People people, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultUtil.success(peopleRepository.save(people));
    }

    /**
     * 查询一个人
     * @param id
     * @return
     */
    @GetMapping(value = "/peoples/{id}")
    public People peopleFindOne(@PathVariable("id") Integer id){
        return peopleRepository.findOne(id);
    }

    /**
     * 更新数据
     * @param id
     * @param height
     * @param age
     * @return
     */
    @PutMapping(value = "/peoples/{id}")
    public People peopleUpdate(@PathVariable("id") Integer id,
                             @RequestParam("height") Integer height,
                             @RequestParam("age") Integer age){

        People people = new People();
        people.setId(id);
        people.setHeight(height);
        people.setAge(age);

        //id对应的数据存在就更新(没有穿值的字段，值为null)，不存在就添加
        return peopleRepository.save(people);
    }

    /**
     * 根据id，删除people表中的一条数据
     * @param id
     */
    @DeleteMapping(value = "/peoples/{id}")
    public void peopleDelete(@PathVariable("id") Integer id){
        peopleRepository.delete(id);
    }

    /**
     * 自定义查询：根据年龄查询
     * @param age
     * @return
     */
    @GetMapping(value = "peoples/age/{age}")
    public List<People> peopleListByAge(@PathVariable("age") Integer age){
        return peopleRepository.findByAge(age);
    }

    @PostMapping(value = "/peoples/two")
    public void peopleTwo(){
        peopleService.insertTwo();
    }

    //统一异常处理
    @GetMapping(value = "/peoples/getAge/{id}")
    public void getPeopleType(@PathVariable Integer id) throws Exception {
        peopleService.getType(id);
    }
}
