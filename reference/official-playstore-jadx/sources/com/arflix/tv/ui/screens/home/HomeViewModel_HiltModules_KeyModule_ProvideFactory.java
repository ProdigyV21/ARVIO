package com.arflix.tv.ui.screens.home;

import com.arflix.tv.ui.screens.home.HomeViewModel_HiltModules;
import dagger.internal.Factory;

/* JADX INFO: loaded from: classes4.dex */
public final class HomeViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<Boolean> {

    public static final class InstanceHolder {
        static final HomeViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new HomeViewModel_HiltModules_KeyModule_ProvideFactory();

        private InstanceHolder() {
        }
    }

    public static HomeViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static boolean provide() {
        return HomeViewModel_HiltModules.KeyModule.provide();
    }

    @Override // javax.inject.Provider
    public Boolean get() {
        return Boolean.valueOf(provide());
    }
}
