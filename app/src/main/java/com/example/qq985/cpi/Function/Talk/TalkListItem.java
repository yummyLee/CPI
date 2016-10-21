package com.example.qq985.cpi.Function.Talk;

import java.io.Serializable;

/**
 * Created by qq985 on 2016/10/5.
 */

public class TalkListItem implements Serializable {

    public static final int TYPE_TEXT = 0;
    public static final int TYPE_PROBLEM = 1;
    public static final int TYPE_CONTENT = 2;
    public static final int TYPE_COMMENT = 3;

    private String name;
    private int type;

    private TalkProblemInfo talkProblemInfo;
    private TalkProblemDetailsCommentInfo talkProblemDetailsCommentInfo;
    private TalkProblemDetailsContent talkProblemDetailsContent;


    public TalkListItem(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public TalkProblemInfo getTalkProblemInfo() {
        return talkProblemInfo;
    }

    public void setTalkProblemInfo(TalkProblemInfo talkProblemInfo) {
        this.talkProblemInfo = talkProblemInfo;
    }

    public TalkProblemDetailsCommentInfo getTalkProblemDetailsCommentInfo() {
        return talkProblemDetailsCommentInfo;
    }

    public void setTalkProblemDetailsCommentInfo(TalkProblemDetailsCommentInfo talkProblemDetailsCommentInfo) {
        this.talkProblemDetailsCommentInfo = talkProblemDetailsCommentInfo;
    }

    public TalkProblemDetailsContent getTalkProblemDetailsContent() {
        return talkProblemDetailsContent;
    }

    public void setTalkProblemDetailsContent(TalkProblemDetailsContent talkProblemDetailsContent) {
        this.talkProblemDetailsContent = talkProblemDetailsContent;
    }
}
