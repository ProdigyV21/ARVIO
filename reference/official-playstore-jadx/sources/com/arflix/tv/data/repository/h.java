package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.IptvProgram;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class h implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7644i;

    public /* synthetic */ h(int i10) {
        this.f7644i = i10;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7644i) {
            case 0:
                return IptvEpgIndex.replaceChannels$lambda$0$1$0((String) obj);
            case 1:
                return Boolean.valueOf(IptvEpgIndex.buildNowNext$lambda$0((IptvProgram) obj));
            case 2:
                return IptvEpgIndex.buildNowNext$lambda$1((IptvProgram) obj);
            case 3:
                return Boolean.valueOf(IptvEpgIndex.useQueryChunks$lambda$0((String) obj));
            case 4:
                return IptvEpgIndex.useQueryChunks$lambda$1$0((String) obj);
            case 5:
                return Boolean.valueOf(IptvEpgIndex.loadWindow$lambda$1$0$0((IptvProgram) obj));
            case 6:
                return IptvProviderOrderKt.orderXtreamChannelsByProviderCategories$lambda$1((String) obj);
            case 7:
                return Boolean.valueOf(IptvProviderOrderKt.orderXtreamChannelsByProviderCategories$lambda$2((String) obj));
            case 8:
                return Boolean.valueOf(IptvProviderOrderKt.changedPlaylistSourceIds$lambda$2((String) obj));
            case 9:
                return IptvRepository.getCatchupUrlCandidates$lambda$8((String) obj);
            case 10:
                return Boolean.valueOf(IptvRepository.getCatchupUrlCandidates$lambda$9((String) obj));
            case 11:
                return IptvRepository.readDedicatedChannelCache$lambda$0$1((String) obj);
            case 12:
                return Boolean.valueOf(IptvRepository.readDedicatedChannelCache$lambda$0$2((String) obj));
            case 13:
                return Boolean.valueOf(IptvRepository.mergeCachedGuideSlice$lambda$0((IptvProgram) obj));
            case 14:
                return IptvRepository.extractXtreamTriplet$lambda$0((String) obj);
            case 15:
                return Boolean.valueOf(IptvRepository.extractXtreamTriplet$lambda$1((String) obj));
            case 16:
                return IptvRepository.extractXtreamTriplet$lambda$2((String) obj);
            case 17:
                return Boolean.valueOf(IptvRepository.extractXtreamTriplet$lambda$3((String) obj));
            case 18:
                return Boolean.valueOf(IptvRepository.indexedGuideWindow$lambda$0$0$0$0((IptvProgram) obj));
            case 19:
                return IptvRepository.indexedGuideWindow$lambda$0$0$0$1((IptvProgram) obj);
            case 20:
                return IptvRepository.buildConfigSignature$lambda$0((IptvPlaylistEntry) obj);
            case 21:
                return Boolean.valueOf(IptvRepository.mergeCachedPrograms$lambda$0((IptvProgram) obj));
            case 22:
                return IptvRepository.buildSourceSignature$lambda$0((IptvPlaylistEntry) obj);
            case 23:
                return IptvRepository.writeCache$lambda$0$4((String) obj);
            case 24:
                return Boolean.valueOf(IptvRepository.writeCache$lambda$0$5((String) obj));
            case 25:
                return IptvRepository.guideKeyCandidates$lambda$2((String) obj);
            case 26:
                return Boolean.valueOf(IptvRepository.guideKeyCandidates$lambda$3((String) obj));
            case 27:
                return Boolean.valueOf(IptvRepository.guideKeyCandidates$lambda$5((String) obj));
            case 28:
                return IptvRepository.extractTitleTokensFromNormalized$lambda$0((String) obj);
            default:
                return IptvRepository.normalizeEpgInputs$lambda$1((kotlin.text.i) obj);
        }
    }
}
