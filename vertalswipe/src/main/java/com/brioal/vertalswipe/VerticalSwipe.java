package com.brioal.vertalswipe;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Vertal Swipe Content
 * Created by Brioal on 2016/9/13.
 */
public class VerticalSwipe extends FrameLayout {
    private View mMainView;
    private View mSwipeView;
    private ViewDragHelper mDragHelper;
    int mMainHeight;
    int mSwipeHeight;
    private boolean isShowing = false;


    public VerticalSwipe(Context context) {
        this(context, null);
    }

    public VerticalSwipe(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mDragHelper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                if (child == mSwipeView) { //SwipeView允许滑动
                    return true;
                }
                return false;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                if (top > mMainHeight) {
                    return mMainHeight;
                }
                if (top < getMeasuredHeight() - mSwipeView.getMeasuredHeight()) {
                    if (top < 0) {
                        return 0;
                    }
                    return getMeasuredHeight() - mSwipeView.getMeasuredHeight();
                }
                return top;
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                int topffset = getMeasuredHeight() - mSwipeView.getMeasuredHeight();
                if (topffset < 0) {
                    topffset = 0;
                }
                if (mSwipeView.getTop() > mMainHeight * 3 / 5) {
                    mDragHelper.smoothSlideViewTo(mSwipeView, 0, mMainHeight); //滑动到原本位置
                    ViewCompat.postInvalidateOnAnimation(VerticalSwipe.this);
                } else {
                    mDragHelper.smoothSlideViewTo(mSwipeView, 0, topffset); //滑动到原本位置
                    ViewCompat.postInvalidateOnAnimation(VerticalSwipe.this);
                }
            }
        });
    }
    //组件是否全部显示
    public boolean isSwipeViewShowing() {
        if (mSwipeView.getTop() == mMainHeight) {
            isShowing = false;
        } else {
            isShowing = true;
        }
        return isShowing;
    }
    //设置组件是否显示
    public void setSwipeViewShowing(boolean show) {
        if (show) {
            mDragHelper.smoothSlideViewTo(mSwipeView, 0, mMainHeight);
            ViewCompat.postInvalidateOnAnimation(VerticalSwipe.this);
        } else {
            mDragHelper.smoothSlideViewTo(mSwipeView, 0, getMeasuredHeight()-mSwipeHeight);
            ViewCompat.postInvalidateOnAnimation(VerticalSwipe.this);
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMainView = getChildAt(0);
        mSwipeView = getChildAt(1);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChild(mMainView, widthMeasureSpec, heightMeasureSpec);
        measureChild(mSwipeView, widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMainHeight = mMainView.getMeasuredHeight();
        mSwipeHeight = mSwipeView.getMeasuredHeight();
        System.out.println("MainHeight:" + mMainHeight);
        System.out.println("SwipeHeight:" + mSwipeView.getMeasuredHeight());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        System.out.println("MainHeight:" + mMainHeight);
        System.out.println("SwipeHeight:" + mSwipeHeight);
        mMainView.layout(0, 0, getMeasuredWidth(), mMainHeight);
        mSwipeView.layout(0, mMainHeight, getMeasuredWidth(), mMainHeight + mSwipeHeight);
    }
}
