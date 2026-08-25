package com.arflix.tv.di;

import com.arflix.tv.data.api.IntroDbApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import retrofit2.u0;

/* JADX INFO: loaded from: classes3.dex */
public final class AppModule_ProvideIntroDbApiFactory implements Factory<IntroDbApi> {
    private final Provider<u0> retrofitProvider;

    private AppModule_ProvideIntroDbApiFactory(Provider<u0> provider) {
        this.retrofitProvider = provider;
    }

    public static AppModule_ProvideIntroDbApiFactory create(Provider<u0> provider) {
        return new AppModule_ProvideIntroDbApiFactory(provider);
    }

    public static IntroDbApi provideIntroDbApi(u0 u0Var) {
        return (IntroDbApi) Preconditions.checkNotNullFromProvides(AppModule.provideIntroDbApi(u0Var));
    }

    @Override // javax.inject.Provider
    public IntroDbApi get() {
        return provideIntroDbApi(this.retrofitProvider.get());
    }
}
