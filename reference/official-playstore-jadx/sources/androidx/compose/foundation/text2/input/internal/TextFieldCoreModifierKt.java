package androidx.compose.foundation.text2.input.internal;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a.\u0010\n\u001a\u00020\u000b*\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u000eH\u0002\u001a\f\u0010\u0013\u001a\u00020\u0005*\u00020\u0005H\u0002\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0018\u0010\u0006\u001a\u00020\u0007*\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\t¨\u0006\u0014"}, d2 = {"DefaultCursorThickness", "Landroidx/compose/ui/unit/Dp;", "F", "cursorAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "isSpecified", "", "Landroidx/compose/ui/graphics/Brush;", "(Landroidx/compose/ui/graphics/Brush;)Z", "getCursorRectInScroller", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/unit/Density;", "cursorOffset", "", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "rtl", "textFieldWidth", "roundToNext", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextFieldCoreModifierKt {
    private static final AnimationSpec<Float> cursorAnimationSpec = AnimationSpecKt.m93infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(TextFieldCoreModifierKt$cursorAnimationSpec$1.INSTANCE), null, 0, 6, null);
    private static final float DefaultCursorThickness = Dp.m5678constructorimpl(2);

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.ui.geometry.Rect getCursorRectInScroller(androidx.compose.ui.unit.Density r7, int r8, androidx.compose.ui.text.TextLayoutResult r9, boolean r10, int r11) {
        /*
            if (r9 == 0) goto L79
            x7.i r0 = new x7.i
            androidx.compose.ui.text.TextLayoutInput r1 = r9.getLayoutInput()
            androidx.compose.ui.text.AnnotatedString r1 = r1.getText()
            int r1 = r1.length()
            r2 = 1
            r3 = 0
            r0.<init>(r3, r1, r2)
            boolean r1 = r0 instanceof x7.e
            if (r1 == 0) goto L2a
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            x7.e r0 = (x7.e) r0
            java.lang.Comparable r8 = qb.d.q(r8, r0)
            java.lang.Number r8 = (java.lang.Number) r8
            int r8 = r8.intValue()
            goto L57
        L2a:
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L60
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)
            int r1 = r1.intValue()
            if (r8 >= r1) goto L43
            java.lang.Integer r8 = java.lang.Integer.valueOf(r3)
            int r8 = r8.intValue()
            goto L57
        L43:
            int r0 = r0.f22620l
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
            int r1 = r1.intValue()
            if (r8 <= r1) goto L57
            java.lang.Integer r8 = java.lang.Integer.valueOf(r0)
            int r8 = r8.intValue()
        L57:
            androidx.compose.ui.geometry.Rect r8 = r9.getCursorRect(r8)
            if (r8 != 0) goto L5e
            goto L79
        L5e:
            r0 = r8
            goto L80
        L60:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "Cannot coerce value to an empty range: "
            r8.<init>(r9)
            r8.append(r0)
            r9 = 46
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L79:
            androidx.compose.ui.geometry.Rect$Companion r8 = androidx.compose.ui.geometry.Rect.INSTANCE
            androidx.compose.ui.geometry.Rect r8 = r8.getZero()
            goto L5e
        L80:
            float r8 = androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierKt.DefaultCursorThickness
            int r7 = r7.mo279roundToPx0680j_4(r8)
            if (r10 == 0) goto L92
            float r8 = (float) r11
            float r9 = r0.getLeft()
            float r8 = r8 - r9
            float r9 = (float) r7
            float r8 = r8 - r9
        L90:
            r1 = r8
            goto L97
        L92:
            float r8 = r0.getLeft()
            goto L90
        L97:
            if (r10 == 0) goto La1
            float r7 = (float) r11
            float r8 = r0.getLeft()
            float r7 = r7 - r8
        L9f:
            r3 = r7
            goto La8
        La1:
            float r8 = r0.getLeft()
            float r7 = (float) r7
            float r7 = r7 + r8
            goto L9f
        La8:
            r5 = 10
            r6 = 0
            r2 = 0
            r4 = 0
            androidx.compose.ui.geometry.Rect r7 = androidx.compose.ui.geometry.Rect.copy$default(r0, r1, r2, r3, r4, r5, r6)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text2.input.internal.TextFieldCoreModifierKt.getCursorRectInScroller(androidx.compose.ui.unit.Density, int, androidx.compose.ui.text.TextLayoutResult, boolean, int):androidx.compose.ui.geometry.Rect");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isSpecified(Brush brush) {
        return ((brush instanceof SolidColor) && ((SolidColor) brush).getValue() == Color.INSTANCE.m3508getUnspecified0d7_KjU()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float roundToNext(float f10) {
        if (Float.isNaN(f10) || Float.isInfinite(f10)) {
            return f10;
        }
        return (float) (f10 > 0.0f ? Math.ceil(f10) : Math.floor(f10));
    }
}
