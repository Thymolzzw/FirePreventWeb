package com.example.demo.mapper;

import com.example.demo.entity.Picture;

import java.util.List;

public interface PictureMapper {
    Picture searchPictureByName(String imgName);
    int insertPicture(Picture picture);
    List<Picture> searchPictureAll();
    Picture searchPictureByID(int imgID);
    int deletePictureByImgID(int imgID);
    int deletePictureByUserID(int userID);
    List<Picture> searchPictureByUserID(int userID);

}
