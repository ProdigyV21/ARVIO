package io.ktor.server.engine;

import java.io.File;
import java.security.KeyStore;
import java.util.List;
import kotlin.Metadata;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0019\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0005\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0016\u0010\t\u001a\u0004\u0018\u00010\u00068&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\r\u001a\u00020\n8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u0016\u0010\u0016\u001a\u0004\u0018\u00010\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0004R\u0016\u0010\u0018\u001a\u0004\u0018\u00010\u00068&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\bR\u001c\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00198&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001d"}, d2 = {"Lio/ktor/server/engine/EngineSSLConnectorConfig;", "Lio/ktor/server/engine/EngineConnectorConfig;", "Ljava/security/KeyStore;", "getKeyStore", "()Ljava/security/KeyStore;", "keyStore", "Ljava/io/File;", "getKeyStorePath", "()Ljava/io/File;", "keyStorePath", "", "getKeyAlias", "()Ljava/lang/String;", "keyAlias", "Lkotlin/Function0;", "", "getKeyStorePassword", "()Lr7/a;", "keyStorePassword", "getPrivateKeyPassword", "privateKeyPassword", "getTrustStore", "trustStore", "getTrustStorePath", "trustStorePath", "", "getEnabledProtocols", "()Ljava/util/List;", "enabledProtocols", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface EngineSSLConnectorConfig extends EngineConnectorConfig {
    List<String> getEnabledProtocols();

    String getKeyAlias();

    KeyStore getKeyStore();

    a<char[]> getKeyStorePassword();

    File getKeyStorePath();

    a<char[]> getPrivateKeyPassword();

    KeyStore getTrustStore();

    File getTrustStorePath();
}
