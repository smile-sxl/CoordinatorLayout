package com.smile.practice;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
/**
 * author: smile .
 * date: On 2018/5/30
 */
public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerview;
    private ActionBar mActionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        setSupportActionBar(mToolbar);
        mActionbar = getSupportActionBar();
        mActionbar.setTitle("CoordinationLayout");
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setItemAnimator(new DefaultItemAnimator());
        mRecyclerview.setAdapter(new MyRecyclerAdapter(this));
    }
}
