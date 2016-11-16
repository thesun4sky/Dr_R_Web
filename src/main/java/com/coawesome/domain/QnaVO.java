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
    private int u_id;
    private String u_name;
    private String qna_title;
    private String qna_content;
    private Date date;
    private int count;
}