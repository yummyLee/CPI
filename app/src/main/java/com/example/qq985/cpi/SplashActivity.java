package com.example.qq985.cpi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.qq985.cpi.Function.Home.HomeGetArticlesItem;
import com.example.qq985.cpi.Function.Home.HomeListItem;

import java.sql.SQLException;
import java.util.ArrayList;

public class SplashActivity extends Activity implements Runnable {

    public static final int VERSION = 1;
    public static SharedPreferences sp;
    private ImageView iv_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        iv_logo = (ImageView) findViewById(R.id.iv_logo);

        ScaleAnimation sAnima = new ScaleAnimation(0.2f, 1, 0.2f, 1, 1, 0.5f, 1, 0.5f);//横向放大7倍，纵向放大7倍
        sAnima.setDuration(1500);

        iv_logo.setAnimation(sAnima);
        // 启动一个延迟线程
//        new Thread(this).start();

        new Thread(PreLoadListData).start();

    }


    @Override
    public void run() {

//        new PreLoadListData().run();

//        try {
//            // 延迟两秒时间
//            Thread.sleep(2000);
//            // 读取SharedPreferences中需要的数据
//            sp = getSharedPreferences("Y_Setting", Context.MODE_PRIVATE);
//            /**
//             * 如果用户不是第一次使用则直接调转到显示界面,否则调转到引导界面
//             */
//            if (sp.getInt("VERSION", 0) != VERSION) {
//                startActivity(new Intent(SplashActivity.this,
//                        SplashGuide.class));
//            } else {
//                startActivity(new Intent(SplashActivity.this,
//                        MainActivity.class));
//            }
//            finish();
//
//        } catch (Exception e) {
//        }
    }

    Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            sp = getSharedPreferences("Y_Setting", Context.MODE_PRIVATE);
            switch (msg.what) {
                case 1:
                    Intent intent_to_main = new Intent(SplashActivity.this, MainActivity.class);
                    Intent intent_to_guide = new Intent(SplashActivity.this, SplashGuide.class);
                    Bundle bundle = msg.getData();
                    bundle.putString("net_state", "net_state1");
                    intent_to_main.putExtras(bundle);
                    intent_to_guide.putExtras(bundle);

                    if (sp.getInt("VERSION", 0) != VERSION) {
                        startActivity(intent_to_guide);
                    } else {
                        startActivity(intent_to_main);
                    }
                    System.out.println("net_state1");
                    finish();
                    break;
                case 2:
                    Toast.makeText(getBaseContext(), "没有网络ヽ(≧□≦)ノ！！", Toast.LENGTH_SHORT);
                    intent_to_main = new Intent(SplashActivity.this, MainActivity.class);
                    intent_to_guide = new Intent(SplashActivity.this, SplashGuide.class);
                    bundle = msg.getData();
                    bundle.putString("net_state", "net_state2");
                    intent_to_main.putExtras(bundle);
                    intent_to_guide.putExtras(bundle);

                    if (sp.getInt("VERSION", 0) != VERSION) {
                        startActivity(intent_to_guide);
                    } else {
                        startActivity(intent_to_main);
                    }
                    System.out.println("net_state2");
                    finish();
                    break;
                case 3:
                    Toast.makeText(getBaseContext(), "没有网络ヽ(≧□≦)ノ！！", Toast.LENGTH_SHORT);
                    intent_to_main = new Intent(SplashActivity.this, MainActivity.class);
                    intent_to_guide = new Intent(SplashActivity.this, SplashGuide.class);

                    bundle = msg.getData();
                    bundle.putString("net_state", "net_state3");

                    intent_to_main.putExtras(bundle);
                    intent_to_guide.putExtras(bundle);

                    if (sp.getInt("VERSION", 0) != VERSION) {
                        startActivity(intent_to_guide);
                    } else {
                        startActivity(intent_to_main);
                    }
                    finish();
                    System.out.println("net_state3");
                    break;
                default:
                    System.out.println("???");
                    finish();
                    break;
            }
        }
    };

    Runnable PreLoadListData = new Runnable() {


        @Override
        public void run() {

            ArrayList<HomeListItem> res = new ArrayList<>();
            SharedPreferences sharedPreferences = getSharedPreferences("home_load_count", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            ACache aCache = ACache.get(getBaseContext());
            res = (ArrayList<HomeListItem>) aCache.getAsObject("home_list_cache");
            if (isNetworkAvailable(getBaseContext())) {

                System.out.println("网络状态好");
                if (res == null) {
                    try {
                        System.out.println("联网获得的数据");
                        res = new HomeGetArticlesItem().getArticleList("0", "homeArticle", 0, 4);
                        System.out.println("splash初始列表大小=" + res.size());
                        editor.putInt("cur_page", res.size());
                        editor.commit();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    //判断是否缓存
                    System.out.println("有网缓存获得的数据，缓存大小为："+res.size());
                }

                Message msg1 = new Message();
                Bundle bundle = checkLoginState();
                aCache.put("home_list_cache", res, ACache.TIME_DAY);
                msg1.what = 1;   //有网或缓存获得的
                msg1.setData(bundle);
                uiHandler.sendMessage(msg1);

            } else {

                Message msg1 = new Message();
                Bundle bundle = checkLoginState();
                System.out.println("没有网");
                if (res != null) {
                    msg1.what = 2;    //没网缓存获得
                    System.out.println("没网缓存获得");
                    msg1.setData(bundle);
                } else {
                    msg1.what = 3; //没网没获得数据
                    System.out.println("没网无缓存");
                }
                uiHandler.sendMessage(msg1);
            }

        }


    };


    //检测是否已经登录，并返回检测参数
    private Bundle checkLoginState() {

        System.out.println("检查是否登录");

        SharedPreferences sharedPreferences = getSharedPreferences("login_in", Activity.MODE_PRIVATE);
        Bundle bundle = new Bundle();

        if (sharedPreferences.getString("is_login", "no").equals("yes")) {
            System.out.println("已经登录了");
            String nick_name = sharedPreferences.getString("nick_name", "未登录");
            String user_illustration = sharedPreferences.getString("user_illustration", "这个人没有登录");
            System.out.println(nick_name + "," + user_illustration);
            bundle.putString("is_login", "yes");
            bundle.putString("nick_name", nick_name);
            bundle.putString("user_illustration", user_illustration);
        } else {
            System.out.println("还没有登录");
            bundle.putString("is_login", "no");
            bundle.putString("nick_name", "未登录");
            bundle.putString("user_illustration", "这个人没有登录");
        }
        return bundle;
    }


    //检测网络状态
    public static boolean isNetworkAvailable(final Context context) {
        boolean hasWifoCon = false;
        boolean hasMobileCon = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfos = cm.getAllNetworkInfo();
        for (NetworkInfo net : netInfos) {

            String type = net.getTypeName();
            if (type.equalsIgnoreCase("WIFI")) {
                if (net.isConnected()) {
                    hasWifoCon = true;
                }
            }

            if (type.equalsIgnoreCase("MOBILE")) {
                if (net.isConnected()) {
                    hasMobileCon = true;
                }
            }
        }
        return hasWifoCon || hasMobileCon;

    }
}
