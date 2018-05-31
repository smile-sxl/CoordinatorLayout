package com.smile.practice;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

/**
 * author: smile .
 * date: On 2018/5/30
 */
public class CollapsingToolbarActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ActionBar mActionBar;
    private List<String> titles = new ArrayList<String>();
    private CollapsingToolbarLayout mCollapsingtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsingtoolbar);
        initData();
        initView();
        initEvent();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        titles.add("电影");
        titles.add("电视剧");
        titles.add("综艺");
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mCollapsingtoolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsingtoolbar);
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mCollapsingtoolbar.setTitle("青春");
        for (int i = 0; i < titles.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
        }
        List<Fragment> fragments = new ArrayList<Fragment>();
        for (int i = 0; i < titles.size(); i++) {
            fragments.add(new MyFragment());
        }
        MyFragmentPager fragmentPager = new MyFragmentPager(getSupportFragmentManager(), fragments, titles);
        // 给ViewPager 设置适配器
        mViewPager.setAdapter(fragmentPager);
        //  将TabLayout 和 ViewPager 关联起来
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
