package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.StreamSource;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/StreamSource;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$resolveMovieSources$2$1$1$1$1", f = "HomeServerRepository.kt", l = {654, 655}, m = "invokeSuspend", v = 2)
public final class HomeServerRepository$resolveMovieSources$2$1$1$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends StreamSource>>, Object> {
    final /* synthetic */ HomeServerConnection $connection;
    final /* synthetic */ String $imdbId;
    final /* synthetic */ String $title;
    final /* synthetic */ Integer $tmdbId;
    final /* synthetic */ Integer $year;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ HomeServerRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeServerRepository$resolveMovieSources$2$1$1$1$1(HomeServerRepository homeServerRepository, HomeServerConnection homeServerConnection, String str, String str2, Integer num, Integer num2, d7.d<? super HomeServerRepository$resolveMovieSources$2$1$1$1$1> dVar) {
        super(2, dVar);
        this.this$0 = homeServerRepository;
        this.$connection = homeServerConnection;
        this.$imdbId = str;
        this.$title = str2;
        this.$year = num;
        this.$tmdbId = num2;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        HomeServerRepository$resolveMovieSources$2$1$1$1$1 homeServerRepository$resolveMovieSources$2$1$1$1$1 = new HomeServerRepository$resolveMovieSources$2$1$1$1$1(this.this$0, this.$connection, this.$imdbId, this.$title, this.$year, this.$tmdbId, dVar);
        homeServerRepository$resolveMovieSources$2$1$1$1$1.L$0 = obj;
        return homeServerRepository$resolveMovieSources$2$1$1$1$1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0079, code lost:
    
        if (r13 == r4) goto L25;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.Object r0 = r12.L$0
            ka.k0 r0 = (ka.k0) r0
            int r0 = r12.label
            r1 = 2
            r2 = 1
            r3 = 0
            e7.a r4 = e7.a.f15033i
            if (r0 == 0) goto L3d
            if (r0 == r2) goto L2a
            if (r0 != r1) goto L22
            java.lang.Object r0 = r12.L$2
            java.util.List r0 = (java.util.List) r0
            java.lang.Object r0 = r12.L$1
            ka.k0 r0 = (ka.k0) r0
            k2.c.G(r13)     // Catch: java.lang.Throwable -> L1e
            r11 = r12
            goto L7c
        L1e:
            r0 = move-exception
            r13 = r0
            r11 = r12
            goto L85
        L22:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L2a:
            int r0 = r12.I$0
            java.lang.Object r2 = r12.L$3
            ka.k0 r2 = (ka.k0) r2
            java.lang.Object r2 = r12.L$2
            com.arflix.tv.data.repository.HomeServerConnection r2 = (com.arflix.tv.data.repository.HomeServerConnection) r2
            java.lang.Object r5 = r12.L$1
            com.arflix.tv.data.repository.HomeServerRepository r5 = (com.arflix.tv.data.repository.HomeServerRepository) r5
            k2.c.G(r13)     // Catch: java.lang.Throwable -> L1e
            r11 = r12
            goto L62
        L3d:
            k2.c.G(r13)
            com.arflix.tv.data.repository.HomeServerRepository r5 = r12.this$0
            com.arflix.tv.data.repository.HomeServerConnection r6 = r12.$connection
            java.lang.String r7 = r12.$imdbId
            java.lang.String r8 = r12.$title
            java.lang.Integer r9 = r12.$year
            java.lang.Integer r10 = r12.$tmdbId
            r12.L$0 = r3     // Catch: java.lang.Throwable -> L82
            r12.L$1 = r5     // Catch: java.lang.Throwable -> L82
            r12.L$2 = r6     // Catch: java.lang.Throwable -> L82
            r12.L$3 = r3     // Catch: java.lang.Throwable -> L82
            r0 = 0
            r12.I$0 = r0     // Catch: java.lang.Throwable -> L82
            r12.label = r2     // Catch: java.lang.Throwable -> L82
            r11 = r12
            java.lang.Object r13 = com.arflix.tv.data.repository.HomeServerRepository.access$findMovieMatches(r5, r6, r7, r8, r9, r10, r11)     // Catch: java.lang.Throwable -> L7f
            if (r13 != r4) goto L61
            goto L7b
        L61:
            r2 = r6
        L62:
            java.util.List r13 = (java.util.List) r13     // Catch: java.lang.Throwable -> L7f
            com.arflix.tv.data.repository.HomeServerRepository$resolveMovieSources$2$1$1$1$1$1$1 r6 = new com.arflix.tv.data.repository.HomeServerRepository$resolveMovieSources$2$1$1$1$1$1$1     // Catch: java.lang.Throwable -> L7f
            r6.<init>(r13, r5, r2, r3)     // Catch: java.lang.Throwable -> L7f
            r11.L$0 = r3     // Catch: java.lang.Throwable -> L7f
            r11.L$1 = r3     // Catch: java.lang.Throwable -> L7f
            r11.L$2 = r3     // Catch: java.lang.Throwable -> L7f
            r11.L$3 = r3     // Catch: java.lang.Throwable -> L7f
            r11.I$0 = r0     // Catch: java.lang.Throwable -> L7f
            r11.label = r1     // Catch: java.lang.Throwable -> L7f
            java.lang.Object r13 = ka.l0.c(r6, r12)     // Catch: java.lang.Throwable -> L7f
            if (r13 != r4) goto L7c
        L7b:
            return r4
        L7c:
            java.util.List r13 = (java.util.List) r13     // Catch: java.lang.Throwable -> L7f
            goto L8b
        L7f:
            r0 = move-exception
        L80:
            r13 = r0
            goto L85
        L82:
            r0 = move-exception
            r11 = r12
            goto L80
        L85:
            x6.c0 r0 = new x6.c0
            r0.<init>(r13)
            r13 = r0
        L8b:
            boolean r0 = r13 instanceof x6.c0
            if (r0 == 0) goto L92
            kotlin.collections.z r13 = kotlin.collections.z.f19728i
        L92:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository$resolveMovieSources$2$1$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<StreamSource>> dVar) {
        return ((HomeServerRepository$resolveMovieSources$2$1$1$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
