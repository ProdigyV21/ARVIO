package com.arflix.tv.ui.motion;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.runtime.SnapshotStateKt;
import d7.d;
import f7.e;
import f7.j;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import na.k;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@e(c = "com.arflix.tv.ui.motion.ArvioPredictiveBackKt$rememberArvioPredictiveBack$1$1", f = "ArvioPredictiveBack.kt", l = {69}, m = "invokeSuspend", v = 2)
public final class ArvioPredictiveBackKt$rememberArvioPredictiveBack$1$1 extends j implements p<k0, d<? super t0>, Object> {
    final /* synthetic */ Animatable<Float, AnimationVector1D> $anim;
    final /* synthetic */ ArvioBackMotion $motion;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ArvioPredictiveBackKt$rememberArvioPredictiveBack$1$1(Animatable<Float, AnimationVector1D> animatable, ArvioBackMotion arvioBackMotion, d<? super ArvioPredictiveBackKt$rememberArvioPredictiveBack$1$1> dVar) {
        super(2, dVar);
        this.$anim = animatable;
        this.$motion = arvioBackMotion;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float invokeSuspend$lambda$0(Animatable animatable) {
        return ((Number) animatable.getValue()).floatValue();
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new ArvioPredictiveBackKt$rememberArvioPredictiveBack$1$1(this.$anim, this.$motion, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            c.G(obj);
            final Animatable<Float, AnimationVector1D> animatable = this.$anim;
            na.j jVarSnapshotFlow = SnapshotStateKt.snapshotFlow(new r7.a() { // from class: com.arflix.tv.ui.motion.b
                @Override // r7.a
                public final Object invoke() {
                    return Float.valueOf(ArvioPredictiveBackKt$rememberArvioPredictiveBack$1$1.invokeSuspend$lambda$0(animatable));
                }
            });
            final ArvioBackMotion arvioBackMotion = this.$motion;
            k kVar = new k() { // from class: com.arflix.tv.ui.motion.ArvioPredictiveBackKt$rememberArvioPredictiveBack$1$1.2
                public final Object emit(float f10, d<? super t0> dVar) {
                    arvioBackMotion.setProgress$app_playRelease(f10);
                    return t0.f22605a;
                }

                @Override // na.k
                public /* bridge */ /* synthetic */ Object emit(Object obj2, d dVar) {
                    return emit(((Number) obj2).floatValue(), (d<? super t0>) dVar);
                }
            };
            this.label = 1;
            Object objCollect = jVarSnapshotFlow.collect(kVar, this);
            e7.a aVar = e7.a.f15033i;
            if (objCollect == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
        return ((ArvioPredictiveBackKt$rememberArvioPredictiveBack$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
