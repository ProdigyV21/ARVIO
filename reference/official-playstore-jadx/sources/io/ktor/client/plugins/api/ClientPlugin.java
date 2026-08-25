package io.ktor.client.plugins.api;

import io.ktor.client.plugins.HttpClientPlugin;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00040\u0003¨\u0006\u0005"}, d2 = {"Lio/ktor/client/plugins/api/ClientPlugin;", "PluginConfig", "", "Lio/ktor/client/plugins/HttpClientPlugin;", "Lio/ktor/client/plugins/api/ClientPluginInstance;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ClientPlugin<PluginConfig> extends HttpClientPlugin<PluginConfig, ClientPluginInstance<PluginConfig>> {
}
