package com.example.pnlib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FloatingButtonBehavior extends FloatingActionButton.Behavior {
    private String TAG = "MyFABAnimate";

    //Không có phương thực này sẽ lỗi
    public FloatingButtonBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                       @NonNull FloatingActionButton child,
                                       @NonNull View directTargetChild,
                                       @NonNull View target,
                                       int axes,
                                       int type) {
        super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;

    }

    /**
     * Xử lý khi RecyclerView cuộn
     * @param target == RecyclerView
     */
    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                               @NonNull FloatingActionButton child,
                               @NonNull View target,
                               int dxConsumed,
                               int dyConsumed,
                               int dxUnconsumed,
                               int dyUnconsumed,
                               int type)
    {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);

        LinearLayoutManager linearLayoutManager = (LinearLayoutManager)((RecyclerView)target).getLayoutManager();
        if (dyConsumed > 0
                && child.getVisibility() == View.VISIBLE
                &&  (linearLayoutManager.findFirstVisibleItemPosition() != 0)) {

            //nếu gọi child.hide(); thì sau đó FAB không còn nhận sự kiện cuộn lên xuống nữa
            //nên cần gọi child.hide(listener);
            child.hide(new FloatingActionButton.OnVisibilityChangedListener() {

                @Override
                public void onHidden(FloatingActionButton fab) {
                    super.onShown(fab);
                    fab.setVisibility(View.INVISIBLE);
                }
            });

        } else if (dyConsumed < 0
                && child.getVisibility() != View.VISIBLE
                && (linearLayoutManager.findFirstVisibleItemPosition() == 0)) {
            child.show();
        }
    }

    /**
     * Thiết lập RecyclerView là lớp mà ứng xử của FAB dựa vào để thực hiện
     */
    @Override
    public boolean layoutDependsOn(
            CoordinatorLayout parent,
            FloatingActionButton child,
            View dependency) {
        if (dependency instanceof RecyclerView)
            return true;

        return false;
    }

}
