package com.example.qq985.cpi.Function.Talk;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by qq985 on 2016/10/5.
 */

public class TalkGetProblemComments {


    public ArrayList<TalkListItem> getProblemComments(TalkProblemInfo talkProblemInfo, String tag, int i, int j) throws SQLException {

        ArrayList<TalkListItem> res = new ArrayList<>();


        if (tag.equals("0")) {
            TalkProblemDetailsContent talkProblemDetailsContent = new TalkProblemDetailsContent();
            talkProblemDetailsContent.setContent(talkProblemInfo.getProblemContent());
            TalkListItem talkListItem_content = new TalkListItem(",", 2);
            talkListItem_content.setTalkProblemDetailsContent(talkProblemDetailsContent);
//        System.out.println("问题详情：" + talkListItem_content.getTalkProblemInfo().getProblemContent());
            res.add(talkListItem_content);
        }

        Connection con = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("1");
        }

        con = (Connection) DriverManager.getConnection("jdbc:mysql://123.206.50.61:3306/CPI", "root", "qq3199619");


        //获取问题列表
        System.out.println("获取评论列表");
        String sql = "SELECT * FROM talkComment WHERE problemName='" + talkProblemInfo.getProblemName() + "' ORDER BY`problemCommentDate` DESC limit " + i + "," + j;
        Statement stmt = (Statement) con.createStatement();//创建Statement
        ResultSet rs = stmt.executeQuery(sql);
        System.out.println(rs.getRow());
        int rowCount = 0;
        while (rs.next()) {

            TalkProblemDetailsCommentInfo commentInfo = new TalkProblemDetailsCommentInfo();
            commentInfo.setProblemName(rs.getString(1));
            commentInfo.setProblemCommenter(rs.getString(2));
            commentInfo.setProblemCommentDate(rs.getString(3));
            commentInfo.setProblemCommentContent(rs.getString(4));
            TalkListItem talkListItem = new TalkListItem("", 3);
            talkListItem.setTalkProblemDetailsCommentInfo(commentInfo);
            rowCount++;
            res.add(talkListItem);
            System.out.println("完成一条评论的获取");
        }
        System.out.println(rowCount);

        return res;
    }

}
