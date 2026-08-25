package com.arflix.tv.data.api;

import java.util.List;
import kotlin.Metadata;
import zc.y;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0005\u001a\u00020\u00042\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\b\u001a\u00020\u00072\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@¢\u0006\u0004\b\b\u0010\u0006J\u001a\u0010\n\u001a\u00020\t2\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@¢\u0006\u0004\b\n\u0010\u0006J\u001a\u0010\f\u001a\u00020\u000b2\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@¢\u0006\u0004\b\f\u0010\u0006J\u001a\u0010\u000e\u001a\u00020\r2\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@¢\u0006\u0004\b\u000e\u0010\u0006J\u001a\u0010\u0010\u001a\u00020\u000f2\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@¢\u0006\u0004\b\u0010\u0010\u0006J\u001a\u0010\u0012\u001a\u00020\u00112\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@¢\u0006\u0004\b\u0012\u0010\u0006J\u001a\u0010\u0014\u001a\u00020\u00132\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@¢\u0006\u0004\b\u0014\u0010\u0006J\u001a\u0010\u0016\u001a\u00020\u00152\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@¢\u0006\u0004\b\u0016\u0010\u0006J \u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@¢\u0006\u0004\b\u0019\u0010\u0006J \u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@¢\u0006\u0004\b\u001a\u0010\u0006¨\u0006\u001bÀ\u0006\u0003"}, d2 = {"Lcom/arflix/tv/data/api/StreamApi;", "", "", "url", "Lcom/arflix/tv/data/api/StremioManifestResponse;", "getAddonManifest", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/api/StremioStreamResponse;", "getAddonStreams", "Lcom/arflix/tv/data/api/StremioCatalogResponse;", "getAddonCatalog", "Lcom/arflix/tv/data/api/StremioMetaResponse;", "getAddonMeta", "Lcom/arflix/tv/data/api/StremioSubtitleResponse;", "getSubtitles", "Lcom/arflix/tv/data/api/KitsuSearchResponse;", "searchKitsuAnime", "Lcom/arflix/tv/data/api/KitsuMappingResponse;", "getKitsuMappings", "Lcom/arflix/tv/data/api/KitsuAnimeDetailResponse;", "getKitsuAnimeDetail", "Lcom/arflix/tv/data/api/KitsuMediaRelationshipsResponse;", "getKitsuMediaRelationships", "", "Lcom/arflix/tv/data/api/ArmMappingEntry;", "getArmMappingByTmdb", "getArmMappingByImdb", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface StreamApi {
    @zc.f
    Object getAddonCatalog(@y String str, d7.d<? super StremioCatalogResponse> dVar);

    @zc.f
    Object getAddonManifest(@y String str, d7.d<? super StremioManifestResponse> dVar);

    @zc.f
    Object getAddonMeta(@y String str, d7.d<? super StremioMetaResponse> dVar);

    @zc.f
    Object getAddonStreams(@y String str, d7.d<? super StremioStreamResponse> dVar);

    @zc.f
    Object getArmMappingByImdb(@y String str, d7.d<? super List<ArmMappingEntry>> dVar);

    @zc.f
    Object getArmMappingByTmdb(@y String str, d7.d<? super List<ArmMappingEntry>> dVar);

    @zc.f
    Object getKitsuAnimeDetail(@y String str, d7.d<? super KitsuAnimeDetailResponse> dVar);

    @zc.f
    Object getKitsuMappings(@y String str, d7.d<? super KitsuMappingResponse> dVar);

    @zc.f
    Object getKitsuMediaRelationships(@y String str, d7.d<? super KitsuMediaRelationshipsResponse> dVar);

    @zc.f
    Object getSubtitles(@y String str, d7.d<? super StremioSubtitleResponse> dVar);

    @zc.f
    Object searchKitsuAnime(@y String str, d7.d<? super KitsuSearchResponse> dVar);
}
