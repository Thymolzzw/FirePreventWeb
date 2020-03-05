package com.example.demo.adm.controller;

import com.example.demo.adm.service.AdmService;
import com.example.demo.entity.Picture;
import com.example.demo.entity.UserTable;
import com.example.demo.entity.Video;
import com.example.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Controller
@Transactional
public class AdmController {


    @Autowired
    AdmService admService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/userManage")
    public String userManage(HttpServletRequest request){
        List<UserTable> userTableList=userService.searchUserAll();
        for(int i=0;i<userTableList.size();i++){
            if(userTableList.get(i).getUserRight()==1){
                userTableList.remove(i);
                i--;
            }
        }
        request.setAttribute("userList",userTableList);
        return "/adm/userManage";
    }

    @RequestMapping(value = "/userSee/{userID}")
    public String userSee(HttpServletRequest request,@PathVariable String userID){
        UserTable userTable=userService.searchUserByID(Integer.parseInt(userID));
        request.setAttribute("theUserToSee",userTable);
        return "/adm/modifyAndSeeUserInfo";
    }


    @RequestMapping(value = "/doModifyUserInfo")
    public String doModifyUserInfo(HttpServletRequest request){
        String userID=request.getParameter("userID");
        String userName=request.getParameter("userName");
        String userPwd=request.getParameter("userPwd");
        String userMail=request.getParameter("userMail");
        String userPhone=request.getParameter("userPhone");
        String userDept=request.getParameter("userDept");
        String empTime=request.getParameter("empTime");
        String userRight=request.getParameter("userRight");
        UserTable userTable=new UserTable();
        userTable.setUserPwd(userPwd);
        userTable.setUserPhone(userPhone);
        userTable.setUserMail(userMail);
        userTable.setUserDept(userDept);
        userTable.setEmpTime(empTime);
        userTable.setUserName(userName);
        userTable.setUserID(Integer.parseInt(userID));
        userTable.setUserRight(Integer.parseInt(userRight));
        userService.updateUser(userTable);
        return "redirect:/userSee/"+userID;
    }

    @RequestMapping(value = "/userDelete/{userID}")
    public String userDelete(@PathVariable String userID,HttpServletRequest request){
        UserTable userTable=userService.searchUserByID(Integer.parseInt(userID));
        List<Video> videoList=admService.searchVideoByUserID(Integer.parseInt(userID));
        List<Picture> pictureList=admService.searchPictureByUserID(Integer.parseInt(userID));


        for (Video video:videoList){

            File file=new File(video.getVidUrl());
            boolean flag1=file.delete();
            if(flag1){
                System.out.println("删除物理文件成功！");
            }else{
                System.out.println("删除物理文件失败！");
            }
        }
        for (Picture picture:pictureList){

            File file=new File(picture.getImgUrl());
            boolean flag1=file.delete();
            if(flag1){
                System.out.println("删除物理文件成功！");
            }else{
                System.out.println("删除物理文件失败！");
            }
        }

        admService.deleteAlarmRecordByUserID(Integer.parseInt(userID));
        admService.deletePictureByUserID(Integer.parseInt(userID));
        admService.deleteVideoByUserID(Integer.parseInt(userID));
        admService.deleteUserByUserID(Integer.parseInt(userID));

        return "redirect:/userManage";
    }

    @RequestMapping(value = "/reloadUserList")
    public String reloadUserList(HttpServletRequest request){
        String userName=request.getParameter("userName");
        String userRight=request.getParameter("userRight");

        List<UserTable> userTableList=userService.searchUserAll();
        for(int i=0;i<userTableList.size();i++){
            if(userTableList.get(i).getUserRight()==1){
                userTableList.remove(i);
                i--;
            }
        }
        if (!userName.equals("")) {
            for(int i=0;i<userTableList.size();i++){
                if(!userTableList.get(i).getUserName().contains(userName)){
                    userTableList.remove(i);
                    i--;
                }
            }
        }
        if (!userRight.equals("")) {
            int right=Integer.parseInt(userRight);
            for(int i=0;i<userTableList.size();i++){
                if(userTableList.get(i).getUserRight()!=right){
                    userTableList.remove(i);
                    i--;
                }
            }
        }
        request.setAttribute("userList",userTableList);
        return "/adm/userManage";
    }

//    @RequestMapping(value = "/seePrivateInformationAdm")
//    public String seePrivateInformationAdm(){
//
//        return "/admuser/seePrivateInformationAdm";
//    }
//
//    @RequestMapping(value = "/modifyPrivateInformationAdm")
//    public String modifyPrivateInformationAdm(){
//
//        return "/admuser/modifyPrivateInformationAdm";
//    }
//
//    @RequestMapping(value = "/registerUserManage")
//    public String registerUserManage(HttpServletRequest request){
//
//        List<UserTable> userList=admService.searchRegisterUser();
//        System.out.println("已经成功加载用户表");
//        request.setAttribute("registerUserList",userList);
//        return "/admuser/registerUserManage";
//    }
//
//    @RequestMapping(value = "/usersee/{userID}")
//    public String userSee(@PathVariable String userID,HttpServletRequest request){
//        int userid=Integer.parseInt(userID);
//        System.out.println("toseeuserID:"+userid);
//        UserTable userTable=userService.searchUserByID(userid);
//        request.setAttribute("userOfToSee",userTable);
//        return "/admuser/registerUserInfo";
//    }
//
//    @RequestMapping(value = "/userdelete/{userID}")
//    public String userDelete(@PathVariable String userID,HttpServletRequest request){
//        int userid=Integer.parseInt(userID);
//        System.out.println("todeleteuserID:"+userid);
//        UserTable userTable=userService.searchUserByID(userid);
//        List<ResourceTable> resourceTableList=userService.searchResourceByUserID(userid);
//        //删除该用户所用的资源
//        for(ResourceTable re:resourceTableList){
//            userService.deleteRelationByResourceID(re.getResourceID());
//            userService.deleteBelongByResourceID(re.getResourceID());
//            userService.deleteDiscussByResourceID(re.getResourceID());
//            userService.deleteDownLoadByResourceID(re.getResourceID());
//            userService.deleteResourceByID(re.getResourceID());
//            File file=new File(re.getResourceLocation());
//            boolean flag=file.delete();
//            if(flag){
//                System.out.println("删除物理文件成功！");
//            }else{
//                System.out.println("删除物理文件失败！");
//            }
//        }
//        //删除改用户所用的download记录
//        admService.deleteDownLoadByUserID(userid);
//        System.out.println("删除改用户所用的download记录！");
//        //删除该用户所有的discuss记录
//        admService.deleteDiscussByUserID(userid);
//        System.out.println("删除该用户所有的discuss记录！");
//        //删除该用户
//        admService.deleteUserByName(userTable.getUserName());
//        System.out.println("删除该用户！");
//
//        return "redirect:/registerUserManage";
//    }
//
//    @RequestMapping(value = "/ResourceManage")
//    public String resourceManage(HttpServletRequest request){
//        List<ResourceTable> list1=userService.searchResourceByType(1);
//        List<ResourceTable> list2=userService.searchResourceByType(2);
//        List<ResourceTable> list3=userService.searchResourceByType(3);
//        list1.addAll(list2);
//        list1.addAll(list3);
//        for(ResourceTable re:list1){
//            List<DownLoad> downLoadList=userService.searchDownLoadByResourceID(re.getResourceID());
//            re.setVisitVolume(downLoadList.size());
//        }
//        request.setAttribute("resourceList",list1);
//        return "/admuser/resourceStatistic";
//    }
//
//    @RequestMapping(value = "/overloadResourceTableStatistic")
//    public String overloadResourceTableStatistic(HttpServletRequest request){
//        List<ResourceTable> list1=userService.searchResourceByType(1);
//        List<ResourceTable> list2=userService.searchResourceByType(2);
//        List<ResourceTable> list3=userService.searchResourceByType(3);
//        list1.addAll(list2);
//        list1.addAll(list3);
//        sortResource(list1);
//
//        String resourceName=request.getParameter("resourceName");
//        String resourceType=request.getParameter("resourceType");
//        List<ResourceTable> mylist=null;
//        if(!resourceName.equals("")){
//            mylist=new ArrayList<ResourceTable>();
//            for(int i=0;i<list1.size();i++){
//                if(list1.get(i).getResourceName().contains(resourceName)){
//                    mylist.add(list1.get(i));
//                }
//            }
//        }else{
//            mylist=list1;
//        }
//        List<ResourceTable> mylist1=null;
//        if(!resourceType.equals("")){
//            int type=Integer.parseInt(resourceType);
//            mylist1=new ArrayList<ResourceTable>();
//            for(int i=0;i<mylist.size();i++){
//                if(mylist.get(i).getResourceType()==type){
//                    mylist1.add(mylist.get(i));
//                }
//            }
//            request.setAttribute("resourceList",mylist1);
//        }else{
//            request.setAttribute("resourceList",mylist);
//        }
//
//        return "/admuser/resourceStatistic";
//    }
//
//
//    private void sortResource(List<ResourceTable> resourceList){
//        Collections.sort(resourceList);
//    }
}
