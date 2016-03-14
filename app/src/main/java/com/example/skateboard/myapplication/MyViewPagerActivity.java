package com.example.skateboard.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyViewPagerActivity extends AppCompatActivity {
    private List<String> data = new ArrayList<>();
    private RecyclerView title;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view_pager);
        for (int i = 0; i < 100; i++) {
            data.add("title" + i);
        }
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyPageAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset > 0.9) {
                    LinearLayoutManager manager = (LinearLayoutManager) title.getLayoutManager();
                    manager.setSmoothScrollbarEnabled(true);
                    manager.scrollToPosition(position+1);
                }
            }

            @Override
            public void onPageSelected(int position) {
                LinearLayoutManager manager = (LinearLayoutManager) title.getLayoutManager();
                manager.setSmoothScrollbarEnabled(true);
//                TitleViewHolder holder = (TitleViewHolder) title.findViewHolderForLayoutPosition(position);
//                holder.bottomLine.setBackgroundColor(ContextCompat.getColor(MyViewPagerActivity.this, android.R.color.holo_blue_bright));
//                for (int i = manager.findFirstVisibleItemPosition(); i < manager.findLastVisibleItemPosition(); i++) {
//                    if (i != position) {
//                        holder.bottomLine.setBackgroundColor(ContextCompat.getColor(MyViewPagerActivity.this, android.R.color.darker_gray));
//                    }
//                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        title = (RecyclerView) findViewById(R.id.title);
        title.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        title.setAdapter(new TitleAdapter());
        title.setFadingEdgeLength(0);
//        title.setHorizontalFadingEdgeEnabled(false);
    }

    private class TitleAdapter extends RecyclerView.Adapter<TitleViewHolder> {

        @Override
        public TitleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_item, parent, false);
            TitleViewHolder holder = new TitleViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final TitleViewHolder holder, final int position) {
            holder.title.setText(data.get(position));
            LinearLayout layout = (LinearLayout) holder.title.getParent();
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    private class TitleViewHolder extends RecyclerView.ViewHolder {
        private View bottomLine;
        private TextView title;

        public TitleViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            bottomLine = itemView.findViewById(R.id.bottom_line);
        }
    }

    private class MyPageAdapter extends FragmentPagerAdapter {

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putString("title", data.get(position));
            Fragment fragment = new PageFragment();
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return data.size();
        }
    }
}
