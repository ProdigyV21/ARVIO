package androidx.compose.foundation.lazy.layout;

import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Float;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class LazyLayoutSemanticsKt$lazyLayoutSemantics$1$accessibilityScrollState$2 extends r implements r7.a<Float> {
    final /* synthetic */ LazyLayoutSemanticState $state;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyLayoutSemanticsKt$lazyLayoutSemantics$1$accessibilityScrollState$2(LazyLayoutSemanticState lazyLayoutSemanticState) {
        super(0);
        this.$state = lazyLayoutSemanticState;
    }

    @Override // r7.a
    public final Float invoke() {
        return Float.valueOf(this.$state.pseudoMaxScrollOffset());
    }
}
