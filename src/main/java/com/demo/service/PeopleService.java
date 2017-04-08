package com.demo.service;

import com.demo.domain.People;
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
}
