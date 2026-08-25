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
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u001a\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0003\u001a\u00020\u0002H¦@¢\u0006\u0004\b\u0005\u0010\u0006JI\u0010\u000f\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010\u00072\u0006\u0010\t\u001a\u00020\b2'\u0010\u000e\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\n¢\u0006\u0002\b\rH\u0096@¢\u0006\u0004\b\u000f\u0010\u0010JG\u0010\u0011\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00072\u0006\u0010\t\u001a\u00020\b2'\u0010\u000e\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\n¢\u0006\u0002\b\rH\u0096@¢\u0006\u0004\b\u0011\u0010\u0010R\u001a\u0010\u0015\u001a\u00020\u00128&X¦\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0018\u001a\u00020\u00168VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0014R\u0014\u0010\u001b\u001a\u00020\u00048&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001f\u001a\u00020\u001c8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eø\u0001\u0002\u0082\u0002\u0011\n\u0005\b¡\u001e0\u0001\n\u0002\b!\n\u0004\b!0\u0001¨\u0006 À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "pass", "Landroidx/compose/ui/input/pointer/PointerEvent;", "awaitPointerEvent", "(Landroidx/compose/ui/input/pointer/PointerEventPass;Ld7/d;)Ljava/lang/Object;", "T", "", "timeMillis", "Lkotlin/Function2;", "Ld7/d;", "", "Lx6/n;", "block", "withTimeoutOrNull", "(JLr7/p;Ld7/d;)Ljava/lang/Object;", "withTimeout", "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g", "()J", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/geometry/Size;", "getExtendedTouchPadding-NH-jbRc", "extendedTouchPadding", "getCurrentEvent", "()Landroidx/compose/ui/input/pointer/PointerEvent;", "currentEvent", "Landroidx/compose/ui/platform/ViewConfiguration;", "getViewConfiguration", "()Landroidx/compose/ui/platform/ViewConfiguration;", "viewConfiguration", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface AwaitPointerEventScope extends Density {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        /* JADX INFO: renamed from: getExtendedTouchPadding-NH-jbRc, reason: not valid java name */
        public static long m4431getExtendedTouchPaddingNHjbRc(AwaitPointerEventScope awaitPointerEventScope) {
            return a.a(awaitPointerEventScope);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m4432roundToPxR2X_6o(AwaitPointerEventScope awaitPointerEventScope, long j10) {
            return androidx.compose.ui.unit.a.a(awaitPointerEventScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m4433roundToPx0680j_4(AwaitPointerEventScope awaitPointerEventScope, float f10) {
            return androidx.compose.ui.unit.a.b(awaitPointerEventScope, f10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m4434toDpGaN1DYA(AwaitPointerEventScope awaitPointerEventScope, long j10) {
            return androidx.compose.ui.unit.b.a(awaitPointerEventScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m4435toDpu2uoSUM(AwaitPointerEventScope awaitPointerEventScope, float f10) {
            return androidx.compose.ui.unit.a.c(awaitPointerEventScope, f10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m4437toDpSizekrfVVM(AwaitPointerEventScope awaitPointerEventScope, long j10) {
            return androidx.compose.ui.unit.a.e(awaitPointerEventScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m4438toPxR2X_6o(AwaitPointerEventScope awaitPointerEventScope, long j10) {
            return androidx.compose.ui.unit.a.f(awaitPointerEventScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m4439toPx0680j_4(AwaitPointerEventScope awaitPointerEventScope, float f10) {
            return androidx.compose.ui.unit.a.g(awaitPointerEventScope, f10);
        }

        @Deprecated
        public static Rect toRect(AwaitPointerEventScope awaitPointerEventScope, DpRect dpRect) {
            return androidx.compose.ui.unit.a.h(awaitPointerEventScope, dpRect);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m4440toSizeXkaWNTQ(AwaitPointerEventScope awaitPointerEventScope, long j10) {
            return androidx.compose.ui.unit.a.i(awaitPointerEventScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m4441toSp0xMU5do(AwaitPointerEventScope awaitPointerEventScope, float f10) {
            return androidx.compose.ui.unit.b.b(awaitPointerEventScope, f10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m4442toSpkPz2Gy4(AwaitPointerEventScope awaitPointerEventScope, float f10) {
            return androidx.compose.ui.unit.a.j(awaitPointerEventScope, f10);
        }

        @Deprecated
        public static <T> Object withTimeout(AwaitPointerEventScope awaitPointerEventScope, long j10, p<? super AwaitPointerEventScope, ? super d<? super T>, ? extends Object> pVar, d<? super T> dVar) {
            return a.b(awaitPointerEventScope, j10, pVar, dVar);
        }

        @Deprecated
        public static <T> Object withTimeoutOrNull(AwaitPointerEventScope awaitPointerEventScope, long j10, p<? super AwaitPointerEventScope, ? super d<? super T>, ? extends Object> pVar, d<? super T> dVar) {
            return a.c(awaitPointerEventScope, j10, pVar, dVar);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m4436toDpu2uoSUM(AwaitPointerEventScope awaitPointerEventScope, int i10) {
            return androidx.compose.ui.unit.a.d(awaitPointerEventScope, i10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m4443toSpkPz2Gy4(AwaitPointerEventScope awaitPointerEventScope, int i10) {
            return androidx.compose.ui.unit.a.k(awaitPointerEventScope, i10);
        }
    }

    Object awaitPointerEvent(PointerEventPass pointerEventPass, d<? super PointerEvent> dVar);

    PointerEvent getCurrentEvent();

    /* JADX INFO: renamed from: getExtendedTouchPadding-NH-jbRc, reason: not valid java name */
    long mo4429getExtendedTouchPaddingNHjbRc();

    /* JADX INFO: renamed from: getSize-YbymL2g, reason: not valid java name */
    long mo4430getSizeYbymL2g();

    ViewConfiguration getViewConfiguration();

    <T> Object withTimeout(long j10, p<? super AwaitPointerEventScope, ? super d<? super T>, ? extends Object> pVar, d<? super T> dVar);

    <T> Object withTimeoutOrNull(long j10, p<? super AwaitPointerEventScope, ? super d<? super T>, ? extends Object> pVar, d<? super T> dVar);
}
