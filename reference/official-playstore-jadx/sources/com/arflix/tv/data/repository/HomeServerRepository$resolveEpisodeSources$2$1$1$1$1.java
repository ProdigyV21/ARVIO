package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.StreamSource;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/StreamSource;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$resolveEpisodeSources$2$1$1$1$1", f = "HomeServerRepository.kt", l = {695, 711}, m = "invokeSuspend", v = 2)
public final class HomeServerRepository$resolveEpisodeSources$2$1$1$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends StreamSource>>, Object> {
    final /* synthetic */ HomeServerConnection $connection;
    final /* synthetic */ int $episode;
    final /* synthetic */ String $imdbId;
    final /* synthetic */ int $season;
    final /* synthetic */ String $title;
    final /* synthetic */ Integer $tmdbId;
    final /* synthetic */ Integer $tvdbId;
    int I$0;
    int I$1;
    int I$2;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    int label;
    final /* synthetic */ HomeServerRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeServerRepository$resolveEpisodeSources$2$1$1$1$1(HomeServerRepository homeServerRepository, HomeServerConnection homeServerConnection, String str, String str2, Integer num, Integer num2, int i10, int i11, d7.d<? super HomeServerRepository$resolveEpisodeSources$2$1$1$1$1> dVar) {
        super(2, dVar);
        this.this$0 = homeServerRepository;
        this.$connection = homeServerConnection;
        this.$imdbId = str;
        this.$title = str2;
        this.$tmdbId = num;
        this.$tvdbId = num2;
        this.$season = i10;
        this.$episode = i11;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        HomeServerRepository$resolveEpisodeSources$2$1$1$1$1 homeServerRepository$resolveEpisodeSources$2$1$1$1$1 = new HomeServerRepository$resolveEpisodeSources$2$1$1$1$1(this.this$0, this.$connection, this.$imdbId, this.$title, this.$tmdbId, this.$tvdbId, this.$season, this.$episode, dVar);
        homeServerRepository$resolveEpisodeSources$2$1$1$1$1.L$0 = obj;
        return homeServerRepository$resolveEpisodeSources$2$1$1$1$1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x00e5, code lost:
    
        if (r0 == r11) goto L29;
     */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r21) {
        /*
            Method dump skipped, instruction units count: 249
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository$resolveEpisodeSources$2$1$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<StreamSource>> dVar) {
        return ((HomeServerRepository$resolveEpisodeSources$2$1$1$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
