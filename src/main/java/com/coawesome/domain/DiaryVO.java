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
    private int u_id;
    private String u_name;
    private String u_disease;
    private String c_breakfast;
    private String c_lunch;
    private String c_dinner;
    private int c_temperature;
    private int c_humid;
    private int c_sleepTime;
    private int c_bloodPressure;
    private String c_drinking;
    private String c_memo;
    private String c_date = null;
    private int img_id = 0;
    private String stored_file_name = null;
    private byte[] c_img;
}
