package androidx.compose.material3;

import android.content.res.Configuration;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aq\u0010\u0013\u001a\u00020\u000e*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0002\b\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a@\u0010\u001e\u001a\u00020\u001b*\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00032\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0003ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001f"}, d2 = {"Landroidx/compose/material3/CaretScope;", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/material3/CaretProperties;", "caretProperties", "Landroidx/compose/ui/graphics/Shape;", "shape", "Landroidx/compose/ui/graphics/Color;", "contentColor", "containerColor", "Landroidx/compose/ui/unit/Dp;", "tonalElevation", "shadowElevation", "Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "content", "PlainTooltip-Fg7CxbU", "(Landroidx/compose/material3/CaretScope;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/CaretProperties;Landroidx/compose/ui/graphics/Shape;JJFFLr7/p;Landroidx/compose/runtime/Composer;II)V", "PlainTooltip", "Landroidx/compose/ui/draw/CacheDrawScope;", "Landroidx/compose/ui/unit/Density;", "density", "Landroid/content/res/Configuration;", "configuration", "Landroidx/compose/ui/layout/LayoutCoordinates;", "anchorLayoutCoordinates", "Landroidx/compose/ui/draw/DrawResult;", "drawCaretWithPath-Bx497Mc", "(Landroidx/compose/ui/draw/CacheDrawScope;Landroidx/compose/ui/unit/Density;Landroid/content/res/Configuration;JLandroidx/compose/material3/CaretProperties;Landroidx/compose/ui/layout/LayoutCoordinates;)Landroidx/compose/ui/draw/DrawResult;", "drawCaretWithPath", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class Tooltip_androidKt {
    /* JADX WARN: Removed duplicated region for block: B:101:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x02b2  */
    /* JADX WARN: Removed duplicated region for block: B:179:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00ff  */
    /* JADX INFO: renamed from: PlainTooltip-Fg7CxbU, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m2300PlainTooltipFg7CxbU(androidx.compose.material3.CaretScope r29, androidx.compose.ui.Modifier r30, androidx.compose.material3.CaretProperties r31, androidx.compose.ui.graphics.Shape r32, long r33, long r35, float r37, float r38, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r39, androidx.compose.runtime.Composer r40, int r41, int r42) {
        /*
            Method dump skipped, instruction units count: 699
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.Tooltip_androidKt.m2300PlainTooltipFg7CxbU(androidx.compose.material3.CaretScope, androidx.compose.ui.Modifier, androidx.compose.material3.CaretProperties, androidx.compose.ui.graphics.Shape, long, long, float, float, r7.p, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawCaretWithPath-Bx497Mc, reason: not valid java name */
    public static final DrawResult m2302drawCaretWithPathBx497Mc(CacheDrawScope cacheDrawScope, Density density, Configuration configuration, long j10, CaretProperties caretProperties, LayoutCoordinates layoutCoordinates) {
        Path Path = AndroidPath_androidKt.Path();
        if (layoutCoordinates != null) {
            int iMo279roundToPx0680j_4 = density.mo279roundToPx0680j_4(caretProperties.m1298getCaretHeightD9Ej5fM());
            int iMo279roundToPx0680j_42 = density.mo279roundToPx0680j_4(caretProperties.m1299getCaretWidthD9Ej5fM());
            int iMo279roundToPx0680j_43 = density.mo279roundToPx0680j_4(Dp.m5678constructorimpl(configuration.screenWidthDp));
            int iMo279roundToPx0680j_44 = density.mo279roundToPx0680j_4(TooltipKt.getSpacingBetweenTooltipAndAnchor());
            Rect rectBoundsInWindow = LayoutCoordinatesKt.boundsInWindow(layoutCoordinates);
            float left = rectBoundsInWindow.getLeft();
            float right = rectBoundsInWindow.getRight();
            float top = rectBoundsInWindow.getTop();
            float f10 = 2;
            float f11 = (right + left) / f10;
            float f12 = right - left;
            float fM3305getWidthimpl = Size.m3305getWidthimpl(cacheDrawScope.m3140getSizeNHjbRc());
            float fM3302getHeightimpl = Size.m3302getHeightimpl(cacheDrawScope.m3140getSizeNHjbRc());
            boolean z = (top - fM3302getHeightimpl) - ((float) iMo279roundToPx0680j_44) < 0.0f;
            if (z) {
                fM3302getHeightimpl = 0.0f;
            }
            float f13 = iMo279roundToPx0680j_43;
            long jOffset = (fM3305getWidthimpl / f10) + f11 > f13 ? OffsetKt.Offset(fM3305getWidthimpl - (f13 - f11), fM3302getHeightimpl) : OffsetKt.Offset(f11 - Math.max(left - ((Size.m3305getWidthimpl(cacheDrawScope.m3140getSizeNHjbRc()) / f10) - (f12 / f10)), 0.0f), fM3302getHeightimpl);
            if (z) {
                Path.moveTo(Offset.m3236getXimpl(jOffset), Offset.m3237getYimpl(jOffset));
                float f14 = iMo279roundToPx0680j_42 / 2;
                Path.lineTo(Offset.m3236getXimpl(jOffset) + f14, Offset.m3237getYimpl(jOffset));
                Path.lineTo(Offset.m3236getXimpl(jOffset), Offset.m3237getYimpl(jOffset) - iMo279roundToPx0680j_4);
                Path.lineTo(Offset.m3236getXimpl(jOffset) - f14, Offset.m3237getYimpl(jOffset));
                Path.close();
            } else {
                Path.moveTo(Offset.m3236getXimpl(jOffset), Offset.m3237getYimpl(jOffset));
                float f15 = iMo279roundToPx0680j_42 / 2;
                Path.lineTo(Offset.m3236getXimpl(jOffset) + f15, Offset.m3237getYimpl(jOffset));
                Path.lineTo(Offset.m3236getXimpl(jOffset), Offset.m3237getYimpl(jOffset) + iMo279roundToPx0680j_4);
                Path.lineTo(Offset.m3236getXimpl(jOffset) - f15, Offset.m3237getYimpl(jOffset));
                Path.close();
            }
        }
        return cacheDrawScope.onDrawWithContent(new Tooltip_androidKt$drawCaretWithPath$4(layoutCoordinates, Path, j10));
    }
}
