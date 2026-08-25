package androidx.compose.material3.pulltorefresh;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class PullToRefreshKt$PullToRefreshContainer$showElevation$1$1 extends r implements a<Boolean> {
    final /* synthetic */ PullToRefreshState $state;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PullToRefreshKt$PullToRefreshContainer$showElevation$1$1(PullToRefreshState pullToRefreshState) {
        super(0);
        this.$state = pullToRefreshState;
    }

    @Override // r7.a
    public final Boolean invoke() {
        return Boolean.valueOf(this.$state.getVerticalOffset() > 1.0f || this.$state.isRefreshing());
    }
}
