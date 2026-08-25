package io.ktor.server.engine;

import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0013\u001a\u00020\u0006H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Lio/ktor/server/engine/EngineConnectorBuilder;", "Lio/ktor/server/engine/EngineConnectorConfig;", LinkHeader.Parameters.Type, "Lio/ktor/server/engine/ConnectorType;", "(Lio/ktor/server/engine/ConnectorType;)V", "host", "", "getHost", "()Ljava/lang/String;", "setHost", "(Ljava/lang/String;)V", "port", "", "getPort", "()I", "setPort", "(I)V", "getType", "()Lio/ktor/server/engine/ConnectorType;", "toString", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class EngineConnectorBuilder implements EngineConnectorConfig {
    private String host;
    private int port;
    private final ConnectorType type;

    /* JADX WARN: Multi-variable type inference failed */
    public EngineConnectorBuilder() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // io.ktor.server.engine.EngineConnectorConfig
    public String getHost() {
        return this.host;
    }

    @Override // io.ktor.server.engine.EngineConnectorConfig
    public int getPort() {
        return this.port;
    }

    @Override // io.ktor.server.engine.EngineConnectorConfig
    public ConnectorType getType() {
        return this.type;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public void setPort(int i10) {
        this.port = i10;
    }

    public String toString() {
        return getType().getName() + ' ' + getHost() + ':' + getPort();
    }

    public EngineConnectorBuilder(ConnectorType connectorType) {
        this.type = connectorType;
        this.host = "0.0.0.0";
        this.port = 80;
    }

    public /* synthetic */ EngineConnectorBuilder(ConnectorType connectorType, int i10, h hVar) {
        this((i10 & 1) != 0 ? ConnectorType.INSTANCE.getHTTP() : connectorType);
    }
}
