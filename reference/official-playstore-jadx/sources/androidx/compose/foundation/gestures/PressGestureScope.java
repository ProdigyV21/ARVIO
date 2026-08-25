package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u0002H¦@¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0006\u001a\u00020\u0005H¦@¢\u0006\u0004\b\u0006\u0010\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/PressGestureScope;", "Landroidx/compose/ui/unit/Density;", "Lx6/t0;", "awaitRelease", "(Ld7/d;)Ljava/lang/Object;", "", "tryAwaitRelease", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface PressGestureScope extends Density {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m354roundToPxR2X_6o(PressGestureScope pressGestureScope, long j10) {
            return androidx.compose.ui.unit.a.a(pressGestureScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m355roundToPx0680j_4(PressGestureScope pressGestureScope, float f10) {
            return androidx.compose.ui.unit.a.b(pressGestureScope, f10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m356toDpGaN1DYA(PressGestureScope pressGestureScope, long j10) {
            return androidx.compose.ui.unit.b.a(pressGestureScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m357toDpu2uoSUM(PressGestureScope pressGestureScope, float f10) {
            return androidx.compose.ui.unit.a.c(pressGestureScope, f10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m359toDpSizekrfVVM(PressGestureScope pressGestureScope, long j10) {
            return androidx.compose.ui.unit.a.e(pressGestureScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m360toPxR2X_6o(PressGestureScope pressGestureScope, long j10) {
            return androidx.compose.ui.unit.a.f(pressGestureScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m361toPx0680j_4(PressGestureScope pressGestureScope, float f10) {
            return androidx.compose.ui.unit.a.g(pressGestureScope, f10);
        }

        @Deprecated
        public static Rect toRect(PressGestureScope pressGestureScope, DpRect dpRect) {
            return androidx.compose.ui.unit.a.h(pressGestureScope, dpRect);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m362toSizeXkaWNTQ(PressGestureScope pressGestureScope, long j10) {
            return androidx.compose.ui.unit.a.i(pressGestureScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m363toSp0xMU5do(PressGestureScope pressGestureScope, float f10) {
            return androidx.compose.ui.unit.b.b(pressGestureScope, f10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m364toSpkPz2Gy4(PressGestureScope pressGestureScope, float f10) {
            return androidx.compose.ui.unit.a.j(pressGestureScope, f10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m358toDpu2uoSUM(PressGestureScope pressGestureScope, int i10) {
            return androidx.compose.ui.unit.a.d(pressGestureScope, i10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m365toSpkPz2Gy4(PressGestureScope pressGestureScope, int i10) {
            return androidx.compose.ui.unit.a.k(pressGestureScope, i10);
        }
    }

    Object awaitRelease(d7.d<? super t0> dVar);

    Object tryAwaitRelease(d7.d<? super Boolean> dVar);
}
