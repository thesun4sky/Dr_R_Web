package com.coawesome.controller;

import com.coawesome.cryptoUtil.AES256CipherTest;
import com.coawesome.domain.*;
import com.coawesome.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by TeasunKim on 2016-11-09.
 */
@RestController
public class ApiController {

    @Autowired
    private DiaryMapper diaryMapper;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private QnaMapper qnaMapper;

    @Autowired
    private DataMapper dataMapper;

    @Resource(name = "fileUtils")
    private FileUtils fileUtils;



    //회원가입
    @RequestMapping(method = RequestMethod.POST, value = "/api/addData")
    public String addData(@RequestBody DataVO dataVO) throws Exception {
        System.out.println("Add Data of : " + dataVO.getU_id());
        java.sql.Timestamp today = new Timestamp(System.currentTimeMillis()+32400000);
        dataVO.setD_date(today);
        dataMapper.addData(dataVO);
        return "true";
    }

}
