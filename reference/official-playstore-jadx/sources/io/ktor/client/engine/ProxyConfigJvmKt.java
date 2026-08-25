package io.ktor.client.engine;

import io.ktor.http.LinkHeader;
import java.net.Proxy;
import java.net.SocketAddress;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0006\u001a\u00060\u0007j\u0002`\b*\u00060\u0002j\u0002`\u0003\"\u0019\u0010\u0000\u001a\u00020\u0001*\u00060\u0002j\u0002`\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005*\n\u0010\t\"\u00020\u00022\u00020\u0002¨\u0006\n"}, d2 = {LinkHeader.Parameters.Type, "Lio/ktor/client/engine/ProxyType;", "Ljava/net/Proxy;", "Lio/ktor/client/engine/ProxyConfig;", "getType", "(Ljava/net/Proxy;)Lio/ktor/client/engine/ProxyType;", "resolveAddress", "Ljava/net/SocketAddress;", "Lio/ktor/util/network/NetworkAddress;", "ProxyConfig", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ProxyConfigJvmKt {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Proxy.Type.values().length];
            try {
                iArr[Proxy.Type.SOCKS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Proxy.Type.HTTP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final ProxyType getType(Proxy proxy) {
        Proxy.Type type = proxy.type();
        int i10 = type == null ? -1 : WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
        return i10 != 1 ? i10 != 2 ? ProxyType.UNKNOWN : ProxyType.HTTP : ProxyType.SOCKS;
    }

    public static final SocketAddress resolveAddress(Proxy proxy) {
        return proxy.address();
    }
}
