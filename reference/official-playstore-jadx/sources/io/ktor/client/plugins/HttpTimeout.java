package io.ktor.client.plugins;

import io.ktor.client.HttpClient;
import io.ktor.client.engine.HttpClientEngineCapability;
import io.ktor.util.AttributeKey;
import io.ktor.util.KtorDsl;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u000b2\u00020\u0001:\u0002\n\u000bB%\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\b\u0010\b\u001a\u00020\tH\u0002R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\f"}, d2 = {"Lio/ktor/client/plugins/HttpTimeout;", "", "requestTimeoutMillis", "", "connectTimeoutMillis", "socketTimeoutMillis", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;)V", "Ljava/lang/Long;", "hasNotNullTimeouts", "", "HttpTimeoutCapabilityConfiguration", "Plugin", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HttpTimeout {
    public static final long INFINITE_TIMEOUT_MS = Long.MAX_VALUE;

    /* JADX INFO: renamed from: Plugin, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AttributeKey<HttpTimeout> key = new AttributeKey<>("TimeoutPlugin");
    private final Long connectTimeoutMillis;
    private final Long requestTimeoutMillis;
    private final Long socketTimeoutMillis;

    @KtorDsl
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB+\b\u0016\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\r\u0010\u0014\u001a\u00020\u0015H\u0000¢\u0006\u0002\b\u0016J\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0002¢\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0016R\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u0012\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR(\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u00038F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR(\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u00038F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR(\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u00038F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000f¨\u0006\u001f"}, d2 = {"Lio/ktor/client/plugins/HttpTimeout$HttpTimeoutCapabilityConfiguration;", "", "requestTimeoutMillis", "", "connectTimeoutMillis", "socketTimeoutMillis", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;)V", "_connectTimeoutMillis", "Ljava/lang/Long;", "_requestTimeoutMillis", "_socketTimeoutMillis", "value", "getConnectTimeoutMillis", "()Ljava/lang/Long;", "setConnectTimeoutMillis", "(Ljava/lang/Long;)V", "getRequestTimeoutMillis", "setRequestTimeoutMillis", "getSocketTimeoutMillis", "setSocketTimeoutMillis", "build", "Lio/ktor/client/plugins/HttpTimeout;", "build$ktor_client_core", "checkTimeoutValue", "(Ljava/lang/Long;)Ljava/lang/Long;", "equals", "", "other", "hashCode", "", "Companion", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class HttpTimeoutCapabilityConfiguration {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final AttributeKey<HttpTimeoutCapabilityConfiguration> key = new AttributeKey<>("TimeoutConfiguration");
        private Long _connectTimeoutMillis;
        private Long _requestTimeoutMillis;
        private Long _socketTimeoutMillis;

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/ktor/client/plugins/HttpTimeout$HttpTimeoutCapabilityConfiguration$Companion;", "", "()V", "key", "Lio/ktor/util/AttributeKey;", "Lio/ktor/client/plugins/HttpTimeout$HttpTimeoutCapabilityConfiguration;", "getKey", "()Lio/ktor/util/AttributeKey;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(h hVar) {
                this();
            }

            public final AttributeKey<HttpTimeoutCapabilityConfiguration> getKey() {
                return HttpTimeoutCapabilityConfiguration.key;
            }

            private Companion() {
            }
        }

        public /* synthetic */ HttpTimeoutCapabilityConfiguration(Long l10, Long l11, Long l12, int i10, h hVar) {
            this((i10 & 1) != 0 ? null : l10, (i10 & 2) != 0 ? null : l11, (i10 & 4) != 0 ? null : l12);
        }

        private final Long checkTimeoutValue(Long value) {
            if (value == null || value.longValue() > 0) {
                return value;
            }
            throw new IllegalArgumentException("Only positive timeout values are allowed, for infinite timeout use HttpTimeout.INFINITE_TIMEOUT_MS");
        }

        public final HttpTimeout build$ktor_client_core() {
            return new HttpTimeout(get_requestTimeoutMillis(), get_connectTimeoutMillis(), get_socketTimeoutMillis(), null);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other == null || HttpTimeoutCapabilityConfiguration.class != other.getClass()) {
                return false;
            }
            HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration = (HttpTimeoutCapabilityConfiguration) other;
            return p.a(this._requestTimeoutMillis, httpTimeoutCapabilityConfiguration._requestTimeoutMillis) && p.a(this._connectTimeoutMillis, httpTimeoutCapabilityConfiguration._connectTimeoutMillis) && p.a(this._socketTimeoutMillis, httpTimeoutCapabilityConfiguration._socketTimeoutMillis);
        }

        /* JADX INFO: renamed from: getConnectTimeoutMillis, reason: from getter */
        public final Long get_connectTimeoutMillis() {
            return this._connectTimeoutMillis;
        }

        /* JADX INFO: renamed from: getRequestTimeoutMillis, reason: from getter */
        public final Long get_requestTimeoutMillis() {
            return this._requestTimeoutMillis;
        }

        /* JADX INFO: renamed from: getSocketTimeoutMillis, reason: from getter */
        public final Long get_socketTimeoutMillis() {
            return this._socketTimeoutMillis;
        }

        public int hashCode() {
            Long l10 = this._requestTimeoutMillis;
            int iHashCode = (l10 != null ? l10.hashCode() : 0) * 31;
            Long l11 = this._connectTimeoutMillis;
            int iHashCode2 = (iHashCode + (l11 != null ? l11.hashCode() : 0)) * 31;
            Long l12 = this._socketTimeoutMillis;
            return iHashCode2 + (l12 != null ? l12.hashCode() : 0);
        }

        public final void setConnectTimeoutMillis(Long l10) {
            this._connectTimeoutMillis = checkTimeoutValue(l10);
        }

        public final void setRequestTimeoutMillis(Long l10) {
            this._requestTimeoutMillis = checkTimeoutValue(l10);
        }

        public final void setSocketTimeoutMillis(Long l10) {
            this._socketTimeoutMillis = checkTimeoutValue(l10);
        }

        public HttpTimeoutCapabilityConfiguration(Long l10, Long l11, Long l12) {
            this._requestTimeoutMillis = 0L;
            this._connectTimeoutMillis = 0L;
            this._socketTimeoutMillis = 0L;
            setRequestTimeoutMillis(l10);
            setConnectTimeoutMillis(l11);
            setSocketTimeoutMillis(l12);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.HttpTimeout$Plugin, reason: from kotlin metadata */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\b\u0012\u0004\u0012\u00020\u00020\u0004B\t\b\u0002¢\u0006\u0004\b\u0005\u0010\u0006J(\u0010\u000b\u001a\u00020\u00032\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\b\tH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u0010\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0010\u0010\u0011R \u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u00128\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00178\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lio/ktor/client/plugins/HttpTimeout$Plugin;", "Lio/ktor/client/plugins/HttpClientPlugin;", "Lio/ktor/client/plugins/HttpTimeout$HttpTimeoutCapabilityConfiguration;", "Lio/ktor/client/plugins/HttpTimeout;", "Lio/ktor/client/engine/HttpClientEngineCapability;", "<init>", "()V", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "block", "prepare", "(Lr7/l;)Lio/ktor/client/plugins/HttpTimeout;", "plugin", "Lio/ktor/client/HttpClient;", "scope", "install", "(Lio/ktor/client/plugins/HttpTimeout;Lio/ktor/client/HttpClient;)V", "Lio/ktor/util/AttributeKey;", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "", "INFINITE_TIMEOUT_MS", "J", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion implements HttpClientPlugin<HttpTimeoutCapabilityConfiguration, HttpTimeout>, HttpClientEngineCapability<HttpTimeoutCapabilityConfiguration> {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public AttributeKey<HttpTimeout> getKey() {
            return HttpTimeout.key;
        }

        private Companion() {
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public void install(HttpTimeout plugin, HttpClient scope) {
            ((HttpSend) HttpClientPluginKt.plugin(scope, HttpSend.INSTANCE)).intercept(new HttpTimeout$Plugin$install$1(plugin, scope, null));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.ktor.client.plugins.HttpClientPlugin
        public HttpTimeout prepare(l<? super HttpTimeoutCapabilityConfiguration, t0> block) {
            HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration = new HttpTimeoutCapabilityConfiguration(null, null, null, 7, null);
            block.invoke(httpTimeoutCapabilityConfiguration);
            return httpTimeoutCapabilityConfiguration.build$ktor_client_core();
        }
    }

    public /* synthetic */ HttpTimeout(Long l10, Long l11, Long l12, h hVar) {
        this(l10, l11, l12);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean hasNotNullTimeouts() {
        return (this.requestTimeoutMillis == null && this.connectTimeoutMillis == null && this.socketTimeoutMillis == null) ? false : true;
    }

    private HttpTimeout(Long l10, Long l11, Long l12) {
        this.requestTimeoutMillis = l10;
        this.connectTimeoutMillis = l11;
        this.socketTimeoutMillis = l12;
    }
}
