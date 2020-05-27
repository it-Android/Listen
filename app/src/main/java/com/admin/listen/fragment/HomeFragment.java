package com.admin.listen.fragment;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.admin.listen.databinding.HomeFragmentBinding;
import com.admin.listen.fragment.viewmodel.HomeViewModel;
import com.admin.listen.R;
import com.admin.listen.myView.SelectPopupWindow;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    private NavController controller;
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }
    private HomeFragmentBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.home_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        controller= Navigation.findNavController(getView());

        binding.mainBtn1.setOnClickListener((view)->{
            controller.navigate(R.id.action_homeFragment_to_bookFragment);
        });
    }

}
