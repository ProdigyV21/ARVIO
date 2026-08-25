package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.TraktListSearchResult;
import org.jsoup.nodes.Element;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class b implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7621i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ CatalogDiscoveryRepository f7622l;

    public /* synthetic */ b(CatalogDiscoveryRepository catalogDiscoveryRepository, int i10) {
        this.f7621i = i10;
        this.f7622l = catalogDiscoveryRepository;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7621i) {
            case 0:
                return CatalogDiscoveryRepository.searchTraktLists$lambda$0(this.f7622l, (TraktListSearchResult) obj);
            case 1:
                return this.f7622l.normalizePosterUrl((String) obj);
            case 2:
                return this.f7622l.normalizePosterUrl((String) obj);
            default:
                return CatalogDiscoveryRepository.searchMdblistLists$lambda$1(this.f7622l, (Element) obj);
        }
    }
}
