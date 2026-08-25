package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.lazy.layout.NearestRangeKeyIndexMap;
import androidx.compose.runtime.State;
import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/lazy/grid/LazyGridItemProviderImpl;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class LazyGridItemProviderKt$rememberLazyGridItemProviderLambda$1$itemProviderState$1 extends r implements r7.a<LazyGridItemProviderImpl> {
    final /* synthetic */ State<LazyGridIntervalContent> $intervalContentState;
    final /* synthetic */ LazyGridState $state;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyGridItemProviderKt$rememberLazyGridItemProviderLambda$1$itemProviderState$1(State<LazyGridIntervalContent> state, LazyGridState lazyGridState) {
        super(0);
        this.$intervalContentState = state;
        this.$state = lazyGridState;
    }

    @Override // r7.a
    public final LazyGridItemProviderImpl invoke() {
        LazyGridIntervalContent value = this.$intervalContentState.getValue();
        return new LazyGridItemProviderImpl(this.$state, value, new NearestRangeKeyIndexMap(this.$state.getNearestRange$foundation_release(), value));
    }
}
