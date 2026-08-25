package com.arflix.tv.di;

import com.arflix.tv.data.api.ArmApi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import retrofit2.u0;

/* JADX INFO: loaded from: classes3.dex */
public final class AppModule_ProvideArmApiFactory implements Factory<ArmApi> {
    private final Provider<u0> retrofitProvider;

    private AppModule_ProvideArmApiFactory(Provider<u0> provider) {
        this.retrofitProvider = provider;
    }

    public static AppModule_ProvideArmApiFactory create(Provider<u0> provider) {
        return new AppModule_ProvideArmApiFactory(provider);
    }

    public static ArmApi provideArmApi(u0 u0Var) {
        return (ArmApi) Preconditions.checkNotNullFromProvides(AppModule.provideArmApi(u0Var));
    }

    @Override // javax.inject.Provider
    public ArmApi get() {
        return provideArmApi(this.retrofitProvider.get());
    }
}
