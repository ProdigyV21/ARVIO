package io.ktor.network.sockets;

import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003H\u0086\u0002J\u0010\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0003H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lio/ktor/network/sockets/UnixSocketAddress;", "Lio/ktor/network/sockets/SocketAddress;", "path", "", "(Ljava/lang/String;)V", "address", "Ljava/net/SocketAddress;", "(Ljava/net/SocketAddress;)V", "getAddress$ktor_network", "()Ljava/net/SocketAddress;", "getPath", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Companion", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class UnixSocketAddress extends SocketAddress {
    private static final Companion Companion = new Companion(0 == true ? 1 : 0);
    private static final Class<?> unixDomainSocketAddressClass;
    private final java.net.SocketAddress address;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lio/ktor/network/sockets/UnixSocketAddress$Companion;", "", "()V", "unixDomainSocketAddressClass", "Ljava/lang/Class;", "checkSupportForUnixDomainSockets", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Class<?> checkSupportForUnixDomainSockets() {
            Class<?> cls = UnixSocketAddress.unixDomainSocketAddressClass;
            if (cls != null) {
                return cls;
            }
            throw new IllegalStateException("Unix domain sockets are unsupported before Java 16.");
        }

        private Companion() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static {
        Class<?> cls = null;
        try {
            cls = Class.forName(SocketAddressJvmKt.UNIX_DOMAIN_SOCKET_ADDRESS_CLASS);
        } catch (ClassNotFoundException unused) {
        }
        unixDomainSocketAddressClass = cls;
    }

    public UnixSocketAddress(java.net.SocketAddress socketAddress) {
        super(null);
        this.address = socketAddress;
        if (!getAddress().getClass().getName().equals(SocketAddressJvmKt.UNIX_DOMAIN_SOCKET_ADDRESS_CLASS)) {
            throw new IllegalStateException("address should be java.net.UnixDomainSocketAddress");
        }
    }

    public static /* synthetic */ UnixSocketAddress copy$default(UnixSocketAddress unixSocketAddress, String str, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = unixSocketAddress.getPath();
        }
        return unixSocketAddress.copy(str);
    }

    public final String component1() {
        return getPath();
    }

    public final UnixSocketAddress copy(String path) {
        return new UnixSocketAddress(path);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return UnixSocketAddress.class.equals(other != null ? other.getClass() : null) && p.a(getAddress(), ((UnixSocketAddress) other).getAddress());
    }

    @Override // io.ktor.network.sockets.SocketAddress
    /* JADX INFO: renamed from: getAddress$ktor_network, reason: from getter */
    public java.net.SocketAddress getAddress() {
        return this.address;
    }

    public final String getPath() {
        return Companion.checkSupportForUnixDomainSockets().getMethod("getPath", null).invoke(getAddress(), null).toString();
    }

    public int hashCode() {
        return getAddress().hashCode();
    }

    public String toString() {
        return getAddress().toString();
    }

    public UnixSocketAddress(String str) {
        this((java.net.SocketAddress) Companion.checkSupportForUnixDomainSockets().getMethod("of", String.class).invoke(null, str));
    }
}
