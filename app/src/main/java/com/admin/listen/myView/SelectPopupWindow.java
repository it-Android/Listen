package com.admin.listen.myView;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.admin.listen.R;
import com.admin.listen.adapter.SelectListAdapter;
import com.admin.listen.databinding.SelectPopupItemViewBinding;
import com.admin.listen.utils.SyUtils;

import java.util.List;

/**
 * @作者(author)： JQ
 * @创建时间(date)： 2020/5/27 18:14
 **/
public class SelectPopupWindow extends PopupWindow implements PopupWindow.OnDismissListener {
    private SelectPopupItemViewBinding binding;
    private SelectListAdapter adapter;
    private List<String> list;
    private Activity activity;
    private int number;

    public SelectPopupWindow(Activity activity, @NonNull List<String> list, int number) {
        super(activity);
        this.list = list;
        this.number = number;
        this.activity = activity;
        setPopupWindow(activity);
    }

    private void setPopupWindow(Activity activity) {
        this.setFocusable(true);// 设置弹出窗口点击外部消失
        //this.setAnimationStyle(R.style.pop_style_anim);// 设置动画
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));// 设置背景透明
        //View view=LayoutInflater.from(context).inflate(R.layout.select_popup_item_view, null, false);
        binding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.select_popup_item_view, null, false);
        adapter = new SelectListAdapter(list, number);
        binding.selectPopupList.setAdapter(adapter);
        this.setContentView(binding.getRoot());
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight((SyUtils.getScreen(activity)[1] / 3) * 2);
        this.setOnDismissListener(this::onDismiss);
        this.setAnimationStyle(R.style.popwin_anim_style);
    }

    public void showAtLocation(View parent) {
        super.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER, 0, 0);
        backgroundAlpha(0.5f);
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        activity.getWindow().setAttributes(lp);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    @Override
    public void onDismiss() {
        backgroundAlpha(1f);
    }
}
