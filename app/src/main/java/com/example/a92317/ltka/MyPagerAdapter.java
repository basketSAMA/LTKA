package com.example.a92317.ltka;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<BillAnalysisData> mData;

    public MyPagerAdapter(Context context , List<BillAnalysisData> list) {
        mContext = context;
        mData = list;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.page_item,null);
        TextView text = (TextView) view.findViewById(R.id.page_item_text);
        TextView left = (TextView) view.findViewById(R.id.page_item_left);
        TextView right = (TextView) view.findViewById(R.id.page_item_right);
        TextView sum = (TextView) view.findViewById(R.id.page_item_sum);
        TextView aver = (TextView) view.findViewById(R.id.page_item_aver);
        TextView anal = (TextView) view.findViewById(R.id.page_item_anal);
        if(position == 0)
        {
            left.setText("");
            sum.setText("");
            aver.setText("");
            anal.setText("");
            text.setTextSize(20);
        }
        if(position == getCount() - 1)
        {
            right.setText("");
            sum.setText("");
            aver.setText("");
            anal.setText("");
            text.setTextSize(20);
        }
        text.setText(mData.get(position).getLabel());
        text.setTextColor(mData.get(position).getColor());
        sum.setText(mData.get(position).getSum());
        aver.setText(mData.get(position).getAver());
        anal.setText(mData.get(position).getAnal());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container,position,object); 这一句要删除，否则报错
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
