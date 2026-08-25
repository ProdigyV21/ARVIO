package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.RuntimeKind;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0004\b\u0006\u0010\u0007J,\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\f\u001a\u00020\u000bH\u0086@¢\u0006\u0004\b\u000e\u0010\u000fJ,\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\f\u001a\u00020\u0010H\u0086@¢\u0006\u0004\b\u0011\u0010\u0012R \u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/arflix/tv/data/repository/AddonRuntimeAggregator;", "", "", "Lcom/arflix/tv/data/model/RuntimeKind;", "Lcom/arflix/tv/data/repository/AddonRuntime;", "addonRuntimes", "<init>", "(Ljava/util/Map;)V", "", "Lcom/arflix/tv/data/model/Addon;", "stremioAddons", "Lcom/arflix/tv/data/repository/MovieRuntimeRequest;", "request", "Lcom/arflix/tv/data/model/StreamSource;", "resolveMovieStreams", "(Ljava/util/List;Lcom/arflix/tv/data/repository/MovieRuntimeRequest;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/repository/EpisodeRuntimeRequest;", "resolveEpisodeStreams", "(Ljava/util/List;Lcom/arflix/tv/data/repository/EpisodeRuntimeRequest;Ld7/d;)Ljava/lang/Object;", "Ljava/util/Map;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AddonRuntimeAggregator {
    public static final int $stable = 0;
    private final Map<RuntimeKind, AddonRuntime> addonRuntimes;

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AddonRuntimeAggregator$resolveEpisodeStreams$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.AddonRuntimeAggregator", f = "AddonRuntimeAggregator.kt", l = {26}, m = "resolveEpisodeStreams", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AddonRuntimeAggregator.this.resolveEpisodeStreams(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.AddonRuntimeAggregator$resolveMovieStreams$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.AddonRuntimeAggregator", f = "AddonRuntimeAggregator.kt", l = {16}, m = "resolveMovieStreams", v = 2)
    public static final class C06701 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C06701(d7.d<? super C06701> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AddonRuntimeAggregator.this.resolveMovieStreams(null, null, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AddonRuntimeAggregator(Map<RuntimeKind, ? extends AddonRuntime> map) {
        this.addonRuntimes = map;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0062 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0063 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveEpisodeStreams(java.util.List<com.arflix.tv.data.model.Addon> r5, com.arflix.tv.data.repository.EpisodeRuntimeRequest r6, d7.d<? super java.util.List<com.arflix.tv.data.model.StreamSource>> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.arflix.tv.data.repository.AddonRuntimeAggregator.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            com.arflix.tv.data.repository.AddonRuntimeAggregator$resolveEpisodeStreams$1 r0 = (com.arflix.tv.data.repository.AddonRuntimeAggregator.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.AddonRuntimeAggregator$resolveEpisodeStreams$1 r0 = new com.arflix.tv.data.repository.AddonRuntimeAggregator$resolveEpisodeStreams$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            kotlin.collections.z r2 = kotlin.collections.z.f19728i
            r3 = 1
            if (r1 == 0) goto L37
            if (r1 != r3) goto L2f
            java.lang.Object r5 = r0.L$1
            com.arflix.tv.data.repository.EpisodeRuntimeRequest r5 = (com.arflix.tv.data.repository.EpisodeRuntimeRequest) r5
            java.lang.Object r5 = r0.L$0
            java.util.List r5 = (java.util.List) r5
            k2.c.G(r7)
            goto L5d
        L2f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L37:
            k2.c.G(r7)
            boolean r7 = r5.isEmpty()
            if (r7 == 0) goto L41
            return r2
        L41:
            java.util.Map<com.arflix.tv.data.model.RuntimeKind, com.arflix.tv.data.repository.AddonRuntime> r7 = r4.addonRuntimes
            com.arflix.tv.data.model.RuntimeKind r1 = com.arflix.tv.data.model.RuntimeKind.STREMIO
            java.lang.Object r7 = r7.get(r1)
            com.arflix.tv.data.repository.AddonRuntime r7 = (com.arflix.tv.data.repository.AddonRuntime) r7
            r1 = 0
            if (r7 == 0) goto L60
            r0.L$0 = r1
            r0.L$1 = r1
            r0.label = r3
            java.lang.Object r7 = r7.resolveEpisodeStreams(r5, r6, r0)
            e7.a r5 = e7.a.f15033i
            if (r7 != r5) goto L5d
            return r5
        L5d:
            r1 = r7
            java.util.List r1 = (java.util.List) r1
        L60:
            if (r1 != 0) goto L63
            return r2
        L63:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AddonRuntimeAggregator.resolveEpisodeStreams(java.util.List, com.arflix.tv.data.repository.EpisodeRuntimeRequest, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0062 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0063 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveMovieStreams(java.util.List<com.arflix.tv.data.model.Addon> r5, com.arflix.tv.data.repository.MovieRuntimeRequest r6, d7.d<? super java.util.List<com.arflix.tv.data.model.StreamSource>> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.arflix.tv.data.repository.AddonRuntimeAggregator.C06701
            if (r0 == 0) goto L13
            r0 = r7
            com.arflix.tv.data.repository.AddonRuntimeAggregator$resolveMovieStreams$1 r0 = (com.arflix.tv.data.repository.AddonRuntimeAggregator.C06701) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.AddonRuntimeAggregator$resolveMovieStreams$1 r0 = new com.arflix.tv.data.repository.AddonRuntimeAggregator$resolveMovieStreams$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            kotlin.collections.z r2 = kotlin.collections.z.f19728i
            r3 = 1
            if (r1 == 0) goto L37
            if (r1 != r3) goto L2f
            java.lang.Object r5 = r0.L$1
            com.arflix.tv.data.repository.MovieRuntimeRequest r5 = (com.arflix.tv.data.repository.MovieRuntimeRequest) r5
            java.lang.Object r5 = r0.L$0
            java.util.List r5 = (java.util.List) r5
            k2.c.G(r7)
            goto L5d
        L2f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L37:
            k2.c.G(r7)
            boolean r7 = r5.isEmpty()
            if (r7 == 0) goto L41
            return r2
        L41:
            java.util.Map<com.arflix.tv.data.model.RuntimeKind, com.arflix.tv.data.repository.AddonRuntime> r7 = r4.addonRuntimes
            com.arflix.tv.data.model.RuntimeKind r1 = com.arflix.tv.data.model.RuntimeKind.STREMIO
            java.lang.Object r7 = r7.get(r1)
            com.arflix.tv.data.repository.AddonRuntime r7 = (com.arflix.tv.data.repository.AddonRuntime) r7
            r1 = 0
            if (r7 == 0) goto L60
            r0.L$0 = r1
            r0.L$1 = r1
            r0.label = r3
            java.lang.Object r7 = r7.resolveMovieStreams(r5, r6, r0)
            e7.a r5 = e7.a.f15033i
            if (r7 != r5) goto L5d
            return r5
        L5d:
            r1 = r7
            java.util.List r1 = (java.util.List) r1
        L60:
            if (r1 != 0) goto L63
            return r2
        L63:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.AddonRuntimeAggregator.resolveMovieStreams(java.util.List, com.arflix.tv.data.repository.MovieRuntimeRequest, d7.d):java.lang.Object");
    }
}
