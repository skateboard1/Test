package widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by skateboard on 16-2-25.
 */
public class MyConsumerViewGroup extends ViewGroup {

    private Scroller mScroller;
    private float oldX;
    private float oldY;
    private float newX;
    private float newY;
    private static final int STAT_RESET=0;
    private static final int STAT_SCROLLING=1;
    private int nowState=STAT_RESET;

    public MyConsumerViewGroup(Context context) {
        super(context);
        mScroller=new Scroller(context);
    }

    public MyConsumerViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller=new Scroller(context);
    }

    public MyConsumerViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (getChildCount() > 0) {
            View childView = getChildAt(0);
            childView.layout(0, 0, childView.getMeasuredWidth(), childView.getMeasuredHeight());
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                oldX=event.getX();
                oldY=event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                newX=event.getX();
                newY=event.getY();
                mScroller.startScroll((int)oldX,(int)oldY,(int)newX,(int)newY);
                nowState=STAT_SCROLLING;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                nowState=STAT_RESET;
                break;
        }
        return true;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action=ev.getAction();
        String test="1234";
        int number=test.length()/2-1+test.length();
        char[] fin=new char[number];
        int j=0;
        for(int i=0;i<test.length();i++)
        {
            if(i%2==0 && i!=0)
            {
                fin[j]='a';
            }
            else
            {
                fin[i]=test.charAt(i);
            }
            j++;
        }
        String result=new String(fin);
        System.out.println(result);
        return true;


    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset())
        {
            scrollTo(mScroller.getCurrX(),0);
            postInvalidate();
        }
    }
}
