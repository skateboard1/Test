package com.example.skateboard.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.widget.EdgeEffectCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import java.util.HashMap;

/**
 * Created by skateboard on 16-3-8.
 */
public class FinalViewPagaIndicator extends ViewGroup {

    private EdgeEffectCompat leftEdge;

    private EdgeEffectCompat rightEdge;

    private Scroller scroller;

    private ViewPageIndicatorAdapter adapter;

    private HashMap<Integer, FinalIndicatorViewHolder> viewPos = new HashMap<>();

    private int screenWidth;

    private int itemWidth;

    private int itemHeight;

    private int oneScreenItemNumber;

    private int willShowNumber;

    private int curIndex;

    private int firstIndex;

    private int childCount;

    private float touchX;

    private int touchScrollX;

    private boolean hasAdapter;


    public FinalViewPagaIndicator(Context context) {
        super(context);
        init(context);
    }

    public FinalViewPagaIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FinalViewPagaIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        calculateScreenWidth(context);
        initEdge(context);
        initScroller(context);
    }

    private int calculateScreenWidth(Context context) {
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        return screenWidth;
    }

    private void initScroller(Context context) {
        scroller = new Scroller(context);
    }

    private void initEdge(Context context) {
        leftEdge = new EdgeEffectCompat(context);
        rightEdge = new EdgeEffectCompat(context);
    }

    public void setAdapter(ViewPageIndicatorAdapter adapter) {
        this.adapter = adapter;
        postInvalidate();
        scrollTo(300,0);
        scrollTo(0,0);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        firstSetAdapter(widthMeasureSpec,heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }


    private void firstSetAdapter(int widthMeasureSpec, int heightMeasureSpec)
    {
        calculateItemNumber(widthMeasureSpec, heightMeasureSpec);
        addAdapterViews();
        hasAdapter=true;
    }

    private void addAdapterViews() {
        if(adapter!=null && !hasAdapter)
        {
            viewPos.clear();
            for (int i = 0; i < willShowNumber; i++) {
                FinalIndicatorViewHolder itemViewHolder = adapter.onCreateViewHolder(this);
                addView(itemViewHolder.itemView);
                adapter.onBindViewHolder(itemViewHolder, i);
                viewPos.put(i, itemViewHolder);
            }
            childCount = getChildCount();
        }
    }

    private void calculateItemNumber(int widthMeasureSpec, int heightMeasureSpec) {
        if (adapter != null && !hasAdapter) {
            FinalIndicatorViewHolder viewHolder = adapter.onCreateViewHolder(this);
            View itemView = viewHolder.itemView;
            this.addView(itemView);
            measureChild(itemView, widthMeasureSpec, heightMeasureSpec);
            itemWidth = itemView.getMeasuredWidth();
            itemHeight = itemView.getMeasuredHeight();
            oneScreenItemNumber = screenWidth / itemWidth + 2;
            int adapterItemCount = adapter.getItemCount();
            if (adapterItemCount > oneScreenItemNumber) {
                willShowNumber = oneScreenItemNumber;
            } else {
                willShowNumber = adapterItemCount;
            }
            curIndex=willShowNumber-1;
            firstIndex=0;
            removeAllViews();
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int curLeft = 0;
        for (int i = 0; i < willShowNumber; i++) {
            View item = getChildAt(i);
            item.layout(curLeft, 0, curLeft + item.getMeasuredWidth(), item.getMeasuredHeight());
            curLeft += item.getMeasuredWidth();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchX = event.getX();
                touchScrollX = getScrollX();
                break;
            case MotionEvent.ACTION_MOVE:
                float movX = event.getX();
                float changeX = movX - touchX;
//                if(firstIndex==0 && changeX>0)
//                {
//                    return true;
//                }
//                else if(curIndex==adapter.getItemCount()-1 && changeX<0)
//                {
//                    return true;
//                }
                if (getScrollX()>= itemWidth && changeX<0) {
                    loadNext();
                } else if (getScrollX() <= 0 && changeX>0) {
                    loadPre();
                }
                else
                {
                    scrollBy(-(int) changeX, 0);
                }
                touchX = event.getX();
                break;
        }
        return true;
    }

    private void loadNext() {

        if (curIndex >= (adapter.getItemCount() - 1)) {
            return;
        }
        scrollTo(0,0);
        postInvalidate();
        ++curIndex;
        FinalIndicatorViewHolder itemViewHolder=viewPos.get(firstIndex);
        View itemView=itemViewHolder.itemView;
        removeView(itemView);
        adapter.onBindViewHolder(itemViewHolder,curIndex);
        addView(itemView);




        viewPos.put(curIndex,itemViewHolder);

        viewPos.remove(firstIndex);
        ++firstIndex;

    }

    private void loadPre() {
        if (firstIndex <=0) {
            return;
        }

        firstIndex--;
        FinalIndicatorViewHolder itemViewHolder=viewPos.get(curIndex);
        View itemView=itemViewHolder.itemView;
        removeView(itemView);
        adapter.onBindViewHolder(itemViewHolder,firstIndex);
        scrollTo(itemWidth,0);
        addView(itemView,0);
        viewPos.put(firstIndex,itemViewHolder);
        viewPos.remove(curIndex);
        curIndex--;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }

    public static class ViewPageIndicatorAdapter {
        public int getItemCount() {
            return 0;
        }

        public FinalIndicatorViewHolder onCreateViewHolder(ViewGroup parent) {
            return null;
        }

        public void onBindViewHolder(FinalIndicatorViewHolder holder, int position) {

        }

    }

    public static class FinalIndicatorViewHolder {
        public View itemView;

        public FinalIndicatorViewHolder(View view) {
            this.itemView = view;
        }
    }

//    @Override
//    protected void dispatchDraw(Canvas canvas) {
//        int state=canvas.save();
//        canvas.translate(getScrollX(),0);
//        super.dispatchDraw(canvas);
//        canvas.restoreToCount(state);
//    }

}
