package com.example.skateboard.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.skateboard.myapplication.FinalViewPagaIndicator.FinalIndicatorViewHolder;

import java.util.ArrayList;

public class FinalIndicatorShowActivity extends AppCompatActivity {
   private FinalViewPagaIndicator indicator;
   private ArrayList<String> dataList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for(int i=0;i<20000;i++)
        {
            dataList.add("item"+i);
        }
        setContentView(R.layout.activity_final_indicator_show);
        indicator= (FinalViewPagaIndicator) findViewById(R.id.indicator);
        indicator.setAdapter(new IndicatorAdapter());

    }

    private class IndicatorAdapter extends FinalViewPagaIndicator.ViewPageIndicatorAdapter
    {
        @Override
        public int getItemCount() {
            return dataList.size();
        }

        @Override
        public FinalIndicatorViewHolder onCreateViewHolder(ViewGroup parent) {
            View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.title_item_layout,parent,false);
            IndicatorViewHolder viewHolder=new IndicatorViewHolder(itemView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(FinalIndicatorViewHolder holder, int position) {
            super.onBindViewHolder(holder, position);
            ((IndicatorViewHolder)holder).title.setText(dataList.get(position));
        }
    }

    private class IndicatorViewHolder extends FinalViewPagaIndicator.FinalIndicatorViewHolder
    {

        TextView title;

        public IndicatorViewHolder(View view) {
            super(view);
            title= (TextView) view.findViewById(R.id.title);
        }
    }
}
