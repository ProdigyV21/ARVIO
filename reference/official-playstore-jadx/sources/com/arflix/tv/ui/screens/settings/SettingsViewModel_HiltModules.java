package com.arflix.tv.ui.screens.settings;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.LazyClassKey;

/* JADX INFO: loaded from: classes4.dex */
public final class SettingsViewModel_HiltModules {

    @Module
    public static abstract class BindsModule {
        private BindsModule() {
        }

        @LazyClassKey(SettingsViewModel.class)
        @Binds
        @IntoMap
        public abstract androidx.lifecycle.d1 binds(SettingsViewModel settingsViewModel);
    }

    @Module
    public static final class KeyModule {
        private KeyModule() {
        }

        @Provides
        @LazyClassKey(SettingsViewModel.class)
        @IntoMap
        public static boolean provide() {
            return true;
        }
    }

    private SettingsViewModel_HiltModules() {
    }
}
