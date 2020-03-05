package com.example.demo.adm.service;

import com.example.demo.entity.Picture;
import com.example.demo.entity.Video;

import java.util.List;

public interface AdmService {
//    List<UserTable> searchRegisterUser();
//    int deleteDownLoadByUserID(int userID);
//    int deleteDiscussByUserID(int userID);
//    int deleteUserByName(String userName);
    int deleteAlarmRecordByUserID(int userID);
    int deletePictureByUserID(int userID);
    int deleteUserByUserID(int userID);
    int deleteVideoByUserID(int userID);
    List<Video> searchVideoByUserID(int userID);
    List<Picture> searchPictureByUserID(int userID);
}
