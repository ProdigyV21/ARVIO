package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.StremioMetaPreview;
import com.arflix.tv.data.model.Addon;
import com.arflix.tv.data.model.CatalogConfig;
import com.arflix.tv.data.model.CatalogDiscoveryResult;
import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.data.model.IptvProgram;
import com.arflix.tv.data.model.StreamSource;
import com.arflix.tv.data.repository.HomeServerRepository;
import com.arflix.tv.data.repository.IptvRepository;
import com.arflix.tv.data.repository.SportsRepository$loadSportsCategoryArtworkOverrides$2$1$1;
import com.arflix.tv.data.repository.StreamRepository;
import org.jsoup.nodes.Element;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class f implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7639i;

    public /* synthetic */ f(int i10) {
        this.f7639i = i10;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7639i) {
            case 0:
                return Boolean.valueOf(HomeServerRepository.parsePlexResourcesXml$lambda$1((HomeServerRepository.PlexResourceDevice) obj));
            case 1:
                return Boolean.valueOf(HomeServerRepository.parsePlexResourcesXml$lambda$0$2((HomeServerRepository.PlexResourceConnection) obj));
            case 2:
                return HomeServerRepository.parsePlexResourcesXml$lambda$0$3((HomeServerRepository.PlexResourceConnection) obj);
            case 3:
                return Integer.valueOf(IptvRepository.findMovieCandidatesIndexed$lambda$6((IptvRepository.XtreamVodStream) obj));
            case 4:
                return IptvRepository$fetchFreshChannelsForStartup$channels$1$1$1.invokeSuspend$lambda$0((IptvLoadProgress) obj);
            case 5:
                return ((IptvChannel) obj).getId();
            case 6:
                return ((IptvChannel) obj).getId();
            case 7:
                return ((IptvChannel) obj).getId();
            case 8:
                return SportsRepository$loadSportsCategoryArtworkOverrides$2$1$1.AnonymousClass1.invokeSuspend$lambda$0((StremioMetaPreview) obj);
            case 9:
                return ((EpisodeIdCandidate) obj).getLabel();
            case 10:
                return Boolean.valueOf(StreamRepository.C10092.invokeSuspend$lambda$0((StreamSource) obj));
            case 11:
                return Boolean.valueOf(StreamRepository$resolveHubCloudChain$2$1$1.invokeSuspend$lambda$4((String) obj));
            case 12:
                return Boolean.valueOf(StreamRepository$resolveHubCloudChain$2$1$1.invokeSuspend$lambda$5((String) obj));
            case 13:
                return CatalogDiscoveryRepository.searchTraktLists$lambda$1((CatalogDiscoveryResult) obj);
            case 14:
                return Boolean.valueOf(CatalogDiscoveryRepository.searchTraktLists$lambda$0$6((String) obj));
            case 15:
                return CatalogDiscoveryRepository.searchMdblistLists$lambda$1$8((Element) obj);
            case 16:
                return Boolean.valueOf(CatalogDiscoveryRepository.searchMdblistLists$lambda$1$10((String) obj));
            case 17:
                return CatalogDiscoveryRepository.searchMdblistLists$lambda$2((CatalogDiscoveryResult) obj);
            case 18:
                return ((CatalogConfig) obj).getId();
            case 19:
                return CatalogRepository.sha256Short$lambda$0(((Byte) obj).byteValue());
            case 20:
                return Boolean.valueOf(CatalogRepository.syncAddonCatalogs$lambda$1((Addon) obj));
            case 21:
                return CatalogRepositoryKt.toDisplayTitle$lambda$1((String) obj);
            case 22:
                return DataStoreSessionManager._init_$lambda$0((db.g) obj);
            case 23:
                return ((HomeServerCollection) obj).getId();
            case 24:
                return HomeServerRepository.Companion.buildCatalogSourceRef$lambda$0((String) obj);
            case 25:
                return HttpLocalScraperRuntime.shortHash$lambda$0(((Byte) obj).byteValue());
            case 26:
                return Boolean.valueOf(IptvChannelStore.getByIds$lambda$0((String) obj));
            case 27:
                return IptvChannelStore.getByIds$lambda$1$0((String) obj);
            case 28:
                return Boolean.valueOf(IptvEpgIndex.replaceChannels$lambda$0$0((String) obj));
            default:
                return IptvEpgIndex.loadWindow$lambda$1$0$1((IptvProgram) obj);
        }
    }
}
