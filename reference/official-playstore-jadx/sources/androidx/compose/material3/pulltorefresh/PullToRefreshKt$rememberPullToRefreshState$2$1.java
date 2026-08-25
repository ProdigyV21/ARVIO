package androidx.compose.material3.pulltorefresh;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class PullToRefreshKt$rememberPullToRefreshState$2$1 extends r implements a<PullToRefreshState> {
    final /* synthetic */ a<Boolean> $enabled;
    final /* synthetic */ float $positionalThresholdPx;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PullToRefreshKt$rememberPullToRefreshState$2$1(float f10, a<Boolean> aVar) {
        super(0);
        this.$positionalThresholdPx = f10;
        this.$enabled = aVar;
    }

    @Override // r7.a
    public final PullToRefreshState invoke() {
        return new PullToRefreshStateImpl(false, this.$positionalThresholdPx, this.$enabled);
    }
}
