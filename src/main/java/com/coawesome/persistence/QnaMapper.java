package com.coawesome.persistence;

import com.coawesome.domain.QnaVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

/**
 * Created by TeasunKim on 2016-11-03.
 */
@Mapper
public interface QnaMapper {

    //QnA 목록 출력
    @Select("select * from qna INNER JOIN user ON user.u_id = qna.u_id")
    ArrayList<QnaVO> getQnaList();

    //Qna 작성
    @Insert("INSERT INTO qna (u_id, u_name, qna_question)" +
            " VALUES(#{u_id}, #{u_name}, #{qna_question})")
    void makeQuestion(QnaVO qnaVO);


    //Qna 답변 작성
    @Update("UPDATE qna SET qna_answer = #{qna_answer}, a_id = #{a_id} WHERE qna_id = #{qna_id}")
    void answerQna(QnaVO qnaVO);

}
