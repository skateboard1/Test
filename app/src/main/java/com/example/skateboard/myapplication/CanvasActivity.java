package com.example.skateboard.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CanvasActivity extends AppCompatActivity {
   private PageIndicator indicator;
   private List<String> dataList;
   private ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        dataList=new ArrayList<>();
        for(int i=0;i<20;i++)
        {
            dataList.add("item"+i);
        }
        indicator= (PageIndicator) findViewById(R.id.indicator);
//        indicator.setBottomLineSize(20,3);
//        indicator.setBottomLineColor(android.R.color.holo_red_light);
        indicator.setTitleAdapter(new DataAdapter());
        pager= (ViewPager) findViewById(R.id.view_pager);
        pager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
    }

    private class MyViewPagerAdapter extends FragmentPagerAdapter
    {

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            PageFragment pageFragment=new PageFragment();
            Bundle bundle=new Bundle();
            bundle.putString("title",dataList.get(position));
            pageFragment.setArguments(bundle);
            return pageFragment;
        }

        @Override
        public int getCount() {
            return dataList.size();
        }
    }

    private class DataAdapter extends IndicatorAdapter
    {
         class ItemHolder extends RecyclerView.ViewHolder
         {
             TextView title;

             public ItemHolder(View itemView) {
                 super(itemView);
                 title= (TextView) itemView.findViewById(R.id.title);
             }
         }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.title_item_layout,parent,false);

            ItemHolder holder=new ItemHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            super.onBindViewHolder(holder,position);
            ((ItemHolder)holder).title.setText(dataList.get(position));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }
}
