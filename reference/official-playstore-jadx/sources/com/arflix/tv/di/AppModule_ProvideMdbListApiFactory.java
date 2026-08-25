package com.arflix.tv.di;

import com.arflix.tv.data.api.MdbListApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import gb.h0;

/* JADX INFO: loaded from: classes3.dex */
public final class AppModule_ProvideMdbListApiFactory implements Factory<MdbListApi> {
    private final Provider<h0> okHttpClientProvider;

    private AppModule_ProvideMdbListApiFactory(Provider<h0> provider) {
        this.okHttpClientProvider = provider;
    }

    public static AppModule_ProvideMdbListApiFactory create(Provider<h0> provider) {
        return new AppModule_ProvideMdbListApiFactory(provider);
    }

    public static MdbListApi provideMdbListApi(h0 h0Var) {
        return (MdbListApi) Preconditions.checkNotNullFromProvides(AppModule.provideMdbListApi(h0Var));
    }

    @Override // javax.inject.Provider
    public MdbListApi get() {
        return provideMdbListApi(this.okHttpClientProvider.get());
    }
}
