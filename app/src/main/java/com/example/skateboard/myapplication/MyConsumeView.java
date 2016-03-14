package com.example.skateboard.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by skateboard on 16-2-25.
 */
public class MyConsumeView extends View {

    private int indicateColor;
    private Paint mPaint;
    public MyConsumeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint=new Paint();
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.MyConsumeView_Styleable);
        indicateColor=typedArray.getColor(R.styleable.MyConsumeView_Styleable_indicator_color, Color.WHITE);
        mPaint.setColor(indicateColor);
    }

    public MyConsumeView(Context context) {
        super(context);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(),getMeasuredHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
    }
}
