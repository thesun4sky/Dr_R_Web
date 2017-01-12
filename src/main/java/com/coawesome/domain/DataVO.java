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
    private int d_temperature;            //온도
    private int d_dust;            //미세먼지
    private int d_co2;            //이산화탄소
    private int d_voc;            //VOC
}
/**

 CREATE TABLE `data_list` (
 `d_id` int(11) NOT NULL AUTO_INCREMENT,
 `u_id` int(11) NOT NULL,
 `d_temperature` int(90) DEFAULT NULL,
 `d_dust` int(90) DEFAULT NULL,
 `d_co2` int(90) DEFAULT NULL,
 `d_voc` int(90) DEFAULT NULL,
 PRIMARY KEY (`d_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


 **/