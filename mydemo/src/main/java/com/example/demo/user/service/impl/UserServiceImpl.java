package com.example.demo.user.service.impl;

import com.example.demo.entity.*;
import com.example.demo.mapper.*;
import com.example.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    PictureMapper pictureMapper;
    @Autowired
    VideoMapper videoMapper;

    @Autowired
    AlarmRecordMapper alarmRecordMapper;

    @Autowired
    UserTableMapper userTableMapper;

    @Override
    public Picture searchPictureByName(String imgName) {
        return pictureMapper.searchPictureByName(imgName);
    }

    @Override
    public int insertPicture(Picture picture) {
        return pictureMapper.insertPicture(picture);
    }

    @Override
    public List<Picture> searchPictureAll() {
        return pictureMapper.searchPictureAll();
    }


    @Override
    public Picture searchPictureByID(int imgID) {
        return pictureMapper.searchPictureByID(imgID);
    }

    @Override
    public int deletePictureByID(int imgID) {
        return pictureMapper.deletePictureByImgID(imgID);
    }

    @Override
    public Video searchVideoByName(String vidName) {
        return videoMapper.searchByName(vidName);
    }

    @Override
    public List<Video> searchVideoAll() {
        return videoMapper.searchVideoAll();
    }

    @Override
    public int insertVideo(Video video) {
        return videoMapper.insertVideo(video);
    }

    @Override
    public Video searchVideoByID(int vidID) {
        return videoMapper.searchVideoByID(vidID);
    }

    @Override
    public int deleteVideoByID(int vidID) {
        return videoMapper.deleteVideoByID(vidID);
    }

    @Override
    public int insertAlarmRecord(AlarmRecord alarmRecord) {
        return alarmRecordMapper.insertAlarmRecord(alarmRecord);
    }

    @Override
    public List<AlarmRecord> searchAlarmRecordAll() {
        return alarmRecordMapper.searchAlarmRecordAll();
    }

    @Override
    public AlarmRecord searchAlarmRecordByID(int almRecID) {
        return alarmRecordMapper.searchAlarmRecordByID(almRecID);
    }

    @Override
    public int deleteAlarmRecordByID(int almRecID) {
        return alarmRecordMapper.deleteAlarmRecordByID(almRecID);
    }

    @Override
    public int updateAlarmRecord(AlarmRecord alarmRecord) {
        return alarmRecordMapper.updateAlarmRecord(alarmRecord);
    }

    @Override
    public UserTable searchUserByID(int userID) {
        return userTableMapper.searchUserByID(userID);
    }

    @Override
    public int updateUser(UserTable userTable) {
        return userTableMapper.updateUser(userTable);
    }

    @Override
    public List<UserTable> searchUserAll() {
        return userTableMapper.searchUserAll();
    }

    @Override
    public UserTable searchUserByName(String userName) {
        return userTableMapper.searchUserByName(userName);
    }

    @Override
    public int insertUser(UserTable userTable) {
        return userTableMapper.insertUser(userTable);
    }
//    @Autowired
//    ResourceTableMapper resourceTableMapper;
//    @Autowired
//    BelongMapper belongMapper;
//    @Autowired
//    RelationMapper relationMapper;
//    @Autowired
//    DiscussMapper discussMapper;
//    @Autowired
//    UserTableMapper userTableMapper;
//    @Autowired
//    TerritoryTableMapper territoryTableMapper;
//    @Autowired
//    DownLoadMapper downLoadMapper;
//
//
//    @Override
//    @Transactional
//    public int addResource(ResourceTable resourceTable) {
//        return resourceTableMapper.insertResource(resourceTable);
//    }
//
//    @Override
//    @Transactional
//    public int addBelong(Belong belong) {
//        return belongMapper.insertBelong(belong);
//    }
//
//    @Override
//    public int addRelation(Relation relation) {
//        return relationMapper.insertRelation(relation);
//    }
//
//    @Override
//    public int addDiscuss(Discuss discuss) {
//        return discussMapper.insertDiscuss(discuss);
//    }
//
//    @Override
//    public int addUser(UserTable userTable) {
//        return userTableMapper.insertUserTable(userTable);
//    }
//
//    @Override
//    public int addDownLoad(DownLoad downLoad) {
//        return downLoadMapper.insertDownLoad(downLoad);
//    }
//
//    @Override
//    @Transactional
//    public ResourceTable searchResourceByName(String resourceName) {
//        return resourceTableMapper.searchResourceByName(resourceName);
//    }
//
//    @Override
//    public List<ResourceTable> searchResourceByUserID(int userID) {
//        return resourceTableMapper.searchResourceByUserID(userID);
//    }
//
//    @Override
//    public List<ResourceTable> searchResourceByType(int resourceType) {
//        return resourceTableMapper.searchResourceByType(resourceType);
//    }
//
//    @Override
//    public List<ResourceTable> resourceList(int pagenum, int pagesize) {
//        return resourceTableMapper.resourceList(pagenum,pagesize);
//    }
//
//    @Override
//    public ResourceTable searchResourceByID(int resourceID) {
//        return resourceTableMapper.searchResourceByID(resourceID);
//    }
//
//    @Override
//    public List<Belong> searchBelongByResourceID(int resourceID) {
//        return belongMapper.searchBelongByResourceID(resourceID);
//    }
//
//    @Override
//    public List<Belong> searchBelongByTerritoryID(int territoryID) {
//        return belongMapper.searchBelongByTerritoryID(territoryID);
//    }
//
//    @Override
//    public List<Discuss> searchDiscussByResourceID(int resourceID) {
//        return discussMapper.searchDiscussByResourceID(resourceID);
//    }
//
//    @Override
//    public List<Relation> searchRelationByOne(int resourceID) {
//        Relation re=new Relation();
//        re.setResourceIDOne(resourceID);
//        return relationMapper.searchRelationByOne(re);
//    }
//
//    @Override
//    public List<Relation> searchRelationByTwo(int resourceID) {
//        Relation re=new Relation();
//        re.setResourceIDTwo(resourceID);
//        return relationMapper.searchRelationByTwo(re);
//    }
//
//    @Override
//    public Relation searchRelationByOneAndTwo(Relation relation) {
//        return relationMapper.searchRelationByOneAndTwo(relation);
//    }
//
//    @Override
//    public UserTable searchUserByID(int userID) {
//        return userTableMapper.searchUserTableByUserID(userID);
//    }
//
//    @Override
//    public UserTable searchUserByName(String userName) {
//        return userTableMapper.searchUserTableByUserName(userName);
//    }
//
//    @Override
//    public TerritoryTable searchTerritoryByID(int territoryID) {
//        return territoryTableMapper.searchByID(territoryID);
//    }
//
//    @Override
//    public List<DownLoad> searchDownLoadByResourceID(int resourceID) {
//        return downLoadMapper.searchDownLoadByResourceID(resourceID);
//    }
//
//    @Override
//    public Belong searchBelongByResourceIDAndTerritoryID(int resourceID, int territoryID) {
//        return belongMapper.searchBelongByResourceIDAndTerritoryID(resourceID,territoryID);
//    }
//
//    @Override
//    public int updateResource(ResourceTable resourceTable) {
//        return resourceTableMapper.updateResource(resourceTable);
//    }
//
//    @Override
//    public int deleteBelongByResourceID(int resourceID) {
//        return belongMapper.deleteBelongByResourceID(resourceID);
//    }
//
//    @Override
//    public int deleteRelationByResourceID(int resourceID) {
//        return relationMapper.deleteRelationByResourceID(resourceID);
//    }
//
//    @Override
//    public int deleteResourceByID(int resourceID) {
//        return resourceTableMapper.deleteResourceByID(resourceID);
//    }
//
//    @Override
//    public int deleteDiscussByResourceID(int resourceID) {
//        return discussMapper.deleteDiscussByResourceID(resourceID);
//    }
//
//    @Override
//    public int deleteDownLoadByResourceID(int resourceID) {
//        return downLoadMapper.deleteDownLoadByResourceID(resourceID);
//    }

}
