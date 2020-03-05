package com.example.demo.adm.service.impl;

import com.example.demo.adm.service.AdmService;
import com.example.demo.entity.Picture;
import com.example.demo.entity.Video;
import com.example.demo.mapper.AlarmRecordMapper;
import com.example.demo.mapper.PictureMapper;
import com.example.demo.mapper.UserTableMapper;
import com.example.demo.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdmServiceimpl implements AdmService {
    @Autowired
    AlarmRecordMapper alarmRecordMapper;

    @Autowired
    PictureMapper pictureMapper;

    @Autowired
    UserTableMapper userTableMapper;

    @Autowired
    VideoMapper videoMapper;

    @Override
    public int deleteAlarmRecordByUserID(int userID) {
        return alarmRecordMapper.deleteAlarmRecordByUserID(userID);
    }

    @Override
    public int deletePictureByUserID(int userID) {
        return pictureMapper.deletePictureByUserID(userID);
    }

    @Override
    public int deleteUserByUserID(int userID) {
        return userTableMapper.deleteUserByUserID(userID);
    }

    @Override
    public int deleteVideoByUserID(int userID) {
        return videoMapper.deleteVideoByUserID(userID);
    }

    @Override
    public List<Video> searchVideoByUserID(int userID) {
        return videoMapper.searchVideoByUserID(userID);
    }

    @Override
    public List<Picture> searchPictureByUserID(int userID) {
        return pictureMapper.searchPictureByUserID(userID);
    }
//    @Autowired
//    UserTableMapper userTableMapper;
//    @Autowired
//    DownLoadMapper downLoadMapper;
//    @Autowired
//    DiscussMapper discussMapper;
//
//    @Override
//    public List<UserTable> searchRegisterUser() {
//        return userTableMapper.searchUserTableByUserType(1);
//    }
//
//    @Override
//    public int deleteDownLoadByUserID(int userID) {
//        return downLoadMapper.deleteDownLoadByUserID(userID);
//    }
//
//    @Override
//    public int deleteDiscussByUserID(int userID) {
//        return discussMapper.deleteDiscussByUserID(userID);
//    }
//
//    @Override
//    public int deleteUserByName(String userName) {
//        return userTableMapper.deleteUserTableByUserName(userName);
//    }
}
