package com.coawesome.persistence;

import com.coawesome.domain.DoctorVO;
import com.coawesome.domain.UserVO;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.Map;

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
    ArrayList<Map> getHospital();

    //의사 번호 가져오기 가져오기
    @Select("select doctor.a_phone from doctor INNER JOIN d_u ON d_u.a_id = doctor.a_id WHERE d_u.u_id = #{u_id} limit 1")
    Map getDocPhone(UserVO userVO);

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
    @Select("select * from user \n" +
            "INNER JOIN user ON user.u_id = u.u_id\n")
    ArrayList<UserVO> getPatientList(UserVO userVO);

    //의사 병원 환자 리스트 찾기
    @Select("select * from user ORDER BY u_name")
    ArrayList<UserVO> getAllPatientList(DoctorVO doctorVO);

    //의사 병원 환자 리스트 찾기(성별)
    @Select("select * from user WHERE u_sex = #{u_sex} ORDER BY u_name")
    ArrayList<UserVO> getAllPatientListBySex(DoctorVO doctorVO);

    //의사 병원이름 가져오기
    @Select("select a_hospital from doctor WHERE a_id = #{a_id}")
    String getdoctorHospital(DoctorVO doctorVO);

    //환자 등록
    @Insert("INSERT INTO d_u(a_id, u_id) VALUES(#{a_id}, #{u_id})")
    void addPatient(UserVO userVO);

    //환자 등록 취소
    @Delete("DELETE FROM d_u WHERE a_id = #{a_id} AND u_id = #{u_id}")
    void delPatient(UserVO userVO);
}
