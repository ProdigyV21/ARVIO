package com.arflix.tv.ui.screens.settings.telegram;

import androidx.lifecycle.d1;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.LazyClassKey;

/* JADX INFO: loaded from: classes4.dex */
public final class TelegramSettingsViewModel_HiltModules {

    @Module
    public static abstract class BindsModule {
        private BindsModule() {
        }

        @LazyClassKey(TelegramSettingsViewModel.class)
        @Binds
        @IntoMap
        public abstract d1 binds(TelegramSettingsViewModel telegramSettingsViewModel);
    }

    @Module
    public static final class KeyModule {
        private KeyModule() {
        }

        @Provides
        @LazyClassKey(TelegramSettingsViewModel.class)
        @IntoMap
        public static boolean provide() {
            return true;
        }
    }

    private TelegramSettingsViewModel_HiltModules() {
    }
}
