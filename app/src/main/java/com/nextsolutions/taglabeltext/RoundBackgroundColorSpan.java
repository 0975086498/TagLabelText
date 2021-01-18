package com.nextsolutions.taglabeltext;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

import androidx.annotation.NonNull;

/**
 * @author Good_Boy
 */
public class RoundBackgroundColorSpan extends ReplacementSpan {

    private final int mBackgroundColor;
    private final int mTextColor;
    private final float mCornerRadius;

    private final float mPaddingStart;
    private final float mPaddingEnd;

    private final float mMarginStart;
    private final float mMarginEnd;

    public RoundBackgroundColorSpan(int backgroundColor, int textColor, float cornerRadius, float paddingStart, float paddingEnd, float marginStart, float marginEnd) {
        super();
        mBackgroundColor = backgroundColor;
        mTextColor = textColor;
        mCornerRadius = cornerRadius;

        mPaddingStart = paddingStart;
        mPaddingEnd = paddingEnd;

        mMarginStart = marginStart;
        mMarginEnd = marginEnd;
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        //width of tag : padding + margin + content
        return (int) (mPaddingStart + mMarginStart + paint.measureText(text.subSequence(start, end).toString()) + mPaddingEnd + mMarginEnd);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        float width = paint.measureText(text.subSequence(start, end).toString());
        RectF rect = new RectF(x + mMarginStart, top, x + width + mPaddingStart + mPaddingEnd + mMarginStart, bottom);
        paint.setColor(mBackgroundColor);
        canvas.drawRoundRect(rect, mCornerRadius, mCornerRadius, paint);
        paint.setColor(mTextColor);

        Paint.FontMetrics fm = paint.getFontMetrics();
        float height = fm.bottom - fm.top + fm.leading;

        canvas.drawText(text, start, end, x + mPaddingStart + mMarginStart, (int) ((top + bottom) / 2 + height / 3), paint);
    }
}