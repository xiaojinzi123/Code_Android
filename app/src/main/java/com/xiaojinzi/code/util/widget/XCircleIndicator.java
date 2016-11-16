package com.xiaojinzi.code.util.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.xiaojinzi.code.R;

/**
 * 一个指示器,这个不是本人编写,是从github上脱下来的<br/>
 * 用在发现页面的轮播图上
 */
public class XCircleIndicator extends View {
    private int radius = 4;
    private final Paint mPaintStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint mPaintFill = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final TextPaint mPaintText = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    private int currentScroll = 0;
    private int flowWidth = 0;
    private int count = 1;
    private int currentPage = 0;
    private int circleInterval = radius;
    private float mDensity;
    private int upLimit = 10;

    public XCircleIndicator(Context context) {
        super(context);
        initColors(0xFFFFFFFF, 0xFFFFFFFF);
    }

    /**
     * The contructor used with an inflater
     *
     * @param context
     * @param attrs
     */
    public XCircleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        // Retrieve styles attributs
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.XCircleIndicator);

        try {
            // Retrieve the colors to be used for this view and apply them.
            int fillColor = a.getColor(
                    R.styleable.XCircleIndicator_fillColor, 0xFFFFFFFF);
            int strokeColor = a.getColor(
                    R.styleable.XCircleIndicator_strokeColor, 0xFFFFFFFF);
            // Retrieve the radius
            radius = (int) a.getDimension(
                    R.styleable.XCircleIndicator_radius, radius);
            circleInterval = (int) a.getDimension(
                    R.styleable.XCircleIndicator_circleInterval, radius);
            mDensity = getContext().getResources().getDisplayMetrics().density;
            initColors(fillColor, strokeColor);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            a.recycle();
        }

    }

    /**
     * @param upLimit default 4
     */
    public void setUpLimit(int upLimit) {
        this.upLimit = upLimit;
    }

    public void initData(int count, int contentWidth) {
        this.count = count;
        this.flowWidth = contentWidth;
        requestLayout();
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        invalidate();
    }

    private int mTextHeight;

    private void initColors(int fillColor, int strokeColor) {
        mPaintStroke.setStyle(Style.STROKE);
        mPaintStroke.setColor(strokeColor);
        mPaintFill.setStyle(Style.FILL);
        mPaintFill.setColor(fillColor);
        mPaintText.setColor(Color.parseColor("#F96A0E"));
        mPaintText.setTextSize(18 * mDensity);
        FontMetrics fm = mPaintText.getFontMetrics();
        mTextHeight = (int) Math.ceil(fm.descent - fm.top) + 2;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right,
                            int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Draw stroked circles
        if (count <= upLimit) {
            for (int iLoop = 0; iLoop < count; iLoop++) {
                canvas.drawCircle(getPaddingLeft() + radius
                                + (iLoop * (2 * radius + circleInterval)),
                        getPaddingTop() + radius, radius, mPaintStroke);
            }
            int cx = 0;
            // if (flowWidth != 0) {
            // // Draw the filled circle according to the current scroll
            // cx = (currentScroll * (2 * radius + radius)) / flowWidth;
            // }
            cx = (2 * radius + circleInterval) * currentPage;
            // The flow width has been upadated yet. Draw the default position
            canvas.drawCircle(getPaddingLeft() + radius + cx, getPaddingTop()
                    + radius, radius, mPaintFill);
        } else {
            float textWidth = Layout.getDesiredWidth(currentPage + "/"
                    + (count - 1), mPaintText);
            canvas.drawText(currentPage + 1 + "/" + (count),
                    (getMeasuredWidth() - textWidth) / 2, getPaddingTop()
                            + getMeasuredHeight(), mPaintText);
        }
    }

    /**
     * Determines the width of this view
     *
     * @param measureSpec A measureSpec packed into an int
     * @return The width of the view, honoring constraints from measureSpec
     */
    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        // We were told how big to be
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else { // Calculate the width according the views count
            result = getPaddingLeft() + getPaddingRight()
                    + (count * 2 * radius) + (count - 1) * circleInterval;
            // Respect AT_MOST value if that was what is called for by
            // measureSpec
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    public void onScrolled(int h, int v, int oldh, int oldv) {
        currentScroll = h;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
    }

    /**
     * Determines the height of this view
     *
     * @param measureSpec A measureSpec packed into an int
     * @return The height of the view, honoring constraints from measureSpec
     */
    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (count > upLimit) {
            return mTextHeight + getPaddingTop() + getPaddingBottom();
        }

        // We were told how big to be
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        }
        // Measure the height
        else {
            result = 2 * radius + getPaddingTop() + getPaddingBottom();
            // Respect AT_MOST value if that was what is called for by
            // measureSpec
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * Sets the fill color
     *
     * @param color ARGB value for the text
     */
    public void setFillColor(int color) {
        mPaintFill.setColor(color);
        invalidate();
    }

    /**
     * Sets the stroke color
     *
     * @param color ARGB value for the text
     */
    public void setStrokeColor(int color) {
        mPaintStroke.setColor(color);
        invalidate();
    }

}