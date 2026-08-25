package com.arflix.tv.data.api;

import java.util.List;
import kotlin.Metadata;
import zc.s;
import zc.t;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J>\u0010\n\u001a\u00020\t2\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u00042\u000e\b\u0001\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u00062\b\b\u0003\u0010\b\u001a\u00020\u0004H§@¢\u0006\u0004\b\n\u0010\u000b¨\u0006\fÀ\u0006\u0003"}, d2 = {"Lcom/arflix/tv/data/api/AniSkipApi;", "", "", "malId", "", "episode", "", "types", "episodeLength", "Lcom/arflix/tv/data/api/AniSkipResponse;", "getSkipTimes", "(Ljava/lang/String;ILjava/util/List;ILd7/d;)Ljava/lang/Object;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface AniSkipApi {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    @zc.f("skip-times/{malId}/{episode}")
    Object getSkipTimes(@s("malId") String str, @s("episode") int i10, @t("types") List<String> list, @t("episodeLength") int i11, d7.d<? super AniSkipResponse> dVar);
}
