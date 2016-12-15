package com.coawesome.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

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
    private String u_name;
    private String u_phone;
    private String u_disease;
    private String u_hospital;
    private String u_device;
    private int a_id;
    private int[][] c_temperatureAndHumid;
    private int[][] c_sleepTime;
    private int[][] c_bloodPressure;
    private String[] c_date;
}
