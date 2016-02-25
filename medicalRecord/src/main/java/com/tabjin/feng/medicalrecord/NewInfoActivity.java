package com.tabjin.feng.medicalrecord;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.tabjin.feng.medicalrecord.bean.Patient;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by Administrator on 2016/2/13.
 */
public class NewInfoActivity extends AppCompatActivity implements View.OnClickListener{
    private Patient patient;
    private EditText gender_info,birth_info,age_info,date_info,
            name_info,tel_info,id_info,keshi_info,
            num1_info,num2_info,phone_info,email_info,address_info,
            job_info,contact_info,introduce_info, note_info;
    private TextView back_new_record_info;
    private Spinner typeSpinner1,typeSpinner2;
    private ToggleButton bt_more_info;
    private LinearLayout more_info;
    private Button save_info;
    private Calendar currentCalendar;


    /**
     * 为病人信息设置展示信息
     */
    private void setText(Patient patient){
        name_info.setText(patient.getName());
        gender_info.setText(patient.isGender()?"男":"女");
        birth_info.setText(patient.getDate());
        age_info.setText(String.valueOf(patient.getAge()));
        date_info.setText(patient.getJiuZhen_date());
        id_info.setText(patient.getId1());
        contact_info.setText(patient.getContacter());
        tel_info.setText(patient.getMobil());
        email_info.setText(patient.getEmail());
        job_info.setText(patient.getJob());
        introduce_info.setText(patient.getInterducer());
        address_info.setText(patient.getAddress());
        note_info.setText(patient.getNote());
        phone_info.setText(patient.getTel());

    }


    //    编号类型，可使用枚举类来实现
    private String[] type = new String[]{"类型1","类型2","类型3","类型4","类型5","类型6","类型7","类型8"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_info);
        init();
        setClickListener();
    }

    private void init(){
        patient = new Patient();
        gender_info = (EditText)findViewById(R.id.gender_info);
        birth_info = (EditText) findViewById(R.id.birth_info);
        age_info = (EditText) findViewById(R.id.age_info);
        date_info = (EditText) findViewById(R.id.date_info);
        name_info = (EditText) findViewById(R.id.name_info);
        tel_info = (EditText) findViewById(R.id.tel_info);
        id_info = (EditText) findViewById(R.id.id_info);
        keshi_info = (EditText) findViewById(R.id.keshi_info);
        num1_info = (EditText) findViewById(R.id.num1_info);
        num2_info = (EditText) findViewById(R.id.num2_info);
        phone_info = (EditText) findViewById(R.id.phone_info);
        email_info = (EditText) findViewById(R.id.email_info);
        address_info = (EditText) findViewById(R.id.address_info);
        contact_info = (EditText) findViewById(R.id.contact_info);
        introduce_info = (EditText) findViewById(R.id.introduce_info);
        note_info = (EditText) findViewById(R.id.note_info);
        job_info = (EditText) findViewById(R.id.job_info);



        back_new_record_info = (TextView) findViewById(R.id.back_new_record_info);


        typeSpinner1 = (Spinner) findViewById(R.id.num_type1_info);
        typeSpinner2 = (Spinner) findViewById(R.id.num_type2_info);
        bt_more_info = (ToggleButton) findViewById(R.id.bt_more_info);
        more_info = (LinearLayout) findViewById(R.id.more_info);
        save_info = (Button) findViewById(R.id.save_info);

        currentCalendar = Calendar.getInstance();

        Intent intent = getIntent();
        String from = intent.getStringExtra("from");
        if((MainActivity.ID_MAIN).equals(from)){
            Bundle bundle = intent.getExtras();
            patient = (Patient) bundle.getSerializable("patient");
            if (null!=patient) {
                Toast.makeText(this,"patient不为null",Toast.LENGTH_SHORT).show();

                setText(patient);
            }
        }


//        db = DbUtils.create(this);
//        希望通过这样的方法实现builder的复用，这样可以节省内存，
//        但是，不知道如何实现builder的改变view的方法，失败
//        builder = new AlertDialog.Builder(NewInfoActivity.this);
//        parentBuilder = (LinearLayout) LayoutInflater.from(builder.getContext()).inflate(R.layout.builder,null);

       /* datePicker =(DatePicker) getLayoutInflater().inflate(R.layout.datepicker, null);
        datePicker.init(Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        NewInfoActivity.this.year = year;
                        NewInfoActivity.this.month = monthOfYear;
                        NewInfoActivity.this.day = dayOfMonth;
                    }
                });*/
    }

    private void setClickListener(){
        gender_info.setOnClickListener(this);
        birth_info.setOnClickListener(this);
        date_info.setOnClickListener(this);
        save_info.setOnClickListener(this);

        //为展开开关 设置监听
        bt_more_info.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    more_info.setVisibility(View.VISIBLE);
                } else {
                    more_info.setVisibility(View.GONE);
                }
            }
        });

        back_new_record_info.setOnClickListener(this);
    }

    private void saveInfo(){
        patient.setAddress(address_info.getText().toString());
        //判断年龄是否设置，没有默认为零
        patient.setAge("".equals(age_info.getText().toString())?
                0:Integer.valueOf(age_info.getText().toString()));
        patient.setContacter(contact_info.getText().toString());
        patient.setDate(birth_info.getText().toString());
        patient.setEmail(email_info.getText().toString());
        patient.setId1(id_info.getText().toString());
        patient.setInterducer(introduce_info.getText().toString());
        patient.setJiuZhen_date(date_info.getText().toString());
        patient.setJob(job_info.getText().toString());
        patient.setKeshi(keshi_info.getText().toString());
        patient.setMobil(tel_info.getText().toString());
        patient.setName(name_info.getText().toString());
        patient.setTel(phone_info.getText().toString());
        patient.setNote(note_info.getText().toString());
        /*try {
            db.save(patient);
        } catch (DbException e) {
            e.printStackTrace();
        }*/

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            -----------------------    性别    -----------------------
            case R.id.gender_info:
                AlertDialog.Builder builder = new AlertDialog.Builder(NewInfoActivity.this);
                builder.setTitle(getResources().getString(R.string.gender_choose))
                        .setSingleChoiceItems(new String[]{"男", "女"}, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                patient.setGender(which == 0);
                            }
                        });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gender_info.setText(patient.isGender() ? "男" : "女");
                    }
                });
                builder.create().show();
                break;
//       -------------------     出生日期    ------------------
            case R.id.birth_info:

                showDatePicker(birth_info,age_info);
                break;
//      --------------------      就诊日期     -----------------------
            case R.id.date_info:

                showDatePicker(date_info,null);
                break;

//       ------------------------     标号类型1     -------------------
            case R.id.num_type1_info:
                typeSpinner1.setAdapter(new ArrayAdapter<String>(NewInfoActivity.this,
                        android.R.layout.simple_list_item_1,
                        type));
                break;
//      --------------------------      编号类型2      --------------------
            case R.id.num_type2_info:
                typeSpinner2.setAdapter(new ArrayAdapter<String>(NewInfoActivity.this,
                        android.R.layout.simple_list_item_1,
                        type));
                break;
//       --------------------------     保存信息    -------------------------
            case R.id.save_info:
                saveInfo();
                Bundle bundle = new Bundle();
                bundle.putSerializable("patient",patient);
                setResult(20, new Intent(NewInfoActivity.this, NewRecordAcitivity.class).putExtras(bundle));
                finish();
                break;
//            ---------------    取消编辑    -----------------------
            case R.id.back_new_record_info:
                setResult(40, null);
                finish();
                break;
        }


    }

    /**
     * --------------   显示日期选择对话框   ----------------
     *
     */
    private void showDatePicker(final EditText date_info,final EditText age_info) {
        final Calendar DATE = Calendar.getInstance();
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        //初始化datePicker
        DatePicker datePicker = new DatePicker(NewInfoActivity.this);
        datePicker.setCalendarViewShown(false);
        datePicker.init(currentCalendar.get(Calendar.YEAR) - 3,
                currentCalendar.get(Calendar.MONTH),
                currentCalendar.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        DATE.set(year, monthOfYear, dayOfMonth);
                    }
                });

        //设置datePicker的最大日期
        datePicker.setMaxDate(currentCalendar.getTimeInMillis());


        //创建对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(NewInfoActivity.this);
        builder.setTitle(getResources().getString(R.string.date_choose))
                .setView(datePicker)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        date_info.setText(simpleDateFormat.format(new Date(DATE.getTimeInMillis())));
                        if (age_info != null) {
                            age_info.setText(String.valueOf(currentCalendar.get(Calendar.YEAR) - DATE.get(Calendar.YEAR)));
                        }
                    }
                })
                .setNegativeButton("取消", null);
        builder.create().show();
    }




}
