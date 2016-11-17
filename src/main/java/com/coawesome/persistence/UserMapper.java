package com.coawesome.persistence;

import com.coawesome.domain.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by TeasunKim on 2016-11-03.
 */
@Mapper
public interface UserMapper {


    //로그인 아이디 체크
    @Select("select count(*) from user where login_id = #{login_id}")
    int checkUserLoginId(UserVO user);

    //디바이스로 부터 u_id찾기
    @Select("select u_id from user where u_device = #{u_device}")
    int findUserId(UserVO user);

    //device 체크
    @Select("select count(*) from user where u_device = #{u_device}")
    int checkUserDevice(UserVO user);

    //deviceID로 이름 찾기
    @Select("select u_id, u_name from user where u_device = #{u_device}")
    UserVO findName(UserVO user);

    //login_id로 이름 찾기
    @Select("select u_id, u_name from user where login_id = #{login_id}")
    UserVO findNameById(UserVO user);

    //비밀번호 찾기
    @Select("select u_password from user where login_id = #{login_id} and u_name = #{u_name}")
    String findPass(UserVO user);

    //회원가입
    @Insert("INSERT INTO user(login_id, u_password, u_name, u_phone, u_disease, u_hospital, u_device) VALUES(#{login_id}, #{u_password}, #{u_name}, #{u_phone}, #{u_disease}, #{u_hospital}, #{u_device})")
    void addUser(UserVO user);

    //로그인
    @Select("select u_password from user where login_id = #{login_id}")
    String userLogincheck(UserVO user);

}
