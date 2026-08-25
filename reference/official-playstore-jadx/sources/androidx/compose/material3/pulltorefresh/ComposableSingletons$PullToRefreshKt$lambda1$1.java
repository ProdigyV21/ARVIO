package androidx.compose.material3.pulltorefresh;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.q;
import x6.t0;

/* JADX INFO: renamed from: androidx.compose.material3.pulltorefresh.ComposableSingletons$PullToRefreshKt$lambda-1$1, reason: invalid class name */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "pullRefreshState", "Lx6/t0;", "invoke", "(Landroidx/compose/material3/pulltorefresh/PullToRefreshState;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ComposableSingletons$PullToRefreshKt$lambda1$1 extends r implements q<PullToRefreshState, Composer, Integer, t0> {
    public static final ComposableSingletons$PullToRefreshKt$lambda1$1 INSTANCE = new ComposableSingletons$PullToRefreshKt$lambda1$1();

    public ComposableSingletons$PullToRefreshKt$lambda1$1() {
        super(3);
    }

    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((PullToRefreshState) obj, (Composer) obj2, ((Number) obj3).intValue());
        return t0.f22605a;
    }

    public final void invoke(PullToRefreshState pullToRefreshState, Composer composer, int i10) {
        if ((i10 & 6) == 0) {
            i10 |= composer.changed(pullToRefreshState) ? 4 : 2;
        }
        if ((i10 & 19) == 18 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1116780789, i10, -1, "androidx.compose.material3.pulltorefresh.ComposableSingletons$PullToRefreshKt.lambda-1.<anonymous> (PullToRefresh.kt:110)");
        }
        PullToRefreshDefaults.INSTANCE.m2324IndicatorFNF3uiM(pullToRefreshState, null, 0L, composer, (i10 & 14) | 3072, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
