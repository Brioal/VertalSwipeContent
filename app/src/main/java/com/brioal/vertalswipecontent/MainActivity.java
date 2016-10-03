package com.brioal.vertalswipecontent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.brioal.vertalswipe.VerticalSwipe;
import com.brioal.vertalswipe.interfaces.OnSwipeListener;

public class MainActivity extends AppCompatActivity {
    VerticalSwipe mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContainer = (VerticalSwipe) findViewById(R.id.container);
        //设置滑动界面滑动监听器0~1.0f
        mContainer.setSwipeListener(new OnSwipeListener() {
            @Override
            public void progress(float progress) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        if (mContainer.isSwipeViewShowing()) {
            mContainer.setSwipeViewShowing(false);
        } else {
            super.onBackPressed();
        }
    }
}
