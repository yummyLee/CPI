package com.example.qq985.cpi.Function.Home;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qq985.cpi.ACache;
import com.example.qq985.cpi.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeArticleDetails extends AppCompatActivity {


    @Bind(R.id.iv_had_first_pic)
    ImageView ivHadFirstPic;
    @Bind(R.id.tv_had_title)
    TextView tvHadTitle;
    @Bind(R.id.tv_had_date)
    TextView tvHadDate;
    @Bind(R.id.tv_had_author)
    TextView tvHadAuthor;
    @Bind(R.id.tv_had_abstract)
    TextView tvHadAbstract;
    @Bind(R.id.iv_had_second_pic)
    ImageView ivHadSecondPic;
    @Bind(R.id.tv_had_first_section)
    TextView tvHadFirstSection;
    @Bind(R.id.iv_had_third_pic)
    ImageView ivHadThirdPic;
    @Bind(R.id.tv_had_second_section)
    TextView tvHadSecondSection;
    @Bind(R.id.iv_had_fourth_pic)
    ImageView ivHadFourthPic;
    @Bind(R.id.tv_had_third_section)
    TextView tvHadThirdSection;
    @Bind(R.id.iv_had_fifth_pic)
    ImageView ivHadFifthPic;
    @Bind(R.id.tv_had_fourth_section)
    TextView tvHadFourthSection;
    @Bind(R.id.iv_had_back)
    ImageView ivHadBack;
    @Bind(R.id.iv_had_clct)
    ImageView ivHadClct;
    @Bind(R.id.iv_had_thumb)
    ImageView ivHadThumb;
    @Bind(R.id.iv_had_comment)
    ImageView ivHadComment;
    @Bind(R.id.iv_had_share)
    ImageView ivHadShare;
    @Bind(R.id.activity_home_article_details)
    RelativeLayout activityHomeArticleDetails;
    private String[] picUrls;
    private String title;
    int clct_count = 0;
    int thumb_count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_article_details);
        picUrls = new String[6];
        ButterKnife.bind(this);
        new SetArticleViewThread().start();
    }

    Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //加载文章列表的信息
                case 33:
                    Bundle bundle = msg.getData();
                    title = bundle.getString("title");
                    tvHadTitle.setText(title);
                    tvHadAbstract.setText(bundle.getString("abstract"));
                    tvHadAuthor.setText(bundle.getString("author"));
                    tvHadDate.setText(bundle.getString("date"));
                    tvHadFirstSection.setText(bundle.getString("firstSection"));
                    tvHadSecondSection.setText(bundle.getString("secondSection"));
                    tvHadThirdSection.setText(bundle.getString("thirdSection"));
                    tvHadFourthSection.setText(bundle.getString("fourthSection"));
            }
        }
    };

    @OnClick({R.id.iv_had_back, R.id.iv_had_clct, R.id.iv_had_thumb, R.id.iv_had_comment, R.id.iv_had_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_had_back:
                finish();
                break;
            case R.id.iv_had_clct:
                System.out.println("文章收藏");
                if (clct_count % 2 == 0) {
                    ivHadClct.setImageResource(R.drawable.collect_down);
                } else {
                    ivHadClct.setImageResource(R.drawable.uncollect_down);
                }
                clct_count++;
                Toast.makeText(this,"暂时不能收藏哦ヾ(･ω･`｡)",Toast.LENGTH_SHORT);
                break;
            case R.id.iv_had_thumb:
                Toast.makeText(this,"暂时不能点赞哦ヾ(･ω･`｡)",Toast.LENGTH_SHORT);
                if (thumb_count % 2 == 0) {
                    ivHadThumb.setImageResource(R.drawable.collected_down);
                } else {
                    ivHadThumb.setImageResource(R.drawable.collecting_down);
                }
                thumb_count++;
                break;
            case R.id.iv_had_comment:
                Toast.makeText(this,"暂时不能评论哦ヾ(･ω･`｡)",Toast.LENGTH_SHORT);
                break;
            case R.id.iv_had_share:
                Toast.makeText(this,"暂时不能分享哦ヾ(･ω･`｡)",Toast.LENGTH_SHORT);
                break;
        }
    }

    class SetArticleViewThread extends Thread {
        @Override
        public void run() {
            Intent intent_from_list = getIntent();
            Bundle bundle = intent_from_list.getExtras();
            Message msg = new Message();
            //加载来自列表的文章信息
            msg.what = 33;
            msg.setData(bundle);
            uiHandler.sendMessage(msg);
            picUrls[0] = bundle.getString("abstractSectionPicUrl");
            picUrls[1] = bundle.getString("firstSectionPicUrl");
            picUrls[2] = bundle.getString("secondSectionPicUrl");
            picUrls[3] = bundle.getString("thirdSectionPicUrl");
            picUrls[4] = bundle.getString("fourthSectionPicUrl");
            picUrls[5] = bundle.getString("title");
            System.out.println(picUrls[0]);
            System.out.println(picUrls[1]);
            System.out.println(picUrls[2]);
            System.out.println(picUrls[3]);
            System.out.println(picUrls[4]);

            new LoadImage().execute(picUrls);
        }
    }

    class LoadImage extends AsyncTask<String, Void, Bitmap[]> {

        @Override
        protected void onPostExecute(Bitmap[] cachedBitmaps) {
            ivHadFirstPic.setImageBitmap(cachedBitmaps[0]);
            ivHadSecondPic.setImageBitmap(cachedBitmaps[1]);
            ivHadThirdPic.setImageBitmap(cachedBitmaps[2]);
            ivHadFourthPic.setImageBitmap(cachedBitmaps[3]);
            ivHadFifthPic.setImageBitmap(cachedBitmaps[4]);
        }


        @Override
        protected Bitmap[] doInBackground(String... params) {

            Bitmap[] cachedBitmaps = new Bitmap[5];
            ACache aCache = ACache.get(getBaseContext());
            String is_article_cached = aCache.getAsString(params[5] + "article_bitmaps_is_cached");
            if (is_article_cached != null) {
                if (is_article_cached.equals("yes")) {
                    cachedBitmaps[0] = aCache.getAsBitmap(params[5] + "article_bitmaps0");
                    cachedBitmaps[1] = aCache.getAsBitmap(params[5] + "article_bitmaps1");
                    cachedBitmaps[2] = aCache.getAsBitmap(params[5] + "article_bitmaps2");
                    cachedBitmaps[3] = aCache.getAsBitmap(params[5] + "article_bitmaps3");
                    cachedBitmaps[4] = aCache.getAsBitmap(params[5] + "article_bitmaps4");
                    System.out.println("加载缓存图片" + params[5]);
                    return cachedBitmaps;
                }
            }

            cachedBitmaps[0] = HomeGetArticlesItem.returnBitMap(params[0]);
            cachedBitmaps[1] = HomeGetArticlesItem.returnBitMap(params[1]);
            cachedBitmaps[2] = HomeGetArticlesItem.returnBitMap(params[2]);
            cachedBitmaps[3] = HomeGetArticlesItem.returnBitMap(params[3]);
            cachedBitmaps[4] = HomeGetArticlesItem.returnBitMap(params[4]);

            Resources res=getResources();
            if (cachedBitmaps[0]==null){
                cachedBitmaps[0]= BitmapFactory.decodeResource(res, R.drawable.pic_test2);
            }
            for (int i=1;i<5;i++){
                if (cachedBitmaps[i]==null){
                    cachedBitmaps[i]= BitmapFactory.decodeResource(res, R.drawable.pictest);
                }
            }

            aCache.put(params[5] + "article_bitmaps0", cachedBitmaps[0], 30000);
            aCache.put(params[5] + "article_bitmaps1", cachedBitmaps[1], 30000);
            aCache.put(params[5] + "article_bitmaps2", cachedBitmaps[2], 30000);
            aCache.put(params[5] + "article_bitmaps3", cachedBitmaps[3], 30000);
            aCache.put(params[5] + "article_bitmaps4", cachedBitmaps[4], 30000);
            aCache.put(params[5] + "article_bitmaps_is_cached", "yes", 30000);

            return cachedBitmaps;
        }
    }
}
