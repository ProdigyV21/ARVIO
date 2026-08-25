package com.arflix.tv.data.repository;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class TraktOutboxRepository_Factory implements Factory<TraktOutboxRepository> {
    private final Provider<Context> contextProvider;
    private final Provider<ProfileManager> profileManagerProvider;

    private TraktOutboxRepository_Factory(Provider<Context> provider, Provider<ProfileManager> provider2) {
        this.contextProvider = provider;
        this.profileManagerProvider = provider2;
    }

    public static TraktOutboxRepository_Factory create(Provider<Context> provider, Provider<ProfileManager> provider2) {
        return new TraktOutboxRepository_Factory(provider, provider2);
    }

    public static TraktOutboxRepository newInstance(Context context, ProfileManager profileManager) {
        return new TraktOutboxRepository(context, profileManager);
    }

    @Override // javax.inject.Provider
    public TraktOutboxRepository get() {
        return newInstance(this.contextProvider.get(), this.profileManagerProvider.get());
    }
}
