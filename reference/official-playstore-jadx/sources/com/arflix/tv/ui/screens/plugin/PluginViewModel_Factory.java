package com.arflix.tv.ui.screens.plugin;

import android.content.Context;
import com.arflix.tv.core.plugin.PluginManager;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class PluginViewModel_Factory implements Factory<PluginViewModel> {
    private final Provider<Context> contextProvider;
    private final Provider<PluginManager> pluginManagerProvider;

    private PluginViewModel_Factory(Provider<PluginManager> provider, Provider<Context> provider2) {
        this.pluginManagerProvider = provider;
        this.contextProvider = provider2;
    }

    public static PluginViewModel_Factory create(Provider<PluginManager> provider, Provider<Context> provider2) {
        return new PluginViewModel_Factory(provider, provider2);
    }

    public static PluginViewModel newInstance(PluginManager pluginManager, Context context) {
        return new PluginViewModel(pluginManager, context);
    }

    @Override // javax.inject.Provider
    public PluginViewModel get() {
        return newInstance(this.pluginManagerProvider.get(), this.contextProvider.get());
    }
}
