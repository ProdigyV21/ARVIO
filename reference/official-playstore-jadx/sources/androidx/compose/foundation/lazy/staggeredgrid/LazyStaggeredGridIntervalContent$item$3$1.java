package androidx.compose.foundation.lazy.staggeredgrid;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridItemSpan;", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class LazyStaggeredGridIntervalContent$item$3$1 extends r implements l<Integer, StaggeredGridItemSpan> {
    final /* synthetic */ StaggeredGridItemSpan $span;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyStaggeredGridIntervalContent$item$3$1(StaggeredGridItemSpan staggeredGridItemSpan) {
        super(1);
        this.$span = staggeredGridItemSpan;
    }

    public final StaggeredGridItemSpan invoke(int i10) {
        return this.$span;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }
}
