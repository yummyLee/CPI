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
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TalkReplyProblem extends AppCompatActivity {

    @Bind(R.id.iv_reply_problem_back_up)
    ImageView ivReplyProblemBackUp;
    @Bind(R.id.btn_reply_problem_express)
    Button btnReplyProblemExpress;
    @Bind(R.id.et_reply_problem_content)
    EditText etReplyProblemContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk_reply_problem);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_reply_problem_back_up, R.id.btn_reply_problem_express})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_reply_problem_back_up:
                finish();
                break;
            case R.id.btn_reply_problem_express:
                new ReplyThread(etReplyProblemContent.getText().toString()).start();
                break;
        }
    }


    Handler uiHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 55550:
                    Toast.makeText(getBaseContext(), "发表成功", Toast.LENGTH_SHORT).show();
                    setResult(55551);

                    Intent intent_back_problem_details = new Intent(TalkReplyProblem.this, TalkProblemDetails.class);
                    Bundle bundle = getIntent().getExtras();
                    intent_back_problem_details.putExtras(bundle);
                    startActivity(intent_back_problem_details);
                    finish();
                    break;
                default:
                    Toast.makeText(getBaseContext(), "发表失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    class ReplyThread extends Thread {

        private String content;

        public ReplyThread(String content) {
            this.content = content;
        }


        @Override
        public void run() {
            Message msg = new Message();
            Date date = new Date();
            Intent intent_from_tpd = getIntent();
            Bundle bundle = intent_from_tpd.getExtras();
            String problemName = bundle.getString("problemName");
            int count = Integer.parseInt(bundle.getString("problemReplyCount"));
            count++;
            String date_str = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.CHINESE).format(new java.util.Date());
            String replyID = date.getTime() + problemName;
            SharedPreferences sharedPreferences = getSharedPreferences("login_in", MODE_PRIVATE);
            String nick_name = sharedPreferences.getString("nick_name", "未登录");
            if (nick_name.equals("未登录")) {
                nick_name = "匿名";
            }

            Connection con = null;
            try {
                Class.forName("org.gjt.mm.mysql.Driver");
            } catch (ClassNotFoundException e) {

            }

            try {
                con = (Connection) DriverManager.getConnection("jdbc:mysql://123.206.50.61:3306/CPI", "root", "qq3199619");
                //获取问题列表
                System.out.println("发表问题");
                String sql = "INSERT INTO `talkComment`(`problemName`, `problemCommenter`, `problemCommentDate`, `problemCommentContent`, `problemCommentID`) VALUES ('" + problemName + "','" + nick_name + "','" + date_str + "','" + content + "','" + replyID + "')";
                Statement stmt = (Statement) con.createStatement();//创建Statement
                stmt.execute(sql);
                sql = "UPDATE `talkProblem` SET `problemReplyCount`='" + count + "' WHERE `problemName` ='" + problemName + "'";
                System.out.println("更新文章《"+problemName+"》的回复数为"+count);
                stmt.execute(sql);
                msg.what = 55550;
                uiHandler.sendMessage(msg);
            } catch (SQLException e) {
                e.printStackTrace();
                msg.what = 55551;
                uiHandler.sendMessage(msg);
            }

        }
    }
}
