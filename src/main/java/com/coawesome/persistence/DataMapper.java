package com.coawesome.persistence;

import com.coawesome.domain.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

/**
 * Created by TeasunKim on 2016-11-03.
 */
@Mapper
public interface DataMapper {

    //데이터 쓰기
    @Insert("INSERT INTO data_list(u_id, d_temperature, d_dust, d_co2, d_voc)" +
            " VALUES(#{u_id}, #{d_temperature}, #{d_dust}, #{d_co2}, #{d_voc})")
    void addData(DataVO dataVO);


}
