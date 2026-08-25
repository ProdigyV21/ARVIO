package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.data.model.IptvProgram;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class k implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7657i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ IptvRepository f7658l;

    public /* synthetic */ k(IptvRepository iptvRepository, int i10) {
        this.f7657i = i10;
        this.f7658l = iptvRepository;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7657i) {
            case 0:
                return IptvRepository.describeEpgChannels$lambda$0(this.f7658l, (IptvChannel) obj);
            case 1:
                return this.f7658l.programKey((IptvProgram) obj);
            case 2:
                return IptvRepository.scopedProviderGuideLookupChannels$lambda$0(this.f7658l, (IptvChannel) obj);
            case 3:
                return Boolean.valueOf(IptvRepository.writeCache$lambda$0$2(this.f7658l, (Map.Entry) obj));
            case 4:
                return this.f7658l.compactForCache((IptvProgram) obj);
            case 5:
                return IptvRepository.guideKeyCandidates$lambda$4(this.f7658l, (String) obj);
            case 6:
                return this.f7658l.programKey((IptvProgram) obj);
            case 7:
                return Boolean.valueOf(IptvRepository.extractTitleTokensFromNormalized$lambda$1(this.f7658l, (String) obj));
            default:
                return IptvRepository.parseXtreamListingsFromJson$lambda$1(this.f7658l, (Map.Entry) obj);
        }
    }
}
