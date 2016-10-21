package com.example.qq985.cpi.Function.Talk;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qq985.cpi.Function.XCRoundImageView;
import com.example.qq985.cpi.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.sql.SQLException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TalkProblemDetails extends AppCompatActivity {

    @Bind(R.id.tv_tpd_toolbar_title)
    TextView tvTpdToolbarTitle;
    @Bind(R.id.iv_tpd_back)
    ImageView ivTpdBack;
    @Bind(R.id.toolbar_tpd)
    Toolbar toolbarTpd;
    @Bind(R.id.iv_tpd_problem_asker_header)
    XCRoundImageView ivTpdProblemAskerHeader;
    @Bind(R.id.tv_tpd_problem_asker_name)
    TextView tvTpdProblemAskerName;
    @Bind(R.id.iv_tpd_problem_reply_count)
    ImageView ivTpdProblemReplyCount;
    @Bind(R.id.iv_tpd_problem_sees_count)
    ImageView ivTpdProblemSeesCount;
    @Bind(R.id.tv_tpd_problem_reply_count)
    TextView tvTpdProblemReplyCount;
    @Bind(R.id.tv_tpd_problem_sees_count)
    TextView tvTpdProblemSeesCount;
    @Bind(R.id.tv_tpd_problem_date)
    TextView tvTpdProblemDate;
    @Bind(R.id.tv_tpd_problem_name)
    TextView tvTpdProblemName;
    @Bind(R.id.tv_tpd_problem_type)
    TextView tvTpdProblemType;
    @Bind(R.id.plv_talk_problem_details)
    PullToRefreshListView plvTalkProblemDetails;
    @Bind(R.id.iv_tpd_clct)
    ImageView ivTpdClct;
    @Bind(R.id.iv_tpd_thumb)
    ImageView ivTpdThumb;
    @Bind(R.id.iv_tpd_comment)
    ImageView ivTpdComment;
    @Bind(R.id.iv_tpd_share)
    ImageView ivTpdShare;
    @Bind(R.id.activity_tpd_problem_details)
    LinearLayout activityTpdProblemDetails;
    @Bind(R.id.ll_tpd_title_bar)
    LinearLayout llTpdTitleBar;
    @Bind(R.id.pb_tpd_refresh)
    ProgressBar pbTpdRefresh;
    int refreshCount;
    Animation appearAnimation = new AlphaAnimation(0, 1);
    Animation disappearAnimation = new AlphaAnimation(1, 0);
    int clct_count = 0;
    int thumb_count = 0;

    private TalkListAdapter talkListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk_problem_details);
        ButterKnife.bind(this);
        initView();
        setOnClick();

        pbTpdRefresh.setVisibility(View.VISIBLE);
        new ChildThread().start();
    }

    Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 55:
                    Bundle bundle = msg.getData();
                    ArrayList<TalkListItem> t = (ArrayList<TalkListItem>) bundle.getSerializable("comments");
                    System.out.println("传给主线程的list大小为" + t.size());
                    talkListAdapter = new TalkListAdapter(t, getBaseContext());
                    plvTalkProblemDetails.setAdapter(talkListAdapter);
                    pbTpdRefresh.setVisibility(View.INVISIBLE);
                    llTpdTitleBar.setVisibility(View.VISIBLE);
                    Toast.makeText(getBaseContext(), "刷新完成ლ(⌒▽⌒ლ)~~", Toast.LENGTH_SHORT).show();
                    plvTalkProblemDetails.onRefreshComplete();
                    break;
                case 56:
                    bundle = msg.getData();
                    ArrayList<TalkListItem> res2 = (ArrayList<TalkListItem>) bundle.getSerializable("list2");
                    if (res2.size() == 0) {
                        Toast.makeText(getBaseContext(), "没有更多内容了ヾ(￣▽￣)Bye~Bye~", Toast.LENGTH_SHORT).show();
                        plvTalkProblemDetails.onRefreshComplete();
                        break;
                    }
                    for (TalkListItem tt : res2) {
//                        if (t.getType() != 1) {
                        talkListAdapter.add(tt);
                        talkListAdapter.notifyDataSetChanged();
//                        } else {
//                            System.out.println("????????????????????????????????????????????");
//                        }
                    }
                    plvTalkProblemDetails.onRefreshComplete();

                default:
                    break;
            }
        }
    };


    private void initView() {
        appearAnimation.setDuration(500);
        disappearAnimation.setDuration(500);
        plvTalkProblemDetails.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        Intent intent_from_talk = getIntent();
        Bundle bundle = intent_from_talk.getExtras();
        ArrayList<TalkListItem> arrayList = (ArrayList<TalkListItem>) bundle.getSerializable("problem_info");
        TalkListItem talkListItem = arrayList.get(0);
        TalkProblemInfo talkProblemInfo = talkListItem.getTalkProblemInfo();
        tvTpdProblemName.setText(talkProblemInfo.getProblemName());
        tvTpdProblemAskerName.setText(talkProblemInfo.getProblemAsker());
        tvTpdProblemDate.setText(talkProblemInfo.getProblemDate());
        tvTpdProblemReplyCount.setText(talkProblemInfo.getProblemReplyCount());
        tvTpdProblemSeesCount.setText(talkProblemInfo.getProblemSeesCount());
        tvTpdProblemType.setText(talkProblemInfo.getProblemType());
    }

    private void setOnClick() {
        plvTalkProblemDetails.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String str = DateUtils.formatDateTime(TalkProblemDetails.this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                if (refreshView.isShownHeader()) {
                    plvTalkProblemDetails.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
                    plvTalkProblemDetails.getLoadingLayoutProxy().setPullLabel("下拉刷新");
                    plvTalkProblemDetails.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");
                    refreshView.getLoadingLayoutProxy().setLastUpdatedLabel("最后更新时间:" + str);
                    new ChildThread().start();
//                    new HomeSecond.ChildThread().start();
                    System.out.println("重新刷新");
                }

                if (refreshView.isShownFooter()) {

                    plvTalkProblemDetails.getLoadingLayoutProxy().setRefreshingLabel("正在加载");
                    plvTalkProblemDetails.getLoadingLayoutProxy().setPullLabel("上拉加载更多");
                    plvTalkProblemDetails.getLoadingLayoutProxy().setReleaseLabel("释放开始加载");
                    refreshView.getLoadingLayoutProxy().setLastUpdatedLabel("最后加载时间:" + str);
                    System.out.println("加载更多");
                    new LoadMoreDataThread().start();
                }
            }
        });

        plvTalkProblemDetails.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case SCROLL_STATE_IDLE:
                        llTpdTitleBar.setAnimation(appearAnimation);
                        llTpdTitleBar.setVisibility(View.VISIBLE);
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                        llTpdTitleBar.setAnimation(disappearAnimation);
                        llTpdTitleBar.setVisibility(View.INVISIBLE);
                        break;
                    default:
                        break;

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }


    @OnClick({R.id.iv_tpd_back, R.id.iv_tpd_clct, R.id.iv_tpd_thumb, R.id.iv_tpd_comment, R.id.iv_tpd_share})
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.iv_tpd_back:
                finish();
                break;
            case R.id.iv_tpd_clct:
                Toast.makeText(this, "暂时不能收藏哦ヾ(･ω･`｡)", Toast.LENGTH_SHORT);
                if (clct_count % 2 == 0) {
                    ivTpdClct.setImageResource(R.drawable.collect_down);
                } else {
                    ivTpdClct.setImageResource(R.drawable.uncollect_down);
                }
                clct_count++;
                break;
            case R.id.iv_tpd_thumb:
                Toast.makeText(this, "暂时不能点赞哦ヾ(･ω･`｡)", Toast.LENGTH_SHORT);
                if (thumb_count % 2 == 0) {
                    ivTpdThumb.setImageResource(R.drawable.collected_down);
                } else {
                    ivTpdThumb.setImageResource(R.drawable.collecting_down);
                }
                thumb_count++;
                break;
            case R.id.iv_tpd_comment:

                Intent intent_from_talk = getIntent();
                Bundle bundle = intent_from_talk.getExtras();
                bundle.putString("problemName", tvTpdProblemName.getText().toString());
                bundle.putString("problemReplyCount", tvTpdProblemReplyCount.getText().toString());
                Intent intent_to_reply = new Intent(TalkProblemDetails.this, TalkReplyProblem.class);
                intent_to_reply.putExtras(bundle);
                startActivity(intent_to_reply);
                finish();
                break;
            case R.id.iv_tpd_share:
                Toast.makeText(this, "暂时不能分享哦ヾ(･ω･`｡)", Toast.LENGTH_SHORT);
                break;
        }
    }


    class ChildThread extends Thread {
        @Override
        public void run() {
            Intent intent_from_talk = getIntent();
            Bundle bundle = intent_from_talk.getExtras();
            ArrayList<TalkListItem> arrayList = (ArrayList<TalkListItem>) bundle.getSerializable("problem_info");
//            System.out.println("pro_info的大小:" + arrayList.size());
            TalkListItem talkListItem = null;
            for (TalkListItem t : arrayList) {
                talkListItem = t;
                System.out.println(talkListItem.getType());
            }
            TalkProblemInfo talkProblemInfo = talkListItem.getTalkProblemInfo();
//            System.out.println("获取评论前问题详情：" + talkProblemInfo.getProblemContent());
            ArrayList<TalkListItem> comments = new ArrayList<>();
            try {
                comments = new TalkGetProblemComments().getProblemComments(talkProblemInfo, "0", 0, 7);
                SharedPreferences sharedPreferences = getSharedPreferences("comments_load_count", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("cur_page", comments.size());
                editor.commit();
//                System.out.println("comments_size=" + comments.size());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Message msg = new Message();
            msg.what = 55;
            bundle.putSerializable("comments", comments);
            msg.setData(bundle);
            uiHandler.sendMessage(msg);

        }
    }

    class LoadMoreDataThread extends Thread {
        @Override
        public void run() {
            Intent intent_from_talk = getIntent();
            Bundle bundle = intent_from_talk.getExtras();
            ArrayList<TalkListItem> arrayList = (ArrayList<TalkListItem>) bundle.getSerializable("problem_info");
//            System.out.println("pro_info的大小:" + arrayList.size());
            TalkListItem talkListItem = null;
            for (TalkListItem t : arrayList) {
                talkListItem = t;
                System.out.println(talkListItem.getType());
            }
            TalkProblemInfo talkProblemInfo = talkListItem.getTalkProblemInfo();
            ArrayList<TalkListItem> res = new ArrayList<>();
            try {
                SharedPreferences sharedPreferences = getSharedPreferences("comments_load_count", Activity.MODE_PRIVATE);
                int cur_page = sharedPreferences.getInt("cur_page", 7);
                res = new TalkGetProblemComments().getProblemComments(talkProblemInfo, "1", cur_page, 4);

                System.err.println("load_more:cur_page=" + cur_page);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("cur_page", cur_page + res.size());
                editor.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // 子线程执行完毕的地方，利用主线程的handler发送消息
            Message msg2 = new Message();
            Bundle bundle2 = new Bundle();
            bundle2.putSerializable("list2", res);
            msg2.what = 56;
            msg2.setData(bundle2);
            uiHandler.sendMessage(msg2);
//            pullToRefreshListView.onRefreshComplete();
        }
    }
}
