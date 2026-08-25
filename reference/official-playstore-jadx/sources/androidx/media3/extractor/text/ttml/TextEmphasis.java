package androidx.media3.extractor.text.ttml;

import android.text.TextUtils;
import com.google.common.collect.e4;
import com.google.common.collect.w1;
import com.google.common.collect.w3;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Pattern;
import t7.a;

/* JADX INFO: loaded from: classes3.dex */
final class TextEmphasis {
    public static final int MARK_SHAPE_AUTO = -1;
    public static final int POSITION_OUTSIDE = -2;
    public final int markFill;
    public final int markShape;
    public final int position;
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s+");
    private static final w1 SINGLE_STYLE_VALUES = w1.k(2, TtmlNode.TEXT_EMPHASIS_AUTO, "none");
    private static final w1 MARK_SHAPE_VALUES = w1.k(3, TtmlNode.TEXT_EMPHASIS_MARK_DOT, TtmlNode.TEXT_EMPHASIS_MARK_SESAME, TtmlNode.TEXT_EMPHASIS_MARK_CIRCLE);
    private static final w1 MARK_FILL_VALUES = w1.k(2, TtmlNode.TEXT_EMPHASIS_MARK_FILLED, TtmlNode.TEXT_EMPHASIS_MARK_OPEN);
    private static final w1 POSITION_VALUES = w1.k(3, TtmlNode.ANNOTATION_POSITION_AFTER, TtmlNode.ANNOTATION_POSITION_BEFORE, TtmlNode.ANNOTATION_POSITION_OUTSIDE);

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Position {
    }

    private TextEmphasis(int i10, int i11, int i12) {
        this.markShape = i10;
        this.markFill = i11;
        this.position = i12;
    }

    public static TextEmphasis parse(String str) {
        if (str == null) {
            return null;
        }
        String strS = a.S(str.trim());
        if (strS.isEmpty()) {
            return null;
        }
        String[] strArrSplit = TextUtils.split(strS, WHITESPACE_PATTERN);
        int length = strArrSplit.length;
        return parseWords(length != 0 ? length != 1 ? w1.k(strArrSplit.length, (Object[]) strArrSplit.clone()) : new e4(strArrSplit[0]) : w3.f14135t);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ef  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static androidx.media3.extractor.text.ttml.TextEmphasis parseWords(com.google.common.collect.w1 r7) {
        /*
            Method dump skipped, instruction units count: 249
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.ttml.TextEmphasis.parseWords(com.google.common.collect.w1):androidx.media3.extractor.text.ttml.TextEmphasis");
    }
}
