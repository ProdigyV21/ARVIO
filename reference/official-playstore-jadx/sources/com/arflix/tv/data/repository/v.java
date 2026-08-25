package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.IptvRepository;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class v implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7710i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ IptvRepository f7711l;

    public /* synthetic */ v(IptvRepository iptvRepository, int i10) {
        this.f7710i = i10;
        this.f7711l = iptvRepository;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f7710i) {
            case 0:
                return IptvRepository.IptvSeriesResolverService.prefs_delegate$lambda$0(this.f7711l);
            case 1:
                return IptvRepository.epgIndex_delegate$lambda$0(this.f7711l);
            case 2:
                return IptvRepository.iptvMovieSourcePrefs_delegate$lambda$0(this.f7711l);
            case 3:
                return IptvRepository.channelStore_delegate$lambda$0(this.f7711l);
            case 4:
                return IptvRepository.seriesResolver_delegate$lambda$0(this.f7711l);
            case 5:
                return IptvRepository.iptvHttpClient_delegate$lambda$0(this.f7711l);
            case 6:
                return IptvRepository.xtreamLookupHttpClient_delegate$lambda$0(this.f7711l);
            case 7:
                return IptvRepository.xtreamGuideHttpClient_delegate$lambda$0(this.f7711l);
            case 8:
                return IptvRepository.xtreamCatchupGuideHttpClient_delegate$lambda$0(this.f7711l);
            default:
                return IptvRepository.iptvCatalogHttpClient_delegate$lambda$0(this.f7711l);
        }
    }
}
