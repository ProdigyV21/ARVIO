package androidx.compose.ui.input.pointer;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.jvm.internal.h;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\u001a5\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a/\u0010\t\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\t\u0010\n\u001a/\u0010\r\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00002\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\r\u0010\n\u001a\u001b\u0010\u000e\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001aD\u0010\u001a\u001a\u00020\u0014*\u001e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0010j\u0002`\u00152\u0006\u0010\u0016\u001a\u00020\u00112\b\b\u0002\u0010\u0017\u001a\u00020\u0013H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001aL\u0010\u001e\u001a\u00020\u0014*\u001e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0010j\u0002`\u00152\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u00122\b\b\u0002\u0010\u0017\u001a\u00020\u0013H\u0000ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001aX\u0010#\u001a\u00020\u0014*\u001e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0010j\u0002`\u00152\u0006\u0010\u0016\u001a\u00020\u00112\u0012\u0010 \u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00120\u001f\"\u00020\u00122\b\b\u0002\u0010\u0017\u001a\u00020\u0013H\u0000ø\u0001\u0000¢\u0006\u0004\b!\u0010\"\u001aR\u0010#\u001a\u00020\u0014*\u001e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0010j\u0002`\u00152\u0006\u0010\u0016\u001a\u00020\u00112\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00120$2\b\b\u0002\u0010\u0017\u001a\u00020\u0013H\u0000ø\u0001\u0000¢\u0006\u0004\b!\u0010%*<\b\u0000\u0010&\"\u001a\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u00102\u001a\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0010\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006'"}, d2 = {"", TtmlNode.ATTR_ID, "durationMillis", "", "x", "y", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "down", "(JJFF)Landroidx/compose/ui/input/pointer/PointerInputChange;", "moveTo", "(Landroidx/compose/ui/input/pointer/PointerInputChange;JFF)Landroidx/compose/ui/input/pointer/PointerInputChange;", "dx", "dy", "moveBy", "up", "(Landroidx/compose/ui/input/pointer/PointerInputChange;J)Landroidx/compose/ui/input/pointer/PointerInputChange;", "Lkotlin/Function3;", "Landroidx/compose/ui/input/pointer/PointerEvent;", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "Landroidx/compose/ui/unit/IntSize;", "Lx6/t0;", "Landroidx/compose/ui/input/pointer/PointerInputHandler;", "pointerEvent", ContentDisposition.Parameters.Size, "invokeOverAllPasses-H0pRuoY", "(Lr7/q;Landroidx/compose/ui/input/pointer/PointerEvent;J)V", "invokeOverAllPasses", "pointerEventPass", "invokeOverPass-hUlJWOE", "(Lr7/q;Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "invokeOverPass", "", "pointerEventPasses", "invokeOverPasses-hUlJWOE", "(Lr7/q;Landroidx/compose/ui/input/pointer/PointerEvent;[Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "invokeOverPasses", "", "(Lr7/q;Landroidx/compose/ui/input/pointer/PointerEvent;Ljava/util/List;J)V", "PointerInputHandler", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PointerInputTestUtilKt {
    public static final PointerInputChange down(long j10, long j11, float f10, float f11) {
        return new PointerInputChange(PointerId.m4498constructorimpl(j10), j11, OffsetKt.Offset(f10, f11), true, 1.0f, j11, OffsetKt.Offset(f10, f11), false, false, 0, 0L, 1536, (h) null);
    }

    public static /* synthetic */ PointerInputChange down$default(long j10, long j11, float f10, float f11, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            j11 = 0;
        }
        return down(j10, j11, (i10 & 4) != 0 ? 0.0f : f10, (i10 & 8) != 0 ? 0.0f : f11);
    }

    /* JADX INFO: renamed from: invokeOverAllPasses-H0pRuoY, reason: not valid java name */
    public static final void m4556invokeOverAllPassesH0pRuoY(q<? super PointerEvent, ? super PointerEventPass, ? super IntSize, t0> qVar, PointerEvent pointerEvent, long j10) {
        m4560invokeOverPasseshUlJWOE(qVar, pointerEvent, (List<? extends PointerEventPass>) t7.a.E(PointerEventPass.Initial, PointerEventPass.Main, PointerEventPass.Final), j10);
    }

    /* JADX INFO: renamed from: invokeOverAllPasses-H0pRuoY$default, reason: not valid java name */
    public static /* synthetic */ void m4557invokeOverAllPassesH0pRuoY$default(q qVar, PointerEvent pointerEvent, long j10, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            j10 = IntSizeKt.IntSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }
        m4556invokeOverAllPassesH0pRuoY(qVar, pointerEvent, j10);
    }

    /* JADX INFO: renamed from: invokeOverPass-hUlJWOE, reason: not valid java name */
    public static final void m4558invokeOverPasshUlJWOE(q<? super PointerEvent, ? super PointerEventPass, ? super IntSize, t0> qVar, PointerEvent pointerEvent, PointerEventPass pointerEventPass, long j10) {
        m4560invokeOverPasseshUlJWOE(qVar, pointerEvent, (List<? extends PointerEventPass>) Collections.singletonList(pointerEventPass), j10);
    }

    /* JADX INFO: renamed from: invokeOverPass-hUlJWOE$default, reason: not valid java name */
    public static /* synthetic */ void m4559invokeOverPasshUlJWOE$default(q qVar, PointerEvent pointerEvent, PointerEventPass pointerEventPass, long j10, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            j10 = IntSizeKt.IntSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }
        m4558invokeOverPasshUlJWOE(qVar, pointerEvent, pointerEventPass, j10);
    }

    /* JADX INFO: renamed from: invokeOverPasses-hUlJWOE, reason: not valid java name */
    public static final void m4561invokeOverPasseshUlJWOE(q<? super PointerEvent, ? super PointerEventPass, ? super IntSize, t0> qVar, PointerEvent pointerEvent, PointerEventPass[] pointerEventPassArr, long j10) {
        m4560invokeOverPasseshUlJWOE(qVar, pointerEvent, (List<? extends PointerEventPass>) r.o0(pointerEventPassArr), j10);
    }

    /* JADX INFO: renamed from: invokeOverPasses-hUlJWOE$default, reason: not valid java name */
    public static /* synthetic */ void m4563invokeOverPasseshUlJWOE$default(q qVar, PointerEvent pointerEvent, PointerEventPass[] pointerEventPassArr, long j10, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            j10 = IntSizeKt.IntSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }
        m4561invokeOverPasseshUlJWOE((q<? super PointerEvent, ? super PointerEventPass, ? super IntSize, t0>) qVar, pointerEvent, pointerEventPassArr, j10);
    }

    public static final PointerInputChange moveBy(PointerInputChange pointerInputChange, long j10, float f10, float f11) {
        long id = pointerInputChange.getId();
        long uptimeMillis = pointerInputChange.getUptimeMillis();
        boolean pressed = pointerInputChange.getPressed();
        return new PointerInputChange(id, pointerInputChange.getUptimeMillis() + j10, OffsetKt.Offset(Offset.m3236getXimpl(pointerInputChange.getPosition()) + f10, Offset.m3237getYimpl(pointerInputChange.getPosition()) + f11), true, 1.0f, uptimeMillis, pointerInputChange.getPosition(), pressed, false, 0, 0L, 1536, (h) null);
    }

    public static /* synthetic */ PointerInputChange moveBy$default(PointerInputChange pointerInputChange, long j10, float f10, float f11, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            f10 = 0.0f;
        }
        if ((i10 & 4) != 0) {
            f11 = 0.0f;
        }
        return moveBy(pointerInputChange, j10, f10, f11);
    }

    public static final PointerInputChange moveTo(PointerInputChange pointerInputChange, long j10, float f10, float f11) {
        long id = pointerInputChange.getId();
        long uptimeMillis = pointerInputChange.getUptimeMillis();
        boolean pressed = pointerInputChange.getPressed();
        return new PointerInputChange(id, j10, OffsetKt.Offset(f10, f11), true, 1.0f, uptimeMillis, pointerInputChange.getPosition(), pressed, false, 0, 0L, 1536, (h) null);
    }

    public static /* synthetic */ PointerInputChange moveTo$default(PointerInputChange pointerInputChange, long j10, float f10, float f11, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            f10 = 0.0f;
        }
        if ((i10 & 4) != 0) {
            f11 = 0.0f;
        }
        return moveTo(pointerInputChange, j10, f10, f11);
    }

    public static final PointerInputChange up(PointerInputChange pointerInputChange, long j10) {
        long id = pointerInputChange.getId();
        long uptimeMillis = pointerInputChange.getUptimeMillis();
        boolean pressed = pointerInputChange.getPressed();
        return new PointerInputChange(id, j10, pointerInputChange.getPosition(), false, 1.0f, uptimeMillis, pointerInputChange.getPosition(), pressed, false, 0, 0L, 1536, (h) null);
    }

    /* JADX INFO: renamed from: invokeOverPasses-hUlJWOE, reason: not valid java name */
    public static final void m4560invokeOverPasseshUlJWOE(q<? super PointerEvent, ? super PointerEventPass, ? super IntSize, t0> qVar, PointerEvent pointerEvent, List<? extends PointerEventPass> list, long j10) {
        if (pointerEvent.getChanges().isEmpty()) {
            throw new IllegalArgumentException("invokeOverPasses called with no changes");
        }
        if (list.isEmpty()) {
            throw new IllegalArgumentException("invokeOverPasses called with no passes");
        }
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            qVar.invoke(pointerEvent, list.get(i10), IntSize.m5836boximpl(j10));
        }
    }

    /* JADX INFO: renamed from: invokeOverPasses-hUlJWOE$default, reason: not valid java name */
    public static /* synthetic */ void m4562invokeOverPasseshUlJWOE$default(q qVar, PointerEvent pointerEvent, List list, long j10, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            j10 = IntSizeKt.IntSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }
        m4560invokeOverPasseshUlJWOE((q<? super PointerEvent, ? super PointerEventPass, ? super IntSize, t0>) qVar, pointerEvent, (List<? extends PointerEventPass>) list, j10);
    }
}
