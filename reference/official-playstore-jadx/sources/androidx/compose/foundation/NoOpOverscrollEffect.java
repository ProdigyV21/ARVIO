package androidx.compose.foundation;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.unit.Velocity;
import d7.d;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J6\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\bH\u0016ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ?\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\r2\"\u0010\u0012\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u000fH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0018\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001d\u001a\u00020\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001e"}, d2 = {"Landroidx/compose/foundation/NoOpOverscrollEffect;", "Landroidx/compose/foundation/OverscrollEffect;", "<init>", "()V", "Landroidx/compose/ui/geometry/Offset;", "delta", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "source", "Lkotlin/Function1;", "performScroll", "applyToScroll-Rhakbz0", "(JILr7/l;)J", "applyToScroll", "Landroidx/compose/ui/unit/Velocity;", "velocity", "Lkotlin/Function2;", "Ld7/d;", "", "performFling", "Lx6/t0;", "applyToFling-BMRW4eQ", "(JLr7/p;Ld7/d;)Ljava/lang/Object;", "applyToFling", "", "isInProgress", "()Z", "Landroidx/compose/ui/Modifier;", "getEffectModifier", "()Landroidx/compose/ui/Modifier;", "effectModifier", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class NoOpOverscrollEffect implements OverscrollEffect {
    public static final int $stable = 0;
    public static final NoOpOverscrollEffect INSTANCE = new NoOpOverscrollEffect();

    private NoOpOverscrollEffect() {
    }

    @Override // androidx.compose.foundation.OverscrollEffect
    /* JADX INFO: renamed from: applyToFling-BMRW4eQ */
    public Object mo159applyToFlingBMRW4eQ(long j10, p<? super Velocity, ? super d<? super Velocity>, ? extends Object> pVar, d<? super t0> dVar) {
        Object objInvoke = pVar.invoke(Velocity.m5900boximpl(j10), dVar);
        return objInvoke == e7.a.f15033i ? objInvoke : t0.f22605a;
    }

    @Override // androidx.compose.foundation.OverscrollEffect
    /* JADX INFO: renamed from: applyToScroll-Rhakbz0 */
    public long mo160applyToScrollRhakbz0(long delta, int source, l<? super Offset, Offset> performScroll) {
        return ((Offset) performScroll.invoke(Offset.m3225boximpl(delta))).getPackedValue();
    }

    @Override // androidx.compose.foundation.OverscrollEffect
    public Modifier getEffectModifier() {
        return Modifier.INSTANCE;
    }

    @Override // androidx.compose.foundation.OverscrollEffect
    public boolean isInProgress() {
        return false;
    }
}
