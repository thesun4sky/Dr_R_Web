package com.coawesome.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Hosea on 2016-11-09.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnaVO {
    private int qna_id;
    private int u_id;
    private int a_id;
    private String u_name;
    private String u_disease;
    private String qna_question;
    private String qna_answer;
    private String date;
    private int count;
}