package com.example.a92317.ltka;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ArticleAdapter extends ArrayAdapter<Article> {

    private int resourceId;

    public ArticleAdapter(Context context, int textViewResourceId, List<Article> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Article article=getItem(position);//获取当前实例
        View view;
        ViewHolder viewHolder;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.articleName=(TextView)view.findViewById(R.id.article_name);
            viewHolder.articleAuthor=(TextView)view.findViewById(R.id.article_author);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        viewHolder.articleName.setText(article.getName());
        viewHolder.articleAuthor.setText(article.getAuthor());
        return view;
    }

    class ViewHolder{
        TextView articleName;
        TextView articleAuthor;
    }
}
