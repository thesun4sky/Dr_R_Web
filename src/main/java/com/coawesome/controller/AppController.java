package com.coawesome.controller;

import com.coawesome.domain.DiaryVO;
import com.coawesome.domain.FileUtils;
import com.coawesome.domain.ResultVO;
import com.coawesome.domain.UserVO;
import com.coawesome.persistence.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by TeasunKim on 2016-11-09.
 */
@RestController
public class AppController {

    @Autowired
    private UserMapper userMapper;

    @Resource(name = "fileUtils")
    private FileUtils fileUtils;


    @RequestMapping(value= "/checkUser",method= RequestMethod.POST)
    public ResultVO TestPost(HttpServletRequest request) {
        UserVO userVO = new UserVO();
        userVO.setLogin_id(request.getParameter("device_id"));
        System.out.println("UUID : " + userVO.getLogin_id());
        String found_id = userMapper.checkUserId(userVO);
        if(userVO.getLogin_id().equals(found_id)){
            System.out.println("아이디 있음. 자동로그인");
            userVO.setU_name(userMapper.findName(userVO));
            return new ResultVO(userVO.getU_name(),1);
        }
        else {
            System.out.println("아이디 없음. 회원가입");
            userVO.setU_name("김태선");
            userMapper.addUser(userVO);
            return new ResultVO("새로오신"+userVO.getU_name(),0);
        }
    }

    @RequestMapping(value= "/writeDiary",method= RequestMethod.POST)
    public ResultVO writeDiary(HttpServletRequest request) {
        DiaryVO diaryVO = new DiaryVO();
        diaryVO.setU_id(Integer.parseInt(request.getParameter("u_id")));
        diaryVO.setBreakfast(request.getParameter("breakfast"));
        diaryVO.setLunch(request.getParameter("lunch"));
        diaryVO.setDinner(request.getParameter("dinner"));
        diaryVO.setTemperature(Integer.parseInt(request.getParameter("temperature")));
        diaryVO.setHumid(Integer.parseInt(request.getParameter("humid")));
        diaryVO.setSleepTime(Integer.parseInt(request.getParameter("sleepTime")));
        diaryVO.setBloodPressure(Integer.parseInt(request.getParameter("bloodPressure")));
        diaryVO.setDrinking(request.getParameter("drinking"));

        System.out.println("diaryVO : " + diaryVO);
        return new ResultVO(diaryVO.toString(),1);
    }

    @RequestMapping(value= "/getDiaries",method= RequestMethod.POST)
    public DiaryVO getDiary(HttpServletRequest request) {
        System.out.println("u_id : " + request.getParameter("u_id"));
        System.out.println("getDiaries called");
        ArrayList<DiaryVO> arrayList = new ArrayList<DiaryVO>();
        DiaryVO diaryVO = new DiaryVO();
        diaryVO.setBloodPressure(5);
        diaryVO.setDinner("aa ss");
        diaryVO.setLunch("lun chi");
        arrayList.add(diaryVO);
        diaryVO.setBloodPressure(2);
        diaryVO.setDinner("aa 1");
        diaryVO.setLunch("lu5hi");
        arrayList.add(diaryVO);
        System.out.println(arrayList.toString());
        return diaryVO;
    }


}
