package androidx.compose.ui.graphics.vector;

import androidx.compose.ui.graphics.StrokeCap;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/ui/graphics/vector/PathComponent;", "Landroidx/compose/ui/graphics/StrokeCap;", "it", "Lx6/t0;", "invoke-CSYIeUk", "(Landroidx/compose/ui/graphics/vector/PathComponent;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class VectorComposeKt$Path$2$10 extends r implements p<PathComponent, StrokeCap, t0> {
    public static final VectorComposeKt$Path$2$10 INSTANCE = new VectorComposeKt$Path$2$10();

    public VectorComposeKt$Path$2$10() {
        super(2);
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        m4039invokeCSYIeUk((PathComponent) obj, ((StrokeCap) obj2).getValue());
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke-CSYIeUk, reason: not valid java name */
    public final void m4039invokeCSYIeUk(PathComponent pathComponent, int i10) {
        pathComponent.m4032setStrokeLineCapBeK7IIE(i10);
    }
}
