package com.coawesome.persistence;

import com.coawesome.domain.*;
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
    @Insert("INSERT INTO check_list(u_id, c_shot, c_treat, c_h, c_w, c_memo, c_hospital, c_depart, c_next, c_date)" +
            " VALUES(#{u_id}, #{c_shot}, #{c_treat}, #{c_h}, #{c_w}, #{c_memo}, #{c_hospital}, #{c_depart}, #{c_next}, #{c_date})")
    void addDiary(DiaryVO diaryVO);

    //다이어리 쓰기(with Img)
    @Insert("INSERT INTO check_list(u_id, c_shot, c_treat, c_h, c_w, c_memo, c_hospital, c_depart, c_next, c_date, c_img)" +
            " VALUES(#{u_id}, #{c_shot}, #{c_treat}, #{c_h}, #{c_w}, #{c_memo}, #{c_hospital}, #{c_depart}, #{c_next}, #{c_date}, #{c_img})")
    void addDiaryWithImg(DiaryVO diaryVO);

    //다이어리 업데이트(with newImg)
    @Update("UPDATE check_list SET c_shot =  #{c_shot}, c_treat = #{c_treat}, c_h = #{c_h}, c_w = #{c_w}, c_memo = #{c_memo}, c_hospital = #{c_hospital}, c_depart = #{c_depart}, c_next = #{c_next}, c_img = #{c_img} " +
            "WHERE u_id = #{u_id} AND c_date = #{c_date}")
    void updateDiaryWithNewImg(DiaryVO diaryVO);

    //다이어리 업데이트(with prevImg)
    @Update("UPDATE check_list SET c_shot =  #{c_shot}, c_treat = #{c_treat}, c_h = #{c_h}, c_w = #{c_w}, c_memo = #{c_memo}, c_hospital = #{c_hospital}, c_depart = #{c_depart}, c_next = #{c_next} " +
            "WHERE u_id = #{u_id} AND c_date = #{c_date}")
    void updateDiaryWithPrevImg(DiaryVO diaryVO);

    //다이어리 리스트 보기
    @Select("select * from check_list INNER JOIN user ON user.u_id = check_list.u_id where check_list.u_id = #{u_id} ORDER BY c_date DESC")
    ArrayList<DiaryVO> getDiaryList(UserVO userVO);

    //다이어리 보기
    @Select("select * from check_list INNER JOIN user ON user.u_id = check_list.u_id where check_list.u_id = #{u_id} AND check_list.c_date = #{c_date} LIMIT 1")
    DiaryVO getDiaryByDate(DiaryVO diaryVO);

    //다이어리 리스트 보기
    @Select("select * from check_list " +
            "   INNER JOIN d_u ON d_u.u_id = check_list.u_id " +
            "   INNER JOIN user ON user.u_id = check_list.u_id" +
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

    //다이어리 수면시간 쓰기
    @Insert("INSERT INTO sleep_list(u_id, s_start, s_end, s_total )" +
            " VALUES(#{u_id}, #{s_start}, #{s_end}, #{s_total})")
    void addSleepTime(SleepVO sleepVO);

    //다이어리 수면시간 리스트 보기
    @Select("select * from sleep_list " +
            "   INNER JOIN user ON user.u_id = sleep_list.u_id" +
            "   WHERE sleep_list.u_id = #{u_id} " +
            "   ORDER BY sleep_list.f_start DESC")
    ArrayList<SleepVO> getAllSleepList(UserVO userVO);

    //해당 날짜 다이어리 수면시간 리스트 보기
    @Select("select * from sleep_list WHERE u_id = #{u_id} AND date(s_start) = date(#{s_start}) ")
    ArrayList<SleepVO> getSleepTime(SleepVO sleepVO);


    //다이어리 수유시간 쓰기
    @Insert("INSERT INTO feed_list(u_id, f_start, f_end, f_total, feed )" +
            " VALUES(#{u_id}, #{f_start}, #{f_end}, #{f_total}, #{feed})")
    void addFeedTime(FeedVO feedVO);

    //다이어리 수유시간 리스트 보기
    @Select("select * from feed_list " +
            "   INNER JOIN user ON user.u_id = feed_list.u_id" +
            "   WHERE feed_list.u_id = #{u_id} " +
            "   ORDER BY feed_list.f_start DESC")
    ArrayList<FeedVO> getAllFeedList(UserVO userVO);


    //다이어리 수유시간 해당일자 리스트 조회
    @Select("select * from feed_list " +
            "   INNER JOIN user ON user.u_id = feed_list.u_id" +
            "   WHERE feed_list.u_id = #{u_id} AND date(f_start) = date(#{f_date}) )" +
            "   ORDER BY feed_list.f_start DESC")
    ArrayList<FeedVO> getAllFeedListByDate(FeedVO feedVO);


}
