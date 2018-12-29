package com.example.zjw.friendcircle;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.network.zhouwei.http_network.Server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.login_button);
        Button button1 = (Button) findViewById(R.id.register_button);
        button1.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, register.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            EditText editText = (EditText) findViewById(R.id.username);
                            String name = editText.getText().toString();
                            String pass = ((EditText) findViewById(R.id.passwd)).getText().toString();
                            URL url = new URL("http://119.29.60.170/index.aspx?type=login&username=" + name + "&password=" + pass);
                            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                            httpURLConnection.setRequestMethod("GET");
                            InputStream inputStream = httpURLConnection.getInputStream();
                            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,
                                    "utf-8");
                            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                            StringBuffer stringBuffer = new StringBuffer();
                            String temp;

                            while ((temp = bufferedReader.readLine()) != null) {
                                stringBuffer.append(temp);
                            }
                            if (stringBuffer.toString().equals("login success")) {   //判断登录是否成功

                                Intent intent = new Intent(MainActivity.this, fcinterface.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("username", name);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                Looper.prepare();
                                Toast.makeText(getApplicationContext(), "登入成功", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            } else {
                                Looper.prepare();
                                Toast.makeText(getApplicationContext(), "登入失败，请重新登录！", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        } catch (java.io.IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });


    }

}
