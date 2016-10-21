package com.example.qq985.cpi.Function;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.qq985.cpi.ACache;
import com.example.qq985.cpi.Function.Home.HomeArticleDetails;
import com.example.qq985.cpi.Function.Home.HomeArticleInfo;
import com.example.qq985.cpi.Function.Home.HomeGetArticlesItem;
import com.example.qq985.cpi.Function.Home.HomeListAdapter;
import com.example.qq985.cpi.Function.Home.HomeListItem;
import com.example.qq985.cpi.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.sql.SQLException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class Hot extends Fragment {

    @Bind(R.id.plv_hot)
    PullToRefreshListView plvHot;
    @Bind(R.id.pb_hot_refresh)
    ProgressBar pbHotRefresh;
    private View view;
    private HomeListAdapter hotListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_hot, container, false);
        ButterKnife.bind(this, view);
        initView();
        setOnClick();
        pbHotRefresh.setVisibility(View.VISIBLE);
        new Thread(loadFirstData).start();
        return view;
    }

    Handler uiHandler = new Handler() {
        // 覆写这个方法，接收并处理消息。
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 71:
                    Bundle bundle = msg.getData();
                    ArrayList<HomeListItem> res = (ArrayList<HomeListItem>) bundle.getSerializable("list");

//                    ACache aCache = ACache.get(getContext());
//                    aCache.put("ac_list1", res);

                    hotListAdapter = new HomeListAdapter(res, getContext());
                    plvHot.setAdapter(hotListAdapter);
                    pbHotRefresh.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "刷新完成ლ(⌒▽⌒ლ)~~", Toast.LENGTH_SHORT).show();
                    plvHot.onRefreshComplete();
                    break;
                case 72:
                    bundle = msg.getData();
                    ArrayList<HomeListItem> res2 = (ArrayList<HomeListItem>) bundle.getSerializable("list2");
                    if (res2.size() == 0) {
                        plvHot.onRefreshComplete();
                        Toast.makeText(getContext(), "没有更多内容了ヾ(￣▽￣)Bye~Bye~", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    for (HomeListItem t : res2) {
//                        if (t.getType() != 1) {
                        hotListAdapter.add(t);
                        hotListAdapter.notifyDataSetChanged();
//                        } else {
//                            System.out.println("????????????????????????????????????????????");
//                        }
                    }
                    plvHot.onRefreshComplete();

                    break;
                case 73:
                    bundle = msg.getData();
                    ArrayList<HomeListItem> res3 = (ArrayList<HomeListItem>) bundle.getSerializable("hot_list_cache");
                    if (res3.size() == 0) {
                        pbHotRefresh.setVisibility(View.GONE);
                        Toast.makeText(getContext(), "数据加载失败ヾ(ﾟ∀ﾟゞ)", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    hotListAdapter = new HomeListAdapter(res3, getContext());
                    plvHot.setAdapter(hotListAdapter);
                    pbHotRefresh.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "数据加载完成ლ(⌒▽⌒ლ)~~", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(getContext(), "未知错误X_X", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    //加载第一次的数据
    Runnable loadFirstData = new Runnable() {
        @Override
        public void run() {
            System.out.println("Hot第一次加载数据");
            ACache aCache = ACache.get(getContext());
            ArrayList<HomeListItem> hs = new ArrayList<>();
            hs = (ArrayList<HomeListItem>) aCache.getAsObject("hot_list_cache");
            if (hs!=null) {
                System.out.println("Hot缓存加载数据");

                Bundle bundle = new Bundle();
                bundle.putSerializable("hot_list_cache", hs);
                Message msg = new Message();
                msg.setData(bundle);
                msg.what = 73;
                uiHandler.sendMessage(msg);

            } else {
                System.out.println("Hot联网加载数据");
                new ChildThread().start();
            }
        }
    };



    class ChildThread extends Thread {
        @Override
        public void run() {

            ArrayList<HomeListItem> res = new ArrayList<>();
            try {
                res = new HomeGetArticlesItem().getArticleList("1", "hotArticle", 0, 4);
                ACache aCache = ACache.get(getActivity().getBaseContext());
                aCache.put("hot_list_cache", res, 30000);
                System.out.println("初始列表大小=" + res.size());
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("hot_load_count", Activity.MODE_PRIVATE);
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
            msg1.what = 71;
            msg1.setData(bundle);
            uiHandler.sendMessage(msg1);
//            plvHot.onRefreshComplete();
        }
    }

    class LoadMoreDataThread extends Thread {
        @Override
        public void run() {
            ArrayList<HomeListItem> res = new ArrayList<>();
            try {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("hot_load_count", Activity.MODE_PRIVATE);
                int cur_page = sharedPreferences.getInt("cur_page", 4);
                res = new HomeGetArticlesItem().getArticleList("1", "hotArticle", cur_page, 4);

                System.err.println("cur_page=" + cur_page);
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
            msg2.what = 72;
            msg2.setData(bundle);
            uiHandler.sendMessage(msg2);
//            plvHot.onRefreshComplete();
        }
    }

    private void initView() {
        plvHot.setMode(PullToRefreshBase.Mode.BOTH);
    }

    private void setOnClick() {
        plvHot.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String str = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                if (refreshView.isShownHeader()) {
                    plvHot.getLoadingLayoutProxy().setRefreshingLabel("正在刷新");
                    plvHot.getLoadingLayoutProxy().setPullLabel("下拉刷新");
                    plvHot.getLoadingLayoutProxy().setReleaseLabel("释放开始刷新");
                    refreshView.getLoadingLayoutProxy().setLastUpdatedLabel("最后更新时间:" + str);

                    new ChildThread().start();
                    System.out.println("重新刷新");
                }

                if (refreshView.isShownFooter()) {
                    plvHot.getLoadingLayoutProxy().setRefreshingLabel("正在加载");
                    plvHot.getLoadingLayoutProxy().setPullLabel("上拉加载更多");
                    plvHot.getLoadingLayoutProxy().setReleaseLabel("释放开始加载");
                    refreshView.getLoadingLayoutProxy().setLastUpdatedLabel("最后加载时间:" + str);
                    System.out.println("加载更多");
                    new LoadMoreDataThread().start();
                }
            }
        });

        plvHot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HomeListItem homeListItem = (HomeListItem) hotListAdapter.getItem(position - 1);
                HomeArticleInfo homeArticleInfo = homeListItem.getHomeArticleInfo();
                Intent intent_to_article_details = new Intent(getActivity(), HomeArticleDetails.class);
                Bundle bundle = new Bundle();
                bundle.putString("author", homeArticleInfo.getAuthor());
                bundle.putString("title", homeArticleInfo.getTitle());
                bundle.putString("date", homeArticleInfo.getDate());
                bundle.putString("abstract", homeArticleInfo.getArticleAbstract());
                bundle.putString("abstractSectionPicUrl", homeArticleInfo.getAbstractSectionPicUrl());
                bundle.putString("firstSection", homeArticleInfo.getFirstSection());
                bundle.putString("firstSectionPicUrl", homeArticleInfo.getFirstSectionPicUrl());
                bundle.putString("secondSection", homeArticleInfo.getSecondSection());
                bundle.putString("secondSectionPicUrl", homeArticleInfo.getSecondSectionPicUrl());
                bundle.putString("thirdSection", homeArticleInfo.getThirdSection());
                bundle.putString("thirdSectionPicUrl", homeArticleInfo.getThirdSectionPicUrl());
                bundle.putString("fourthSection", homeArticleInfo.getFourthSection());
                bundle.putString("fourthSectionPicUrl", homeArticleInfo.getFourthSectionPicUrl());
                intent_to_article_details.putExtras(bundle);
                startActivity(intent_to_article_details);

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
