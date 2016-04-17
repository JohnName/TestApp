package com.john.org.hl.testapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    private View mContentView;
    private View mLoadingView;
    private int mShortAnimationDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContentView = findViewById(R.id.content);
        mLoadingView = findViewById(R.id.loading_spinner);
        //设置文本不可见
        mContentView.setVisibility(View.GONE);
        //获得系统中渐变动画的时间
        mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
        crossfade();
    }
    private void crossfade() {

        //内容视图设置为0%,所以它是可见的(但完全透明)在动画期间。
        mContentView.setAlpha(0f);
        mContentView.setVisibility(View.VISIBLE);

        // 内容视图以渐变的方式设置到完全可见，在mshortanimationduration 时间段里
        mContentView.animate().alpha(1f).setDuration(mShortAnimationDuration).setListener(null);

        // 加载视图从可见到渐变到不可见，并监听动画结束设置visible
        mLoadingView.animate().alpha(0f).setDuration(mShortAnimationDuration).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLoadingView.setVisibility(View.GONE);
            }
        });
    }
}
