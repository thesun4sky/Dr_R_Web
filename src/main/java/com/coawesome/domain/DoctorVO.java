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
public class DoctorVO {
    private int a_id;
    private String e_mail;
    private String a_name;
    private String a_password;
    private String a_phone;
    private String a_hospital;
}
