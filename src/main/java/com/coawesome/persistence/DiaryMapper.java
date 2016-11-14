package com.coawesome.persistence;

import com.coawesome.domain.DiaryVO;
import com.coawesome.domain.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by TeasunKim on 2016-11-03.
 */
@Mapper
public interface DiaryMapper {

    //다이어리 찾기
    @Select("select * from check_list where list_id = #{list_id}")
    DiaryVO getDiary(DiaryVO diaryVO);

    //다이어리 쓰기
    @Insert("INSERT INTO user(login_id, u_password, u_name, u_phone, u_disease, u_hospital, u_device) VALUES(#{login_id}, #{u_password}, #{u_name}, #{u_phone}, #{u_disease}, #{u_hospital}, #{u_device})")
    void addUser(UserVO user);


}
