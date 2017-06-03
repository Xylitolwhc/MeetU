package com.hackday.play;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hackday.play.Utils.LocationInfor;

import java.util.Date;

/**
 * Created by wuhanson on 2017/6/3.
 */

public class EditUmbrellaActivity extends Activity {
    private LocationInfor locationInfor;
    private EditText editText;
    private TextView textView, time;
    private Button button;
    private RelativeLayout relativeLayout;
    private ImageView imageView, addboy, addgirl, addsecret,clock,titleImg;
    private LinearLayout backgroung;
    private String selectedTime;
    private int sex = 0;
    private Handler handler = new Handler() {
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
        clock=(ImageView) findViewById(R.id.activity_add_time_ImageView);
        time = (TextView) findViewById(R.id.activity_add_time);
        addboy = (ImageView) findViewById(R.id.center_boy_img);
        addgirl = (ImageView) findViewById(R.id.activity_add_addgirl);
        addsecret = (ImageView) findViewById(R.id.activity_add_addsecret);
        titleImg=(ImageView) findViewById(R.id.activity_add_Title_ImageView);
        backgroung=(LinearLayout) findViewById(R.id.activity_add_background);

        init();
    }

    private void init() {
        locationInfor = MyApplication.getLocationInfor();
        if (getIntent().getIntExtra("Mode", 0) == 0) {//编辑、发布模式
            textView.setVisibility(View.INVISIBLE);
            imageView.setVisibility(View.INVISIBLE);
            button.setText("点击求帮助OvO");
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.center_boy_img: {
                            addboy.setImageResource(R.drawable.addboyc);
                            addgirl.setImageResource(R.drawable.addgirl);
                            addsecret.setImageResource(R.drawable.addsecret);
                            sex = 1;
                            backgroung.setBackground(getResources().getDrawable(R.drawable.add_back));
                            titleImg.setImageResource(R.drawable.add_banner_boy);
                            break;
                        }
                        case R.id.activity_add_addgirl: {
                            addboy.setImageResource(R.drawable.addboy);
                            addgirl.setImageResource(R.drawable.addgirlc);
                            addsecret.setImageResource(R.drawable.addsecret);
                            sex = -1;
                            backgroung.setBackground(getResources().getDrawable(R.drawable.add_back_girl));
                            titleImg.setImageResource(R.drawable.add_banner_girl);
                            break;
                        }
                        case R.id.activity_add_addsecret: {
                            addboy.setImageResource(R.drawable.addboy);
                            addgirl.setImageResource(R.drawable.addgirl);
                            addsecret.setImageResource(R.drawable.addsecretc);
                            sex = 0;
                            backgroung.setBackground(getResources().getDrawable(R.drawable.add_back_secret));
                            titleImg.setImageResource(R.drawable.add_banner_secret);
                            break;
                        }
                    }
                }
            };
            addsecret.setOnClickListener(listener);
            addboy.setOnClickListener(listener);
            addgirl.setOnClickListener(listener);
            View.OnClickListener listener1=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedTime="";
                }
            };
            time.setOnClickListener(listener1);
            clock.setOnClickListener(listener1);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = editText.getText().toString();
                    LocationInfor newLocationInfor = new LocationInfor();
                    newLocationInfor.setDetail(text);
                    newLocationInfor.setName(MyApplication.getName());
                    newLocationInfor.setTime(selectedTime.toString());
                    newLocationInfor.setId(MyApplication.getId());
                    newLocationInfor.setSex(sex);
                    newLocationInfor.setTime(selectedTime);

                }
            });
        } else {//浏览模式
            if (locationInfor == null) finish();
            textView.setVisibility(View.INVISIBLE);
            time.setClickable(false);
            relativeLayout.setVisibility(View.INVISIBLE);
            if (locationInfor.getId() == MyApplication.getId()) {
                button.setText("删除");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            } else {
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
    private void showDialog(){
        View view=getLayoutInflater().inflate(R.layout.alert_dialog2, null);
        Button positive=(Button) view.findViewById(R.id.alert_dialog_positive)
                ,negative=(Button) view.findViewById(R.id.alert_dialog_negative);
        final AlertDialog dialog=new AlertDialog.Builder(this).setView(view).setCancelable(true).create();
        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
