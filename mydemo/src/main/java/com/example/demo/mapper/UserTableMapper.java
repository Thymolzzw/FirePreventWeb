package com.example.demo.mapper;

import com.example.demo.entity.UserTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserTableMapper {
    UserTable searchUserByUserNameAndPasswordAndUserRight(@Param("userName") String userName, @Param("userPwd") String userPwd,
                                                          @Param("userRight") int userRight);
    UserTable searchUserByID(int userID);
    int updateUser(UserTable userTable);
    List<UserTable> searchUserAll();
    int deleteUserByUserID(int userID);
    UserTable searchUserByName(String userName);
    int insertUser(UserTable userTable);
}
