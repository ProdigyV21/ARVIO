package androidx.compose.foundation.pager;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.State;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/pager/PagerLayoutIntervalContent;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class LazyLayoutPagerKt$rememberPagerItemProviderLambda$1$intervalContentState$1 extends r implements r7.a<PagerLayoutIntervalContent> {
    final /* synthetic */ State<r7.r<PagerScope, Integer, Composer, Integer, t0>> $latestContent;
    final /* synthetic */ State<l<Integer, Object>> $latestKey;
    final /* synthetic */ r7.a<Integer> $pageCount;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public LazyLayoutPagerKt$rememberPagerItemProviderLambda$1$intervalContentState$1(State<? extends r7.r<? super PagerScope, ? super Integer, ? super Composer, ? super Integer, t0>> state, State<? extends l<? super Integer, ? extends Object>> state2, r7.a<Integer> aVar) {
        super(0);
        this.$latestContent = state;
        this.$latestKey = state2;
        this.$pageCount = aVar;
    }

    @Override // r7.a
    public final PagerLayoutIntervalContent invoke() {
        return new PagerLayoutIntervalContent(this.$latestContent.getValue(), this.$latestKey.getValue(), ((Number) this.$pageCount.invoke()).intValue());
    }
}
