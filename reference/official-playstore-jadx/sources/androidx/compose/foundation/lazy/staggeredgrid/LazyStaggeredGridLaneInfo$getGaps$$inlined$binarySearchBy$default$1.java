package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridLaneInfo;
import com.google.common.util.concurrent.r0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000\"\u000e\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u00028\u00010\u00012\u0006\u0010\u0003\u001a\u00028\u0000H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"T", "", "K", "it", "", "invoke", "(Ljava/lang/Object;)Ljava/lang/Integer;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class LazyStaggeredGridLaneInfo$getGaps$$inlined$binarySearchBy$default$1 extends r implements l<LazyStaggeredGridLaneInfo.SpannedItem, Integer> {
    final /* synthetic */ Comparable $key;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyStaggeredGridLaneInfo$getGaps$$inlined$binarySearchBy$default$1(Comparable comparable) {
        super(1);
        this.$key = comparable;
    }

    @Override // r7.l
    public final Integer invoke(LazyStaggeredGridLaneInfo.SpannedItem spannedItem) {
        return Integer.valueOf(r0.e(Integer.valueOf(spannedItem.getIndex()), this.$key));
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke((LazyStaggeredGridLaneInfo.SpannedItem) obj);
    }
}
