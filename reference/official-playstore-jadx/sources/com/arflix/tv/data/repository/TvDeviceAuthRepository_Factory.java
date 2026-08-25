package com.arflix.tv.data.repository;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class TvDeviceAuthRepository_Factory implements Factory<TvDeviceAuthRepository> {
    private final Provider<Context> contextProvider;
    private final Provider<gb.h0> okHttpClientProvider;

    private TvDeviceAuthRepository_Factory(Provider<Context> provider, Provider<gb.h0> provider2) {
        this.contextProvider = provider;
        this.okHttpClientProvider = provider2;
    }

    public static TvDeviceAuthRepository_Factory create(Provider<Context> provider, Provider<gb.h0> provider2) {
        return new TvDeviceAuthRepository_Factory(provider, provider2);
    }

    public static TvDeviceAuthRepository newInstance(Context context, gb.h0 h0Var) {
        return new TvDeviceAuthRepository(context, h0Var);
    }

    @Override // javax.inject.Provider
    public TvDeviceAuthRepository get() {
        return newInstance(this.contextProvider.get(), this.okHttpClientProvider.get());
    }
}
