package com.arflix.tv.data.repository;

import io.ktor.http.LinkHeader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0006HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/arflix/tv/data/repository/SkipInterval;", "", "startMs", "", "endMs", LinkHeader.Parameters.Type, "", "provider", "<init>", "(JJLjava/lang/String;Ljava/lang/String;)V", "getStartMs", "()J", "getEndMs", "getType", "()Ljava/lang/String;", "getProvider", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SkipInterval {
    public static final int $stable = 0;
    private final long endMs;
    private final String provider;
    private final long startMs;
    private final String type;

    public SkipInterval(long j10, long j11, String str, String str2) {
        this.startMs = j10;
        this.endMs = j11;
        this.type = str;
        this.provider = str2;
    }

    public static /* synthetic */ SkipInterval copy$default(SkipInterval skipInterval, long j10, long j11, String str, String str2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            j10 = skipInterval.startMs;
        }
        long j12 = j10;
        if ((i10 & 2) != 0) {
            j11 = skipInterval.endMs;
        }
        long j13 = j11;
        if ((i10 & 4) != 0) {
            str = skipInterval.type;
        }
        String str3 = str;
        if ((i10 & 8) != 0) {
            str2 = skipInterval.provider;
        }
        return skipInterval.copy(j12, j13, str3, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getStartMs() {
        return this.startMs;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getEndMs() {
        return this.endMs;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getProvider() {
        return this.provider;
    }

    public final SkipInterval copy(long startMs, long endMs, String type, String provider) {
        return new SkipInterval(startMs, endMs, type, provider);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SkipInterval)) {
            return false;
        }
        SkipInterval skipInterval = (SkipInterval) other;
        return this.startMs == skipInterval.startMs && this.endMs == skipInterval.endMs && kotlin.jvm.internal.p.a(this.type, skipInterval.type) && kotlin.jvm.internal.p.a(this.provider, skipInterval.provider);
    }

    public final long getEndMs() {
        return this.endMs;
    }

    public final String getProvider() {
        return this.provider;
    }

    public final long getStartMs() {
        return this.startMs;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        long j10 = this.startMs;
        long j11 = this.endMs;
        return this.provider.hashCode() + androidx.compose.foundation.c.c(((((int) (j10 ^ (j10 >>> 32))) * 31) + ((int) (j11 ^ (j11 >>> 32)))) * 31, 31, this.type);
    }

    public String toString() {
        long j10 = this.startMs;
        long j11 = this.endMs;
        String str = this.type;
        String str2 = this.provider;
        StringBuilder sbR = androidx.compose.material3.d.r(j10, "SkipInterval(startMs=", ", endMs=");
        sbR.append(j11);
        sbR.append(", type=");
        sbR.append(str);
        return androidx.compose.material3.d.q(sbR, ", provider=", str2, ")");
    }
}
