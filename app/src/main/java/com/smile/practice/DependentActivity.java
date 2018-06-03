package com.smile.practice;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * author: smile .
 * date: On 2018/6/2
 */
public class DependentActivity extends AppCompatActivity implements View.OnTouchListener {

    private Button mbtnFirst;
    private int lastX, lastY;    //保存手指点下的点的坐标
    private int mWidth, mHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependent);
        mbtnFirst = (Button) findViewById(R.id.btn_first);
        //设置屏幕触摸事件
        mbtnFirst.setOnTouchListener(this);
    }

    /**
     * 当activity 的焦点发生改变时（View 已经绘制完成，可以获得宽高）
     *
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mWidth = mbtnFirst.getWidth();
        mHeight = mbtnFirst.getHeight();
    }

    /**
     * 触屏事件处理
     *
     * @param view
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View view, MotionEvent event) {
        // 处理多点触摸的ACTION_POINTER_DOWN和ACTION_POINTER_UP事件
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                //将点下的点的坐标保存
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                //计算出需要移动的距离
                int dx = (int) event.getRawX() - lastX;
                int dy = (int) event.getRawY() - lastY;
                //将移动距离加上，现在本身距离边框的位置
                int left = view.getLeft() + dx;
                int top = view.getTop() + dy;
                //获取到layoutParams然后改变属性，在设置回去
                CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) view
                        .getLayoutParams();
                layoutParams.height = mHeight;
                layoutParams.width = mWidth;
                layoutParams.leftMargin = left;
                layoutParams.topMargin = top;
                view.setLayoutParams(layoutParams);

                //记录最后一次移动的位置
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
        }
        return true;
    }
}
