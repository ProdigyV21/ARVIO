package io.ktor.server.application;

import io.ktor.server.routing.Route;
import io.ktor.util.AttributeKey;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007R\u0014\u0010\b\u001a\u0004\u0018\u00010\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lio/ktor/server/application/RouteScopedPluginBuilder;", "PluginConfig", "", "Lio/ktor/server/application/PluginBuilder;", "key", "Lio/ktor/util/AttributeKey;", "Lio/ktor/server/application/PluginInstance;", "(Lio/ktor/util/AttributeKey;)V", "route", "Lio/ktor/server/routing/Route;", "getRoute", "()Lio/ktor/server/routing/Route;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class RouteScopedPluginBuilder<PluginConfig> extends PluginBuilder<PluginConfig> {
    public RouteScopedPluginBuilder(AttributeKey<PluginInstance> attributeKey) {
        super(attributeKey);
    }

    public abstract Route getRoute();
}
