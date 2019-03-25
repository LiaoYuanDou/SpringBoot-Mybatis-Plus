package com.xx.study.springbootmybatisplus.service.impl;

import com.xx.study.springbootmybatisplus.pojo.User;
import com.xx.study.springbootmybatisplus.mapper.UserMapper;
import com.xx.study.springbootmybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XX
 * @since 2019-03-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
