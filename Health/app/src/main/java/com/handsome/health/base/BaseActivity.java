package com.handsome.health.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by zjw on 2018/12/24.
 */
public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    protected void toast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
    protected void startIntent(Class classIntent){
        Intent intent=new Intent();
        intent.setClass(this,classIntent);
        startActivity(intent);
    }
}
