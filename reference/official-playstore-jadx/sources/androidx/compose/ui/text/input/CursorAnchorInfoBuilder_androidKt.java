package androidx.compose.ui.text.input;

import android.graphics.Matrix;
import android.os.Build;
import android.view.inputmethod.CursorAnchorInfo;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import qb.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0004\u001a4\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002\u001ad\u0010\u000b\u001a\u00020\f*\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\n2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u0013H\u0000\u001a\u001c\u0010\u0017\u001a\u00020\u0013*\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0002\u001a,\u0010\u001b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002¨\u0006\u001d"}, d2 = {"addCharacterBounds", "Landroid/view/inputmethod/CursorAnchorInfo$Builder;", "startOffset", "", "endOffset", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "innerTextFieldBounds", "Landroidx/compose/ui/geometry/Rect;", "build", "Landroid/view/inputmethod/CursorAnchorInfo;", "textFieldValue", "Landroidx/compose/ui/text/input/TextFieldValue;", "matrix", "Landroid/graphics/Matrix;", "decorationBoxBounds", "includeInsertionMarker", "", "includeCharacterBounds", "includeEditorBounds", "includeLineBounds", "containsInclusive", "x", "", "y", "setInsertionMarker", "selectionStart", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CursorAnchorInfoBuilder_androidKt {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0064  */
    /* JADX WARN: Type inference failed for: r16v0 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final android.view.inputmethod.CursorAnchorInfo.Builder addCharacterBounds(android.view.inputmethod.CursorAnchorInfo.Builder r17, int r18, int r19, androidx.compose.ui.text.input.OffsetMapping r20, androidx.compose.ui.text.TextLayoutResult r21, androidx.compose.ui.geometry.Rect r22) {
        /*
            r0 = r19
            r1 = r20
            r2 = r18
            r3 = r22
            int r4 = r1.originalToTransformed(r2)
            int r5 = r1.originalToTransformed(r0)
            int r6 = r5 - r4
            int r6 = r6 * 4
            float[] r6 = new float[r6]
            androidx.compose.ui.text.MultiParagraph r7 = r21.getMultiParagraph()
            long r8 = androidx.compose.ui.text.TextRangeKt.TextRange(r4, r5)
            r5 = 0
            r7.m5071fillBoundingBoxes8ffj60Q(r8, r6, r5)
            r11 = r2
        L23:
            if (r11 >= r0) goto L8b
            int r2 = r1.originalToTransformed(r11)
            int r5 = r2 - r4
            int r5 = r5 * 4
            androidx.compose.ui.geometry.Rect r7 = new androidx.compose.ui.geometry.Rect
            r8 = r6[r5]
            int r9 = r5 + 1
            r9 = r6[r9]
            int r10 = r5 + 2
            r10 = r6[r10]
            int r5 = r5 + 3
            r5 = r6[r5]
            r7.<init>(r8, r9, r10, r5)
            boolean r5 = r3.overlaps(r7)
            float r8 = r7.getLeft()
            float r9 = r7.getTop()
            boolean r8 = containsInclusive(r3, r8, r9)
            if (r8 == 0) goto L64
            float r8 = r7.getRight()
            float r9 = r7.getBottom()
            boolean r8 = containsInclusive(r3, r8, r9)
            if (r8 != 0) goto L61
            goto L64
        L61:
            r8 = r21
            goto L67
        L64:
            r5 = r5 | 2
            goto L61
        L67:
            androidx.compose.ui.text.style.ResolvedTextDirection r2 = r8.getBidiRunDirection(r2)
            androidx.compose.ui.text.style.ResolvedTextDirection r9 = androidx.compose.ui.text.style.ResolvedTextDirection.Rtl
            if (r2 != r9) goto L71
            r5 = r5 | 4
        L71:
            r16 = r5
            float r12 = r7.getLeft()
            float r13 = r7.getTop()
            float r14 = r7.getRight()
            float r15 = r7.getBottom()
            r10 = r17
            r10.addCharacterBounds(r11, r12, r13, r14, r15, r16)
            int r11 = r11 + 1
            goto L23
        L8b:
            return r17
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.input.CursorAnchorInfoBuilder_androidKt.addCharacterBounds(android.view.inputmethod.CursorAnchorInfo$Builder, int, int, androidx.compose.ui.text.input.OffsetMapping, androidx.compose.ui.text.TextLayoutResult, androidx.compose.ui.geometry.Rect):android.view.inputmethod.CursorAnchorInfo$Builder");
    }

    public static final CursorAnchorInfo build(CursorAnchorInfo.Builder builder, TextFieldValue textFieldValue, OffsetMapping offsetMapping, TextLayoutResult textLayoutResult, Matrix matrix, Rect rect, Rect rect2, boolean z, boolean z5, boolean z10, boolean z11) {
        builder.reset();
        builder.setMatrix(matrix);
        int iM5190getMinimpl = TextRange.m5190getMinimpl(textFieldValue.getSelection());
        builder.setSelectionRange(iM5190getMinimpl, TextRange.m5189getMaximpl(textFieldValue.getSelection()));
        if (z) {
            setInsertionMarker(builder, iM5190getMinimpl, offsetMapping, textLayoutResult, rect);
        }
        if (z5) {
            TextRange composition = textFieldValue.getComposition();
            int iM5190getMinimpl2 = composition != null ? TextRange.m5190getMinimpl(composition.getPackedValue()) : -1;
            TextRange composition2 = textFieldValue.getComposition();
            int iM5189getMaximpl = composition2 != null ? TextRange.m5189getMaximpl(composition2.getPackedValue()) : -1;
            if (iM5190getMinimpl2 >= 0 && iM5190getMinimpl2 < iM5189getMaximpl) {
                builder.setComposingText(iM5190getMinimpl2, textFieldValue.getText().subSequence(iM5190getMinimpl2, iM5189getMaximpl));
                addCharacterBounds(builder, iM5190getMinimpl2, iM5189getMaximpl, offsetMapping, textLayoutResult, rect);
            }
        }
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 33 && z10) {
            CursorAnchorInfoApi33Helper.setEditorBoundsInfo(builder, rect2);
        }
        if (i10 >= 34 && z11) {
            CursorAnchorInfoApi34Helper.addVisibleLineBounds(builder, textLayoutResult, rect);
        }
        return builder.build();
    }

    public static /* synthetic */ CursorAnchorInfo build$default(CursorAnchorInfo.Builder builder, TextFieldValue textFieldValue, OffsetMapping offsetMapping, TextLayoutResult textLayoutResult, Matrix matrix, Rect rect, Rect rect2, boolean z, boolean z5, boolean z10, boolean z11, int i10, Object obj) {
        if ((i10 & 64) != 0) {
            z = true;
        }
        if ((i10 & 128) != 0) {
            z5 = true;
        }
        if ((i10 & 256) != 0) {
            z10 = true;
        }
        if ((i10 & 512) != 0) {
            z11 = true;
        }
        return build(builder, textFieldValue, offsetMapping, textLayoutResult, matrix, rect, rect2, z, z5, z10, z11);
    }

    private static final boolean containsInclusive(Rect rect, float f10, float f11) {
        float left = rect.getLeft();
        if (f10 > rect.getRight() || left > f10) {
            return false;
        }
        return f11 <= rect.getBottom() && rect.getTop() <= f11;
    }

    private static final CursorAnchorInfo.Builder setInsertionMarker(CursorAnchorInfo.Builder builder, int i10, OffsetMapping offsetMapping, TextLayoutResult textLayoutResult, Rect rect) {
        if (i10 < 0) {
            return builder;
        }
        int iOriginalToTransformed = offsetMapping.originalToTransformed(i10);
        Rect cursorRect = textLayoutResult.getCursorRect(iOriginalToTransformed);
        float fM = d.m(cursorRect.getLeft(), 0.0f, IntSize.m5844getWidthimpl(textLayoutResult.getSize()));
        boolean zContainsInclusive = containsInclusive(rect, fM, cursorRect.getTop());
        boolean zContainsInclusive2 = containsInclusive(rect, fM, cursorRect.getBottom());
        boolean z = textLayoutResult.getBidiRunDirection(iOriginalToTransformed) == ResolvedTextDirection.Rtl;
        int i11 = (zContainsInclusive || zContainsInclusive2) ? 1 : 0;
        if (!zContainsInclusive || !zContainsInclusive2) {
            i11 |= 2;
        }
        if (z) {
            i11 |= 4;
        }
        builder.setInsertionMarkerLocation(fM, cursorRect.getTop(), cursorRect.getBottom(), cursorRect.getBottom(), i11);
        return builder;
    }
}
