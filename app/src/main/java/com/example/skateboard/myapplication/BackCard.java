package com.example.skateboard.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by skateboard on 16-1-31.
 */
public class BackCard extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=null;
        view=inflater.inflate(R.layout.backcard,container,false);
        return view;
    }
}
