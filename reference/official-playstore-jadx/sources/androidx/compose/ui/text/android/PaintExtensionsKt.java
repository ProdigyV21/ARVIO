package androidx.compose.ui.text.android;

import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a+\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a\u001b\u0010\u000b\u001a\u00020\n*\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000b\u0010\f\u001a+\u0010\u0010\u001a\u00020\u0006*\u00020\r2\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003H\u0001¢\u0006\u0004\b\u0010\u0010\u0011\u001a3\u0010\u0012\u001a\u00020\n*\u00020\r2\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Landroid/text/TextPaint;", "", "text", "", "startInclusive", "endExclusive", "Landroid/graphics/Rect;", "getCharSequenceBounds", "(Landroid/text/TextPaint;Ljava/lang/CharSequence;II)Landroid/graphics/Rect;", "rect", "Lx6/t0;", "extendWith", "(Landroid/graphics/Rect;Landroid/graphics/Rect;)V", "Landroid/graphics/Paint;", TtmlNode.START, TtmlNode.END, "getStringBounds", "(Landroid/graphics/Paint;Ljava/lang/CharSequence;II)Landroid/graphics/Rect;", "fillStringBounds", "(Landroid/graphics/Paint;Ljava/lang/CharSequence;IILandroid/graphics/Rect;)V", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PaintExtensionsKt {
    private static final void extendWith(Rect rect, Rect rect2) {
        rect.right = rect2.width() + rect.right;
        rect.top = Math.min(rect.top, rect2.top);
        rect.bottom = Math.max(rect.bottom, rect2.bottom);
    }

    private static final void fillStringBounds(Paint paint, CharSequence charSequence, int i10, int i11, Rect rect) {
        if (Build.VERSION.SDK_INT >= 29) {
            Paint29.getTextBounds(paint, charSequence, i10, i11, rect);
        } else {
            paint.getTextBounds(charSequence.toString(), i10, i11, rect);
        }
    }

    public static final Rect getCharSequenceBounds(TextPaint textPaint, CharSequence charSequence, int i10, int i11) {
        if (charSequence instanceof Spanned) {
            Spanned spanned = (Spanned) charSequence;
            if (SpannedExtensionsKt.hasSpan(spanned, MetricAffectingSpan.class, i10, i11)) {
                Rect rect = new Rect();
                Rect rect2 = new Rect();
                TextPaint textPaint2 = new TextPaint();
                while (i10 < i11) {
                    int iNextSpanTransition = spanned.nextSpanTransition(i10, i11, MetricAffectingSpan.class);
                    MetricAffectingSpan[] metricAffectingSpanArr = (MetricAffectingSpan[]) spanned.getSpans(i10, iNextSpanTransition, MetricAffectingSpan.class);
                    textPaint2.set(textPaint);
                    for (MetricAffectingSpan metricAffectingSpan : metricAffectingSpanArr) {
                        if (spanned.getSpanStart(metricAffectingSpan) != spanned.getSpanEnd(metricAffectingSpan)) {
                            metricAffectingSpan.updateMeasureState(textPaint2);
                        }
                    }
                    fillStringBounds(textPaint2, charSequence, i10, iNextSpanTransition, rect2);
                    extendWith(rect, rect2);
                    i10 = iNextSpanTransition;
                }
                return rect;
            }
        }
        return getStringBounds(textPaint, charSequence, i10, i11);
    }

    public static final Rect getStringBounds(Paint paint, CharSequence charSequence, int i10, int i11) {
        Rect rect = new Rect();
        fillStringBounds(paint, charSequence, i10, i11, rect);
        return rect;
    }
}
