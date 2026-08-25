package com.arflix.tv.data.repository;

import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "Lx6/x;", "", "", "<anonymous>", "(Lka/k0;)Lx6/x;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.MediaRepository$resolveEpisodeImdbIds$2$1$1", f = "MediaRepository.kt", l = {3971, 470}, m = "invokeSuspend", v = 2)
public final class MediaRepository$resolveEpisodeImdbIds$2$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.x>, Object> {
    final /* synthetic */ int $episodeNumber;
    final /* synthetic */ ua.f $limiter;
    final /* synthetic */ int $seasonNumber;
    final /* synthetic */ int $tvId;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ MediaRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaRepository$resolveEpisodeImdbIds$2$1$1(int i10, int i11, int i12, MediaRepository mediaRepository, ua.f fVar, d7.d<? super MediaRepository$resolveEpisodeImdbIds$2$1$1> dVar) {
        super(2, dVar);
        this.$tvId = i10;
        this.$seasonNumber = i11;
        this.$episodeNumber = i12;
        this.this$0 = mediaRepository;
        this.$limiter = fVar;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        MediaRepository$resolveEpisodeImdbIds$2$1$1 mediaRepository$resolveEpisodeImdbIds$2$1$1 = new MediaRepository$resolveEpisodeImdbIds$2$1$1(this.$tvId, this.$seasonNumber, this.$episodeNumber, this.this$0, this.$limiter, dVar);
        mediaRepository$resolveEpisodeImdbIds$2$1$1.L$0 = obj;
        return mediaRepository$resolveEpisodeImdbIds$2$1$1;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:0|2|(1:(1:(12:6|63|7|8|57|28|(1:35)(2:32|(0))|43|(1:45)|46|47|(1:68)(2:52|53))(2:11|12))(1:13))(2:14|(2:16|17)(2:18|(2:20|26)(1:21)))|65|22|23|61|24|(9:27|57|28|(2:30|35)(0)|43|(0)|46|47|(2:49|68)(1:67))|26|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00f5, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00f6, code lost:
    
        r2 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00f8, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00f9, code lost:
    
        r12 = r13;
     */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:67:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            Method dump skipped, instruction units count: 314
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository$resolveEpisodeImdbIds$2$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.x> dVar) {
        return ((MediaRepository$resolveEpisodeImdbIds$2$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
