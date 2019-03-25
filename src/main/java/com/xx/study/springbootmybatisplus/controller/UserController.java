package com.xx.study.springbootmybatisplus.controller;


import com.xx.study.springbootmybatisplus.pojo.User;
import com.xx.study.springbootmybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author XX
 * @since 2019-03-25
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "getByUserId",method = RequestMethod.GET)
    public void getByUserId(@RequestParam("Id")int Id){
       User user =  userService.getByUserId(Id);
        System.out.println(user);
    }
}
