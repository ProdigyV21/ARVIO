package io.ktor.server.engine;

import androidx.compose.foundation.c;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lio/ktor/server/engine/ConnectorType;", "", ContentDisposition.Parameters.Name, "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class ConnectorType {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ConnectorType HTTP = new ConnectorType("HTTP");
    private static final ConnectorType HTTPS = new ConnectorType("HTTPS");
    private final String name;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, d2 = {"Lio/ktor/server/engine/ConnectorType$Companion;", "", "()V", "HTTP", "Lio/ktor/server/engine/ConnectorType;", "getHTTP", "()Lio/ktor/server/engine/ConnectorType;", "HTTPS", "getHTTPS", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final ConnectorType getHTTP() {
            return ConnectorType.HTTP;
        }

        public final ConnectorType getHTTPS() {
            return ConnectorType.HTTPS;
        }

        private Companion() {
        }
    }

    public ConnectorType(String str) {
        this.name = str;
    }

    public static /* synthetic */ ConnectorType copy$default(ConnectorType connectorType, String str, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = connectorType.name;
        }
        return connectorType.copy(str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final ConnectorType copy(String name) {
        return new ConnectorType(name);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ConnectorType) && p.a(this.name, ((ConnectorType) other).name);
    }

    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String toString() {
        return c.u(new StringBuilder("ConnectorType(name="), this.name, ')');
    }
}
