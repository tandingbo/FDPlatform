package com.zsinda.fdp.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsinda.fdp.entity.SysRole;
import com.zsinda.fdp.entity.SysRoleMenu;
import com.zsinda.fdp.mapper.SysRoleMapper;
import com.zsinda.fdp.mapper.SysRoleMenuMapper;
import com.zsinda.fdp.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysRole> findRoleById(Integer userId) {
        return baseMapper.listRolesByUserId(userId);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteRoleById(Integer id) {
        sysRoleMenuMapper.delete(Wrappers
                .<SysRoleMenu>update().lambda()
                .eq(SysRoleMenu::getRoleId, id));
        return removeById(id);
    }
}
