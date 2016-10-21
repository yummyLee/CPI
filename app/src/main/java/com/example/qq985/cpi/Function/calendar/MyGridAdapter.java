package com.example.qq985.cpi.Function.calendar;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.qq985.cpi.R;

/**
 * @Description:gridview的Adapter
 * @author http://blog.csdn.net/finddreams
 */
public class MyGridAdapter extends BaseAdapter {
    private Context mContext;

    public String[] img_text = { "食品：5%", "教育：5%", "住房：5%", "交通：5%", "医疗：5%", "衣服：5%",
            "家居物品：5%", "烟酒：5%" };
    public int[] imgs = { R.drawable.food, R.drawable.house,
            R.drawable.car, R.drawable.funandaducation,
            R.drawable.home, R.drawable.wine,
            R.drawable.doctor, R.drawable.cloths};

    public MyGridAdapter(Context mContext) {
        super();
        this.mContext = mContext;
    }


    public void setImg_text(String[]str){
        img_text = str;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return img_text.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.grid_item, parent, false);
        }
        TextView tv = BaseViewHolder.get(convertView, R.id.tv_item);
        ImageView iv = BaseViewHolder.get(convertView, R.id.iv_item);
        iv.setBackgroundResource(imgs[position]);

        tv.setText(img_text[position]);
        return convertView;
    }

}

