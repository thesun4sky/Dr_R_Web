package com.coawesome.persistence;

import com.coawesome.domain.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by TeasunKim on 2016-11-03.
 */
@Mapper
public interface UserMapper {

    //회원가입
    @Insert("INSERT INTO user(u_id, user_id, u_name) VALUES(#{u_id}, #{user_id}, #{u_name})")
    void addUser(UserVO user);
}
