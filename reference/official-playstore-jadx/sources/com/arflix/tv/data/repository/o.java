package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.TmdbImage;
import com.arflix.tv.data.api.TmdbMediaItem;
import com.arflix.tv.data.api.TmdbTvSeason;
import com.arflix.tv.data.model.Addon;
import com.arflix.tv.data.model.AddonResource;
import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.data.model.MediaItem;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class o implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7680i;

    public /* synthetic */ o(int i10) {
        this.f7680i = i10;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7680i) {
            case 0:
                return Boolean.valueOf(IptvRepository.normalizeEpgInputs$lambda$2((String) obj));
            case 1:
                return ((IptvChannel) obj).getId();
            case 2:
                return IptvRepository.rememberDiscoveredEpgUrls$lambda$0((String) obj);
            case 3:
                return Boolean.valueOf(IptvRepository.rememberDiscoveredEpgUrls$lambda$1((String) obj));
            case 4:
                return IptvRepository.loadSnapshot$lambda$0((IptvLoadProgress) obj);
            case 5:
                return IptvRepository.fetchVisibleXmlEpgForChannels$lambda$0((IptvChannel) obj);
            case 6:
                return Boolean.valueOf(IptvRepository.fetchVisibleXmlEpgForChannels$lambda$1((String) obj));
            case 7:
                return ((IptvChannel) obj).getId();
            case 8:
                return Boolean.valueOf(IptvRepository.fetchVisibleXmlEpgForChannels$lambda$3((String) obj));
            case 9:
                return IptvRepository.buildConfigSignature$lambda$0$0((String) obj);
            case 10:
                return Boolean.valueOf(MediaRepository.searchPeopleKnownFor$lambda$0((TmdbMediaItem) obj));
            case 11:
                return Integer.valueOf(((TmdbMediaItem) obj).getId());
            case 12:
                return Boolean.valueOf(MediaRepository.searchPeopleKnownFor$lambda$3$0((TmdbMediaItem) obj));
            case 13:
                return MediaRepository.searchPeopleKnownFor$lambda$3$3((TmdbMediaItem) obj);
            case 14:
                return MediaRepository.searchPeopleKnownFor$lambda$3$4((MediaItem) obj);
            case 15:
                return Boolean.valueOf(MediaRepository.getLogoUrl$lambda$1((TmdbImage) obj));
            case 16:
                return Boolean.valueOf(MediaRepository.getLogoUrl$lambda$2((TmdbImage) obj));
            case 17:
                return Boolean.valueOf(MediaRepositoryKt.toMediaItem$lambda$13((TmdbTvSeason) obj));
            case 18:
                return Integer.valueOf(((TmdbTvSeason) obj).getSeasonNumber());
            case 19:
                return StreamRepository.sanitizeLogUrl$lambda$0$0((String) obj);
            case 20:
                return StreamRepository.pickBestM3uUrl$lambda$0((String) obj);
            case 21:
                return Boolean.valueOf(StreamRepository.pickBestM3uUrl$lambda$1((String) obj));
            case 22:
                return Boolean.valueOf(StreamRepository.pickBestM3uUrl$lambda$2((String) obj));
            case 23:
                return StreamRepository.buildAddonInstanceId$lambda$0(((Byte) obj).byteValue());
            case 24:
                return StreamRepositoryKt.streamAddonConfigurationFingerprint$lambda$0((Addon) obj);
            case 25:
                return Boolean.valueOf(StreamRepositoryKt.pickHubCloudDirectLink$lambda$0((String) obj));
            case 26:
                return Boolean.valueOf(StreamRepositoryKt.pickHubCloudDirectLink$lambda$1((String) obj));
            case 27:
                return Boolean.valueOf(StreamRepositoryKt.pickHubCloudDirectLink$lambda$2((String) obj));
            case 28:
                return Boolean.valueOf(StreamRepositoryKt.pickHubCloudDirectLink$lambda$3((String) obj));
            default:
                return StreamRepositoryKt.streamAddonConfigurationFingerprint$lambda$0$0((AddonResource) obj);
        }
    }
}
