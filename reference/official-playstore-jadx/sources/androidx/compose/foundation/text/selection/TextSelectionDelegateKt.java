package androidx.compose.foundation.text.selection;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.text.TextLayoutResult;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\u001a-\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0000¢\u0006\u0002\u0010\t\u001a$\u0010\n\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0000¨\u0006\f"}, d2 = {"getSelectionHandleCoordinates", "Landroidx/compose/ui/geometry/Offset;", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "offset", "", "isStart", "", "areHandlesCrossed", "(Landroidx/compose/ui/text/TextLayoutResult;IZZ)J", "getHorizontalPosition", "", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextSelectionDelegateKt {
    public static final float getHorizontalPosition(TextLayoutResult textLayoutResult, int i10, boolean z, boolean z5) {
        return textLayoutResult.getHorizontalPosition(i10, textLayoutResult.getBidiRunDirection(((!z || z5) && (z || !z5)) ? Math.max(i10 + (-1), 0) : i10) == textLayoutResult.getParagraphDirection(i10));
    }

    public static final long getSelectionHandleCoordinates(TextLayoutResult textLayoutResult, int i10, boolean z, boolean z5) {
        int lineForOffset = textLayoutResult.getLineForOffset(i10);
        return lineForOffset >= textLayoutResult.getLineCount() ? Offset.INSTANCE.m3251getUnspecifiedF1C5BW0() : OffsetKt.Offset(getHorizontalPosition(textLayoutResult, i10, z, z5), textLayoutResult.getLineBottom(lineForOffset));
    }
}
