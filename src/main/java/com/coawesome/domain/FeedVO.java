package com.coawesome.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedVO {
    private int f_id=0;
    private int u_id=0;
    private String u_name="";
    private java.sql.Timestamp f_start=new Timestamp(11111111);
    private java.sql.Timestamp f_end=new Timestamp(11111111);
    private int f_total = 0;
    private String feed = "";
}