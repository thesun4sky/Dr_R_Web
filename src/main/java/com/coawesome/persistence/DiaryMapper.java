package com.coawesome.persistence;

import com.coawesome.domain.DiaryVO;
import com.coawesome.domain.DoctorVO;
import com.coawesome.domain.ImageVO;
import com.coawesome.domain.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

/**
 * Created by TeasunKim on 2016-11-03.
 */
@Mapper
public interface DiaryMapper {

    //다이어리 찾기
    @Select("select * from check_list where list_id = #{list_id}")
    DiaryVO getDiary(DiaryVO diaryVO);

    //다이어리 쓰기
    @Insert("INSERT INTO check_list(u_id, c_temperature, c_humid, c_sleepTime, c_drinking, c_bloodPressure, c_memo, c_breakfast, c_lunch, c_dinner, c_img)" +
            " VALUES(#{u_id}, #{c_temperature}, #{c_humid}, #{c_sleepTime}, #{c_drinking}, #{c_bloodPressure}, #{c_memo}, #{c_breakfast}, #{c_lunch}, #{c_dinner}, #{c_img})")
    void addDiaryWithImg(DiaryVO diaryVO);


    //다이어리 쓰기
    @Insert("INSERT INTO check_list(u_id, c_temperature, c_humid, c_sleepTime, c_drinking, c_bloodPressure, c_memo, c_breakfast, c_lunch, c_dinner)" +
            " VALUES(#{u_id}, #{c_temperature}, #{c_humid}, #{c_sleepTime}, #{c_drinking}, #{c_bloodPressure}, #{c_memo}, #{c_breakfast}, #{c_lunch}, #{c_dinner})")
    void addDiary(DiaryVO diaryVO);

    //다이어리 이미지 업로드
    @Insert("INSERT INTO list_img(original_file_name, stored_file_name, list_id)" +
            " VALUES(#{original_file_name}, #{stored_file_name}, #{list_id})")
    void uploadDiaryImg(ImageVO imageVO);


    //다이어리 리스트 보기
    @Select("select * from check_list " +
            "INNER JOIN user ON user.u_id = check_list.u_id " +
            "WHERE check_list.u_id = #{u_id} ORDER BY c_date DESC")
    ArrayList<DiaryVO> getDiaryList(UserVO userVO);


    //다이어리 리스트 보기
    @Select("select * from check_list " +
            "   INNER JOIN d_u ON d_u.u_id = check_list.u_id " +
            "   INNER JOIN user ON user.u_id = check_list.u_id" +
            "   LEFT OUTER JOIN list_img ON list_img.list_id = check_list.list_id" +
            "   WHERE d_u.a_id = #{a_id} " +
            "   ORDER BY check_list.c_date DESC")
    ArrayList<DiaryVO> getAllDiaryList(DoctorVO doctorVO);


    //다이어리 존재확인 API
    @Select("select COUNT(*) from check_list where u_id = #{u_id} AND (to_days(now())-to_days(c_date) <= -1 ) AND (to_days(now())-to_days(c_date) > 0 )")
    int getPatientValue(DiaryVO diaryVO);

    //다이어리 입력 API
    @Update("update check_list SET c_temperature = #{c_temperature}, c_humid = #{c_humid} " +
            "WHERE u_id = #{u_id} AND (to_days(now())-to_days(c_date) <= -1 ) AND (to_days(now())-to_days(c_date) > 0 )")
    void setPatientValue(DiaryVO diaryVO);

    //지정날짜 다이어리 입력 API
    @Update("update check_list SET c_temperature = #{c_temperature}, c_humid = #{c_humid} " +
            "WHERE u_id = #{u_id} AND (to_days(#{c_date})-to_days(c_date) <= -1 ) AND (to_days(#{c_date})-to_days(c_date) > 0 )")
    void setPatientValueWithDate(DiaryVO diaryVO);


    //다이어리 온도 보기
    @Select("select c_temperature from check_list " +
            "INNER JOIN user ON user.u_id = check_list.u_id " +
            "WHERE check_list.u_id = #{u_id} ORDER BY c_date DESC LIMIT 10")
    ArrayList<Integer> getTemperature(UserVO userVO);

    //다이어리 습도 보기
    @Select("select c_humid from check_list " +
            "INNER JOIN user ON user.u_id = check_list.u_id " +
            "WHERE check_list.u_id = #{u_id} ORDER BY c_date DESC LIMIT 10")
    ArrayList<Integer> gethumid(UserVO userVO);

    //다이어리 날짜 보기
    @Select("select c_date from check_list " +
            "INNER JOIN user ON user.u_id = check_list.u_id " +
            "WHERE check_list.u_id = #{u_id} ORDER BY c_date DESC LIMIT 10")
    ArrayList<String> getDate(UserVO userVO);

    //다이어리 수면시간 보기
    @Select("select c_sleepTime from check_list " +
            "INNER JOIN user ON user.u_id = check_list.u_id " +
            "WHERE check_list.u_id = #{u_id} ORDER BY c_date DESC LIMIT 10")
    ArrayList<Integer> getSleepTime(UserVO userVO);

    //다이어리 수면시간 보기
    @Select("select c_bloodPressure from check_list " +
            "INNER JOIN user ON user.u_id = check_list.u_id " +
            "WHERE check_list.u_id = #{u_id} ORDER BY c_date DESC LIMIT 10")
    ArrayList<Integer> getBloodPressure(UserVO userVO);
}
