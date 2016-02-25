package com.tabjin.feng.medicalrecord;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.tabjin.feng.medicalrecord.bean.Patient;
import com.tabjin.feng.medicalrecord.bean.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/9.
 */
public class NewRecordAcitivity extends AppCompatActivity implements View.OnClickListener {

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
    private String from;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record);
        init();
        setClickListener();


        //为更多信息展示设置监听

    }

    /**
     *list<String></String>转成String
     * @param list list集合
     * @return String
     */
    private String listToString(List<String> list) {
        if(0!=list.size()){
            StringBuilder sb = new StringBuilder();
            for (String str : list) {
                sb.append(str+SEPREATOR);
            }
            return sb.substring(0,sb.length()-1);
        }
        return "";
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
                    if (null!=patient) {
                        DbUtils db = DbUtils.create(NewRecordAcitivity.this,"MyRecord");
                        try {
                            db.saveOrUpdate(patient);
                            record.setPatientId(patient.getId1());
                        } catch (DbException e) {
                            e.printStackTrace();
                        } finally {
                            db.close();
                        }
                        setText(patient);
                    }
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
    private void setText(Patient patient){
        name_new_record.setText(patient.getName());
        gender_new_record.setText(patient.isGender()?"男":"女");
        date_new_record.setText(patient.getDate());
        id_new_record.setText(patient.getId1());
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

//        获取intent,判断是否从MainActivity传过来，如果是，就初始化信息,
        Intent intent = getIntent();
        from = intent.getStringExtra("from");
        if((MainActivity.ID_MAIN).equals(from)){
            Bundle bundle = intent.getExtras();
            record = (Record) bundle.getSerializable("record");
            patient = (Patient) bundle.getSerializable("patient");
            if (null!=patient) {
                setText(patient);
            }
            if (null!=record) {
                label_tv.setText(record.getLabels());
                diagnosis_tv.setText(record.getDiagnosis());
                state_tv.setText(record.getStates());
            }
        }

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
//        保存键设置监听
        findViewById(R.id.btn).setOnClickListener(this);
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
                Intent intentInfo = new Intent(this, NewInfoActivity.class);
                if (MainActivity.ID_MAIN.equals(from)) {
                    intentInfo.putExtra("from", MainActivity.ID_MAIN);
                    Bundle bundle = new Bundle();
                    bundle .putSerializable("patient", patient);
                    intentInfo.putExtras(bundle);
                }
                startActivityForResult(intentInfo, REQUESTCODE);
                break;
//                -----------------------    保存    --------------------
            case R.id.btn:

                DbUtils db = DbUtils.create(NewRecordAcitivity.this,"MyRecord");
                try {
                    db.saveOrUpdate(record);
                } catch (DbException e) {
                    e.printStackTrace();
                } finally {
                    db.close();
                }
                startActivity(new Intent(NewRecordAcitivity.this, MainActivity.class));
                break;

        }
    }

}
