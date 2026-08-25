package io.ktor.server.cio;

import a0.c;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\tHÆ\u0003J1\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\t2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001c"}, d2 = {"Lio/ktor/server/cio/HttpServerSettings;", "", "host", "", "port", "", "connectionIdleTimeoutSeconds", "", "reuseAddress", "", "(Ljava/lang/String;IJZ)V", "getConnectionIdleTimeoutSeconds", "()J", "getHost", "()Ljava/lang/String;", "getPort", "()I", "getReuseAddress", "()Z", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "ktor-server-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class HttpServerSettings {
    private final long connectionIdleTimeoutSeconds;
    private final String host;
    private final int port;
    private final boolean reuseAddress;

    public HttpServerSettings() {
        this(null, 0, 0L, false, 15, null);
    }

    public static /* synthetic */ HttpServerSettings copy$default(HttpServerSettings httpServerSettings, String str, int i10, long j10, boolean z, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            str = httpServerSettings.host;
        }
        if ((i11 & 2) != 0) {
            i10 = httpServerSettings.port;
        }
        if ((i11 & 4) != 0) {
            j10 = httpServerSettings.connectionIdleTimeoutSeconds;
        }
        if ((i11 & 8) != 0) {
            z = httpServerSettings.reuseAddress;
        }
        boolean z5 = z;
        return httpServerSettings.copy(str, i10, j10, z5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getHost() {
        return this.host;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getPort() {
        return this.port;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getConnectionIdleTimeoutSeconds() {
        return this.connectionIdleTimeoutSeconds;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getReuseAddress() {
        return this.reuseAddress;
    }

    public final HttpServerSettings copy(String host, int port, long connectionIdleTimeoutSeconds, boolean reuseAddress) {
        return new HttpServerSettings(host, port, connectionIdleTimeoutSeconds, reuseAddress);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HttpServerSettings)) {
            return false;
        }
        HttpServerSettings httpServerSettings = (HttpServerSettings) other;
        return p.a(this.host, httpServerSettings.host) && this.port == httpServerSettings.port && this.connectionIdleTimeoutSeconds == httpServerSettings.connectionIdleTimeoutSeconds && this.reuseAddress == httpServerSettings.reuseAddress;
    }

    public final long getConnectionIdleTimeoutSeconds() {
        return this.connectionIdleTimeoutSeconds;
    }

    public final String getHost() {
        return this.host;
    }

    public final int getPort() {
        return this.port;
    }

    public final boolean getReuseAddress() {
        return this.reuseAddress;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v5, types: [int] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    public int hashCode() {
        int iHashCode = ((this.host.hashCode() * 31) + this.port) * 31;
        long j10 = this.connectionIdleTimeoutSeconds;
        int i10 = (iHashCode + ((int) (j10 ^ (j10 >>> 32)))) * 31;
        boolean z = this.reuseAddress;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return i10 + r1;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("HttpServerSettings(host=");
        sb2.append(this.host);
        sb2.append(", port=");
        sb2.append(this.port);
        sb2.append(", connectionIdleTimeoutSeconds=");
        sb2.append(this.connectionIdleTimeoutSeconds);
        sb2.append(", reuseAddress=");
        return c.r(sb2, this.reuseAddress, ')');
    }

    public HttpServerSettings(String str, int i10, long j10, boolean z) {
        this.host = str;
        this.port = i10;
        this.connectionIdleTimeoutSeconds = j10;
        this.reuseAddress = z;
    }

    public /* synthetic */ HttpServerSettings(String str, int i10, long j10, boolean z, int i11, h hVar) {
        this((i11 & 1) != 0 ? "0.0.0.0" : str, (i11 & 2) != 0 ? 8080 : i10, (i11 & 4) != 0 ? 45L : j10, (i11 & 8) != 0 ? false : z);
    }
}
