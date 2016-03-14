package com.example.skateboard.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.widget.EdgeEffectCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * Created by skateboard on 16-3-1.
 */
public class MyTextView extends FrameLayout {

    private EdgeEffectCompat leftEdge;

    private EdgeEffectCompat rightEdge;

    private Scroller scroller;

    private float touchX;

    public MyTextView(Context context) {
        super(context);
        init();
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        this.setWillNotDraw(false);
        scroller=new Scroller(getContext());
        leftEdge=new EdgeEffectCompat(getContext());
        rightEdge=new EdgeEffectCompat(getContext());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                touchX=event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float distancX=event.getX()-touchX;
                if(distancX>0)
                {
                    leftEdge.onPull(distancX/getWidth(),distancX/getWidth());
                    rightEdge.onRelease();
                    postInvalidate();
                }
                else if(distancX<0)
                {
                    rightEdge.onPull(distancX/getWidth(),distancX/getWidth());
                    leftEdge.onRelease();
                    postInvalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                rightEdge.onRelease();
                leftEdge.onRelease();
                postInvalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                rightEdge.onRelease();
                leftEdge.onRelease();
                postInvalidate();
                break;
        }
        return true;
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int height=canvas.getHeight();
        int width=canvas.getWidth();
            if (!leftEdge.isFinished()) {//画滑到左的晕影效果
                final int restoreCount = canvas.save();
                canvas.rotate(-90, width / 2, height / 2);
                canvas.translate(width / 2 - height / 2, -width / 2 + height / 2);
                leftEdge.setSize(height,300);
                if (leftEdge.draw(canvas)) {
                    postInvalidate();
                }
                canvas.restoreToCount(restoreCount);
            }
            if (!rightEdge.isFinished()) {//画滑动右底的晕影效果
                final int restoreCount = canvas.save();
                canvas.rotate(90, width / 2, height / 2);
                canvas.translate(width / 2 - height / 2, -width / 2 + height / 2);
                rightEdge.setSize(height, 300);
                if (rightEdge.draw(canvas)) {
                    postInvalidate();
                }
                canvas.restoreToCount(restoreCount);
            }
    }
}
