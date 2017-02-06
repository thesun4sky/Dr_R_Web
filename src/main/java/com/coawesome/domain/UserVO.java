package com.coawesome.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by TeasunKim on 2016-11-03.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    private int u_id;
    private String login_id;
    private String u_password;
    private String u_device;
    private String u_name;
    private int u_a_week;
    private int u_a_date;
    private String u_sex;
    private float u_w;
    private float u_h;
    private int p_id;
    private int a_id;
    private java.sql.Timestamp u_expected = null; //예정일자
    private java.sql.Timestamp u_born = null; //출생일자
    private java.sql.Timestamp u_join_date =null;
}
