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
    private String u_name;
    private String u_hospital;

    public int getU_id() {
        return u_id;
    }

    public String getLogin_id() {
        return login_id;
    }

    public String getU_name() {
        return u_name;
    }

    public  String getU_password(){
        return u_password;
    }
    public  String getU_hospital(){
        return u_hospital;
    }
    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }
    public void setU_password(String u_password){
        this.u_password = u_password;
    }
    public void setU_hospital(String u_hospital){
        this.u_hospital = u_hospital;
    }
}
