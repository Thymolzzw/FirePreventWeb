package com.example.demo.user.controller;



import com.example.demo.entity.AlarmRecord;
import com.example.demo.entity.Picture;
import com.example.demo.entity.UserTable;
import com.example.demo.entity.Video;
import com.example.demo.mapper.PictureMapper;
import com.example.demo.user.service.UserService;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;


    @RequestMapping("/uploadPicture")
    public String uploadPicture(HttpServletRequest request){

        return "user/uploadPicture";
    }
    @RequestMapping("/uploadVideo")
    public String uploadVideo(HttpServletRequest request){

        return "user/uploadVideo";
    }

    @RequestMapping("/doUploadPicture")
    public String doUploadPicture(HttpServletRequest request, MultipartHttpServletRequest multiReq){
        UserTable user= (UserTable) request.getSession().getAttribute("user");
        System.out.println("用户:"+user.getUserName());
        String imgType=request.getParameter("imgType");
        System.out.println("图片类型:"+imgType);
        String opLocX=request.getParameter("opLocX");
        String opLocY=request.getParameter("opLocY");
        System.out.println("opLocX:"+opLocX);
        System.out.println("opLocY:"+opLocY);
        String uploadPath ="D:\\IDEA_workspace\\森林防火\\mydemo\\src\\main\\resources\\static\\imgs\\";

        String uploadFilePath = multiReq.getFile("myfile").getOriginalFilename();
        if(uploadFilePath.trim().length()==0){
            return "/common/nofileerror";
        }
        System.out.println("上传文件的路径:" + uploadFilePath);
        // 截取上传文件的文件名
        String uploadFileName = uploadFilePath.substring(
                uploadFilePath.lastIndexOf('\\') + 1, uploadFilePath.indexOf('.'));
        System.out.println("上传文件的文件名" + uploadFileName);
        if(userService.searchPictureByName(uploadFilePath)!=null){
            return "/common/samefileerror";
        }

        // 截取上传文件的后缀
        String uploadFileSuffix = uploadFilePath.substring(
                uploadFilePath.indexOf('.') + 1, uploadFilePath.length());
        System.out.println("uploadFileSuffix:" + uploadFileSuffix);
        FileOutputStream fos = null;
        FileInputStream fis = null;
        String resourcelocation=uploadPath+ uploadFileName+"."+uploadFileSuffix;
        System.out.println("服务器存放地址："+resourcelocation);
        try {
            fis = (FileInputStream) multiReq.getFile("myfile").getInputStream();
            fos = new FileOutputStream(new File( uploadPath+ uploadFileName
                    + ".")
                    + uploadFileSuffix);
            byte[] temp = new byte[1024];
            int i = fis.read(temp);
            while (i != -1){
                fos.write(temp,0,temp.length);
                fos.flush();
                i = fis.read(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Picture picture=new Picture();
        picture.setCreateTime(new Timestamp(System.currentTimeMillis()));
        picture.setImgName(uploadFilePath);
        picture.setImgType(Integer.parseInt(imgType));
        picture.setImgUrl(resourcelocation);
        picture.setOpLocX(Double.parseDouble(opLocX));
        picture.setOpLocY(Double.parseDouble(opLocY));
        picture.setStaffID(user.getUserID());
        userService.insertPicture(picture);

        return "redirect:/uploadPicture";
    }

    @RequestMapping(value = "/doUploadVideo")
    public String doUploadVideo(HttpServletRequest request, MultipartHttpServletRequest multiReq){
        UserTable user= (UserTable) request.getSession().getAttribute("user");
        System.out.println("用户:"+user.getUserName());
        String vidType=request.getParameter("vidType");
        System.out.println("视频类型:"+vidType);
        String opLocX=request.getParameter("opLocX");
        String opLocY=request.getParameter("opLocY");
        System.out.println("opLocX:"+opLocX);
        System.out.println("opLocY:"+opLocY);
        String uploadPath ="D:\\IDEA_workspace\\森林防火\\mydemo\\src\\main\\resources\\static\\videos\\";

        String uploadFilePath = multiReq.getFile("myfile").getOriginalFilename();
        if(uploadFilePath.trim().length()==0){
            return "/common/nofileerror";
        }
        System.out.println("上传文件的路径:" + uploadFilePath);
        // 截取上传文件的文件名
        String uploadFileName = uploadFilePath.substring(
                uploadFilePath.lastIndexOf('\\') + 1, uploadFilePath.indexOf('.'));
        System.out.println("上传文件的文件名" + uploadFileName);
        if(userService.searchPictureByName(uploadFilePath)!=null){
            return "/common/samefileerror";
        }

        // 截取上传文件的后缀
        String uploadFileSuffix = uploadFilePath.substring(
                uploadFilePath.indexOf('.') + 1, uploadFilePath.length());
        System.out.println("uploadFileSuffix:" + uploadFileSuffix);
        FileOutputStream fos = null;
        FileInputStream fis = null;
        String resourcelocation=uploadPath+ uploadFileName+"."+uploadFileSuffix;
        System.out.println("服务器存放地址："+resourcelocation);
        try {
            fis = (FileInputStream) multiReq.getFile("myfile").getInputStream();
            fos = new FileOutputStream(new File( uploadPath+ uploadFileName
                    + ".")
                    + uploadFileSuffix);
            byte[] temp = new byte[1024];
            int i = fis.read(temp);
            while (i != -1){
                fos.write(temp,0,temp.length);
                fos.flush();
                i = fis.read(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Video video=new Video();
        video.setVidStartTime(new Timestamp(System.currentTimeMillis()));
        video.setVidName(uploadFilePath);
        video.setVidType(Integer.parseInt(vidType));
        video.setVidUrl(resourcelocation);
        video.setOpLocX(Double.parseDouble(opLocX));
        video.setOpLocY(Double.parseDouble(opLocY));
        video.setStaffID(user.getUserID());
        userService.insertVideo(video);


        return "redirect:/uploadVideo";
    }

    @RequestMapping(value = "/findImg")
    public String findImg(HttpServletRequest request){
        List<Picture> pictureList=userService.searchPictureAll();
        request.setAttribute("imgList",pictureList);
        return "/common/findImg";
    }
    @RequestMapping(value = "/findImgapp")
    public String findImgapp(HttpServletRequest request){
        List<Picture> pictureList=userService.searchPictureAll();
        request.setAttribute("imgList",pictureList);
        return "/common/findImgapp";
    }

    @RequestMapping(value = "/imgSee/{imgID}")
    public String imgSee(HttpServletRequest request, @PathVariable String imgID){
        System.out.println("imgID："+imgID);
        Picture picture=userService.searchPictureByID(Integer.parseInt(imgID));
        System.out.println("picture:"+picture);
        System.out.println("url:"+picture.getImgUrl());
        request.setAttribute("thePictureToSee",picture);
        String url="../imgs/"+picture.getImgName();
        request.setAttribute("imgSeeUrl",url);

        return "/common/imgSee";
    }

    @RequestMapping(value = "/imgSeeapp/{imgID}")
    public String imgSeeapp(HttpServletRequest request, @PathVariable String imgID){
        System.out.println("imgID："+imgID);
        Picture picture=userService.searchPictureByID(Integer.parseInt(imgID));
        System.out.println("picture:"+picture);
        System.out.println("url:"+picture.getImgUrl());
        request.setAttribute("thePictureToSee",picture);
        String url="../imgs/"+picture.getImgName();
        request.setAttribute("imgSeeUrl",url);

        return "/common/imgSeeapp";
    }

    @RequestMapping(value = "/downloadImg/{imgID}")
    public void downloadImg(HttpServletRequest request, @PathVariable String imgID, HttpServletResponse response){
        System.out.println("imgID："+imgID);
        Picture picture=userService.searchPictureByID(Integer.parseInt(imgID));
        System.out.println("picture:"+picture);
        System.out.println("url:"+picture.getImgUrl());

        String downloadFilePath ="D:\\IDEA_workspace\\森林防火\\mydemo\\src\\main\\resources\\static\\imgs\\";
        String fileName=picture.getImgName();
        File file = new File(downloadFilePath+fileName);
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开            
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream outputStream = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {

            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    @RequestMapping(value = "/deleteImg")
    public String deleteImg(HttpServletRequest request){
        String[] deleteList=request.getParameterValues("toDeleteImg");
        for (String imgID:deleteList){

            Picture picture=userService.searchPictureByID(Integer.parseInt(imgID));
            File file=new File(picture.getImgUrl());
            boolean flag1=file.delete();
            if(flag1){
                System.out.println("删除物理文件成功！");
            }else{
                System.out.println("删除物理文件失败！");
            }
            int flag=userService.deletePictureByID(Integer.parseInt(imgID));
            if(flag==0){
                System.out.println("删除失败！"+imgID);
            }else{
                System.out.println("删除成功！"+imgID);
            }

        }

        return "redirect:/findImg";
    }
    @RequestMapping(value = "/reloadImg")
    public String reloadImg(HttpServletRequest request){
        String imgName=request.getParameter("imgName");
        String imgType=request.getParameter("imgType");
        System.out.println("imgName:"+imgName);
        System.out.println("imgType:"+imgType);
        List<Picture> pictureList=userService.searchPictureAll();
        if(!imgType.equals("")){
            int type=Integer.parseInt(imgType);
            for(int i=0;i<pictureList.size();i++){
                if(pictureList.get(i).getImgType()!=type){
                    pictureList.remove(i);
                    i--;
                }
            }
        }
        if(!imgName.equals("")){
            for(int i=0;i<pictureList.size();i++){
                if(!pictureList.get(i).getImgName().contains(imgName)){
                    pictureList.remove(i);
                    i--;
                }
            }
        }

        request.setAttribute("imgList",pictureList);
        return "/common/findImg";
    }

    @RequestMapping(value = "/reloadImgapp")
    public String reloadImgapp(HttpServletRequest request){
        String imgName=request.getParameter("imgName");
        String imgType=request.getParameter("imgType");
        System.out.println("imgName:"+imgName);
        System.out.println("imgType:"+imgType);
        List<Picture> pictureList=userService.searchPictureAll();
        if(!imgType.equals("")){
            int type=Integer.parseInt(imgType);
            for(int i=0;i<pictureList.size();i++){
                if(pictureList.get(i).getImgType()!=type){
                    pictureList.remove(i);
                    i--;
                }
            }
        }
        if(!imgName.equals("")){
            for(int i=0;i<pictureList.size();i++){
                if(!pictureList.get(i).getImgName().contains(imgName)){
                    pictureList.remove(i);
                    i--;
                }
            }
        }

        request.setAttribute("imgList",pictureList);
        return "/common/findImgapp";
    }

    @RequestMapping(value = "/findVideo")
    public String findVideo(HttpServletRequest request){
        List<Video> videoList=userService.searchVideoAll();
        request.setAttribute("videoList",videoList);
        return "/common/findVideo";
    }

    @RequestMapping(value = "/findVideoapp")
    public String findVideoapp(HttpServletRequest request){
        List<Video> videoList=userService.searchVideoAll();
        request.setAttribute("videoList",videoList);
        return "/common/findVideoapp";
    }

    @RequestMapping(value = "/videoSee/{vidID}")
    public String videoSee(HttpServletRequest request, @PathVariable String vidID){
        System.out.println("vidID："+vidID);
        Video video=userService.searchVideoByID(Integer.parseInt(vidID));
        System.out.println("video:"+video);
        System.out.println("url:"+video.getVidUrl());
        request.setAttribute("theVideoToSee",video);
        String url="../videos/"+video.getVidName();
        request.setAttribute("videoSeeUrl",url);

        return "/common/videoSee";
    }

    @RequestMapping(value = "/videoSeeapp/{vidID}")
    public String videoSeeapp(HttpServletRequest request, @PathVariable String vidID){
        System.out.println("vidID："+vidID);
        Video video=userService.searchVideoByID(Integer.parseInt(vidID));
        System.out.println("video:"+video);
        System.out.println("url:"+video.getVidUrl());
        request.setAttribute("theVideoToSee",video);
        String url="../videos/"+video.getVidName();
        request.setAttribute("videoSeeUrl",url);

        return "/common/videoSeeapp";
    }
    @RequestMapping(value = "/deleteVideo")
    public String deleteVideo(HttpServletRequest request){
        String[] deleteList=request.getParameterValues("toDeleteVideo");
        for (String vidID:deleteList){

            Video video=userService.searchVideoByID(Integer.parseInt(vidID));
            File file=new File(video.getVidUrl());
            boolean flag1=file.delete();
            if(flag1){
                System.out.println("删除物理文件成功！");
            }else{
                System.out.println("删除物理文件失败！");
            }
            int flag=userService.deleteVideoByID(Integer.parseInt(vidID));
            if(flag==0){
                System.out.println("删除失败！"+vidID);
            }else{
                System.out.println("删除成功！"+vidID);
            }

        }

        return "redirect:/findVideo";
    }

    @RequestMapping(value = "/reloadVideo")
    public String reloadVideo(HttpServletRequest request){
        String vidName=request.getParameter("vidName");
        String vidType=request.getParameter("vidType");
        System.out.println("vidName:"+vidName);
        System.out.println("vidType:"+vidType);
        List<Video> videoList=userService.searchVideoAll();
        if(!vidType.equals("")){
            int type=Integer.parseInt(vidType);
            for(int i=0;i<videoList.size();i++){
                if(videoList.get(i).getVidType()!=type){
                    videoList.remove(i);
                    i--;
                }
            }
        }
        if(!vidName.equals("")){
            for(int i=0;i<videoList.size();i++){
                if(!videoList.get(i).getVidName().contains(vidName)){
                    videoList.remove(i);
                    i--;
                }
            }
        }

        request.setAttribute("videoList",videoList);
        return "/common/findVideo";
    }

    @RequestMapping(value = "/reloadVideoapp")
    public String reloadVideoapp(HttpServletRequest request){
        String vidName=request.getParameter("vidName");
        String vidType=request.getParameter("vidType");
        System.out.println("vidName:"+vidName);
        System.out.println("vidType:"+vidType);
        List<Video> videoList=userService.searchVideoAll();
        if(!vidType.equals("")){
            int type=Integer.parseInt(vidType);
            for(int i=0;i<videoList.size();i++){
                if(videoList.get(i).getVidType()!=type){
                    videoList.remove(i);
                    i--;
                }
            }
        }
        if(!vidName.equals("")){
            for(int i=0;i<videoList.size();i++){
                if(!videoList.get(i).getVidName().contains(vidName)){
                    videoList.remove(i);
                    i--;
                }
            }
        }

        request.setAttribute("videoList",videoList);
        return "/common/findVideoapp";
    }

    @RequestMapping(value = "/createVideoAlarm/{vidID}")
    public String createVideoAlarm(@PathVariable String vidID){
        System.out.println("vidID:"+vidID);
        Video video=userService.searchVideoByID(Integer.parseInt(vidID));
        AlarmRecord alarmRecord=new AlarmRecord();
        alarmRecord.setAlmTime(new Timestamp(System.currentTimeMillis()));
        alarmRecord.setIsHandled(0);
        alarmRecord.setOpLocX(video.getOpLocX());
        alarmRecord.setOpLocY(video.getOpLocY());
        alarmRecord.setStaffID(video.getStaffID());
        int flag=userService.insertAlarmRecord(alarmRecord);
        if(flag!=0){
            System.out.println("报警记录插入成功！");
        }else{
            System.out.println("报警记录插入失败！");
        }
        return "redirect:/findAlarmRecord";
    }

    @RequestMapping(value = "/findAlarmRecord")
    public String findAlarmRecord(HttpServletRequest request){
        List<AlarmRecord> alarmRecordList=userService.searchAlarmRecordAll();
        request.setAttribute("alarmRecordList",alarmRecordList);

        return "/common/findAlarmRecord";
    }

    @RequestMapping(value = "/findAlarmRecordapp")
    public String findAlarmRecordapp(HttpServletRequest request){
        List<AlarmRecord> alarmRecordList=userService.searchAlarmRecordAll();
        request.setAttribute("alarmRecordList",alarmRecordList);

        return "/common/findAlarmRecordapp";
    }

    @RequestMapping(value = "/deleteAlarm")
    public String deleteAlarm(HttpServletRequest request){
        String[] deleteList=request.getParameterValues("toDeleteAlarm");
        for (String alarmID:deleteList){
            int flag=userService.deleteAlarmRecordByID(Integer.parseInt(alarmID));
            if(flag==0){
                System.out.println("删除失败！"+alarmID);
            }else{
                System.out.println("删除成功！"+alarmID);
            }

        }

        return "redirect:/findAlarmRecord";

    }

    @RequestMapping(value = "/reloadAlarm")
    public String reloadAlarm(HttpServletRequest request){
        String isHandled=request.getParameter("isHandled");
        System.out.println("isHandled:"+isHandled);
        List<AlarmRecord> alarmRecordList=userService.searchAlarmRecordAll();
        if(!isHandled.equals("")){
            int ishandled=Integer.parseInt(isHandled);
            for(int i=0;i<alarmRecordList.size();i++){
                if(alarmRecordList.get(i).getIsHandled()!=ishandled){
                    alarmRecordList.remove(i);
                    i--;
                }
            }
        }

        request.setAttribute("alarmRecordList",alarmRecordList);
        return "/common/findAlarmRecord";
    }
    @RequestMapping(value = "/reloadAlarmapp")
    public String reloadAlarmapp(HttpServletRequest request){
        String isHandled=request.getParameter("isHandled");
        System.out.println("isHandled:"+isHandled);
        List<AlarmRecord> alarmRecordList=userService.searchAlarmRecordAll();
        if(!isHandled.equals("")){
            int ishandled=Integer.parseInt(isHandled);
            for(int i=0;i<alarmRecordList.size();i++){
                if(alarmRecordList.get(i).getIsHandled()!=ishandled){
                    alarmRecordList.remove(i);
                    i--;
                }
            }
        }

        request.setAttribute("alarmRecordList",alarmRecordList);
        return "/common/findAlarmRecordapp";
    }
    @RequestMapping(value = "/alarmHandle/{almRecID}")
    public String alarmHandle(@PathVariable String almRecID){
        System.out.println("almRecID:"+almRecID);
        AlarmRecord alarmRecord= userService.searchAlarmRecordByID(Integer.parseInt(almRecID));
        alarmRecord.setIsHandled(1);
        userService.updateAlarmRecord(alarmRecord);
        return "redirect:/findAlarmRecord";
    }

    @RequestMapping(value = "/createImgAlarm/{imgID}")
    public String createImgAlarm(@PathVariable String imgID){
        System.out.println("imgID:"+imgID);
        Picture picture=userService.searchPictureByID(Integer.parseInt(imgID));
        AlarmRecord alarmRecord=new AlarmRecord();
        alarmRecord.setAlmTime(new Timestamp(System.currentTimeMillis()));
        alarmRecord.setIsHandled(0);
        alarmRecord.setOpLocX(picture.getOpLocX());
        alarmRecord.setOpLocY(picture.getOpLocY());
        alarmRecord.setStaffID(picture.getStaffID());
        int flag=userService.insertAlarmRecord(alarmRecord);
        if(flag!=0){
            System.out.println("报警记录插入成功！");
        }else{
            System.out.println("报警记录插入失败！");
        }
        return "redirect:/findAlarmRecord";
    }

    @RequestMapping(value = "/seePrivateInformation")
    public String seePrivateInformation(){

        return "/common/seePrivateInformation";
    }

    @RequestMapping(value = "/modifyPrivateInformation")
    public String modifyPrivateInformation(){

        return "/common/modifyPrivateInformation";
    }

    @RequestMapping(value = "/modifiedPrivateInformationToDB")
    public String modifiedPrivateInformationToDB(HttpServletRequest request){
        String userID=request.getParameter("userID");
        String userName=request.getParameter("userName");
        String userPwd=request.getParameter("userPwd");
        String userMail=request.getParameter("userMail");
        String userPhone=request.getParameter("userPhone");
        String userDept=request.getParameter("userDept");
        String empTime=request.getParameter("empTime");
        UserTable userTable=userService.searchUserByID(Integer.parseInt(userID));
        userTable.setUserName(userName);
        userTable.setEmpTime(empTime);
        userTable.setUserDept(userDept);
        userTable.setUserMail(userMail);
        userTable.setUserPhone(userPhone);
        userTable.setUserPwd(userPwd);
        userService.updateUser(userTable);
        request.getSession().setAttribute("user",userTable);
        return "redirect:/seePrivateInformation";
    }

    @RequestMapping(value = "/registerAccount")
    public String registerAccount(){

        return "/common/registerAccount";
    }

    @RequestMapping(value = "/doRegister")
    public String doRegister(HttpServletRequest request){
        String userName=request.getParameter("userName");
        String userPwd=request.getParameter("userPwd");
        String userMail=request.getParameter("userMail");
        String userPhone=request.getParameter("userPhone");
        String userRight=request.getParameter("userRight");
        String empTime=request.getParameter("empTime");
        String userDept=request.getParameter("userDept");


        UserTable userTable=new UserTable();
        userTable.setUserName(userName);
        userTable.setUserRight(Integer.parseInt(userRight));
        userTable.setEmpTime(empTime);
        userTable.setUserDept(userDept);
        userTable.setUserMail(userMail);
        userTable.setUserPhone(userPhone);
        userTable.setUserPwd(userPwd);
        UserTable test=userService.searchUserByName(userName);
        if(test!=null){
            //数据库中已经存在此用户
            System.out.println("注册失败！");
            request.setAttribute("errormsg","用户已存在，请重新注册！");
            return "/common/registerAccount";
        }else{
            //数据库中不存在此用户
            userService.insertUser(userTable);
            System.out.println("注册成功！");
            return "/common/registerok";
        }
    }

}
