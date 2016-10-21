package com.example.qq985.cpi.Function.Talk;

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
 * Created by qq985 on 2016/10/5.
 */

public class TalkGetProblemsInfo {

    public ArrayList<TalkListItem> getProblemList(String sql_s, int i, int j) throws SQLException {

        ArrayList<TalkListItem> res = new ArrayList<>();
        Connection con = null;


        try {
            Class.forName("org.gjt.mm.mysql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("1");
        }

        con = (Connection) DriverManager.getConnection("jdbc:mysql://123.206.50.61:3306/CPI", "root", "qq3199619");


        //获取问题列表
        System.out.println("获取问题列表");
        String sql = "SELECT * FROM talkProblem  ORDER BY`problemDate` DESC limit " + i + "," + j;
        Statement stmt = (Statement) con.createStatement();//创建Statement
        ResultSet rs = stmt.executeQuery(sql);
        System.out.println(rs.getRow());
        int rowCount = 0;
        while (rs.next()) {

            TalkProblemInfo talkProblemInfo = new TalkProblemInfo();
            talkProblemInfo.setProblemName(rs.getString(1));
            talkProblemInfo.setProblemType(rs.getString(2));
            talkProblemInfo.setProblemAsker(rs.getString(3));
            talkProblemInfo.setProblemDate(rs.getString(4));
            talkProblemInfo.setProblemReplyCount(rs.getString(5));
            talkProblemInfo.setProblemSeesCount(rs.getString(6));
            talkProblemInfo.setProblemLatestReplyDate(rs.getString(7));
            talkProblemInfo.setProblemContent(rs.getString(8));
            rowCount++;
            TalkListItem talkListItem=new TalkListItem("", 1);
            talkListItem.setTalkProblemInfo(talkProblemInfo);
            res.add(talkListItem);
            System.out.println("完成一条问题的获取");
        }
        System.out.println(rowCount);

        return res;
    }


}
