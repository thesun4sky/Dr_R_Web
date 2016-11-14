package com.coawesome.controller;

import com.coawesome.config.SendSimpleMail;
import com.coawesome.cryptoUtil.AES256CipherTest;
import com.coawesome.domain.*;
import com.coawesome.persistence.DiaryMapper;
import com.coawesome.persistence.DoctorMapper;
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
public class ApiController {

    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DiaryMapper diaryMapper;
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
//        AES256CipherTest aes = new AES256CipherTest(ori_pass);
//        String en_pass = aes.encPass();
        //암호화한 후 set
//        doctor.setA_password(en_pass);
        doctor.setA_password(ori_pass);
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
        System.out.println("패스워드 일치 체크 ");
        //복호화된 패스워드와 일치 확인
        if(password != null) {
//            AES256CipherTest aes = new AES256CipherTest(password);
//            String des_pass = aes.desPass();
            if (input_pass.equals(password)) {
//            if (input_pass.equals(des_pass)) {
                System.out.println("비밀번호 일치" );
                return new ResultVO(doctorMapper.findPass(doctor),1);
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

    //다이어리 올리기
    @RequestMapping(method = RequestMethod.POST, value = "/api/board")
    public boolean addBoard(@RequestParam("file") MultipartFile file, BoardVO board) throws Exception {
        //TODO 중복체크
//        boardMapper.insertBoard(board);
//        int storedBoardId = boardMapper.selectBoardId(board);
//        board.setBoard_id(storedBoardId);
//        System.out.println("board: " + board);
          ImageVO image = fileUtils.parseInsertFileInfo(file, board);
//        boardMapper.insertBoardImage(image);
        return true;
    }


    //다이어리 상세보기
    @RequestMapping(method = RequestMethod.POST, value = "diary/getDiary")
    public DiaryVO getDiary(@RequestBody DiaryVO diary) throws Exception {
        //TODO 중복체크
        DiaryVO diaryVO = diaryMapper.getDiary(diary);
        System.out.println("diary: " + diaryVO);
        return diaryVO;
    }


    //다이어리 리스트 보기
    @RequestMapping(method = RequestMethod.POST, value = "diary/getDiaryList")
    public ArrayList<DiaryVO> getDiaryList(@RequestBody UserVO userVO) throws Exception {
        //TODO 중복체크
        ArrayList<DiaryVO> diaryVO = diaryMapper.getDiaryList(userVO);
        System.out.println("diary list of : " + userVO.getU_id());


        return diaryVO;
    }


    //환자 리스트 보기
    @RequestMapping(method = RequestMethod.POST, value = "api/getPatientList")
    public ArrayList<UserVO> getPatientList(@RequestBody DoctorVO doctorVO) throws Exception {
        //TODO 중복체크
        ArrayList<UserVO> userVOs = doctorMapper.getPatientList(doctorVO);
        System.out.println("patient list of : " + doctorVO.getE_mail());

        return userVOs;
    }

}
