package androidx.compose.foundation.lazy;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import kotlin.Metadata;
import kotlin.jvm.internal.z;
import kotlin.reflect.n;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a6\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0001\u001a\u00020\u00002\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0002\b\u0005H\u0001¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/compose/foundation/lazy/LazyListState;", "state", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/LazyListScope;", "Lx6/t0;", "Lx6/n;", "content", "Lkotlin/Function0;", "Landroidx/compose/foundation/lazy/LazyListItemProvider;", "rememberLazyListItemProviderLambda", "(Landroidx/compose/foundation/lazy/LazyListState;Lr7/l;Landroidx/compose/runtime/Composer;I)Lr7/a;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyListItemProviderKt {
    public static final r7.a<LazyListItemProvider> rememberLazyListItemProviderLambda(LazyListState lazyListState, l<? super LazyListScope, t0> lVar, Composer composer, int i10) {
        composer.startReplaceableGroup(-343736148);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-343736148, i10, -1, "androidx.compose.foundation.lazy.rememberLazyListItemProviderLambda (LazyListItemProvider.kt:43)");
        }
        State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(lVar, composer, (i10 >> 3) & 14);
        composer.startReplaceableGroup(1157296644);
        boolean zChanged = composer.changed(lazyListState);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            LazyItemScopeImpl lazyItemScopeImpl = new LazyItemScopeImpl();
            final State stateDerivedStateOf = SnapshotStateKt.derivedStateOf(SnapshotStateKt.referentialEqualityPolicy(), new LazyListItemProviderKt$rememberLazyListItemProviderLambda$1$itemProviderState$1(SnapshotStateKt.derivedStateOf(SnapshotStateKt.referentialEqualityPolicy(), new LazyListItemProviderKt$rememberLazyListItemProviderLambda$1$intervalContentState$1(stateRememberUpdatedState)), lazyListState, lazyItemScopeImpl));
            objRememberedValue = new z(stateDerivedStateOf) { // from class: androidx.compose.foundation.lazy.LazyListItemProviderKt$rememberLazyListItemProviderLambda$1$1
                @Override // kotlin.reflect.n
                public Object get() {
                    return ((State) this.receiver).getValue();
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        n nVar = (n) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return nVar;
    }
}
