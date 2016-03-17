package com.example.skateboard.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.skateboard.myapplication.FinalViewPagaIndicator.FinalIndicatorViewHolder;

import java.util.ArrayList;

public class FinalIndicatorShowActivity extends AppCompatActivity {
    private FinalViewPagaIndicator indicator;
    private RecyclerView recycler;
    private ArrayList<String> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i = 0; i < 50; i++) {
            dataList.add("item" + i);
        }
        setContentView(R.layout.activity_final_indicator_show);
        indicator = (FinalViewPagaIndicator) findViewById(R.id.indicator);
        indicator.setAdapter(new IndicatorAdapter());

        recycler= (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recycler.setAdapter(new RecyclerAdapter());
        recycler.scrollToPosition(30);
        System.out.println("scrollx="+recycler.getScrollX());
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>
    {
        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_item_layout, parent, false);
            RecyclerViewHolder viewHolder = new RecyclerViewHolder(itemView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {
            holder.title.setText(dataList.get(position));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        class RecyclerViewHolder extends RecyclerView.ViewHolder
        {
            TextView title;
            public RecyclerViewHolder(View itemView) {
                super(itemView);
                title = (TextView) itemView.findViewById(R.id.title);
            }
        }



    }

    private class IndicatorAdapter extends FinalViewPagaIndicator.ViewPageIndicatorAdapter {
        @Override
        public int getItemCount() {
            return dataList.size();
        }

        @Override
        public FinalIndicatorViewHolder onCreateViewHolder(ViewGroup parent) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.title_item_layout, parent, false);
            IndicatorViewHolder viewHolder = new IndicatorViewHolder(itemView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(FinalIndicatorViewHolder holder, int position) {
            super.onBindViewHolder(holder, position);
            ((IndicatorViewHolder) holder).title.setText(dataList.get(position));
        }
    }

    private class IndicatorViewHolder extends FinalViewPagaIndicator.FinalIndicatorViewHolder {

        TextView title;

        public IndicatorViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
        }
    }
}
