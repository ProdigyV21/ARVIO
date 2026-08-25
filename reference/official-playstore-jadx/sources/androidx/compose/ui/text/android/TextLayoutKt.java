package androidx.compose.ui.text.android;

import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import androidx.compose.ui.text.android.style.LineHeightStyleSpan;
import kotlin.Metadata;
import kotlin.collections.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001d\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0000¢\u0006\u0002\u0010\t\u001a\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007H\u0000\u001a1\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0002¢\u0006\u0002\u0010\u0016\u001a\u001f\u0010\u0017\u001a\u00020\u0003*\u00020\u000f2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0002¢\u0006\u0002\u0010\u0018\u001a\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014*\u00020\u000fH\u0002¢\u0006\u0002\u0010\u001a\u001a\u0011\u0010\u001b\u001a\u00020\u0003*\u00020\u000fH\u0002¢\u0006\u0002\u0010\u001c\u001a\u0014\u0010\u001d\u001a\u00020\u001e*\u00020\u001f2\u0006\u0010 \u001a\u00020\u0007H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004¨\u0006!"}, d2 = {"SharedTextAndroidCanvas", "Landroidx/compose/ui/text/android/TextAndroidCanvas;", "ZeroVerticalPadding", "Landroidx/compose/ui/text/android/VerticalPaddings;", "J", "VerticalPaddings", "topPadding", "", "bottomPadding", "(II)J", "getTextDirectionHeuristic", "Landroid/text/TextDirectionHeuristic;", "textDirectionHeuristic", "getLastLineMetrics", "Landroid/graphics/Paint$FontMetricsInt;", "Landroidx/compose/ui/text/android/TextLayout;", "textPaint", "Landroid/text/TextPaint;", "frameworkTextDir", "lineHeightSpans", "", "Landroidx/compose/ui/text/android/style/LineHeightStyleSpan;", "(Landroidx/compose/ui/text/android/TextLayout;Landroid/text/TextPaint;Landroid/text/TextDirectionHeuristic;[Landroidx/compose/ui/text/android/style/LineHeightStyleSpan;)Landroid/graphics/Paint$FontMetricsInt;", "getLineHeightPaddings", "(Landroidx/compose/ui/text/android/TextLayout;[Landroidx/compose/ui/text/android/style/LineHeightStyleSpan;)J", "getLineHeightSpans", "(Landroidx/compose/ui/text/android/TextLayout;)[Landroidx/compose/ui/text/android/style/LineHeightStyleSpan;", "getVerticalPaddings", "(Landroidx/compose/ui/text/android/TextLayout;)J", "isLineEllipsized", "", "Landroid/text/Layout;", "lineIndex", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextLayoutKt {
    private static final TextAndroidCanvas SharedTextAndroidCanvas = new TextAndroidCanvas();
    private static final long ZeroVerticalPadding = VerticalPaddings(0, 0);

    public static final long VerticalPaddings(int i10, int i11) {
        return VerticalPaddings.m5240constructorimpl((((long) i11) & 4294967295L) | (((long) i10) << 32));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Paint.FontMetricsInt getLastLineMetrics(TextLayout textLayout, TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic, LineHeightStyleSpan[] lineHeightStyleSpanArr) {
        int lineCount = textLayout.getLineCount() - 1;
        if (textLayout.getLayout().getLineStart(lineCount) != textLayout.getLayout().getLineEnd(lineCount)) {
            return null;
        }
        if (lineHeightStyleSpanArr.length == 0) {
            return null;
        }
        SpannableString spannableString = new SpannableString("\u200b");
        LineHeightStyleSpan lineHeightStyleSpan = (LineHeightStyleSpan) r.Y(lineHeightStyleSpanArr);
        spannableString.setSpan(lineHeightStyleSpan.copy$ui_text_release(0, spannableString.length(), (lineCount == 0 || !lineHeightStyleSpan.getTrimLastLineBottom()) ? lineHeightStyleSpan.getTrimLastLineBottom() : false), 0, spannableString.length(), 33);
        StaticLayout staticLayoutCreate$default = StaticLayoutFactory.create$default(StaticLayoutFactory.INSTANCE, spannableString, textPaint, Integer.MAX_VALUE, 0, spannableString.length(), textDirectionHeuristic, null, 0, null, 0, 0.0f, 0.0f, 0, textLayout.getIncludePadding(), textLayout.getFallbackLineSpacing(), 0, 0, 0, 0, null, null, 2072512, null);
        Paint.FontMetricsInt fontMetricsInt = new Paint.FontMetricsInt();
        fontMetricsInt.ascent = staticLayoutCreate$default.getLineAscent(0);
        fontMetricsInt.descent = staticLayoutCreate$default.getLineDescent(0);
        fontMetricsInt.top = staticLayoutCreate$default.getLineTop(0);
        fontMetricsInt.bottom = staticLayoutCreate$default.getLineBottom(0);
        return fontMetricsInt;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long getLineHeightPaddings(TextLayout textLayout, LineHeightStyleSpan[] lineHeightStyleSpanArr) {
        int iMax = 0;
        int iMax2 = 0;
        for (LineHeightStyleSpan lineHeightStyleSpan : lineHeightStyleSpanArr) {
            if (lineHeightStyleSpan.getFirstAscentDiff() < 0) {
                iMax = Math.max(iMax, Math.abs(lineHeightStyleSpan.getFirstAscentDiff()));
            }
            if (lineHeightStyleSpan.getLastDescentDiff() < 0) {
                iMax2 = Math.max(iMax, Math.abs(lineHeightStyleSpan.getLastDescentDiff()));
            }
        }
        return (iMax == 0 && iMax2 == 0) ? ZeroVerticalPadding : VerticalPaddings(iMax, iMax2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LineHeightStyleSpan[] getLineHeightSpans(TextLayout textLayout) {
        if (!(textLayout.getText() instanceof Spanned)) {
            return new LineHeightStyleSpan[0];
        }
        LineHeightStyleSpan[] lineHeightStyleSpanArr = (LineHeightStyleSpan[]) ((Spanned) textLayout.getText()).getSpans(0, textLayout.getText().length(), LineHeightStyleSpan.class);
        return lineHeightStyleSpanArr.length == 0 ? new LineHeightStyleSpan[0] : lineHeightStyleSpanArr;
    }

    public static final TextDirectionHeuristic getTextDirectionHeuristic(int i10) {
        return i10 != 0 ? i10 != 1 ? i10 != 2 ? i10 != 3 ? i10 != 4 ? i10 != 5 ? TextDirectionHeuristics.FIRSTSTRONG_LTR : TextDirectionHeuristics.LOCALE : TextDirectionHeuristics.ANYRTL_LTR : TextDirectionHeuristics.FIRSTSTRONG_RTL : TextDirectionHeuristics.FIRSTSTRONG_LTR : TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long getVerticalPaddings(TextLayout textLayout) {
        if (textLayout.getIncludePadding() || textLayout.isFallbackLinespacingApplied$ui_text_release()) {
            return ZeroVerticalPadding;
        }
        TextPaint paint = textLayout.getLayout().getPaint();
        CharSequence text = textLayout.getLayout().getText();
        Rect charSequenceBounds = PaintExtensionsKt.getCharSequenceBounds(paint, text, textLayout.getLayout().getLineStart(0), textLayout.getLayout().getLineEnd(0));
        int lineAscent = textLayout.getLayout().getLineAscent(0);
        int i10 = charSequenceBounds.top;
        int topPadding = i10 < lineAscent ? lineAscent - i10 : textLayout.getLayout().getTopPadding();
        if (textLayout.getLineCount() != 1) {
            int lineCount = textLayout.getLineCount() - 1;
            charSequenceBounds = PaintExtensionsKt.getCharSequenceBounds(paint, text, textLayout.getLayout().getLineStart(lineCount), textLayout.getLayout().getLineEnd(lineCount));
        }
        int lineDescent = textLayout.getLayout().getLineDescent(textLayout.getLineCount() - 1);
        int i11 = charSequenceBounds.bottom;
        int bottomPadding = i11 > lineDescent ? i11 - lineDescent : textLayout.getLayout().getBottomPadding();
        return (topPadding == 0 && bottomPadding == 0) ? ZeroVerticalPadding : VerticalPaddings(topPadding, bottomPadding);
    }

    public static final boolean isLineEllipsized(Layout layout, int i10) {
        return layout.getEllipsisCount(i10) > 0;
    }
}
