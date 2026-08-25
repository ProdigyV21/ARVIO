package androidx.compose.material3;

import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class DividerKt$VerticalDivider$1$1 extends r implements l<DrawScope, t0> {
    final /* synthetic */ long $color;
    final /* synthetic */ float $thickness;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DividerKt$VerticalDivider$1$1(float f10, long j10) {
        super(1);
        this.$thickness = f10;
        this.$color = j10;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DrawScope) obj);
        return t0.f22605a;
    }

    public final void invoke(DrawScope drawScope) {
        float f10 = 2;
        androidx.compose.ui.graphics.drawscope.c.C(drawScope, this.$color, OffsetKt.Offset(drawScope.mo285toPx0680j_4(this.$thickness) / f10, 0.0f), OffsetKt.Offset(drawScope.mo285toPx0680j_4(this.$thickness) / f10, Size.m3302getHeightimpl(drawScope.mo3916getSizeNHjbRc())), drawScope.mo285toPx0680j_4(this.$thickness), 0, null, 0.0f, null, 0, 496, null);
    }
}
