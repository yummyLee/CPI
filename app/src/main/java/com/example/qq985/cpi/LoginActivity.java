package com.example.qq985.cpi;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.zip.Inflater;

public class LoginActivity extends Activity implements View.OnClickListener {

    private ImageView iv_login_logo, iv_login_back;
    private Button btn_third_login, btn_login_login, btn_login_register;
    private PopupWindow pw_third_login;
    private View view_pw;
    private RelativeLayout rl_third_login;
    private EditText et_login_user_account, et_login_password;

    private boolean is_popup_opened = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        initView();
        setOnClick();
        //logo动画
        ScaleAnimation sAnima = new ScaleAnimation(0.2f, 1, 0.2f, 1, 1, 0.5f, 1, 0.5f);//横向放大7倍，纵向放大7倍
        sAnima.setDuration(1500);
        iv_login_logo.setAnimation(sAnima);


    }

    void setOnClick() {
        btn_third_login.setOnClickListener(this);
        iv_login_back.setOnClickListener(this);
        btn_login_login.setOnClickListener(this);
        btn_login_register.setOnClickListener(this);
    }

    void initView() {
        LayoutInflater inflater = LayoutInflater.from(this);

        iv_login_logo = (ImageView) findViewById(R.id.iv_login_logo);
        iv_login_back = (ImageView) findViewById(R.id.iv_login_back_up);
        et_login_password = (EditText) findViewById(R.id.et_login_password);
        et_login_user_account = (EditText) findViewById(R.id.et_login_account);
        rl_third_login = (RelativeLayout) findViewById(R.id.rl_third_login);
        btn_third_login = (Button) findViewById(R.id.btn_login_third_login);
        btn_login_login = (Button) findViewById(R.id.btn_login_login);
        btn_login_register = (Button) findViewById(R.id.btn_login_register);
        view_pw = inflater.inflate(R.layout.login_thrid_popup, null);
        pw_third_login = new PopupWindow(view_pw, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, false);
        pw_third_login.setBackgroundDrawable(new BitmapDrawable());
        pw_third_login.setOutsideTouchable(true);
        pw_third_login.setAnimationStyle(R.style.MenuAnimationFade);
//        pw_third_login.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
//        pw_third_login.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_login:
                new ESqlLogin().execute(et_login_user_account.getText().toString(), et_login_password.getText().toString());
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_login_register:
                Intent intent_to_register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent_to_register,2);
                break;
            case R.id.iv_login_back_up:
                finish();
                System.out.println("clicked");
                break;
            case R.id.btn_login_third_login:
                if (is_popup_opened) {
                    pw_third_login.dismiss();
                    is_popup_opened = false;
                } else {
                    pw_third_login.showAtLocation(view_pw, Gravity.BOTTOM, 0, 200);
                    is_popup_opened = true;
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode){
            //33表示从注册界面返回
            case 33:
                setResult(22,data);
                finish();
                break;
            default:
                break;
        }
    }

    //使用executesql登录
    class ESqlLogin extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {

            try {
                executeSQL(params[0], params[1]);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    //登录的函数
    public void executeSQL(String user_name, String pass_word) throws SQLException {
        Connection con = null;


        try {
            Class.forName("org.gjt.mm.mysql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("1");
        }

        con = (Connection) DriverManager.getConnection("jdbc:mysql://123.206.50.61:3306/CPI", "root", getString(R.string.mysql_password));
//        System.out.println(user_name+pass_word);
        String sql = "SELECT * FROM user where user_name='" + user_name + "' and pass_word='" + pass_word + "'";
        Statement stmt = (Statement) con.createStatement();//创建Statement
        ResultSet rs = stmt.executeQuery(sql);

        SharedPreferences sharedPreferences = getSharedPreferences("login_in", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Intent intent_back = getIntent();
        Bundle bundle = new Bundle();
        if (rs.next()) {

            //修改登录状态
            editor.putString("cur_user", user_name);
            editor.putString("is_login", "yes");
            editor.putString("nick_name", rs.getString(3));
            editor.putString("sex", rs.getString(4));
            editor.putString("user_illustration", rs.getString(5));
            editor.commit();

            bundle.putString("is_login", "yes");
            bundle.putString("cur_user", user_name);
            bundle.putString("nick_name", rs.getString(3));
            bundle.putString("sex", rs.getString(4));
            bundle.putString("user_illustration", rs.getString(5));
            intent_back.putExtras(bundle);

            setResult(11, intent_back);

            System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
            System.out.println(rs.getString(4));
            System.out.println(rs.getString(5));

//            startActivity(new Intent(LoginActivity.this, MainActivity.class));


        } else {

            editor.putString("cur_user", "未登录");
            editor.putString("is_login", "no");
            editor.commit();

            bundle.putString("is_login", "no");
            bundle.putString("cur_user", "未登录");
            bundle.putString("nick_name", "未登录");
            bundle.putString("sex", "未登录");
            bundle.putString("user_illustration", "这个人没有登录");
            intent_back.putExtras(bundle);
            setResult(11, intent_back);
        }

        finish();
    }
}

