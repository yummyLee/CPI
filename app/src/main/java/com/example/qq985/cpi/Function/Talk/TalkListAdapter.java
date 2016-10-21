package com.example.qq985.cpi.Function.Talk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qq985.cpi.Function.XCRoundImageView;
import com.example.qq985.cpi.R;

import java.io.Serializable;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by qq985 on 2016/10/5.
 */

public class TalkListAdapter extends BaseAdapter {


    private List<TalkListItem> talkListItemList;
    private Context ta_context;

    public TalkListAdapter(List<TalkListItem> talkListItemList, Context ta_context) {
        this.talkListItemList = talkListItemList;
        this.ta_context = ta_context;
    }


    @Override
    public int getCount() {
        return talkListItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return talkListItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (talkListItemList != null && position < talkListItemList.size()) {
            return talkListItemList.get(position).getType();
        }
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        int type = getItemViewType(position);
        switch (type) {

            case TalkListItem.TYPE_PROBLEM:
                TalkProblemInfoViewHolder talkProblemInfoViewHolder;
                if (convertView == null) {
                    convertView = LayoutInflater.from(ta_context).
                            inflate(R.layout.talk_list_cell, null);

                    talkProblemInfoViewHolder = new TalkProblemInfoViewHolder(convertView);


                    talkProblemInfoViewHolder.tvTalkProblemName
                            .setText(talkListItemList.get(position).getTalkProblemInfo().getProblemName());
                    talkProblemInfoViewHolder.tvTalkAskerName
                            .setText(talkListItemList.get(position).getTalkProblemInfo().getProblemAsker());
                    talkProblemInfoViewHolder.tvProblemSeesCount
                            .setText(talkListItemList.get(position).getTalkProblemInfo().getProblemSeesCount());
                    talkProblemInfoViewHolder.tvProblemReplyCount
                            .setText(talkListItemList.get(position).getTalkProblemInfo().getProblemReplyCount());
                    talkProblemInfoViewHolder.tvTalkProblemDate
                            .setText(talkListItemList.get(position).getTalkProblemInfo().getProblemDate());
                    talkProblemInfoViewHolder.tvTalkProblemType
                            .setText(talkListItemList.get(position).getTalkProblemInfo().getProblemType());


                    convertView.setTag(talkProblemInfoViewHolder);

                } else {
                    talkProblemInfoViewHolder = (TalkProblemInfoViewHolder) convertView.getTag();

                    talkProblemInfoViewHolder.tvTalkProblemName
                            .setText(talkListItemList.get(position).getTalkProblemInfo().getProblemName());
                    talkProblemInfoViewHolder.tvTalkAskerName
                            .setText(talkListItemList.get(position).getTalkProblemInfo().getProblemAsker());
                    talkProblemInfoViewHolder.tvProblemSeesCount
                            .setText(talkListItemList.get(position).getTalkProblemInfo().getProblemSeesCount());
                    talkProblemInfoViewHolder.tvProblemReplyCount
                            .setText(talkListItemList.get(position).getTalkProblemInfo().getProblemReplyCount());
                    talkProblemInfoViewHolder.tvTalkProblemDate
                            .setText(talkListItemList.get(position).getTalkProblemInfo().getProblemDate());
                    talkProblemInfoViewHolder.tvTalkProblemType
                            .setText(talkListItemList.get(position).getTalkProblemInfo().getProblemType());
                }
                break;
            case TalkListItem.TYPE_CONTENT:
                ContentViewHolder contentViewHolder = new ContentViewHolder();
//                if (convertView == null) {
                convertView = LayoutInflater.from(ta_context).
                        inflate(R.layout.tpd_concent_cell, null);
                contentViewHolder.tvTpdProblemContent = (TextView) convertView.findViewById(R.id.tv_tpd_problem_content);
                contentViewHolder.tvTpdProblemContent
                        .setText(talkListItemList.get(position).getTalkProblemDetailsContent().getContent());

//                } else {
//                    contentViewHolder = (ContentViewHolder) convertView.getTag();
//                    contentViewHolder.tvTpdProblemContent
//                            .setText(talkListItemList.get(position).getTalkProblemDetailsContent().getContent());
//
//                }
                break;
            case TalkListItem.TYPE_COMMENT:
                CommentViewHolder commentViewHolder = new CommentViewHolder();
//                if (convertView == null) {

                convertView = LayoutInflater.from(ta_context).
                        inflate(R.layout.tpd_comment_cell, null);

                commentViewHolder.tvTpdCommentDate = (TextView) convertView.findViewById(R.id.tv_tpd_comment_date);
                commentViewHolder.tvTpdCommenterName = (TextView) convertView.findViewById(R.id.tv_tpd_commenter_name);
                commentViewHolder.tvTpdCommentContent = (TextView) convertView.findViewById(R.id.tv_tpd_comment_content);

                commentViewHolder.tvTpdCommentContent
                        .setText(talkListItemList.get(position).getTalkProblemDetailsCommentInfo().getProblemCommentContent());
                commentViewHolder.tvTpdCommentDate
                        .setText(talkListItemList.get(position).getTalkProblemDetailsCommentInfo().getProblemCommentDate());
                commentViewHolder.tvTpdCommenterName
                        .setText(talkListItemList.get(position).getTalkProblemDetailsCommentInfo().getProblemCommenter());

//                } else {
//
//                    commentViewHolder = (CommentViewHolder) convertView.getTag();
//                    commentViewHolder.tvTpdCommentContent
//                            .setText(talkListItemList.get(position).getTalkProblemDetailsCommentInfo().getProblemCommentContent());
//                    commentViewHolder.tvTpdCommentDate
//                            .setText(talkListItemList.get(position).getTalkProblemDetailsCommentInfo().getProblemCommentContent());
//                    commentViewHolder.tvTpdCommenterName
//                            .setText(talkListItemList.get(position).getTalkProblemDetailsCommentInfo().getProblemCommenter());
//
//
//                }

        }
        return convertView;
    }


    public void add(TalkListItem info) {
        talkListItemList.add(info);
    }


    static class TalkProblemInfoViewHolder implements Serializable {
        @Bind(R.id.iv_talk_asker_header)
        XCRoundImageView ivTalkAskerHeader;
        @Bind(R.id.tv_talk_asker_name)
        TextView tvTalkAskerName;
        @Bind(R.id.iv_talk_reply_count)
        ImageView ivTalkReplyCount;
        @Bind(R.id.iv_talk_sees_count)
        ImageView ivTalkSeesCount;
        @Bind(R.id.tv_problem_sees_count)
        TextView tvProblemSeesCount;
        @Bind(R.id.tv_problem_reply_count)
        TextView tvProblemReplyCount;
        @Bind(R.id.tv_talk_problem_date)
        TextView tvTalkProblemDate;
        @Bind(R.id.tv_talk_problem_name)
        TextView tvTalkProblemName;
        @Bind(R.id.tv_talk_problem_type)
        TextView tvTalkProblemType;

        TalkProblemInfoViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ContentViewHolder {

        TextView tvTpdProblemContent;

    }


    static class CommentViewHolder {

        XCRoundImageView ivTpdCommenterHeader;
        TextView tvTpdCommenterName;
        TextView tvTpdCommentDate;
        TextView tvTpdCommentContent;

    }
}
