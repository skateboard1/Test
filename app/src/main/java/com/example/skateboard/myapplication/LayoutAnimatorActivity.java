package com.example.skateboard.myapplication;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LayoutAnimatorActivity extends AppCompatActivity {

    private RelativeLayout container;

    private int topOffset=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animator);
        container= (RelativeLayout) findViewById(R.id.container);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout_animator,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.add)
        {
            View layout_item= LayoutInflater.from(this).inflate(R.layout.layout_item,container,false);
            RelativeLayout.LayoutParams params=((RelativeLayout.LayoutParams)layout_item.getLayoutParams());
            params.setMargins(0,topOffset,0,0);
            int topMargin=params.topMargin;
            layout_item.setLayoutParams(params);
            container.addView(layout_item);
            topOffset+=10;
        }
        return super.onOptionsItemSelected(item);
    }
}
