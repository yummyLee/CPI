package com.example.qq985.cpi.Function.Talk;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.qq985.cpi.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Talk extends Fragment {

    @Bind(R.id.fab_talk_add_problem)
    ImageButton fabTalkAddProblem;
    @Bind(R.id.plv_talk_list)
    PullToRefreshListView plvTalkList;
    @Bind(R.id.pb_talk_refresh)
    ProgressBar pbTalkRefresh;
    private TalkListAdapter talkListAdapter;
    private int refreshCount;
    private View view;
    Animation appearAnimation = new AlphaAnimation(0, 1);
    Animation disappearAnimation = new AlphaAnimation(1, 0);


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appearAnimation.setDuration(500);
        disappearAnimation.setDuration(500);
        new ChildThread().start();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_talk, container, false);

        appearAnimation.setDuration(500);
        disappearAnimation.setDuration(500);

        ButterKnife.bind(this, view);
        initView();
        setOnClick();
        if (refreshCount == 0) {
            pbTalkRefresh.setVisibility(View.VISIBLE);
        }
        new ChildThread().start();
        return view;
    }




    Handler uiHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 44:
                    Bundle bundle = msg.getData();
                    ArrayList<TalkListItem> res = (ArrayList<TalkListItem>) bundle.getSerializable("list");
                    talkListAdapter = new TalkListAdapter(res, getContext());
                    plvTalkList.setAdapter(talkListAdapter);
                    pbTalkRefresh.setVisibility(View.GONE);
                    plvTalkList.onRefreshComplete();
                    Toast.makeText(getContext(), "刷新完成ლ(⌒▽⌒ლ)~~", Toast.LENGTH_SHORT).show();
                    System.out.println("刷新完成");
                    break;

                case 45:
                    bundle = msg.getData();
                    ArrayList<TalkListItem> res2 = (ArrayList<TalkListItem>) bundle.getSerializable("list2");
                    if (res2.size() == 0) {
                        Toast.makeText(getContext(), "没有更多内容了ヾ(￣▽￣)Bye~Bye~", Toast.LENGTH_SHORT).show();
                        plvTalkList.onRefreshComplete();
                        break;
                    }
                    for (TalkListItem t : res2) {
//                        if (t.getType() != 1) {
                        talkListAdapter.add(t);
                        talkListAdapter.notifyDataSetChanged();
//                        } else {
//                            System.out.println("????????????????????????????????????????????");
//                        }
                    }
                    plvTalkList.onRefreshComplete();

                    break;
                default:

                    break;
            }
        }
    };

    class ChildThread extends Thread {

        @Override
        public void run() {
            ArrayList<TalkListItem> res = new ArrayList<>();
            try {
                res = new TalkGetProblemsInfo().getProblemList("", 0, 7);
                System.out.println("初始列表大小=" + res.size());
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("talk_load_count", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("cur_page", res.size());
                editor.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // 子线程执行完毕的地方，利用主线程的handler发送消息
            Message msg1 = new Message();
            Bundle bundle = new Bundle();
            bundle.putSerializable("list", res);
            msg1.what = 44;
            msg1.setData(bundle);
            uiHandler.sendMessage(msg1);

        }
    }

    class LoadMoreDataThread extends Thread {
        @Override
        public void run() {
            ArrayList<TalkListItem> res = new ArrayList<>();
            try {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("talk_load_count", Activity.MODE_PRIVATE);
                int cur_page = sharedPreferences.getInt("cur_page", 7);
                res = new TalkGetProblemsInfo().getProblemList("1", cur_page, 4);

                System.err.println("talk_cur_page=" + cur_page);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("cur_page", cur_page + 4);
                editor.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // 子线程执行完毕的地方，利用主线程的handler发送消息
            Message msg2 = new Message();
            Bundle bundle = new Bundle();
            bundle.putSerializable("list2", res);
            msg2.what = 45;
            msg2.setData(bundle);
            uiHandler.sendMessage(msg2);
//            pullToRefreshListView.onRefreshComplete();
        }
    }

    class UpdateSeesCount extends Thread{

        String title;
        int count;

        public UpdateSeesCount(String title,String count) {
            this.title=title;
            this.count=Integer.parseInt(count);
            this.count++;
        }

        @Override
        public void run() {
            Connection con = null;
            try {
                Class.forName("org.gjt.mm.mysql.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("1");
            }

            try {
                con = (Connection) DriverManager.getConnection("jdbc:mysql://123.206.50.61:3306/CPI", "root", "qq3199619");
                //获取问题列表
                System.out.println("获取评论列表");
                String sql = "UPDATE `talkProblem` SET `problemSeeCount`='"+count+"' WHERE `problemName` ='"+title+"'";
                System.out.println("更新文章《"+title+"》的回复数为"+count);
                Statement stmt = (Statement) con.createStatement();//创建Statement
                stmt.execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }



        }
    }

    private void initView() {
        plvTalkList.setMode(PullToRefreshBase.Mode.BOTH);
    }

    private void setOnClick() {
        plvTalkList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String str = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                if (refreshView.isShownHeader()) {
                    plvTalkList.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
                    plvTalkList.getLoadingLayoutProxy().setPullLabel("下拉刷新");
                    plvTalkList.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");
                    refreshView.getLoadingLayoutProxy().setLastUpdatedLabel("最后更新时间:" + str);
                    System.out.println("重新刷新");
                    new ChildThread().start();
                }

                if (refreshView.isShownFooter()) {
                    plvTalkList.getLoadingLayoutProxy().setRefreshingLabel("正在加载");
                    plvTalkList.getLoadingLayoutProxy().setPullLabel("上拉加载更多");
                    plvTalkList.getLoadingLayoutProxy().setReleaseLabel("释放开始加载");
                    refreshView.getLoadingLayoutProxy().setLastUpdatedLabel("最后加载时间:" + str);
                    System.out.println("加载更多");
                    new LoadMoreDataThread().start();
                }
            }
        });

        plvTalkList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TalkListItem talkListItem = (TalkListItem) talkListAdapter.getItem(position - 1);
                ArrayList<TalkListItem> arrayList = new ArrayList<>();
                arrayList.add(talkListItem);
//                TalkProblemInfo talkProblemInfo = talkListItem.getTalkProblemInfo();
//                System.out.println("进入详情前：" + talkProblemInfo.getProblemContent());
                Intent intent_to_details = new Intent(getActivity(), TalkProblemDetails.class);
                String title=talkListItem.getTalkProblemInfo().getProblemName();
                String count=talkListItem.getTalkProblemInfo().getProblemSeesCount();

                new UpdateSeesCount(title,count).start();

                Bundle bundle = new Bundle();
                bundle.putSerializable("problem_info", arrayList);
                intent_to_details.putExtras(bundle);
                startActivity(intent_to_details);

            }
        });

        plvTalkList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case SCROLL_STATE_IDLE:

                        fabTalkAddProblem.setAnimation(appearAnimation);
                        fabTalkAddProblem.setVisibility(View.VISIBLE);
                        break;
                    case  SCROLL_STATE_TOUCH_SCROLL:
                        fabTalkAddProblem.setAnimation(disappearAnimation);
                        fabTalkAddProblem.setVisibility(View.INVISIBLE);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.fab_talk_add_problem)
    public void onClick() {
        System.out.println("add_problem");
        Intent intent_to_edit_problem = new Intent(getActivity(), TalkEditProblem.class);
        startActivityForResult(intent_to_edit_problem, 444);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode){
            case 4441:
                System.out.println("提交问题后talk");

                break;
            default:
                System.out.println("what happen?talk");
                break;
        }
    }
}
