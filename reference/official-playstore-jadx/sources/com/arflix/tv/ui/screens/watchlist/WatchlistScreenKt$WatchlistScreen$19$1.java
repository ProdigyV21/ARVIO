package com.arflix.tv.ui.screens.watchlist;

import androidx.compose.foundation.lazy.grid.LazyGridState;
import androidx.compose.runtime.MutableIntState;
import com.arflix.tv.data.model.MediaItem;
import java.util.List;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt$WatchlistScreen$19$1", f = "WatchlistScreen.kt", l = {404}, m = "invokeSuspend", v = 2)
public final class WatchlistScreenKt$WatchlistScreen$19$1 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ MutableIntState $focusedItemIndex$delegate;
    final /* synthetic */ boolean $isHomeServerMode;
    final /* synthetic */ boolean $isLibraryMode;
    final /* synthetic */ int $libraryColumns;
    final /* synthetic */ LazyGridState $libraryGridState;
    final /* synthetic */ WatchlistViewModel $viewModel;
    final /* synthetic */ List<MediaItem> $visibleLibraryItems;
    int I$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchlistScreenKt$WatchlistScreen$19$1(boolean z, List<MediaItem> list, LazyGridState lazyGridState, int i10, boolean z5, WatchlistViewModel watchlistViewModel, MutableIntState mutableIntState, d7.d<? super WatchlistScreenKt$WatchlistScreen$19$1> dVar) {
        super(2, dVar);
        this.$isLibraryMode = z;
        this.$visibleLibraryItems = list;
        this.$libraryGridState = lazyGridState;
        this.$libraryColumns = i10;
        this.$isHomeServerMode = z5;
        this.$viewModel = watchlistViewModel;
        this.$focusedItemIndex$delegate = mutableIntState;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new WatchlistScreenKt$WatchlistScreen$19$1(this.$isLibraryMode, this.$visibleLibraryItems, this.$libraryGridState, this.$libraryColumns, this.$isHomeServerMode, this.$viewModel, this.$focusedItemIndex$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        WatchlistScreenKt$WatchlistScreen$19$1 watchlistScreenKt$WatchlistScreen$19$1;
        int i10;
        int i11 = this.label;
        if (i11 == 0) {
            k2.c.G(obj);
            if (!this.$isLibraryMode || this.$visibleLibraryItems.isEmpty()) {
                return t0.f22605a;
            }
            int iN = qb.d.n(this.$focusedItemIndex$delegate.getIntValue(), 0, t7.a.w(this.$visibleLibraryItems));
            LazyGridState lazyGridState = this.$libraryGridState;
            this.I$0 = iN;
            this.label = 1;
            watchlistScreenKt$WatchlistScreen$19$1 = this;
            Object objAnimateScrollToItem$default = LazyGridState.animateScrollToItem$default(lazyGridState, iN, 0, watchlistScreenKt$WatchlistScreen$19$1, 2, null);
            e7.a aVar = e7.a.f15033i;
            if (objAnimateScrollToItem$default == aVar) {
                return aVar;
            }
            i10 = iN;
        } else {
            if (i11 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            i10 = this.I$0;
            k2.c.G(obj);
            watchlistScreenKt$WatchlistScreen$19$1 = this;
        }
        if (i10 >= watchlistScreenKt$WatchlistScreen$19$1.$visibleLibraryItems.size() - (watchlistScreenKt$WatchlistScreen$19$1.$libraryColumns * 2)) {
            if (watchlistScreenKt$WatchlistScreen$19$1.$isHomeServerMode) {
                watchlistScreenKt$WatchlistScreen$19$1.$viewModel.loadMoreLibrary();
            } else {
                watchlistScreenKt$WatchlistScreen$19$1.$viewModel.loadMoreActiveSource();
            }
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((WatchlistScreenKt$WatchlistScreen$19$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
