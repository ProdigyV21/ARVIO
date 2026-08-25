package io.ktor.network.sockets;

import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0000\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003H\u0086\u0002J\t\u0010\u0011\u001a\u00020\u0005H\u0086\u0002J\u001a\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0096\u0002J\b\u0010\u0017\u001a\u00020\u0005H\u0016J\b\u0010\u0018\u001a\u00020\u0003H\u0016R\u0014\u0010\u0007\u001a\u00020\bX\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lio/ktor/network/sockets/InetSocketAddress;", "Lio/ktor/network/sockets/SocketAddress;", "hostname", "", "port", "", "(Ljava/lang/String;I)V", "address", "Ljava/net/InetSocketAddress;", "(Ljava/net/InetSocketAddress;)V", "getAddress$ktor_network", "()Ljava/net/InetSocketAddress;", "getHostname", "()Ljava/lang/String;", "getPort", "()I", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class InetSocketAddress extends SocketAddress {
    private final java.net.InetSocketAddress address;

    public InetSocketAddress(java.net.InetSocketAddress inetSocketAddress) {
        super(null);
        this.address = inetSocketAddress;
    }

    public static /* synthetic */ InetSocketAddress copy$default(InetSocketAddress inetSocketAddress, String str, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            str = inetSocketAddress.getHostname();
        }
        if ((i11 & 2) != 0) {
            i10 = inetSocketAddress.getPort();
        }
        return inetSocketAddress.copy(str, i10);
    }

    public final String component1() {
        return getHostname();
    }

    public final int component2() {
        return getPort();
    }

    public final InetSocketAddress copy(String hostname, int port) {
        return new InetSocketAddress(hostname, port);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return InetSocketAddress.class.equals(other != null ? other.getClass() : null) && p.a(getAddress(), ((InetSocketAddress) other).getAddress());
    }

    public final String getHostname() {
        return getAddress().getHostName();
    }

    public final int getPort() {
        return getAddress().getPort();
    }

    public int hashCode() {
        return getAddress().hashCode();
    }

    public String toString() {
        return getAddress().toString();
    }

    @Override // io.ktor.network.sockets.SocketAddress
    /* JADX INFO: renamed from: getAddress$ktor_network, reason: from getter */
    public java.net.InetSocketAddress getAddress() {
        return this.address;
    }

    public InetSocketAddress(String str, int i10) {
        this(new java.net.InetSocketAddress(str, i10));
    }
}
