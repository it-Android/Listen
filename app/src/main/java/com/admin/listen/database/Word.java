package com.admin.listen.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.admin.listen.analysis.entity.DetailedData;

import java.util.List;

/**
 * @作者(author)： JQ
 * @创建时间(date)： 2020/5/25 11:18
 **/
@Entity
public class Word {
    @PrimaryKey
    @NonNull
    private String english;//英语
    private String chinese;//中文
    private String phoneticSymbol;//音标
    private String pronunciation;//发音连接
    private String skill;//技巧
    private String listExample;//例子

    @NonNull
    public String getEnglish() {
        return english;
    }

    public void setEnglish(@NonNull String english) {
        this.english = english;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getPhoneticSymbol() {
        return phoneticSymbol;
    }

    public void setPhoneticSymbol(String phoneticSymbol) {
        this.phoneticSymbol = phoneticSymbol;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getListExample() {
        return listExample;
    }

    public void setListExample(String listExample) {
        this.listExample = listExample;
    }
}


