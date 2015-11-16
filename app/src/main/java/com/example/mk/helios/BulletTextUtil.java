package com.example.mk.helios;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BulletSpan;

/**
 * Created by Matt on 8/10/2015.
 * //
 * Taken from the following page.
 * http://stackoverflow.com/questions/4992794/how-to-add-bulleted-list-to-android-application
 */
//CharSequence bulletedList = BulletListUtil.makeBulletList("First line", "Second line", "Really long third line that will wrap and indent properly.");
  //      textView.setText(bulletedList);

public class BulletTextUtil {

    /**
     * Returns a CharSequence containing a bulleted and properly indented list.
     *
     * @param leadingMargin In pixels, the space between the left edge of the bullet and the left edge of the text.
     * @param context
     * @param stringArrayResId A resource id pointing to a string array. Each string will be a separate line/bullet-point.
     * @return
     */
    public static CharSequence makeBulletListFromStringArrayResource(int leadingMargin, Context context, int stringArrayResId) {
        return makeBulletList(leadingMargin, context.getResources().getStringArray(stringArrayResId));
    }

    /**
     * Returns a CharSequence containing a bulleted and properly indented list.
     *
     * @param leadingMargin In pixels, the space between the left edge of the bullet and the left edge of the text.
     * @param context
     * @param linesResIds An array of string resource ids. Each string will be a separate line/bullet-point.
     * @return
     */
    public static CharSequence makeBulletListFromStringResources(int leadingMargin, Context context, int... linesResIds) {
        int len = linesResIds.length;
        CharSequence[] cslines = new CharSequence[len];
        for (int i = 0; i < len; i++) {
            cslines[i] = context.getString(linesResIds[i]);
        }
        return makeBulletList(leadingMargin, cslines);
    }

    /**
     * Returns a CharSequence containing a bulleted and properly indented list.
     *
     * @param leadingMargin In pixels, the space between the left edge of the bullet and the left edge of the text.
     * @param lines An array of CharSequences. Each CharSequences will be a separate line/bullet-point.
     * @return
     */
    public static CharSequence makeBulletList(int leadingMargin, CharSequence... lines) {
        SpannableStringBuilder sb = new SpannableStringBuilder();
        for (int i = 0; i < lines.length; i++) {
            CharSequence line = lines[i] + (i < lines.length-1 ? "\n" : "");
            Spannable spannable = new SpannableString(line);
            spannable.setSpan(new BulletSpan(leadingMargin), 0, spannable.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            sb.append(spannable);
        }
        return sb;
    }

}
