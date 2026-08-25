package com.arflix.tv.updater;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Provider;
import gb.h0;

/* JADX INFO: loaded from: classes4.dex */
public final class AppUpdateRepository_Factory implements Factory<AppUpdateRepository> {
    private final Provider<Context> contextProvider;
    private final Provider<h0> okHttpClientProvider;

    private AppUpdateRepository_Factory(Provider<Context> provider, Provider<h0> provider2) {
        this.contextProvider = provider;
        this.okHttpClientProvider = provider2;
    }

    public static AppUpdateRepository_Factory create(Provider<Context> provider, Provider<h0> provider2) {
        return new AppUpdateRepository_Factory(provider, provider2);
    }

    public static AppUpdateRepository newInstance(Context context, h0 h0Var) {
        return new AppUpdateRepository(context, h0Var);
    }

    @Override // javax.inject.Provider
    public AppUpdateRepository get() {
        return newInstance(this.contextProvider.get(), this.okHttpClientProvider.get());
    }
}
