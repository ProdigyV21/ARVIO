package androidx.media3.ui;

import android.text.BidiFormatter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextDirectionHeuristics;
import androidx.media3.common.util.Log;
import com.google.common.base.b0;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

/* JADX INFO: loaded from: classes3.dex */
final class BidiUtils {
    private static final String TAG = "BidiUtils";
    private static final b0 LF_SPLITTER = b0.b("\n");
    private static final b0 CRLF_SPLITTER = b0.b("\r\n");
    private static final com.google.common.base.o LF_JOINER = new com.google.common.base.o("\n");

    @EnsuresNonNullIf(expression = {"#1"}, result = true)
    public static boolean containsRtl(CharSequence charSequence) {
        if (charSequence == null) {
            return false;
        }
        int length = charSequence.length();
        int iCharCount = 0;
        while (iCharCount < length) {
            int iCodePointAt = Character.codePointAt(charSequence, iCharCount);
            byte directionality = Character.getDirectionality(iCodePointAt);
            if (directionality == 1 || directionality == 2 || directionality == 16 || directionality == 17) {
                return true;
            }
            iCharCount += Character.charCount(iCodePointAt);
        }
        return false;
    }

    public static CharSequence wrapText(CharSequence charSequence) {
        Spanned spanned;
        Object[] spans;
        int[] iArr;
        int[] iArr2;
        List<String> listC;
        int i10;
        BidiFormatter bidiFormatter = BidiFormatter.getInstance();
        int i11 = 0;
        if (charSequence instanceof Spanned) {
            spanned = (Spanned) charSequence;
            spans = spanned.getSpans(0, charSequence.length(), Object.class);
            iArr = new int[spans.length];
            iArr2 = new int[spans.length];
            Arrays.fill(iArr, -1);
            Arrays.fill(iArr2, -1);
        } else {
            spanned = null;
            spans = null;
            iArr = null;
            iArr2 = null;
        }
        if (charSequence.toString().contains("\r\n")) {
            listC = CRLF_SPLITTER.c(charSequence);
            i10 = 2;
        } else {
            listC = LF_SPLITTER.c(charSequence);
            i10 = 1;
        }
        ArrayList arrayList = new ArrayList(listC.size());
        int i12 = 0;
        int length = 0;
        for (String str : listC) {
            String strUnicodeWrap = bidiFormatter.unicodeWrap(str, TextDirectionHeuristics.LTR);
            if (spans != null) {
                spanned.getClass();
                iArr.getClass();
                iArr2.getClass();
                int length2 = strUnicodeWrap.length() - str.length();
                if (length2 > 0) {
                    i12++;
                }
                for (int i13 = i11; i13 < spans.length; i13++) {
                    if (iArr[i13] < 0 && spanned.getSpanStart(spans[i13]) >= length) {
                        if (spanned.getSpanStart(spans[i13]) < str.length() + length) {
                            iArr[i13] = i12;
                        }
                    }
                    if (iArr2[i13] < 0 && spanned.getSpanEnd(spans[i13]) - 1 >= length && spanned.getSpanEnd(spans[i13]) - 1 < str.length() + length) {
                        iArr2[i13] = i12;
                    }
                }
                length += str.length() + i10;
                if (length2 > 0) {
                    i12++;
                }
            }
            arrayList.add(strUnicodeWrap);
            i11 = 0;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(LF_JOINER.b(arrayList));
        if (spans != null) {
            spanned.getClass();
            iArr.getClass();
            iArr2.getClass();
            for (int i14 = 0; i14 < spans.length; i14++) {
                int spanStart = spanned.getSpanStart(spans[i14]) + iArr[i14];
                int spanEnd = spanned.getSpanEnd(spans[i14]) + iArr2[i14];
                int spanFlags = spanned.getSpanFlags(spans[i14]);
                if (spanStart < 0 || spanStart >= spannableStringBuilder.length() || spanEnd < 0 || spanEnd > spannableStringBuilder.length()) {
                    StringBuilder sbV = androidx.compose.foundation.c.v("Span out of bounds: start=", spanStart, ",end=", spanEnd, ",len=");
                    sbV.append(spannableStringBuilder.length());
                    Log.w(TAG, sbV.toString());
                } else {
                    spannableStringBuilder.setSpan(spans[i14], spanStart, spanEnd, spanFlags);
                }
            }
        }
        return spannableStringBuilder;
    }
}
