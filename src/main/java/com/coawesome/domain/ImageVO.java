package com.coawesome.domain;

/**
 * Created by 이호세아 on 2016-04-26.
 */

/**
 * Created by eastflag on 2016-04-25.
 */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class ImageVO {
    private int id;
    private int board_id;
    private int login_id;
    private String user_img;
    private String original_file_name;
    private String stored_file_name;
    private long file_size;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public int getLogin_id() {
        return login_id;
    }

    public void setLogin_id(int login_id) {
        this.login_id = login_id;
    }

    public String getUser_img() {
        return user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public String getOriginal_file_name() {
        return original_file_name;
    }

    public void setOriginal_file_name(String original_file_name) {
        this.original_file_name = original_file_name;
    }

    public String getStored_file_name() {
        return stored_file_name;
    }

    public void setStored_file_name(String stored_file_name) {
        this.stored_file_name = stored_file_name;
    }

    public long getFile_size() {
        return file_size;
    }

    public void setFile_size(long file_size) {
        this.file_size = file_size;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public int getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }

    private String created_date;
    private int creator_id;
}
