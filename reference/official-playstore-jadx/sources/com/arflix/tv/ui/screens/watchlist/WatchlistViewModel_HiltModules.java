package com.arflix.tv.ui.screens.watchlist;

import androidx.lifecycle.d1;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.LazyClassKey;

/* JADX INFO: loaded from: classes4.dex */
public final class WatchlistViewModel_HiltModules {

    @Module
    public static abstract class BindsModule {
        private BindsModule() {
        }

        @LazyClassKey(WatchlistViewModel.class)
        @Binds
        @IntoMap
        public abstract d1 binds(WatchlistViewModel watchlistViewModel);
    }

    @Module
    public static final class KeyModule {
        private KeyModule() {
        }

        @Provides
        @LazyClassKey(WatchlistViewModel.class)
        @IntoMap
        public static boolean provide() {
            return true;
        }
    }

    private WatchlistViewModel_HiltModules() {
    }
}
