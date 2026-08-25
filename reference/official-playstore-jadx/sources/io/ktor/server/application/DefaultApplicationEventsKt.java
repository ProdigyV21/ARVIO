package io.ktor.server.application;

import io.ktor.events.EventDefinition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0004\"\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0004\"\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0004\"\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0004\"\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0004¨\u0006\u0010"}, d2 = {"ApplicationStarted", "Lio/ktor/events/EventDefinition;", "Lio/ktor/server/application/Application;", "getApplicationStarted", "()Lio/ktor/events/EventDefinition;", "ApplicationStarting", "getApplicationStarting", "ApplicationStopPreparing", "Lio/ktor/server/application/ApplicationEnvironment;", "getApplicationStopPreparing", "ApplicationStopped", "getApplicationStopped", "ApplicationStopping", "getApplicationStopping", "ServerReady", "getServerReady", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DefaultApplicationEventsKt {
    private static final EventDefinition<Application> ApplicationStarting = new EventDefinition<>();
    private static final EventDefinition<Application> ApplicationStarted = new EventDefinition<>();
    private static final EventDefinition<ApplicationEnvironment> ServerReady = new EventDefinition<>();
    private static final EventDefinition<ApplicationEnvironment> ApplicationStopPreparing = new EventDefinition<>();
    private static final EventDefinition<Application> ApplicationStopping = new EventDefinition<>();
    private static final EventDefinition<Application> ApplicationStopped = new EventDefinition<>();

    public static final EventDefinition<Application> getApplicationStarted() {
        return ApplicationStarted;
    }

    public static final EventDefinition<Application> getApplicationStarting() {
        return ApplicationStarting;
    }

    public static final EventDefinition<ApplicationEnvironment> getApplicationStopPreparing() {
        return ApplicationStopPreparing;
    }

    public static final EventDefinition<Application> getApplicationStopped() {
        return ApplicationStopped;
    }

    public static final EventDefinition<Application> getApplicationStopping() {
        return ApplicationStopping;
    }

    public static final EventDefinition<ApplicationEnvironment> getServerReady() {
        return ServerReady;
    }
}
