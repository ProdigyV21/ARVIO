package com.arflix.tv.data.repository;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class AppUsageAnalyticsRepository_Factory implements Factory<AppUsageAnalyticsRepository> {
    private final Provider<AuthRepository> authRepositoryProvider;
    private final Provider<Context> contextProvider;
    private final Provider<gb.h0> okHttpClientProvider;
    private final Provider<ProfileManager> profileManagerProvider;

    private AppUsageAnalyticsRepository_Factory(Provider<Context> provider, Provider<gb.h0> provider2, Provider<AuthRepository> provider3, Provider<ProfileManager> provider4) {
        this.contextProvider = provider;
        this.okHttpClientProvider = provider2;
        this.authRepositoryProvider = provider3;
        this.profileManagerProvider = provider4;
    }

    public static AppUsageAnalyticsRepository_Factory create(Provider<Context> provider, Provider<gb.h0> provider2, Provider<AuthRepository> provider3, Provider<ProfileManager> provider4) {
        return new AppUsageAnalyticsRepository_Factory(provider, provider2, provider3, provider4);
    }

    public static AppUsageAnalyticsRepository newInstance(Context context, gb.h0 h0Var, AuthRepository authRepository, ProfileManager profileManager) {
        return new AppUsageAnalyticsRepository(context, h0Var, authRepository, profileManager);
    }

    @Override // javax.inject.Provider
    public AppUsageAnalyticsRepository get() {
        return newInstance(this.contextProvider.get(), this.okHttpClientProvider.get(), this.authRepositoryProvider.get(), this.profileManagerProvider.get());
    }
}
