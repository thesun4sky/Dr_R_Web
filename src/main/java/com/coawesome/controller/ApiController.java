package com.coawesome.controller;

import com.coawesome.cryptoUtil.AES256CipherTest;
import com.coawesome.domain.BoardVO;
import com.coawesome.domain.ImageVO;
import com.coawesome.domain.UserVO;
import com.coawesome.persistence.UserMapper;
import com.coawesome.domain.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * Created by TeasunKim on 2016-11-03.
 */
@RestController
public class ApiController {

    @Autowired
    private UserMapper userMapper;

    @Resource(name = "fileUtils")
    private FileUtils fileUtils;


    //아이디 체크
    @RequestMapping(method = RequestMethod.POST, value = "/user/checkID")
    public boolean checkUserId(@RequestBody UserVO user) throws Exception {

        System.out.println("cehck ID : " + user.getLogin_id());
        String found_id = userMapper.checkUserId(user);
        System.out.println("아이디 중복 체크 id: " + user.getLogin_id()+ "<< input id ,  found_id  >>" + found_id);
        if(user.getLogin_id().equals(found_id)){
            System.out.println("아이디 있음. 사용 불가");
            return false;
        }
        else {
            System.out.println("아이디 없음. 사용 가능");
            return true;
        }
    }

    //회원가입
    @RequestMapping(method = RequestMethod.POST, value = "/user/join")
    public String getUserData(@RequestBody UserVO user) throws Exception {
        System.out.println("Sign in : " + user.getLogin_id());
        String ori_pass = user.getU_password();
        //암호화
        AES256CipherTest aes = new AES256CipherTest(ori_pass);
        String en_pass = aes.encPass();
        //암호화한 후 set
        user.setU_password(en_pass);
        userMapper.addUser(user);
        return "true";
    }

    //로그인
    @RequestMapping(method = RequestMethod.POST, value = "/user/login")
    public boolean checkLogin(@RequestBody UserVO user) throws Exception {

        System.out.println("Login Check : " + user.getLogin_id());
        String input_pass = user.getU_password();
        String password = userMapper.checkLogin(user);
        System.out.println("패스워드 일치 체크 ");
        //복호화된 패스워드와 일치 확인
        AES256CipherTest aes = new AES256CipherTest(password);
        String des_pass = aes.desPass();
        if(input_pass.equals(des_pass)){
            System.out.println("비밀번호 일치" + input_pass +"/"+ des_pass);
            return true;
        }
        else {
            System.out.println("비밀번호 불일치" + input_pass +"/"+ des_pass);
            return false;
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
}
