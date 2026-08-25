package com.arflix.tv.data.repository;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class HomeServerRepository_Factory implements Factory<HomeServerRepository> {
    private final Provider<Context> contextProvider;
    private final Provider<gb.h0> okHttpClientProvider;
    private final Provider<ProfileManager> profileManagerProvider;

    private HomeServerRepository_Factory(Provider<Context> provider, Provider<gb.h0> provider2, Provider<ProfileManager> provider3) {
        this.contextProvider = provider;
        this.okHttpClientProvider = provider2;
        this.profileManagerProvider = provider3;
    }

    public static HomeServerRepository_Factory create(Provider<Context> provider, Provider<gb.h0> provider2, Provider<ProfileManager> provider3) {
        return new HomeServerRepository_Factory(provider, provider2, provider3);
    }

    public static HomeServerRepository newInstance(Context context, gb.h0 h0Var, ProfileManager profileManager) {
        return new HomeServerRepository(context, h0Var, profileManager);
    }

    @Override // javax.inject.Provider
    public HomeServerRepository get() {
        return newInstance(this.contextProvider.get(), this.okHttpClientProvider.get(), this.profileManagerProvider.get());
    }
}
