package com.example.qq985.cpi.Function.Home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.example.qq985.cpi.Function.CachedBitmap;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by qq985 on 2016/10/3.
 */

public class HomeGetArticlesItem {

    public ArrayList<HomeListItem> getArticleList(String sql_s, String table, int i, int j) throws SQLException {

        ArrayList<HomeListItem> res = new ArrayList<>();
        Connection con = null;


        try {
            Class.forName("org.gjt.mm.mysql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("1");
        }

        con = (Connection) DriverManager.getConnection("jdbc:mysql://123.206.50.61:3306/CPI", "root", "qq3199619");

        if (sql_s.equals("0")) {
            //获取ViewPager图片
            String vp_sql = "SELECT * FROM homeViewPager";
            Statement vp_stmt = (Statement) con.createStatement();//创建Statement
            ResultSet vp_rs = vp_stmt.executeQuery(vp_sql);
            HomeViewPagerItem[] homeViewPagerItems = new HomeViewPagerItem[4];
            int vpCount = 0;
            while (vp_rs.next()) {
                System.out.println("图片地址");
                Bitmap bbitmap = returnBitMap(vp_rs.getString(2));
                CachedBitmap cachedBitmap = new CachedBitmap(bbitmap, null, null);
                homeViewPagerItems[vpCount] = new HomeViewPagerItem(vp_rs.getString(1), cachedBitmap);
                System.out.println(vp_rs.getString(1));
                System.out.println("完成一条");
                vpCount++;
            }
            HomeListItem vpHomeListItem = new HomeListItem(1, "", homeViewPagerItems, null);
            res.add(vpHomeListItem);
        }

        //获取文章列表
        System.out.println("获取文章列表");
//        String sql = "SELECT * FROM homeArticle limit " + i + "," + j;//查询表名为“table_test”的所有内容
        String sql = "SELECT * FROM " + table + " limit " + i + "," + j;//查询表名为“table_test”的所有内容
        Statement stmt = (Statement) con.createStatement();//创建Statement
        ResultSet rs = stmt.executeQuery(sql);
        System.out.println(rs.getRow());
        int rowCount = 0;
        while (rs.next()) {
            System.out.println("图片地址");
            Bitmap bbitmap = returnBitMap(rs.getString(4));
            System.out.println("1");
            CachedBitmap cachedBitmap = new CachedBitmap(bbitmap, null, null);
            HomeArticleInfo homeArticleInfo = new HomeArticleInfo();
            homeArticleInfo.setCachedBitmap(cachedBitmap);
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
            res.add(new HomeListItem(2, "", null, homeArticleInfo));
            System.out.println("完成一条");
        }
        System.out.println(rowCount);

        return res;
    }


    public static Bitmap returnBitMap(String url) {
        System.out.println("url======" + url);
        URL myFileUrl = null;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
//        System.out.println("01");
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
//            System.out.println("010");
            conn.setDoInput(true);
//            conn.setReadTimeout(5*1000);
            conn.setConnectTimeout(2 * 1000);
            conn.connect();
//            System.out.println("011");
            InputStream is = conn.getInputStream();
//            System.out.println("012");
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }


    public static Bitmap small(Bitmap bitmap) {
        Matrix matrix = new Matrix();
//        matrix.postScale(0.3f,0.3f); //长和宽放大缩小的比例
        matrix.postScale(1.5f, 1.5f); //长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizeBmp;
    }

}
