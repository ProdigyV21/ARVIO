package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationVector1D;
import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.material3.SnackbarHostKt$animatedOpacity$2$1", f = "SnackbarHost.kt", l = {440}, m = "invokeSuspend")
public final class SnackbarHostKt$animatedOpacity$2$1 extends j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ Animatable<Float, AnimationVector1D> $alpha;
    final /* synthetic */ AnimationSpec<Float> $animation;
    final /* synthetic */ r7.a<t0> $onAnimationFinish;
    final /* synthetic */ boolean $visible;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SnackbarHostKt$animatedOpacity$2$1(Animatable<Float, AnimationVector1D> animatable, boolean z, AnimationSpec<Float> animationSpec, r7.a<t0> aVar, d7.d<? super SnackbarHostKt$animatedOpacity$2$1> dVar) {
        super(2, dVar);
        this.$alpha = animatable;
        this.$visible = z;
        this.$animation = animationSpec;
        this.$onAnimationFinish = aVar;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new SnackbarHostKt$animatedOpacity$2$1(this.$alpha, this.$visible, this.$animation, this.$onAnimationFinish, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        SnackbarHostKt$animatedOpacity$2$1 snackbarHostKt$animatedOpacity$2$1;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            Animatable<Float, AnimationVector1D> animatable = this.$alpha;
            Float f10 = new Float(this.$visible ? 1.0f : 0.0f);
            AnimationSpec<Float> animationSpec = this.$animation;
            this.label = 1;
            snackbarHostKt$animatedOpacity$2$1 = this;
            Object objAnimateTo$default = Animatable.animateTo$default(animatable, f10, animationSpec, null, null, snackbarHostKt$animatedOpacity$2$1, 12, null);
            e7.a aVar = e7.a.f15033i;
            if (objAnimateTo$default == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            snackbarHostKt$animatedOpacity$2$1 = this;
        }
        snackbarHostKt$animatedOpacity$2$1.$onAnimationFinish.invoke();
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((SnackbarHostKt$animatedOpacity$2$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
