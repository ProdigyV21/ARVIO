package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import f7.j;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/runtime/DisposableEffectResult;", "Landroidx/compose/runtime/DisposableEffectScope;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class SwitchKt$Switch$3$1 extends r implements l<DisposableEffectScope, DisposableEffectResult> {
    final /* synthetic */ Animatable<Float, AnimationVector1D> $offset;
    final /* synthetic */ k0 $scope;
    final /* synthetic */ float $targetValue;

    /* JADX INFO: renamed from: androidx.compose.material3.SwitchKt$Switch$3$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.material3.SwitchKt$Switch$3$1$1", f = "Switch.kt", l = {125}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ Animatable<Float, AnimationVector1D> $offset;
        final /* synthetic */ float $targetValue;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Animatable<Float, AnimationVector1D> animatable, float f10, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$offset = animatable;
            this.$targetValue = f10;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass1(this.$offset, this.$targetValue, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                Animatable<Float, AnimationVector1D> animatable = this.$offset;
                Float f10 = new Float(this.$targetValue);
                TweenSpec tweenSpec = SwitchKt.AnimationSpec;
                this.label = 1;
                Object objAnimateTo$default = Animatable.animateTo$default(animatable, f10, tweenSpec, null, null, this, 12, null);
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
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SwitchKt$Switch$3$1(Animatable<Float, AnimationVector1D> animatable, float f10, k0 k0Var) {
        super(1);
        this.$offset = animatable;
        this.$targetValue = f10;
        this.$scope = k0Var;
    }

    @Override // r7.l
    public final DisposableEffectResult invoke(DisposableEffectScope disposableEffectScope) {
        float fFloatValue = this.$offset.getTargetValue().floatValue();
        float f10 = this.$targetValue;
        if (fFloatValue != f10) {
            m0.p(this.$scope, null, 0, new AnonymousClass1(this.$offset, f10, null), 3);
        }
        return new DisposableEffectResult() { // from class: androidx.compose.material3.SwitchKt$Switch$3$1$invoke$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
            }
        };
    }
}
