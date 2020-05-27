package com.admin.listen.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * @作者(author)： JQ
 * @创建时间(date)： 2020/5/25 15:32
 **/
@Database(entities = {Word.class},version = 1,exportSchema = false)
public abstract class  WordDatabase extends RoomDatabase {
    public abstract WordDao getWordDao();
}
