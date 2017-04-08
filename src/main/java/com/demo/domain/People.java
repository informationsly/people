package com.demo.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * Created by liyong on 2017/4/8.
 * 1.安装mysql数据库和mysqlworkbench,系统偏好设置中打开数据库服务
 * 2.mysql安装在/usr/local/mysql下，添加bin路径到~./bash_profile中
 * 3.mysql -u root -p 登录mysql控制台修改root密码
 * 4.SET PASSWORD = PASSWORD('your new password');
 * 5.使用mysqlworkbench测试连接，连接成功后创建dbpeople数据库（schemes）
 *
 */

@Entity
public class People {
    @Id
    @GeneratedValue
    private Integer id;

    /**
     *
     * @NotEmpty 用在集合类上面
     * @NotBlank 用在String上面
     * @NotNull    用在基本类型上
     *
     */
    @NotBlank(message = "姓名必填")
    private String name;

    //对年龄进行表单校验
    @Min(value = 18, message = "年龄都必须在18岁以上")
    private Integer age;

    private Integer height;

    public People() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}
