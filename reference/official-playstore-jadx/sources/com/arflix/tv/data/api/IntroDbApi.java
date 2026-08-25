package com.arflix.tv.data.api;

import kotlin.Metadata;
import zc.t;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J.\u0010\b\u001a\u00020\u00072\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u00042\b\b\u0001\u0010\u0006\u001a\u00020\u0004H§@¢\u0006\u0004\b\b\u0010\t¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lcom/arflix/tv/data/api/IntroDbApi;", "", "", "imdbId", "", "season", "episode", "Lcom/arflix/tv/data/api/IntroDbSegmentsResponse;", "getSegments", "(Ljava/lang/String;IILd7/d;)Ljava/lang/Object;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface IntroDbApi {
    @zc.f("segments")
    Object getSegments(@t("imdb_id") String str, @t("season") int i10, @t("episode") int i11, d7.d<? super IntroDbSegmentsResponse> dVar);
}
