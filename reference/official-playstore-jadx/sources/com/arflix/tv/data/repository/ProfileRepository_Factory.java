package com.arflix.tv.data.repository;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class ProfileRepository_Factory implements Factory<ProfileRepository> {
    private final Provider<AuthRepository> authRepositoryProvider;
    private final Provider<Context> contextProvider;
    private final Provider<CloudSyncInvalidationBus> invalidationBusProvider;
    private final Provider<ProfileAvatarImageManager> profileAvatarImageManagerProvider;

    private ProfileRepository_Factory(Provider<Context> provider, Provider<AuthRepository> provider2, Provider<ProfileAvatarImageManager> provider3, Provider<CloudSyncInvalidationBus> provider4) {
        this.contextProvider = provider;
        this.authRepositoryProvider = provider2;
        this.profileAvatarImageManagerProvider = provider3;
        this.invalidationBusProvider = provider4;
    }

    public static ProfileRepository_Factory create(Provider<Context> provider, Provider<AuthRepository> provider2, Provider<ProfileAvatarImageManager> provider3, Provider<CloudSyncInvalidationBus> provider4) {
        return new ProfileRepository_Factory(provider, provider2, provider3, provider4);
    }

    public static ProfileRepository newInstance(Context context, AuthRepository authRepository, ProfileAvatarImageManager profileAvatarImageManager, CloudSyncInvalidationBus cloudSyncInvalidationBus) {
        return new ProfileRepository(context, authRepository, profileAvatarImageManager, cloudSyncInvalidationBus);
    }

    @Override // javax.inject.Provider
    public ProfileRepository get() {
        return newInstance(this.contextProvider.get(), this.authRepositoryProvider.get(), this.profileAvatarImageManagerProvider.get(), this.invalidationBusProvider.get());
    }
}
