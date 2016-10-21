package com.example.hp.calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;


public class CPIMainActivity extends Activity {
    private ImageButton LeftButton;
    private MyGridView gridview;
    private TextView year;
    private TextView analysis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpi);
        year = (TextView)findViewById(R.id.yearText);
        analysis = (TextView)findViewById(R.id.textView4);
        initView();
        //setYear();
    }




    private void initView() {
        gridview=(MyGridView) findViewById(R.id.gridview);
        String[]cpi = {"0.8%","1.5%","1.4%","1.6%","0.7%","1.1%","1.7%","2.6%","1.2%","0.3%","0.2%","0.1%","0.9%","3.0%","3.5%","2.6%","3.0%","1.7%","2.8%","3.7%","未知"};
        com.example.hp.calendar.MyGridAdapter my = new com.example.hp.calendar.MyGridAdapter(this);
        String sss[] = setYear();
        if(sss[0].equals("2015") && sss[1].equals("1")){
            String[]string = {"0.2%", "3.5%", "1.1%", "7.3%", "0.5%", "1.3%", "-10.5%", "1.5%"};
            String[]str = { "食品："+string[0], "住房："+string[1], "交通："+string[2], "娱乐："+string[3], "电器："+string[4], "烟酒："+string[5],
                    "医疗："+string[6], "服装："+string[7] };
            my.setImg_text(str);
            gridview.setAdapter(my);
            String syearStr = sss[0]+"年"+sss[1]+"月CPI: "+cpi[0];
            year.setText(syearStr);
            analysis.setText("        CPI涨幅为0.8%，其中娱乐教育文化用品及服务对于CPI增长影响最大，食品类对CPI降低影响最大。原因在于气候因素导致鲜菜价格下降，春节期间娱乐教育活动价格上升。");
        }else if(sss[0].equals("2015") && sss[1].equals("2")){
            String[]string = {"0.3%", "4.1%", "0.8%", "8.1%", "0.8%", "0.8%", "-5.7%", "1.1%"};
            String[]str = { "食品："+string[0], "住房："+string[1], "交通："+string[2], "娱乐："+string[3], "电器："+string[4], "烟酒："+string[5],
                    "医疗："+string[6], "服装："+string[7] };
            my.setImg_text(str);
            gridview.setAdapter(my);
            String syearStr = sss[0]+"年"+sss[1]+"月CPI: "+cpi[1];
            year.setText(syearStr);
            analysis.setText("        CPI涨幅为1.5%，其中交通及食品类对于CPI增长影响最大，医疗保健及个人用品对CPI降低影响最大。原因在于春节期间鲜活食品需求增加，春运高峰交通价格上涨。");
        }else if(sss[0].equals("2015") && sss[1].equals("3")){
            String[]string = {"0.5%", "2.7%", "0.3%", "5.7%", "0.7%", "0.3%", "-0.7%", "0.9%"};
            String[]str = { "食品："+string[0], "住房："+string[1], "交通："+string[2], "娱乐："+string[3], "电器："+string[4], "烟酒："+string[5],
                    "医疗："+string[6], "服装："+string[7] };
            my.setImg_text(str);
            gridview.setAdapter(my);
            String syearStr = sss[0]+"年"+sss[1]+"月CPI: "+cpi[2];
            year.setText(syearStr);
            analysis.setText("        CPI涨幅为1.4%，其中娱乐教育文化用品及服务对于CPI增长影响最大，食品对CPI降低影响最大。原因在于节后人们对食品需求减少价格下降，新学期教育费提高，学前教育价格上涨，部分景区值旺季门票价格提高。");
        }else if(sss[0].equals("2015") && sss[1].equals("4")){
            String[]string = {"0.8%", "3.0%", "-0.9%", "6.9%", "0.5%", "-0.7%", "-1.5%", "2.3%"};
            String[]str = { "食品："+string[0], "住房："+string[1], "交通："+string[2], "娱乐："+string[3], "电器："+string[4], "烟酒："+string[5],
                    "医疗："+string[6], "服装："+string[7] };
            my.setImg_text(str);
            gridview.setAdapter(my);
            String syearStr = sss[0]+"年"+sss[1]+"月CPI: "+cpi[3];
            year.setText(syearStr);
            analysis.setText("        CPI涨幅为1.6%，其中娱乐教育文化用品及服务对于CPI增长影响最大，食品类对CPI降低影响最大。原因在于春夏相交鲜果鲜菜上市价格下降。");
        }else if(sss[0].equals("2015") && sss[1].equals("5")){
            String[]string = {"-0.5%", "2.5%", "-1.1%", "7.3%", "0.3%", "-0.5%", "-6.7%", "3.1%"};
            String[]str = { "食品："+string[0], "住房："+string[1], "交通："+string[2], "娱乐："+string[3], "电器："+string[4], "烟酒："+string[5],
                    "医疗："+string[6], "服装："+string[7] };
            my.setImg_text(str);
            gridview.setAdapter(my);
            String syearStr = sss[0]+"年"+sss[1]+"月CPI: "+cpi[4];
            year.setText(syearStr);
            analysis.setText("        CPI涨幅为0.7%，其中娱乐教育文化用品及服务对于CPI增长影响最大，食品对CPI降低影响最大。原因在于气候因素食品价格下降，猪肉价格降低。");
        }else if(sss[0].equals("2015") && sss[1].equals("6")){
            String[]string = {"-0.8%", "2.7%", "-1.4%", "7.4%", "0.2%", "-0.7%", "0.0%", "1.5%"};
            String[]str = { "食品："+string[0], "住房："+string[1], "交通："+string[2], "娱乐："+string[3], "电器："+string[4], "烟酒："+string[5],
                    "医疗："+string[6], "服装："+string[7] };
            my.setImg_text(str);
            gridview.setAdapter(my);
            String syearStr = sss[0]+"年"+sss[1]+"月CPI: "+cpi[5];
            year.setText(syearStr);
            analysis.setText("        CPI涨幅为1.1%，其中娱乐教育文化用品及服务对于CPI增长影响最大，食品类对CPI降低影响最大。" +
                    "原因在于气候因素鲜果鲜菜价格下降，夏季旅游旺季，娱乐教育价格上升。");
        }else if(sss[0].equals("2015") && sss[1].equals("7")){
            String[]string = {"0.2%", "3.1%", "-1.7%", "8.3%", "0.4%", "0.5%", "0.0%", "2.1%"};
            String[]str = { "食品："+string[0], "住房："+string[1], "交通："+string[2], "娱乐："+string[3], "电器："+string[4], "烟酒："+string[5],
                    "医疗："+string[6], "服装："+string[7] };
            my.setImg_text(str);
            gridview.setAdapter(my);
            String syearStr = sss[0]+"年"+sss[1]+"月CPI: "+cpi[6];
            year.setText(syearStr);
            analysis.setText("        CPI涨幅为1.7%，其中娱乐教育文化用品及服务对于CPI增长影响最大，食品类对CPI降低影响最大。原因在于暑期娱乐活动价格上升，猪肉价格下降。");
        }else if(sss[0].equals("2015") && sss[1].equals("8")){
            String[]string = {"0.8%", "4.7%", "0.1%", "9.7%", "0.5%", "1.1%", "0.0%", "3.1%"};
            String[]str = { "食品："+string[0], "住房："+string[1], "交通："+string[2], "娱乐："+string[3], "电器："+string[4], "烟酒："+string[5],
                    "医疗："+string[6], "服装："+string[7] };
            my.setImg_text(str);
            gridview.setAdapter(my);
            String syearStr = sss[0]+"年"+sss[1]+"月CPI: "+cpi[7];
            year.setText(syearStr);
            analysis.setText("        CPI涨幅为2.6%，其中娱乐教育文化用品及服务对于CPI增长影响最大，衣着对CPI降低影响最大。原因在于夏季服装价格较低，暑期娱乐活动价格上升。");
        }else if(sss[0].equals("2015") && sss[1].equals("9")){
            String[]string = {"-1.3%", "2.6%", "-1.9%", "8.7%", "0.2%", "0.3%", "0.0%", "2.7%"};
            String[]str = { "食品："+string[0], "住房："+string[1], "交通："+string[2], "娱乐："+string[3], "电器："+string[4], "烟酒："+string[5],
                    "医疗："+string[6], "服装："+string[7] };
            my.setImg_text(str);
            gridview.setAdapter(my);
            String syearStr = sss[0]+"年"+sss[1]+"月CPI: "+cpi[8];
            year.setText(syearStr);
            analysis.setText("        CPI涨幅为0.3%，其中衣着对于CPI增长影响最大，食品类对CPI降低影响最大。原因在于冬装上市衣着价格上涨，鲜活食品价格下降。");
        }else if(sss[0].equals("2015") && sss[1].equals("10")){
            String[]string = {"-2.5%", "1.7%", "-2.3%", "7.1%", "-0.2%", "-1.1%", "-0.1%", "2.4%"};
            String[]str = { "食品："+string[0], "住房："+string[1], "交通："+string[2], "娱乐："+string[3], "电器："+string[4], "烟酒："+string[5],
                    "医疗："+string[6], "服装："+string[7] };
            my.setImg_text(str);
            gridview.setAdapter(my);
            String syearStr = sss[0]+"年"+sss[1]+"月CPI: "+cpi[9];
            year.setText(syearStr);
            analysis.setText("        CPI涨幅为0.3%，其中衣着对于CPI增长影响最大，食品类对CPI降低影响最大。原因在于冬装上市衣着价格上涨，鲜活食品价格下降。");
        }else if(sss[0].equals("2015") && sss[1].equals("11")){
            String[]string = {"-3.5%", "3.3%", "-2.3%", "15.2%", "-0.1%", "1.1%", "-11.9%", "2.3%"};
            String[]str = { "食品："+string[0], "住房："+string[1], "交通："+string[2], "娱乐："+string[3], "电器："+string[4], "烟酒："+string[5],
                    "医疗："+string[6], "服装："+string[7] };
            my.setImg_text(str);
            gridview.setAdapter(my);
            String syearStr = sss[0]+"年"+sss[1]+"月CPI: "+cpi[10];
            year.setText(syearStr);
            analysis.setText("        CPI涨幅为0.2%，其中娱乐教育文化用品及服务对于CPI增长影响最大，食品类对CPI降低影响最大。原因在于双十一影响总体价格普遍下降。");
        }else if(sss[0].equals("2015") && sss[1].equals("12")){
            String[]string = {"-3.5%", "5.1%", "-2.2%", "14.7%", "-1.2%", "-0.8%", "-12.6%", "2.0%"};
            String[]str = { "食品："+string[0], "住房："+string[1], "交通："+string[2], "娱乐："+string[3], "电器："+string[4], "烟酒："+string[5],
                    "医疗："+string[6], "服装："+string[7] };
            my.setImg_text(str);
            gridview.setAdapter(my);
            String syearStr = sss[0]+"年"+sss[1]+"月CPI: "+cpi[11];
            year.setText(syearStr);
            analysis.setText("        CPI涨幅为0.1%，其中娱乐教育文化用品及服务对于CPI增长影响最大，医疗保健及个人用品对CPI降低影响最大。原因在于寒假影响娱乐活动价格上升。");
        }else if(sss[0].equals("2016") && sss[1].equals("1")){
            String[]string = {"-2.5%","4.7%","-2.2%","15.9%","-0.9%","-0.2%","-8.1%","-0.4%"};
            String[]str = { "食品："+string[0], "住房："+string[1], "交通："+string[2], "娱乐："+string[3], "电器："+string[4], "烟酒："+string[5],
                    "医疗："+string[6], "服装："+string[7] };
            my.setImg_text(str);
            gridview.setAdapter(my);
            String syearStr = sss[0]+"年"+sss[1]+"月CPI: "+cpi[12];
            year.setText(syearStr);
            analysis.setText("        CPI涨幅为0.9%，其中食品类，交通类对于CPI增长影响最大，原因在于寒潮天气导致食品价格上涨，春运期间交通费用增加。");
        }else if(sss[0].equals("2016") && sss[1].equals("2")){
            String[]string = {"0.1%","7.9%","-1.6%","15.0%","-2.7%","0.4%","-0.6%","1.9%"};
            String[]str = { "食品："+string[0], "住房："+string[1], "交通："+string[2], "娱乐："+string[3], "电器："+string[4], "烟酒："+string[5],
                    "医疗："+string[6], "服装："+string[7] };
            my.setImg_text(str);
            gridview.setAdapter(my);
            String syearStr = sss[0]+"年"+sss[1]+"月CPI: "+cpi[13];
            year.setText(syearStr);
            analysis.setText("        CPI涨幅为3.0%，其中娱乐教育文化用品及服务对于CPI增长影响最大，家庭设备及维修服务对CPI降低影响最大。原因在于春节期间电商活动，电器减价，春节期间服务人员减少，服务业价格上升。");
        }else if(sss[0].equals("2016") && sss[1].equals("3")){
            String[]string = {"2.2%","10.2%","0.7%","13.1%","-5.2%","4.8%","-5.0%","1.5%"};
            String[]str = { "食品："+string[0], "住房："+string[1], "交通："+string[2], "娱乐："+string[3], "电器："+string[4], "烟酒："+string[5],
                    "医疗："+string[6], "服装："+string[7] };
            my.setImg_text(str);
            gridview.setAdapter(my);
            String syearStr = sss[0]+"年"+sss[1]+"月CPI: "+cpi[14];
            year.setText(syearStr);
            analysis.setText("        CPI涨幅为3.5%，其中食品对于CPI增长影响最大，医疗保健及个人用品对CPI降低影响最大。原因在于寒潮影响食品价格上升。");
        }else if(sss[0].equals("2016") && sss[1].equals("4")){
            String[]string = {"1.2%","12.5%","-2.4%","9.6%","-5.5%","4.2%","-5.4%","1.5%"};
            String[]str = { "食品："+string[0], "住房："+string[1], "交通："+string[2], "娱乐："+string[3], "电器："+string[4], "烟酒："+string[5],
                    "医疗："+string[6], "服装："+string[7] };
            my.setImg_text(str);
            gridview.setAdapter(my);
            String syearStr = sss[0]+"年"+sss[1]+"月CPI: "+cpi[15];
            year.setText(syearStr);
            analysis.setText("        CPI涨幅为2.6%，其中住房对于CPI增长影响最大，食品类对CPI降低影响最大。原因在于房价的过快增长，春夏之交，食品价格有所下降。");
        }else if(sss[0].equals("2016") && sss[1].equals("5")){
            String[]string = {"0.3%","15.3%","0.3%","10.3%","-6.0%","3.8%","-5.4%","1.5%"};
            String[]str = { "食品："+string[0], "住房："+string[1], "交通："+string[2], "娱乐："+string[3], "电器："+string[4], "烟酒："+string[5],
                    "医疗："+string[6], "服装："+string[7] };
            my.setImg_text(str);
            gridview.setAdapter(my);
            String syearStr = sss[0]+"年"+sss[1]+"月CPI: "+cpi[16];
            year.setText(syearStr);
            analysis.setText("        CPI涨幅为3.0%，其中住房对于CPI增长影响最大，食品类对CPI降低影响最大。原因在于气候转暖食品价格进一步降低，房价持续上涨。");
        }else if(sss[0].equals("2016") && sss[1].equals("6")){
            String[]string = {"-0.9%","17.5%","-1.8%","10.4%","-6.1%","1.6","-13.4%","1.4%"};
            String[]str = { "食品："+string[0], "住房："+string[1], "交通："+string[2], "娱乐："+string[3], "电器："+string[4], "烟酒："+string[5],
                    "医疗："+string[6], "服装："+string[7] };
            my.setImg_text(str);
            gridview.setAdapter(my);
            String syearStr = sss[0]+"年"+sss[1]+"月CPI: "+cpi[17];
            year.setText(syearStr);
            analysis.setText("        CPI涨幅为1.7%，其中住房对于CPI增长影响最大，医疗保健及个人用品对CPI降低影响最大。原因在于房价持续上涨，气候适宜医疗费用降低。");
        }else if(sss[0].equals("2016") && sss[1].equals("7")){
            String[]string = {"0.1%","18.0%","-1.3%","11.4%","-5.3%","2.7%","-9.7%","1.6%"};
            String[]str = { "食品："+string[0], "住房："+string[1], "交通："+string[2], "娱乐："+string[3], "电器："+string[4], "烟酒："+string[5],
                    "医疗："+string[6], "服装："+string[7] };
            my.setImg_text(str);
            gridview.setAdapter(my);
            String syearStr = sss[0]+"年"+sss[1]+"月CPI: "+cpi[18];
            year.setText(syearStr);
            analysis.setText("        CPI涨幅为2.8%，其中住房对于CPI增长影响最大，医疗保健及个人用品对CPI降低影响最大。原因在于房价持续上涨，夏季医疗保健用品使用量减少，费用降低。");
        }else if(sss[0].equals("2016") && sss[1].equals("8")){
            String[]string = {"0.2%","16.0%","0.1%","12.7%","-4.8%","3.9%","-2.3%","1.3%"};
            String[]str = { "食品："+string[0], "住房："+string[1], "交通："+string[2], "娱乐："+string[3], "电器："+string[4], "烟酒："+string[5],
                    "医疗："+string[6], "服装："+string[7] };
            my.setImg_text(str);
            gridview.setAdapter(my);
            String syearStr = sss[0]+"年"+sss[1]+"月CPI: "+cpi[19];
            year.setText(syearStr);
            analysis.setText("        CPI涨幅为3.7%，其中住房，娱乐教育活动对于CPI增长影响最大，原因在于房价持续上涨，暑期娱乐活动价格上涨。");
        }else{
            String[]string = {"未知","未知","未知","未知","未知","未知","未知","未知"};
            String[]str = { "食品："+string[0], "住房："+string[1], "交通："+string[2], "娱乐："+string[3], "电器："+string[4], "烟酒："+string[5],
                    "医疗："+string[6], "服装："+string[7] };
            my.setImg_text(str);
            gridview.setAdapter(my);
            String syearStr = sss[0]+"年"+sss[1]+"月CPI: "+cpi[20];
            year.setText(syearStr);
            analysis.setText("小编暂未收集到数据哦~敬请期待");
        }




    }

    private String[] setYear(){
        Intent intent = getIntent();
        String[]yearStr = intent.getStringArrayExtra("year");
        System.out.println(yearStr);
       // String []cpi = {"0.8%","1.5%","1.4%","1.6%","0.7%","1.1%","1.7%","2.6%","1.2%","0.3%","0.2%","0.1%","0.9%","3.0%","3.5%","2.6%","3.0%","1.7%","2.8%","3.7%"};
        //String syearStr = yearStr[0]+"年"+yearStr[1]+"月CPI: "+"5%";

        //year.setText(syearStr);
        return  yearStr;
    }

}
