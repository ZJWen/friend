package com.handsome.health;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.handsome.health.base.BaseActivity;
import com.handsome.health.net.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by zjw on 2018/12/26.
 */
public class HealthEnter extends BaseActivity implements View.OnClickListener {
    private Button addBtn,cancelBtn,morningStartBtn,morningEndBtn;
    private EditText mileageEdit,bikeMileageEdit,nightRunEdit;
    private Button morningExerciseStartBtn,morningExerciseEndBtn;
    private Button walkStarBtn,walkEndBtn;
    private Button bikeStartBtn,bikeEndBtn;
    private Button swimStartBtn,swimEndBtn;
    private Button ballStartBtn,ballEndBtn;
    private Button nightStartBtn,nightEndBtn;
    private Calendar calendar;
    private TimePickerDialog timePickerDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_enter);
        calendar=Calendar.getInstance();
        initView();
        addBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        morningStartBtn.setOnClickListener(this);
        morningEndBtn.setOnClickListener(this);
        morningExerciseStartBtn.setOnClickListener(this);
        morningExerciseEndBtn.setOnClickListener(this);
        walkStarBtn.setOnClickListener(this);
        walkEndBtn.setOnClickListener(this);
        bikeStartBtn.setOnClickListener(this);
        bikeEndBtn.setOnClickListener(this);
        swimStartBtn.setOnClickListener(this);
        swimEndBtn.setOnClickListener(this);
        ballStartBtn.setOnClickListener(this);
        ballEndBtn.setOnClickListener(this);
        nightStartBtn.setOnClickListener(this);
        nightEndBtn.setOnClickListener(this);

    }
    private void initView(){
        addBtn =(Button)findViewById(R.id.add_submit_btn);
        cancelBtn = (Button)findViewById(R.id.add_cancel_btn);
        morningStartBtn =(Button)(Button) findViewById(R.id.add_morning_start_btn);
        morningEndBtn = (Button)findViewById(R.id.add_morning_end_btn);
        mileageEdit =(EditText) findViewById(R.id.add_mileage_edit);
        bikeMileageEdit =(EditText)  findViewById(R.id.add_bike_mileage_edit);
        nightRunEdit = (EditText)findViewById(R.id.add_night_run_mileage_edit);
        morningExerciseStartBtn = (Button)findViewById(R.id.add_morning_exercise_start_btn);
        morningExerciseEndBtn = (Button)findViewById(R.id.add_morning_exercise_end_btn);
        walkStarBtn = (Button)findViewById(R.id.add_walk_start_btn);
        walkEndBtn = (Button)findViewById(R.id.add_walk_end_btn);
        bikeStartBtn =(Button) findViewById(R.id.add_bike_start_btn);
        bikeEndBtn = (Button) findViewById(R.id.add_bike_end_btn);
        swimStartBtn =(Button) findViewById(R.id.add_swim_start_btn);
        swimEndBtn = (Button)findViewById(R.id.add_swim_end_btn);
        ballStartBtn = (Button)findViewById(R.id.add_ball_start_btn);
        ballEndBtn = (Button)findViewById(R.id.add_ball_end_btn);
        nightStartBtn = (Button) findViewById(R.id.add_night_run_start_btn);
        nightEndBtn = (Button)findViewById(R.id.add_night_run_end_btn);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()){
            case R.id.add_submit_btn:
//                �ύ��Ϣ
                if(morningStartBtn.getText().toString().trim().matches("[\\u4e00-\\u9fa5]*")){
                    toast("��ѡ��ʼʱ��");
                    return;
                }
                else if (morningEndBtn.getText().toString().trim().matches("[\\u4e00-\\u9fa5]*")){
                    toast("��ѡ�����ʱ��");
                    return;
                }
                else if (TextUtils.isEmpty(mileageEdit.getText())){
                    toast("�����������");
                    return;
                }
                else if (morningExerciseStartBtn.getText().toString().trim().matches("[\\u4e00-\\u9fa5]*")){
                    toast("��ѡ��ʼʱ��");
                    return;
                }
                else if (morningExerciseEndBtn.getText().toString().trim().matches("[\\u4e00-\\u9fa5]*")){
                    toast("��ѡ�����ʱ��");
                    return;
                }
                else if (walkStarBtn.getText().toString().trim().matches("[\\u4e00-\\u9fa5]*")){
                    toast("��ѡ��ʼʱ��");
                    return;
                }
                else if (walkEndBtn.getText().toString().trim().matches("[\\u4e00-\\u9fa5]*")){
                    toast("��ѡ�����ʱ��");
                    return;
                }
                    insertData();
                //startIntent(MainActivity.class);
                break;
            case R.id.add_cancel_btn:
//                ȡ����Ϣ
                finish();
                break;
           default:
                timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (minute<10) {
                            ((Button) v).setText(hourOfDay + ":0" + minute);
                        }
                        else{
                            ((Button)v).setText(hourOfDay + ":" + minute);
                        }
                    }
                },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true);
                timePickerDialog.show();
                break;
        }

    }

    private void insertData() {
        final Runnable runnable=new Runnable() {
            @Override
            public void run() {
                String today=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH)+" ";
               // Log.e("TAG","11111");
                HashMap<String,Object> map=new HashMap<>();
                HashMap<String,Object> map1=new HashMap<>();
                HashMap<String,Object> map2=new HashMap<>();
                map.put("sport_name", "����");
                map.put("start_time", today + morningStartBtn.getText().toString().trim());
                map.put("end_time", today + morningEndBtn.getText().toString().trim());
                map.put("account","zjw");
                map.put("distance", mileageEdit.getText().toString().trim());

                map1.put("sport_name", "��ٳ���");
                map1.put("start_time", today + morningExerciseStartBtn.getText().toString().trim());
                map1.put("end_time", today + morningExerciseEndBtn.getText().toString().trim());
                map1.put("account", "zjw");

                map2.put("sport_name", "�ռ�����");
                map2.put("start_time", today + walkStarBtn.getText().toString().trim());
                map2.put("end_time", today + walkEndBtn.getText().toString().trim());
                map2.put("account", "zjw");

              //  final String result =  HttpUtils.doPost("insertSport",map);
                final String result[]=new String[10];
                result[0] =  HttpUtils.doPost("add_record.aspx",map);
                result[1] =  HttpUtils.doPost("add_record.aspx",map1);
                result[2] =  HttpUtils.doPost("add_record.aspx",map2);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject(result[0]);
                            JSONObject jsonObject1 = new JSONObject(result[1]);
                            JSONObject jsonObject2 = new JSONObject(result[2]);
                            //Log.e("TAG",result);
                            if (jsonObject.getInt("code") == 0&&jsonObject1.getInt("code") == 0&&jsonObject2.getInt("code") == 0) {
                                toast("��ӳɹ�");
                                finish();
                            } else {
                                toast(jsonObject.getString("msg"));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });

            }
        };
//        �����߳�
        new Thread(runnable).start();
    }
}
