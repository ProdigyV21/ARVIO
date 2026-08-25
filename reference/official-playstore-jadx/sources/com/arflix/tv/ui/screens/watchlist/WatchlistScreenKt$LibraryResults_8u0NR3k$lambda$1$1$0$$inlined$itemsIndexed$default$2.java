package com.arflix.tv.ui.screens.watchlist;

import androidx.compose.foundation.lazy.grid.GridItemSpan;
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import r7.q;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "Landroidx/compose/foundation/lazy/grid/GridItemSpan;", "T", "Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;", "it", "", "invoke-_-orMbw", "(Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;I)J", "androidx/compose/foundation/lazy/grid/LazyGridDslKt$itemsIndexed$3"}, k = 3, mv = {2, 3, 0}, xi = 48)
public final class WatchlistScreenKt$LibraryResults_8u0NR3k$lambda$1$1$0$$inlined$itemsIndexed$default$2 extends r implements p<LazyGridItemSpanScope, Integer, GridItemSpan> {
    final /* synthetic */ List $items;
    final /* synthetic */ q $span;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchlistScreenKt$LibraryResults_8u0NR3k$lambda$1$1$0$$inlined$itemsIndexed$default$2(q qVar, List list) {
        super(2);
        this.$span = qVar;
        this.$items = list;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return GridItemSpan.m644boximpl(m6464invoke_orMbw((LazyGridItemSpanScope) obj, ((Number) obj2).intValue()));
    }

    /* JADX INFO: renamed from: invoke-_-orMbw, reason: not valid java name */
    public final long m6464invoke_orMbw(LazyGridItemSpanScope lazyGridItemSpanScope, int i10) {
        return ((GridItemSpan) this.$span.invoke(lazyGridItemSpanScope, Integer.valueOf(i10), this.$items.get(i10))).getPackedValue();
    }
}
