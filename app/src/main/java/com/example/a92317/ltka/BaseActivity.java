package com.example.a92317.ltka;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("BaseActivity:onCreate",getClass().getSimpleName());
        Log.d("BaseActivity:Adress",this.toString());
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("BaseActivity:onDestroy",getClass().getSimpleName());
        ActivityCollector.removeActivity(this);
    }

    protected void onStart(){
        super.onStart();
        Log.d("BaseActivity:onStart",getClass().getSimpleName());
    }

    protected void onResume(){
        super.onResume();
        Log.d("BaseActivity:onResume",getClass().getSimpleName());
    }

    protected void onPause(){
        super.onPause();
        Log.d("BaseActivity:onPause",getClass().getSimpleName());
    }

    protected void onStop(){
        super.onStop();
        Log.d("BaseActivity:onStop",getClass().getSimpleName());
    }

    protected void onRestart(){
        super.onRestart();
        Log.d("BaseActivity:onRestart",getClass().getSimpleName());
    }

}

