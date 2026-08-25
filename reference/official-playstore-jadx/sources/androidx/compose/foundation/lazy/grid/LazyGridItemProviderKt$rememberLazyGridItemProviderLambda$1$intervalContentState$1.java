package androidx.compose.foundation.lazy.grid;

import androidx.compose.runtime.State;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/lazy/grid/LazyGridIntervalContent;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class LazyGridItemProviderKt$rememberLazyGridItemProviderLambda$1$intervalContentState$1 extends r implements r7.a<LazyGridIntervalContent> {
    final /* synthetic */ State<l<LazyGridScope, t0>> $latestContent;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public LazyGridItemProviderKt$rememberLazyGridItemProviderLambda$1$intervalContentState$1(State<? extends l<? super LazyGridScope, t0>> state) {
        super(0);
        this.$latestContent = state;
    }

    @Override // r7.a
    public final LazyGridIntervalContent invoke() {
        return new LazyGridIntervalContent(this.$latestContent.getValue());
    }
}
