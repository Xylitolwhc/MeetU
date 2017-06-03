package com.hackday.play;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hackday.play.Utils.LocationInfor;

import java.util.Date;

/**
 * Created by wuhanson on 2017/6/3.
 */

public class EditUmbrellaActivity extends Activity {
    private LocationInfor locationInfor;
    private EditText editText;
    private TextView textView,time;
    private Button button;
    private RelativeLayout relativeLayout;
    private ImageView imageView;
    private Long selectedTime;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        editText = (EditText) findViewById(R.id.activity_add_EditText);
        textView = (TextView) findViewById(R.id.activity_add_TextView);
        button = (Button) findViewById(R.id.activity_add_Button);
        relativeLayout = (RelativeLayout) findViewById(R.id.activity_add_RelativeLayout);
        imageView = (ImageView) findViewById(R.id.activity_add_sex);
        time=(TextView) findViewById(R.id.activity_add_time);

        locationInfor = MyApplication.getLocationInfor();
        if (getIntent().getIntExtra("Mode", 0) == 0) {//编辑、发布模式
            textView.setVisibility(View.INVISIBLE);
            relativeLayout.setVisibility(View.INVISIBLE);
            button.setText("点击求帮助OvO");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text=editText.getText().toString();
                    LocationInfor newLocationInfor=new LocationInfor();
                    newLocationInfor.setDetail(text);
                    newLocationInfor.setName(MyApplication.getName());
                    newLocationInfor.setTime(selectedTime.toString());
                    newLocationInfor.setId(MyApplication.getId());

                }
            });
        } else {//浏览模式
            if (locationInfor == null) finish();
            textView.setVisibility(View.INVISIBLE);
            time.setClickable(false);
            imageView.setVisibility(View.INVISIBLE);
            if (locationInfor.getId() == MyApplication.getId()){
                button.setText("删除");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
            else {
                switch (locationInfor.getSex()) {
                    case 1:
                        button.setText("帮助他");
                        break;
                    case -1:
                        button.setText("帮助她");
                        break;
                    case 0:
                        button.setText("帮助他/她");
                        break;
                }
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }
    }
}
