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
    private int u_id;
    private String breakfast;
    private String lunch;
    private String dinner;
    private int temperature;
    private int humid;
    private int sleepTime;
    private int bloodPressure;
    private String drinking;
}
