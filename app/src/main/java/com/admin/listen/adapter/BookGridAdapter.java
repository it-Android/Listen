package com.admin.listen.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.admin.listen.R;
import com.admin.listen.analysis.entity.GradeAllData;
import com.admin.listen.databinding.GrideItemBookBinding;
import com.admin.listen.utils.SyUtils;

import java.util.List;

/**
 * @作者(author)： JQ
 * @创建时间(date)： 2020/5/26 23:15
 **/
public class BookGridAdapter extends BaseAdapter {
    private List<GradeAllData.FrameData> list;
    private int[] size={0,0};

    public BookGridAdapter(List<GradeAllData.FrameData> list) {
        this.list = list;
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
        ViewHolder holder=null;
        if(convertView==null){
            holder=new ViewHolder();
            GrideItemBookBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.gride_item_book,parent,false);
            convertView=binding.getRoot();
            ViewGroup.LayoutParams params = binding.gridBookImgBg.getLayoutParams();
            int[] screen = SyUtils.getScreen(parent.getContext());
            size[0]=(screen[0]-300)/3;
            size[1]= (int) (size[0]/0.74);
            params.width=size[0];
            params.height=size[1];
            binding.gridBookImgBg.setLayoutParams(params);
            holder.binding=binding;
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.binding.gridBookName.setText(list.get(position).getFrameName());
        return convertView;
    }
    public int[] getItemSize() {

        return size;
    }

    class ViewHolder {
        GrideItemBookBinding binding;
    }

}
