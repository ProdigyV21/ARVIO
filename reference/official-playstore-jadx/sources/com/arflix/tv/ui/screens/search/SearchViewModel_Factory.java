package com.arflix.tv.ui.screens.search;

import com.arflix.tv.data.repository.MediaRepository;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class SearchViewModel_Factory implements Factory<SearchViewModel> {
    private final Provider<MediaRepository> mediaRepositoryProvider;

    private SearchViewModel_Factory(Provider<MediaRepository> provider) {
        this.mediaRepositoryProvider = provider;
    }

    public static SearchViewModel_Factory create(Provider<MediaRepository> provider) {
        return new SearchViewModel_Factory(provider);
    }

    public static SearchViewModel newInstance(MediaRepository mediaRepository) {
        return new SearchViewModel(mediaRepository);
    }

    @Override // javax.inject.Provider
    public SearchViewModel get() {
        return newInstance(this.mediaRepositoryProvider.get());
    }
}
