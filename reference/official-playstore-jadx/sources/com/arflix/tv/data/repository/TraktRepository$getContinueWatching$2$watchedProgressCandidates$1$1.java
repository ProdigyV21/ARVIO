package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.TraktWatchedShow;
import java.util.Set;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/ContinueWatchingCandidate;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/ContinueWatchingCandidate;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.TraktRepository$getContinueWatching$2$watchedProgressCandidates$1$1", f = "TraktRepository.kt", l = {4806, 1551}, m = "invokeSuspend", v = 2)
public final class TraktRepository$getContinueWatching$2$watchedProgressCandidates$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super ContinueWatchingCandidate>, Object> {
    final /* synthetic */ String[] $authHolder;
    final /* synthetic */ boolean $includeSpecials;
    final /* synthetic */ Set<String> $processedKeys;
    final /* synthetic */ ua.f $semaphore;
    final /* synthetic */ TraktWatchedShow $watched;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    boolean Z$0;
    int label;
    final /* synthetic */ TraktRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TraktRepository$getContinueWatching$2$watchedProgressCandidates$1$1(ua.f fVar, TraktWatchedShow traktWatchedShow, boolean z, Set<String> set, TraktRepository traktRepository, String[] strArr, d7.d<? super TraktRepository$getContinueWatching$2$watchedProgressCandidates$1$1> dVar) {
        super(2, dVar);
        this.$semaphore = fVar;
        this.$watched = traktWatchedShow;
        this.$includeSpecials = z;
        this.$processedKeys = set;
        this.this$0 = traktRepository;
        this.$authHolder = strArr;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TraktRepository$getContinueWatching$2$watchedProgressCandidates$1$1(this.$semaphore, this.$watched, this.$includeSpecials, this.$processedKeys, this.this$0, this.$authHolder, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00ec A[PHI: r3 r11
      0x00ec: PHI (r3v6 com.arflix.tv.data.repository.ContinueWatchingCandidate) = 
      (r3v5 com.arflix.tv.data.repository.ContinueWatchingCandidate)
      (r3v7 com.arflix.tv.data.repository.ContinueWatchingCandidate)
      (r3v7 com.arflix.tv.data.repository.ContinueWatchingCandidate)
      (r3v7 com.arflix.tv.data.repository.ContinueWatchingCandidate)
      (r3v7 com.arflix.tv.data.repository.ContinueWatchingCandidate)
      (r3v7 com.arflix.tv.data.repository.ContinueWatchingCandidate)
      (r3v7 com.arflix.tv.data.repository.ContinueWatchingCandidate)
     binds: [B:88:0x021b, B:35:0x00ea, B:39:0x00f4, B:48:0x0148, B:51:0x0150, B:46:0x0109, B:42:0x0100] A[DONT_GENERATE, DONT_INLINE]
      0x00ec: PHI (r11v9 ua.f) = (r11v7 ua.f), (r11v10 ua.f), (r11v10 ua.f), (r11v10 ua.f), (r11v10 ua.f), (r11v10 ua.f), (r11v10 ua.f) binds: [B:88:0x021b, B:35:0x00ea, B:39:0x00f4, B:48:0x0148, B:51:0x0150, B:46:0x0109, B:42:0x0100] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x021b A[Catch: all -> 0x0035, TryCatch #1 {all -> 0x0035, blocks: (B:7:0x0029, B:33:0x00e4, B:34:0x00e6, B:38:0x00f0, B:40:0x00f6, B:44:0x0103, B:47:0x010a, B:49:0x014a, B:52:0x0151, B:55:0x018d, B:60:0x019d, B:64:0x01a8, B:67:0x01b3, B:69:0x01f2, B:71:0x01f8, B:75:0x0200, B:86:0x0217, B:88:0x021b, B:89:0x0258), top: B:97:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0258 A[Catch: all -> 0x0035, TRY_LEAVE, TryCatch #1 {all -> 0x0035, blocks: (B:7:0x0029, B:33:0x00e4, B:34:0x00e6, B:38:0x00f0, B:40:0x00f6, B:44:0x0103, B:47:0x010a, B:49:0x014a, B:52:0x0151, B:55:0x018d, B:60:0x019d, B:64:0x01a8, B:67:0x01b3, B:69:0x01f2, B:71:0x01f8, B:75:0x0200, B:86:0x0217, B:88:0x021b, B:89:0x0258), top: B:97:0x000f }] */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 3 */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r54) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 611
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.TraktRepository$getContinueWatching$2$watchedProgressCandidates$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super ContinueWatchingCandidate> dVar) {
        return ((TraktRepository$getContinueWatching$2$watchedProgressCandidates$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
