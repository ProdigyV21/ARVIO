package androidx.tv.material3;

import androidx.compose.ui.graphics.CompositingStrategy;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;

/* JADX INFO: loaded from: classes3.dex */
public final class j3 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ float f6136i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Shape f6137l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j3(float f10, Shape shape) {
        super(1);
        this.f6136i = f10;
        this.f6137l = shape;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        GraphicsLayerScope graphicsLayerScope = (GraphicsLayerScope) obj;
        graphicsLayerScope.setAlpha(this.f6136i);
        graphicsLayerScope.setShape(this.f6137l);
        graphicsLayerScope.setClip(true);
        graphicsLayerScope.mo3644setCompositingStrategyaDBOjCE(CompositingStrategy.INSTANCE.m3563getOffscreenNrFUSI());
        return x6.t0.f22605a;
    }
}
