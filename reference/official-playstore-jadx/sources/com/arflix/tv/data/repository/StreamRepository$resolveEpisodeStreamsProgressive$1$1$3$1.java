package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.Addon;
import com.arflix.tv.data.model.StreamSource;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.StreamRepository$resolveEpisodeStreamsProgressive$1$1$3$1", f = "StreamRepository.kt", l = {2797, 4385, 2829}, m = "invokeSuspend", v = 2)
public final class StreamRepository$resolveEpisodeStreamsProgressive$1$1$3$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ ma.u<ProgressiveStreamResult> $$this$callbackFlow;
    final /* synthetic */ Addon $addon;
    final /* synthetic */ List<StreamSource> $aggregatedStreams;
    final /* synthetic */ String $airDate;
    final /* synthetic */ String $animeQueryOverride;
    final /* synthetic */ String $cacheKey;
    final /* synthetic */ kotlin.jvm.internal.i0 $completed;
    final /* synthetic */ int $episode;
    final /* synthetic */ List<Integer> $genreIds;
    final /* synthetic */ String $imdbId;
    final /* synthetic */ ua.a $mutex;
    final /* synthetic */ String $originalLanguage;
    final /* synthetic */ int $season;
    final /* synthetic */ String $title;
    final /* synthetic */ Integer $tmdbId;
    final /* synthetic */ int $totalAddons;
    final /* synthetic */ Integer $tvdbId;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    final /* synthetic */ StreamRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public StreamRepository$resolveEpisodeStreamsProgressive$1$1$3$1(StreamRepository streamRepository, Addon addon, String str, int i10, int i11, Integer num, Integer num2, List<Integer> list, String str2, String str3, String str4, String str5, ua.a aVar, List<StreamSource> list2, kotlin.jvm.internal.i0 i0Var, int i12, ma.u<? super ProgressiveStreamResult> uVar, String str6, d7.d<? super StreamRepository$resolveEpisodeStreamsProgressive$1$1$3$1> dVar) {
        super(2, dVar);
        this.this$0 = streamRepository;
        this.$addon = addon;
        this.$imdbId = str;
        this.$season = i10;
        this.$episode = i11;
        this.$tmdbId = num;
        this.$tvdbId = num2;
        this.$genreIds = list;
        this.$originalLanguage = str2;
        this.$title = str3;
        this.$animeQueryOverride = str4;
        this.$airDate = str5;
        this.$mutex = aVar;
        this.$aggregatedStreams = list2;
        this.$completed = i0Var;
        this.$totalAddons = i12;
        this.$$this$callbackFlow = uVar;
        this.$cacheKey = str6;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new StreamRepository$resolveEpisodeStreamsProgressive$1$1$3$1(this.this$0, this.$addon, this.$imdbId, this.$season, this.$episode, this.$tmdbId, this.$tvdbId, this.$genreIds, this.$originalLanguage, this.$title, this.$animeQueryOverride, this.$airDate, this.$mutex, this.$aggregatedStreams, this.$completed, this.$totalAddons, this.$$this$callbackFlow, this.$cacheKey, dVar);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:0|2|78|(1:(1:(1:(6:7|74|8|9|67|68)(2:12|13))(5:14|89|63|(4:66|9|67|68)|65))(2:15|16))(18:19|20|21|87|22|23|76|24|25|85|26|27|80|28|29|82|30|31)|84|34|35|59|(4:62|89|63|(0))|65|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00af, code lost:
    
        if (r0 == r13) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00b8, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01d4  */
    /* JADX WARN: Type inference failed for: r13v0 */
    /* JADX WARN: Type inference failed for: r13v1 */
    /* JADX WARN: Type inference failed for: r13v10 */
    /* JADX WARN: Type inference failed for: r13v11 */
    /* JADX WARN: Type inference failed for: r13v12 */
    /* JADX WARN: Type inference failed for: r13v13 */
    /* JADX WARN: Type inference failed for: r13v14 */
    /* JADX WARN: Type inference failed for: r13v15 */
    /* JADX WARN: Type inference failed for: r13v16 */
    /* JADX WARN: Type inference failed for: r13v17 */
    /* JADX WARN: Type inference failed for: r13v18 */
    /* JADX WARN: Type inference failed for: r13v19 */
    /* JADX WARN: Type inference failed for: r13v2 */
    /* JADX WARN: Type inference failed for: r13v20 */
    /* JADX WARN: Type inference failed for: r13v21 */
    /* JADX WARN: Type inference failed for: r13v3 */
    /* JADX WARN: Type inference failed for: r13v4 */
    /* JADX WARN: Type inference failed for: r13v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r13v7 */
    /* JADX WARN: Type inference failed for: r13v8 */
    /* JADX WARN: Type inference failed for: r13v9 */
    /* JADX WARN: Type inference failed for: r16v0 */
    /* JADX WARN: Type inference failed for: r16v1 */
    /* JADX WARN: Type inference failed for: r16v10 */
    /* JADX WARN: Type inference failed for: r16v11 */
    /* JADX WARN: Type inference failed for: r16v2 */
    /* JADX WARN: Type inference failed for: r16v3 */
    /* JADX WARN: Type inference failed for: r16v4 */
    /* JADX WARN: Type inference failed for: r16v6 */
    /* JADX WARN: Type inference failed for: r16v8 */
    /* JADX WARN: Type inference failed for: r16v9 */
    /* JADX WARN: Type inference failed for: r17v0 */
    /* JADX WARN: Type inference failed for: r17v1 */
    /* JADX WARN: Type inference failed for: r17v10 */
    /* JADX WARN: Type inference failed for: r17v11 */
    /* JADX WARN: Type inference failed for: r17v12 */
    /* JADX WARN: Type inference failed for: r17v13 */
    /* JADX WARN: Type inference failed for: r17v14 */
    /* JADX WARN: Type inference failed for: r17v15 */
    /* JADX WARN: Type inference failed for: r17v16 */
    /* JADX WARN: Type inference failed for: r17v2 */
    /* JADX WARN: Type inference failed for: r17v3 */
    /* JADX WARN: Type inference failed for: r17v4 */
    /* JADX WARN: Type inference failed for: r17v5 */
    /* JADX WARN: Type inference failed for: r17v6 */
    /* JADX WARN: Type inference failed for: r17v8 */
    /* JADX WARN: Type inference failed for: r17v9 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r3v0, types: [e7.a] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11, types: [boolean] */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7, types: [boolean] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r20) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 469
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.StreamRepository$resolveEpisodeStreamsProgressive$1$1$3$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((StreamRepository$resolveEpisodeStreamsProgressive$1$1$3$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
