package com.coawesome.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedVO {
    private int f_id;
    private int u_id;
    private String u_name;
    private java.sql.Timestamp f_date;
    private java.sql.Timestamp f_start;
    private java.sql.Timestamp f_end;
    private int f_total;
    private String feed;
}