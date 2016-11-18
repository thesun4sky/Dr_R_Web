package com.coawesome.controller;

import com.coawesome.domain.*;
import com.coawesome.persistence.DiaryMapper;
import com.coawesome.persistence.DoctorMapper;
import com.coawesome.persistence.QnaMapper;
import com.coawesome.persistence.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by TeasunKim on 2016-11-09.
 */
@RestController
public class AppController {

    @Autowired
    private DiaryMapper diaryMapper;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private QnaMapper qnaMapper;

    @Resource(name = "fileUtils")
    private FileUtils fileUtils;


    @RequestMapping(value= "/checkUserDevice",method= RequestMethod.POST)
    public ResultVO checkUserDevice(HttpServletRequest request) {
        UserVO userVO = new UserVO();
        userVO.setU_device(request.getParameter("u_device"));
        System.out.println("u_device : " + userVO.getU_device());
        int found_device = userMapper.checkUserDevice(userVO);
//        if(userVO.getU_device().equals(found_device)){
        if(found_device > 0){
            System.out.println("device 있음. 자동로그인");
            userVO.setU_name(userMapper.findName(userVO).getU_name());
            userVO.setU_id(userMapper.findName(userVO).getU_id());
            return new ResultVO(userVO.getU_name(),userVO.getU_id());
        }
        else {
            System.out.println("아이디 없음. ");
            return new ResultVO("회원가입 필요",-1);
        }
    }

    @RequestMapping(value= "/checkLoginId",method= RequestMethod.POST)
    public ResultVO checkLoginId(HttpServletRequest request) {
        String login_id = request.getParameter("login_id");
        System.out.println("Login_id check : " + login_id);
        UserVO userVO = new UserVO();
        userVO.setLogin_id(login_id);
        int found_id = userMapper.checkUserLoginId(userVO);
//        if(userVO.getLogin_id().equals(found_id)){
        if(found_id > 0){
            //System.out.println("아이디 있음. 사용 불가");
            return new ResultVO("아이디가 중복됩니다.",-1);
        }
        else {
            //System.out.println("아이디 없음. 사용 가능");
            return new ResultVO("사용가능한 아이디 입니다.",0);
        }
    }

    @RequestMapping(value= "/findUserId",method= RequestMethod.POST)
    public ResultVO findUserId(HttpServletRequest request) {
        String device = request.getParameter("u_device");
        System.out.println("device check : " + device);
        UserVO userVO = new UserVO();
        userVO.setU_device(device);
        int found_userId = userMapper.findUserId(userVO);

        return new ResultVO("정상 작동",found_userId);
    }

    @RequestMapping(value = "/getHospital",method = RequestMethod.GET)
    public ArrayList<Map> getHospital(){
        ArrayList<Map> array = doctorMapper.getHospital();

        return array;
    }


    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ResultVO checkLogin(HttpServletRequest request){
        String login_id = request.getParameter("login_id");
        String u_password = request.getParameter("u_password");
        System.out.println("try login : " + login_id);
        UserVO userVO = new UserVO();
        userVO.setLogin_id(login_id);
        userVO.setU_password(u_password);
        int found_id = userMapper.checkUserLoginId(userVO);
        if (!(found_id > 0)) {
            System.out.println("login   failed");
            return new ResultVO("",-1);
        }
        String found_password = userMapper.userLogincheck(userVO);

        String u_name = userMapper.findNameById(userVO).getU_name();
        int u_id = userMapper.findNameById(userVO).getU_id();

        if(u_password.equals(found_password)){
            System.out.println("login success" + u_name);
            return new ResultVO(u_name, u_id);
        }
        else{
            System.out.println("login   failed" + "");
            return new ResultVO("",-1);
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
        diaryMapper.addDiary(diaryVO);
        System.out.println("diaryVO : " + diaryVO);
        return new ResultVO("정상 작동",1);
    }

    @RequestMapping(value= "/getDiaries",method= RequestMethod.POST)
    public ArrayList<DiaryVO> getDiary(HttpServletRequest request) {
        //TODO 중복체크
        UserVO userVO = new UserVO();
        userVO.setU_id(Integer.parseInt(request.getParameter("u_id")));
        ArrayList<DiaryVO> diaryVOs = diaryMapper.getDiaryList(userVO);

        return diaryVOs;
    }


    @RequestMapping(value= "/getQnaList",method= RequestMethod.GET)
    public ArrayList<QnaVO> getQnaList(HttpServletRequest request) {
        return qnaMapper.getQnaList();
    }

    @RequestMapping(value= "/makeQuestion",method= RequestMethod.POST)
    public ResultVO makeQuestion(HttpServletRequest request) {
        QnaVO qnaVO = new QnaVO();
        qnaVO.setU_id(Integer.parseInt(request.getParameter("u_id")));
        qnaVO.setU_name(request.getParameter("u_name"));
        qnaVO.setQna_title(request.getParameter("qna_title"));
        qnaVO.setQna_content(request.getParameter("qna_content"));
        //date, count 는 sql에서 생성
        qnaMapper.makeQuestion(qnaVO);

        return new ResultVO("정상 작동",1);
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

    @RequestMapping(value= "/getDocPhone",method= RequestMethod.POST)
    public Map getPhoneNum(HttpServletRequest request) {
        System.out.println("getPhoneNum called by " + request.getParameter("u_id"));
        UserVO userVO = new UserVO();
        userVO.setU_id(Integer.parseInt(request.getParameter("u_id")));
        return doctorMapper.getDocPhone(userVO);
    }
}
