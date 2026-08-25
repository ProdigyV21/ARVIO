package com.arflix.tv.ui.screens.player;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.LazyClassKey;

/* JADX INFO: loaded from: classes4.dex */
public final class PlayerViewModel_HiltModules {

    @Module
    public static abstract class BindsModule {
        private BindsModule() {
        }

        @LazyClassKey(PlayerViewModel.class)
        @Binds
        @IntoMap
        public abstract androidx.lifecycle.d1 binds(PlayerViewModel playerViewModel);
    }

    @Module
    public static final class KeyModule {
        private KeyModule() {
        }

        @Provides
        @LazyClassKey(PlayerViewModel.class)
        @IntoMap
        public static boolean provide() {
            return true;
        }
    }

    private PlayerViewModel_HiltModules() {
    }
}
