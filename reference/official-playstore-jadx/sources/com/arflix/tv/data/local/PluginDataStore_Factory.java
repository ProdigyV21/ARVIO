package com.arflix.tv.data.local;

import android.content.Context;
import com.squareup.moshi.z;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class PluginDataStore_Factory implements Factory<PluginDataStore> {
    private final Provider<Context> contextProvider;
    private final Provider<ProfileDataStoreFactory> factoryProvider;
    private final Provider<z> moshiProvider;

    private PluginDataStore_Factory(Provider<Context> provider, Provider<z> provider2, Provider<ProfileDataStoreFactory> provider3) {
        this.contextProvider = provider;
        this.moshiProvider = provider2;
        this.factoryProvider = provider3;
    }

    public static PluginDataStore_Factory create(Provider<Context> provider, Provider<z> provider2, Provider<ProfileDataStoreFactory> provider3) {
        return new PluginDataStore_Factory(provider, provider2, provider3);
    }

    public static PluginDataStore newInstance(Context context, z zVar, ProfileDataStoreFactory profileDataStoreFactory) {
        return new PluginDataStore(context, zVar, profileDataStoreFactory);
    }

    @Override // javax.inject.Provider
    public PluginDataStore get() {
        return newInstance(this.contextProvider.get(), this.moshiProvider.get(), this.factoryProvider.get());
    }
}
