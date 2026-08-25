package androidx.compose.material3.pulltorefresh;

import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/graphics/GraphicsLayerScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/graphics/GraphicsLayerScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class PullToRefreshKt$PullToRefreshContainer$1$1$1 extends r implements l<GraphicsLayerScope, t0> {
    final /* synthetic */ PullToRefreshState $state;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PullToRefreshKt$PullToRefreshContainer$1$1$1(PullToRefreshState pullToRefreshState) {
        super(1);
        this.$state = pullToRefreshState;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((GraphicsLayerScope) obj);
        return t0.f22605a;
    }

    public final void invoke(GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setTranslationY(this.$state.getVerticalOffset() - Size.m3302getHeightimpl(graphicsLayerScope.mo3640getSizeNHjbRc()));
    }
}
