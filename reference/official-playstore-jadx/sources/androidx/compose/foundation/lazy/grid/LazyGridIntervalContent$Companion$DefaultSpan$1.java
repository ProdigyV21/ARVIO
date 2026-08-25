package androidx.compose.foundation.lazy.grid;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/lazy/grid/GridItemSpan;", "Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;", "it", "", "invoke-_-orMbw", "(Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;I)J"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class LazyGridIntervalContent$Companion$DefaultSpan$1 extends r implements p<LazyGridItemSpanScope, Integer, GridItemSpan> {
    public static final LazyGridIntervalContent$Companion$DefaultSpan$1 INSTANCE = new LazyGridIntervalContent$Companion$DefaultSpan$1();

    public LazyGridIntervalContent$Companion$DefaultSpan$1() {
        super(2);
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return GridItemSpan.m644boximpl(m659invoke_orMbw((LazyGridItemSpanScope) obj, ((Number) obj2).intValue()));
    }

    /* JADX INFO: renamed from: invoke-_-orMbw, reason: not valid java name */
    public final long m659invoke_orMbw(LazyGridItemSpanScope lazyGridItemSpanScope, int i10) {
        return LazyGridSpanKt.GridItemSpan(1);
    }
}
