package com.example.qq985.cpi.Function.Talk;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.qq985.cpi.R;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TalkEditProblem extends AppCompatActivity {

    @Bind(R.id.iv_edit_problem_back_up)
    ImageView ivEditProblemBackUp;
    @Bind(R.id.btn_edit_problem_express)
    Button btnEditProblemExpress;
    @Bind(R.id.et_edit_problem_title)
    EditText etEditProblemTitle;
    @Bind(R.id.et_edit_problem_content)
    EditText etEditProblemContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_problem);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_edit_problem_back_up, R.id.btn_edit_problem_express})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_edit_problem_back_up:
                finish();
                break;
            case R.id.btn_edit_problem_express:
                new ExpressThread(etEditProblemTitle.getText().toString(), etEditProblemContent.getText().toString()).start();
                break;
        }
    }


    Handler uiHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 44440:
                    Toast.makeText(getBaseContext(), "发表成功", Toast.LENGTH_SHORT).show();
                    setResult(4441);
                    finish();
                    break;
                default:
                    Toast.makeText(getBaseContext(), "发表失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    class ExpressThread extends Thread {

        private String title;
        private String content;

        public ExpressThread(String title, String content) {
            this.title = title;
            this.content = content;
        }

        @Override
        public void run() {
            Message msg = new Message();
            String date_str = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.CHINESE).format(new java.util.Date());
            date_str.replace("GMT+08:00","");
            String type = "经济";
            SharedPreferences sharedPreferences = getSharedPreferences("login_in", MODE_PRIVATE);
            String nick_name = sharedPreferences.getString("nick_name", "未登录");
            if (nick_name.equals("未登录")) {
                nick_name = "匿名";
            }
            String replyCount = "1";
            String seesCount = "1";
            String reply_date_str = date_str;
            Connection con = null;
            try {
                Class.forName("org.gjt.mm.mysql.Driver");
            } catch (ClassNotFoundException e) {

            }

            try {
                con = (Connection) DriverManager.getConnection("jdbc:mysql://123.206.50.61:3306/CPI", "root", "qq3199619");
                //获取问题列表
                System.out.println("发表问题");
                String sql = "INSERT INTO `talkProblem`(`problemName`, `problemType`, `problemAsker`, `problemDate`, `problemReplyCount`, `problemSeeCount`, `problemLatestReplyDate`, `problemContent`) VALUES ('" + title + "','" + type + "','" + nick_name + "','" + date_str + "','" + replyCount + "','" + seesCount + "','" + reply_date_str + "','" + content + "')";
                Statement stmt = (Statement) con.createStatement();//创建Statement
                stmt.execute(sql);
                msg.what = 44440;
                uiHandler.sendMessage(msg);
            } catch (SQLException e) {
                e.printStackTrace();
                msg.what = 44441;
                uiHandler.sendMessage(msg);
            }

        }
    }
}