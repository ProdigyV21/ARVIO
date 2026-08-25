package androidx.compose.foundation.lazy;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/lazy/LazyListState;", "it", "", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class LazyListState$Companion$Saver$2 extends r implements l<List<? extends Integer>, LazyListState> {
    public static final LazyListState$Companion$Saver$2 INSTANCE = new LazyListState$Companion$Saver$2();

    public LazyListState$Companion$Saver$2() {
        super(1);
    }

    @Override // r7.l
    public final LazyListState invoke(List<Integer> list) {
        return new LazyListState(list.get(0).intValue(), list.get(1).intValue());
    }
}
