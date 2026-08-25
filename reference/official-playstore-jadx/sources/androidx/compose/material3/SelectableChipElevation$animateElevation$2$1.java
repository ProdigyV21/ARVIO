package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.unit.Dp;
import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.material3.SelectableChipElevation$animateElevation$2$1", f = "Chip.kt", l = {2329, 2331}, m = "invokeSuspend")
public final class SelectableChipElevation$animateElevation$2$1 extends j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ Animatable<Dp, AnimationVector1D> $animatable;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ Interaction $interaction;
    final /* synthetic */ MutableState<Interaction> $lastInteraction$delegate;
    final /* synthetic */ float $target;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectableChipElevation$animateElevation$2$1(Animatable<Dp, AnimationVector1D> animatable, float f10, boolean z, Interaction interaction, MutableState<Interaction> mutableState, d7.d<? super SelectableChipElevation$animateElevation$2$1> dVar) {
        super(2, dVar);
        this.$animatable = animatable;
        this.$target = f10;
        this.$enabled = z;
        this.$interaction = interaction;
        this.$lastInteraction$delegate = mutableState;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new SelectableChipElevation$animateElevation$2$1(this.$animatable, this.$target, this.$enabled, this.$interaction, this.$lastInteraction$delegate, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0042, code lost:
    
        if (r6.snapTo(r1, r5) == r0) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0057, code lost:
    
        if (androidx.compose.material3.ElevationKt.m1549animateElevationrAjV9yQ(r2, r3, r6, r4, r5) == r0) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0059, code lost:
    
        return r0;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            int r0 = r5.label
            r1 = 2
            r2 = 1
            if (r0 == 0) goto L17
            if (r0 == r2) goto L13
            if (r0 != r1) goto Lb
            goto L13
        Lb:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L13:
            k2.c.G(r6)
            goto L5a
        L17:
            k2.c.G(r6)
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r6 = r5.$animatable
            java.lang.Object r6 = r6.getTargetValue()
            androidx.compose.ui.unit.Dp r6 = (androidx.compose.ui.unit.Dp) r6
            float r6 = r6.m5692unboximpl()
            float r0 = r5.$target
            boolean r6 = androidx.compose.ui.unit.Dp.m5683equalsimpl0(r6, r0)
            if (r6 != 0) goto L61
            boolean r6 = r5.$enabled
            e7.a r0 = e7.a.f15033i
            if (r6 != 0) goto L45
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r6 = r5.$animatable
            float r1 = r5.$target
            androidx.compose.ui.unit.Dp r1 = androidx.compose.ui.unit.Dp.m5676boximpl(r1)
            r5.label = r2
            java.lang.Object r6 = r6.snapTo(r1, r5)
            if (r6 != r0) goto L5a
            goto L59
        L45:
            androidx.compose.runtime.MutableState<androidx.compose.foundation.interaction.Interaction> r6 = r5.$lastInteraction$delegate
            androidx.compose.foundation.interaction.Interaction r6 = androidx.compose.material3.SelectableChipElevation.access$animateElevation$lambda$2(r6)
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r2 = r5.$animatable
            float r3 = r5.$target
            androidx.compose.foundation.interaction.Interaction r4 = r5.$interaction
            r5.label = r1
            java.lang.Object r6 = androidx.compose.material3.ElevationKt.m1549animateElevationrAjV9yQ(r2, r3, r6, r4, r5)
            if (r6 != r0) goto L5a
        L59:
            return r0
        L5a:
            androidx.compose.runtime.MutableState<androidx.compose.foundation.interaction.Interaction> r6 = r5.$lastInteraction$delegate
            androidx.compose.foundation.interaction.Interaction r0 = r5.$interaction
            androidx.compose.material3.SelectableChipElevation.access$animateElevation$lambda$3(r6, r0)
        L61:
            x6.t0 r6 = x6.t0.f22605a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SelectableChipElevation$animateElevation$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((SelectableChipElevation$animateElevation$2$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
