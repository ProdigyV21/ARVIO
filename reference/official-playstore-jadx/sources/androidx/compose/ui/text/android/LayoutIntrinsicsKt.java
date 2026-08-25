package androidx.compose.ui.text.android;

import android.text.Layout;
import android.text.Spanned;
import android.text.TextPaint;
import androidx.compose.ui.node.d;
import androidx.compose.ui.text.android.style.LetterSpacingSpanEm;
import androidx.compose.ui.text.android.style.LetterSpacingSpanPx;
import java.text.BreakIterator;
import java.util.PriorityQueue;
import kotlin.Metadata;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0005H\u0002¨\u0006\u000b"}, d2 = {"minIntrinsicWidth", "", "text", "", "paint", "Landroid/text/TextPaint;", "shouldIncreaseMaxIntrinsic", "", "desiredWidth", "charSequence", "textPaint", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LayoutIntrinsicsKt {
    public static final float minIntrinsicWidth(CharSequence charSequence, TextPaint textPaint) {
        BreakIterator lineInstance = BreakIterator.getLineInstance(textPaint.getTextLocale());
        int i10 = 0;
        lineInstance.setText(new CharSequenceCharacterIterator(charSequence, 0, charSequence.length()));
        PriorityQueue<x> priorityQueue = new PriorityQueue(10, new d(1));
        int next = lineInstance.next();
        while (true) {
            int i11 = i10;
            i10 = next;
            if (i10 == -1) {
                break;
            }
            if (priorityQueue.size() < 10) {
                priorityQueue.add(new x(Integer.valueOf(i11), Integer.valueOf(i10)));
            } else {
                x xVar = (x) priorityQueue.peek();
                if (xVar != null && ((Number) xVar.f22609l).intValue() - ((Number) xVar.f22608i).intValue() < i10 - i11) {
                    priorityQueue.poll();
                    priorityQueue.add(new x(Integer.valueOf(i11), Integer.valueOf(i10)));
                }
            }
            next = lineInstance.next();
        }
        float fMax = 0.0f;
        for (x xVar2 : priorityQueue) {
            fMax = Math.max(fMax, Layout.getDesiredWidth(charSequence, ((Number) xVar2.f22608i).intValue(), ((Number) xVar2.f22609l).intValue(), textPaint));
        }
        return fMax;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int minIntrinsicWidth$lambda$0(x xVar, x xVar2) {
        return (((Number) xVar.f22609l).intValue() - ((Number) xVar.f22608i).intValue()) - (((Number) xVar2.f22609l).intValue() - ((Number) xVar2.f22608i).intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean shouldIncreaseMaxIntrinsic(float f10, CharSequence charSequence, TextPaint textPaint) {
        if (f10 == 0.0f) {
            return false;
        }
        if (charSequence instanceof Spanned) {
            Spanned spanned = (Spanned) charSequence;
            if (SpannedExtensionsKt.hasSpan(spanned, LetterSpacingSpanPx.class) || SpannedExtensionsKt.hasSpan(spanned, LetterSpacingSpanEm.class)) {
                return true;
            }
        }
        return textPaint.getLetterSpacing() != 0.0f;
    }
}
