package io.ktor.server.engine;

import androidx.media3.common.MimeTypes;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.server.application.Application;
import io.ktor.server.application.ApplicationEnvironment;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0004R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068&X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\u000e\u001a\u00020\u000b8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Lio/ktor/server/engine/ApplicationEngineEnvironment;", "Lio/ktor/server/application/ApplicationEnvironment;", "Lx6/t0;", TtmlNode.START, "()V", "stop", "", "Lio/ktor/server/engine/EngineConnectorConfig;", "getConnectors", "()Ljava/util/List;", "connectors", "Lio/ktor/server/application/Application;", "getApplication", "()Lio/ktor/server/application/Application;", MimeTypes.BASE_TYPE_APPLICATION, "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ApplicationEngineEnvironment extends ApplicationEnvironment {
    Application getApplication();

    List<EngineConnectorConfig> getConnectors();

    void start();

    void stop();
}
