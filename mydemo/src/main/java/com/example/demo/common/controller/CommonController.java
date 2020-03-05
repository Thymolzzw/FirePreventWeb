package com.example.demo.common.controller;


import com.example.demo.common.service.CommonService;
import com.example.demo.entity.UserTable;
import com.example.demo.user.service.UserService;
import com.example.demo.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;

@Controller
public class CommonController {
    @Autowired
    CommonService commonService;
    @Autowired
    UserService userService;


    @RequestMapping(value = "/")
    public String userToLogin(){
        System.out.println("进入 /");
        return "/common/login";
    }

    @RequestMapping(value = "/app")
    public String userToLoginapp(){
        System.out.println("进入 /app");
        return "/common/index";
    }

    @RequestMapping(value = "/loginapp")
    public String  loginapp(){
        return "/common/loginapp";
    }
    @RequestMapping(value = "/dologinapp")
    public String loginappp(HttpServletRequest request, UserTable user, String verifyCode){
        System.out.println("用户输入内容如下：");
        System.out.println("----------------用户名为："+user.getUserName());
        System.out.println("----------------密码为："+user.getUserPwd());
        System.out.println("----------------用户类型为："+user.getUserRight());
        System.out.println("----------------验证码为："+verifyCode);


//        String succCode= (String) request.getSession().getAttribute("imageCode");
//        succCode = succCode.toLowerCase();
//        String userCode = verifyCode.toLowerCase();
//        if(!succCode.equals(userCode)) {
//            request.setAttribute("msg","验证码错误");
//            return "/common/login";
//        }

        UserTable myuser=commonService.userLogin(user);

        if(myuser!=null){
            //用户存在
            request.getSession().setAttribute("user",myuser);
            if(myuser.getUserRight()==1){
                //系统管理员
                return "redirect:/app";
            }else{
                //森林防火工作人员与专家
                return "redirect:/app";
            }
        }else{
            //用户不存在
            request.setAttribute("msg","用户名或密码输入错误！");
            return "redirect:/app";
        }


    }

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, UserTable user, String verifyCode){
        System.out.println("用户输入内容如下：");
        System.out.println("----------------用户名为："+user.getUserName());
        System.out.println("----------------密码为："+user.getUserPwd());
        System.out.println("----------------用户类型为："+user.getUserRight());
        System.out.println("----------------验证码为："+verifyCode);


//        String succCode= (String) request.getSession().getAttribute("imageCode");
//        succCode = succCode.toLowerCase();
//        String userCode = verifyCode.toLowerCase();
//        if(!succCode.equals(userCode)) {
//            request.setAttribute("msg","验证码错误");
//            return "/common/login";
//        }

        UserTable myuser=commonService.userLogin(user);

        if(myuser!=null){
            //用户存在
            request.getSession().setAttribute("user",myuser);
            if(myuser.getUserRight()==1){
                //系统管理员
                return "adm/index";
            }else{
                //森林防火工作人员与专家
                return "user/index";
            }
        }else{
            //用户不存在
            request.setAttribute("msg","用户名或密码输入错误！");
            return "common/login";
        }


    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        System.out.println("用户退出！");
        return "redirect:/";
    }

    @RequestMapping("/getcode")
    public void getCode(HttpServletResponse response, HttpServletRequest request) throws Exception{
        HttpSession session=request.getSession();
        //利用图片工具生成图片
        //第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = VerifyUtil.createImage();
        //将验证码存入Session
        session.setAttribute("imageCode",objs[0]);

        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }


}
