package com.admin.listen.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @作者(author)： JQ
 * @创建时间(date)： 2020/5/26 20:27
 **/
public class OkUtil {
    private OkHttpClient client;
    private  static OkUtil okUtil;
    public static OkUtil init(){
        if(okUtil==null){
            okUtil=new OkUtil();
        }
        return okUtil;
    }


    private OkHttpClient getClient() {
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .connectTimeout(10_000, TimeUnit.MILLISECONDS).build();
        }
        return client;
    }

    public Call okGet(String paramer) {
        Request request = new Request.Builder()
                .url(paramer)
                .build();
        return getClient().newCall(request);
    }

}
