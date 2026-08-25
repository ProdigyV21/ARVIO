package com.arflix.tv.ui.screens.profile;

import androidx.lifecycle.d1;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.LazyClassKey;

/* JADX INFO: loaded from: classes4.dex */
public final class ProfileViewModel_HiltModules {

    @Module
    public static abstract class BindsModule {
        private BindsModule() {
        }

        @LazyClassKey(ProfileViewModel.class)
        @Binds
        @IntoMap
        public abstract d1 binds(ProfileViewModel profileViewModel);
    }

    @Module
    public static final class KeyModule {
        private KeyModule() {
        }

        @Provides
        @LazyClassKey(ProfileViewModel.class)
        @IntoMap
        public static boolean provide() {
            return true;
        }
    }

    private ProfileViewModel_HiltModules() {
    }
}
