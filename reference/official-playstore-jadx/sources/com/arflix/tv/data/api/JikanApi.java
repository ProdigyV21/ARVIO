package com.arflix.tv.data.api;

import kotlin.Metadata;
import zc.s;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0005\u001a\u00020\u00042\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lcom/arflix/tv/data/api/JikanApi;", "", "", "malId", "Lcom/arflix/tv/data/api/JikanAnimeResponse;", "getAnime", "(ILd7/d;)Ljava/lang/Object;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface JikanApi {
    @zc.f("anime/{malId}")
    Object getAnime(@s("malId") int i10, d7.d<? super JikanAnimeResponse> dVar);
}
