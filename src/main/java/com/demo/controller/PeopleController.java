package com.demo.controller;

import com.demo.domain.People;
import com.demo.repository.PeopleRepository;
import com.demo.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @param name
     * @param age
     * @return
     */
    @PostMapping(value = "/peoples")
    public People peopleAdd(@RequestParam("name") String name,
                            @RequestParam("age") Integer age){
        People people = new People();
        people.setName(name);
        people.setAge(age);
        //save方法返回添加的对象
        return peopleRepository.save(people);
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
}
