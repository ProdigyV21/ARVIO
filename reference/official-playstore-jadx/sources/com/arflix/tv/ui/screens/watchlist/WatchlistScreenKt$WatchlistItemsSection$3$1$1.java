package com.arflix.tv.ui.screens.watchlist;

import androidx.compose.foundation.lazy.LazyListState;
import com.arflix.tv.data.model.MediaItem;
import java.util.List;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt$WatchlistItemsSection$3$1$1", f = "WatchlistScreen.kt", l = {1434, 1435}, m = "invokeSuspend", v = 2)
public final class WatchlistScreenKt$WatchlistItemsSection$3$1$1 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ int $focusedItemIndex;
    final /* synthetic */ List<MediaItem> $items;
    final /* synthetic */ LazyListState $lazyListState;
    int I$0;
    int I$1;
    int I$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchlistScreenKt$WatchlistItemsSection$3$1$1(int i10, List<MediaItem> list, LazyListState lazyListState, d7.d<? super WatchlistScreenKt$WatchlistItemsSection$3$1$1> dVar) {
        super(2, dVar);
        this.$focusedItemIndex = i10;
        this.$items = list;
        this.$lazyListState = lazyListState;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new WatchlistScreenKt$WatchlistItemsSection$3$1$1(this.$focusedItemIndex, this.$items, this.$lazyListState, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0086 A[RETURN] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            int r0 = r11.label
            x6.t0 r1 = x6.t0.f22605a
            r2 = 2
            r3 = 1
            if (r0 == 0) goto L1d
            if (r0 == r3) goto L18
            if (r0 != r2) goto L10
            k2.c.G(r12)
            goto L71
        L10:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L18:
            k2.c.G(r12)
            goto L87
        L1d:
            k2.c.G(r12)
            int r12 = r11.$focusedItemIndex
            if (r12 >= 0) goto L25
            return r1
        L25:
            java.util.List<com.arflix.tv.data.model.MediaItem> r0 = r11.$items
            int r0 = r0.size()
            int r0 = r0 - r3
            r4 = 0
            if (r0 >= 0) goto L30
            r0 = r4
        L30:
            int r6 = qb.d.n(r12, r4, r0)
            androidx.compose.foundation.lazy.LazyListState r12 = r11.$lazyListState
            int r12 = r12.getFirstVisibleItemIndex()
            androidx.compose.foundation.lazy.LazyListState r0 = r11.$lazyListState
            androidx.compose.foundation.lazy.LazyListLayoutInfo r0 = r0.getLayoutInfo()
            java.util.List r0 = r0.getVisibleItemsInfo()
            java.lang.Object r0 = kotlin.collections.x.y0(r0)
            androidx.compose.foundation.lazy.LazyListItemInfo r0 = (androidx.compose.foundation.lazy.LazyListItemInfo) r0
            if (r0 == 0) goto L51
            int r0 = r0.getIndex()
            goto L52
        L51:
            r0 = r12
        L52:
            e7.a r4 = e7.a.f15033i
            if (r6 < r12) goto L58
            if (r6 <= r0) goto L5a
        L58:
            r8 = r11
            goto L73
        L5a:
            if (r6 == r12) goto L71
            androidx.compose.foundation.lazy.LazyListState r5 = r11.$lazyListState
            r11.I$0 = r6
            r11.I$1 = r12
            r11.I$2 = r0
            r11.label = r2
            r7 = 0
            r9 = 2
            r10 = 0
            r8 = r11
            java.lang.Object r12 = androidx.compose.foundation.lazy.LazyListState.animateScrollToItem$default(r5, r6, r7, r8, r9, r10)
            if (r12 != r4) goto L87
            goto L86
        L71:
            r8 = r11
            goto L87
        L73:
            androidx.compose.foundation.lazy.LazyListState r5 = r8.$lazyListState
            r8.I$0 = r6
            r8.I$1 = r12
            r8.I$2 = r0
            r8.label = r3
            r7 = 0
            r9 = 2
            r10 = 0
            java.lang.Object r12 = androidx.compose.foundation.lazy.LazyListState.scrollToItem$default(r5, r6, r7, r8, r9, r10)
            if (r12 != r4) goto L87
        L86:
            return r4
        L87:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt$WatchlistItemsSection$3$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((WatchlistScreenKt$WatchlistItemsSection$3$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
