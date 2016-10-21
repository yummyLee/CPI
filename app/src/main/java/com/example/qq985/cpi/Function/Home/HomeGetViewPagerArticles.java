package com.example.qq985.cpi.Function.Home;

import android.graphics.Bitmap;

import com.example.qq985.cpi.Function.CachedBitmap;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by qq985 on 2016/10/6.
 */

public class HomeGetViewPagerArticles {


    public HomeArticleInfo getArticles(String sql_s, String table, String title) throws SQLException {

        Connection con = null;
        con = (Connection) DriverManager.getConnection("jdbc:mysql://123.206.50.61:3306/CPI", "root", "qq3199619");


        //获取文章列表
        System.out.println("获取文章列表");
//        String sql = "SELECT * FROM homeArticle limit " + i + "," + j;//查询表名为“table_test”的所有内容
        String sql = "SELECT * FROM " + table + "WHERE title='" + title;//查询表名为“table_test”的所有内容
        Statement stmt = (Statement) con.createStatement();//创建Statement
        ResultSet rs = stmt.executeQuery(sql);
        System.out.println(rs.getRow());
        int rowCount = 0;
        HomeArticleInfo homeArticleInfo = new HomeArticleInfo();
        while (rs.next()) {

            homeArticleInfo.setTitle(rs.getString(2));
            homeArticleInfo.setAuthor(rs.getString(1));
            System.out.println(rs.getString(1));
            homeArticleInfo.setDate(rs.getString(3));
            homeArticleInfo.setvPPicUrl(rs.getString(4));
            homeArticleInfo.setArticleAbstract(rs.getString(5));
            homeArticleInfo.setAbstractSectionPicUrl(rs.getString(14));
            homeArticleInfo.setFirstSection(rs.getString(6));
            homeArticleInfo.setFirstSectionPicUrl(rs.getString(7));
            homeArticleInfo.setSecondSection(rs.getString(8));
            homeArticleInfo.setSecondSectionPicUrl(rs.getString(9));
            homeArticleInfo.setThirdSection(rs.getString(10));
            homeArticleInfo.setThirdSectionPicUrl(rs.getString(11));
            homeArticleInfo.setFourthSection(rs.getString(12));
            homeArticleInfo.setFourthSectionPicUrl(rs.getString(13));
            rowCount++;
        }
        System.out.println(rowCount);

        return homeArticleInfo;
    }

}
