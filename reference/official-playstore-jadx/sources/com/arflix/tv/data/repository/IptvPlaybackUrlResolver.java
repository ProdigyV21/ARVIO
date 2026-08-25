package com.arflix.tv.data.repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import org.jsoup.helper.HttpConnection;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001:\u0002 !B#\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ5\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u000b\u001a\u00020\n2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J6\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0013\u001a\u00020\n2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\f2\b\b\u0002\u0010\u0014\u001a\u00020\u000eH\u0086@¢\u0006\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0018R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0019R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u001aR0\u0010\u001e\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001c0\u001bj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001c`\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001f¨\u0006\""}, d2 = {"Lcom/arflix/tv/data/repository/IptvPlaybackUrlResolver;", "", "Lgb/h0;", "client", "", "cacheTtlMs", "", "maxCacheEntries", "<init>", "(Lgb/h0;JI)V", "", "url", "", "headers", "", "useHead", "Lcom/arflix/tv/data/repository/IptvPlaybackUrlResolver$ProbeResult;", "executeProbe", "(Ljava/lang/String;Ljava/util/Map;Z)Lcom/arflix/tv/data/repository/IptvPlaybackUrlResolver$ProbeResult;", "rawUrl", "forceRefresh", "Lcom/arflix/tv/data/repository/IptvPlaybackTarget;", "resolve", "(Ljava/lang/String;Ljava/util/Map;ZLd7/d;)Ljava/lang/Object;", "Lgb/h0;", "J", "I", "Ljava/util/LinkedHashMap;", "Lcom/arflix/tv/data/repository/IptvPlaybackUrlResolver$CachedTarget;", "Lkotlin/collections/LinkedHashMap;", "cache", "Ljava/util/LinkedHashMap;", "ProbeResult", "CachedTarget", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class IptvPlaybackUrlResolver {
    public static final int $stable = 8;
    private final LinkedHashMap<String, CachedTarget> cache;
    private final long cacheTtlMs;
    private final gb.h0 client;
    private final int maxCacheEntries;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/data/repository/IptvPlaybackUrlResolver$CachedTarget;", "", "target", "Lcom/arflix/tv/data/repository/IptvPlaybackTarget;", "resolvedAtMs", "", "<init>", "(Lcom/arflix/tv/data/repository/IptvPlaybackTarget;J)V", "getTarget", "()Lcom/arflix/tv/data/repository/IptvPlaybackTarget;", "getResolvedAtMs", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class CachedTarget {
        private final long resolvedAtMs;
        private final IptvPlaybackTarget target;

        public CachedTarget(IptvPlaybackTarget iptvPlaybackTarget, long j10) {
            this.target = iptvPlaybackTarget;
            this.resolvedAtMs = j10;
        }

        public static /* synthetic */ CachedTarget copy$default(CachedTarget cachedTarget, IptvPlaybackTarget iptvPlaybackTarget, long j10, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                iptvPlaybackTarget = cachedTarget.target;
            }
            if ((i10 & 2) != 0) {
                j10 = cachedTarget.resolvedAtMs;
            }
            return cachedTarget.copy(iptvPlaybackTarget, j10);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final IptvPlaybackTarget getTarget() {
            return this.target;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getResolvedAtMs() {
            return this.resolvedAtMs;
        }

        public final CachedTarget copy(IptvPlaybackTarget target, long resolvedAtMs) {
            return new CachedTarget(target, resolvedAtMs);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CachedTarget)) {
                return false;
            }
            CachedTarget cachedTarget = (CachedTarget) other;
            return kotlin.jvm.internal.p.a(this.target, cachedTarget.target) && this.resolvedAtMs == cachedTarget.resolvedAtMs;
        }

        public final long getResolvedAtMs() {
            return this.resolvedAtMs;
        }

        public final IptvPlaybackTarget getTarget() {
            return this.target;
        }

        public int hashCode() {
            int iHashCode = this.target.hashCode() * 31;
            long j10 = this.resolvedAtMs;
            return iHashCode + ((int) (j10 ^ (j10 >>> 32)));
        }

        public String toString() {
            return "CachedTarget(target=" + this.target + ", resolvedAtMs=" + this.resolvedAtMs + ")";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/arflix/tv/data/repository/IptvPlaybackUrlResolver$ProbeResult;", "", "target", "Lcom/arflix/tv/data/repository/IptvPlaybackTarget;", "isConclusive", "", "<init>", "(Lcom/arflix/tv/data/repository/IptvPlaybackTarget;Z)V", "getTarget", "()Lcom/arflix/tv/data/repository/IptvPlaybackTarget;", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ProbeResult {
        private final boolean isConclusive;
        private final IptvPlaybackTarget target;

        public ProbeResult(IptvPlaybackTarget iptvPlaybackTarget, boolean z) {
            this.target = iptvPlaybackTarget;
            this.isConclusive = z;
        }

        public static /* synthetic */ ProbeResult copy$default(ProbeResult probeResult, IptvPlaybackTarget iptvPlaybackTarget, boolean z, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                iptvPlaybackTarget = probeResult.target;
            }
            if ((i10 & 2) != 0) {
                z = probeResult.isConclusive;
            }
            return probeResult.copy(iptvPlaybackTarget, z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final IptvPlaybackTarget getTarget() {
            return this.target;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsConclusive() {
            return this.isConclusive;
        }

        public final ProbeResult copy(IptvPlaybackTarget target, boolean isConclusive) {
            return new ProbeResult(target, isConclusive);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ProbeResult)) {
                return false;
            }
            ProbeResult probeResult = (ProbeResult) other;
            return kotlin.jvm.internal.p.a(this.target, probeResult.target) && this.isConclusive == probeResult.isConclusive;
        }

        public final IptvPlaybackTarget getTarget() {
            return this.target;
        }

        public int hashCode() {
            return (this.target.hashCode() * 31) + (this.isConclusive ? 1231 : 1237);
        }

        public final boolean isConclusive() {
            return this.isConclusive;
        }

        public String toString() {
            return "ProbeResult(target=" + this.target + ", isConclusive=" + this.isConclusive + ")";
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.IptvPlaybackUrlResolver$resolve$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.IptvPlaybackUrlResolver", f = "IptvPlaybackUrlResolver.kt", l = {53}, m = "resolve", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return IptvPlaybackUrlResolver.this.resolve(null, null, false, this);
        }
    }

    public IptvPlaybackUrlResolver(gb.h0 h0Var, long j10, int i10) {
        this.client = h0Var;
        this.cacheTtlMs = j10;
        this.maxCacheEntries = i10;
        this.cache = new LinkedHashMap<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ProbeResult executeProbe(String url, Map<String, String> headers, boolean useHead) {
        try {
            gb.j0 j0Var = new gb.j0();
            j0Var.i(url);
            if (useHead) {
                j0Var.f("HEAD", null);
            } else {
                j0Var.d();
                j0Var.f15724c.h("Range", "bytes=0-63");
            }
            j0Var.f15724c.h("Accept", "*/*");
            j0Var.f15724c.h("Accept-Encoding", "identity");
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!kotlin.text.o.h0(key) && !kotlin.text.o.h0(value) && !key.equalsIgnoreCase("Range")) {
                    j0Var.f15724c.h(key, value);
                }
            }
            gb.p0 p0VarD = this.client.a(j0Var.b()).d();
            try {
                String str = p0VarD.f15780i.f15730a.f15625i;
                if (kotlin.text.o.h0(str)) {
                    str = url;
                }
                String strA = p0VarD.f15785p.a(HttpConnection.CONTENT_TYPE);
                if (strA == null) {
                    strA = null;
                }
                IptvPlaybackTarget iptvPlaybackTarget = new IptvPlaybackTarget(str, IptvPlaybackUrlResolverKt.looksLikeHlsPlaybackUrl(str) || IptvPlaybackUrlResolverKt.isHlsContentType(strA) || (!useHead ? kotlin.text.u.P(kotlin.text.o.P0(p0VarD.o(64L).x()).toString(), "#EXTM3U", true) : false));
                ProbeResult probeResult = new ProbeResult(iptvPlaybackTarget, !str.equals(url) || iptvPlaybackTarget.isHls() || IptvPlaybackUrlResolverKt.isDirectMediaContentType(strA));
                p0VarD.close();
                return probeResult;
            } finally {
            }
        } catch (CancellationException e5) {
            throw e5;
        } catch (Exception unused) {
            return null;
        }
    }

    public static /* synthetic */ Object resolve$default(IptvPlaybackUrlResolver iptvPlaybackUrlResolver, String str, Map map, boolean z, d7.d dVar, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            z = false;
        }
        return iptvPlaybackUrlResolver.resolve(str, map, z, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolve(java.lang.String r20, java.util.Map<java.lang.String, java.lang.String> r21, boolean r22, d7.d<? super com.arflix.tv.data.repository.IptvPlaybackTarget> r23) {
        /*
            Method dump skipped, instruction units count: 239
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.IptvPlaybackUrlResolver.resolve(java.lang.String, java.util.Map, boolean, d7.d):java.lang.Object");
    }

    public /* synthetic */ IptvPlaybackUrlResolver(gb.h0 h0Var, long j10, int i10, int i11, kotlin.jvm.internal.h hVar) {
        this(h0Var, (i11 & 2) != 0 ? 300000L : j10, (i11 & 4) != 0 ? 256 : i10);
    }
}
