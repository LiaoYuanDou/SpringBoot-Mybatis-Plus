package com.xx.study.springbootmybatisplus.service.impl;

import com.xx.study.springbootmybatisplus.pojo.User;
import com.xx.study.springbootmybatisplus.mapper.UserMapper;
import com.xx.study.springbootmybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author XX
 * @since 2019-03-25
 */
@Transactional
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public User getByUserId(int id) {
        return baseMapper.selectById(id);
    }
}
