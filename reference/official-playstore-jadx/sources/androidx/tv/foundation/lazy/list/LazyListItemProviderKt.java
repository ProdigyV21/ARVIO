package androidx.tv.foundation.lazy.list;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a6\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0001\u001a\u00020\u00002\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0002\b\u0005H\u0001¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/tv/foundation/lazy/list/m0;", "state", "Lkotlin/Function1;", "Landroidx/tv/foundation/lazy/list/g0;", "Lx6/t0;", "Lx6/n;", "content", "Lkotlin/Function0;", "Landroidx/tv/foundation/lazy/list/l;", "rememberLazyListItemProviderLambda", "(Landroidx/tv/foundation/lazy/list/m0;Lr7/l;Landroidx/compose/runtime/Composer;I)Lr7/a;", "tv-foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyListItemProviderKt {
    public static final r7.a<l> rememberLazyListItemProviderLambda(m0 m0Var, r7.l<? super g0, t0> lVar, Composer composer, int i10) {
        composer.startReplaceableGroup(-1033166156);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1033166156, i10, -1, "androidx.tv.foundation.lazy.list.rememberLazyListItemProviderLambda (LazyListItemProvider.kt:45)");
        }
        State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(lVar, composer, (i10 >> 3) & 14);
        composer.startReplaceableGroup(-1209245305);
        boolean z = (((i10 & 14) ^ 6) > 4 && composer.changed(m0Var)) || (i10 & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            d0 d0Var = new d0();
            d0Var.f5381a = SnapshotIntStateKt.mutableIntStateOf(Integer.MAX_VALUE);
            d0Var.f5382b = SnapshotIntStateKt.mutableIntStateOf(Integer.MAX_VALUE);
            androidx.tv.foundation.lazy.grid.r rVar = new androidx.tv.foundation.lazy.grid.r(0, 1, State.class, SnapshotStateKt.derivedStateOf(SnapshotStateKt.referentialEqualityPolicy(), new n(SnapshotStateKt.derivedStateOf(SnapshotStateKt.referentialEqualityPolicy(), new androidx.navigation.compose.b0(stateRememberUpdatedState, 2)), m0Var, d0Var)), "value", "getValue()Ljava/lang/Object;");
            composer.updateRememberedValue(rVar);
            objRememberedValue = rVar;
        }
        kotlin.reflect.n nVar = (kotlin.reflect.n) objRememberedValue;
        composer.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return nVar;
    }
}
