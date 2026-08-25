package com.arflix.tv.di;

import com.arflix.tv.data.api.JikanApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import retrofit2.u0;

/* JADX INFO: loaded from: classes3.dex */
public final class AppModule_ProvideJikanApiFactory implements Factory<JikanApi> {
    private final Provider<u0> retrofitProvider;

    private AppModule_ProvideJikanApiFactory(Provider<u0> provider) {
        this.retrofitProvider = provider;
    }

    public static AppModule_ProvideJikanApiFactory create(Provider<u0> provider) {
        return new AppModule_ProvideJikanApiFactory(provider);
    }

    public static JikanApi provideJikanApi(u0 u0Var) {
        return (JikanApi) Preconditions.checkNotNullFromProvides(AppModule.provideJikanApi(u0Var));
    }

    @Override // javax.inject.Provider
    public JikanApi get() {
        return provideJikanApi(this.retrofitProvider.get());
    }
}
