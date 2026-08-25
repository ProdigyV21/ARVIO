package com.arflix.tv.ui.screens.plugin;

import androidx.lifecycle.d1;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.LazyClassKey;

/* JADX INFO: loaded from: classes4.dex */
public final class PluginViewModel_HiltModules {

    @Module
    public static abstract class BindsModule {
        private BindsModule() {
        }

        @LazyClassKey(PluginViewModel.class)
        @Binds
        @IntoMap
        public abstract d1 binds(PluginViewModel pluginViewModel);
    }

    @Module
    public static final class KeyModule {
        private KeyModule() {
        }

        @Provides
        @LazyClassKey(PluginViewModel.class)
        @IntoMap
        public static boolean provide() {
            return true;
        }
    }

    private PluginViewModel_HiltModules() {
    }
}
