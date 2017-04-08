package com.demo.service;

import com.demo.domain.People;
import com.demo.enums.ResultEnum;
import com.demo.exception.PeopleException;
import com.demo.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liyong on 2017/4/8.
 */

@Service
public class PeopleService {

    @Autowired
    PeopleRepository peopleRepository;

    /**
     * 演示事务处理
     * 导入的Transactional是spring包里的，非javax包
     */
    @Transactional
    public void insertTwo(){
        People peopleB = new People();
        peopleB.setName("xiaoxi");
        peopleB.setAge(19);
        peopleB.setHeight(161);
        peopleRepository.save(peopleB);

        People peopleA = new People();
        peopleA.setName("xiaohua");
        peopleA.setAge(19);
        peopleA.setHeight(161);
        peopleRepository.save(peopleA);
    }

    /**
     * 统一异常处理
     * 1.初级做法：使用new Exception("这个人还是未成年")抛出异常扩展性不够。
     * 2.更好的做法：继承RuntimeException抛出自定义的异常
     * 3.创建ResultEnum对错误码进行统一管理
     *
     */

    public void getType(Integer id) throws Exception {
        People people = peopleRepository.findOne(id);
        Integer age = people.getAge();
        if(age < 18){
            //未成年
            throw new PeopleException(ResultEnum.MINOR);
        }else if(age > 18 && age < 60){
            //中年
            throw new PeopleException(ResultEnum.MIDLIFE);
        }
    }
}
