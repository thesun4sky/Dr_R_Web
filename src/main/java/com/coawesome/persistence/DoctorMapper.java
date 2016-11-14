package com.coawesome.persistence;

import com.coawesome.domain.DoctorVO;
import com.coawesome.domain.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by TeasunKim on 2016-11-03.
 */
@Mapper
public interface DoctorMapper {

    //아이디 체크
    @Select("select e_mail from doctor where e_mail = #{e_mail}")
    String checkUserEmail(DoctorVO doctor);

    //비밀번호 찾기
    @Select("select a_password from doctor where e_mail = #{e_mail} and a_name = #{a_name}")
    String findPass(DoctorVO doctor);

    //회원가입(의사)
    @Insert("INSERT INTO doctor(e_mail, a_name, a_password, a_phone, a_hospital) VALUES(#{e_mail}, #{a_name}, #{a_password}, #{a_phone}, #{a_hospital})")
    void addUser(DoctorVO doctor);

    //로그인
    @Select("select a_password from doctor where e_mail = #{e_mail}")
    String doctorLogincheck(DoctorVO doctor);

    //이름 찾기
    @Select("select a_name from doctor where e_mail = #{e_mail}")
    String findName(DoctorVO user);


}
