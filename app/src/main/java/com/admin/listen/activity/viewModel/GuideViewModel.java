package com.admin.listen.activity.viewModel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.admin.listen.analysis.GradeAnalysis;
import com.admin.listen.analysis.entity.GradeAllData;
import com.admin.listen.utils.FileUtil;
import com.admin.listen.utils.OkUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @作者(author)： JQ
 * @创建时间(date)： 2020/5/26 20:15
 **/
public class GuideViewModel extends AndroidViewModel {
    private MutableLiveData<Boolean> verify;
    private List<GradeAllData> list = new ArrayList<>();

    public GuideViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Boolean> getVerify() {
        if (verify == null) {
            verify = new MutableLiveData<>();
        }
        return verify;
    }

    public void verify(int num) {
        Gson gson = new Gson();
        String paramer;
        String string = FileUtil.readFile(getApplication().getFilesDir().getAbsolutePath() + File.separator + "grade");
        if (string != null) {
            //Type type=new TypeToken<List<GradeAllData>>() {}.getType();
            try {
                Type type=new TypeToken<List<GradeAllData>>(){}.getType();
                list = gson.fromJson(string, type);
                getVerify().postValue(true);
                return;
            } catch (Exception e) {
            }
        }
        if (num == 1) {
            paramer = "https://www.suxuewang.com/word_juniorschool.html";
        } else if (num == 2) {
            paramer = "https://www.suxuewang.com/word_highschool.html";
        } else if (num >= 3) {
            String json = gson.toJson(list);
            boolean guide = FileUtil.writeFile(getApplication().getFilesDir().getAbsolutePath(), "grade", json);
            if (guide) {
                getVerify().postValue(true);
            } else {
                getVerify().postValue(false);
            }
            return;
        } else {
            paramer = "https://www.suxuewang.com/word_primaryschool.html";
        }
        OkUtil.init().okGet(paramer).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getVerify().postValue(true);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String html = response.body().string();
                GradeAnalysis analysis = new GradeAnalysis();
                GradeAllData allData = analysis.analysis(html);
                list.add(allData);
                verify(num + 1);
            }
        });
    }

}
