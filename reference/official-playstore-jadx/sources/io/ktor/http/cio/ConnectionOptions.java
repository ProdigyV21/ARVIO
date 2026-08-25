package io.ktor.http.cio;

import io.ktor.http.cio.internals.AsciiCharTree;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import t7.a;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B3\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\b\u0010\u0010\u001a\u00020\bH\u0002J\u0013\u0010\u0011\u001a\u00020\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u0017"}, d2 = {"Lio/ktor/http/cio/ConnectionOptions;", "", "close", "", "keepAlive", "upgrade", "extraOptions", "", "", "(ZZZLjava/util/List;)V", "getClose", "()Z", "getExtraOptions", "()Ljava/util/List;", "getKeepAlive", "getUpgrade", "buildToString", "equals", "other", "hashCode", "", "toString", "Companion", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ConnectionOptions {
    private static final ConnectionOptions Close;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ConnectionOptions KeepAlive;
    private static final ConnectionOptions Upgrade;
    private static final AsciiCharTree<x> knownTypes;
    private final boolean close;
    private final List<String> extraOptions;
    private final boolean keepAlive;
    private final boolean upgrade;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\t\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\t\u0010\bR\u0017\u0010\n\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\rR\u0017\u0010\u0010\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\rR&\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00060\u00130\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lio/ktor/http/cio/ConnectionOptions$Companion;", "", "<init>", "()V", "", "connection", "Lio/ktor/http/cio/ConnectionOptions;", "parseSlow", "(Ljava/lang/CharSequence;)Lio/ktor/http/cio/ConnectionOptions;", "parse", "Close", "Lio/ktor/http/cio/ConnectionOptions;", "getClose", "()Lio/ktor/http/cio/ConnectionOptions;", "KeepAlive", "getKeepAlive", "Upgrade", "getUpgrade", "Lio/ktor/http/cio/internals/AsciiCharTree;", "Lx6/x;", "", "knownTypes", "Lio/ktor/http/cio/internals/AsciiCharTree;", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        private final ConnectionOptions parseSlow(CharSequence connection) {
            int i10;
            int length = connection.length();
            ConnectionOptions keepAlive = null;
            ArrayList arrayList = null;
            int i11 = 0;
            int i12 = 0;
            while (i11 < length) {
                while (true) {
                    char cCharAt = connection.charAt(i11);
                    if (cCharAt != ' ' && cCharAt != ',') {
                        i12 = i11;
                        i10 = i12;
                        break;
                    }
                    i11++;
                    if (i11 >= length) {
                        i10 = i11;
                        break;
                    }
                }
                while (i10 < length) {
                    char cCharAt2 = connection.charAt(i10);
                    if (cCharAt2 == ' ' || cCharAt2 == ',') {
                        break;
                    }
                    i10++;
                }
                x xVar = (x) kotlin.collections.x.T0(ConnectionOptions.knownTypes.search(connection, i12, i10, true, ConnectionOptions$Companion$parseSlow$detected$1.INSTANCE));
                if (xVar == null) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(connection.subSequence(i12, i10).toString());
                } else {
                    Object obj = xVar.f22609l;
                    if (keepAlive == null) {
                        keepAlive = (ConnectionOptions) obj;
                    } else {
                        boolean z = true;
                        boolean z5 = keepAlive.getClose() || ((ConnectionOptions) obj).getClose();
                        boolean z10 = keepAlive.getKeepAlive() || ((ConnectionOptions) obj).getKeepAlive();
                        if (!keepAlive.getUpgrade() && !((ConnectionOptions) obj).getUpgrade()) {
                            z = false;
                        }
                        i11 = i10;
                        keepAlive = new ConnectionOptions(z5, z10, z, z.f19728i);
                    }
                }
                i11 = i10;
            }
            if (keepAlive == null) {
                keepAlive = getKeepAlive();
            }
            return arrayList == null ? keepAlive : new ConnectionOptions(keepAlive.getClose(), keepAlive.getKeepAlive(), keepAlive.getUpgrade(), arrayList);
        }

        public final ConnectionOptions getClose() {
            return ConnectionOptions.Close;
        }

        public final ConnectionOptions getKeepAlive() {
            return ConnectionOptions.KeepAlive;
        }

        public final ConnectionOptions getUpgrade() {
            return ConnectionOptions.Upgrade;
        }

        public final ConnectionOptions parse(CharSequence connection) {
            if (connection == null) {
                return null;
            }
            List listSearch$default = AsciiCharTree.search$default(ConnectionOptions.knownTypes, connection, 0, 0, true, ConnectionOptions$Companion$parse$known$1.INSTANCE, 6, null);
            return listSearch$default.size() == 1 ? (ConnectionOptions) ((x) listSearch$default.get(0)).f22609l : parseSlow(connection);
        }

        private Companion() {
        }
    }

    static {
        boolean z = false;
        ConnectionOptions connectionOptions = new ConnectionOptions(true, z, false, null, 14, null);
        Close = connectionOptions;
        boolean z5 = false;
        ConnectionOptions connectionOptions2 = new ConnectionOptions(z, true, z5, null, 13, null);
        KeepAlive = connectionOptions2;
        ConnectionOptions connectionOptions3 = new ConnectionOptions(false, z5, true, null, 11, null);
        Upgrade = connectionOptions3;
        knownTypes = AsciiCharTree.INSTANCE.build(a.E(new x("close", connectionOptions), new x("keep-alive", connectionOptions2), new x("upgrade", connectionOptions3)), ConnectionOptions$Companion$knownTypes$1.INSTANCE, ConnectionOptions$Companion$knownTypes$2.INSTANCE);
    }

    public ConnectionOptions() {
        this(false, false, false, null, 15, null);
    }

    private final String buildToString() throws IOException {
        StringBuilder sb2 = new StringBuilder();
        ArrayList arrayList = new ArrayList(this.extraOptions.size() + 3);
        if (this.close) {
            arrayList.add("close");
        }
        if (this.keepAlive) {
            arrayList.add("keep-alive");
        }
        if (this.upgrade) {
            arrayList.add("Upgrade");
        }
        if (!this.extraOptions.isEmpty()) {
            arrayList.addAll(this.extraOptions);
        }
        kotlin.collections.x.t0(arrayList, sb2, null, null, null, null, 126);
        return sb2.toString();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || ConnectionOptions.class != other.getClass()) {
            return false;
        }
        ConnectionOptions connectionOptions = (ConnectionOptions) other;
        return this.close == connectionOptions.close && this.keepAlive == connectionOptions.keepAlive && this.upgrade == connectionOptions.upgrade && p.a(this.extraOptions, connectionOptions.extraOptions);
    }

    public final boolean getClose() {
        return this.close;
    }

    public final List<String> getExtraOptions() {
        return this.extraOptions;
    }

    public final boolean getKeepAlive() {
        return this.keepAlive;
    }

    public final boolean getUpgrade() {
        return this.upgrade;
    }

    public int hashCode() {
        return this.extraOptions.hashCode() + ((((((this.close ? 1231 : 1237) * 31) + (this.keepAlive ? 1231 : 1237)) * 31) + (this.upgrade ? 1231 : 1237)) * 31);
    }

    public String toString() {
        if (!this.extraOptions.isEmpty()) {
            return buildToString();
        }
        boolean z = this.close;
        return (!z || this.keepAlive || this.upgrade) ? (z || !this.keepAlive || this.upgrade) ? (!z && this.keepAlive && this.upgrade) ? "keep-alive, Upgrade" : buildToString() : "keep-alive" : "close";
    }

    public ConnectionOptions(boolean z, boolean z5, boolean z10, List<String> list) {
        this.close = z;
        this.keepAlive = z5;
        this.upgrade = z10;
        this.extraOptions = list;
    }

    public /* synthetic */ ConnectionOptions(boolean z, boolean z5, boolean z10, List list, int i10, h hVar) {
        this((i10 & 1) != 0 ? false : z, (i10 & 2) != 0 ? false : z5, (i10 & 4) != 0 ? false : z10, (i10 & 8) != 0 ? z.f19728i : list);
    }
}
