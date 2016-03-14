package com.example.skateboard.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Scroller;

import java.util.HashMap;
import java.util.List;

/**
 * Created by skateboard on 16-3-7.
 */
public class CousumePageIndicator extends HorizontalScrollView {

   private HashMap<Integer,View> viewPosMap;

   private LinearLayout container;

   private ConsumeAdapter adapter;

   private int screenWidth;

   private int itemWidth;

   private int itemHeight;

   private int screenCount;

   private int currIndex;

   private int firstIndex;

   private int itemNumber;

   private int scrollx;

    public CousumePageIndicator(Context context) {
        super(context);
        init(context);
    }

    public CousumePageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CousumePageIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context)
    {
        initView(context);
        countScreenWidth(context);
    }

    private void initView(Context context)
    {
         container=new LinearLayout(context);
         container.setOrientation(LinearLayout.HORIZONTAL);
         container.setLayoutParams(new ViewGroup.LayoutParams(320, 320));
         container.requestLayout();
         postInvalidate();
         addView(container);
    }

    public void setAdapter(ConsumeAdapter adapter)
    {
        this.adapter=adapter;
        notifyDataChanged();
    }

    public void notifyDataChanged()
    {
        initData();
    }

    private void initData()
    {
        if(adapter==null || adapter.getItemCount()<=0)
            return;
        ViewHolder viewHolder=adapter.onCreateViewHolder(container);
        View itemView=viewHolder.itemView;
        container.addView(itemView);
        itemWidth=itemView.getMeasuredWidth();
        itemHeight=itemView.getMeasuredHeight();
//        itemWidth=80;
//        itemHeight=80;
        itemNumber=adapter.getItemCount();
        if(itemWidth==0 && itemHeight==0)
        {

        }
        else
        {
           screenCount=screenWidth/itemWidth+2;
           currIndex=screenCount-1;
        }
        initFirstScreen();
    }

    private void loadNextData()
    {
         if(currIndex==itemNumber-1)
         {
             return;
         }
        else
         {   scrollTo(0,0);
             container.removeViewAt(0);
             viewPosMap.remove(firstIndex);
             ViewHolder viewHolder=adapter.onCreateViewHolder(container);
             adapter.onBindViewHolder(viewHolder,++currIndex);
             container.addView(viewHolder.itemView);
             firstIndex++;
         }
    }

    private void loadPreData()
    {
         if(firstIndex==0)
         {
             return;
         }
        int index=currIndex-screenCount;
        if(index>0)
        {
            scrollTo(itemWidth,0);
            int lastIndex=container.getChildCount()-1;
            viewPosMap.remove(lastIndex);
            container.removeViewAt(lastIndex);
            ViewHolder viewHolder=adapter.onCreateViewHolder(container);
            adapter.onBindViewHolder(viewHolder,index);
            container.addView(viewHolder.itemView,0);
            viewPosMap.put(index,viewHolder.itemView);
            currIndex--;
            firstIndex--;
        }
    }

    private void initFirstScreen()
    {
        viewPosMap=new HashMap<>();
        viewPosMap.clear();
        for(int i=0;i<screenCount;i++)
        {
            ViewHolder viewHolder=adapter.onCreateViewHolder(container);
            View itemView=viewHolder.itemView;
            container.addView(itemView);
            adapter.onBindViewHolder(viewHolder,i);
            viewPosMap.put(i,itemView);

        }
    }


    private int countScreenWidth(Context context)
    {
        screenWidth=context.getResources().getDisplayMetrics().widthPixels;
        return screenWidth;
    }


    public static class ConsumeAdapter
    {
        private Context context;

        private List data;

        public ConsumeAdapter(Context context,List data)
        {
            this.context=context;
            this.data=data;
        }

        public int getItemCount()
        {
             return data.size();
        }

        public ViewHolder onCreateViewHolder(ViewGroup parent)
        {
                return null;
        }

        public void onBindViewHolder(ViewHolder holder,int position)
        {

        }

    }

    public static class ViewHolder
    {
        public View itemView;

        public ViewHolder(View itemView)
        {
            this.itemView=itemView;
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action=ev.getAction();
        scrollx=getScrollX();
        switch(action)
        {
            case MotionEvent.ACTION_MOVE:
                if(scrollx>=itemWidth)
                {
                    loadNextData();
//                    scrollx=getScrollX();
                }
                if(scrollx==0)
                {
                    loadPreData();
                }
                break;
        }

        return super.onTouchEvent(ev);
    }
}
