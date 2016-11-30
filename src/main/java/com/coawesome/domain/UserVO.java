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
    private int u_b_month;
    private int u_b_date;
    private int u_sex;
    private int u_w;
    private int u_h;
    private int p_id;
}
