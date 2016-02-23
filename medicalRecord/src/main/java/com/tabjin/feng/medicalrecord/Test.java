package com.tabjin.feng.medicalrecord;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

/**
 * Created by Administrator on 2016/2/9.
 */
public class Test extends AppCompatActivity {

    ToggleButton toggleButton ;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_info);
        toggleButton = (ToggleButton) findViewById(R.id.bt_more_info);
        linearLayout = (LinearLayout) findViewById(R.id.more_info);

//                为是否显示更多信息这只开关
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    toggleButton.setChecked(true);
                    linearLayout.setVisibility(View.VISIBLE);
                }else{
                    toggleButton.setChecked(false);
                    linearLayout.setVisibility(View.GONE);
                }
            }
        });


    }
}
