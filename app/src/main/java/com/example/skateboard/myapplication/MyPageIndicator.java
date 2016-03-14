package com.example.skateboard.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * Created by skateboard on 16-3-1.
 */
public class MyPageIndicator extends ViewGroup {

    private Scroller mScroller;
    private int state=STATE_IDLE;
    private final static int STATE_IDLE=0;
    private final static int STATE_SCROLLING=1;
    private final static boolean isBeingDraged=false;
    private VelocityTracker velocityTracker;
    public MyPageIndicator(Context context) {
        super(context);
        init();
    }

    public MyPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyPageIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        velocityTracker=VelocityTracker.obtain();
        ViewConfiguration configuration=ViewConfiguration.get(getContext());
        mScroller=new Scroller(getContext());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount=getChildCount();
        int offset=0;
        for(int i=0;i<childCount;i++)
        {
            View view=getChildAt(i);
            view.layout(l+offset,t,r,b);
            offset+=view.getMeasuredWidth();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {


        return super.onInterceptTouchEvent(ev);
    }
}
