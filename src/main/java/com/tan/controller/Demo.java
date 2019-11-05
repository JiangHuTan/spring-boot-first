package com.tan.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Controller
public class Demo {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("/show")
    public String show(){
        System.out.println("fdsfdsf");
        return "demo";
    }

    @RequestMapping("/get")
    public String get(){
        return "demo2";
    }

    @RequestMapping("/redis")
    public void append(){
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();

    }

    @RequestMapping("/getUsers/{id}")
    @ResponseBody
    public String getUsers(@PathVariable Integer id){
        if (stringRedisTemplate.hasKey(String.valueOf(id))){
            String s = stringRedisTemplate.opsForValue().get(String.valueOf(id));

            return s + "从redis查出数据";
        }else{
            stringRedisTemplate.opsForValue().set(String.valueOf(id),"数字" + id);
            return id + "从数据库返回数据";
        }
    }

    @RequestMapping("/getPojo/{name}")
    @ResponseBody
    public String getPojo(@PathVariable String name){
        if (redisTemplate.hasKey(name)){
            Emp emp = (Emp) redisTemplate.opsForValue().get(name);
            return emp.toString();
        }else{
            redisTemplate.opsForValue().set(name,new Emp(name,23));
            return "从数据库返回数据";
        }
    }
}

class Emp implements Serializable {
    private String name;
    private Integer age;

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

    public Emp() {

    }

    public Emp(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
