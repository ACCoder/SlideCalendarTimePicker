package net.angrycode.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import net.angrycode.appdemo.R;


/**
 * Created by lancelot on 15/7/25.
 */
public class FloatingActionMenu extends RelativeLayout {
    private int mVerticalSpacing = 10;
    private int mPaddingRight = 10;
    private int mPaddingBottom = 10;

    private Drawable mIcon;
    private ImageView mImageToggle;

    public FloatingActionMenu(Context context) {
        super(context);
        init();
    }

    public FloatingActionMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FloatingActionMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
//        mScreenWidth = dm.widthPixels;
//        mScreenHeight = dm.heightPixels;
        mIcon = getResources().getDrawable(R.mipmap.fab_add);
        mImageToggle = new ImageView(getContext());
        mImageToggle.setImageDrawable(mIcon);

        addView(mImageToggle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getPaddingLeft();
        int height = getPaddingTop();
        int verticalSpacing = mVerticalSpacing;
        measureChildWithMargins(mImageToggle, widthMeasureSpec, 0, heightMeasureSpec, 0);
        int count = getChildCount();
        for (int i = 0; i < count; ++i) {
            View child = getChildAt(i);
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);

            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            lp.x = width;
            lp.y = height;

            height += verticalSpacing + child.getMeasuredHeight();
        }
//        width += getChildAt(getChildCount() - 1).getMeasuredWidth() + getPaddingRight();
//        height += getPaddingBottom();
//
//        setMeasuredDimension(resolveSize(width, widthMeasureSpec),
//                resolveSize(height, heightMeasureSpec));
        if (getLayoutParams().width == ViewGroup.LayoutParams.MATCH_PARENT) {
            width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        }

        if (getLayoutParams().height == ViewGroup.LayoutParams.MATCH_PARENT) {
            height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        int imageLeft = r - l - mImageToggle.getMeasuredWidth();
        int imageTop = b - t - mImageToggle.getMeasuredHeight();
        Log.i("onLayout", "l=" + l + ",t=" + t + ",r=" + r + ",b=" + b);
        Log.i("onLayout", "imageLeft=" + imageLeft + ",imageTop=" + imageTop);
        mImageToggle.layout(imageLeft, imageTop, imageLeft + mImageToggle.getMeasuredWidth(),
                imageTop + mImageToggle.getMeasuredHeight());
        int left, top;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child == mImageToggle) {
                continue;
            }
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            left = r - l - child.getMeasuredWidth();
            top = b - t - child.getMeasuredHeight() - lp.y;
            child.layout(left, top, left + child.getMeasuredWidth(), top
                    + child.getMeasuredHeight());
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p.width, p.height);
    }

    public static class LayoutParams extends RelativeLayout.LayoutParams {
        int x;
        int y;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }
    }
}
