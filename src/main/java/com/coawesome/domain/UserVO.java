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
    private ArrayList<Integer> c_temperature;
    private ArrayList<Integer> c_humid;
    private ArrayList<Integer> c_sleepTime;
    private ArrayList<Integer> c_bloodPressure;
}
