package com.example.skateboard.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        recyclerView= (RecyclerView) findViewById(R.id.recycler);
        dataList=new ArrayList<>();
        for(int i=0;i<20;i++)
        {
            dataList.add("item"+i);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(new DataAdapter());
    }

    private class DataAdapter extends RecyclerView.Adapter<DataAdapter.ItemHolder>
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
        public void onBindViewHolder(ItemHolder holder, int position) {
            holder.title.setText(dataList.get(position));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }
}


