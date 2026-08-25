package com.arflix.tv.ui.startup;

import android.content.Context;
import com.arflix.tv.data.repository.MediaRepository;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class StartupViewModel_Factory implements Factory<StartupViewModel> {
    private final Provider<Context> contextProvider;
    private final Provider<MediaRepository> mediaRepositoryProvider;

    private StartupViewModel_Factory(Provider<MediaRepository> provider, Provider<Context> provider2) {
        this.mediaRepositoryProvider = provider;
        this.contextProvider = provider2;
    }

    public static StartupViewModel_Factory create(Provider<MediaRepository> provider, Provider<Context> provider2) {
        return new StartupViewModel_Factory(provider, provider2);
    }

    public static StartupViewModel newInstance(MediaRepository mediaRepository, Context context) {
        return new StartupViewModel(mediaRepository, context);
    }

    @Override // javax.inject.Provider
    public StartupViewModel get() {
        return newInstance(this.mediaRepositoryProvider.get(), this.contextProvider.get());
    }
}
