package com.tabjin.feng.medicalrecord;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/2/22.
 */
public class NewLDSActivity extends AppCompatActivity implements View.OnClickListener {

    private Map<String, Boolean> map = new HashMap<String, Boolean>();
    static ArrayList<String> labels=new ArrayList<>();
    static ArrayList<String> diagnosis=new ArrayList<>();
    static ArrayList<String> states=new ArrayList<>();
    private Button back_bt,add_bt,save_bt;
    private TextView title_tv;
    private ListView listView;
    private EditText edit_tv;
    private int from;

    private StringBuilder sb = new StringBuilder();

    static final int LABEL = 111;
    static final int DIAGNOSIS = 222;
    static final int STATE = 333;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_lds);
        init();
        setListener();
        fromWhich();

    }

    /**
     * 分析从哪里跳转过来，三种情况，新建标签，新建诊断信息，新建病情信息
     */
    private void fromWhich() {

        Intent intent = getIntent();
        from = intent.getIntExtra("from", -1);
        switch (from) {
            case LABEL:
                doLabel();
                break;
            case DIAGNOSIS:
                doDiagnosis();
                break;
            case STATE:
                doState();
                break;
        }
    }

    /**
     * 诊断界面业务
     */
    private void doDiagnosis() {
        title_tv.setText(getResources().getString(R.string.diagnosis));
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, diagnosis);
        //设置多选框
        listView.setAdapter(adapter);

        //listview 的点击事件监听
        add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemSingle(diagnosis,edit_tv.getText().toString());
                edit_tv.setText("");
                //更新界面
                adapter.notifyDataSetChanged();
            }
        });

    }

    /**
     * 病情界面业务
     */
    private void doState() {
        title_tv.setText(getResources().getString(R.string.states));
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, states);
        //设置多选框
        listView.setAdapter(adapter);

        //listview 的点击事件监听
        add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemSingle(states,edit_tv.getText().toString());
                edit_tv.setText("");
                //更新界面
                adapter.notifyDataSetChanged();
            }
        });

    }

    /**
     * 标签界面业务
     */
    private void doLabel() {

        title_tv.setText(getResources().getString(R.string.label));
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, labels);
        //设置多选框
        listView.setAdapter(adapter);

        //listview 的点击事件监听
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CheckedTextView checkedTextView = (CheckedTextView)view;
                //设置勾选框是否勾选
                checkedTextView.setChecked(!checkedTextView.isChecked());
                Toast.makeText(NewLDSActivity.this, "您点击了" + checkedTextView.isChecked(), Toast.LENGTH_LONG).show();
                //将列表的内容设置成map的键，是否选中设置为置
                map.put((String) checkedTextView.getText(), checkedTextView.isChecked());
            }
        });

        add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemSingle(labels,edit_tv.getText().toString());
                edit_tv.setText("");
                //更新界面
                adapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 初始化各组件
     */
    private void init() {
        back_bt = (Button) findViewById(R.id.back_bt);
        add_bt = (Button) findViewById(R.id.add_bt);
        save_bt = (Button) findViewById(R.id.save_bt);
        title_tv = (TextView) findViewById(R.id.title_tv);
        edit_tv = (EditText) findViewById(R.id.edit_tv);
        listView = (ListView) findViewById(R.id.listview);
        for (String str : MainActivity.labels) {
            addItemSingle(labels,str);
        }

    }

    /**
     * 为各组件设置监听
     */
    private void setListener() {
        back_bt.setOnClickListener(this);
        save_bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        switch (v.getId()) {
//            ---------------    返回    -----------------------
            case R.id.back_bt:
                break;
//            ----------------    保存    ----------------------
            case R.id.save_bt:
                switch (from) {
                    case LABEL:
                        saveLabels();
                        break;
                    case DIAGNOSIS:
                        saveDiagnosis();
                        break;
                    case STATE:
                        saveStates();
                        break;
                }

                break;
        }
        finish();
    }

    /**
     * 保存标签信息
      */
    private void saveLabels() {
        ArrayList<String> list = new ArrayList<>();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        Set<String> set = map.keySet();
        for(String str:set){
            if(map.get(str)){
                list.add(str);
            }
        }
        bundle.putStringArrayList("label", list);
        intent.putExtras(bundle);
        setResult(LABEL, intent);
    }
    /**
     * 保存诊断信息
     */
    private void saveDiagnosis() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("diagnosis", diagnosis);
        intent.putExtras(bundle);
        setResult(DIAGNOSIS, intent);
    }
    /**
     * 保存病情信息
     */
    private void saveStates() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("states", states);
        intent.putExtras(bundle);
        setResult(STATE, intent);
    }

    private <E> Boolean addItemSingle(List<E> list,E item){
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(item)) {
                return false;
            }
        }
        list.add(item);
        return true;
    }

}

