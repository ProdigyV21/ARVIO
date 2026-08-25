package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.Addon;
import com.arflix.tv.data.model.RuntimeKind;
import com.arflix.tv.data.model.StreamSource;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J,\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u0005H¦@¢\u0006\u0004\b\b\u0010\tJ,\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0006\u001a\u00020\nH¦@¢\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u0010\u001a\u00020\r8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0011À\u0006\u0003"}, d2 = {"Lcom/arflix/tv/data/repository/AddonRuntime;", "", "", "Lcom/arflix/tv/data/model/Addon;", "addons", "Lcom/arflix/tv/data/repository/MovieRuntimeRequest;", "request", "Lcom/arflix/tv/data/model/StreamSource;", "resolveMovieStreams", "(Ljava/util/List;Lcom/arflix/tv/data/repository/MovieRuntimeRequest;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/repository/EpisodeRuntimeRequest;", "resolveEpisodeStreams", "(Ljava/util/List;Lcom/arflix/tv/data/repository/EpisodeRuntimeRequest;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/model/RuntimeKind;", "getKind", "()Lcom/arflix/tv/data/model/RuntimeKind;", "kind", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface AddonRuntime {
    RuntimeKind getKind();

    Object resolveEpisodeStreams(List<Addon> list, EpisodeRuntimeRequest episodeRuntimeRequest, d7.d<? super List<StreamSource>> dVar);

    Object resolveMovieStreams(List<Addon> list, MovieRuntimeRequest movieRuntimeRequest, d7.d<? super List<StreamSource>> dVar);
}
