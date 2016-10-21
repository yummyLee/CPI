package com.example.qq985.cpi.Function.Talk;

import java.io.Serializable;

/**
 * Created by qq985 on 2016/10/5.
 */

public class TalkProblemInfo implements Serializable {

    String problemName;
    String problemType;
    String problemAsker;
    String problemDate;
    String problemReplyCount;
    String problemSeesCount;
    String problemLatestReplyDate;
    String problemContent;

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType;
    }

    public String getProblemAsker() {
        return problemAsker;
    }

    public void setProblemAsker(String problemAsker) {
        this.problemAsker = problemAsker;
    }

    public String getProblemDate() {
        return problemDate;
    }

    public void setProblemDate(String problemDate) {
        this.problemDate = problemDate;
    }

    public String getProblemReplyCount() {
        return problemReplyCount;
    }

    public void setProblemReplyCount(String problemReplyCount) {
        this.problemReplyCount = problemReplyCount;
    }

    public String getProblemSeesCount() {
        return problemSeesCount;
    }

    public void setProblemSeesCount(String problemSeesCount) {
        this.problemSeesCount = problemSeesCount;
    }

    public String getProblemLatestReplyDate() {
        return problemLatestReplyDate;
    }

    public void setProblemLatestReplyDate(String problemLatestReplyDate) {
        this.problemLatestReplyDate = problemLatestReplyDate;
    }

    public String getProblemContent() {
        return problemContent;
    }

    public void setProblemContent(String problemContent) {
        this.problemContent = problemContent;
    }
}
