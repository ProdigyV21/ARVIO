package com.arflix.tv.ui.screens.watchlist;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
public final /* synthetic */ class WatchlistScreenKt$WatchlistScreen$22$1$1$1 extends kotlin.jvm.internal.m implements r7.l<Integer, t0> {
    final /* synthetic */ MutableIntState $focusedItemIndex$delegate;
    final /* synthetic */ MutableIntState $focusedSectionIndex$delegate;
    final /* synthetic */ MutableIntState $libraryFocusIndex$delegate;
    final /* synthetic */ MutableIntState $providerFocusIndex$delegate;
    final /* synthetic */ List<LibraryProviderOption> $providers;
    final /* synthetic */ MutableState<String> $selectedProviderId$delegate;
    final /* synthetic */ MutableState<String> $trackerSearchQuery$delegate;
    final /* synthetic */ WatchlistViewModel $viewModel;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WatchlistScreenKt$WatchlistScreen$22$1$1$1(List<LibraryProviderOption> list, WatchlistViewModel watchlistViewModel, MutableIntState mutableIntState, MutableState<String> mutableState, MutableState<String> mutableState2, MutableIntState mutableIntState2, MutableIntState mutableIntState3, MutableIntState mutableIntState4) {
        super(1, kotlin.jvm.internal.o.class, "activateProvider", "WatchlistScreen$activateProvider(Ljava/util/List;Lcom/arflix/tv/ui/screens/watchlist/WatchlistViewModel;Landroidx/compose/runtime/MutableIntState;Landroidx/compose/runtime/MutableState;Landroidx/compose/runtime/MutableState;Landroidx/compose/runtime/MutableIntState;Landroidx/compose/runtime/MutableIntState;Landroidx/compose/runtime/MutableIntState;I)V", 0);
        this.$providers = list;
        this.$viewModel = watchlistViewModel;
        this.$providerFocusIndex$delegate = mutableIntState;
        this.$selectedProviderId$delegate = mutableState;
        this.$trackerSearchQuery$delegate = mutableState2;
        this.$libraryFocusIndex$delegate = mutableIntState2;
        this.$focusedSectionIndex$delegate = mutableIntState3;
        this.$focusedItemIndex$delegate = mutableIntState4;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke(((Number) obj).intValue());
        return t0.f22605a;
    }

    public final void invoke(int i10) {
        WatchlistScreenKt.WatchlistScreen$activateProvider(this.$providers, this.$viewModel, this.$providerFocusIndex$delegate, this.$selectedProviderId$delegate, this.$trackerSearchQuery$delegate, this.$libraryFocusIndex$delegate, this.$focusedSectionIndex$delegate, this.$focusedItemIndex$delegate, i10);
    }
}
