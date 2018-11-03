package com.example.a92317.ltka;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BillAdapter extends ArrayAdapter<Bill> {

    private int resourceId;

    public BillAdapter(Context context, int textViewResourceId, List<Bill> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Bill bill=getItem(position);//获取当前实例
        View view;
        BillAdapter.ViewHolder viewHolder;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.billSum=(TextView)view.findViewById(R.id.my_view_sum);
            viewHolder.billRemarks=(TextView)view.findViewById(R.id.my_view_remarks);
            viewHolder.billTime=(TextView)view.findViewById(R.id.my_view_time);
            viewHolder.billClassification=(TextView)view.findViewById(R.id.my_view_classification);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder=(BillAdapter.ViewHolder)view.getTag();
        }
        viewHolder.billSum.setText(Double.toString(bill.getSum()));
        viewHolder.billRemarks.setText(bill.getRemarks());
        viewHolder.billTime.setText(bill.getTime());
        viewHolder.billClassification.setText(bill.getClassificationName());
        viewHolder.billClassification.setTextColor(bill.getSetClassificationColor());
        return view;
    }

    class ViewHolder{
        TextView billSum;
        TextView billRemarks;
        TextView billTime;
        TextView billClassification;
    }
}
