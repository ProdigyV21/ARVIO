package com.arflix.tv.ui.screens.collections;

import com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel_HiltModules;
import dagger.internal.Factory;

/* JADX INFO: loaded from: classes4.dex */
public final class CollectionDetailsViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<Boolean> {

    public static final class InstanceHolder {
        static final CollectionDetailsViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new CollectionDetailsViewModel_HiltModules_KeyModule_ProvideFactory();

        private InstanceHolder() {
        }
    }

    public static CollectionDetailsViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static boolean provide() {
        return CollectionDetailsViewModel_HiltModules.KeyModule.provide();
    }

    @Override // javax.inject.Provider
    public Boolean get() {
        return Boolean.valueOf(provide());
    }
}
