package com.example.zjw.friendcircle;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.network.zhouwei.http_network.Server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class register extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button button2 = (Button) findViewById(R.id.register_button1);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            EditText editText = (EditText) findViewById(R.id.editText);
                            String name = editText.getText().toString();
                            String passwd = ((EditText) findViewById(R.id.passwd1)).getText().toString();
                            String phone = ((EditText) findViewById(R.id.phone)).getText().toString();
                            String email = ((EditText) findViewById(R.id.email)).getText().toString();
                            URL url = new URL("http://119.29.60.170/index.aspx?type=regist&username=" + name + "&password=" + passwd + "&email=" + email + "&phone=" + phone);
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
                            Message message = new Message();
                            message.obj = stringBuffer.toString();
                            Looper.prepare();
                            if (stringBuffer.toString().equals("regist success")) {
                                Toast.makeText(getApplicationContext(), "×¢²á³É¹¦", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(register.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "×¢²áÊ§°Ü£¬ÇëÖØÐÂ×¢²á£¡", Toast.LENGTH_SHORT).show();
                            }
                            Looper.loop();

                        } catch (java.io.IOException e) {
                            e.printStackTrace();
                        }

                    }
                }.start();

            }
        });
    }


}
