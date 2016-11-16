package com.coawesome.persistence;

import com.coawesome.domain.QnaVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

/**
 * Created by TeasunKim on 2016-11-03.
 */
@Mapper
public interface QnaMapper {

    //QnA 목록 출력
    @Select("select * from qna")
    ArrayList<QnaVO> getQnaList();

    //Qna 작성
    @Insert("INSERT INTO qna (u_id, u_name, qna_title, qna_content)" +
            " VALUES(#{u_id}, #{u_name}, #{qna_title}, #{qna_content})")
    void makeQuestion(QnaVO qnaVO);

}
