package com.xx.study.springbootmybatisplus.service.impl;

import com.xx.study.springbootmybatisplus.pojo.SysUser;
import com.xx.study.springbootmybatisplus.mapper.SysUserMapper;
import com.xx.study.springbootmybatisplus.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author XX
 * @since 2019-03-25
 */
@Transactional
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}
