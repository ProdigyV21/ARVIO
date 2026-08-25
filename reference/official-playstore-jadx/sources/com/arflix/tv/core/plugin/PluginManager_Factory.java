package com.arflix.tv.core.plugin;

import dagger.internal.Factory;

/* JADX INFO: loaded from: classes3.dex */
public final class PluginManager_Factory implements Factory<PluginManager> {

    public static final class InstanceHolder {
        static final PluginManager_Factory INSTANCE = new PluginManager_Factory();

        private InstanceHolder() {
        }
    }

    public static PluginManager_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static PluginManager newInstance() {
        return new PluginManager();
    }

    @Override // javax.inject.Provider
    public PluginManager get() {
        return newInstance();
    }
}
