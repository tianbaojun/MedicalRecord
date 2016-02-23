package com.tabjin.feng.medicalrecord;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.tabjin.feng.medicalrecord.bean.Patient;
import com.tabjin.feng.medicalrecord.bean.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/9.
 */
public class NewRecordAcitivity extends AppCompatActivity implements View.OnClickListener {


    DbUtils db ;
    private LinearLayout linearLayout;
    private ToggleButton toggleButton;
    private TextView name_new_record,gender_new_record,time_new_record,label_new_record
    ,date_new_record,num1_new_record,num2_new_record,id_new_record,contect_new_record
    ,tel_new_record,email_new_record,job_new_record,introduce_new_record,address_new_ord
    ,moreinfomation_new_record,label_tv,diagnosis_tv,state_tv;
    private Record record;
    private Patient patient;
    static final int REQUESTCODE = 10;
    private final String SEPREATOR = ",";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record);
        init();
        setClickListener();


        //为更多信息展示设置监听

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        try {
            db.save(record);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
private String listToString(List<String> list) {
    StringBuilder sb = new StringBuilder();
    for (String str : list) {
        sb.append(str+SEPREATOR);
    }
    return sb.substring(0,sb.length()-1);
}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE && data != null) {
            Bundle bundle = data.getExtras();
            String ldsString;
            switch (resultCode) {
//                -----------------------    个人信息    --------------------------
                case 20:
                    patient = (Patient) bundle.get("patient");
                    try {
                        db.save(patient);
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                    setText();
                    break;
//                ------------------------    标签     --------------------------
                case NewLDSActivity.LABEL:
                    ArrayList<String> label = bundle.getStringArrayList("label");
                    ldsString = listToString(label);
                    record.setLabels(ldsString);
                    label_tv.setText(ldsString);
                    break;
                case NewLDSActivity.DIAGNOSIS:
                    ArrayList<String> diagnosis = bundle.getStringArrayList("diagnosis");
                    ldsString = listToString(diagnosis);
                    record.setDiagnosis(ldsString);
                    diagnosis_tv.setText(ldsString);
                    break;
                case NewLDSActivity.STATE:
                    ArrayList<String> states = bundle.getStringArrayList("states");
                    ldsString = listToString(states);
                    record.setStates(ldsString);
                    state_tv.setText(ldsString);
                    break;
            }
        }
    }

    /**
     * 为病人信息设置展示信息
     */
    private void setText(){
        name_new_record.setText(patient.getName());
        gender_new_record.setText(patient.isGender()?"男":"女");
        date_new_record.setText(patient.getDate());
        id_new_record.setText(patient.getId());
        contect_new_record.setText(patient.getContacter());
        tel_new_record.setText(patient.getTel());
        email_new_record.setText(patient.getEmail());
        job_new_record.setText(patient.getJob());
        introduce_new_record.setText(patient.getInterducer());
        address_new_ord.setText(patient.getAddress());
        moreinfomation_new_record.setText(patient.getNote());

    }

    private void init(){

        linearLayout = (LinearLayout) findViewById(R.id.more_info_new_record);
        toggleButton = (ToggleButton) findViewById(R.id.more_info);
        record = new Record();

        name_new_record = (TextView) findViewById(R.id.name_new_record);
        gender_new_record = (TextView) findViewById(R.id.gender_new_record);
        time_new_record = (TextView) findViewById(R.id.time_new_record);

//        time_new_record.setText(patient.set);
        label_new_record = (TextView) findViewById(R.id.label_new_record);
        num1_new_record = (TextView) findViewById(R.id.num1_new_record);
        num2_new_record = (TextView) findViewById(R.id.num2_new_record);
        date_new_record = (TextView) findViewById(R.id.date_new_record);
        id_new_record = (TextView) findViewById(R.id.id_new_record);
        contect_new_record = (TextView) findViewById(R.id.contect_new_record);
        tel_new_record = (TextView) findViewById(R.id.tel_new_record);
        email_new_record = (TextView) findViewById(R.id.email_new_record);
        job_new_record = (TextView) findViewById(R.id.job_new_record);
        introduce_new_record = (TextView) findViewById(R.id.introduce_new_record);
        address_new_ord = (TextView) findViewById(R.id.address_new_ord);
        moreinfomation_new_record = (TextView) findViewById(R.id.moreinfomation_new_record);

        label_tv = (TextView) findViewById(R.id.label_tv);
        diagnosis_tv = (TextView) findViewById(R.id.diagnosis_tv);
        state_tv = (TextView) findViewById(R.id.state_tv);

        db = DbUtils.create(this);

        test();

    }


    /**
     * 位组件设监听
     */
    private void setClickListener(){
        findViewById(R.id.label).setOnClickListener(this);
        findViewById(R.id.diagnosis_id).setOnClickListener(this);
        findViewById(R.id.state_id).setOnClickListener(this);
        findViewById(R.id.back_new_record).setOnClickListener(this);
        findViewById(R.id.info).setOnClickListener(this);
        label_tv.setOnClickListener(this);
        diagnosis_tv.setOnClickListener(this);
        state_tv.setOnClickListener(this);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    toggleButton.setChecked(true);
                    linearLayout.setVisibility(View.VISIBLE);
                } else {
                    toggleButton.setChecked(false);
                    linearLayout.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            ---------------    返回    -----------------
            case R.id.back_new_record:
                Intent intent = new Intent(this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
//            ---------------    标签    -----------------
            case R.id.label_tv:
            case R.id.label:
                Intent i_label = new Intent(this, NewLDSActivity.class);
                i_label.putExtra("from", NewLDSActivity.LABEL);
                startActivityForResult(i_label, REQUESTCODE);
                break;
//            ---------------    诊断    -----------------
            case R.id.diagnosis_tv:
            case R.id.diagnosis_id:
                Intent i_diagnosis = new Intent(this, NewLDSActivity.class);
                i_diagnosis.putExtra("from", NewLDSActivity.DIAGNOSIS);
                startActivityForResult(i_diagnosis, REQUESTCODE);
                break;
 //            ---------------    病情    -----------------
            case R.id.state_tv:
            case R.id.state_id:
                Intent i_state = new Intent(this, NewLDSActivity.class);
                i_state.putExtra("from",NewLDSActivity.STATE);
                startActivityForResult(i_state, REQUESTCODE);
                break;
//            ---------------    病人基本信息    -----------------
            case R.id.info:
                startActivityForResult(new Intent(this, NewInfoActivity.class), REQUESTCODE);
        }
    }

    /**
     *  临时方法
     *
     */

    private  void test(){
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Parent Parent = db.findFirst(Selector.from(Parent.class).where("name","=","test"));

                try {
                    Patient patient = db.findFirst(Selector.from(Patient.class).where("name","=","112233"));
                    Toast.makeText(NewRecordAcitivity.this, ""+patient, Toast.LENGTH_SHORT).show();
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
