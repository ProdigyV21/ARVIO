package androidx.tv.foundation.lazy.grid;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a6\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0001\u001a\u00020\u00002\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0002\b\u0005H\u0001¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/tv/foundation/lazy/grid/b1;", "state", "Lkotlin/Function1;", "Landroidx/tv/foundation/lazy/grid/v0;", "Lx6/t0;", "Lx6/n;", "content", "Lkotlin/Function0;", "Landroidx/tv/foundation/lazy/grid/p;", "rememberLazyGridItemProviderLambda", "(Landroidx/tv/foundation/lazy/grid/b1;Lr7/l;Landroidx/compose/runtime/Composer;I)Lr7/a;", "tv-foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyGridItemProviderKt {
    public static final r7.a<p> rememberLazyGridItemProviderLambda(b1 b1Var, r7.l<? super v0, x6.t0> lVar, Composer composer, int i10) {
        composer.startReplaceableGroup(1304242276);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1304242276, i10, -1, "androidx.tv.foundation.lazy.grid.rememberLazyGridItemProviderLambda (LazyGridItemProvider.kt:42)");
        }
        State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(lVar, composer, (i10 >> 3) & 14);
        composer.startReplaceableGroup(-1097632784);
        boolean z = (((i10 & 14) ^ 6) > 4 && composer.changed(b1Var)) || (i10 & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            r rVar = new r(0, 0, State.class, SnapshotStateKt.derivedStateOf(SnapshotStateKt.referentialEqualityPolicy(), new s(SnapshotStateKt.derivedStateOf(SnapshotStateKt.referentialEqualityPolicy(), new androidx.navigation.compose.b0(stateRememberUpdatedState, 1)), b1Var)), "value", "getValue()Ljava/lang/Object;");
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
