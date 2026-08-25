package com.arflix.tv.di;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import gb.h0;

/* JADX INFO: loaded from: classes3.dex */
public final class AppModule_ProvideOkHttpClientFactory implements Factory<h0> {

    public static final class InstanceHolder {
        static final AppModule_ProvideOkHttpClientFactory INSTANCE = new AppModule_ProvideOkHttpClientFactory();

        private InstanceHolder() {
        }
    }

    public static AppModule_ProvideOkHttpClientFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static h0 provideOkHttpClient() {
        return (h0) Preconditions.checkNotNullFromProvides(AppModule.provideOkHttpClient());
    }

    @Override // javax.inject.Provider
    public h0 get() {
        return provideOkHttpClient();
    }
}
