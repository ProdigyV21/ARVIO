package com.arflix.tv.ui.startup;

import androidx.lifecycle.d1;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.LazyClassKey;

/* JADX INFO: loaded from: classes4.dex */
public final class StartupViewModel_HiltModules {

    @Module
    public static abstract class BindsModule {
        private BindsModule() {
        }

        @LazyClassKey(StartupViewModel.class)
        @Binds
        @IntoMap
        public abstract d1 binds(StartupViewModel startupViewModel);
    }

    @Module
    public static final class KeyModule {
        private KeyModule() {
        }

        @Provides
        @LazyClassKey(StartupViewModel.class)
        @IntoMap
        public static boolean provide() {
            return true;
        }
    }

    private StartupViewModel_HiltModules() {
    }
}
