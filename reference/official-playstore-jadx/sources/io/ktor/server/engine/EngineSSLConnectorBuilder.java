package io.ktor.server.engine;

import java.io.File;
import java.security.KeyStore;
import java.util.List;
import kotlin.Metadata;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0019\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B3\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\u000b\u0010\fR\"\u0010\u0004\u001a\u00020\u00038\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\"\u0010\u0006\u001a\u00020\u00058\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R(\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\t\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR(\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u0017\u001a\u0004\b\u001c\u0010\u0019\"\u0004\b\u001d\u0010\u001bR$\u0010\u001f\u001a\u0004\u0018\u00010\u001e8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R$\u0010%\u001a\u0004\u0018\u00010\u00038\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b%\u0010\r\u001a\u0004\b&\u0010\u000f\"\u0004\b'\u0010\u0011R$\u0010(\u001a\u0004\u0018\u00010\u001e8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b(\u0010 \u001a\u0004\b)\u0010\"\"\u0004\b*\u0010$R\"\u0010,\u001a\u00020+8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R*\u00103\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u0001028\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b3\u00104\u001a\u0004\b5\u00106\"\u0004\b7\u00108¨\u00069"}, d2 = {"Lio/ktor/server/engine/EngineSSLConnectorBuilder;", "Lio/ktor/server/engine/EngineConnectorBuilder;", "Lio/ktor/server/engine/EngineSSLConnectorConfig;", "Ljava/security/KeyStore;", "keyStore", "", "keyAlias", "Lkotlin/Function0;", "", "keyStorePassword", "privateKeyPassword", "<init>", "(Ljava/security/KeyStore;Ljava/lang/String;Lr7/a;Lr7/a;)V", "Ljava/security/KeyStore;", "getKeyStore", "()Ljava/security/KeyStore;", "setKeyStore", "(Ljava/security/KeyStore;)V", "Ljava/lang/String;", "getKeyAlias", "()Ljava/lang/String;", "setKeyAlias", "(Ljava/lang/String;)V", "Lr7/a;", "getKeyStorePassword", "()Lr7/a;", "setKeyStorePassword", "(Lr7/a;)V", "getPrivateKeyPassword", "setPrivateKeyPassword", "Ljava/io/File;", "keyStorePath", "Ljava/io/File;", "getKeyStorePath", "()Ljava/io/File;", "setKeyStorePath", "(Ljava/io/File;)V", "trustStore", "getTrustStore", "setTrustStore", "trustStorePath", "getTrustStorePath", "setTrustStorePath", "", "port", "I", "getPort", "()I", "setPort", "(I)V", "", "enabledProtocols", "Ljava/util/List;", "getEnabledProtocols", "()Ljava/util/List;", "setEnabledProtocols", "(Ljava/util/List;)V", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class EngineSSLConnectorBuilder extends EngineConnectorBuilder implements EngineSSLConnectorConfig {
    private List<String> enabledProtocols;
    private String keyAlias;
    private KeyStore keyStore;
    private a<char[]> keyStorePassword;
    private File keyStorePath;
    private int port;
    private a<char[]> privateKeyPassword;
    private KeyStore trustStore;
    private File trustStorePath;

    public EngineSSLConnectorBuilder(KeyStore keyStore, String str, a<char[]> aVar, a<char[]> aVar2) {
        super(ConnectorType.INSTANCE.getHTTPS());
        this.keyStore = keyStore;
        this.keyAlias = str;
        this.keyStorePassword = aVar;
        this.privateKeyPassword = aVar2;
        this.port = 443;
    }

    @Override // io.ktor.server.engine.EngineSSLConnectorConfig
    public List<String> getEnabledProtocols() {
        return this.enabledProtocols;
    }

    @Override // io.ktor.server.engine.EngineSSLConnectorConfig
    public String getKeyAlias() {
        return this.keyAlias;
    }

    @Override // io.ktor.server.engine.EngineSSLConnectorConfig
    public KeyStore getKeyStore() {
        return this.keyStore;
    }

    @Override // io.ktor.server.engine.EngineSSLConnectorConfig
    public a<char[]> getKeyStorePassword() {
        return this.keyStorePassword;
    }

    @Override // io.ktor.server.engine.EngineSSLConnectorConfig
    public File getKeyStorePath() {
        return this.keyStorePath;
    }

    @Override // io.ktor.server.engine.EngineConnectorBuilder, io.ktor.server.engine.EngineConnectorConfig
    public int getPort() {
        return this.port;
    }

    @Override // io.ktor.server.engine.EngineSSLConnectorConfig
    public a<char[]> getPrivateKeyPassword() {
        return this.privateKeyPassword;
    }

    @Override // io.ktor.server.engine.EngineSSLConnectorConfig
    public KeyStore getTrustStore() {
        return this.trustStore;
    }

    @Override // io.ktor.server.engine.EngineSSLConnectorConfig
    public File getTrustStorePath() {
        return this.trustStorePath;
    }

    public void setEnabledProtocols(List<String> list) {
        this.enabledProtocols = list;
    }

    public void setKeyAlias(String str) {
        this.keyAlias = str;
    }

    public void setKeyStore(KeyStore keyStore) {
        this.keyStore = keyStore;
    }

    public void setKeyStorePassword(a<char[]> aVar) {
        this.keyStorePassword = aVar;
    }

    public void setKeyStorePath(File file) {
        this.keyStorePath = file;
    }

    @Override // io.ktor.server.engine.EngineConnectorBuilder
    public void setPort(int i10) {
        this.port = i10;
    }

    public void setPrivateKeyPassword(a<char[]> aVar) {
        this.privateKeyPassword = aVar;
    }

    public void setTrustStore(KeyStore keyStore) {
        this.trustStore = keyStore;
    }

    public void setTrustStorePath(File file) {
        this.trustStorePath = file;
    }
}
