package androidx.compose.foundation.lazy.layout;

import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "needle", "", "invoke", "(Ljava/lang/Object;)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class LazyLayoutSemanticsKt$lazyLayoutSemantics$1$indexForKeyMapping$1 extends r implements l<Object, Integer> {
    final /* synthetic */ r7.a<LazyLayoutItemProvider> $itemProviderLambda;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public LazyLayoutSemanticsKt$lazyLayoutSemantics$1$indexForKeyMapping$1(r7.a<? extends LazyLayoutItemProvider> aVar) {
        super(1);
        this.$itemProviderLambda = aVar;
    }

    @Override // r7.l
    public final Integer invoke(Object obj) {
        LazyLayoutItemProvider lazyLayoutItemProvider = (LazyLayoutItemProvider) this.$itemProviderLambda.invoke();
        int itemCount = lazyLayoutItemProvider.getItemCount();
        int i10 = 0;
        while (true) {
            if (i10 >= itemCount) {
                i10 = -1;
                break;
            }
            if (p.a(lazyLayoutItemProvider.getKey(i10), obj)) {
                break;
            }
            i10++;
        }
        return Integer.valueOf(i10);
    }
}
