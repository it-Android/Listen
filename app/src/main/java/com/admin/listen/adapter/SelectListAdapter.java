package com.admin.listen.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.admin.listen.R;

import java.util.List;

/**
 * @作者(author)： JQ
 * @创建时间(date)： 2020/5/27 18:41
 **/
public class SelectListAdapter extends BaseAdapter {
    private List<String> list;
    private int number;

    public SelectListAdapter(List<String> list, int number) {
        this.list = list;
        this.number = number;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.select_list_item,parent,false);
        TextView textView=convertView.findViewById(R.id.select_item_text);
        if(number==position){
            textView.setTextColor(parent.getContext().getResources().getColor(R.color.colorText2));
        }else {
            textView.setTextColor(parent.getContext().getResources().getColor(R.color.colorText1));
        }
        textView.setText(list.get(position));
        return convertView;
    }
}
