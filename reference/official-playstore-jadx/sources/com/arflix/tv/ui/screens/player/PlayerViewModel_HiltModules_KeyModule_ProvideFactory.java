package com.arflix.tv.ui.screens.player;

import com.arflix.tv.ui.screens.player.PlayerViewModel_HiltModules;
import dagger.internal.Factory;

/* JADX INFO: loaded from: classes4.dex */
public final class PlayerViewModel_HiltModules_KeyModule_ProvideFactory implements Factory<Boolean> {

    public static final class InstanceHolder {
        static final PlayerViewModel_HiltModules_KeyModule_ProvideFactory INSTANCE = new PlayerViewModel_HiltModules_KeyModule_ProvideFactory();

        private InstanceHolder() {
        }
    }

    public static PlayerViewModel_HiltModules_KeyModule_ProvideFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static boolean provide() {
        return PlayerViewModel_HiltModules.KeyModule.provide();
    }

    @Override // javax.inject.Provider
    public Boolean get() {
        return Boolean.valueOf(provide());
    }
}
