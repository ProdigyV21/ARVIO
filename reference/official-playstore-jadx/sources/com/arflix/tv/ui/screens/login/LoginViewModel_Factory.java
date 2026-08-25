package com.arflix.tv.ui.screens.login;

import android.content.Context;
import com.arflix.tv.data.repository.AuthRepository;
import com.arflix.tv.data.repository.CloudSyncRepository;
import com.arflix.tv.data.repository.StreamRepository;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class LoginViewModel_Factory implements Factory<LoginViewModel> {
    private final Provider<AuthRepository> authRepositoryProvider;
    private final Provider<CloudSyncRepository> cloudSyncRepositoryProvider;
    private final Provider<Context> contextProvider;
    private final Provider<StreamRepository> streamRepositoryProvider;

    private LoginViewModel_Factory(Provider<Context> provider, Provider<AuthRepository> provider2, Provider<StreamRepository> provider3, Provider<CloudSyncRepository> provider4) {
        this.contextProvider = provider;
        this.authRepositoryProvider = provider2;
        this.streamRepositoryProvider = provider3;
        this.cloudSyncRepositoryProvider = provider4;
    }

    public static LoginViewModel_Factory create(Provider<Context> provider, Provider<AuthRepository> provider2, Provider<StreamRepository> provider3, Provider<CloudSyncRepository> provider4) {
        return new LoginViewModel_Factory(provider, provider2, provider3, provider4);
    }

    public static LoginViewModel newInstance(Context context, AuthRepository authRepository, StreamRepository streamRepository, CloudSyncRepository cloudSyncRepository) {
        return new LoginViewModel(context, authRepository, streamRepository, cloudSyncRepository);
    }

    @Override // javax.inject.Provider
    public LoginViewModel get() {
        return newInstance(this.contextProvider.get(), this.authRepositoryProvider.get(), this.streamRepositoryProvider.get(), this.cloudSyncRepositoryProvider.get());
    }
}
