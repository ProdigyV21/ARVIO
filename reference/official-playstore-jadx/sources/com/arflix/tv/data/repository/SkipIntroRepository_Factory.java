package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.AniSkipApi;
import com.arflix.tv.data.api.ArmApi;
import com.arflix.tv.data.api.IntroDbApi;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class SkipIntroRepository_Factory implements Factory<SkipIntroRepository> {
    private final Provider<AniSkipApi> aniSkipApiProvider;
    private final Provider<ArmApi> armApiProvider;
    private final Provider<IntroDbApi> introDbApiProvider;

    private SkipIntroRepository_Factory(Provider<IntroDbApi> provider, Provider<AniSkipApi> provider2, Provider<ArmApi> provider3) {
        this.introDbApiProvider = provider;
        this.aniSkipApiProvider = provider2;
        this.armApiProvider = provider3;
    }

    public static SkipIntroRepository_Factory create(Provider<IntroDbApi> provider, Provider<AniSkipApi> provider2, Provider<ArmApi> provider3) {
        return new SkipIntroRepository_Factory(provider, provider2, provider3);
    }

    public static SkipIntroRepository newInstance(IntroDbApi introDbApi, AniSkipApi aniSkipApi, ArmApi armApi) {
        return new SkipIntroRepository(introDbApi, aniSkipApi, armApi);
    }

    @Override // javax.inject.Provider
    public SkipIntroRepository get() {
        return newInstance(this.introDbApiProvider.get(), this.aniSkipApiProvider.get(), this.armApiProvider.get());
    }
}
