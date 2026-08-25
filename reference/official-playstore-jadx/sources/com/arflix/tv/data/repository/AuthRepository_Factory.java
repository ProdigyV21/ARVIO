package com.arflix.tv.data.repository;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class AuthRepository_Factory implements Factory<AuthRepository> {
    private final Provider<CloudSyncRepository> cloudSyncRepositoryProvider;
    private final Provider<Context> contextProvider;
    private final Provider<gb.h0> okHttpClientProvider;
    private final Provider<TraktRepository> traktRepositoryProvider;

    private AuthRepository_Factory(Provider<Context> provider, Provider<gb.h0> provider2, Provider<TraktRepository> provider3, Provider<CloudSyncRepository> provider4) {
        this.contextProvider = provider;
        this.okHttpClientProvider = provider2;
        this.traktRepositoryProvider = provider3;
        this.cloudSyncRepositoryProvider = provider4;
    }

    public static AuthRepository_Factory create(Provider<Context> provider, Provider<gb.h0> provider2, Provider<TraktRepository> provider3, Provider<CloudSyncRepository> provider4) {
        return new AuthRepository_Factory(provider, provider2, provider3, provider4);
    }

    public static AuthRepository newInstance(Context context, gb.h0 h0Var, javax.inject.Provider<TraktRepository> provider, javax.inject.Provider<CloudSyncRepository> provider2) {
        return new AuthRepository(context, h0Var, provider, provider2);
    }

    @Override // javax.inject.Provider
    public AuthRepository get() {
        return newInstance(this.contextProvider.get(), this.okHttpClientProvider.get(), this.traktRepositoryProvider, this.cloudSyncRepositoryProvider);
    }
}
