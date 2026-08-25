package androidx.compose.animation;

import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "S", "it", "invoke", "(I)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class AnimatedContentTransitionScopeImpl$slideIntoContainer$3 extends r implements l<Integer, Integer> {
    final /* synthetic */ l<Integer, Integer> $initialOffset;
    final /* synthetic */ AnimatedContentTransitionScopeImpl<S> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AnimatedContentTransitionScopeImpl$slideIntoContainer$3(l<? super Integer, Integer> lVar, AnimatedContentTransitionScopeImpl<S> animatedContentTransitionScopeImpl) {
        super(1);
        this.$initialOffset = lVar;
        this.this$0 = animatedContentTransitionScopeImpl;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }

    public final Integer invoke(int i10) {
        return (Integer) this.$initialOffset.invoke(Integer.valueOf(IntSize.m5843getHeightimpl(this.this$0.m24getCurrentSizeYbymL2g()) - IntOffset.m5803getYimpl(this.this$0.m23calculateOffsetemnUabE(IntSizeKt.IntSize(i10, i10), this.this$0.m24getCurrentSizeYbymL2g()))));
    }
}
