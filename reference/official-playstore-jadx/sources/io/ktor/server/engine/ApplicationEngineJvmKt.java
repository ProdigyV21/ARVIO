package io.ktor.server.engine;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a)\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lio/ktor/server/engine/ApplicationEngine;", "", "gracePeriod", "timeout", "Ljava/util/concurrent/TimeUnit;", "timeUnit", "Lx6/t0;", "stop", "(Lio/ktor/server/engine/ApplicationEngine;JJLjava/util/concurrent/TimeUnit;)V", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ApplicationEngineJvmKt {
    public static final void stop(ApplicationEngine applicationEngine, long j10, long j11, TimeUnit timeUnit) {
        applicationEngine.stop(timeUnit.toMillis(j10), timeUnit.toMillis(j11));
    }
}
