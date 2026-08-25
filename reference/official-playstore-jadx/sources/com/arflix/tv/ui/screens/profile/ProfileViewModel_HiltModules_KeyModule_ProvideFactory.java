package com.arflix.tv.ui.screens.profile;

import com.arflix.tv.ui.screens.profile.ProfileViewModel_HiltModules;
import dagger.internal.Factory;

/* JADX INFO: loaded from: classes4.dex */
public final class ProfileViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<Boolean> {

    public static final class InstanceHolder {
        static final ProfileViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new ProfileViewModel_HiltModules_KeyModule_ProvideFactory();

        private InstanceHolder() {
        }
    }

    public static ProfileViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static boolean provide() {
        return ProfileViewModel_HiltModules.KeyModule.provide();
    }

    @Override // javax.inject.Provider
    public Boolean get() {
        return Boolean.valueOf(provide());
    }
}
