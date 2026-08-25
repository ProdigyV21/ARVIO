package com.arflix.tv.di;

import com.arflix.tv.data.api.AniSkipApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import retrofit2.u0;

/* JADX INFO: loaded from: classes3.dex */
public final class AppModule_ProvideAniSkipApiFactory implements Factory<AniSkipApi> {
    private final Provider<u0> retrofitProvider;

    private AppModule_ProvideAniSkipApiFactory(Provider<u0> provider) {
        this.retrofitProvider = provider;
    }

    public static AppModule_ProvideAniSkipApiFactory create(Provider<u0> provider) {
        return new AppModule_ProvideAniSkipApiFactory(provider);
    }

    public static AniSkipApi provideAniSkipApi(u0 u0Var) {
        return (AniSkipApi) Preconditions.checkNotNullFromProvides(AppModule.provideAniSkipApi(u0Var));
    }

    @Override // javax.inject.Provider
    public AniSkipApi get() {
        return provideAniSkipApi(this.retrofitProvider.get());
    }
}
