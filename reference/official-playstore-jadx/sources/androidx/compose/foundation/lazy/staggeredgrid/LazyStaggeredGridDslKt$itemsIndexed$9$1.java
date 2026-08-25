package androidx.compose.foundation.lazy.staggeredgrid;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/lazy/staggeredgrid/StaggeredGridItemSpan;", "T", "index", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
public final class LazyStaggeredGridDslKt$itemsIndexed$9$1 extends r implements l<Integer, StaggeredGridItemSpan> {
    final /* synthetic */ T[] $items;
    final /* synthetic */ p<Integer, T, StaggeredGridItemSpan> $span;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public LazyStaggeredGridDslKt$itemsIndexed$9$1(p<? super Integer, ? super T, StaggeredGridItemSpan> pVar, T[] tArr) {
        super(1);
        this.$span = pVar;
        this.$items = tArr;
    }

    public final StaggeredGridItemSpan invoke(int i10) {
        return (StaggeredGridItemSpan) this.$span.invoke(Integer.valueOf(i10), this.$items[i10]);
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }
}
