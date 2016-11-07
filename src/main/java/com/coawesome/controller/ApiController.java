package com.coawesome.controller;

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

        System.out.println("Sign in : " + user.getU_name());
        userMapper.addUser(user);
        return "true";
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
