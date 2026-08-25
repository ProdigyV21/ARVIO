package androidx.compose.foundation.lazy.staggeredgrid;

import kotlin.Metadata;
import kotlin.jvm.internal.m;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public /* synthetic */ class LazyStaggeredGridState$scrollPosition$1 extends m implements p<Integer, Integer, int[]> {
    public LazyStaggeredGridState$scrollPosition$1(Object obj) {
        super(2, 0, LazyStaggeredGridState.class, obj, "fillNearestIndices", "fillNearestIndices(II)[I");
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke(((Number) obj).intValue(), ((Number) obj2).intValue());
    }

    public final int[] invoke(int i10, int i11) {
        return ((LazyStaggeredGridState) this.receiver).fillNearestIndices(i10, i11);
    }
}
