package com.coawesome.controller;

import com.coawesome.config.SendSimpleMail;
import com.coawesome.cryptoUtil.AES256CipherTest;
import com.coawesome.domain.*;
import com.coawesome.persistence.DiaryMapper;
import com.coawesome.persistence.DoctorMapper;
import com.coawesome.persistence.QnaMapper;
import com.coawesome.persistence.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.print.Doc;
import java.util.ArrayList;

/**
 * Created by TeasunKim on 2016-11-03.
 */
@RestController
@RequestMapping(value = "/web")
public class WebController {

    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DiaryMapper diaryMapper;
    @Autowired
    private QnaMapper qnaMapper;
    @Resource(name = "fileUtils")
    private FileUtils fileUtils;


    //아이디 체크
    @RequestMapping(method = RequestMethod.POST, value = "/user/checkID")
    public boolean checkUserId(@RequestBody DoctorVO doctor) throws Exception {

        System.out.println("check ID : " + doctor.getE_mail());
        String found_Email = doctorMapper.checkUserEmail(doctor);
        System.out.println("e_mail 중복 체크 e_mail : " + doctor.getE_mail()+ "<< input id ,  found_id  >>" + found_Email);
        if(doctor.getE_mail().equals(found_Email)){
            System.out.println("e_mail 있음. 사용 불가");
            return false;
        }
        else {
            System.out.println("e_mail 없음. 사용 가능");
            return true;
        }
    }



    //회원가입
    @RequestMapping(method = RequestMethod.POST, value = "/user/join")
    public String getUserData(@RequestBody DoctorVO doctor) throws Exception {
        System.out.println("Sign in : " + doctor.getE_mail());
        String ori_pass = doctor.getA_password();
        //암호화
        System.out.println(ori_pass);
        AES256CipherTest aes = new AES256CipherTest(ori_pass);
        String en_pass = aes.encPass();
        //암호화한 후 set
        doctor.setA_password(en_pass);
//        doctor.setA_password(ori_pass);
        System.out.println(ori_pass);
        doctorMapper.addUser(doctor);
        return "true";
    }

    //로그인
    @RequestMapping(method = RequestMethod.POST, value = "/user/login")
    public ResultVO checkLogin(@RequestBody DoctorVO doctor) throws Exception {

        System.out.println("Login Check : " + doctor.getE_mail());
        String input_pass = doctor.getA_password();
        String password = doctorMapper.doctorLogincheck(doctor);
//        int u_id = doctorMapper.findA_id(doctor);
        System.out.println("패스워드 일치 체크 ");
        //복호화된 패스워드와 일치 확인
        if(password != null) {
            AES256CipherTest aes = new AES256CipherTest(password);
            String des_pass = aes.desPass();
//            if (input_pass.equals(password)) {
            if (input_pass.equals(des_pass)) {
                System.out.println("비밀번호 일치" );
                return new ResultVO(doctorMapper.findName(doctor),1);
            } else {
                System.out.println("비밀번호 불일치" );
                return new ResultVO("비밀번호가 다릅니다",0);
            }
        }
        System.out.println("계정이 없습니다.");
        return new ResultVO("계정이 없습니다.",0);
    }


    //비밀번호 찾기
    @RequestMapping(method = RequestMethod.POST, value = "/user/findPASS")
    public ResultVO findPASS(@RequestBody DoctorVO doctor) throws Exception {
        System.out.println("try to find pass: " + doctor);
        String found_pass = doctorMapper.findPass(doctor);
        if(found_pass != null) {
            //비밀번호 복호화
            AES256CipherTest aes = new AES256CipherTest(found_pass);
            String des_pass = aes.desPass();

            //메일로 비밀번호 전송
            SendSimpleMail mail = new SendSimpleMail();
            mail.sendmail(des_pass, doctor.getE_mail());

            return new ResultVO("success",0);

        } else {
            System.out.println("해당하는 password 없음");
            return new ResultVO("false",0);
        }
    }



    //다이어리 상세보기
    @RequestMapping(method = RequestMethod.POST, value = "diary/getDiary")
    public DiaryVO getDiary(@RequestBody DiaryVO diary) throws Exception {
        //TODO 중복체크
        DiaryVO diaryVO = diaryMapper.getDiary(diary);
        System.out.println("diary: " + diaryVO);
        return diaryVO;
    }


    //수면일지 리스트 보기
    @RequestMapping(method = RequestMethod.POST, value = "diary/getSleepList")
    public ArrayList<SleepVO> getSleepList(@RequestBody UserVO userVO) throws Exception {
        //TODO 중복체크
        ArrayList<SleepVO> sleepVOs = diaryMapper.getAllSleepList(userVO);
        System.out.println("sleep list of : " + userVO.getU_id());


        return sleepVOs;
    }


    //수유일지 리스트 보기
    @RequestMapping(method = RequestMethod.POST, value = "diary/getFeedList")
    public ArrayList<FeedVO> getFeedList(@RequestBody UserVO userVO) throws Exception {
        //TODO 중복체크
        ArrayList<FeedVO> feedVOs = diaryMapper.getAllFeedList(userVO);
        System.out.println("feed list of : " + userVO.getU_id());


        return feedVOs;
    }

    //다이어리 리스트 보기
    @RequestMapping(method = RequestMethod.POST, value = "diary/getDiaryList")
    public ArrayList<DiaryVO> getDiaryList(@RequestBody UserVO userVO) throws Exception {
        //TODO 중복체크
        ArrayList<DiaryVO> diaryVO = diaryMapper.getDiaryList(userVO);
        System.out.println("diary list of : " + userVO.getU_id());


        return diaryVO;
    }


    //다이어리 리스트 보기
    @RequestMapping(method = RequestMethod.POST, value = "diary/getDataList")
    public ArrayList<DataVO> getDataList(@RequestBody UserVO userVO) throws Exception {
        //TODO 중복체크
        ArrayList<DataVO> dataVO = diaryMapper.getDataList(userVO);
        System.out.println("data list of : " + userVO.getU_id());


        return dataVO;
    }


    //다이어리 전체 리스트 보기
    @RequestMapping(method = RequestMethod.POST, value = "diary/allDiaryListPost")
    public ArrayList<DiaryVO> allDiaryListPost(@RequestBody DoctorVO doctorVO) throws Exception {
        //TODO 중복체크
        System.out.println("all diary list of : " + doctorVO.getA_id());
        ArrayList<DiaryVO> diaryVO = diaryMapper.getAllDiaryList(doctorVO);
        System.out.println("result : " + diaryVO);
        return diaryVO;
    }

    //전체 환자 리스트 보기
    @RequestMapping(method = RequestMethod.POST, value = "api/getAllPatientList")
    public ArrayList<UserVO> getAllPatientList(@RequestBody DoctorVO doctorVO) throws Exception {
        //TODO 중복체크
        System.out.println("doctor : " + doctorVO);
        ArrayList<UserVO> userVOs = doctorMapper.getAllPatientList(doctorVO);
        System.out.println("patient list of : " + userVOs);

        return userVOs;
    }


    //의사 병원이름 보기
    @RequestMapping(method = RequestMethod.POST, value = "api/getdoctorHospital")
    public ResultVO getdoctorHospital(@RequestBody DoctorVO doctorVO) throws Exception {
        return new ResultVO(doctorMapper.getdoctorHospital(doctorVO),0);
    }

/*
    //환자 등록
    @RequestMapping(method = RequestMethod.POST, value = "api/addPatient")
    public ResultVO addPatient(@RequestBody UserVO userVO) throws Exception {
        //TODO 중복체크
        System.out.println("doctor : " + userVO.getA_id());
        doctorMapper.addPatient(userVO);
        System.out.println("add patient of id : " + userVO.getU_id());

        return new ResultVO("success",0);
    }

    //환자 등록 취소
    @RequestMapping(method = RequestMethod.POST, value = "api/delPatient")
    public ResultVO delPatient(@RequestBody UserVO userVO) throws Exception {
        //TODO 중복체크
        System.out.println("doctor : " + userVO.getA_id());
        doctorMapper.delPatient(userVO);
        System.out.println("del patient of id : " + userVO.getU_id());

        return new ResultVO("success",0);
    }*/


    //qna 전체 리스트 보기
    @RequestMapping(method = RequestMethod.POST, value = "qna/QnaListPost")
    public ArrayList<QnaVO> QnaListPost(@RequestBody DoctorVO doctorVO) throws Exception {
        //TODO 중복체크
        System.out.println("all qna list of : " + doctorVO.getA_id());
        ArrayList<QnaVO> qnaVOs = qnaMapper.getQnaList();
        System.out.println("result : " + qnaVOs);
        return qnaVOs;
    }


    //qna 답변 등록
    @RequestMapping(method = RequestMethod.POST, value = "qna/answerQna")
    public ResultVO answerQna(@RequestBody QnaVO qnaVO) throws Exception {
        //TODO 중복체크
        System.out.println("answer qna of : " + qnaVO.getA_id());
        qnaMapper.answerQna(qnaVO);
        return new ResultVO("success",1);
    }

    //퇴원처리
    @RequestMapping(method = RequestMethod.POST, value = "api/discharged")
    public ResultVO discharged(@RequestBody UserVO userVO) throws Exception {
        //TODO 중복체크
        System.out.println("discharged user of : " + userVO.getU_id());
        userMapper.discharged(userVO);
        return new ResultVO("success",1);
    }

    //재입원처리
    @RequestMapping(method = RequestMethod.POST, value = "api/hospitalize")
    public ResultVO hospitalize(@RequestBody UserVO userVO) throws Exception {
        //TODO 중복체크
        System.out.println("hospitalize user of : " + userVO.getU_id());
        userMapper.hospitalize(userVO);
        return new ResultVO("success",1);
    }
}
