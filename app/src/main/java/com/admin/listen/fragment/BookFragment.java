package com.admin.listen.fragment;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.admin.listen.R;
import com.admin.listen.adapter.BookAdapter;
import com.admin.listen.analysis.entity.GradeAllData;
import com.admin.listen.databinding.BookFragmentBinding;
import com.admin.listen.myView.DownLoadDialog;
import com.admin.listen.myView.RecycleViewLisitenter;
import com.admin.listen.utils.FileUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BookFragment extends Fragment implements RecycleViewLisitenter.onItemClickDataLisitenter {
    private BookFragmentBinding binding;
    private BookAdapter adapter;
    private Gson gson = new Gson();
    private List<GradeAllData> list = new ArrayList<>();
    private AlertDialog alertDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.book_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String string = FileUtil.readFile(getContext().getFilesDir().getAbsolutePath() + File.separator + "grade");
        Type type = new TypeToken<List<GradeAllData>>() {
        }.getType();
        list = gson.fromJson(string, type);
        adapter = new BookAdapter(list);
        adapter.setOnItemClickDataLisitenter(this::onItemDataClick);
        binding.fraBook.setAdapter(adapter);
        binding.fraBook.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onItemDataClick(View view, Object data, int position) {
        GradeAllData.FrameData frameData=(GradeAllData.FrameData)data;
        alertDialog= new AlertDialog.Builder(getContext())
                .setTitle("确定选择！")
                .setMessage(getSpan(frameData.getFrameName()))
                .setPositiveButton("确定", (DialogInterface dialog, int which) -> {
                    DownLoadDialog downLoadDialog =new DownLoadDialog(getContext());
                    downLoadDialog.show(frameData.getUrlPath());
                    //Navigation.findNavController(getView()).navigateUp();
                })
                .setNegativeButton("取消", (DialogInterface dialog, int which) -> {

                })
                .create();
        alertDialog.show();
    }

    private SpannableString getSpan(String str){
        SpannableString span = new SpannableString("是否将"+str+"加入学习！");
        span.setSpan(new ForegroundColorSpan(Color.RED), 3, str.length()+3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return span;
    }


}
