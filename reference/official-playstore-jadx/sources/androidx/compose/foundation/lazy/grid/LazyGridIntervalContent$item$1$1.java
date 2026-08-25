package androidx.compose.foundation.lazy.grid;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class LazyGridIntervalContent$item$1$1 extends r implements l<Integer, Object> {
    final /* synthetic */ Object $key;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyGridIntervalContent$item$1$1(Object obj) {
        super(1);
        this.$key = obj;
    }

    public final Object invoke(int i10) {
        return this.$key;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).intValue());
    }
}
