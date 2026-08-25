package androidx.media3.extractor.text.ttml;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import androidx.media3.common.text.HorizontalTextInVerticalContextSpan;
import androidx.media3.common.text.RubySpan;
import androidx.media3.common.text.SpanUtil;
import androidx.media3.common.text.TextEmphasisSpan;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import java.util.ArrayDeque;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
final class TtmlRenderUtil {
    private static final String TAG = "TtmlRenderUtil";

    private TtmlRenderUtil() {
    }

    public static void applyStylesToSpan(Spannable spannable, int i10, int i11, TtmlStyle ttmlStyle, TtmlNode ttmlNode, Map<String, TtmlStyle> map, int i12) {
        TtmlNode ttmlNodeFindRubyTextNode;
        TtmlStyle ttmlStyleResolveStyle;
        int i13;
        if (ttmlStyle.getStyle() != -1) {
            spannable.setSpan(new StyleSpan(ttmlStyle.getStyle()), i10, i11, 33);
        }
        if (ttmlStyle.isLinethrough()) {
            spannable.setSpan(new StrikethroughSpan(), i10, i11, 33);
        }
        if (ttmlStyle.isUnderline()) {
            spannable.setSpan(new UnderlineSpan(), i10, i11, 33);
        }
        if (ttmlStyle.hasFontColor()) {
            SpanUtil.addOrReplaceSpan(spannable, new ForegroundColorSpan(ttmlStyle.getFontColor()), i10, i11, 33);
        }
        if (ttmlStyle.hasBackgroundColor()) {
            SpanUtil.addOrReplaceSpan(spannable, new BackgroundColorSpan(ttmlStyle.getBackgroundColor()), i10, i11, 33);
        }
        if (ttmlStyle.getFontFamily() != null) {
            SpanUtil.addOrReplaceSpan(spannable, new TypefaceSpan(ttmlStyle.getFontFamily()), i10, i11, 33);
        }
        if (ttmlStyle.getTextEmphasis() != null) {
            TextEmphasis textEmphasis = ttmlStyle.getTextEmphasis();
            textEmphasis.getClass();
            int i14 = textEmphasis.markShape;
            if (i14 == -1) {
                i14 = (i12 == 2 || i12 == 1) ? 3 : 1;
                i13 = 1;
            } else {
                i13 = textEmphasis.markFill;
            }
            int i15 = textEmphasis.position;
            if (i15 == -2) {
                i15 = 1;
            }
            SpanUtil.addOrReplaceSpan(spannable, new TextEmphasisSpan(i14, i13, i15), i10, i11, 33);
        }
        int rubyType = ttmlStyle.getRubyType();
        if (rubyType == 2) {
            TtmlNode ttmlNodeFindRubyContainerNode = findRubyContainerNode(ttmlNode, map);
            if (ttmlNodeFindRubyContainerNode != null && (ttmlNodeFindRubyTextNode = findRubyTextNode(ttmlNodeFindRubyContainerNode, map)) != null) {
                if (ttmlNodeFindRubyTextNode.getChildCount() != 1 || ttmlNodeFindRubyTextNode.getChild(0).text == null) {
                    Log.i(TAG, "Skipping rubyText node without exactly one text child.");
                } else {
                    String str = (String) Util.castNonNull(ttmlNodeFindRubyTextNode.getChild(0).text);
                    TtmlStyle ttmlStyleResolveStyle2 = resolveStyle(ttmlNodeFindRubyTextNode.style, ttmlNodeFindRubyTextNode.getStyleIds(), map);
                    int rubyPosition = ttmlStyleResolveStyle2 != null ? ttmlStyleResolveStyle2.getRubyPosition() : -1;
                    if (rubyPosition == -1 && (ttmlStyleResolveStyle = resolveStyle(ttmlNodeFindRubyContainerNode.style, ttmlNodeFindRubyContainerNode.getStyleIds(), map)) != null) {
                        rubyPosition = ttmlStyleResolveStyle.getRubyPosition();
                    }
                    spannable.setSpan(new RubySpan(str, rubyPosition), i10, i11, 33);
                }
            }
        } else if (rubyType == 3 || rubyType == 4) {
            spannable.setSpan(new DeleteTextSpan(), i10, i11, 33);
        }
        if (ttmlStyle.getTextCombine()) {
            SpanUtil.addOrReplaceSpan(spannable, new HorizontalTextInVerticalContextSpan(), i10, i11, 33);
        }
        int fontSizeUnit = ttmlStyle.getFontSizeUnit();
        if (fontSizeUnit == 1) {
            SpanUtil.addOrReplaceSpan(spannable, new AbsoluteSizeSpan((int) ttmlStyle.getFontSize(), true), i10, i11, 33);
        } else if (fontSizeUnit == 2) {
            SpanUtil.addOrReplaceSpan(spannable, new RelativeSizeSpan(ttmlStyle.getFontSize()), i10, i11, 33);
        } else {
            if (fontSizeUnit != 3) {
                return;
            }
            SpanUtil.addInheritedRelativeSizeSpan(spannable, ttmlStyle.getFontSize() / 100.0f, i10, i11, 33);
        }
    }

    public static String applyTextElementSpacePolicy(String str) {
        return str.replaceAll("\r\n", "\n").replaceAll(" *\n *", "\n").replaceAll("\n", " ").replaceAll("[ \t\\x0B\f\r]+", " ");
    }

    public static void endParagraph(SpannableStringBuilder spannableStringBuilder) {
        int length = spannableStringBuilder.length() - 1;
        while (length >= 0 && spannableStringBuilder.charAt(length) == ' ') {
            length--;
        }
        if (length < 0 || spannableStringBuilder.charAt(length) == '\n') {
            return;
        }
        spannableStringBuilder.append('\n');
    }

    private static TtmlNode findRubyContainerNode(TtmlNode ttmlNode, Map<String, TtmlStyle> map) {
        while (ttmlNode != null) {
            TtmlStyle ttmlStyleResolveStyle = resolveStyle(ttmlNode.style, ttmlNode.getStyleIds(), map);
            if (ttmlStyleResolveStyle != null && ttmlStyleResolveStyle.getRubyType() == 1) {
                return ttmlNode;
            }
            ttmlNode = ttmlNode.parent;
        }
        return null;
    }

    private static TtmlNode findRubyTextNode(TtmlNode ttmlNode, Map<String, TtmlStyle> map) {
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(ttmlNode);
        while (!arrayDeque.isEmpty()) {
            TtmlNode ttmlNode2 = (TtmlNode) arrayDeque.pop();
            TtmlStyle ttmlStyleResolveStyle = resolveStyle(ttmlNode2.style, ttmlNode2.getStyleIds(), map);
            if (ttmlStyleResolveStyle != null && ttmlStyleResolveStyle.getRubyType() == 3) {
                return ttmlNode2;
            }
            for (int childCount = ttmlNode2.getChildCount() - 1; childCount >= 0; childCount--) {
                arrayDeque.push(ttmlNode2.getChild(childCount));
            }
        }
        return null;
    }

    public static TtmlStyle resolveStyle(TtmlStyle ttmlStyle, String[] strArr, Map<String, TtmlStyle> map) {
        int i10 = 0;
        if (ttmlStyle == null) {
            if (strArr == null) {
                return null;
            }
            if (strArr.length == 1) {
                return map.get(strArr[0]);
            }
            if (strArr.length > 1) {
                TtmlStyle ttmlStyle2 = new TtmlStyle();
                int length = strArr.length;
                while (i10 < length) {
                    ttmlStyle2.chain(map.get(strArr[i10]));
                    i10++;
                }
                return ttmlStyle2;
            }
        } else {
            if (strArr != null && strArr.length == 1) {
                return ttmlStyle.chain(map.get(strArr[0]));
            }
            if (strArr != null && strArr.length > 1) {
                int length2 = strArr.length;
                while (i10 < length2) {
                    ttmlStyle.chain(map.get(strArr[i10]));
                    i10++;
                }
            }
        }
        return ttmlStyle;
    }
}
