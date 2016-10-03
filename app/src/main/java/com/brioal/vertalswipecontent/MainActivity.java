package com.brioal.vertalswipecontent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.brioal.vertalswipe.VerticalSwipe;

public class MainActivity extends AppCompatActivity {
    VerticalSwipe mContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContainer = (VerticalSwipe) findViewById(R.id.container);

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
