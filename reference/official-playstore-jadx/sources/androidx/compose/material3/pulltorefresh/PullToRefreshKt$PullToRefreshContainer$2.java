package androidx.compose.material3.pulltorefresh;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public final class PullToRefreshKt$PullToRefreshContainer$2 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$default;
    final /* synthetic */ long $containerColor;
    final /* synthetic */ long $contentColor;
    final /* synthetic */ q<PullToRefreshState, Composer, Integer, t0> $indicator;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ Shape $shape;
    final /* synthetic */ PullToRefreshState $state;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public PullToRefreshKt$PullToRefreshContainer$2(PullToRefreshState pullToRefreshState, Modifier modifier, q<? super PullToRefreshState, ? super Composer, ? super Integer, t0> qVar, Shape shape, long j10, long j11, int i10, int i11) {
        super(2);
        this.$state = pullToRefreshState;
        this.$modifier = modifier;
        this.$indicator = qVar;
        this.$shape = shape;
        this.$containerColor = j10;
        this.$contentColor = j11;
        this.$$changed = i10;
        this.$$default = i11;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        PullToRefreshKt.m2327PullToRefreshContainerwBJOh4Y(this.$state, this.$modifier, this.$indicator, this.$shape, this.$containerColor, this.$contentColor, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
    }
}
