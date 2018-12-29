package com.example.zjw.friendcircle;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.network.zhouwei.http_network.Comment;
import com.network.zhouwei.http_network.Server;

import java.util.List;


public class fcinterface extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcinterface);
        ImageView image = (ImageView) findViewById(R.id.imageView3);
        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
        ImageButton imageButton1 = (ImageButton) findViewById(R.id.button3);

        imageButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(fcinterface.this, publish.class);
                Bundle bundle = getIntent().getExtras();

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        imageButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(fcinterface.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Server server = new Server();
        server.setServer("119.29.60.170");
        List<Comment> comments = server.getComments(0, 200);
        ListViewAdapter ListViewAdapter = new ListViewAdapter(this, R.layout.listview, comments);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(ListViewAdapter);
    }
}
