package com.arflix.tv.data.repository;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class ProfileAvatarImageManager_Factory implements Factory<ProfileAvatarImageManager> {
    private final Provider<AuthRepository> authRepositoryProvider;
    private final Provider<Context> contextProvider;

    private ProfileAvatarImageManager_Factory(Provider<Context> provider, Provider<AuthRepository> provider2) {
        this.contextProvider = provider;
        this.authRepositoryProvider = provider2;
    }

    public static ProfileAvatarImageManager_Factory create(Provider<Context> provider, Provider<AuthRepository> provider2) {
        return new ProfileAvatarImageManager_Factory(provider, provider2);
    }

    public static ProfileAvatarImageManager newInstance(Context context, AuthRepository authRepository) {
        return new ProfileAvatarImageManager(context, authRepository);
    }

    @Override // javax.inject.Provider
    public ProfileAvatarImageManager get() {
        return newInstance(this.contextProvider.get(), this.authRepositoryProvider.get());
    }
}
