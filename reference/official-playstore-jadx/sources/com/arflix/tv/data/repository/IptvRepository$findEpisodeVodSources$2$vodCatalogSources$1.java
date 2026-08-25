package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.StreamSource;
import com.arflix.tv.data.repository.IptvRepository;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/StreamSource;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.IptvRepository$findEpisodeVodSources$2$vodCatalogSources$1", f = "IptvRepository.kt", l = {4367}, m = "invokeSuspend", v = 2)
public final class IptvRepository$findEpisodeVodSources$2$vodCatalogSources$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends StreamSource>>, Object> {
    final /* synthetic */ IptvRepository.XtreamCredentials $creds;
    final /* synthetic */ int $episode;
    final /* synthetic */ String $normalizedImdb;
    final /* synthetic */ String $normalizedTmdb;
    final /* synthetic */ int $season;
    final /* synthetic */ String $title;
    int label;
    final /* synthetic */ IptvRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IptvRepository$findEpisodeVodSources$2$vodCatalogSources$1(IptvRepository iptvRepository, IptvRepository.XtreamCredentials xtreamCredentials, String str, int i10, int i11, String str2, String str3, d7.d<? super IptvRepository$findEpisodeVodSources$2$vodCatalogSources$1> dVar) {
        super(2, dVar);
        this.this$0 = iptvRepository;
        this.$creds = xtreamCredentials;
        this.$title = str;
        this.$season = i10;
        this.$episode = i11;
        this.$normalizedImdb = str2;
        this.$normalizedTmdb = str3;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new IptvRepository$findEpisodeVodSources$2$vodCatalogSources$1(this.this$0, this.$creds, this.$title, this.$season, this.$episode, this.$normalizedImdb, this.$normalizedTmdb, dVar);
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
        IptvRepository iptvRepository = this.this$0;
        IptvRepository.XtreamCredentials xtreamCredentials = this.$creds;
        String str = this.$title;
        int i11 = this.$season;
        int i12 = this.$episode;
        String str2 = this.$normalizedImdb;
        String str3 = this.$normalizedTmdb;
        this.label = 1;
        Object objFindEpisodeVodFromVodCatalogFallbackSources = iptvRepository.findEpisodeVodFromVodCatalogFallbackSources(xtreamCredentials, str, i11, i12, str2, str3, true, this);
        e7.a aVar = e7.a.f15033i;
        return objFindEpisodeVodFromVodCatalogFallbackSources == aVar ? aVar : objFindEpisodeVodFromVodCatalogFallbackSources;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<StreamSource>> dVar) {
        return ((IptvRepository$findEpisodeVodSources$2$vodCatalogSources$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
