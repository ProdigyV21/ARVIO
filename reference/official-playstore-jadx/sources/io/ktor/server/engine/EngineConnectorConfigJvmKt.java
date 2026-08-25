package io.ktor.server.engine;

import java.io.File;
import java.security.KeyStore;
import java.util.List;
import kotlin.Metadata;
import r7.a;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u001a`\u0010\u000e\u001a\u00020\u000b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\u000e\b\b\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\b\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0017\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\fH\u0086\bø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0019\u0010\u0013\u001a\u00020\u0010*\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0015"}, d2 = {"Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;", "Ljava/security/KeyStore;", "keyStore", "", "keyAlias", "Lkotlin/Function0;", "", "keyStorePassword", "privateKeyPassword", "Lkotlin/Function1;", "Lio/ktor/server/engine/EngineSSLConnectorBuilder;", "Lx6/t0;", "Lx6/n;", "builder", "sslConnector", "(Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;Ljava/security/KeyStore;Ljava/lang/String;Lr7/a;Lr7/a;Lr7/l;)V", "Lio/ktor/server/engine/EngineConnectorConfig;", "", "otherPort", "withPort", "(Lio/ktor/server/engine/EngineConnectorConfig;I)Lio/ktor/server/engine/EngineConnectorConfig;", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EngineConnectorConfigJvmKt {
    public static final void sslConnector(ApplicationEngineEnvironmentBuilder applicationEngineEnvironmentBuilder, KeyStore keyStore, String str, a<char[]> aVar, a<char[]> aVar2, l<? super EngineSSLConnectorBuilder, t0> lVar) {
        List<EngineConnectorConfig> connectors = applicationEngineEnvironmentBuilder.getConnectors();
        EngineSSLConnectorBuilder engineSSLConnectorBuilder = new EngineSSLConnectorBuilder(keyStore, str, aVar, aVar2);
        lVar.invoke(engineSSLConnectorBuilder);
        connectors.add(engineSSLConnectorBuilder);
    }

    public static final EngineConnectorConfig withPort(EngineConnectorConfig engineConnectorConfig, int i10) {
        return engineConnectorConfig instanceof EngineSSLConnectorBuilder ? new EngineSSLConnectorConfig(engineConnectorConfig, i10) { // from class: io.ktor.server.engine.EngineConnectorConfigJvmKt.withPort.1
            private final /* synthetic */ EngineSSLConnectorBuilder $$delegate_0;
            private final int port;

            {
                this.$$delegate_0 = (EngineSSLConnectorBuilder) engineConnectorConfig;
                this.port = i10;
            }

            @Override // io.ktor.server.engine.EngineSSLConnectorConfig
            public List<String> getEnabledProtocols() {
                return this.$$delegate_0.getEnabledProtocols();
            }

            @Override // io.ktor.server.engine.EngineConnectorConfig
            public String getHost() {
                return this.$$delegate_0.getHost();
            }

            @Override // io.ktor.server.engine.EngineSSLConnectorConfig
            public String getKeyAlias() {
                return this.$$delegate_0.getKeyAlias();
            }

            @Override // io.ktor.server.engine.EngineSSLConnectorConfig
            public KeyStore getKeyStore() {
                return this.$$delegate_0.getKeyStore();
            }

            @Override // io.ktor.server.engine.EngineSSLConnectorConfig
            public a<char[]> getKeyStorePassword() {
                return this.$$delegate_0.getKeyStorePassword();
            }

            @Override // io.ktor.server.engine.EngineSSLConnectorConfig
            public File getKeyStorePath() {
                return this.$$delegate_0.getKeyStorePath();
            }

            @Override // io.ktor.server.engine.EngineConnectorConfig
            public int getPort() {
                return this.port;
            }

            @Override // io.ktor.server.engine.EngineSSLConnectorConfig
            public a<char[]> getPrivateKeyPassword() {
                return this.$$delegate_0.getPrivateKeyPassword();
            }

            @Override // io.ktor.server.engine.EngineSSLConnectorConfig
            public KeyStore getTrustStore() {
                return this.$$delegate_0.getTrustStore();
            }

            @Override // io.ktor.server.engine.EngineSSLConnectorConfig
            public File getTrustStorePath() {
                return this.$$delegate_0.getTrustStorePath();
            }

            @Override // io.ktor.server.engine.EngineConnectorConfig
            public ConnectorType getType() {
                return this.$$delegate_0.getType();
            }
        } : new EngineConnectorConfig(i10) { // from class: io.ktor.server.engine.EngineConnectorConfigJvmKt.withPort.2
            private final int port;

            {
                this.port = i10;
            }

            @Override // io.ktor.server.engine.EngineConnectorConfig
            public String getHost() {
                return this.$$delegate_0.getHost();
            }

            @Override // io.ktor.server.engine.EngineConnectorConfig
            public int getPort() {
                return this.port;
            }

            @Override // io.ktor.server.engine.EngineConnectorConfig
            public ConnectorType getType() {
                return this.$$delegate_0.getType();
            }
        };
    }
}
