package com.xx.study.springbootmybatisplus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xx.study.springbootmybatisplus.pojo.SysUser;
import com.xx.study.springbootmybatisplus.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author XX
 * @since 2019-03-25
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public void test() {
        //测试逻辑删除功能
        SysUser sysUser = sysUserService.getById(1);

        //测试分页
        IPage<SysUser> sysUserIPage = sysUserService.page(
                new Page<SysUser>(1, 10), new QueryWrapper<>());

        //测试公共字段自动填充
        SysUser sysUser1 = new SysUser();
        sysUser1.setUsername("shen");
        sysUser1.setNickname("shen");
        sysUser1.setPassword("shen");
        sysUserService.save(sysUser1);

        //测试乐观锁
        SysUser sysUser2 = new SysUser();
        sysUser2.setId(19);
        sysUser2.setUsername("shen2");
        sysUser2.setNickname("shen2");
        sysUser2.setPassword("shen2");
        sysUser2.setUpdateVersion(1);
        sysUserService.updateById(sysUser2);
    }
}
