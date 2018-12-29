package com.handsome.health;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.handsome.health.base.BaseActivity;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button addBtn,morningBtn,morningExceciseBtn,walkBtn,bikeBtn,ballBtn,nightRunBtn,swimBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        addBtn.setOnClickListener(this);
        morningBtn.setOnClickListener(this);
        morningExceciseBtn.setOnClickListener(this);
        walkBtn.setOnClickListener(this);
        bikeBtn.setOnClickListener(this);
        ballBtn.setOnClickListener(this);
        nightRunBtn.setOnClickListener(this);
        swimBtn.setOnClickListener(this);
    }
    private void initView(){
        morningBtn=(Button)findViewById(R.id.main_morning_btn);
        morningExceciseBtn=(Button)findViewById(R.id.main_morning_exercise_btn);
        walkBtn=(Button)findViewById(R.id.main_walk_btn);
        bikeBtn=(Button)findViewById(R.id.main_bike_btn);
        ballBtn=(Button)findViewById(R.id.main_ball_btn);
        nightRunBtn=(Button)findViewById(R.id.main_night_run_btn);
        swimBtn=(Button)findViewById(R.id.main_swim_btn);
        addBtn=(Button)findViewById(R.id.main_add_btn);
    }


    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this,SearchActivity.class);
        switch (v.getId()){
            case R.id.main_add_btn:
                startIntent(HealthEnter.class);
                break;
            case R.id.main_morning_btn:
                intent.putExtra("name","����");
                break;
            case R.id.main_morning_exercise_btn:
                intent.putExtra("name","��ٳ���");
                break;
            case R.id.main_walk_btn:
                intent.putExtra("name","�ռ�����");
                break;
            case R.id.main_bike_btn:
                intent.putExtra("name","����");
                break;
            case R.id.main_swim_btn:
                intent.putExtra("name","��Ӿ");
                break;
            case R.id.main_ball_btn:
                intent.putExtra("name","�����˶�");
                break;
            case R.id.main_night_run_btn:
                intent.putExtra("name","����ܲ�");
                break;
        }
        if (v.getId()!=R.id.main_add_btn){
            startActivity(intent);
        }
    }
}
