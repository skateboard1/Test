package com.example.skateboard.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    private Button insert;
    private Button delete;
    private Button update;
    private Button query;
    private Button change;
    private Button Second;
    private Button layoutAnimator;
    private Button zoomView;
    private MyDateBaseHelper myDateBaseHelper;
    private String dbName="mydatebase";
    private String tableName="mytable";

    private Toolbar toolbar;

    private Button netInfo;

    private Button ticket;

    private TextView iconFont;
    private Button inputLayout;
    private Button viewPager;
    private Button consume;
    private Button canvas;
    private Button test;
    private Button totalConsume;
    private Button finalIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insert= (Button) findViewById(R.id.insert);
        delete= (Button) findViewById(R.id.delete);
        update= (Button) findViewById(R.id.update);
        query= (Button) findViewById(R.id.query);
        Second=(Button)findViewById(R.id.second);
        zoomView=(Button)findViewById(R.id.zoom);
        zoomView.setOnClickListener(this);
        Second.setOnClickListener(this);
        insert.setOnClickListener(this);
        delete.setOnClickListener(this);
        update.setOnClickListener(this);
        query.setOnClickListener(this);
        layoutAnimator= (Button) findViewById(R.id.layoutanimator);
        layoutAnimator.setOnClickListener(this);
        myDateBaseHelper=new MyDateBaseHelper(this,dbName);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        change= (Button) findViewById(R.id.change);
        change.setOnClickListener(this);
        netInfo=(Button)findViewById(R.id.connect);
        netInfo.setOnClickListener(this);
        ticket= (Button) findViewById(R.id.ticket);
        ticket.setOnClickListener(this);

        iconFont=(TextView)findViewById(R.id.icon_font);
        iconFont.setTypeface(Typeface.createFromAsset(getAssets(), "iconfont/iconfont.ttf"));

        inputLayout= (Button) findViewById(R.id.inputlayout);
        inputLayout.setOnClickListener(this);

        viewPager= (Button) findViewById(R.id.viewpager);
        viewPager.setOnClickListener(this);
        consume= (Button) findViewById(R.id.consumeview);
        consume.setOnClickListener(this);
        canvas= (Button) findViewById(R.id.canvas);
        canvas.setOnClickListener(this);
        test= (Button) findViewById(R.id.test);
        test.setOnClickListener(this);

        totalConsume= (Button) findViewById(R.id.totalCounsume);
        totalConsume.setOnClickListener(this);

        finalIndicator= (Button) findViewById(R.id.finalindicator);
        finalIndicator.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu,menu);
        MenuItem item=menu.findItem(R.id.shareprovider);
        ShareActionProvider provider= (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,"来吧，英雄");
        if(provider!=null) {
            provider.setShareIntent(intent);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        SQLiteDatabase database=null;
        switch(v.getId())
        {
            case R.id.finalindicator:
                Intent finalIntent=new Intent(this,FinalIndicatorShowActivity.class);
                startActivity(finalIntent);
                break;

            case R.id.totalCounsume:
                Intent totalIntent=new Intent(this,TotalCounsumeActivity.class);
                startActivity(totalIntent);
                break;

            case R.id.ticket:
                Intent ticketIntent=new Intent(this,TicketActivity.class);
                startActivity(ticketIntent);
                break;
            case R.id.insert:
                database=myDateBaseHelper.getWritableDatabase();
                String insert="INSERT INTO "+tableName+"(name,score) VALUES('XIAOMING',32);";
                database.execSQL(insert);
                database.close();
                break;
            case R.id.delete:
                database=myDateBaseHelper.getWritableDatabase();
                String delete="DELETE FROM "+ tableName;
                database.execSQL(delete);
                database.close();
                break;
            case R.id.update:
                database=myDateBaseHelper.getWritableDatabase();
                String update="UPDATE "+ tableName+ " SET id = 233,name = 'xiaoli',score = 100;";
                database.execSQL(update);
                database.close();
                break;
            case R.id.query:
                database=myDateBaseHelper.getWritableDatabase();
                Cursor cursor=database.query(tableName,new String[]{"name","score"},null,null,null,null,null);
                cursor.moveToNext();
                while(!cursor.isLast())
                {
                    int nameIndex=cursor.getColumnIndex("name");
                    String name=cursor.getString(nameIndex);
                    int scoreIndex=cursor.getColumnIndex("score");
                    int score=cursor.getInt(scoreIndex);
                    Toast.makeText(MainActivity.this,name+" score is "+score,Toast.LENGTH_SHORT).show();
                    cursor.moveToNext();
                }
                break;
            case R.id.change:
                ViewGroup mainLayout= (ViewGroup) findViewById(R.id.mainlayout);
                Scene scene=Scene.getSceneForLayout(mainLayout,R.layout.layout_a,MainActivity.this);
//                Scene scene1=Scene.getSceneForLayout(mainLayout,R.layout.activity_main,MainActivity.this);
                Fade fade=new Fade(Fade.IN);
                fade.setDuration(3000);
                TransitionManager.go(scene,fade);
                break;
            case R.id.second:
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
                break;
            case R.id.layoutanimator:
                Intent layoutIntent=new Intent(MainActivity.this,LayoutAnimatorActivity.class);
                startActivity(layoutIntent);
                break;
            case R.id.zoom:
                Intent zoomIntent=new Intent(MainActivity.this,ZoomActivity.class);
                startActivity(zoomIntent);
                break;
            case R.id.connect:
                Intent connectIntent=new Intent(MainActivity.this,ConnectActivity.class);
                startActivity(connectIntent);
                break;
            case R.id.inputlayout:
                Intent inputIntent=new Intent(MainActivity.this,InputLayoutActivity.class);
                startActivity(inputIntent);
                break;
            case R.id.viewpager:
                Intent pageIntent=new Intent(MainActivity.this,MyViewPagerActivity.class);
                startActivity(pageIntent);
                break;
            case R.id.consumeview:
                Intent consumeIntent=new Intent(MainActivity.this,ConsumeViewActivity.class);
                startActivity(consumeIntent);
                break;
            case R.id.canvas:
                Intent canvas=new Intent(MainActivity.this,CanvasActivity.class);
                startActivity(canvas);
                break;
            case R.id.test:
                Intent test=new Intent(MainActivity.this,TestActivity.class);
                startActivity(test);
                break;
        }

    }
}
