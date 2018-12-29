package com.handsome.health;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.handsome.health.base.BaseActivity;
import com.handsome.health.net.HttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by zjw on 2018/12/24.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private Button backBtn,regBtn,birthBtn;
    private EditText accountEdit,nameEdit,pwdEdit,repwdEdit;
    private RadioGroup rg;
    private RadioButton rb_man,rb_women;
    private Calendar calendar;
    private  String sex="��";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

        backBtn.setOnClickListener(this);
        regBtn.setOnClickListener(this);
        birthBtn.setOnClickListener(this);

        calendar=Calendar.getInstance();
        birthBtn.setText(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH));
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.radio_men){
                    sex="��";
                }
                else if(checkedId==R.id.radio_women){
                    sex="Ů";
                }
                else{
                    sex="����";
                }
            }
        });
    }
    public void initView(){
        backBtn=(Button)findViewById(R.id.reg_back_btn);
        regBtn=(Button)findViewById(R.id.reg_ok_btn);
        birthBtn=(Button)findViewById(R.id.reg_birthday);
        accountEdit=(EditText)findViewById(R.id.reg_account);
        nameEdit=(EditText)findViewById(R.id.reg_name);
        pwdEdit=(EditText)findViewById(R.id.reg_password);
        repwdEdit=(EditText)findViewById(R.id.reg_repassword);
        rg=(RadioGroup)findViewById(R.id.reg_sex);
        rb_man=(RadioButton)findViewById(R.id.radio_men);
        rb_women=(RadioButton)findViewById(R.id.radio_women);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reg_back_btn:
                finish();
                break;
            case R.id.reg_birthday:
                DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        birthBtn.setText(year+"-"+month+"-"+dayOfMonth);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            case R.id.reg_ok_btn:
                if (TextUtils.isEmpty(accountEdit.getText())){
                    toast("�˺Ų���Ϊ��");
                    return;
                }
                else if (TextUtils.isEmpty(nameEdit.getText())){
                    toast("��������Ϊ��");
                    return;
                }
                else if (TextUtils.isEmpty(pwdEdit.getText())){
                    toast("���벻��Ϊ��");
                    return;
                }
                else if (!pwdEdit.getText().toString().trim().equals(repwdEdit.getText().toString().trim())){
                    toast("�����������벻һ��");
                    return;
                }
                else{
                    //�ύ
                    reg();
                }
                break;
        }
    }//�ύע����Ϣ
    private void reg(){
//        �����߳�(�߳�ִ��˳�򲻿ɿ�)
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                HashMap<String,Object>map=new HashMap<>();
                map.put("account",accountEdit.getText().toString().trim());
                map.put("name",nameEdit.getText().toString().trim());
                map.put("sex",sex);
                map.put("birthday",birthBtn.getText().toString().trim());
                map.put("password", pwdEdit.getText().toString().trim());
                final  String result =  HttpUtils.doPost("regeist", map);
                //final  String result =  HttpUtils.doPost("regist.aspx", map);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            if (result!=null){
//                        ���������߳�

                                JSONObject jsonObject=new JSONObject(result);
                                if (jsonObject.getInt("code")==0){
//                            ע��ɹ�
                                    toast("ע��ɹ�");
                                    startIntent(LoginActivity.class);
                                    return;
                                }
                                else{
//                            ע��ʧ��
                                    toast(jsonObject.getString("msg"));
                                }

                            }
                        }  catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


            }
        };
        new Thread(runnable).start();
    }
}
