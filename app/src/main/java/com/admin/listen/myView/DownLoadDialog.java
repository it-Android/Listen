package com.admin.listen.myView;

import android.app.ProgressDialog;
import android.content.Context;

import com.admin.listen.R;
import com.admin.listen.analysis.ExtractOkhttp;
import com.admin.listen.utils.FileUtil;
import com.admin.listen.utils.SyUtils;

import java.util.HashMap;


/**
 * @作者(author)： JQ
 * @创建时间(date)： 2020/5/28 12:56
 **/

public class DownLoadDialog extends ProgressDialog {
    private int time = -1;

    public DownLoadDialog(Context context) {
        super(context);
        settingInit(context);
    }

    private void settingInit(Context context) {
        this.setTitle("提示");
        this.setMessage("正在提取，请稍后...");
        this.setCancelable(false);
        setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        setMax(1);
    }

    public void show(String urlPath) {
        super.show();
        ExtractOkhttp okhttp = new ExtractOkhttp();
        if (time == -1) {
            time = 0;
            new Thread(runnable).start();
        }
        okhttp.downUnitToDetailed(urlPath, new ExtractOkhttp.AnalysisCallBack() {
            @Override
            public void onStartAnalysis(int num) {
                setMax(num);
            }

            @Override
            public void onProgressAnalysis(int progress) {
                setProgress(progress + 1);
                time = 0;
            }

            @Override
            public void onCompleteAnalysis(Object obj) {
                HashMap<String, String> hashMap;
                hashMap = (HashMap<String, String>) FileUtil.readSerialize(getContext(), getContext().getString(R.string.map_data));
                if (hashMap == null) {
                    hashMap = new HashMap<>();
                }
                String dbName = SyUtils.strToMD5(urlPath);
                if (!hashMap.containsKey(dbName)) {
                    hashMap.put(dbName, dbName);
                    FileUtil.writeSerialize(getContext(), getContext().getString(R.string.map_data), hashMap);
                }
                dismiss();
                time = -1;
            }
        });
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (time < 10) {
                try {
                    if (time == -1) {
                        return;
                    }
                    time++;
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            time = -1;
            dismiss();
        }
    };

}
