package com.arflix.tv.ui.screens.watchlist;

import androidx.compose.foundation.lazy.grid.LazyGridItemInfo;
import androidx.compose.foundation.lazy.grid.LazyGridState;
import com.arflix.tv.data.model.MediaItem;
import java.util.List;
import ka.k0;
import kotlin.Metadata;
import kotlin.collections.x;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt$WatchlistScreen$20$1", f = "WatchlistScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class WatchlistScreenKt$WatchlistScreen$20$1 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ boolean $isHomeServerMode;
    final /* synthetic */ boolean $isMobile;
    final /* synthetic */ int $libraryColumns;
    final /* synthetic */ LazyGridState $libraryGridState;
    final /* synthetic */ WatchlistViewModel $viewModel;
    final /* synthetic */ List<MediaItem> $visibleLibraryItems;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchlistScreenKt$WatchlistScreen$20$1(LazyGridState lazyGridState, boolean z, List<MediaItem> list, int i10, boolean z5, WatchlistViewModel watchlistViewModel, d7.d<? super WatchlistScreenKt$WatchlistScreen$20$1> dVar) {
        super(2, dVar);
        this.$libraryGridState = lazyGridState;
        this.$isMobile = z;
        this.$visibleLibraryItems = list;
        this.$libraryColumns = i10;
        this.$isHomeServerMode = z5;
        this.$viewModel = watchlistViewModel;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new WatchlistScreenKt$WatchlistScreen$20$1(this.$libraryGridState, this.$isMobile, this.$visibleLibraryItems, this.$libraryColumns, this.$isHomeServerMode, this.$viewModel, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        LazyGridItemInfo lazyGridItemInfo = (LazyGridItemInfo) x.y0(this.$libraryGridState.getLayoutInfo().getVisibleItemsInfo());
        int index = lazyGridItemInfo != null ? lazyGridItemInfo.getIndex() : -1;
        if (this.$isMobile && index >= this.$visibleLibraryItems.size() - (this.$libraryColumns * 2)) {
            if (this.$isHomeServerMode) {
                this.$viewModel.loadMoreLibrary();
            } else {
                this.$viewModel.loadMoreActiveSource();
            }
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((WatchlistScreenKt$WatchlistScreen$20$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
