package com.arflix.tv.ui.screens.home;

import androidx.lifecycle.d1;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.LazyClassKey;

/* JADX INFO: loaded from: classes4.dex */
public final class HomeViewModel_HiltModules {

    @Module
    public static abstract class BindsModule {
        private BindsModule() {
        }

        @LazyClassKey(HomeViewModel.class)
        @Binds
        @IntoMap
        public abstract d1 binds(HomeViewModel homeViewModel);
    }

    @Module
    public static final class KeyModule {
        private KeyModule() {
        }

        @Provides
        @LazyClassKey(HomeViewModel.class)
        @IntoMap
        public static boolean provide() {
            return true;
        }
    }

    private HomeViewModel_HiltModules() {
    }
}
