package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.MediaRepository;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "Lx6/x;", "Lcom/arflix/tv/data/model/MediaType;", "", "<anonymous>", "(Lka/k0;)Lx6/x;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.MediaRepository$mapTraktItemsToTmdbRefs$2$resolved$1$1", f = "MediaRepository.kt", l = {3970, 3660}, m = "invokeSuspend", v = 2)
public final class MediaRepository$mapTraktItemsToTmdbRefs$2$resolved$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.x>, Object> {
    final /* synthetic */ MediaRepository.C09262.Unresolved $candidate;
    final /* synthetic */ ua.f $semaphore;
    int I$0;
    int I$1;
    int I$2;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ MediaRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MediaRepository$mapTraktItemsToTmdbRefs$2$resolved$1$1(ua.f fVar, MediaRepository mediaRepository, MediaRepository.C09262.Unresolved unresolved, d7.d<? super MediaRepository$mapTraktItemsToTmdbRefs$2$resolved$1$1> dVar) {
        super(2, dVar);
        this.$semaphore = fVar;
        this.this$0 = mediaRepository;
        this.$candidate = unresolved;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        MediaRepository$mapTraktItemsToTmdbRefs$2$resolved$1$1 mediaRepository$mapTraktItemsToTmdbRefs$2$resolved$1$1 = new MediaRepository$mapTraktItemsToTmdbRefs$2$resolved$1$1(this.$semaphore, this.this$0, this.$candidate, dVar);
        mediaRepository$mapTraktItemsToTmdbRefs$2$resolved$1$1.L$0 = obj;
        return mediaRepository$mapTraktItemsToTmdbRefs$2$resolved$1$1;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:0|2|(1:(1:(17:6|73|7|8|22|(6:25|(1:27)(2:28|(1:30)(1:31))|32|(3:79|34|83)(1:82)|81|23)|80|35|(2:36|(5:38|(1:40)|(1:42)(1:43)|44|(3:46|(2:88|51)|89)(1:85))(2:84|52))|53|(1:55)|(1:57)(1:59)|63|(1:65)(1:66)|67|68|69)(2:11|12))(1:13))(3:14|(1:17)|20)|75|18|(14:21|22|(1:23)|80|35|(3:36|(0)(0)|89)|53|(0)|(0)(0)|63|(0)(0)|67|68|69)|20|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0139, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x013a, code lost:
    
        r1 = r11;
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00a9 A[Catch: all -> 0x0025, TryCatch #0 {all -> 0x0025, blocks: (B:7:0x001e, B:22:0x0094, B:23:0x00a3, B:25:0x00a9, B:27:0x00bc, B:32:0x00cc, B:34:0x00d2, B:28:0x00bf, B:30:0x00c8, B:35:0x00d6, B:36:0x00da, B:38:0x00e0, B:40:0x00ed, B:42:0x00f3, B:44:0x00fe, B:46:0x0104, B:49:0x010f, B:53:0x0117, B:57:0x0124), top: B:73:0x001e }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00e0 A[Catch: all -> 0x0025, TryCatch #0 {all -> 0x0025, blocks: (B:7:0x001e, B:22:0x0094, B:23:0x00a3, B:25:0x00a9, B:27:0x00bc, B:32:0x00cc, B:34:0x00d2, B:28:0x00bf, B:30:0x00c8, B:35:0x00d6, B:36:0x00da, B:38:0x00e0, B:40:0x00ed, B:42:0x00f3, B:44:0x00fe, B:46:0x0104, B:49:0x010f, B:53:0x0117, B:57:0x0124), top: B:73:0x001e }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0124 A[Catch: all -> 0x0025, TRY_LEAVE, TryCatch #0 {all -> 0x0025, blocks: (B:7:0x001e, B:22:0x0094, B:23:0x00a3, B:25:0x00a9, B:27:0x00bc, B:32:0x00cc, B:34:0x00d2, B:28:0x00bf, B:30:0x00c8, B:35:0x00d6, B:36:0x00da, B:38:0x00e0, B:40:0x00ed, B:42:0x00f3, B:44:0x00fe, B:46:0x0104, B:49:0x010f, B:53:0x0117, B:57:0x0124), top: B:73:0x001e }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0116 A[SYNTHETIC] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            Method dump skipped, instruction units count: 339
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository$mapTraktItemsToTmdbRefs$2$resolved$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.x> dVar) {
        return ((MediaRepository$mapTraktItemsToTmdbRefs$2$resolved$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
