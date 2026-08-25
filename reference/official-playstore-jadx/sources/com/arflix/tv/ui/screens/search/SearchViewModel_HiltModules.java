package com.arflix.tv.ui.screens.search;

import androidx.lifecycle.d1;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.LazyClassKey;

/* JADX INFO: loaded from: classes4.dex */
public final class SearchViewModel_HiltModules {

    @Module
    public static abstract class BindsModule {
        private BindsModule() {
        }

        @LazyClassKey(SearchViewModel.class)
        @Binds
        @IntoMap
        public abstract d1 binds(SearchViewModel searchViewModel);
    }

    @Module
    public static final class KeyModule {
        private KeyModule() {
        }

        @Provides
        @LazyClassKey(SearchViewModel.class)
        @IntoMap
        public static boolean provide() {
            return true;
        }
    }

    private SearchViewModel_HiltModules() {
    }
}
