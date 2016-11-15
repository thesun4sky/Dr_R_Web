package com.coawesome.persistence;

import com.coawesome.domain.DoctorVO;
import com.coawesome.domain.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * Created by TeasunKim on 2016-11-03.
 */
@Mapper
public interface DoctorMapper {

    //아이디 체크
    @Select("select e_mail from doctor where e_mail = #{e_mail}")
    String checkUserEmail(DoctorVO doctor);

    //병원이름 가져오기
    @Select("select DISTINCT a_hospital from doctor")
    ArrayList<String> getHospital();

    //비밀번호 찾기
    @Select("select a_password from doctor where e_mail = #{e_mail} and a_name = #{a_name}")
    String findPass(DoctorVO doctor);

    //아이디 찾기
    @Select("select a_id from doctor where e_mail = #{e_mail}")
    int findA_id(DoctorVO doctorVO);

    //회원가입(의사)
    @Insert("INSERT INTO doctor(e_mail, a_name, a_password, a_phone, a_hospital) VALUES(#{e_mail}, #{a_name}, #{a_password}, #{a_phone}, #{a_hospital})")
    void addUser(DoctorVO doctor);

    //로그인
    @Select("select a_password from doctor where e_mail = #{e_mail}")
    String doctorLogincheck(DoctorVO doctor);

    //이름 찾기
    @Select("select a_name from doctor where e_mail = #{e_mail}")
    String findName(DoctorVO user);

    //환자 리스트 찾기
    @Select("select * from d_u \n" +
            "INNER JOIN user ON user.u_id = d_u.u_id\n" +
            "WHERE d_u.a_id = #{a_id}; ")
    ArrayList<UserVO> getPatientList(DoctorVO doctorVO);

    //전체 환자 리스트 찾기
    @Select("select * from user ")   //임시
    ArrayList<UserVO> getAllPatientList(DoctorVO doctorVO);
}
