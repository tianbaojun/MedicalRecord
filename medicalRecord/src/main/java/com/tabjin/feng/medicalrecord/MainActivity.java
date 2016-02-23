package com.tabjin.feng.medicalrecord;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Spinner;

/**
 * Created by Administrator on 2016/2/8.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    static final String[] labels={
            "label1","label2","label3","label4","label5","label6"
    };
    private Spinner spinner;
    private SearchView searchView;
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
        findViewById(R.id.bt_new_record).setOnClickListener(this);

    }

    /**
     * Activity初始化
     */
    private void init() {
        searchView = (SearchView) findViewById(R.id.search_record_list);
        spinner = (Spinner) findViewById(R.id.label_record_list);

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
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, labels));

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
