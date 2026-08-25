package androidx.tv.foundation.lazy.grid;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u000b¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/tv/foundation/lazy/grid/q0;", "", "it", "Lx6/t0;", "invoke", "(Landroidx/tv/foundation/lazy/grid/q0;ILandroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
final class LazyGridIntervalContent$item$4 extends kotlin.jvm.internal.r implements r7.r<q0, Integer, Composer, Integer, x6.t0> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ r7.q f5136i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyGridIntervalContent$item$4(r7.q qVar) {
        super(4);
        this.f5136i = qVar;
    }

    @Override // r7.r
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        invoke((q0) obj, ((Number) obj2).intValue(), (Composer) obj3, ((Number) obj4).intValue());
        return x6.t0.f22605a;
    }

    public final void invoke(q0 q0Var, int i10, Composer composer, int i11) {
        if ((i11 & 6) == 0) {
            i11 |= composer.changed(q0Var) ? 4 : 2;
        }
        if ((i11 & 131) == 130 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(659494635, i11, -1, "androidx.tv.foundation.lazy.grid.LazyGridIntervalContent.item.<anonymous> (LazyGridSpanLayoutProvider.kt:281)");
        }
        this.f5136i.invoke(q0Var, composer, Integer.valueOf(i11 & 14));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
