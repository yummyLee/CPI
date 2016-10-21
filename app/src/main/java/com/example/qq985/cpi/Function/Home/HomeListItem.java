package com.example.qq985.cpi.Function.Home;

import java.io.Serializable;

/**
 * Created by qq985 on 2016/10/3.
 */

public class HomeListItem implements Serializable {

    public static final int TYPE_TEXT = 0;
    public static final int TYPE_VIEW_PAGER = 1;
    public static final int TYPE_ARTICLE = 2;
    public static final int TYPE_COUNT = 3;

    private String name;
    private int type;


    private HomeViewPagerItem[] homeViewPagerItems;

    private HomeArticleInfo homeArticleInfo;

    public HomeViewPagerItem[] getHomeViewPagerItems() {
        return homeViewPagerItems;
    }

    public HomeArticleInfo getHomeArticleInfo() {
        return homeArticleInfo;
    }

    public void setHomeArticleInfo(HomeArticleInfo homeArticleInfo) {
        this.homeArticleInfo = homeArticleInfo;
    }

    public HomeListItem(int type, String name, HomeViewPagerItem[] homeViewPagerItems, HomeArticleInfo homeArticleInfo) {
        this.type = type;
        this.name = name;
        this.homeArticleInfo = homeArticleInfo;
        this.homeViewPagerItems = homeViewPagerItems;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
