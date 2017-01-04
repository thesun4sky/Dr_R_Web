package com.coawesome.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


/**
 * Created by TeasunKim on 2016-11-03.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SleepVO {
    private int s_id=0;
    private int u_id=0;
    private String u_name="";
    private java.sql.Timestamp s_start=new Timestamp(11111111);
    private java.sql.Timestamp s_end=new Timestamp(11111111);
    private int s_total=0;
}