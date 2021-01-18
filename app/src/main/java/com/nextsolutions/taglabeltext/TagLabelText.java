package com.nextsolutions.taglabeltext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;

import com.nextsolutions.myapplication.R;

import androidx.databinding.BindingAdapter;

/**
 * @author Good_Boy
 * @apiNote Tag text's size should be lower than origin text.
 */

public class TagLabelText extends androidx.appcompat.widget.AppCompatTextView {
    public String tagText;
    private String text;//text gốc trước khi thêm tag
    boolean isTagFirst;
    boolean isTagBold;

    float tagRadius;
    int tagSize;

    float tagPaddingStart;
    float tagPaddingEnd;

    float tagMarginStart;
    float tagMarginEnd;

    public int tagTextColor;
    public int tagBackgroundColor;

    public TagLabelText(Context context) {
        super(context);
    }

    public TagLabelText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.text = super.getText().toString();
        setSpanText(context, attrs);
    }

    public TagLabelText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.text = super.getText().toString();
        setSpanText(context, attrs);
    }

    private void setSpanText(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.TagLabelText);

        tagText = a.getString(R.styleable.TagLabelText_tagText);
        getAttr(a);
        if (tagText != null && tagText.length() > 0) {


            setSpan();
        }

        a.recycle();
    }

    private void getAttr(TypedArray a) {
        isTagFirst = a.getBoolean(R.styleable.TagLabelText_tagFirst, true);
        isTagBold = a.getBoolean(R.styleable.TagLabelText_tagBold, false);

        tagRadius = a.getDimension(R.styleable.TagLabelText_tagRadius, 4);

        tagSize = (int) a.getDimension(R.styleable.TagLabelText_tagSize, 14);

        tagPaddingStart = a.getDimension(R.styleable.TagLabelText_tagPaddingStart, 0);
        tagPaddingEnd = a.getDimension(R.styleable.TagLabelText_tagPaddingEnd, 0);

        tagMarginStart = a.getDimension(R.styleable.TagLabelText_tagMarginStart, 0);
        tagMarginEnd = a.getDimension(R.styleable.TagLabelText_tagMarginEnd, 0);

        tagTextColor = a.getColor(R.styleable.TagLabelText_tagTextColor, Color.parseColor("#FFFFFF"));
        tagBackgroundColor = a.getColor(R.styleable.TagLabelText_tagTextBackgroundColor, Color.parseColor("#289767"));
    }

    public void setSpan() {
        SpannableString tag = new SpannableString(tagText);

        tag.setSpan(new AbsoluteSizeSpan(tagSize, false), 0, tag.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        tag.setSpan(new RoundBackgroundColorSpan(tagBackgroundColor, tagTextColor, tagRadius, tagPaddingStart, tagPaddingEnd, tagMarginStart, tagMarginEnd)
                , 0, tag.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        if (isTagBold) {
            tag.setSpan(new StyleSpan(Typeface.BOLD), 0, tag.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        } else {
            tag.setSpan(new StyleSpan(Typeface.NORMAL), 0, tag.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }

        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        if (isTagFirst) {
            stringBuilder.append(tag).append(this.text);
        } else {
            stringBuilder.append(this.text).append(tag);
        }

        setText(stringBuilder);
    }


    public void mSetText(String text) {
        this.text = text;
        super.setText(this.text);
    }

    public String mGetText() {
        return text;
    }


    @BindingAdapter({"text", "tagText", "tagBackgroundColor", "tagTextColor"})
    public static void setTagText(TagLabelText view, CharSequence text, CharSequence tagText, Integer tagBackgroundColor, Integer tagTextColor) {
        if (text != null) {
            view.mSetText(text.toString());
        }
        if (tagText != null) {
            view.tagText = tagText.toString();
        }
        if (tagBackgroundColor != null) {
            view.tagBackgroundColor = tagBackgroundColor;
        }
        if (tagTextColor != null) {
            view.tagTextColor = tagTextColor;
        }
        view.setSpan();
    }

    @BindingAdapter({"text", "tagText"})
    public static void setTagText(TagLabelText view, CharSequence text, CharSequence tagText) {
        if (text != null) {
            view.mSetText(text.toString());
        }
        if (tagText != null) {
            view.tagText = tagText.toString();
        }

        view.setSpan();
    }

}

