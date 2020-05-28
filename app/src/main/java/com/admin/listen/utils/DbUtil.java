package com.admin.listen.utils;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.admin.listen.database.WordDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * @作者(author)： JQ
 * @创建时间(date)： 2020/5/26 16:39
 **/
public class DbUtil {
    private final static String TAG="监控—DbUtil";
    private Map<String, WordDatabase> map = new HashMap<>();
    private Context context;
    private static DbUtil dbUtil =new DbUtil();

    public static DbUtil init(){
        return dbUtil;
    }

    private boolean containsKey(String key) {
        return map.containsKey(key);
    }

    public synchronized WordDatabase getDatabase(String key) {
        if (containsKey(key)) {
            return map.get(key);
        } else {
            if(context==null){
                Log.i(TAG,"没有上下文对象，初始化终止！");
                return null;
            }
            WordDatabase wordDatabase = Room.databaseBuilder(context.getApplicationContext(), WordDatabase.class, key)
                    .allowMainThreadQueries()
                    .build();
            map.put(key,wordDatabase);
            return wordDatabase;
        }
    }

    /**
     * 注意 使用该类必须设置Context 最好设置为getApplicationContext()(Application层) 这样以后使用就不需要重复设置了
     * @param context
     * @return
     */
    public DbUtil setContext(Context context) {
        this.context = context;
        return dbUtil;
    }

    public boolean isOk(){
        return context!=null;
    }

}
