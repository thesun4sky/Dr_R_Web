package com.coawesome.controller;

import com.coawesome.domain.FileUtils;
import com.coawesome.domain.UserVO;
import com.coawesome.domain.result;
import com.coawesome.persistence.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by TeasunKim on 2016-11-09.
 */
@RestController
public class AppController {

    @Autowired
    private UserMapper userMapper;

    @Resource(name = "fileUtils")
    private FileUtils fileUtils;


    @RequestMapping(value= "/expost",method= RequestMethod.POST)
    public result TestPost(HttpServletRequest request) {
        UserVO userVO = new UserVO();
        userVO.setLogin_id(request.getParameter("device_id"));
        System.out.println("UUID : " + userVO.getLogin_id());
        String found_id = userMapper.checkUserId(userVO);
        if(userVO.getLogin_id().equals(found_id)){
            System.out.println("아이디 있음. 자동로그인");
            userVO.setU_name(userMapper.findName(userVO));
            return new result(userVO.getU_name(),1);
        }
        else {
            System.out.println("아이디 없음. 회원가입");
            userVO.setU_name("김태선");
            userMapper.addUser(userVO);
            return new result("새로오신"+userVO.getU_name(),0);
        }
    }
}
