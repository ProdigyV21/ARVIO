package androidx.compose.material3;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/geometry/Size;", "it", "Lx6/t0;", "invoke-uvyYCjk", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class TextFieldImplKt$CommonDecorationBox$3$1$1 extends r implements l<Size, t0> {
    final /* synthetic */ float $labelProgress;
    final /* synthetic */ MutableState<Size> $labelSize;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextFieldImplKt$CommonDecorationBox$3$1$1(float f10, MutableState<Size> mutableState) {
        super(1);
        this.$labelProgress = f10;
        this.$labelSize = mutableState;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        m2145invokeuvyYCjk(((Size) obj).getPackedValue());
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke-uvyYCjk, reason: not valid java name */
    public final void m2145invokeuvyYCjk(long j10) {
        float fM3305getWidthimpl = Size.m3305getWidthimpl(j10) * this.$labelProgress;
        float fM3302getHeightimpl = Size.m3302getHeightimpl(j10) * this.$labelProgress;
        if (Size.m3305getWidthimpl(this.$labelSize.getValue().getPackedValue()) == fM3305getWidthimpl && Size.m3302getHeightimpl(this.$labelSize.getValue().getPackedValue()) == fM3302getHeightimpl) {
            return;
        }
        this.$labelSize.setValue(Size.m3293boximpl(SizeKt.Size(fM3305getWidthimpl, fM3302getHeightimpl)));
    }
}
