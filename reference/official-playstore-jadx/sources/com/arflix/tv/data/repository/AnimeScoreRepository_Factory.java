package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.ArmApi;
import com.arflix.tv.data.api.JikanApi;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class AnimeScoreRepository_Factory implements Factory<AnimeScoreRepository> {
    private final Provider<ArmApi> armApiProvider;
    private final Provider<JikanApi> jikanApiProvider;

    private AnimeScoreRepository_Factory(Provider<ArmApi> provider, Provider<JikanApi> provider2) {
        this.armApiProvider = provider;
        this.jikanApiProvider = provider2;
    }

    public static AnimeScoreRepository_Factory create(Provider<ArmApi> provider, Provider<JikanApi> provider2) {
        return new AnimeScoreRepository_Factory(provider, provider2);
    }

    public static AnimeScoreRepository newInstance(ArmApi armApi, JikanApi jikanApi) {
        return new AnimeScoreRepository(armApi, jikanApi);
    }

    @Override // javax.inject.Provider
    public AnimeScoreRepository get() {
        return newInstance(this.armApiProvider.get(), this.jikanApiProvider.get());
    }
}
