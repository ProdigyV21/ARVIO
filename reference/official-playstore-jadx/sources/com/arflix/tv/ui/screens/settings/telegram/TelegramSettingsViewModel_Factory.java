package com.arflix.tv.ui.screens.settings.telegram;

import com.arflix.tv.data.telegram.TelegramRepository;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class TelegramSettingsViewModel_Factory implements Factory<TelegramSettingsViewModel> {
    private final Provider<TelegramRepository> repositoryProvider;

    private TelegramSettingsViewModel_Factory(Provider<TelegramRepository> provider) {
        this.repositoryProvider = provider;
    }

    public static TelegramSettingsViewModel_Factory create(Provider<TelegramRepository> provider) {
        return new TelegramSettingsViewModel_Factory(provider);
    }

    public static TelegramSettingsViewModel newInstance(TelegramRepository telegramRepository) {
        return new TelegramSettingsViewModel(telegramRepository);
    }

    @Override // javax.inject.Provider
    public TelegramSettingsViewModel get() {
        return newInstance(this.repositoryProvider.get());
    }
}
