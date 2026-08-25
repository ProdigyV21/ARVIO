package androidx.compose.ui.input.pointer;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import d7.d;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\bf\u0018\u00002\u00020\u0001J?\u0010\t\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00022'\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0003¢\u0006\u0002\b\u0007H¦@¢\u0006\u0004\b\t\u0010\nR\u001a\u0010\u000e\u001a\u00020\u000b8&X¦\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0011\u001a\u00020\u000f8VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0010\u0010\rR\u0014\u0010\u0015\u001a\u00020\u00128&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R*\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00168V@VX\u0096\u000e¢\u0006\u0012\u0012\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bø\u0001\u0002\u0082\u0002\u0011\n\u0005\b¡\u001e0\u0001\n\u0002\b!\n\u0004\b!0\u0001¨\u0006\u001fÀ\u0006\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Landroidx/compose/ui/unit/Density;", "R", "Lkotlin/Function2;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Ld7/d;", "", "Lx6/n;", "block", "awaitPointerEventScope", "(Lr7/p;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g", "()J", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/geometry/Size;", "getExtendedTouchPadding-NH-jbRc", "extendedTouchPadding", "Landroidx/compose/ui/platform/ViewConfiguration;", "getViewConfiguration", "()Landroidx/compose/ui/platform/ViewConfiguration;", "viewConfiguration", "", "<anonymous parameter 0>", "getInterceptOutOfBoundsChildEvents", "()Z", "setInterceptOutOfBoundsChildEvents", "(Z)V", "getInterceptOutOfBoundsChildEvents$annotations", "()V", "interceptOutOfBoundsChildEvents", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface PointerInputScope extends Density {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        /* JADX INFO: renamed from: getExtendedTouchPadding-NH-jbRc, reason: not valid java name */
        public static long m4543getExtendedTouchPaddingNHjbRc(PointerInputScope pointerInputScope) {
            return c.a(pointerInputScope);
        }

        @Deprecated
        public static boolean getInterceptOutOfBoundsChildEvents(PointerInputScope pointerInputScope) {
            return c.b(pointerInputScope);
        }

        public static /* synthetic */ void getInterceptOutOfBoundsChildEvents$annotations() {
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m4544roundToPxR2X_6o(PointerInputScope pointerInputScope, long j10) {
            return androidx.compose.ui.unit.a.a(pointerInputScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m4545roundToPx0680j_4(PointerInputScope pointerInputScope, float f10) {
            return androidx.compose.ui.unit.a.b(pointerInputScope, f10);
        }

        @Deprecated
        public static void setInterceptOutOfBoundsChildEvents(PointerInputScope pointerInputScope, boolean z) {
            c.c(pointerInputScope, z);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m4546toDpGaN1DYA(PointerInputScope pointerInputScope, long j10) {
            return androidx.compose.ui.unit.b.a(pointerInputScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m4547toDpu2uoSUM(PointerInputScope pointerInputScope, float f10) {
            return androidx.compose.ui.unit.a.c(pointerInputScope, f10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m4549toDpSizekrfVVM(PointerInputScope pointerInputScope, long j10) {
            return androidx.compose.ui.unit.a.e(pointerInputScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m4550toPxR2X_6o(PointerInputScope pointerInputScope, long j10) {
            return androidx.compose.ui.unit.a.f(pointerInputScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m4551toPx0680j_4(PointerInputScope pointerInputScope, float f10) {
            return androidx.compose.ui.unit.a.g(pointerInputScope, f10);
        }

        @Deprecated
        public static Rect toRect(PointerInputScope pointerInputScope, DpRect dpRect) {
            return androidx.compose.ui.unit.a.h(pointerInputScope, dpRect);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m4552toSizeXkaWNTQ(PointerInputScope pointerInputScope, long j10) {
            return androidx.compose.ui.unit.a.i(pointerInputScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m4553toSp0xMU5do(PointerInputScope pointerInputScope, float f10) {
            return androidx.compose.ui.unit.b.b(pointerInputScope, f10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m4554toSpkPz2Gy4(PointerInputScope pointerInputScope, float f10) {
            return androidx.compose.ui.unit.a.j(pointerInputScope, f10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m4548toDpu2uoSUM(PointerInputScope pointerInputScope, int i10) {
            return androidx.compose.ui.unit.a.d(pointerInputScope, i10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m4555toSpkPz2Gy4(PointerInputScope pointerInputScope, int i10) {
            return androidx.compose.ui.unit.a.k(pointerInputScope, i10);
        }
    }

    <R> Object awaitPointerEventScope(p<? super AwaitPointerEventScope, ? super d<? super R>, ? extends Object> pVar, d<? super R> dVar);

    /* JADX INFO: renamed from: getExtendedTouchPadding-NH-jbRc */
    long mo276getExtendedTouchPaddingNHjbRc();

    boolean getInterceptOutOfBoundsChildEvents();

    /* JADX INFO: renamed from: getSize-YbymL2g */
    long mo277getSizeYbymL2g();

    ViewConfiguration getViewConfiguration();

    void setInterceptOutOfBoundsChildEvents(boolean z);
}
