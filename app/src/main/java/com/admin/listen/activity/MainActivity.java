package com.admin.listen.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import com.admin.listen.R;
import com.admin.listen.database.Word;
import com.admin.listen.database.WordDao;
import com.admin.listen.database.WordDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

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

        WordDao wordDao =WordDatabase.getDatabase(this).getWordDao();
        List<Word> list=new ArrayList<>();
        list.add(new Word("words","单词"));
        //wordDao.inserts(list);


    }
}
