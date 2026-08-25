package androidx.compose.animation;

import androidx.compose.runtime.State;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "S", "it", "invoke", "(I)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class AnimatedContentTransitionScopeImpl$slideOutOfContainer$1 extends r implements l<Integer, Integer> {
    final /* synthetic */ l<Integer, Integer> $targetOffset;
    final /* synthetic */ AnimatedContentTransitionScopeImpl<S> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AnimatedContentTransitionScopeImpl$slideOutOfContainer$1(AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl, l<? super Integer, Integer> lVar) {
        super(1);
        this.this$0 = animatedContentTransitionScopeImpl;
        this.$targetOffset = lVar;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }

    public final Integer invoke(int i10) {
        State state = (State) this.this$0.getTargetSizeMap$animation_release().get(this.this$0.getTransition$animation_release().getTargetState());
        return (Integer) this.$targetOffset.invoke(Integer.valueOf((-IntOffset.m5802getXimpl(this.this$0.m23calculateOffsetemnUabE(IntSizeKt.IntSize(i10, i10), state != null ? ((IntSize) state.getValue()).getPackedValue() : IntSize.INSTANCE.m5849getZeroYbymL2g()))) - i10));
    }
}
