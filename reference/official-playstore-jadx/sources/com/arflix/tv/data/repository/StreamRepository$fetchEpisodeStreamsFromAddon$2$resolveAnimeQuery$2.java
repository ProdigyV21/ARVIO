package com.arflix.tv.data.repository;

import com.arflix.tv.util.AnimeMapper;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/String;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.StreamRepository$fetchEpisodeStreamsFromAddon$2$resolveAnimeQuery$2", f = "StreamRepository.kt", l = {1904}, m = "invokeSuspend", v = 2)
public final class StreamRepository$fetchEpisodeStreamsFromAddon$2$resolveAnimeQuery$2 extends f7.j implements r7.p<ka.k0, d7.d<? super String>, Object> {
    final /* synthetic */ int $episode;
    final /* synthetic */ String $imdbId;
    final /* synthetic */ int $season;
    final /* synthetic */ String $title;
    final /* synthetic */ Integer $tmdbId;
    final /* synthetic */ Integer $tvdbId;
    int label;
    final /* synthetic */ StreamRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StreamRepository$fetchEpisodeStreamsFromAddon$2$resolveAnimeQuery$2(StreamRepository streamRepository, Integer num, Integer num2, String str, String str2, int i10, int i11, d7.d<? super StreamRepository$fetchEpisodeStreamsFromAddon$2$resolveAnimeQuery$2> dVar) {
        super(2, dVar);
        this.this$0 = streamRepository;
        this.$tmdbId = num;
        this.$tvdbId = num2;
        this.$title = str;
        this.$imdbId = str2;
        this.$season = i10;
        this.$episode = i11;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new StreamRepository$fetchEpisodeStreamsFromAddon$2$resolveAnimeQuery$2(this.this$0, this.$tmdbId, this.$tvdbId, this.$title, this.$imdbId, this.$season, this.$episode, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return obj;
        }
        k2.c.G(obj);
        AnimeMapper animeMapper = this.this$0.animeMapper;
        Integer num = this.$tmdbId;
        Integer num2 = this.$tvdbId;
        String str = this.$title;
        String str2 = this.$imdbId;
        Integer num3 = new Integer(this.$season);
        Integer num4 = new Integer(this.$episode);
        this.label = 1;
        Object objResolveAnimeEpisodeQuery = animeMapper.resolveAnimeEpisodeQuery(num, num2, str, str2, num3, num4, this);
        e7.a aVar = e7.a.f15033i;
        return objResolveAnimeEpisodeQuery == aVar ? aVar : objResolveAnimeEpisodeQuery;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super String> dVar) {
        return ((StreamRepository$fetchEpisodeStreamsFromAddon$2$resolveAnimeQuery$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
