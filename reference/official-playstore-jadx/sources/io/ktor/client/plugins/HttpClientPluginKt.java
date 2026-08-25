package io.ktor.client.plugins;

import io.ktor.client.HttpClient;
import io.ktor.util.AttributeKey;
import io.ktor.util.Attributes;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a7\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0007*\u00020\b\"\b\b\u0001\u0010\u0006*\u00020\b*\u00020\t2\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u00060\n¢\u0006\u0002\u0010\u000b\u001a9\u0010\f\u001a\u0004\u0018\u0001H\u0006\"\b\b\u0000\u0010\u0007*\u00020\b\"\b\b\u0001\u0010\u0006*\u00020\b*\u00020\t2\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u00060\n¢\u0006\u0002\u0010\u000b\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\r"}, d2 = {"PLUGIN_INSTALLED_LIST", "Lio/ktor/util/AttributeKey;", "Lio/ktor/util/Attributes;", "getPLUGIN_INSTALLED_LIST", "()Lio/ktor/util/AttributeKey;", "plugin", "F", "B", "", "Lio/ktor/client/HttpClient;", "Lio/ktor/client/plugins/HttpClientPlugin;", "(Lio/ktor/client/HttpClient;Lio/ktor/client/plugins/HttpClientPlugin;)Ljava/lang/Object;", "pluginOrNull", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpClientPluginKt {
    private static final AttributeKey<Attributes> PLUGIN_INSTALLED_LIST = new AttributeKey<>("ApplicationPluginRegistry");

    public static final AttributeKey<Attributes> getPLUGIN_INSTALLED_LIST() {
        return PLUGIN_INSTALLED_LIST;
    }

    public static final <B, F> F plugin(HttpClient httpClient, HttpClientPlugin<? extends B, F> httpClientPlugin) {
        F f10 = (F) pluginOrNull(httpClient, httpClientPlugin);
        if (f10 != null) {
            return f10;
        }
        throw new IllegalStateException("Plugin " + httpClientPlugin + " is not installed. Consider using `install(" + httpClientPlugin.getKey() + ")` in client config first.");
    }

    public static final <B, F> F pluginOrNull(HttpClient httpClient, HttpClientPlugin<? extends B, F> httpClientPlugin) {
        Attributes attributes = (Attributes) httpClient.getAttributes().getOrNull(PLUGIN_INSTALLED_LIST);
        if (attributes != null) {
            return (F) attributes.getOrNull(httpClientPlugin.getKey());
        }
        return null;
    }
}
