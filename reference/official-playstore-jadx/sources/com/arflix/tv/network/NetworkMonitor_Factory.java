package com.arflix.tv.network;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class NetworkMonitor_Factory implements Factory<NetworkMonitor> {
    private final Provider<Context> contextProvider;

    private NetworkMonitor_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static NetworkMonitor_Factory create(Provider<Context> provider) {
        return new NetworkMonitor_Factory(provider);
    }

    public static NetworkMonitor newInstance(Context context) {
        return new NetworkMonitor(context);
    }

    @Override // javax.inject.Provider
    public NetworkMonitor get() {
        return newInstance(this.contextProvider.get());
    }
}
