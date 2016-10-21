package com.example.qq985.cpi.Function.Home;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.qq985.cpi.Function.CachedBitmap;

import java.io.Serializable;

/**
 * Created by qq985 on 2016/10/3.
 */
//home文章的对象
public class HomeArticleInfo implements Serializable {

    private String author;
    private String title;
    private String date;
    private String articleAbstract;
    private String abstractSectionPicUrl;
    private String vPPicUrl;
    private CachedBitmap cachedBitmap;
    private String firstSection;
    private String firstSectionPicUrl;
    private String secondSection;
    private String secondSectionPicUrl;
    private String thirdSection;
    private String thirdSectionPicUrl;
    private String fourthSection;
    private String fourthSectionPicUrl;

//    public HomeArticleInfo(String author, String title, String articleAbstract, String abstractSectionPicUrl, String picUrl, CachedBitmap cachedBitmap, String firstSection, String firstSectionPicUrl, String secondSection, String secondSectionPicUrl, String thirdSection, String thirdSectionPicUrl, String fourthSection, String fourthSectionPicUrl) {
//        this.author = author;
//        this.title = title;
//        this.articleAbstract = articleAbstract;
//        this.abstractSectionPicUrl = abstractSectionPicUrl;
//        this.picUrl = picUrl;
//        this.cachedBitmap = cachedBitmap;
//        this.firstSection = firstSection;
//        this.firstSectionPicUrl = firstSectionPicUrl;
//        this.secondSection = secondSection;
//        this.secondSectionPicUrl = secondSectionPicUrl;
//        this.thirdSection = thirdSection;
//        this.thirdSectionPicUrl = thirdSectionPicUrl;
//        this.fourthSection = fourthSection;
//        this.fourthSectionPicUrl = fourthSectionPicUrl;
//    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getArticleAbstract() {
        return articleAbstract;
    }

    public void setArticleAbstract(String articleAbstract) {
        this.articleAbstract = articleAbstract;
    }

    public String getAbstractSectionPicUrl() {
        return abstractSectionPicUrl;
    }

    public void setAbstractSectionPicUrl(String abstractSectionPicUrl) {
        this.abstractSectionPicUrl = abstractSectionPicUrl;
    }

    public String getvPPicUrl() {
        return vPPicUrl;
    }

    public void setvPPicUrl(String vPPicUrl) {
        this.vPPicUrl = vPPicUrl;
    }

    public CachedBitmap getCachedBitmap() {
        return cachedBitmap;
    }

    public void setCachedBitmap(CachedBitmap cachedBitmap) {
        this.cachedBitmap = cachedBitmap;
    }

    public String getFirstSection() {
        return firstSection;
    }

    public void setFirstSection(String firstSection) {
        this.firstSection = firstSection;
    }

    public String getFirstSectionPicUrl() {
        return firstSectionPicUrl;
    }

    public void setFirstSectionPicUrl(String firstSectionPicUrl) {
        this.firstSectionPicUrl = firstSectionPicUrl;
    }

    public String getSecondSection() {
        return secondSection;
    }

    public void setSecondSection(String secondSection) {
        this.secondSection = secondSection;
    }

    public String getSecondSectionPicUrl() {
        return secondSectionPicUrl;
    }

    public void setSecondSectionPicUrl(String secondSectionPicUrl) {
        this.secondSectionPicUrl = secondSectionPicUrl;
    }

    public String getThirdSection() {
        return thirdSection;
    }

    public void setThirdSection(String thirdSection) {
        this.thirdSection = thirdSection;
    }

    public String getThirdSectionPicUrl() {
        return thirdSectionPicUrl;
    }

    public void setThirdSectionPicUrl(String thirdSectionPicUrl) {
        this.thirdSectionPicUrl = thirdSectionPicUrl;
    }

    public String getFourthSection() {
        return fourthSection;
    }

    public void setFourthSection(String fourthSection) {
        this.fourthSection = fourthSection;
    }

    public String getFourthSectionPicUrl() {
        return fourthSectionPicUrl;
    }

    public void setFourthSectionPicUrl(String fourthSectionPicUrl) {
        this.fourthSectionPicUrl = fourthSectionPicUrl;
    }

}
