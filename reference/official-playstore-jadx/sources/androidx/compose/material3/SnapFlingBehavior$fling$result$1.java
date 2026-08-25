package androidx.compose.material3;

import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.gestures.ScrollScope;
import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "Landroidx/compose/material3/AnimationResult;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "<anonymous>", "(Lka/k0;)Landroidx/compose/material3/AnimationResult;"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.material3.SnapFlingBehavior$fling$result$1", f = "SnapFlingBehavior.kt", l = {97, 99}, m = "invokeSuspend")
public final class SnapFlingBehavior$fling$result$1 extends j implements p<k0, d7.d<? super AnimationResult<Float, AnimationVector1D>>, Object> {
    final /* synthetic */ float $initialVelocity;
    final /* synthetic */ ScrollScope $this_fling;
    int label;
    final /* synthetic */ SnapFlingBehavior this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SnapFlingBehavior$fling$result$1(float f10, SnapFlingBehavior snapFlingBehavior, ScrollScope scrollScope, d7.d<? super SnapFlingBehavior$fling$result$1> dVar) {
        super(2, dVar);
        this.$initialVelocity = f10;
        this.this$0 = snapFlingBehavior;
        this.$this_fling = scrollScope;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new SnapFlingBehavior$fling$result$1(this.$initialVelocity, this.this$0, this.$this_fling, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x003f, code lost:
    
        if (r5 == r0) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0051, code lost:
    
        if (r5 == r0) goto L19;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r5) {
        /*
            r4 = this;
            int r0 = r4.label
            r1 = 2
            r2 = 1
            if (r0 == 0) goto L1a
            if (r0 == r2) goto L16
            if (r0 != r1) goto Le
            k2.c.G(r5)
            goto L54
        Le:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L16:
            k2.c.G(r5)
            goto L42
        L1a:
            k2.c.G(r5)
            float r5 = r4.$initialVelocity
            float r5 = java.lang.Math.abs(r5)
            androidx.compose.material3.SnapFlingBehavior r0 = r4.this$0
            float r0 = androidx.compose.material3.SnapFlingBehavior.access$getVelocityThreshold$p(r0)
            float r0 = java.lang.Math.abs(r0)
            int r5 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            e7.a r0 = e7.a.f15033i
            if (r5 > 0) goto L45
            androidx.compose.material3.SnapFlingBehavior r5 = r4.this$0
            androidx.compose.foundation.gestures.ScrollScope r1 = r4.$this_fling
            float r3 = r4.$initialVelocity
            r4.label = r2
            java.lang.Object r5 = androidx.compose.material3.SnapFlingBehavior.access$shortSnap(r5, r1, r3, r4)
            if (r5 != r0) goto L42
            goto L53
        L42:
            androidx.compose.material3.AnimationResult r5 = (androidx.compose.material3.AnimationResult) r5
            return r5
        L45:
            androidx.compose.material3.SnapFlingBehavior r5 = r4.this$0
            androidx.compose.foundation.gestures.ScrollScope r2 = r4.$this_fling
            float r3 = r4.$initialVelocity
            r4.label = r1
            java.lang.Object r5 = androidx.compose.material3.SnapFlingBehavior.access$longSnap(r5, r2, r3, r4)
            if (r5 != r0) goto L54
        L53:
            return r0
        L54:
            androidx.compose.material3.AnimationResult r5 = (androidx.compose.material3.AnimationResult) r5
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SnapFlingBehavior$fling$result$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super AnimationResult<Float, AnimationVector1D>> dVar) {
        return ((SnapFlingBehavior$fling$result$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
