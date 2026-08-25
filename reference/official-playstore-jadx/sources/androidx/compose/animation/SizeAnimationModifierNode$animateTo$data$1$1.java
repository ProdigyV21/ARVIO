package androidx.compose.animation;

import androidx.compose.animation.SizeAnimationModifierNode;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationEndReason;
import androidx.compose.animation.core.AnimationResult;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.ui.unit.IntSize;
import d7.d;
import f7.e;
import f7.j;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "androidx.compose.animation.SizeAnimationModifierNode$animateTo$data$1$1", f = "AnimationModifier.kt", l = {175}, m = "invokeSuspend")
public final class SizeAnimationModifierNode$animateTo$data$1$1 extends j implements p<k0, d<? super t0>, Object> {
    final /* synthetic */ long $targetSize;
    final /* synthetic */ SizeAnimationModifierNode.AnimData $this_apply;
    int label;
    final /* synthetic */ SizeAnimationModifierNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SizeAnimationModifierNode$animateTo$data$1$1(SizeAnimationModifierNode.AnimData animData, long j10, SizeAnimationModifierNode sizeAnimationModifierNode, d<? super SizeAnimationModifierNode$animateTo$data$1$1> dVar) {
        super(2, dVar);
        this.$this_apply = animData;
        this.$targetSize = j10;
        this.this$0 = sizeAnimationModifierNode;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new SizeAnimationModifierNode$animateTo$data$1$1(this.$this_apply, this.$targetSize, this.this$0, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        SizeAnimationModifierNode$animateTo$data$1$1 sizeAnimationModifierNode$animateTo$data$1$1;
        p<IntSize, IntSize, t0> listener;
        int i10 = this.label;
        if (i10 == 0) {
            c.G(obj);
            Animatable<IntSize, AnimationVector2D> anim = this.$this_apply.getAnim();
            IntSize intSizeM5836boximpl = IntSize.m5836boximpl(this.$targetSize);
            AnimationSpec<IntSize> animationSpec = this.this$0.getAnimationSpec();
            this.label = 1;
            sizeAnimationModifierNode$animateTo$data$1$1 = this;
            obj = Animatable.animateTo$default(anim, intSizeM5836boximpl, animationSpec, null, null, sizeAnimationModifierNode$animateTo$data$1$1, 12, null);
            e7.a aVar = e7.a.f15033i;
            if (obj == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            sizeAnimationModifierNode$animateTo$data$1$1 = this;
        }
        AnimationResult animationResult = (AnimationResult) obj;
        if (animationResult.getEndReason() == AnimationEndReason.Finished && (listener = sizeAnimationModifierNode$animateTo$data$1$1.this$0.getListener()) != null) {
            listener.invoke(IntSize.m5836boximpl(sizeAnimationModifierNode$animateTo$data$1$1.$this_apply.m76getStartSizeYbymL2g()), animationResult.getEndState().getValue());
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
        return ((SizeAnimationModifierNode$animateTo$data$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
