package com.coawesome.persistence;

import com.coawesome.domain.DiaryVO;
import com.coawesome.domain.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * Created by TeasunKim on 2016-11-03.
 */
@Mapper
public interface DiaryMapper {

    //다이어리 찾기
    @Select("select * from check_list where list_id = #{list_id}")
    DiaryVO getDiary(DiaryVO diaryVO);

    //다이어리 리스트 보기
    @Select("select * from check_list INNER JOIN user ON user.u_id = check_list.u_id where check_list.u_id = #{u_id} ORDER BY c_date DESC")
    ArrayList<DiaryVO> getDiaryList(UserVO userVO);
}
