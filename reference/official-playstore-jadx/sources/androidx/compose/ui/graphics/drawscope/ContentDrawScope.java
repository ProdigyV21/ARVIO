package androidx.compose.ui.graphics.drawscope;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.unit.DpRect;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0005À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lx6/t0;", "drawContent", "()V", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ContentDrawScope extends DrawScope {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        /* JADX INFO: renamed from: drawImage-AZ2fEMs, reason: not valid java name */
        public static void m3931drawImageAZ2fEMs(ContentDrawScope contentDrawScope, ImageBitmap imageBitmap, long j10, long j11, long j12, long j13, float f10, DrawStyle drawStyle, ColorFilter colorFilter, int i10, int i11) {
            c.a(contentDrawScope, imageBitmap, j10, j11, j12, j13, f10, drawStyle, colorFilter, i10, i11);
        }

        @Deprecated
        /* JADX INFO: renamed from: getCenter-F1C5BW0, reason: not valid java name */
        public static long m3932getCenterF1C5BW0(ContentDrawScope contentDrawScope) {
            return c.b(contentDrawScope);
        }

        @Deprecated
        /* JADX INFO: renamed from: getSize-NH-jbRc, reason: not valid java name */
        public static long m3933getSizeNHjbRc(ContentDrawScope contentDrawScope) {
            return c.c(contentDrawScope);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m3934roundToPxR2X_6o(ContentDrawScope contentDrawScope, long j10) {
            return androidx.compose.ui.unit.a.a(contentDrawScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m3935roundToPx0680j_4(ContentDrawScope contentDrawScope, float f10) {
            return androidx.compose.ui.unit.a.b(contentDrawScope, f10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m3936toDpGaN1DYA(ContentDrawScope contentDrawScope, long j10) {
            return androidx.compose.ui.unit.b.a(contentDrawScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m3937toDpu2uoSUM(ContentDrawScope contentDrawScope, float f10) {
            return androidx.compose.ui.unit.a.c(contentDrawScope, f10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m3939toDpSizekrfVVM(ContentDrawScope contentDrawScope, long j10) {
            return androidx.compose.ui.unit.a.e(contentDrawScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m3940toPxR2X_6o(ContentDrawScope contentDrawScope, long j10) {
            return androidx.compose.ui.unit.a.f(contentDrawScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m3941toPx0680j_4(ContentDrawScope contentDrawScope, float f10) {
            return androidx.compose.ui.unit.a.g(contentDrawScope, f10);
        }

        @Deprecated
        public static Rect toRect(ContentDrawScope contentDrawScope, DpRect dpRect) {
            return androidx.compose.ui.unit.a.h(contentDrawScope, dpRect);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m3942toSizeXkaWNTQ(ContentDrawScope contentDrawScope, long j10) {
            return androidx.compose.ui.unit.a.i(contentDrawScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m3943toSp0xMU5do(ContentDrawScope contentDrawScope, float f10) {
            return androidx.compose.ui.unit.b.b(contentDrawScope, f10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m3944toSpkPz2Gy4(ContentDrawScope contentDrawScope, float f10) {
            return androidx.compose.ui.unit.a.j(contentDrawScope, f10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m3938toDpu2uoSUM(ContentDrawScope contentDrawScope, int i10) {
            return androidx.compose.ui.unit.a.d(contentDrawScope, i10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m3945toSpkPz2Gy4(ContentDrawScope contentDrawScope, int i10) {
            return androidx.compose.ui.unit.a.k(contentDrawScope, i10);
        }
    }

    void drawContent();
}
