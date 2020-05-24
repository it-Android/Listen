package com.admin.listen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取顶部导航栏控件
        BottomNavigationView bottomNavigationView=findViewById(R.id.main_bottomNavigationView);
        //获取Navigation对象，即activity_main文件里面的fragment
        NavController navController= Navigation.findNavController(this,R.id.main_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
    }
}
