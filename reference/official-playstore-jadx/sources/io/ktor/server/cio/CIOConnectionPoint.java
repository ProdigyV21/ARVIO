package io.ktor.server.cio;

import io.ktor.http.HttpMethod;
import io.ktor.http.RequestConnectionPoint;
import io.ktor.http.URLProtocol;
import io.ktor.util.network.NetworkAddressJvmKt;
import java.net.SocketAddress;
import kotlin.Metadata;
import kotlin.text.o;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b \b\u0000\u0018\u00002\u00020\u0001BG\u0012\u000e\u0010\u0002\u001a\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u0004\u0012\u000e\u0010\u0005\u001a\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u00020\u00078VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u0014\u0010\u0016\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0013R\u0016\u0010\u0005\u001a\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u000e8VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u001e\u0010\u0011\u001a\u0004\b\u001f\u0010\u001aR\u0014\u0010 \u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u0013R\u0014\u0010\"\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u0013R\u0016\u0010\u0002\u001a\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010$\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010\u001aR\u0014\u0010&\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b'\u0010\u0013R\u0014\u0010(\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b)\u0010\u0013R\u0014\u0010*\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b+\u0010\u001aR\u0014\u0010\b\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0013R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0013¨\u0006."}, d2 = {"Lio/ktor/server/cio/CIOConnectionPoint;", "Lio/ktor/http/RequestConnectionPoint;", "remoteNetworkAddress", "Ljava/net/SocketAddress;", "Lio/ktor/util/network/NetworkAddress;", "localNetworkAddress", "version", "", "uri", "hostHeaderValue", "method", "Lio/ktor/http/HttpMethod;", "(Ljava/net/SocketAddress;Ljava/net/SocketAddress;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lio/ktor/http/HttpMethod;)V", "defaultPort", "", "host", "getHost$annotations", "()V", "getHost", "()Ljava/lang/String;", "localAddress", "getLocalAddress", "localHost", "getLocalHost", "localPort", "getLocalPort", "()I", "getMethod", "()Lio/ktor/http/HttpMethod;", "port", "getPort$annotations", "getPort", "remoteAddress", "getRemoteAddress", "remoteHost", "getRemoteHost", "remotePort", "getRemotePort", "scheme", "getScheme", "serverHost", "getServerHost", "serverPort", "getServerPort", "getUri", "getVersion", "ktor-server-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CIOConnectionPoint implements RequestConnectionPoint {
    private final int defaultPort = URLProtocol.INSTANCE.createOrDefault(getScheme()).getDefaultPort();
    private final String hostHeaderValue;
    private final SocketAddress localNetworkAddress;
    private final HttpMethod method;
    private final SocketAddress remoteNetworkAddress;
    private final String uri;
    private final String version;

    public CIOConnectionPoint(SocketAddress socketAddress, SocketAddress socketAddress2, String str, String str2, String str3, HttpMethod httpMethod) {
        this.remoteNetworkAddress = socketAddress;
        this.localNetworkAddress = socketAddress2;
        this.version = str;
        this.uri = str2;
        this.hostHeaderValue = str3;
        this.method = httpMethod;
    }

    @e
    public static /* synthetic */ void getHost$annotations() {
    }

    @e
    public static /* synthetic */ void getPort$annotations() {
    }

    @Override // io.ktor.http.RequestConnectionPoint
    public String getHost() {
        String hostname;
        SocketAddress socketAddress = this.localNetworkAddress;
        if (socketAddress != null && (hostname = NetworkAddressJvmKt.getHostname(socketAddress)) != null) {
            return hostname;
        }
        String str = this.hostHeaderValue;
        return str != null ? o.H0(str, ":") : "localhost";
    }

    @Override // io.ktor.http.RequestConnectionPoint
    public String getLocalAddress() {
        String address;
        SocketAddress socketAddress = this.localNetworkAddress;
        return (socketAddress == null || (address = NetworkAddressJvmKt.getAddress(socketAddress)) == null) ? "localhost" : address;
    }

    @Override // io.ktor.http.RequestConnectionPoint
    public String getLocalHost() {
        String hostname;
        SocketAddress socketAddress = this.localNetworkAddress;
        return (socketAddress == null || (hostname = NetworkAddressJvmKt.getHostname(socketAddress)) == null) ? "localhost" : hostname;
    }

    @Override // io.ktor.http.RequestConnectionPoint
    public int getLocalPort() {
        SocketAddress socketAddress = this.localNetworkAddress;
        return socketAddress != null ? NetworkAddressJvmKt.getPort(socketAddress) : this.defaultPort;
    }

    @Override // io.ktor.http.RequestConnectionPoint
    public HttpMethod getMethod() {
        return this.method;
    }

    @Override // io.ktor.http.RequestConnectionPoint
    public int getPort() {
        SocketAddress socketAddress = this.localNetworkAddress;
        if (socketAddress != null) {
            return NetworkAddressJvmKt.getPort(socketAddress);
        }
        String str = this.hostHeaderValue;
        if (str != null) {
            return Integer.parseInt(o.D0(str, ":", "80"));
        }
        return 80;
    }

    @Override // io.ktor.http.RequestConnectionPoint
    public String getRemoteAddress() {
        String address;
        SocketAddress socketAddress = this.remoteNetworkAddress;
        return (socketAddress == null || (address = NetworkAddressJvmKt.getAddress(socketAddress)) == null) ? "unknown" : address;
    }

    @Override // io.ktor.http.RequestConnectionPoint
    public String getRemoteHost() {
        String hostname;
        SocketAddress socketAddress = this.remoteNetworkAddress;
        return (socketAddress == null || (hostname = NetworkAddressJvmKt.getHostname(socketAddress)) == null) ? "unknown" : hostname;
    }

    @Override // io.ktor.http.RequestConnectionPoint
    public int getRemotePort() {
        SocketAddress socketAddress = this.remoteNetworkAddress;
        if (socketAddress != null) {
            return NetworkAddressJvmKt.getPort(socketAddress);
        }
        return 0;
    }

    @Override // io.ktor.http.RequestConnectionPoint
    public String getScheme() {
        return "http";
    }

    @Override // io.ktor.http.RequestConnectionPoint
    public String getServerHost() {
        String str = this.hostHeaderValue;
        return str != null ? o.H0(str, ":") : getLocalHost();
    }

    @Override // io.ktor.http.RequestConnectionPoint
    public int getServerPort() {
        String str = this.hostHeaderValue;
        return str != null ? Integer.parseInt(o.D0(str, ":", String.valueOf(this.defaultPort))) : getLocalPort();
    }

    @Override // io.ktor.http.RequestConnectionPoint
    public String getUri() {
        return this.uri;
    }

    @Override // io.ktor.http.RequestConnectionPoint
    public String getVersion() {
        return this.version;
    }
}
