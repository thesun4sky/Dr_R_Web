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
public class SleepVO {
    private int s_id;
    private int u_id;
    private java.sql.Timestamp s_start;
    private java.sql.Timestamp s_end;
    private int s_total;
}