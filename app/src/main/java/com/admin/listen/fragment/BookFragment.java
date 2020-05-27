package com.admin.listen.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.admin.listen.R;
import com.admin.listen.adapter.BookAdapter;
import com.admin.listen.analysis.entity.GradeAllData;
import com.admin.listen.databinding.BookFragmentBinding;
import com.admin.listen.utils.FileUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BookFragment extends Fragment {
    private BookFragmentBinding binding;
    private BookAdapter adapter;
    private Gson gson=new Gson();
    private List<GradeAllData> list=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.book_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String string = FileUtil.readFile(getContext().getFilesDir().getAbsolutePath() + File.separator + "grade");
        Type type=new TypeToken<List<GradeAllData>>(){}.getType();
        list=gson.fromJson(string,type);
        adapter=new BookAdapter(list);
        binding.fraBook.setAdapter(adapter);
        binding.fraBook.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
