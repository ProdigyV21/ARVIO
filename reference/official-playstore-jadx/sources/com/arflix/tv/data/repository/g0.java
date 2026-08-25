package com.arflix.tv.data.repository;

import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class g0 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7643i;

    public /* synthetic */ g0(int i10) {
        this.f7643i = i10;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f7643i) {
            case 0:
                return t0.f22605a;
            case 1:
                return t0.f22605a;
            case 2:
                return CatalogRepository.bundledPreinstalledCatalogsById_delegate$lambda$0();
            case 3:
                return IptvRepository.vodCategoriesDiskCacheType_delegate$lambda$0();
            case 4:
                return IptvRepository.seriesCategoriesDiskCacheType_delegate$lambda$0();
            case 5:
                return IptvRepository.vodDiskCacheType_delegate$lambda$0();
            case 6:
                return IptvRepository.seriesDiskCacheType_delegate$lambda$0();
            default:
                return RealtimeSyncManager.wsClient_delegate$lambda$0();
        }
    }
}
