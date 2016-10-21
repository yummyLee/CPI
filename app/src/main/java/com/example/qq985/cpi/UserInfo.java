package com.example.qq985.cpi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class UserInfo extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.iv_user_info_back_up)
    ImageView ivUserInfoBackUp;
    @Bind(R.id.iv_user_info_header)
    ImageView ivUserInfoHeader;
    @Bind(R.id.tv_user_info_header)
    TextView tvUserInfoHeader;
    @Bind(R.id.iv_user_info_header_to_edit)
    ImageView ivUserInfoHeaderToEdit;
    @Bind(R.id.tv_user_info_user_name)
    TextView tvUserInfoUserName;
    @Bind(R.id.iv_user_info_user_name_to_edit)
    ImageView ivUserInfoUserNameToEdit;
    @Bind(R.id.tv_user_info_nick_name)
    TextView tvUserInfoNickName;
    @Bind(R.id.iv_user_info_nick_name_to_edit)
    ImageView ivUserInfoNickNameToEdit;
    @Bind(R.id.tv_user_info_sex)
    TextView tvUserInfoSex;
    @Bind(R.id.iv_user_info_sex_to_edit)
    ImageView ivUserInfoSexToEdit;
    @Bind(R.id.tv_user_info_user_illustration)
    TextView tvUserInfoUserIllustration;
    @Bind(R.id.iv_user_info_illustration_to_edit)
    ImageView ivUserInfoIllustrationToEdit;
    @Bind(R.id.btn_logout)
    Button btnLogout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);

        initView();
        setOnClick();

        new SetUserInfo().execute();

    }

    public void initView() {
        btnLogout = (Button) findViewById(R.id.btn_logout);
    }

    public void setOnClick() {
        btnLogout.setOnClickListener(this);
        ivUserInfoBackUp.setOnClickListener(this);
        tvUserInfoHeader.setOnClickListener(this);
        tvUserInfoUserName.setOnClickListener(this);
        tvUserInfoSex.setOnClickListener(this);
        tvUserInfoUserIllustration.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_logout:
                Intent intent_from_main = getIntent();
                intent_from_main.putExtras(logout());
                setResult(22, intent_from_main);

                finish();
            case R.id.iv_user_info_back_up:
                finish();
                break;

            case R.id.iv_user_info_user_name_to_edit:

                break;
            case R.id.iv_user_info_nick_name_to_edit:

                break;
            case R.id.iv_user_info_sex_to_edit:

                break;
            case R.id.iv_user_info_illustration_to_edit:

                break;
            default:

                break;
        }
    }

    class SetUserInfo extends AsyncTask<Void, Void, Bundle> {

        @Override
        protected void onPostExecute(Bundle bundle) {
            tvUserInfoUserName.setText(bundle.getString("cur_user"));
            tvUserInfoNickName.setText(bundle.getString("nick_name"));
            tvUserInfoSex.setText(bundle.getString("sex"));
            tvUserInfoUserIllustration.setText(bundle.getString("user_illustration"));
        }

        @Override
        protected Bundle doInBackground(Void... params) {

            SharedPreferences sharedPreferences = getSharedPreferences("login_in", MODE_PRIVATE);
            Bundle bundle = new Bundle();
            bundle.putString("is_login", sharedPreferences.getString("is_login", "未登录"));
            bundle.putString("cur_user", sharedPreferences.getString("cur_user", "未登录"));
            bundle.putString("nick_name", sharedPreferences.getString("nick_name", "未登录"));
            bundle.putString("sex", sharedPreferences.getString("sex", "未登录"));
            bundle.putString("user_illustration", sharedPreferences.getString("user_illustration", "这个人没有登录"));

            return bundle;
        }
    }

    public Bundle logout() {
        SharedPreferences sharedPreferences = getSharedPreferences("login_in", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("is_login", "no");
        editor.putString("cur_user", "未登录");
        editor.putString("nick_name", "未登录");
        editor.putString("sex", "未登录");
        editor.putString("user_illustration", "这个人没有登录");
        editor.commit();

        Bundle bundle = new Bundle();
        bundle.putString("is_login", "no");
        bundle.putString("cur_user", "未登录");
        bundle.putString("nick_name", "未登录");
        bundle.putString("sex", "未登录");
        bundle.putString("user_illustration", "这个人没有登录");

        editor.commit();

        return bundle;
    }
}

