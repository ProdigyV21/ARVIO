package io.ktor.server.application;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\b\u0000\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004R\u0018\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/server/application/PluginInstance;", "", "builder", "Lio/ktor/server/application/PluginBuilder;", "(Lio/ktor/server/application/PluginBuilder;)V", "getBuilder$ktor_server_core", "()Lio/ktor/server/application/PluginBuilder;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PluginInstance {
    private final PluginBuilder<?> builder;

    public PluginInstance(PluginBuilder<?> pluginBuilder) {
        this.builder = pluginBuilder;
    }

    public final PluginBuilder<?> getBuilder$ktor_server_core() {
        return this.builder;
    }
}
