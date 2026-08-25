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
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J6\u0010\n\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0006H&ø\u0001\u0000¢\u0006\u0004\b\b\u0010\tJ?\u0010\u0013\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u000b2\"\u0010\u000f\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00010\rH¦@ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0015\u001a\u00020\u00148&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u001a\u001a\u00020\u00178&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019ø\u0001\u0001\u0082\u0002\r\n\u0005\b¡\u001e0\u0001\n\u0004\b!0\u0001¨\u0006\u001bÀ\u0006\u0001"}, d2 = {"Landroidx/compose/foundation/OverscrollEffect;", "", "Landroidx/compose/ui/geometry/Offset;", "delta", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "source", "Lkotlin/Function1;", "performScroll", "applyToScroll-Rhakbz0", "(JILr7/l;)J", "applyToScroll", "Landroidx/compose/ui/unit/Velocity;", "velocity", "Lkotlin/Function2;", "Ld7/d;", "performFling", "Lx6/t0;", "applyToFling-BMRW4eQ", "(JLr7/p;Ld7/d;)Ljava/lang/Object;", "applyToFling", "", "isInProgress", "()Z", "Landroidx/compose/ui/Modifier;", "getEffectModifier", "()Landroidx/compose/ui/Modifier;", "effectModifier", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface OverscrollEffect {
    /* JADX INFO: renamed from: applyToFling-BMRW4eQ */
    Object mo159applyToFlingBMRW4eQ(long j10, p<? super Velocity, ? super d<? super Velocity>, ? extends Object> pVar, d<? super t0> dVar);

    /* JADX INFO: renamed from: applyToScroll-Rhakbz0 */
    long mo160applyToScrollRhakbz0(long delta, int source, l<? super Offset, Offset> performScroll);

    Modifier getEffectModifier();

    boolean isInProgress();
}
