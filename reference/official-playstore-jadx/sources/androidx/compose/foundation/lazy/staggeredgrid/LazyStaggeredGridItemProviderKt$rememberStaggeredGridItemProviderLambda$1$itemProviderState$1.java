package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.lazy.layout.NearestRangeKeyIndexMap;
import androidx.compose.runtime.State;
import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProviderImpl;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class LazyStaggeredGridItemProviderKt$rememberStaggeredGridItemProviderLambda$1$itemProviderState$1 extends r implements r7.a<LazyStaggeredGridItemProviderImpl> {
    final /* synthetic */ State<LazyStaggeredGridIntervalContent> $intervalContentState;
    final /* synthetic */ LazyStaggeredGridState $state;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyStaggeredGridItemProviderKt$rememberStaggeredGridItemProviderLambda$1$itemProviderState$1(State<LazyStaggeredGridIntervalContent> state, LazyStaggeredGridState lazyStaggeredGridState) {
        super(0);
        this.$intervalContentState = state;
        this.$state = lazyStaggeredGridState;
    }

    @Override // r7.a
    public final LazyStaggeredGridItemProviderImpl invoke() {
        LazyStaggeredGridIntervalContent value = this.$intervalContentState.getValue();
        return new LazyStaggeredGridItemProviderImpl(this.$state, value, new NearestRangeKeyIndexMap(this.$state.getNearestRange$foundation_release(), value));
    }
}
