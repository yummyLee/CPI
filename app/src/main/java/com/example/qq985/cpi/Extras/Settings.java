package com.example.qq985.cpi.Extras;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.qq985.cpi.ACache;
import com.example.qq985.cpi.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class Settings extends Fragment {


    @Bind(R.id.tv_settings_cache_size)
    TextView tvSettingsCacheSize;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_settings, container, false);


        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 90:

                    break;
                default:
                    break;

            }
        }
    };


    Runnable getCacheSize = new Runnable() {
        @Override
        public void run() {



        }
    };


}
