package com.admin.listen.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * @作者(author)： JQ
 * @创建时间(date)： 2020/5/25 15:10
 **/
@Dao
public interface WordDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void inserts(Word... words);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void inserts(List<Word> words);

    @Update
    void updata(Word... words);

    @Update
    void updata(List<Word> words);

    @Delete
    void delete(Word... words);
    @Delete
    void delete(List<Word> words);
    @Query("DELETE FROM Word")
    void deleteAllWords();
    @Query("DELETE FROM Word WHERE english= :name")
    void delete(String name);

    @Query("SELECT * FROM WORD")
    LiveData<List<Word>> getLiveAllWords();
    @Query("SELECT * FROM WORD")
    List<Word> getAllWords();
    @Query("SELECT * FROM WORD WHERE english= :name")
    LiveData<Word> getLiveWords(String name);
    @Query("SELECT * FROM WORD WHERE english= :name")
    Word getWord(String name);
    @Query("SELECT * FROM WORD WHERE english LIKE '%' || :name || '%'")
    LiveData<List<Word>> getLiveVagueWords(String name);
}
