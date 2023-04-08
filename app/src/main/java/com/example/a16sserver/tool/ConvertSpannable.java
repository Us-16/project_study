package com.example.a16sserver.tool;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

public class ConvertSpannable {
    /**
     * 부분적으로 글자 타입(bold, italic, plain), 크기(1기준 배수), 색상 변경하는 메서드입니다.
     * @param full
     * @param target
     * @param type
     * @param proportion
     * @param color
     * @return
     */
    public SpannableString spannableString(String full, String target, int type , float proportion, int color){
        SpannableString spannableString = new SpannableString(full);

        int startIndex = full.indexOf(target);
        int endIndex = startIndex + target.length();

        StyleSpan boldSpan = new StyleSpan(type);
        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(proportion);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);

        spannableString.setSpan(boldSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return  spannableString;
    }

    public SpannableString moreSpannable(SpannableString full, int startIndex, int endIndex, int type, float proportion, int color){
        SpannableString spannableString = new SpannableString(full);

        StyleSpan boldSpan = new StyleSpan(type);
        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(proportion);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);

        spannableString.setSpan(boldSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(sizeSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }
}
