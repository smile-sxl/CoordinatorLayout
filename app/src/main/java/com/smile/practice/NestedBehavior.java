package com.smile.practice;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * author: smile .
 * date: On 2018/6/3
 */
public class NestedBehavior extends CoordinatorLayout.Behavior<View> {

    int offsetTotal = 0;

    public NestedBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 会遍历每一个 子View，询问它们是否对滚动列表的滚动事件感兴趣，若 Behavior.onStartNestedScroll 方法返回 true，
     * 则表示感兴趣，那么滚动列表后续的滚动事件都会分发到该 子View的Behavior
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int axes, int type) {
        return true;
    }

    /**
     * 处理 子View 的滚动事件
     */
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        offset(child, dy);
    }

    public void offset(View child, int dy) {
        // 上次保存的位置
        int old = offsetTotal;
        // 当前的位置
        int curr = offsetTotal - dy;
        // 保证子控件的位置一直在 0-控件高度之间
        curr = Math.max(curr, -child.getHeight());
        curr = Math.min(curr, 0);
        offsetTotal = curr;
        if (old == offsetTotal) {
            return;
        }
        // 原来的位置 - 当前的位置 = 要移动的位置
        int delta = old - offsetTotal;
        child.offsetTopAndBottom(delta);
    }

}
