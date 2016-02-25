package com.tabjin.feng.medicalrecord;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.tabjin.feng.medicalrecord.bean.Patient;
import com.tabjin.feng.medicalrecord.bean.Record;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/8.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    static final String[] labels={
            "label1","label2","label3","label4","label5","label6"
    };
    static final String ID_MAIN = "main";
    private Spinner spinner;
    private SearchView searchView;
    private ListView record_list;
//    private String sql_name = "select * from com_tabjin_feng_medicalrecord_bean_Record";
    private DbUtils db ;
    private List<Map<String ,String>> listContent;   //listView的内容源
    private List<Record> records;     //所有的病历


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setListener();
        set();


     /* actionBar = getActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);*/
    }

    /**
     * 监听
     */
    private void setListener(){
//        新建病历监听
        findViewById(R.id.bt_new_record).setOnClickListener(this);
//        listView的组件设置监听
        record_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Record record = records.get(position);
                Patient patient = null;
                try {
                    DbUtils db = DbUtils.create(MainActivity.this,"MyRecord");
                    patient = db.findFirst(Selector.from(Patient.class).where("id1", "=", record.getPatientId()));
                } catch (DbException e) {
                    e.printStackTrace();
                } finally {
                    db.close();
                }
                Bundle bundle = new Bundle();
                Intent intent = new Intent(MainActivity.this,NewRecordAcitivity.class);
                bundle.putSerializable("record",record);
                bundle.putSerializable("patient",patient);
                intent.putExtras(bundle);
                intent.putExtra("from",ID_MAIN);
                startActivity(intent);
            }
        });
    }

    /**
     * Activity初始化
     */
    private void init() {

        searchView = (SearchView) findViewById(R.id.search_record_list);
        spinner = (Spinner) findViewById(R.id.label_record_list);
        record_list = (ListView) findViewById(R.id.record_list);
        db = DbUtils.create(this,"MyRecord");
        listContent = new ArrayList<>();
        try {
            records = db.findAll(Selector.from(Record.class));
            if(records!=null) {
                ((TextView) findViewById(R.id.label_num)).setText(String.valueOf(records.size()));
                for (Record record : records) {
                    Patient patients = db.findFirst(Selector.from(Patient.class).where("id1", "=", record.getPatientId()));

                    if (null != patients) {
                        Map<String, String> mapRecord = new HashMap<>();
                        mapRecord.put("name", patients.getName());
                        mapRecord.put("birth", patients.getDate());
                        mapRecord.put("age", String.valueOf(patients.getAge()));
                        mapRecord.put("id", patients.getId1());
                        mapRecord.put("tel", patients.getMobil());
                        MainActivity.this.listContent.add(mapRecord);
                    }
                }
            }else{
                Toast.makeText(this,"现在还没有任何数据",Toast.LENGTH_SHORT).show();
            }

        } catch (DbException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    /**
     * 对组件进行相关设置
     */
    private void set() {
        final RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) searchView.getLayoutParams();
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT;
                searchView.setLayoutParams(layoutParams);
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                searchView.setBackgroundColor(getResources().getColor(android.R.color.background_light));
                layoutParams.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
                searchView.setLayoutParams(layoutParams);
                return false;
            }
        });
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, labels));
//      测试  listview设置内容，
        record_list.setAdapter(new SimpleAdapter(this, listContent, R.layout.lsit_item,
                new String[]{"name","birth","age","id","tel"}, new int[]{R.id.name,R.id.birth,R.id.age,R.id.id,R.id.tel}));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_new_record:
                startActivity(new Intent(this,NewRecordAcitivity.class));
                break;
        }
    }



     /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menu_new_record, menu);
        viewFlipper = (AdapterViewFlipper) menu.findItem(R.id.label_flipper);
        viewFlipper.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,labels));
        return super.onCreateOptionsMenu(menu);
    }*/

}
