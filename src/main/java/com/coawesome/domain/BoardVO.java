package com.coawesome.domain;

/**
 * Created by 이호세아 on 2016-04-26.
 */

import java.util.ArrayList;

/**
 * Created by eastflag on 2016-04-25.
 */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class BoardVO {
    private int board_id;
    private int u_id;
    private int public_level;
    private int likes_num;

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getPublic_level() {
        return public_level;
    }

    public void setPublic_level(int public_level) {
        this.public_level = public_level;
    }

    public int getLikes_num() {
        return likes_num;
    }

    public void setLikes_num(int likes_num) {
        this.likes_num = likes_num;
    }

    public int getFavorite_num() {
        return favorite_num;
    }

    public void setFavorite_num(int favorite_num) {
        this.favorite_num = favorite_num;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public int getLine1_x() {
        return line1_x;
    }

    public void setLine1_x(int line1_x) {
        this.line1_x = line1_x;
    }

    public int getLine1_y() {
        return line1_y;
    }

    public void setLine1_y(int line1_y) {
        this.line1_y = line1_y;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public int getLine2_x() {
        return line2_x;
    }

    public void setLine2_x(int line2_x) {
        this.line2_x = line2_x;
    }

    public int getLine2_y() {
        return line2_y;
    }

    public void setLine2_y(int line2_y) {
        this.line2_y = line2_y;
    }

    public int getWord_table_id() {
        return word_table_id;
    }

    public void setWord_table_id(int word_table_id) {
        this.word_table_id = word_table_id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getCatagory() {
        return catagory;
    }

    public void setCatagory(int catagory) {
        this.catagory = catagory;
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }

    private int favorite_num;
    private String tag1;
    private String tag2;
    private String tag3;
    private String line1;
    private int line1_x;
    private int line1_y;
    private String line2;
    private int line2_x;
    private int line2_y;
    private int word_table_id;
    private String created;
    private int catagory;
    private ArrayList<String> words;
//    private ArrayList<HashMap> values;
}
