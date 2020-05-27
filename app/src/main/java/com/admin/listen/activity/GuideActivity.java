package com.admin.listen.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.admin.listen.R;
import com.admin.listen.activity.viewModel.GuideViewModel;
import com.admin.listen.utils.DbUtil;

public class GuideActivity extends AppCompatActivity {
    private GuideViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        DbUtil.init().setContext(getApplicationContext());
        viewModel=new  ViewModelProvider(this).get(GuideViewModel.class);

        viewModel.getVerify().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    startActivity(new Intent(GuideActivity.this,MainActivity.class));
                }
            }
        });
        viewModel.verify(0);
    }
}
