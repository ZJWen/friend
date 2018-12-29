package com.handsome.health;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.handsome.health.base.BaseActivity;
import com.handsome.health.net.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by zjw on 2018/12/28.
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {
    private Button searchBtn,cancelBtn,startBtn,endBtn;
    private ListView listView;
    private Calendar calendar;
    private TextView tv;
    private String sportName="";
    private List<JSONObject>sportDatas=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        calendar=Calendar.getInstance();
        findByid();
        searchBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        startBtn.setOnClickListener(this);
        endBtn.setOnClickListener(this);
        sportName=getIntent().getStringExtra("name");
        tv.setText(sportName);
    }
    private void findByid(){
        searchBtn=(Button)findViewById(R.id.search_btn);
        cancelBtn=(Button)findViewById(R.id.search_cancel_btn);
        startBtn=(Button)findViewById(R.id.search_morning_start_btn);
        endBtn=(Button)findViewById(R.id.search_morning_end_btn);
        listView=(ListView)findViewById(R.id.listview);
        tv=(TextView)findViewById(R.id.search_title_tv);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()){
            case R.id.search_btn:
                getData();
                break;
            case R.id.search_cancel_btn:
                finish();
                break;
            default:
                DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        ((Button)v).setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
        }
    }

    private void getData() {
        sportDatas.clear();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                HashMap<String,Object>map=new HashMap<>();
                map.put("account",AppApplication.account);
                map.put("sport_time",sportName);
                map.put("start_time","");
                map.put("end_time", "");

               final String result= HttpUtils.doPost("get_records.aspx",map);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        toast(result);

                        try {
                            JSONObject jsonObject=new JSONObject(result);
                            if (jsonObject.getInt("code")==0){
                                JSONArray jsonArray=jsonObject.getJSONArray("data");
                                for (int i=0;i<jsonArray.length();i++){
                                    sportDatas.add(jsonArray.getJSONObject(i));
                                }
                            }
                            else{
                                toast(jsonObject.getString("msg"));
                            }
                            listView.setAdapter(new MyAdapter());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        };
        new Thread(runnable).start();
    }
    private class  MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return sportDatas.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView=getLayoutInflater().inflate(R.layout.listview_item,parent,false);
            TextView startTv=(TextView)convertView.findViewById(R.id.item_start);
            TextView endTv=(TextView)convertView.findViewById(R.id.item_end);
            TextView mileTv=(TextView)convertView.findViewById(R.id.item_mileage);


            try {
                startTv.setText(sportDatas.get(position).getString("start_time"));
                endTv.setText(sportDatas.get(position).getString("end_time"));
                mileTv.setText(sportDatas.get(position).getString("distance"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return convertView;
        }
    }
}
