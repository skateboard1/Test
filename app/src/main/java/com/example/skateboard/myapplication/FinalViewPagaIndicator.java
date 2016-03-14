package com.example.skateboard.myapplication;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.storage.OnObbStateChangeListener;
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

    private int offsetIndex;


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
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        calculateItemNumber(widthMeasureSpec, heightMeasureSpec);
        addAdapterViews();
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    private void addAdapterViews() {
        for (int i = 0; i < willShowNumber; i++) {
            FinalIndicatorViewHolder itemViewHolder = adapter.onCreateViewHolder(this);
            addView(itemViewHolder.itemView);
            adapter.onBindViewHolder(itemViewHolder, i);
            viewPos.put(i, itemViewHolder);
        }
    }

    private void calculateItemNumber(int widthMeasureSpec, int heightMeasureSpec) {
        if (adapter != null) {
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
            removeAllViews();
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        childCount = getChildCount();
        int curLeft = 0;
        for (int i = 0; i < childCount; i++) {
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
                scrollBy(-(int) changeX, 0);
                postInvalidate();
                if ((getScrollX() - touchScrollX) > itemWidth) {
                    loadNext();
                } else if ((getScrollX() - touchScrollX) < -itemWidth) {
                    loadPre();
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

        FinalIndicatorViewHolder itemViewHolder = viewPos.get(firstIndex);
        viewPos.remove(firstIndex);
        removeViewAt(0);

        offsetIndex++;
        curIndex=childCount+offsetIndex;
        firstIndex=offsetIndex;

        adapter.onBindViewHolder(itemViewHolder, curIndex);
        viewPos.put(curIndex, itemViewHolder);

        touchScrollX = getScrollX();
    }

    private void loadPre() {

        if (firstIndex <=0) {
            return;
        }


        FinalIndicatorViewHolder itemViewHolder = viewPos.remove(curIndex);
        removeView(itemViewHolder.itemView);
        offsetIndex--;
        curIndex-=offsetIndex;
        firstIndex=offsetIndex;

        adapter.onBindViewHolder(itemViewHolder,firstIndex);
        viewPos.put(firstIndex,itemViewHolder);
        touchScrollX = getScrollX();
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

}
