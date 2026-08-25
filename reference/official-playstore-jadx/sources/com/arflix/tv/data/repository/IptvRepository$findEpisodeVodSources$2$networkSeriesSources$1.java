package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.StreamSource;
import com.arflix.tv.data.repository.IptvRepository;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/StreamSource;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.IptvRepository$findEpisodeVodSources$2$networkSeriesSources$1", f = "IptvRepository.kt", l = {4350}, m = "invokeSuspend", v = 2)
public final class IptvRepository$findEpisodeVodSources$2$networkSeriesSources$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends StreamSource>>, Object> {
    final /* synthetic */ IptvRepository.XtreamCredentials $creds;
    final /* synthetic */ int $episode;
    final /* synthetic */ String $imdbId;
    final /* synthetic */ String $providerKey;
    final /* synthetic */ int $season;
    final /* synthetic */ String $title;
    final /* synthetic */ Integer $tmdbId;
    int label;
    final /* synthetic */ IptvRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IptvRepository$findEpisodeVodSources$2$networkSeriesSources$1(IptvRepository iptvRepository, String str, IptvRepository.XtreamCredentials xtreamCredentials, String str2, int i10, int i11, Integer num, String str3, d7.d<? super IptvRepository$findEpisodeVodSources$2$networkSeriesSources$1> dVar) {
        super(2, dVar);
        this.this$0 = iptvRepository;
        this.$providerKey = str;
        this.$creds = xtreamCredentials;
        this.$title = str2;
        this.$season = i10;
        this.$episode = i11;
        this.$tmdbId = num;
        this.$imdbId = str3;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new IptvRepository$findEpisodeVodSources$2$networkSeriesSources$1(this.this$0, this.$providerKey, this.$creds, this.$title, this.$season, this.$episode, this.$tmdbId, this.$imdbId, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        IptvRepository$findEpisodeVodSources$2$networkSeriesSources$1 iptvRepository$findEpisodeVodSources$2$networkSeriesSources$1;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            IptvRepository.IptvSeriesResolverService seriesResolver = this.this$0.getSeriesResolver();
            String str = this.$providerKey;
            IptvRepository.XtreamCredentials xtreamCredentials = this.$creds;
            String str2 = this.$title;
            int i11 = this.$season;
            int i12 = this.$episode;
            Integer num = this.$tmdbId;
            String str3 = this.$imdbId;
            Integer year = this.this$0.parseYear(str2);
            this.label = 1;
            iptvRepository$findEpisodeVodSources$2$networkSeriesSources$1 = this;
            obj = seriesResolver.resolveEpisodeVariants(str, xtreamCredentials, str2, i11, i12, num, str3, year, true, iptvRepository$findEpisodeVodSources$2$networkSeriesSources$1);
            e7.a aVar = e7.a.f15033i;
            if (obj == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            iptvRepository$findEpisodeVodSources$2$networkSeriesSources$1 = this;
        }
        return IptvRepository.C08022.invokeSuspend$toSeriesVodSources((List) obj, iptvRepository$findEpisodeVodSources$2$networkSeriesSources$1.$creds, iptvRepository$findEpisodeVodSources$2$networkSeriesSources$1.this$0, iptvRepository$findEpisodeVodSources$2$networkSeriesSources$1.$title, iptvRepository$findEpisodeVodSources$2$networkSeriesSources$1.$season, iptvRepository$findEpisodeVodSources$2$networkSeriesSources$1.$episode);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<StreamSource>> dVar) {
        return ((IptvRepository$findEpisodeVodSources$2$networkSeriesSources$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
