package com.example.qq985.cpi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qq985.cpi.Extras.Collection;
import com.example.qq985.cpi.Extras.More;
import com.example.qq985.cpi.Extras.Settings;
import com.example.qq985.cpi.Function.BlankFragment;
import com.example.qq985.cpi.Function.Concept;
import com.example.qq985.cpi.Function.Home.HomeSecond;
import com.example.qq985.cpi.Function.Hot;
import com.example.qq985.cpi.Function.PriceCmp;
import com.example.qq985.cpi.Function.Talk.Talk;
import com.example.qq985.cpi.Function.calendar.DSCPI;

//import com.example.qq985.cpi.Function.Home;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.iv_main_tool_right)
    ImageView ivMainToolRight;
    private DrawerLayout dl_main;
    private RelativeLayout left_menu_main;
    private LinearLayout left_menu, left_menu_above, ll_menu_mid;
    private FrameLayout fl_main;
    private TextView tv_main_toolbar_title;
    private ImageView iv_drawer;
    private ImageView iv_header;
    private TextView tv_nv_header_name, tv_nv_header_illustration;
    //left_menu_item
    private View view_left_menu_item1;
    private ImageView iv_list_item_icon1;
    private TextView tv_list_item_name1;
    private TextView tv_list_item_eng_name1;
    private View view_left_menu_item2;
    private ImageView iv_list_item_icon2;
    private TextView tv_list_item_name2;
    private TextView tv_list_item_eng_name2;
    private View view_left_menu_item3;
    private ImageView iv_list_item_icon3;
    private TextView tv_list_item_name3;
    private TextView tv_list_item_eng_name3;
    private View view_left_menu_item4;
    private ImageView iv_list_item_icon4;
    private TextView tv_list_item_name4;
    private TextView tv_list_item_eng_name4;
    private View view_left_menu_item5;
    private ImageView iv_list_item_icon5;
    private TextView tv_list_item_name5;
    private TextView tv_list_item_eng_name5;
    private View view_left_menu_item6;
    private ImageView iv_list_item_icon6;
    private TextView tv_list_item_name6;
    private TextView tv_list_item_eng_name6;
    //left_menu_settings
    private View view_left_menu_item7;
    private TextView tv_list_item_name7;
    private TextView tv_list_item_eng_name7;
    private View view_left_menu_item8;
    private TextView tv_list_item_name8;
    private TextView tv_list_item_eng_name8;
    private View view_left_menu_item9;
    private TextView tv_list_item_name9;
    private TextView tv_list_item_eng_name9;
    private HomeSecond homeSecond;
    private DSCPI cpi;
    private Concept concept;
    private PriceCmp priceCmp;
    private Hot hot;
    private Talk talk;
    private More more;
    private Collection collection;
    private Settings settings;
    private BlankFragment blankFragment;

    private FragmentManager fragmentManager = getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        instantiationFragment();
        initView();
        setOnClick();
        setNavigation();

        fragmentManager.beginTransaction().replace(R.id.fl_main, homeSecond).commit();

    }

    private void initView() {
        tv_main_toolbar_title = (TextView) findViewById(R.id.tv_main_toolbar_title);
        fl_main = (FrameLayout) findViewById(R.id.fl_main);
        dl_main = (DrawerLayout) findViewById(R.id.dl_main);
        left_menu_main = (RelativeLayout) findViewById(R.id.rl_left_menu_container);
        left_menu = (LinearLayout) findViewById(R.id.ll_left_menu);
        left_menu_above = (LinearLayout) findViewById(R.id.ll_left_menu_above);
        ll_menu_mid = (LinearLayout) findViewById(R.id.ll_left_menu_mid);
        iv_drawer = (ImageView) findViewById(R.id.iv_main_drawer);
        iv_header = (ImageView) findViewById(R.id.nv_header);
        tv_nv_header_name = (TextView) findViewById(R.id.tv_nv_header_name);
        tv_nv_header_illustration = (TextView) findViewById(R.id.tv_nv_header_illustration);

        left_menu_main.getBackground().setAlpha(180);
        left_menu_above.getBackground().setAlpha(180);
        dl_main.setScrimColor(Color.TRANSPARENT);

        /*left_menu_set_list_item */
        view_left_menu_item1 = findViewById(R.id.ll_left_menu_item_home);
        tv_list_item_name1 = (TextView) view_left_menu_item1.findViewById(R.id.ll_left_menu_list_item_name);
        tv_list_item_name1.setText("首页");
        tv_list_item_eng_name1 = (TextView) view_left_menu_item1.findViewById(R.id.ll_left_menu_list_item_eng_name);
        tv_list_item_eng_name1.setText("HOME");
        iv_list_item_icon1 = (ImageView) view_left_menu_item1.findViewById(R.id.ll_left_menu_list_item_icon);
        iv_list_item_icon1.setImageResource(R.drawable.item_icon1);
        view_left_menu_item2 = findViewById(R.id.ll_left_menu_item_concept);
        tv_list_item_name2 = (TextView) view_left_menu_item2.findViewById(R.id.ll_left_menu_list_item_name);
        tv_list_item_name2.setText("你该知道");
        tv_list_item_eng_name2 = (TextView) view_left_menu_item2.findViewById(R.id.ll_left_menu_list_item_eng_name);
        tv_list_item_eng_name2.setText("CONCEPT");
        iv_list_item_icon2 = (ImageView) view_left_menu_item2.findViewById(R.id.ll_left_menu_list_item_icon);
        iv_list_item_icon2.setImageResource(R.drawable.item_icon2);
        view_left_menu_item3 = findViewById(R.id.ll_left_menu_item_cpi);
        tv_list_item_name3 = (TextView) view_left_menu_item3.findViewById(R.id.ll_left_menu_list_item_name);
        tv_list_item_name3.setText("电商CPI");
        tv_list_item_eng_name3 = (TextView) view_left_menu_item3.findViewById(R.id.ll_left_menu_list_item_eng_name);
        tv_list_item_eng_name3.setText("CPI");
        iv_list_item_icon3 = (ImageView) view_left_menu_item3.findViewById(R.id.ll_left_menu_list_item_icon);
        iv_list_item_icon3.setImageResource(R.drawable.item_icon3);
        view_left_menu_item4 = findViewById(R.id.ll_left_menu_item_price);
        tv_list_item_name4 = (TextView) view_left_menu_item4.findViewById(R.id.ll_left_menu_list_item_name);
        tv_list_item_name4.setText("惠购");
        tv_list_item_eng_name4 = (TextView) view_left_menu_item4.findViewById(R.id.ll_left_menu_list_item_eng_name);
        tv_list_item_eng_name4.setText("PRICE CMP");
        iv_list_item_icon4 = (ImageView) view_left_menu_item4.findViewById(R.id.ll_left_menu_list_item_icon);
        iv_list_item_icon4.setImageResource(R.drawable.item_icon4);
        view_left_menu_item5 = findViewById(R.id.ll_left_menu_item_hot);
        tv_list_item_name5 = (TextView) view_left_menu_item5.findViewById(R.id.ll_left_menu_list_item_name);
        tv_list_item_name5.setText("商业头条");
        tv_list_item_eng_name5 = (TextView) view_left_menu_item5.findViewById(R.id.ll_left_menu_list_item_eng_name);
        tv_list_item_eng_name5.setText("HOT");
        iv_list_item_icon5 = (ImageView) view_left_menu_item5.findViewById(R.id.ll_left_menu_list_item_icon);
        iv_list_item_icon5.setImageResource(R.drawable.item_icon5);
        view_left_menu_item6 = findViewById(R.id.ll_left_menu_item_talk);
        tv_list_item_name6 = (TextView) view_left_menu_item6.findViewById(R.id.ll_left_menu_list_item_name);
        tv_list_item_name6.setText("讨论区");
        tv_list_item_eng_name6 = (TextView) view_left_menu_item6.findViewById(R.id.ll_left_menu_list_item_eng_name);
        tv_list_item_eng_name6.setText("TALK");
        iv_list_item_icon6 = (ImageView) view_left_menu_item6.findViewById(R.id.ll_left_menu_list_item_icon);
        iv_list_item_icon6.setImageResource(R.drawable.item_icon6);


        /*left_menu_set_list_settings*/
        view_left_menu_item7 = findViewById(R.id.ll_left_menu_item_more);
        tv_list_item_name7 = (TextView) view_left_menu_item7.findViewById(R.id.ll_left_menu_list_item_name);
        tv_list_item_name7.setText("更多");
        tv_list_item_eng_name7 = (TextView) view_left_menu_item7.findViewById(R.id.ll_left_menu_list_item_eng_name);
        tv_list_item_eng_name7.setText("MORE");
        view_left_menu_item8 = findViewById(R.id.ll_left_menu_item_clct);
        tv_list_item_name8 = (TextView) view_left_menu_item8.findViewById(R.id.ll_left_menu_list_item_name);
        tv_list_item_name8.setText("收藏");
        tv_list_item_eng_name8 = (TextView) view_left_menu_item8.findViewById(R.id.ll_left_menu_list_item_eng_name);
        tv_list_item_eng_name8.setText("CLCT");
        view_left_menu_item9 = findViewById(R.id.ll_left_menu_item_setttings);
        tv_list_item_name9 = (TextView) view_left_menu_item9.findViewById(R.id.ll_left_menu_list_item_name);
        tv_list_item_name9.setText("设置");
        tv_list_item_eng_name9 = (TextView) view_left_menu_item9.findViewById(R.id.ll_left_menu_list_item_eng_name);
        tv_list_item_eng_name9.setText("SETTINGS");


    }

    //实例化Fragment
    private void instantiationFragment() {
        homeSecond = new HomeSecond();
        cpi = new DSCPI();
        concept = new Concept();
        priceCmp = new PriceCmp();
        hot = new Hot();
        talk = new Talk();
        more = new More();
        collection = new Collection();
        settings = new Settings();
        blankFragment = new BlankFragment();

    }

    private void setOnClick() {
        iv_drawer.setOnClickListener(this);
        iv_header.setOnClickListener(this);
        view_left_menu_item1.setOnClickListener(this);
        view_left_menu_item2.setOnClickListener(this);
        view_left_menu_item3.setOnClickListener(this);
        view_left_menu_item4.setOnClickListener(this);
        view_left_menu_item5.setOnClickListener(this);
        view_left_menu_item6.setOnClickListener(this);
        view_left_menu_item7.setOnClickListener(this);
        view_left_menu_item8.setOnClickListener(this);
        view_left_menu_item9.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_main_drawer:
                if (!dl_main.isDrawerOpen(left_menu)) {
                    dl_main.openDrawer(left_menu);
                } else {
                    dl_main.closeDrawers();
                }
                break;
            case R.id.iv_main_tool_right:
                Toast.makeText(getBaseContext(),"啥都没有，哈哈[]~(￣▽￣)~*",Toast.LENGTH_SHORT);
                break;
            case R.id.nv_header:
                Bundle bundle_check_login = checkLoginState();
                if (bundle_check_login.get("is_login").equals("no")) {
                    Intent intent_to_login = new Intent(MainActivity.this, LoginActivity.class);
                    startActivityForResult(intent_to_login, 0);
                    System.out.println("??");
                } else {
                    Intent intent_to_user_info = new Intent(MainActivity.this, UserInfo.class);
                    startActivityForResult(intent_to_user_info, 1);
                }
                break;

            case R.id.ll_left_menu_item_home:
                fragmentManager.beginTransaction().replace(R.id.fl_main, homeSecond).commit();
                dl_main.closeDrawers();
                tv_main_toolbar_title.setText("CPI");
                break;
            case R.id.ll_left_menu_item_concept:
                fragmentManager.beginTransaction().replace(R.id.fl_main, concept).commit();
                dl_main.closeDrawers();
                tv_main_toolbar_title.setText("你该知道");
                break;
            case R.id.ll_left_menu_item_cpi:
                fragmentManager.beginTransaction().replace(R.id.fl_main, cpi).commit();
                dl_main.closeDrawers();
                tv_main_toolbar_title.setText("电商CPI");
                break;
            case R.id.ll_left_menu_item_price:
                fragmentManager.beginTransaction().replace(R.id.fl_main, priceCmp).commit();
                dl_main.closeDrawers();
                tv_main_toolbar_title.setText("惠购");
                break;
            case R.id.ll_left_menu_item_hot:
                fragmentManager.beginTransaction().replace(R.id.fl_main, hot).commit();
                tv_main_toolbar_title.setText("商业头条");
                dl_main.closeDrawers();
                break;
            case R.id.ll_left_menu_item_talk:
                fragmentManager.beginTransaction().replace(R.id.fl_main, talk).commit();
                dl_main.closeDrawers();
                tv_main_toolbar_title.setText("讨论区");
                break;
            case R.id.ll_left_menu_item_more:
                fragmentManager.beginTransaction().replace(R.id.fl_main, more).commit();
                dl_main.closeDrawers();
                tv_main_toolbar_title.setText("更多");
                break;
            case R.id.ll_left_menu_item_clct:
                fragmentManager.beginTransaction().replace(R.id.fl_main, collection).commit();
                tv_main_toolbar_title.setText("收藏");
                dl_main.closeDrawers();
                break;
            case R.id.ll_left_menu_item_setttings:
                fragmentManager.beginTransaction().replace(R.id.fl_main, settings).commit();
                tv_main_toolbar_title.setText("设置");
                dl_main.closeDrawers();
                break;
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            //从登录界面返回
            case 11:
                System.out.println("从登录界面返回");
                Bundle bundle = data.getExtras();
                String is_login = bundle.getString("is_login");
                if (is_login.equals("yes")) {
                    String nv_name = bundle.getString("cur_user");
                    String nv_nick_name = bundle.getString("nick_name");
                    String nv_illustration = bundle.getString("user_illustration");
                    iv_header.setImageResource(R.drawable.header);
                    tv_nv_header_name.setText(nv_nick_name);
                    tv_nv_header_illustration.setText(nv_illustration);
                }
                break;
            //从个人信息界面返回
            case 22:
                System.out.println("从个人信息界面返回");
                bundle = data.getExtras();
                is_login = bundle.getString("is_login");

                if (is_login.equals("no")) {

                    String nv_name = bundle.getString("nick_name");
                    String nv_illustration = bundle.getString("user_illustration");
                    iv_header.setImageResource(R.drawable.header);
                    tv_nv_header_name.setText(nv_name);
                    tv_nv_header_illustration.setText(nv_illustration);
                }
                break;
//            //从注册界面返回
//            case 33:
//                System.out.println("从注册界面返回");
//                bundle = data.getExtras();
//                is_login = bundle.getString("is_login");
//
//                if (is_login.equals("no")) {
//
//                    String nv_name = bundle.getString("nick_name");
//                    String nv_illustration = bundle.getString("user_illustration");
//                    iv_header.setImageResource(R.drawable.header);
//                    tv_nv_header_name.setText(nv_name);
//                    tv_nv_header_illustration.setText(nv_illustration);
//                }
//                break;
            case 4441:
                System.out.println("提交问题后");
//                fragmentManager.beginTransaction().replace(R.id.fl_main,blankFragment).commitAllowingStateLoss();
//                fragmentManager.beginTransaction().replace(R.id.fl_main,talk).commitAllowingStateLoss();
                break;
            default:
                System.out.println("what happen?");
                break;
        }
    }

    public void setNavigation() {

        System.out.println("设置标题栏");
        Intent intent_from_splash = getIntent();
        Bundle bundle = intent_from_splash.getExtras();
        String nick_name = bundle.getString("nick_name");
        String user_illustration = bundle.getString("user_illustration");

        System.out.println(nick_name);
        System.out.println(user_illustration);

        iv_header.setImageResource(R.drawable.header);
        tv_nv_header_name.setText(nick_name);
        tv_nv_header_illustration.setText(user_illustration);
    }

    //检测登录状态
    private Bundle checkLoginState() {

        System.out.println("检查是否登录");

        SharedPreferences sharedPreferences = getSharedPreferences("login_in", Activity.MODE_PRIVATE);
        Bundle bundle = new Bundle();

        if (sharedPreferences.getString("is_login", "no").equals("yes")) {
            System.out.println("已经登录了");
            String user_name = sharedPreferences.getString("cur_user", "未登录");
            String nick_name = sharedPreferences.getString("nick_name", "未登录");
            String user_illustration = sharedPreferences.getString("user_illustration", "这个人没有登录");
            System.out.println(user_name + "," + user_illustration);
            bundle.putString("is_login", "yes");
            bundle.putString("user_name", user_name);
            bundle.putString("nick_name", nick_name);
            bundle.putString("user_illustration", user_illustration);
        } else {
            bundle.putString("is_login", "no");
            bundle.putString("user_name", "未登录");
            bundle.putString("nick_name", "未登录");
            bundle.putString("user_illustration", "这个人没有登录");
        }
        return bundle;
    }
}

