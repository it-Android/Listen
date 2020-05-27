package com.admin.listen.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * @作者(author)： JQ
 * @创建时间(date)： 2020/5/25 11:18
 **/
@Entity
public class Word {
    @PrimaryKey
    @NonNull
    private String word;
    private String chineseMeaning;
    private String tag;

    public Word() {
    }


    @Ignore
    public Word(@NonNull String word, String chineseMeaning, String tag) {
        this.word = word;
        this.chineseMeaning = chineseMeaning;
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getChineseMeaning() {
        return chineseMeaning;
    }

    public void setChineseMeaning(String chineseMeaning) {
        this.chineseMeaning = chineseMeaning;
    }
}
