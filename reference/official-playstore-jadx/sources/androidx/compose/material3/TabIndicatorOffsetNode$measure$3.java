package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.ui.unit.Dp;
import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.material3.TabIndicatorOffsetNode$measure$3", f = "TabRow.kt", l = {552}, m = "invokeSuspend")
public final class TabIndicatorOffsetNode$measure$3 extends j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ float $indicatorOffset;
    final /* synthetic */ Animatable<Dp, AnimationVector1D> $offsetAnim;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TabIndicatorOffsetNode$measure$3(Animatable<Dp, AnimationVector1D> animatable, float f10, d7.d<? super TabIndicatorOffsetNode$measure$3> dVar) {
        super(2, dVar);
        this.$offsetAnim = animatable;
        this.$indicatorOffset = f10;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TabIndicatorOffsetNode$measure$3(this.$offsetAnim, this.$indicatorOffset, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            Animatable<Dp, AnimationVector1D> animatable = this.$offsetAnim;
            Dp dpM5676boximpl = Dp.m5676boximpl(this.$indicatorOffset);
            this.label = 1;
            Object objAnimateTo$default = Animatable.animateTo$default(animatable, dpM5676boximpl, null, null, null, this, 14, null);
            e7.a aVar = e7.a.f15033i;
            if (objAnimateTo$default == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((TabIndicatorOffsetNode$measure$3) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
