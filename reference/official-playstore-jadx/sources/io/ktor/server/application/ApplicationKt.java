package io.ktor.server.application;

import kotlin.Metadata;
import vc.b;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\"\u0019\u0010\u0005\u001a\u00060\u0001j\u0002`\u0002*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0006"}, d2 = {"Lio/ktor/server/application/Application;", "Lvc/b;", "Lio/ktor/util/logging/Logger;", "getLog", "(Lio/ktor/server/application/Application;)Lvc/b;", "log", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ApplicationKt {
    public static final b getLog(Application application) {
        return application.getEnvironment().getLog();
    }
}
