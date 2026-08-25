package androidx.compose.foundation.lazy.staggeredgrid;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0015\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "it", "", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class LazyStaggeredGridState$Companion$Saver$2 extends r implements l<List<? extends int[]>, LazyStaggeredGridState> {
    public static final LazyStaggeredGridState$Companion$Saver$2 INSTANCE = new LazyStaggeredGridState$Companion$Saver$2();

    public LazyStaggeredGridState$Companion$Saver$2() {
        super(1);
    }

    @Override // r7.l
    public final LazyStaggeredGridState invoke(List<int[]> list) {
        return new LazyStaggeredGridState(list.get(0), list.get(1), null);
    }
}
