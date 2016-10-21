package com.example.hp.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //12个月份的按钮
    private Button Jan;
    private Button Feb;
    private Button Mar;
    private Button Apr;
    private Button May;
    private Button Jun;
    private Button Jul;
    private Button Aug;
    private Button Sep;
    private Button Oct;
    private Button Nov;
    private Button Dec;
    private TextView year;
    private ImageButton LeftButton;        //上一年
    private ImageButton RightButton;       //下一年
    private String yearStr = "2016";
    private String[]yearAndMonth = {"2016", "1"};
    private String yearString = "2016";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        year = (TextView)findViewById(R.id.textView2);
        left();
        right();
        JanButton();
        FebButton();
        MarButton();
        AprButton();
        MayButton();
        JunButton();
        JulButton();
        AugButton();
        SepButton();
        OctButton();
        NovButton();
        DecButton();

    }

    public void setYearAndMonth(String[]str){
        yearAndMonth = str;
    }

    public String[] getYearAndMonth(){
        return yearAndMonth;
    }
    //月份选择跳转到某月具体的CPI情况
    private  void jumpStr(String str){
        Intent intent = new Intent(com.example.hp.calendar.MainActivity.this, CPIMainActivity.class);
        intent.putExtra("year",str);
        System.out.println("jump:"+str);
        com.example.hp.calendar.MainActivity.this.startActivity(intent);

    }

    private  void jump(){
        Intent intent = new Intent(com.example.hp.calendar.MainActivity.this, CPIMainActivity.class);
        com.example.hp.calendar.MainActivity.this.startActivity(intent);

    }

    //转到当前年1月份CPI的详情
    private void JanButton(){
        Jan = (Button)findViewById(R.id.buttonl);
        Jan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //yearStr = year.getText().toString().trim();
                String str = yearStr+"年"+"1月CPI：";
                String[]ss = {yearStr, "1"};
                setYearAndMonth(ss);
                Intent intent = new Intent(com.example.hp.calendar.MainActivity.this, CPIMainActivity.class);
                intent.putExtra("year",ss);
                com.example.hp.calendar.MainActivity.this.startActivity(intent);
            }
        });
    }

    //转到当前年2月份的CPI详情
    private void FebButton(){
        Feb = (Button)findViewById(R.id.buttonPane2);
        Feb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //yearStr = year.getText().toString().trim();
                String str = yearStr+"年"+"2月CPI：";
                String[]ss = {yearStr, "2"};
                setYearAndMonth(ss);
                Intent intent = new Intent(com.example.hp.calendar.MainActivity.this, CPIMainActivity.class);
                intent.putExtra("year",ss);
                com.example.hp.calendar.MainActivity.this.startActivity(intent);
            }
        });
    }

    private void MarButton(){
        Mar = (Button)findViewById(R.id.buttonPane3);
        Mar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //yearStr = year.getText().toString().trim();
                String str = yearStr+"年"+"3月CPI：";
                System.out.println("Mar: "+ str);
                String[]ss = {yearStr, "3"};
                Intent intent = new Intent(com.example.hp.calendar.MainActivity.this, CPIMainActivity.class);
                intent.putExtra("year",ss);
                com.example.hp.calendar.MainActivity.this.startActivity(intent);
            }
        });
    }

    private void AprButton(){
        Apr = (Button)findViewById(R.id.buttonPane4);
        Apr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //yearStr = year.getText().toString().trim();
                String str = yearStr+"年"+"4月CPI：";
                String[]ss = {yearStr, "4"};
                Intent intent = new Intent(com.example.hp.calendar.MainActivity.this, CPIMainActivity.class);
                intent.putExtra("year",ss);
                com.example.hp.calendar.MainActivity.this.startActivity(intent);
            }
        });
    }

    private void MayButton(){
        May = (Button)findViewById(R.id.buttonPane5);
        May.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //yearStr = year.getText().toString().trim();
                String str = yearStr+"年"+"5月CPI：";
                String[]ss = {yearStr, "5"};
                Intent intent = new Intent(com.example.hp.calendar.MainActivity.this, CPIMainActivity.class);
                intent.putExtra("year",ss);
                com.example.hp.calendar.MainActivity.this.startActivity(intent);
            }
        });
    }

    private void JunButton(){
        Jun = (Button)findViewById(R.id.buttonPane6);
        Jun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //yearStr = year.getText().toString().trim();
                String str = yearStr+"年"+"6月CPI：";
                String[]ss = {yearStr, "6"};
                Intent intent = new Intent(com.example.hp.calendar.MainActivity.this, CPIMainActivity.class);
                intent.putExtra("year",ss);
                com.example.hp.calendar.MainActivity.this.startActivity(intent);
            }
        });
    }

    private void JulButton(){
        Jul = (Button)findViewById(R.id.buttonPane7);
        Jul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //yearStr = year.getText().toString().trim();
                String str = yearStr+"年"+"7月CPI：";
                String[]ss = {yearStr, "7"};
                Intent intent = new Intent(com.example.hp.calendar.MainActivity.this, CPIMainActivity.class);
                intent.putExtra("year",ss);
                com.example.hp.calendar.MainActivity.this.startActivity(intent);
            }
        });
    }

    private void AugButton(){
        Aug = (Button)findViewById(R.id.buttonPane8);
        Aug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // yearStr = year.getText().toString().trim();
                String str = yearStr+"年"+"8月CPI：";
                String[]ss = {yearStr, "8"};
                Intent intent = new Intent(com.example.hp.calendar.MainActivity.this, CPIMainActivity.class);
                intent.putExtra("year",ss);
                com.example.hp.calendar.MainActivity.this.startActivity(intent);
            }
        });
    }

    private void SepButton(){
        Sep = (Button)findViewById(R.id.buttonPane9);
        Sep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //yearStr = year.getText().toString().trim();
                String str = yearStr+"年"+"9月CPI：";
                String[]ss = {yearStr, "9"};
                Intent intent = new Intent(com.example.hp.calendar.MainActivity.this, CPIMainActivity.class);
                intent.putExtra("year",ss);
                com.example.hp.calendar.MainActivity.this.startActivity(intent);
            }
        });
    }

    private void OctButton(){
        Oct = (Button)findViewById(R.id.buttonPanel0);
        Oct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //yearStr = year.getText().toString().trim();
                String str = yearStr+"年"+"10月CPI：";
                String[]ss = {yearStr, "10"};
                Intent intent = new Intent(com.example.hp.calendar.MainActivity.this, CPIMainActivity.class);
                intent.putExtra("year",ss);
                com.example.hp.calendar.MainActivity.this.startActivity(intent);
            }
        });
    }



    private void NovButton(){
        Nov = (Button)findViewById(R.id.buttonPane1l);
        Nov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //yearStr = year.getText().toString().trim();
                String str = yearStr+"年"+"11月CPI：";
                String[]ss = {yearStr, "11"};
                Intent intent = new Intent(com.example.hp.calendar.MainActivity.this, CPIMainActivity.class);
                intent.putExtra("year",ss);
                com.example.hp.calendar.MainActivity.this.startActivity(intent);
            }
        });
    }

    private void DecButton(){
        Dec = (Button)findViewById(R.id.buttonPanel2);
        Dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //yearStr = year.getText().toString().trim();
                String str = yearStr+"年"+"12月CPI：";
                String[]ss = {yearStr, "12"};
                Intent intent = new Intent(com.example.hp.calendar.MainActivity.this, CPIMainActivity.class);
                intent.putExtra("year",ss);
                com.example.hp.calendar.MainActivity.this.startActivity(intent);
            }
        });
    }
    //改变到当前年份的上一年
    private void left(){
        LeftButton = (ImageButton)findViewById(R.id.imageButton);
        LeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yearStr = year.getText().toString().trim();
                int yearInt = Integer.parseInt(yearStr);
                yearInt--;
                yearStr = String.valueOf(yearInt);
                System.out.println("left: "+yearStr);
                year.setText(yearStr);
                yearStr = year.getText().toString().trim();
                System.out.println("leftChange: "+yearStr);
            }
        });

    }

    //改变到当前年份的下一年
    private void right(){
        RightButton = (ImageButton)findViewById(R.id.imageButton2);
        RightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yearStr = year.getText().toString().trim();
                int yearInt = Integer.parseInt(yearStr);
                yearInt++;
                yearStr = String.valueOf(yearInt);
                System.out.println("right: "+yearStr);
                year.setText(yearStr);
                yearStr = year.getText().toString().trim();
                System.out.println("leftChange: "+yearStr);
            }
        });
    }
}
