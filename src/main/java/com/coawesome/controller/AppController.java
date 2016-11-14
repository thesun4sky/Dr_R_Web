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


    @RequestMapping(value= "/checkUserDevice",method= RequestMethod.POST)
    public ResultVO checkUserDevice(HttpServletRequest request) {
        UserVO userVO = new UserVO();
        userVO.setU_device(request.getParameter("u_device"));
        System.out.println("u_device : " + userVO.getU_device());
        String found_device = userMapper.checkUserDevice(userVO);
        if(userVO.getU_device().equals(found_device)){
            System.out.println("device 있음. 자동로그인");
            userVO.setU_name(userMapper.findName(userVO));
            return new ResultVO(userVO.getU_name(),1);
        }
        else {
            System.out.println("아이디 없음. ");
            return new ResultVO("회원가입 필요",0);
        }
    }

    @RequestMapping(value= "/checkLoginId",method= RequestMethod.POST)
    public ResultVO checkLoginId(HttpServletRequest request) {
        String login_id = request.getParameter("login_id");
        System.out.println("Login_id check : " + login_id);
        UserVO userVO = new UserVO();
        userVO.setLogin_id(login_id);
        String found_id = userMapper.checkUserLoginId(userVO);
        if(userVO.getLogin_id().equals(found_id)){
            //System.out.println("아이디 있음. 사용 불가");
            return new ResultVO("아이디가 중복됩니다.",1);
        }
        else {
            //System.out.println("아이디 없음. 사용 가능");
            return new ResultVO(userVO.getLogin_id(),0);
        }
    }

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ResultVO checkLogin(HttpServletRequest request){
        String login_id = request.getParameter("login_id");
        String u_password = request.getParameter("u_password");
        System.out.println("try login : " + login_id);
        UserVO userVO = new UserVO();
        userVO.setLogin_id(login_id);
        userVO.setU_password(u_password);
        String found_password = userMapper.userLogincheck(userVO);

        if(u_password.equals(found_password)){
            System.out.println("login success" + login_id);
            return new ResultVO(userVO.getLogin_id(),1);
        }
        else{
            System.out.println("login failed" + login_id);
            return new ResultVO(userVO.getLogin_id(),0);
        }

    }
    @RequestMapping(value= "/writeDiary",method= RequestMethod.POST)
    public ResultVO writeDiary(HttpServletRequest request) {
        DiaryVO diaryVO = new DiaryVO();
        diaryVO.setU_id(Integer.parseInt(request.getParameter("u_id")));
        diaryVO.setC_breakfast(request.getParameter("breakfast"));
        diaryVO.setC_lunch(request.getParameter("lunch"));
        diaryVO.setC_dinner(request.getParameter("dinner"));
        diaryVO.setC_temperature(Integer.parseInt(request.getParameter("temperature")));
        diaryVO.setC_humid(Integer.parseInt(request.getParameter("humid")));
        diaryVO.setC_sleepTime(Integer.parseInt(request.getParameter("sleepTime")));
        diaryVO.setC_bloodPressure(Integer.parseInt(request.getParameter("bloodPressure")));
        diaryVO.setC_drinking(request.getParameter("drinking"));

        System.out.println("diaryVO : " + diaryVO);
        return new ResultVO("정상 작동",1);
    }

    @RequestMapping(value= "/getDiaries",method= RequestMethod.POST)
    public ArrayList<DiaryVO> getDiary(HttpServletRequest request) {
        System.out.println("u_id : " + request.getParameter("u_id"));
        System.out.println("getDiaries called");
        ArrayList<DiaryVO> arrayList = new ArrayList<DiaryVO>();
        DiaryVO diaryVO = new DiaryVO();
        diaryVO.setC_bloodPressure(5);
        diaryVO.setC_dinner("aa ss");
        diaryVO.setC_dinner("lun chi");
        arrayList.add(diaryVO);
        diaryVO.setC_bloodPressure(2);
        diaryVO.setC_dinner("aa 1");
        diaryVO.setC_lunch("lu5hi");
        arrayList.add(diaryVO);
        System.out.println(arrayList.toString());
        return arrayList;
    }

    @RequestMapping(value= "/joinUser",method= RequestMethod.POST)
    public ResultVO joinUser(HttpServletRequest request) {
        System.out.println("joinUser called");

        UserVO userVO = new UserVO();
        userVO.setLogin_id(request.getParameter("login_id"));
        userVO.setU_name(request.getParameter("u_name"));
        userVO.setU_password(request.getParameter("u_password"));
        userVO.setU_phone(request.getParameter("u_phone"));
        userVO.setU_disease(request.getParameter("u_disease"));
        userVO.setU_hospital(request.getParameter("u_hospital"));
        userVO.setU_device(request.getParameter("u_device"));
        System.out.println(userVO.toString());
        userMapper.addUser(userVO);
        return new ResultVO("정상 작동",1);
    }
}
