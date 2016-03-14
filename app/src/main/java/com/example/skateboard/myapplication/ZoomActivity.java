package com.example.skateboard.myapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ZoomActivity extends AppCompatActivity {
    private FrameLayout container;
    private ImageButton thumButton;
    private ImageView expand_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);
        container=(FrameLayout)findViewById(R.id.container);
        expand_img=(ImageView)findViewById(R.id.expand_img);
        thumButton=(ImageButton)findViewById(R.id.thumb_button);
        thumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              expand_img.setImageResource(R.mipmap.ic_launcher);
                Rect startRect=new Rect();
                Rect endRect=new Rect();
                Point offset=new Point();
                thumButton.getGlobalVisibleRect(startRect);
                container.getGlobalVisibleRect(endRect,offset);
                startRect.offset(-offset.x,-offset.y);
                endRect.offset(-offset.x,-offset.y);
                float startScale;

                startScale = (float) startRect.height() / endRect.height();
                float startWidth = startScale * endRect.width();
                float deltaWidth = (startWidth - startRect.width()) / 2;
                startRect.left -= deltaWidth;
                startRect.right += deltaWidth;
                thumButton.setVisibility(View.GONE);
                expand_img.setVisibility(View.VISIBLE);
                expand_img.setPivotX(0f);
                expand_img.setPivotY(0f);

                AnimatorSet set = new AnimatorSet();
                set
                        .play(ObjectAnimator.ofFloat(expand_img, View.X,
                                startRect.left, endRect.left))
                        .with(ObjectAnimator.ofFloat(expand_img, View.Y,
                                startRect.top, endRect.top))
                        .with(ObjectAnimator.ofFloat(expand_img, View.SCALE_X,
                                startScale, 1f)).with(ObjectAnimator.ofFloat(expand_img,
                        View.SCALE_Y, startScale, 1f));
                set.setDuration(1000);
                set.setInterpolator(new DecelerateInterpolator());
                set.start();

            }
        });
    }
}
