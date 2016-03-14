package com.example.skateboard.myapplication;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;


/**
 * Created by skateboard on 16-3-4.
 */
public class PageIndicator extends LinearLayout implements IndicatorAdapter.OnItemClickListener {

    private RecyclerView titleGroup;

    private View bottomLine;

    private int bottomLineWidth;

    private int bottomLineHeight;

    private Context context;

    private Scroller scroller;

    private LayoutParams titleGroupsParams;

    private LayoutParams bottomLineParams;

    private IndicatorAdapter indicatorAdapter;

    public PageIndicator(Context context) {
        super(context);
        initView(context);
    }

    public PageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PageIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        setOrientation(VERTICAL);
        initWidget(context);
        addWidget();
    }

    private void initLayoutParams()
    {
        titleGroupsParams=new LayoutParams(LayoutParams.MATCH_PARENT,80);
        bottomLineParams=new LayoutParams(80, 5);
    }


    private void initWidget(Context context) {
        this.context = context;
        this.scroller = new Scroller(context);
        initLayoutParams();
        initTitleGroups();
        initBottomLine();
    }



    private void initTitleGroups() {
        this.titleGroup = new RecyclerView(context);

        titleGroup.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

        titleGroup.setLayoutParams(titleGroupsParams);
    }

    private void initBottomLine() {
        this.bottomLine = new View(context);

        this.bottomLine.setBackgroundColor(ContextCompat.getColor(context, android.R.color.black));

        bottomLine.setLayoutParams(bottomLineParams);

    }

    public void setBottomLineSize(int width, int height) {
        this.bottomLineWidth = (int) (width * getDensity(context));

        this.bottomLineHeight = (int) (height * getDensity(context));

        LayoutParams params = new LayoutParams(bottomLineWidth, bottomLineHeight);

        bottomLine.setLayoutParams(params);

    }

    public void setBottomLineColor(int color) {
        bottomLine.setBackgroundColor(color);
        bottomLine.postInvalidate();
    }

    private void addWidget() {
        this.addView(titleGroup);
        this.addView(bottomLine);
    }


    public void setTitleAdapter(IndicatorAdapter adapter) {
        this.indicatorAdapter=adapter;
        indicatorAdapter.setOnItemClickListener(this);
        this.titleGroup.setAdapter(adapter);
    }

    private float getDensity(Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return density;
    }

    @Override
    public void onItemClick(int position) {
        titleGroup.smoothScrollToPosition(position);

    }
}
