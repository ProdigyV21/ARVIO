package androidx.compose.foundation.lazy.staggeredgrid;

import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class LazyStaggeredGridStateKt$rememberLazyStaggeredGridState$1$1 extends r implements r7.a<LazyStaggeredGridState> {
    final /* synthetic */ int $initialFirstVisibleItemIndex;
    final /* synthetic */ int $initialFirstVisibleItemScrollOffset;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyStaggeredGridStateKt$rememberLazyStaggeredGridState$1$1(int i10, int i11) {
        super(0);
        this.$initialFirstVisibleItemIndex = i10;
        this.$initialFirstVisibleItemScrollOffset = i11;
    }

    @Override // r7.a
    public final LazyStaggeredGridState invoke() {
        return new LazyStaggeredGridState(this.$initialFirstVisibleItemIndex, this.$initialFirstVisibleItemScrollOffset);
    }
}
