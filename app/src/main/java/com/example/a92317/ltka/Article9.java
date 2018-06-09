package com.example.a92317.ltka;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Article9 extends BaseActivity{

    private TextView articleName;
    private TextView articleAuthor;
    private TextView articleContents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_item_details);

        articleName=(TextView)findViewById(R.id.article_name);
        articleAuthor=(TextView)findViewById(R.id.article_author);
        articleContents=(TextView)findViewById(R.id.article_contents);

        articleName.setText((String) this.getResources().getText(R.string.article9_name));
        articleAuthor.setText((String) this.getResources().getText(R.string.article9_author));
        articleContents.setText((String) this.getResources().getText(R.string.article9_contents));

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.hide();
        }

        Button button_back=(Button)findViewById(R.id.title_back);
        button_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
}
