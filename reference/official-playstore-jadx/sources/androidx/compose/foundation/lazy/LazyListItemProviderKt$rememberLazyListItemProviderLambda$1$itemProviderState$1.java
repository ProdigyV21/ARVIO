package androidx.compose.foundation.lazy;

import androidx.compose.foundation.lazy.layout.NearestRangeKeyIndexMap;
import androidx.compose.runtime.State;
import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/lazy/LazyListItemProviderImpl;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class LazyListItemProviderKt$rememberLazyListItemProviderLambda$1$itemProviderState$1 extends r implements r7.a<LazyListItemProviderImpl> {
    final /* synthetic */ State<LazyListIntervalContent> $intervalContentState;
    final /* synthetic */ LazyItemScopeImpl $scope;
    final /* synthetic */ LazyListState $state;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyListItemProviderKt$rememberLazyListItemProviderLambda$1$itemProviderState$1(State<LazyListIntervalContent> state, LazyListState lazyListState, LazyItemScopeImpl lazyItemScopeImpl) {
        super(0);
        this.$intervalContentState = state;
        this.$state = lazyListState;
        this.$scope = lazyItemScopeImpl;
    }

    @Override // r7.a
    public final LazyListItemProviderImpl invoke() {
        LazyListIntervalContent value = this.$intervalContentState.getValue();
        return new LazyListItemProviderImpl(this.$state, value, this.$scope, new NearestRangeKeyIndexMap(this.$state.getNearestRange$foundation_release(), value));
    }
}
