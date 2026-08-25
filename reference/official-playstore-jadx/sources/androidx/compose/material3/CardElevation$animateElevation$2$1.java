package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.ui.unit.Dp;
import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.material3.CardElevation$animateElevation$2$1", f = "Card.kt", l = {732, 741}, m = "invokeSuspend")
public final class CardElevation$animateElevation$2$1 extends j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ Animatable<Dp, AnimationVector1D> $animatable;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ Interaction $interaction;
    final /* synthetic */ float $target;
    int label;
    final /* synthetic */ CardElevation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CardElevation$animateElevation$2$1(Animatable<Dp, AnimationVector1D> animatable, float f10, boolean z, CardElevation cardElevation, Interaction interaction, d7.d<? super CardElevation$animateElevation$2$1> dVar) {
        super(2, dVar);
        this.$animatable = animatable;
        this.$target = f10;
        this.$enabled = z;
        this.this$0 = cardElevation;
        this.$interaction = interaction;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new CardElevation$animateElevation$2$1(this.$animatable, this.$target, this.$enabled, this.this$0, this.$interaction, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0043, code lost:
    
        if (r7.snapTo(r1, r6) == r0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00ad, code lost:
    
        if (androidx.compose.material3.ElevationKt.m1549animateElevationrAjV9yQ(r7, r2, r3, r4, r6) == r0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00af, code lost:
    
        return r0;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            int r0 = r6.label
            r1 = 2
            r2 = 1
            if (r0 == 0) goto L18
            if (r0 == r2) goto L13
            if (r0 != r1) goto Lb
            goto L13
        Lb:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L13:
            k2.c.G(r7)
            goto Lb0
        L18:
            k2.c.G(r7)
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r7 = r6.$animatable
            java.lang.Object r7 = r7.getTargetValue()
            androidx.compose.ui.unit.Dp r7 = (androidx.compose.ui.unit.Dp) r7
            float r7 = r7.m5692unboximpl()
            float r0 = r6.$target
            boolean r7 = androidx.compose.ui.unit.Dp.m5683equalsimpl0(r7, r0)
            if (r7 != 0) goto Lb0
            boolean r7 = r6.$enabled
            e7.a r0 = e7.a.f15033i
            if (r7 != 0) goto L46
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r7 = r6.$animatable
            float r1 = r6.$target
            androidx.compose.ui.unit.Dp r1 = androidx.compose.ui.unit.Dp.m5676boximpl(r1)
            r6.label = r2
            java.lang.Object r7 = r7.snapTo(r1, r6)
            if (r7 != r0) goto Lb0
            goto Laf
        L46:
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r7 = r6.$animatable
            java.lang.Object r7 = r7.getTargetValue()
            androidx.compose.ui.unit.Dp r7 = (androidx.compose.ui.unit.Dp) r7
            float r7 = r7.m5692unboximpl()
            androidx.compose.material3.CardElevation r2 = r6.this$0
            float r2 = androidx.compose.material3.CardElevation.access$getPressedElevation$p(r2)
            boolean r2 = androidx.compose.ui.unit.Dp.m5683equalsimpl0(r7, r2)
            r3 = 0
            if (r2 == 0) goto L6c
            androidx.compose.foundation.interaction.PressInteraction$Press r7 = new androidx.compose.foundation.interaction.PressInteraction$Press
            androidx.compose.ui.geometry.Offset$Companion r2 = androidx.compose.ui.geometry.Offset.INSTANCE
            long r4 = r2.m3252getZeroF1C5BW0()
            r7.<init>(r4, r3)
            r3 = r7
            goto La1
        L6c:
            androidx.compose.material3.CardElevation r2 = r6.this$0
            float r2 = androidx.compose.material3.CardElevation.access$getHoveredElevation$p(r2)
            boolean r2 = androidx.compose.ui.unit.Dp.m5683equalsimpl0(r7, r2)
            if (r2 == 0) goto L7e
            androidx.compose.foundation.interaction.HoverInteraction$Enter r3 = new androidx.compose.foundation.interaction.HoverInteraction$Enter
            r3.<init>()
            goto La1
        L7e:
            androidx.compose.material3.CardElevation r2 = r6.this$0
            float r2 = androidx.compose.material3.CardElevation.access$getFocusedElevation$p(r2)
            boolean r2 = androidx.compose.ui.unit.Dp.m5683equalsimpl0(r7, r2)
            if (r2 == 0) goto L90
            androidx.compose.foundation.interaction.FocusInteraction$Focus r3 = new androidx.compose.foundation.interaction.FocusInteraction$Focus
            r3.<init>()
            goto La1
        L90:
            androidx.compose.material3.CardElevation r2 = r6.this$0
            float r2 = androidx.compose.material3.CardElevation.access$getDraggedElevation$p(r2)
            boolean r7 = androidx.compose.ui.unit.Dp.m5683equalsimpl0(r7, r2)
            if (r7 == 0) goto La1
            androidx.compose.foundation.interaction.DragInteraction$Start r3 = new androidx.compose.foundation.interaction.DragInteraction$Start
            r3.<init>()
        La1:
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r7 = r6.$animatable
            float r2 = r6.$target
            androidx.compose.foundation.interaction.Interaction r4 = r6.$interaction
            r6.label = r1
            java.lang.Object r7 = androidx.compose.material3.ElevationKt.m1549animateElevationrAjV9yQ(r7, r2, r3, r4, r6)
            if (r7 != r0) goto Lb0
        Laf:
            return r0
        Lb0:
            x6.t0 r7 = x6.t0.f22605a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.CardElevation$animateElevation$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((CardElevation$animateElevation$2$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
