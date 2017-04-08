package com.demo.repository;

import com.demo.domain.People;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by liyong on 2017/4/8.
 * 继承JpaRepository，可以使用一些简单的查询
 *
 */
public interface PeopleRepository extends JpaRepository<People, Integer>{

    /**
     * 通过年龄查询
     * 方法名不能随便写，需要按照指定的格式书写
     */
    public List<People> findByAge(Integer age);

}
