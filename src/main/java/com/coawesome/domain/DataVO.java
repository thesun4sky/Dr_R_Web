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
public class DataVO {
    private int d_id;
    private int u_id;                 //계정ID
    private double d_temperature;            //온도
    private double d_dust;            //미세먼지
    private double d_co2;            //이산화탄소
    private double d_voc;            //VOC
    private java.sql.Timestamp d_date = null;   //작성일
}
/**

 CREATE TABLE `data_list` (
 `d_id` int(11) NOT NULL AUTO_INCREMENT,
 `u_id` int(11) NOT NULL,
 `d_temperature` double DEFAULT NULL,
 `d_dust` double DEFAULT NULL,
 `d_co2` double DEFAULT NULL,
 `d_voc` double DEFAULT NULL,
 PRIMARY KEY (`d_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


 **/