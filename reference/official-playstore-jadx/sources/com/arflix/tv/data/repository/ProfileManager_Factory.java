package com.arflix.tv.data.repository;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class ProfileManager_Factory implements Factory<ProfileManager> {
    private final Provider<Context> contextProvider;
    private final Provider<ProfileRepository> profileRepositoryProvider;

    private ProfileManager_Factory(Provider<Context> provider, Provider<ProfileRepository> provider2) {
        this.contextProvider = provider;
        this.profileRepositoryProvider = provider2;
    }

    public static ProfileManager_Factory create(Provider<Context> provider, Provider<ProfileRepository> provider2) {
        return new ProfileManager_Factory(provider, provider2);
    }

    public static ProfileManager newInstance(Context context, ProfileRepository profileRepository) {
        return new ProfileManager(context, profileRepository);
    }

    @Override // javax.inject.Provider
    public ProfileManager get() {
        return newInstance(this.contextProvider.get(), this.profileRepositoryProvider.get());
    }
}
