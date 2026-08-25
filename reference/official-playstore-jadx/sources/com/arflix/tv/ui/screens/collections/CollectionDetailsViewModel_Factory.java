package com.arflix.tv.ui.screens.collections;

import com.arflix.tv.data.repository.CatalogRepository;
import com.arflix.tv.data.repository.MediaRepository;
import com.arflix.tv.data.repository.SportsRepository;
import dagger.internal.Factory;
import dagger.internal.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class CollectionDetailsViewModel_Factory implements Factory<CollectionDetailsViewModel> {
    private final Provider<CatalogRepository> catalogRepositoryProvider;
    private final Provider<MediaRepository> mediaRepositoryProvider;
    private final Provider<SportsRepository> sportsRepositoryProvider;

    private CollectionDetailsViewModel_Factory(Provider<CatalogRepository> provider, Provider<MediaRepository> provider2, Provider<SportsRepository> provider3) {
        this.catalogRepositoryProvider = provider;
        this.mediaRepositoryProvider = provider2;
        this.sportsRepositoryProvider = provider3;
    }

    public static CollectionDetailsViewModel_Factory create(Provider<CatalogRepository> provider, Provider<MediaRepository> provider2, Provider<SportsRepository> provider3) {
        return new CollectionDetailsViewModel_Factory(provider, provider2, provider3);
    }

    public static CollectionDetailsViewModel newInstance(CatalogRepository catalogRepository, MediaRepository mediaRepository, SportsRepository sportsRepository) {
        return new CollectionDetailsViewModel(catalogRepository, mediaRepository, sportsRepository);
    }

    @Override // javax.inject.Provider
    public CollectionDetailsViewModel get() {
        return newInstance(this.catalogRepositoryProvider.get(), this.mediaRepositoryProvider.get(), this.sportsRepositoryProvider.get());
    }
}
