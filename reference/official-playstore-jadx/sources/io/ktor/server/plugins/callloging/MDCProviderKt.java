package io.ktor.server.plugins.callloging;

import io.ktor.server.application.ApplicationPluginKt;
import io.ktor.server.application.PluginBuilder;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0019\u0010\u0003\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\u0000¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lio/ktor/server/application/PluginBuilder;", "Lio/ktor/server/plugins/callloging/CallLoggingConfig;", "Lx6/t0;", "setupMDCProvider", "(Lio/ktor/server/application/PluginBuilder;)V", "ktor-server-call-logging"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MDCProviderKt {
    public static final void setupMDCProvider(PluginBuilder<CallLoggingConfig> pluginBuilder) {
        ApplicationPluginKt.getPluginRegistry(pluginBuilder.getApplication()).put(KtorMDCProvider.INSTANCE.getKey(), new KtorMDCProvider(pluginBuilder.getPluginConfig().getMdcEntries$ktor_server_call_logging()));
    }
}
