package com.example.qq985.cpi.Function.Home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.qq985.cpi.R;
import com.squareup.picasso.Picasso;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


public class HomeViewPagerVideo extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_view_pager_video);
        textView = (TextView) findViewById(R.id.textView);
        textView.setText("嗯，这是一个从服务器看的视频\n" +
                "嗯，很可能它有点卡\n" +
                "嗯，也许你并不知道它在讲什么\n" +
                "嗯，好吧，这其实只是测试用的\n" +
                "其实，下面才是重点\n" +
                "致橡树\n" +
                "我如果爱你——\n" +
                "绝不像攀援的凌霄花，\n" +
                "借你的高枝炫耀自己；\n" +
                "我如果爱你——\n" +
                "绝不学痴情的鸟儿，\n" +
                "为绿荫重复单调的歌曲\n" +
                "也不止像泉源，\n" +
                "常年送来清凉的慰藉；\n" +
                "也不止像险峰，\n" +
                "增加你的高度，衬托你的威仪。\n" +
                "好吧，老实告诉你\n" +
                "这都只是在测试");
        videoJar();


    }

    public void videoJar() {
        JCVideoPlayerStandard jcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.custom_videoplayer_standard);
        jcVideoPlayerStandard.setUp("http://123.206.50.61/Double100/CPI05.mp4"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, "CPI");
        Picasso.with(this)
                .load("http://123.206.50.61/Double100/CPIImage.png")
                .into(jcVideoPlayerStandard.thumbImageView);
        JCVideoPlayerStandard.startFullscreen(this, JCVideoPlayerStandard.class, "http://123.206.50.61/Double100/CPI05.mp4", "CPI");
    }


    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

}
