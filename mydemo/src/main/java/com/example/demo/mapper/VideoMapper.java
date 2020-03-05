package com.example.demo.mapper;

import com.example.demo.entity.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoMapper {
    int insertVideo(Video video);
    Video searchByName(String vidName);
    List<Video> searchVideoAll();
    Video searchVideoByID(int vidID);
    int deleteVideoByID(int vidID);
    int deleteVideoByUserID(int userID);
    List<Video> searchVideoByUserID(int userID);
}
