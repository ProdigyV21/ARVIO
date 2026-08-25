package com.arflix.tv.ui.screens.details;

import com.arflix.tv.ui.screens.details.DetailsViewModel_HiltModules;
import dagger.internal.Factory;

/* JADX INFO: loaded from: classes4.dex */
public final class DetailsViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<Boolean> {

    public static final class InstanceHolder {
        static final DetailsViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new DetailsViewModel_HiltModules_KeyModule_ProvideFactory();

        private InstanceHolder() {
        }
    }

    public static DetailsViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static boolean provide() {
        return DetailsViewModel_HiltModules.KeyModule.provide();
    }

    @Override // javax.inject.Provider
    public Boolean get() {
        return Boolean.valueOf(provide());
    }
}
