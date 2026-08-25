package androidx.compose.ui.text.font;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a6\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0000ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\f"}, d2 = {"synthesizeTypeface", "", "Landroidx/compose/ui/text/font/FontSynthesis;", "typeface", "font", "Landroidx/compose/ui/text/font/Font;", "requestedWeight", "Landroidx/compose/ui/text/font/FontWeight;", "requestedStyle", "Landroidx/compose/ui/text/font/FontStyle;", "synthesizeTypeface-FxwP2eA", "(ILjava/lang/Object;Landroidx/compose/ui/text/font/Font;Landroidx/compose/ui/text/font/FontWeight;I)Ljava/lang/Object;", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FontSynthesis_androidKt {
    /* JADX WARN: Removed duplicated region for block: B:14:0x0033  */
    /* JADX INFO: renamed from: synthesizeTypeface-FxwP2eA, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m5323synthesizeTypefaceFxwP2eA(int r5, java.lang.Object r6, androidx.compose.ui.text.font.Font r7, androidx.compose.ui.text.font.FontWeight r8, int r9) {
        /*
            boolean r0 = r6 instanceof android.graphics.Typeface
            if (r0 != 0) goto L5
            return r6
        L5:
            boolean r0 = androidx.compose.ui.text.font.FontSynthesis.m5316isWeightOnimpl$ui_text_release(r5)
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L33
            androidx.compose.ui.text.font.FontWeight r0 = r7.getWeight()
            boolean r0 = kotlin.jvm.internal.p.a(r0, r8)
            if (r0 != 0) goto L33
            androidx.compose.ui.text.font.FontWeight$Companion r0 = androidx.compose.ui.text.font.FontWeight.INSTANCE
            androidx.compose.ui.text.font.FontWeight r3 = androidx.compose.ui.text.font.AndroidFontUtils_androidKt.getAndroidBold(r0)
            int r3 = r8.compareTo(r3)
            if (r3 < 0) goto L33
            androidx.compose.ui.text.font.FontWeight r3 = r7.getWeight()
            androidx.compose.ui.text.font.FontWeight r0 = androidx.compose.ui.text.font.AndroidFontUtils_androidKt.getAndroidBold(r0)
            int r0 = r3.compareTo(r0)
            if (r0 >= 0) goto L33
            r0 = r2
            goto L34
        L33:
            r0 = r1
        L34:
            boolean r5 = androidx.compose.ui.text.font.FontSynthesis.m5315isStyleOnimpl$ui_text_release(r5)
            if (r5 == 0) goto L46
            int r5 = r7.getStyle()
            boolean r5 = androidx.compose.ui.text.font.FontStyle.m5302equalsimpl0(r9, r5)
            if (r5 != 0) goto L46
            r5 = r2
            goto L47
        L46:
            r5 = r1
        L47:
            if (r5 != 0) goto L4c
            if (r0 != 0) goto L4c
            return r6
        L4c:
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 28
            if (r3 >= r4) goto L6c
            if (r5 == 0) goto L61
            androidx.compose.ui.text.font.FontStyle$Companion r5 = androidx.compose.ui.text.font.FontStyle.INSTANCE
            int r5 = r5.m5308getItalic_LCdwA()
            boolean r5 = androidx.compose.ui.text.font.FontStyle.m5302equalsimpl0(r9, r5)
            if (r5 == 0) goto L61
            r1 = r2
        L61:
            int r5 = androidx.compose.ui.text.font.AndroidFontUtils_androidKt.getAndroidTypefaceStyle(r0, r1)
            android.graphics.Typeface r6 = (android.graphics.Typeface) r6
            android.graphics.Typeface r5 = android.graphics.Typeface.create(r6, r5)
            return r5
        L6c:
            if (r0 == 0) goto L73
            int r8 = r8.getWeight()
            goto L7b
        L73:
            androidx.compose.ui.text.font.FontWeight r8 = r7.getWeight()
            int r8 = r8.getWeight()
        L7b:
            if (r5 == 0) goto L88
            androidx.compose.ui.text.font.FontStyle$Companion r5 = androidx.compose.ui.text.font.FontStyle.INSTANCE
            int r5 = r5.m5308getItalic_LCdwA()
            boolean r5 = androidx.compose.ui.text.font.FontStyle.m5302equalsimpl0(r9, r5)
            goto L96
        L88:
            int r5 = r7.getStyle()
            androidx.compose.ui.text.font.FontStyle$Companion r7 = androidx.compose.ui.text.font.FontStyle.INSTANCE
            int r7 = r7.m5308getItalic_LCdwA()
            boolean r5 = androidx.compose.ui.text.font.FontStyle.m5302equalsimpl0(r5, r7)
        L96:
            androidx.compose.ui.text.font.TypefaceHelperMethodsApi28 r7 = androidx.compose.ui.text.font.TypefaceHelperMethodsApi28.INSTANCE
            android.graphics.Typeface r6 = (android.graphics.Typeface) r6
            android.graphics.Typeface r5 = r7.create(r6, r8, r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.font.FontSynthesis_androidKt.m5323synthesizeTypefaceFxwP2eA(int, java.lang.Object, androidx.compose.ui.text.font.Font, androidx.compose.ui.text.font.FontWeight, int):java.lang.Object");
    }
}
