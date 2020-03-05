package com.example.demo.common.service.impl;

import com.example.demo.common.service.CommonService;
import com.example.demo.entity.UserTable;
import com.example.demo.mapper.UserTableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommonServiceImpl implements CommonService {
    @Autowired
    UserTableMapper userMapper;

    @Override
    public UserTable userLogin(UserTable user) {
        UserTable myuser=userMapper.searchUserByUserNameAndPasswordAndUserRight(user.getUserName(),user.getUserPwd(),user.getUserRight());
        if(myuser==null){
            System.out.println("没有此用户！");

        }else{
            System.out.println("查找到此用户，用户ID为 "+myuser.getUserID());
            System.out.println("-------------用户名为 "+myuser.getUserName());
            System.out.println("-------------用户密码为 "+myuser.getUserPwd());
            System.out.println("-------------用户类型为 "+myuser.getUserRight());
            System.out.println("-------------用户入职时间为 "+myuser.getEmpTime());
            System.out.println("-------------用户邮箱为 "+myuser.getUserMail());
            System.out.println("-------------用户手机号为 "+myuser.getUserPhone());
            System.out.println("-------------用户部门为 "+myuser.getUserDept());
        }
        return myuser;
    }

//    @Override
//    @Transactional
//    public int updateUser(UserTable userTable) {
//        return userTableMapper.updateUserTableByUserID(userTable);
//    }
}
