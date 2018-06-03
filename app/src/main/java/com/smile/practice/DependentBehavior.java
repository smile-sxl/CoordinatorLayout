package com.smile.practice;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * author: smile .
 * date: On 2018/6/2
 */
public class DependentBehavior extends CoordinatorLayout.Behavior<View> {
    /**
     * 构造方法
     *
     * @param context
     * @param attrs
     */
    public DependentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 确定要依赖的对象
     *
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency.getId() == R.id.btn_first;
    }

    /**
     * 当依赖的对象发生变化时会自动回调这个方法
     *
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        child.setX(parent.getWidth() - dependency.getX() - dependency.getWidth());
        child.setY(dependency.getY());
        return super.onDependentViewChanged(parent, child, dependency);
    }
}
