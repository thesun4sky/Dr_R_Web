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

    //안드로이드 기기ID 체크
    @Select("select u_device from user where u_device = #{u_device}")
    String checkDeviceId(UserVO user);

    //아이디 체크
    @Select("select login_id from user where login_id = #{login_id}")
    String checkUserId(UserVO user);

    //아이디 체크
    @Select("select login_id from user where login_id = #{login_id}")
    String checkUserLoginId(UserVO user);

    //이름 찾기
    @Select("select u_name from user where login_id = #{login_id}")
    String findName(UserVO user);

    //안드로이드 기기ID로 이름 찾기
    @Select("select u_name from user where u_device = #{u_device}")
    String aFindName(UserVO user);

    //비밀전호 찾기
    @Select("select u_password from user where login_id = #{login_id} and u_name = #{u_name}")
    String findPASS(UserVO user);

    //회원가입(의사)
    @Insert("INSERT INTO user(u_id, login_id, u_password, u_name, u_hospital, u_position) VALUES(#{u_id}, #{login_id}, #{u_password}, #{u_name}, #{u_hospital}, 1)")
    void addUser(UserVO user);

    //로그인
    @Select("select u_password from user where login_id = #{login_id}")
    String checkLogin(UserVO user);

}
