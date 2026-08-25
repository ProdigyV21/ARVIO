package com.arflix.tv.updater;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Provider;
import gb.h0;

/* JADX INFO: loaded from: classes4.dex */
public final class ApkDownloader_Factory implements Factory<ApkDownloader> {
    private final Provider<Context> contextProvider;
    private final Provider<h0> okHttpClientProvider;

    private ApkDownloader_Factory(Provider<Context> provider, Provider<h0> provider2) {
        this.contextProvider = provider;
        this.okHttpClientProvider = provider2;
    }

    public static ApkDownloader_Factory create(Provider<Context> provider, Provider<h0> provider2) {
        return new ApkDownloader_Factory(provider, provider2);
    }

    public static ApkDownloader newInstance(Context context, h0 h0Var) {
        return new ApkDownloader(context, h0Var);
    }

    @Override // javax.inject.Provider
    public ApkDownloader get() {
        return newInstance(this.contextProvider.get(), this.okHttpClientProvider.get());
    }
}
