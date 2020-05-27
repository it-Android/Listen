package com.admin.listen.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.listen.R;
import com.admin.listen.analysis.entity.GradeAllData;
import com.admin.listen.databinding.RecyclerItemBookBinding;
import com.admin.listen.myView.SelectPopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者(author)： JQ
 * @创建时间(date)： 2020/5/26 22:29
 **/
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private List<GradeAllData> list;
    private Context context;

    public BookAdapter(List<GradeAllData> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        RecyclerItemBookBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.recycler_item_book, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GradeAllData allData = list.get(position);//小学的全部书本
        holder.binding.recycleBookGrade.setText(allData.getGrade());//小学
        List<GradeAllData.GradeData> list = allData.getList();//
        final int number=0;
        GradeAllData.GradeData gradeData = list.get(number);//一个类型
        holder.binding.recycleBookFrame.setText(gradeData.getName());//类型名称
        initGrideView(holder.binding.recycleBookGrid, gradeData.getFrame());
        holder.binding.recycleBookEdition.setOnClickListener((view)->{
            List<String> typeList=new ArrayList<>();
            for(GradeAllData.GradeData grade:list){
                typeList.add(grade.getName());
            }
            SelectPopupWindow popupWindow=new SelectPopupWindow((Activity) context,typeList,number);
            popupWindow.showAtLocation(view);
        });
    }

    private void initGrideView(GridView gridView, List<GradeAllData.FrameData> list) {
        BookGridAdapter adapter = new BookGridAdapter(list);
        gridView.setHorizontalSpacing(300/3);
        gridView.setVerticalSpacing(80);
        gridView.setAdapter(adapter);
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        gridView.post(() -> {
            int[] itemSize = adapter.getItemSize();
            int num=(int) Math.ceil(list.size()/3.0);
            params.height= (itemSize[1]+80)*num;
            gridView.setLayoutParams(params);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerItemBookBinding binding;

        public ViewHolder(@NonNull RecyclerItemBookBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
