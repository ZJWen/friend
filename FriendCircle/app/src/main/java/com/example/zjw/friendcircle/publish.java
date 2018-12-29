package com.example.zjw.friendcircle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.network.zhouwei.http_network.Server;


public class publish extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        Button button2 = (Button) findViewById(R.id.cancel_button);
        Button button3 = (Button) findViewById(R.id.publish_button);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent1 = new Intent(publish.this, fcinterface.class);
                startActivity(intent1);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();
                String name = bundle.getString("username");
                EditText editText1 = (EditText) findViewById(R.id.editText3);
                String context = editText1.getText().toString();
                Server server = new Server();
                server.setServer("119.29.60.170");
                Bitmap[] bitmaps = {
                };
                server.commnetResourcesUpload(name, context, bitmaps);
                Intent intent = new Intent(publish.this, fcinterface.class);
                startActivity(intent);
            }
        });

    }
}
