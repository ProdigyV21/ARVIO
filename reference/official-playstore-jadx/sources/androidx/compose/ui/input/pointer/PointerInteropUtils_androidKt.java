package androidx.compose.ui.input.pointer;

import android.os.SystemClock;
import android.view.MotionEvent;
import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a2\u0010\t\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a2\u0010\u000b\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0000ø\u0001\u0000¢\u0006\u0004\b\n\u0010\b\u001a-\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\f2\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a:\u0010\t\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0006\u0010\u0011\u001a\u00020\u0010H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0014"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerEvent;", "Landroidx/compose/ui/geometry/Offset;", "offset", "Lkotlin/Function1;", "Landroid/view/MotionEvent;", "Lx6/t0;", "block", "toMotionEventScope-d-4ec7I", "(Landroidx/compose/ui/input/pointer/PointerEvent;JLr7/l;)V", "toMotionEventScope", "toCancelMotionEventScope-d-4ec7I", "toCancelMotionEventScope", "", "nowMillis", "emptyCancelMotionEventScope", "(JLr7/l;)V", "", "cancel", "toMotionEventScope-ubNVwUQ", "(Landroidx/compose/ui/input/pointer/PointerEvent;JLr7/l;Z)V", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PointerInteropUtils_androidKt {
    public static final void emptyCancelMotionEventScope(long j10, l<? super MotionEvent, t0> lVar) {
        MotionEvent motionEventObtain = MotionEvent.obtain(j10, j10, 3, 0.0f, 0.0f, 0);
        motionEventObtain.setSource(0);
        lVar.invoke(motionEventObtain);
        motionEventObtain.recycle();
    }

    public static /* synthetic */ void emptyCancelMotionEventScope$default(long j10, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            j10 = SystemClock.uptimeMillis();
        }
        emptyCancelMotionEventScope(j10, lVar);
    }

    /* JADX INFO: renamed from: toCancelMotionEventScope-d-4ec7I, reason: not valid java name */
    public static final void m4564toCancelMotionEventScoped4ec7I(PointerEvent pointerEvent, long j10, l<? super MotionEvent, t0> lVar) {
        m4566toMotionEventScopeubNVwUQ(pointerEvent, j10, lVar, true);
    }

    /* JADX INFO: renamed from: toMotionEventScope-d-4ec7I, reason: not valid java name */
    public static final void m4565toMotionEventScoped4ec7I(PointerEvent pointerEvent, long j10, l<? super MotionEvent, t0> lVar) {
        m4566toMotionEventScopeubNVwUQ(pointerEvent, j10, lVar, false);
    }

    /* JADX INFO: renamed from: toMotionEventScope-ubNVwUQ, reason: not valid java name */
    private static final void m4566toMotionEventScopeubNVwUQ(PointerEvent pointerEvent, long j10, l<? super MotionEvent, t0> lVar, boolean z) {
        MotionEvent motionEvent$ui_release = pointerEvent.getMotionEvent$ui_release();
        if (motionEvent$ui_release == null) {
            throw new IllegalArgumentException("The PointerEvent receiver cannot have a null MotionEvent.");
        }
        int action = motionEvent$ui_release.getAction();
        if (z) {
            motionEvent$ui_release.setAction(3);
        }
        motionEvent$ui_release.offsetLocation(-Offset.m3236getXimpl(j10), -Offset.m3237getYimpl(j10));
        lVar.invoke(motionEvent$ui_release);
        motionEvent$ui_release.offsetLocation(Offset.m3236getXimpl(j10), Offset.m3237getYimpl(j10));
        motionEvent$ui_release.setAction(action);
    }
}
