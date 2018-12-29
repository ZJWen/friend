package com.example.zjw.friendcircle;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.network.zhouwei.http_network.Comment;
import com.network.zhouwei.http_network.Server;

import java.util.List;


public class ListViewAdapter extends ArrayAdapter {

    public ListViewAdapter(){
        super(null,0);
    }

    private int resourceId;

    public ListViewAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Comment comment = (Comment) getItem(position);
        ListLayout listLayout = new ListLayout();
        Server server = new Server();
        server.setServer("119.29.60.170");
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            listLayout.head = (ImageView) view.findViewById(R.id.imageView7);
            listLayout.NC = (TextView) view.findViewById(R.id.nicheng);
            listLayout.content = (TextView) view.findViewById(R.id.show_text);
            listLayout.ImageView1 = (ImageView) view.findViewById(R.id.imageView9);
            listLayout.ImageView2 = (ImageView) view.findViewById(R.id.imageView10);
            listLayout.ImageView3 = (ImageView) view.findViewById(R.id.imageView11);
            view.setTag(listLayout);
        } else {
            view = convertView;
            listLayout = (ListLayout) view.getTag();
        }
        listLayout.head.setImageBitmap(server.imageFileDownload(comment.getUsername()));
        listLayout.NC.setText(comment.getUsername());
        listLayout.content.setText(comment.getContext());
        return view;

    }

    class ListLayout {
        private ImageView head;
        private TextView NC;
        private TextView content;
        private ImageView ImageView1;
        private ImageView ImageView2;
        private ImageView ImageView3;
    }


}
