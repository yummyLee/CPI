package com.example.qq985.cpi.Function.Home;

import com.example.qq985.cpi.Function.CachedBitmap;

import java.io.Serializable;

/**
 * Created by qq985 on 2016/10/4.
 */

public class HomeViewPagerItem implements Serializable {

    private String str;
    private CachedBitmap cachedBitmap;

    public HomeViewPagerItem(String str, CachedBitmap cachedBitmap) {
        this.str = str;
        this.cachedBitmap = cachedBitmap;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public CachedBitmap getCachedBitmap() {
        return cachedBitmap;
    }

    public void setCachedBitmap(CachedBitmap cachedBitmap) {
        this.cachedBitmap = cachedBitmap;
    }
}
