package com.example.qq985.cpi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.iv_register_back_up)
    ImageView ivRegisterBackUp;
    @Bind(R.id.iv_register_logo)
    ImageView ivRegisterLogo;
    @Bind(R.id.et_register_account)
    EditText etRegisterAccount;
    @Bind(R.id.et_register_password)
    EditText etRegisterPassword;
    @Bind(R.id.et_register_password_again)
    EditText etRegisterPasswordAgain;
    @Bind(R.id.et_register_nick_name)
    EditText etRegisterNickName;
    @Bind(R.id.et_register_user_illustration)
    EditText etRegisterIllustration;
    @Bind(R.id.btn_register_register)
    Button btnRegisterRegister;
    @Bind(R.id.et_register_sex)
    EditText etRegisterSex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        SetOnClick();
    }

    private void SetOnClick() {
        btnRegisterRegister.setOnClickListener(this);
        ivRegisterBackUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_register_back_up:
                finish();
                break;
            case R.id.btn_register_register:
                String[] registerInfo = new String[6];
                String user_name = etRegisterAccount.getText().toString();
                String pass_word = etRegisterPassword.getText().toString();
                String pass_word_again = etRegisterPasswordAgain.getText().toString();
                String nick_name = etRegisterNickName.getText().toString();
                String sex = etRegisterSex.getText().toString();
                String user_illustration = etRegisterIllustration.getText().toString();
                registerInfo[0] = user_name;
                registerInfo[1] = pass_word;
                registerInfo[2] = pass_word_again;
                registerInfo[3] = nick_name;
                registerInfo[4] = sex;
                registerInfo[5] = user_illustration;

                if (pass_word.equals(pass_word_again)) {
                    System.out.println("两次密码一样");
                    new EXRegister().execute(registerInfo);
//                    Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                } else {
                    System.out.println("两次密码不一样");
                    Toast.makeText(this, "两次输入密码不一样", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    //执行注册的异步任务
    class EXRegister extends AsyncTask<String, Void, Integer> {
        @Override
        protected void onPostExecute(Integer integer) {
            switch (integer) {
                case 0:
                    Toast.makeText(getBaseContext(), "该账号已经被注册", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    System.out.println("success");
                    Toast.makeText(getBaseContext(), "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case 2:
//                    System.out.println("success");
                    Toast.makeText(getBaseContext(), "注册不成功", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(getBaseContext(), "未知错误", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        protected Integer doInBackground(String... strings) {
            try {
                if (isRegistered()) {
                    //表示已经注册了
                    return 0;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                executeSQL(strings[0], strings[1], strings[2], strings[3], strings[4], strings[5]);
            } catch (SQLException e) {
                e.printStackTrace();
                return 2;
            }
            //注册成功

            SharedPreferences sharedPreferences = getSharedPreferences("login_in", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("cur_user", strings[0]);
            editor.putString("is_login", "yes");
            editor.putString("nick_name", strings[3]);
            editor.putString("sex", strings[4]);
            editor.putString("user_illustration", strings[5]);
            editor.commit();

            Bundle bundle=new Bundle();
            bundle.putString("is_login","no");
            bundle.putString("cur_user", strings[0]);
            bundle.putString("nick_name", strings[3]);
            bundle.putString("sex", strings[4]);
            bundle.putString("user_illustration", strings[5]);
            Intent intent_back=getIntent();

            intent_back.putExtras(bundle);
            setResult(33,intent_back);

            return 1;
        }
    }

    //判断是否已经注册
    public boolean isRegistered() throws SQLException {

        com.mysql.jdbc.Connection con = null;

        try {
            Class.forName("org.gjt.mm.mysql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("1");
        }


        con = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://123.206.50.61:3306/CPI", "root", getString(R.string.mysql_password));

//        System.out.println(username+password);
        String sql = "SELECT * FROM user where user_name='" + etRegisterAccount.getText().toString() + "'";//查询表名为“table_test”的所有内容
        Statement stmt = null;//创建Statement

        stmt = (Statement) con.createStatement();//创建Statement

        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next())
            return true;
        else
            return false;

    }

    //注册的函数
    private void executeSQL(String user_name, String pass_word, String password_again, String nick_name, String sex, String user_illustration) throws SQLException {
        java.sql.Connection con = null;

        try {
            Class.forName("org.gjt.mm.mysql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("1");
        }

        con = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://123.206.50.61:3306/CPI", "root", getString(R.string.mysql_password));
//        System.out.println(user_name+pass_word);
        String sql = "INSERT INTO `CPI`.`user` (`user_name`, `pass_word`, `nick_name`, `sex`, `user_illustration`) VALUES ('" + user_name + "', '" + pass_word + "', '" + nick_name + "', '" + sex + "', '" + user_illustration + "')";
        Statement stmt = (Statement) con.createStatement();//创建Statement
        stmt.execute(sql);
    }


}
