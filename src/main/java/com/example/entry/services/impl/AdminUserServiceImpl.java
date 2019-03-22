package com.example.entry.services.impl;

import com.example.entry.dao.IAdminUserDao;
import com.example.entry.pojo.vo.AdminUserVO;
import com.example.entry.services.IAdminUserService;
import com.example.entry.util.MD5CoderUtils;
import com.example.entry.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @ClassName
 * @Auther: Administrator
 * @Date: 2019/3/21 17:31
 * @Description:
 * @Version 1.0
 */
@Service
public class AdminUserServiceImpl implements IAdminUserService {

    @Autowired
    IAdminUserDao adminUserDao;

    @Override
    public String login(String account, String pwd) throws Exception {
        AdminUserVO vo = adminUserDao.queryByAccount(account);
        if (vo == null) {
            return ResultUtil.LOGIN_ACCOUNT_ERROR();
        }
        String str1 = MD5CoderUtils.encodeMD5Hex(pwd);
        if (!str1.equals(vo.getPwd())) {
            return ResultUtil.LOGIN_PASSWORD_ERROR();
        }

        return ResultUtil.OK(vo);
    }

    @Transactional
    @Override
    public String register(String name, String account, String pwd) throws Exception {
        AdminUserVO vo = adminUserDao.queryByAccount(account);
        if (vo != null) {// 判断帐号是否存在
            return ResultUtil.ACCOUNT_EXIST();
        }
        vo = new AdminUserVO();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        vo.setId(uuid);
        vo.setAccount(account);
        vo.setName(name);
        vo.setPwd(MD5CoderUtils.encodeMD5Hex(pwd));

        adminUserDao.add(vo);

        return ResultUtil.OK(vo);
    }

    @Override
    public String updatePwd(String account, String pwd, String newPwd) throws Exception {
        AdminUserVO vo = adminUserDao.queryByAccount(account);
        String str1 = MD5CoderUtils.encodeMD5Hex(pwd);
        String str2 = MD5CoderUtils.encodeMD5Hex(newPwd);
        if (!vo.getPwd().equals(str1)) {
            return ResultUtil.LOGIN_PASSWORD_ERROR();
        }else {
            int result = adminUserDao.updateUser(vo.getId(),vo.getName(),str2,vo.getAccount());
            if (result > 0) {
                return ResultUtil.OK();
            } else {
                return ResultUtil.UPDATE_PASSWORD_ERROR();
            }
        }

    }
}
