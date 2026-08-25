package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.Addon;
import com.arflix.tv.data.model.Subtitle;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/Subtitle;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.StreamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1", f = "StreamRepository.kt", l = {3072, 3074, 3075}, m = "invokeSuspend", v = 2)
public final class StreamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends Subtitle>>, Object> {
    final /* synthetic */ Addon $addon;
    final /* synthetic */ String $contentId;
    final /* synthetic */ Set<String> $speculativeAddonIds;
    final /* synthetic */ String $type;
    final /* synthetic */ String $videoHash;
    final /* synthetic */ Long $videoSize;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ StreamRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StreamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1(Set<String> set, Addon addon, StreamRepository streamRepository, String str, String str2, String str3, Long l10, d7.d<? super StreamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1> dVar) {
        super(2, dVar);
        this.$speculativeAddonIds = set;
        this.$addon = addon;
        this.this$0 = streamRepository;
        this.$type = str;
        this.$contentId = str2;
        this.$videoHash = str3;
        this.$videoSize = l10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object invokeSuspend$attempt(ka.k0 r13, com.arflix.tv.data.repository.StreamRepository r14, com.arflix.tv.data.model.Addon r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, java.lang.Long r19, d7.d<? super java.util.List<com.arflix.tv.data.model.Subtitle>> r20) {
        /*
            r13 = r20
            boolean r0 = r13 instanceof com.arflix.tv.data.repository.StreamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1$attempt$1
            if (r0 == 0) goto L15
            r0 = r13
            com.arflix.tv.data.repository.StreamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1$attempt$1 r0 = (com.arflix.tv.data.repository.StreamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1$attempt$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L15
            int r1 = r1 - r2
            r0.label = r1
            goto L1a
        L15:
            com.arflix.tv.data.repository.StreamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1$attempt$1 r0 = new com.arflix.tv.data.repository.StreamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1$attempt$1
            r0.<init>(r13)
        L1a:
            java.lang.Object r13 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L53
            if (r1 != r2) goto L4b
            java.lang.Object r14 = r0.L$7
            ka.k0 r14 = (ka.k0) r14
            java.lang.Object r14 = r0.L$6
            java.lang.Long r14 = (java.lang.Long) r14
            java.lang.Object r14 = r0.L$5
            java.lang.String r14 = (java.lang.String) r14
            java.lang.Object r14 = r0.L$4
            java.lang.String r14 = (java.lang.String) r14
            java.lang.Object r14 = r0.L$3
            java.lang.String r14 = (java.lang.String) r14
            java.lang.Object r14 = r0.L$2
            com.arflix.tv.data.model.Addon r14 = (com.arflix.tv.data.model.Addon) r14
            java.lang.Object r1 = r0.L$1
            com.arflix.tv.data.repository.StreamRepository r1 = (com.arflix.tv.data.repository.StreamRepository) r1
            java.lang.Object r0 = r0.L$0
            ka.k0 r0 = (ka.k0) r0
            k2.c.G(r13)     // Catch: java.lang.Throwable -> L47
            goto L8a
        L47:
            r0 = move-exception
            r13 = r0
            r6 = r14
            goto L90
        L4b:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L53:
            k2.c.G(r13)
            long r3 = com.arflix.tv.data.repository.StreamRepository.access$getSUBTITLE_TIMEOUT_MS$p(r14)     // Catch: java.lang.Throwable -> L8d
            com.arflix.tv.data.repository.StreamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1$attempt$2$1 r5 = new com.arflix.tv.data.repository.StreamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1$attempt$2$1     // Catch: java.lang.Throwable -> L8d
            r12 = 0
            r7 = r14
            r6 = r15
            r8 = r16
            r9 = r17
            r10 = r18
            r11 = r19
            r5.<init>(r6, r7, r8, r9, r10, r11, r12)     // Catch: java.lang.Throwable -> L8d
            r13 = 0
            r0.L$0 = r13     // Catch: java.lang.Throwable -> L8d
            r0.L$1 = r13     // Catch: java.lang.Throwable -> L8d
            r0.L$2 = r15     // Catch: java.lang.Throwable -> L8d
            r0.L$3 = r13     // Catch: java.lang.Throwable -> L8d
            r0.L$4 = r13     // Catch: java.lang.Throwable -> L8d
            r0.L$5 = r13     // Catch: java.lang.Throwable -> L8d
            r0.L$6 = r13     // Catch: java.lang.Throwable -> L8d
            r0.L$7 = r13     // Catch: java.lang.Throwable -> L8d
            r13 = 0
            r0.I$0 = r13     // Catch: java.lang.Throwable -> L8d
            r0.label = r2     // Catch: java.lang.Throwable -> L8d
            java.lang.Object r13 = ka.m0.z(r3, r5, r0)     // Catch: java.lang.Throwable -> L8d
            e7.a r14 = e7.a.f15033i
            if (r13 != r14) goto L89
            return r14
        L89:
            r14 = r15
        L8a:
            java.util.List r13 = (java.util.List) r13     // Catch: java.lang.Throwable -> L47
            goto L97
        L8d:
            r0 = move-exception
            r13 = r0
            r6 = r15
        L90:
            x6.c0 r14 = new x6.c0
            r14.<init>(r13)
            r13 = r14
            r14 = r6
        L97:
            java.lang.Throwable r0 = x6.d0.a(r13)
            if (r0 == 0) goto Lc0
            java.lang.String r14 = r14.getName()
            java.lang.String r0 = r0.getMessage()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "subtitles fetch failed addon="
            r1.<init>(r2)
            r1.append(r14)
            java.lang.String r14 = " err="
            r1.append(r14)
            r1.append(r0)
            java.lang.String r14 = r1.toString()
            java.lang.String r0 = "StreamRepository"
            android.util.Log.w(r0, r14)
        Lc0:
            boolean r14 = r13 instanceof x6.c0
            if (r14 == 0) goto Lc6
            kotlin.collections.z r13 = kotlin.collections.z.f19728i
        Lc6:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.StreamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1.invokeSuspend$attempt(ka.k0, com.arflix.tv.data.repository.StreamRepository, com.arflix.tv.data.model.Addon, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, d7.d):java.lang.Object");
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        StreamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1 streamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1 = new StreamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1(this.$speculativeAddonIds, this.$addon, this.this$0, this.$type, this.$contentId, this.$videoHash, this.$videoSize, dVar);
        streamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1.L$0 = obj;
        return streamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x008b, code lost:
    
        if (r14 != r12) goto L25;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            java.lang.Object r0 = r13.L$0
            r1 = r0
            ka.k0 r1 = (ka.k0) r1
            int r0 = r13.label
            r9 = 3
            r10 = 2
            r2 = 1
            r11 = 0
            e7.a r12 = e7.a.f15033i
            if (r0 == 0) goto L34
            if (r0 == r2) goto L2f
            if (r0 == r10) goto L26
            if (r0 != r9) goto L1e
            java.lang.Object r0 = r13.L$1
            java.util.List r0 = (java.util.List) r0
            k2.c.G(r14)
            goto L8e
        L1e:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L26:
            java.lang.Object r0 = r13.L$1
            java.util.List r0 = (java.util.List) r0
            k2.c.G(r14)
            r8 = r13
            goto L75
        L2f:
            k2.c.G(r14)
            r8 = r13
            goto L50
        L34:
            k2.c.G(r14)
            r14 = r2
            com.arflix.tv.data.repository.StreamRepository r2 = r13.this$0
            com.arflix.tv.data.model.Addon r3 = r13.$addon
            java.lang.String r4 = r13.$type
            java.lang.String r5 = r13.$contentId
            java.lang.String r6 = r13.$videoHash
            java.lang.Long r7 = r13.$videoSize
            r13.L$0 = r1
            r13.label = r14
            r8 = r13
            java.lang.Object r14 = invokeSuspend$attempt(r1, r2, r3, r4, r5, r6, r7, r8)
            if (r14 != r12) goto L50
            goto L8d
        L50:
            java.util.List r14 = (java.util.List) r14
            boolean r0 = r14.isEmpty()
            if (r0 == 0) goto L90
            java.util.Set<java.lang.String> r0 = r8.$speculativeAddonIds
            com.arflix.tv.data.model.Addon r2 = r8.$addon
            java.lang.String r2 = r2.getId()
            boolean r0 = r0.contains(r2)
            if (r0 != 0) goto L90
            r8.L$0 = r1
            r8.L$1 = r11
            r8.label = r10
            r2 = 1500(0x5dc, double:7.41E-321)
            java.lang.Object r14 = ka.s0.a(r2, r13)
            if (r14 != r12) goto L75
            goto L8d
        L75:
            com.arflix.tv.data.repository.StreamRepository r2 = r8.this$0
            com.arflix.tv.data.model.Addon r3 = r8.$addon
            java.lang.String r4 = r8.$type
            java.lang.String r5 = r8.$contentId
            java.lang.String r6 = r8.$videoHash
            java.lang.Long r7 = r8.$videoSize
            r8.L$0 = r11
            r8.L$1 = r11
            r8.label = r9
            java.lang.Object r14 = invokeSuspend$attempt(r1, r2, r3, r4, r5, r6, r7, r8)
            if (r14 != r12) goto L8e
        L8d:
            return r12
        L8e:
            java.util.List r14 = (java.util.List) r14
        L90:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.StreamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<Subtitle>> dVar) {
        return ((StreamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
