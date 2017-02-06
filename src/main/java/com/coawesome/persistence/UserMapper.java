package com.coawesome.persistence;

import com.coawesome.domain.DiaryVO;
import com.coawesome.domain.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;

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

    //디바이스로 부터 u_id 조회
    @Select("select COUNT(*) from user where u_id = #{u_id}")
    int findUserU_id(DiaryVO diaryVO);

    //device 체크
    @Select("select count(*) from user where u_device = #{u_device}")
    int checkUserDevice(UserVO user);

    //deviceID 업데이트
    @Update("update user SET u_device = #{u_device} WHERE login_id = #{login_id}")
    void setDeviceID(UserVO user);

    //deviceID 삭제
    @Update("update user SET u_device = 0 WHERE u_device = #{u_device}")
    void deleteDeviceID(UserVO user);

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
    @Insert("INSERT INTO user(login_id, u_password, u_name, u_device, u_a_week, u_a_date, u_b_month, u_b_date, u_sex, u_w, u_h, u_born, u_join_date ) VALUES(#{login_id}, #{u_password}, #{u_name}, #{u_device}, #{u_a_week}, #{u_a_date}, #{u_b_month}, #{u_b_date}, #{u_sex}, #{u_w}, #{u_h}, #{u_born}, #{u_join_date})")
    void addUser(UserVO user);

    //로그인
    @Select("select u_password from user where login_id = #{login_id}")
    String userLogincheck(UserVO user);

    //사용자 정보
    @Select("select * from user where u_id = #{u_id}")
    UserVO getDetail(UserVO user);

    //성별 받아오기
    @Select("select u_sex from user where u_id = #{u_id}")
    String getSex(UserVO user);

}
