package com.example.skateboard.myapplication;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class SecondActivity extends AppCompatActivity {

    private FrontCard frontCard;
    private BackCard backCard;
    private RelativeLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        container= (RelativeLayout) findViewById(R.id.container);
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.flip_left_in,R.animator.flip_left_out).replace(R.id.container,backCard).commit();
            }
        });
        frontCard=new FrontCard();
        backCard=new BackCard();
        getFragmentManager().beginTransaction().add(R.id.container,frontCard).commit();

    }
}
