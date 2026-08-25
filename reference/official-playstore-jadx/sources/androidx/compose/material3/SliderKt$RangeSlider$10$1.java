package androidx.compose.material3;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/material3/SliderRange;", "it", "Lx6/t0;", "invoke-If1S1O4", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SliderKt$RangeSlider$10$1 extends r implements l<SliderRange, t0> {
    final /* synthetic */ l<x7.e<Float>, t0> $onValueChange;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SliderKt$RangeSlider$10$1(l<? super x7.e<Float>, t0> lVar) {
        super(1);
        this.$onValueChange = lVar;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        m1896invokeIf1S1O4(((SliderRange) obj).m1909unboximpl());
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke-If1S1O4, reason: not valid java name */
    public final void m1896invokeIf1S1O4(long j10) {
        this.$onValueChange.invoke(new x7.d(SliderRange.m1906getStartimpl(j10), SliderRange.m1905getEndInclusiveimpl(j10)));
    }
}
