package androidx.compose.foundation.gestures.snapping;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.gestures.ScrollScope;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.h0;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "Landroidx/compose/foundation/gestures/snapping/AnimationResult;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "<anonymous>", "(Lka/k0;)Landroidx/compose/foundation/gestures/snapping/AnimationResult;"}, k = 3, mv = {1, 8, 0})
@e(c = "androidx.compose.foundation.gestures.snapping.SnapFlingBehavior$fling$result$1", f = "SnapFlingBehavior.kt", l = {174, 187}, m = "invokeSuspend")
public final class SnapFlingBehavior$fling$result$1 extends j implements p<k0, d<? super AnimationResult<Float, AnimationVector1D>>, Object> {
    final /* synthetic */ float $initialVelocity;
    final /* synthetic */ l<Float, t0> $onRemainingScrollOffsetUpdate;
    final /* synthetic */ ScrollScope $this_fling;
    Object L$0;
    int label;
    final /* synthetic */ SnapFlingBehavior this$0;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.snapping.SnapFlingBehavior$fling$result$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "delta", "Lx6/t0;", "invoke", "(F)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<Float, t0> {
        final /* synthetic */ l<Float, t0> $onRemainingScrollOffsetUpdate;
        final /* synthetic */ h0 $remainingScrollOffset;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(h0 h0Var, l<? super Float, t0> lVar) {
            super(1);
            this.$remainingScrollOffset = h0Var;
            this.$onRemainingScrollOffsetUpdate = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke(((Number) obj).floatValue());
            return t0.f22605a;
        }

        public final void invoke(float f10) {
            h0 h0Var = this.$remainingScrollOffset;
            float f11 = h0Var.f19742i - f10;
            h0Var.f19742i = f11;
            this.$onRemainingScrollOffsetUpdate.invoke(Float.valueOf(f11));
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SnapFlingBehavior$fling$result$1(SnapFlingBehavior snapFlingBehavior, float f10, l<? super Float, t0> lVar, ScrollScope scrollScope, d<? super SnapFlingBehavior$fling$result$1> dVar) {
        super(2, dVar);
        this.this$0 = snapFlingBehavior;
        this.$initialVelocity = f10;
        this.$onRemainingScrollOffsetUpdate = lVar;
        this.$this_fling = scrollScope;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new SnapFlingBehavior$fling$result$1(this.this$0, this.$initialVelocity, this.$onRemainingScrollOffsetUpdate, this.$this_fling, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        h0 h0Var;
        Object objTryApproach;
        int i10 = this.label;
        a aVar = a.f15033i;
        if (i10 == 0) {
            c.G(obj);
            float fCalculateApproachOffset = this.this$0.snapLayoutInfoProvider.calculateApproachOffset(this.$initialVelocity);
            float fSignum = Math.signum(this.$initialVelocity) * Math.abs(fCalculateApproachOffset);
            h0Var = new h0();
            h0Var.f19742i = fSignum;
            this.$onRemainingScrollOffsetUpdate.invoke(new Float(fSignum));
            SnapFlingBehavior snapFlingBehavior = this.this$0;
            ScrollScope scrollScope = this.$this_fling;
            float f10 = h0Var.f19742i;
            float f11 = this.$initialVelocity;
            SnapFlingBehavior$fling$result$1$animationState$1 snapFlingBehavior$fling$result$1$animationState$1 = new SnapFlingBehavior$fling$result$1$animationState$1(h0Var, this.$onRemainingScrollOffsetUpdate);
            this.L$0 = h0Var;
            this.label = 1;
            objTryApproach = snapFlingBehavior.tryApproach(scrollScope, f10, f11, snapFlingBehavior$fling$result$1$animationState$1, this);
            if (objTryApproach != aVar) {
            }
        }
        if (i10 != 1) {
            if (i10 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            return obj;
        }
        h0 h0Var2 = (h0) this.L$0;
        c.G(obj);
        h0Var = h0Var2;
        objTryApproach = obj;
        AnimationState animationState = (AnimationState) objTryApproach;
        float fCalculateSnappingOffset = this.this$0.snapLayoutInfoProvider.calculateSnappingOffset(((Number) animationState.getVelocity()).floatValue());
        h0Var.f19742i = fCalculateSnappingOffset;
        ScrollScope scrollScope2 = this.$this_fling;
        AnimationState animationStateCopy$default = AnimationStateKt.copy$default(animationState, 0.0f, 0.0f, 0L, 0L, false, 30, (Object) null);
        AnimationSpec animationSpec = this.this$0.snapAnimationSpec;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(h0Var, this.$onRemainingScrollOffsetUpdate);
        this.L$0 = null;
        this.label = 2;
        Object objAnimateWithTarget = SnapFlingBehaviorKt.animateWithTarget(scrollScope2, fCalculateSnappingOffset, fCalculateSnappingOffset, animationStateCopy$default, animationSpec, anonymousClass2, this);
        return objAnimateWithTarget == aVar ? aVar : objAnimateWithTarget;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super AnimationResult<Float, AnimationVector1D>> dVar) {
        return ((SnapFlingBehavior$fling$result$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
