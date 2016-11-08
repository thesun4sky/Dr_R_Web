package com.coawesome.persistence;

import com.coawesome.domain.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by TeasunKim on 2016-11-03.
 */
@Mapper
public interface UserMapper {

    //아이디 체크
    @Select("select login_id from user where login_id = #{login_id}")
    String checkUserId(UserVO user);

    //이름 찾기
    @Select("select u_name from user where login_id = #{login_id}")
    String findName(UserVO user);


    //회원가입
    @Insert("INSERT INTO user(u_id, login_id, u_name) VALUES(#{u_id}, #{login_id}, #{u_name})")
    void addUser(UserVO user);
}
