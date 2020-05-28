package com.admin.listen.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import com.admin.listen.R;
import com.admin.listen.database.Word;
import com.admin.listen.database.WordDao;
import com.admin.listen.myView.DownLoadDialog;
import com.admin.listen.myView.SelectPopupWindow;
import com.admin.listen.utils.DbUtil;
import com.admin.listen.utils.SyUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取顶部导航栏控件
        bottomNavigationView=findViewById(R.id.main_bottomNavigationView);
        //获取Navigation对象，即activity_main文件里面的fragment
        NavController navController= Navigation.findNavController(this,R.id.main_fragment);
        navController.addOnDestinationChangedListener(listener);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
    }
    NavController.OnDestinationChangedListener listener=new NavController.OnDestinationChangedListener() {
        @Override
        public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
            if(destination.getId()==R.id.bookFragment){
                bottomNavigationView.setVisibility(View.GONE);
            }else {
                bottomNavigationView.setVisibility(View.VISIBLE);
            }
        }
    };

}
