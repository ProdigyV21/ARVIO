package com.arflix.tv.di;

import com.arflix.tv.data.api.SupabaseApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import gb.h0;

/* JADX INFO: loaded from: classes3.dex */
public final class AppModule_ProvideSupabaseApiFactory implements Factory<SupabaseApi> {
    private final Provider<h0> okHttpClientProvider;

    private AppModule_ProvideSupabaseApiFactory(Provider<h0> provider) {
        this.okHttpClientProvider = provider;
    }

    public static AppModule_ProvideSupabaseApiFactory create(Provider<h0> provider) {
        return new AppModule_ProvideSupabaseApiFactory(provider);
    }

    public static SupabaseApi provideSupabaseApi(h0 h0Var) {
        return (SupabaseApi) Preconditions.checkNotNullFromProvides(AppModule.provideSupabaseApi(h0Var));
    }

    @Override // javax.inject.Provider
    public SupabaseApi get() {
        return provideSupabaseApi(this.okHttpClientProvider.get());
    }
}
