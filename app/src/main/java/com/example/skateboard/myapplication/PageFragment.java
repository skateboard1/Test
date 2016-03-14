package com.example.skateboard.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by skateboard on 16-2-25.
 */
public class PageFragment extends Fragment {
    private TextView content;
    private String title;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle data=getArguments();
        if(data!=null && data.containsKey("title"))
        {
            title=data.getString("title");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=null;
        view=inflater.inflate(R.layout.item_page,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        content= (TextView) view.findViewById(R.id.content);
        content.setText(title);
    }
}
