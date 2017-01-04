package com.coawesome.controller;

import com.coawesome.domain.*;
import com.coawesome.persistence.DiaryMapper;
import com.coawesome.persistence.DoctorMapper;
import com.coawesome.persistence.QnaMapper;
import com.coawesome.persistence.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
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
        userVO.setU_device(request.getParameter("u_device"));
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
            userMapper.deleteDeviceID(userVO);
            userMapper.setDeviceID(userVO);
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
        diaryVO.setC_date(java.sql.Timestamp.valueOf(request.getParameter("c_date")));
        diaryVO.setC_depart(request.getParameter("c_depart"));
        diaryVO.setC_h(Float.parseFloat(request.getParameter("c_h")));
        diaryVO.setC_w(Float.parseFloat(request.getParameter("c_w")));
        diaryVO.setC_hospital(request.getParameter("c_hospital"));
        diaryVO.setC_memo(request.getParameter("c_memo"));
        if(request.getParameter("c_next").toString() != "0") {
            diaryVO.setC_next(request.getParameter("c_next"));
        }
        diaryVO.setC_treat(request.getParameter("c_treat"));
        diaryVO.setC_shot(request.getParameter("c_shot"));


        DiaryVO diaryExist = diaryMapper.getDiaryByDate(diaryVO);
        if(diaryExist == null) {                                 //기존의 일지가 없으면
            diaryMapper.updateDiaryWithPrevImg(diaryVO);
        }
        else {
            diaryMapper.addDiary(diaryVO);
        }
        System.out.println("diaryVO : " + diaryVO);
        return new ResultVO("정상 작동",1);
    }


    @RequestMapping(value= "/writeDiaryWithImg",method= RequestMethod.POST)
    public ResultVO writeDiaryWithImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        DiaryVO diaryVO = new DiaryVO();

        diaryVO.setU_id(Integer.parseInt(request.getParameter("u_id")));
        diaryVO.setC_date(java.sql.Timestamp.valueOf(request.getParameter("c_date")));
        diaryVO.setC_depart(request.getParameter("c_depart"));
        diaryVO.setC_h(Float.parseFloat(request.getParameter("c_h")));
        diaryVO.setC_w(Float.parseFloat(request.getParameter("c_w")));
        diaryVO.setC_hospital(request.getParameter("c_hospital"));
        diaryVO.setC_memo(request.getParameter("c_memo"));
        if(request.getParameter("c_next").toString() != "0") {
            diaryVO.setC_next(request.getParameter("c_next"));
        }
        diaryVO.setC_treat(request.getParameter("c_treat"));
        diaryVO.setC_shot(request.getParameter("c_shot"));
        diaryVO.setC_img(request.getParameter("c_img"));

        DiaryVO diaryExist = diaryMapper.getDiaryByDate(diaryVO);
        if(diaryExist == null){                                 //기존의 일지가 없으면
            if(request.getParameter("c_img")!=null) {
                ImageVO image = fileUtils.parseInsertFileInfo(file, diaryVO);
                System.out.println("image : " + image);
                diaryVO.setC_img(image.getStored_file_name());
                diaryMapper.addDiaryWithImg(diaryVO);
            }
            else diaryMapper.addDiary(diaryVO);
        }
        else{
            if(request.getParameter("c_img")!=null) {
                ImageVO image = fileUtils.parseInsertFileInfo(file, diaryVO);
                System.out.println("image : " + image);
                diaryVO.setC_img(image.getStored_file_name());
                diaryMapper.updateDiaryWithNewImg(diaryVO);
            }
            else diaryMapper.updateDiaryWithPrevImg(diaryVO);

        }

        System.out.println("diaryVO : " + diaryVO);
        return new ResultVO("정상 작동",1);
    }

    @RequestMapping(value= "/getDiary",method= RequestMethod.POST)
    public DiaryVO getDiary(HttpServletRequest request) {
        //TODO 중복체크
        DiaryVO diaryVO = new DiaryVO();
        diaryVO.setU_id(Integer.parseInt(request.getParameter("u_id")));
        diaryVO.setC_date(java.sql.Timestamp.valueOf(request.getParameter("c_date")));
        DiaryVO diaryVOs = diaryMapper.getDiaryByDate(diaryVO);

        System.out.println("diaryVO by date : " + diaryVOs);
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
        qnaVO.setQna_question(request.getParameter("qna_content"));
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
        userVO.setU_device(request.getParameter("u_device"));
        userVO.setU_a_week(Integer.parseInt(request.getParameter("u_a_week")));
        userVO.setU_a_date(Integer.parseInt(request.getParameter("u_a_date")));
        userVO.setU_b_month(Integer.parseInt(request.getParameter("u_b_month")));
        userVO.setU_b_date(Integer.parseInt(request.getParameter("u_b_date")));
        userVO.setU_sex(request.getParameter("u_sex"));
        userVO.setU_w(Float.parseFloat(request.getParameter("u_w")));
        userVO.setU_h(Float.parseFloat(request.getParameter("u_h")));
        //TODO 이미지 추가    p_id
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

    @RequestMapping(value= "/addSleepTime",method= RequestMethod.POST)
    public ResultVO addSleepTime(HttpServletRequest request) {
        SleepVO sleepVO = new SleepVO();

        sleepVO.setU_id(Integer.parseInt(request.getParameter("u_id")));

        System.out.println(sleepVO.getU_id() + "/" + request.getParameter("s_start") + "/" + request.getParameter("s_end"));
        sleepVO.setS_start(java.sql.Timestamp.valueOf(request.getParameter("s_start")));
        sleepVO.setS_end(java.sql.Timestamp.valueOf(request.getParameter("s_end")));
        sleepVO.setS_total(Integer.parseInt(request.getParameter("s_total")));

        diaryMapper.addSleepTime(sleepVO);

        return new ResultVO("정상 작동",1);
    }

    @RequestMapping(value= "/addFeedTime",method= RequestMethod.POST)
    public ResultVO addFeedTime(HttpServletRequest request) {
        FeedVO feedVO = new FeedVO();

        feedVO.setU_id(Integer.parseInt(request.getParameter("u_id")));

        System.out.println(feedVO.getU_id() + "/" + request.getParameter("s_start") + "/" + request.getParameter("s_end"));
        feedVO.setF_start(java.sql.Timestamp.valueOf(request.getParameter("s_start")));
        feedVO.setF_end(java.sql.Timestamp.valueOf(request.getParameter("s_end")));
        feedVO.setF_total(Integer.parseInt(request.getParameter("s_total")));
        feedVO.setFeed(request.getParameter("feed"));

        diaryMapper.addFeedTime(feedVO);

        return new ResultVO("정상 작동",1);
    }

    @RequestMapping(value= "/getAllFeedList",method= RequestMethod.POST)
    public ArrayList<FeedVO> getAllFeedList(HttpServletRequest request) {
        ArrayList<FeedVO> feedList = new ArrayList<>();

        return feedList;
    }

    @RequestMapping(value= "/getAllSleepList",method= RequestMethod.POST)
    public ArrayList<SleepVO> getAllSleepList(HttpServletRequest request) {
        SleepVO sleepVO = new SleepVO();

        sleepVO.setU_id(Integer.parseInt(request.getParameter("u_id")));

        ArrayList<SleepVO> sleepList = diaryMapper.getAllSleepList(sleepVO);
        return sleepList;
    }

    //해당 날짜 수면시간 불러오기
    @RequestMapping(value= "/getSleepTimeByDate",method= RequestMethod.POST)
    public ArrayList<SleepVO> getSleepTimeByDate(HttpServletRequest request) {
        SleepVO sleepVO = new SleepVO();

        sleepVO.setU_id(Integer.parseInt(request.getParameter("u_id")));

        System.out.println(sleepVO.getU_id() + "/" + request.getParameter("s_start") );
        sleepVO.setS_start(java.sql.Timestamp.valueOf(request.getParameter("s_start")));

        ArrayList<SleepVO> arrayList =  diaryMapper.getSleepTime(sleepVO);

        System.out.println(arrayList);
        return arrayList;
    }

    @RequestMapping(value= "/getAllFeedTimeByDate",method= RequestMethod.POST)
    public ArrayList<FeedVO> getAllFeedTimeByDate(HttpServletRequest request) {
        FeedVO feedVO = new FeedVO();

        feedVO.setU_id(Integer.parseInt(request.getParameter("u_id")));

        System.out.println(feedVO.getU_id() + "/" + request.getParameter("f_start") );
        feedVO.setF_start(java.sql.Timestamp.valueOf(request.getParameter("f_start")));

        ArrayList<FeedVO> arrayList =  diaryMapper.getAllFeedListByDate(feedVO);

        return arrayList;
    }

    @RequestMapping(value= "/getDateSleepTime",method= RequestMethod.POST)
    public ArrayList<SleepVO> getDateSleepTime(HttpServletRequest request) {
        SleepVO sleepVO = new SleepVO();

        sleepVO.setU_id(Integer.parseInt(request.getParameter("u_id")));

        ArrayList<SleepVO> arrayList =  diaryMapper.getDateSleepTime(sleepVO);

        return arrayList;
    }

    @RequestMapping(value= "/getDateFeedTime",method= RequestMethod.POST)
    public ArrayList<FeedVO> getDateFeedTime(HttpServletRequest request) {
        FeedVO feedVO = new FeedVO();

        feedVO.setU_id(Integer.parseInt(request.getParameter("u_id")));

        ArrayList<FeedVO> arrayList =  diaryMapper.getDateFeedTime(feedVO);

        return arrayList;
    }
}
