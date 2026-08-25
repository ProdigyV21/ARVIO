package androidx.compose.foundation.gestures;

import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.ui.geometry.Offset;
import f7.j;
import kotlin.Metadata;
import kotlin.jvm.internal.j0;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/TransformScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/gestures/TransformScope;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.foundation.gestures.TransformableStateKt$animatePanBy$2", f = "TransformableState.kt", l = {182}, m = "invokeSuspend")
public final class TransformableStateKt$animatePanBy$2 extends j implements p<TransformScope, d7.d<? super t0>, Object> {
    final /* synthetic */ AnimationSpec<Offset> $animationSpec;
    final /* synthetic */ long $offset;
    final /* synthetic */ j0 $previous;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformableStateKt$animatePanBy$2$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/animation/core/AnimationScope;", "Landroidx/compose/ui/geometry/Offset;", "Landroidx/compose/animation/core/AnimationVector2D;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/core/AnimationScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<AnimationScope<Offset, AnimationVector2D>, t0> {
        final /* synthetic */ TransformScope $$this$transform;
        final /* synthetic */ j0 $previous;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(j0 j0Var, TransformScope transformScope) {
            super(1);
            this.$previous = j0Var;
            this.$$this$transform = transformScope;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((AnimationScope<Offset, AnimationVector2D>) obj);
            return t0.f22605a;
        }

        public final void invoke(AnimationScope<Offset, AnimationVector2D> animationScope) {
            g.a(this.$$this$transform, 0.0f, Offset.m3240minusMKHz9U(animationScope.getValue().getPackedValue(), this.$previous.f19745i), 0.0f, 5, null);
            this.$previous.f19745i = animationScope.getValue().getPackedValue();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TransformableStateKt$animatePanBy$2(j0 j0Var, long j10, AnimationSpec<Offset> animationSpec, d7.d<? super TransformableStateKt$animatePanBy$2> dVar) {
        super(2, dVar);
        this.$previous = j0Var;
        this.$offset = j10;
        this.$animationSpec = animationSpec;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        TransformableStateKt$animatePanBy$2 transformableStateKt$animatePanBy$2 = new TransformableStateKt$animatePanBy$2(this.$previous, this.$offset, this.$animationSpec, dVar);
        transformableStateKt$animatePanBy$2.L$0 = obj;
        return transformableStateKt$animatePanBy$2;
    }

    @Override // r7.p
    public final Object invoke(TransformScope transformScope, d7.d<? super t0> dVar) {
        return ((TransformableStateKt$animatePanBy$2) create(transformScope, dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            TransformScope transformScope = (TransformScope) this.L$0;
            AnimationState animationState = new AnimationState(VectorConvertersKt.getVectorConverter(Offset.INSTANCE), Offset.m3225boximpl(this.$previous.f19745i), null, 0L, 0L, false, 60, null);
            Offset offsetM3225boximpl = Offset.m3225boximpl(this.$offset);
            AnimationSpec<Offset> animationSpec = this.$animationSpec;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$previous, transformScope);
            this.label = 1;
            Object objAnimateTo$default = SuspendAnimationKt.animateTo$default(animationState, offsetM3225boximpl, animationSpec, false, anonymousClass1, this, 4, null);
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
}
