package com.coawesome.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Hosea on 2016-11-09.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiaryVO {
    private int list_id;
    private int u_id;                 //계정ID
    private String u_name;            //아기 이름
    private float c_h;                //아기 키
    private float c_w;                //아기 몸무게
    private int c_feed_hour;          //수유시간
    private int c_feed_type;         //수유 타입
    private String c_hospital;       //병원
    private String c_treat=null;     //증상(열,감기,설사)
    private String c_shot=null;      //접종 주사
    private String c_next = null;  //예상 진료일
    private String c_depart;                    //진료과
    private String c_img ;                       //이미지
    private String c_memo;                      //엄마일기
    private java.sql.Timestamp c_date = null;   //작성일
}
