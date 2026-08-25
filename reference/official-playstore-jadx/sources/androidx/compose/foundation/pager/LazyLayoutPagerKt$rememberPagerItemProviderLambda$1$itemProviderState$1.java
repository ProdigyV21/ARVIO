package androidx.compose.foundation.pager;

import androidx.compose.foundation.lazy.layout.NearestRangeKeyIndexMap;
import androidx.compose.runtime.State;
import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class LazyLayoutPagerKt$rememberPagerItemProviderLambda$1$itemProviderState$1 extends r implements r7.a<PagerLazyLayoutItemProvider> {
    final /* synthetic */ State<PagerLayoutIntervalContent> $intervalContentState;
    final /* synthetic */ PagerState $state;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyLayoutPagerKt$rememberPagerItemProviderLambda$1$itemProviderState$1(State<PagerLayoutIntervalContent> state, PagerState pagerState) {
        super(0);
        this.$intervalContentState = state;
        this.$state = pagerState;
    }

    @Override // r7.a
    public final PagerLazyLayoutItemProvider invoke() {
        PagerLayoutIntervalContent value = this.$intervalContentState.getValue();
        return new PagerLazyLayoutItemProvider(this.$state, value, new NearestRangeKeyIndexMap(this.$state.getNearestRange$foundation_release(), value));
    }
}
