package androidx.compose.material3.pulltorefresh;

import d7.d;
import f7.c;
import f7.e;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@e(c = "androidx.compose.material3.pulltorefresh.PullToRefreshStateImpl$nestedScrollConnection$1", f = "PullToRefresh.kt", l = {341}, m = "onPreFling-QWom1Mo")
public final class PullToRefreshStateImpl$nestedScrollConnection$1$onPreFling$1 extends c {
    float F$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PullToRefreshStateImpl$nestedScrollConnection$1 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PullToRefreshStateImpl$nestedScrollConnection$1$onPreFling$1(PullToRefreshStateImpl$nestedScrollConnection$1 pullToRefreshStateImpl$nestedScrollConnection$1, d<? super PullToRefreshStateImpl$nestedScrollConnection$1$onPreFling$1> dVar) {
        super(dVar);
        this.this$0 = pullToRefreshStateImpl$nestedScrollConnection$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.mo370onPreFlingQWom1Mo(0L, this);
    }
}
