package com.example.skateboard.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TotalCounsumeActivity extends AppCompatActivity {
    private List<String> dataList;
    private CousumePageIndicator indicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_counsume);
        dataList=new ArrayList<>();
        for(int i=0;i<200;i++)
        {
            dataList.add("item"+i);
        }
        indicator= (CousumePageIndicator) findViewById(R.id.titlegroup);
        indicator.setAdapter(new DataAdapter(this,dataList));
    }

    private class DataAdapter extends CousumePageIndicator.ConsumeAdapter
    {
        class ViewHolder extends CousumePageIndicator.ViewHolder
        {
           TextView title;

            public ViewHolder(View itemView) {
                super(itemView);
                title= (TextView) itemView.findViewById(R.id.title);
            }
        }

        public DataAdapter(Context context, List data) {
            super(context, data);
        }

        @Override
        public CousumePageIndicator.ViewHolder onCreateViewHolder(ViewGroup parent) {

            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.title_item_layout,parent,false);

            int parentHeight=parent.getHeight();

            int parentWidth=parent.getWidth();

            int height=view.getMeasuredHeight();

            int width=view.getMeasuredWidth();

            ViewHolder holder=new ViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(CousumePageIndicator.ViewHolder holder, int position) {
            super.onBindViewHolder(holder,position);
            ((ViewHolder)holder).title.setText(dataList.get(position));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }
}
