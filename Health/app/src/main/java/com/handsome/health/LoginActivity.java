package com.handsome.health;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;
import com.handsome.health.base.BaseActivity;
import com.handsome.health.net.HttpUtils;

import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by zjw on 2018/12/24.
 */
public class LoginActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {
    private EditText accountEdit, pwdEdit;
    private Button regBtn, loginBtn;
    private CheckBox rememberCheckBox;
    private SharedPreferences spf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initview();
        regBtn.setOnClickListener(listener);
        loginBtn.setOnClickListener(listener);
        rememberCheckBox.setOnCheckedChangeListener(this);
        spf = getSharedPreferences("user",MODE_PRIVATE);
    }

    private void initview() {
        accountEdit = (EditText) findViewById(R.id.login_account);
        pwdEdit = (EditText) findViewById(R.id.login_password);
        regBtn = (Button) findViewById(R.id.login_reg_btn);
        loginBtn = (Button) findViewById(R.id.login_login_btn);
        rememberCheckBox = (CheckBox) findViewById(R.id.login_remember_password);


    }

    private View.OnClickListener listener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.login_login_btn:
                    if (TextUtils.isEmpty(accountEdit.getText())){
                        toast("用户名为空");
                        return;
                    }
                    else if(TextUtils.isEmpty(pwdEdit.getText())){
                        toast("密码不能为空");
                        return;
                    }
                    else {
                        login();
                    }
                    break;
                case R.id.login_reg_btn:
                    startIntent(RegisterActivity.class);
                    break;
            }
        }


    };

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        SharedPreferences.Editor editor=spf.edit();
        if (isChecked){

        }
        else {

        }
    }
    private void login() {
//        创建线程
        Runnable runnabe=new Runnable() {
            @Override
            public void run() {
                try {
                    HashMap<String,Object>map=new HashMap<>();
                    map.put("account",accountEdit.getText().toString().trim());
                    map.put("password",pwdEdit.getText().toString().trim());
                    final String result=  HttpUtils.doPost("login.aspx",map);
                   //final String result=  HttpUtils.doPost("login",map);
                    Log.e("IAG","====="+result);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //toast(result);
                            try {
                                //将数据转化为json格式
                                JSONObject jsonObject = new JSONObject(result);
                                int code=jsonObject.getInt("code");
                                if (code==0){
                                    AppApplication.account=accountEdit.getText().toString().trim();
                                    startIntent(MainActivity.class);
                                    toast("登录成功");
                                }
                                else{
                                    toast(jsonObject.getString("msg"));
                                }
                                //取出
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnabe).start();
    }
}
