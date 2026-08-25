package androidx.compose.foundation.pager;

import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/pager/PagerStateImpl;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class PagerStateKt$rememberPagerState$1$1 extends r implements r7.a<PagerStateImpl> {
    final /* synthetic */ int $initialPage;
    final /* synthetic */ float $initialPageOffsetFraction;
    final /* synthetic */ r7.a<Integer> $pageCount;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PagerStateKt$rememberPagerState$1$1(int i10, float f10, r7.a<Integer> aVar) {
        super(0);
        this.$initialPage = i10;
        this.$initialPageOffsetFraction = f10;
        this.$pageCount = aVar;
    }

    @Override // r7.a
    public final PagerStateImpl invoke() {
        return new PagerStateImpl(this.$initialPage, this.$initialPageOffsetFraction, this.$pageCount);
    }
}
