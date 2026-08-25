package io.ktor.client.plugins.api;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.client.HttpClient;
import io.ktor.http.ContentDisposition;
import io.ktor.util.AttributeKey;
import io.ktor.util.InternalAPI;
import java.io.Closeable;
import java.util.Iterator;
import kotlin.Metadata;
import r7.a;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00060\u0003j\u0002`\u0004B8\b\u0000\u0012\u0006\u0010\u0005\u001a\u00028\u0000\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u001d\u0010\f\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0005\u001a\u00028\u00008\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0007\u001a\u00020\u00068\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0007\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR1\u0010\f\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000b8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\f\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001c\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\n0\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010 ¨\u0006!"}, d2 = {"Lio/ktor/client/plugins/api/ClientPluginInstance;", "", "PluginConfig", "Ljava/io/Closeable;", "Lio/ktor/utils/io/core/Closeable;", "config", "", ContentDisposition.Parameters.Name, "Lkotlin/Function1;", "Lio/ktor/client/plugins/api/ClientPluginBuilder;", "Lx6/t0;", "Lx6/n;", TtmlNode.TAG_BODY, "<init>", "(Ljava/lang/Object;Ljava/lang/String;Lr7/l;)V", "Lio/ktor/client/HttpClient;", "scope", "install", "(Lio/ktor/client/HttpClient;)V", "close", "()V", "Ljava/lang/Object;", "getConfig$ktor_client_core", "()Ljava/lang/Object;", "Ljava/lang/String;", "getName$ktor_client_core", "()Ljava/lang/String;", "Lr7/l;", "getBody$ktor_client_core", "()Lr7/l;", "Lkotlin/Function0;", "onClose", "Lr7/a;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ClientPluginInstance<PluginConfig> implements Closeable {
    private final l<ClientPluginBuilder<PluginConfig>, t0> body;
    private final PluginConfig config;
    private final String name;
    private a<t0> onClose = ClientPluginInstance$onClose$1.INSTANCE;

    /* JADX WARN: Multi-variable type inference failed */
    public ClientPluginInstance(PluginConfig pluginconfig, String str, l<? super ClientPluginBuilder<PluginConfig>, t0> lVar) {
        this.config = pluginconfig;
        this.name = str;
        this.body = lVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.onClose.invoke();
    }

    public final l<ClientPluginBuilder<PluginConfig>, t0> getBody$ktor_client_core() {
        return this.body;
    }

    public final PluginConfig getConfig$ktor_client_core() {
        return this.config;
    }

    /* JADX INFO: renamed from: getName$ktor_client_core, reason: from getter */
    public final String getName() {
        return this.name;
    }

    @InternalAPI
    public final void install(HttpClient scope) {
        ClientPluginBuilder clientPluginBuilder = new ClientPluginBuilder(new AttributeKey(this.name), scope, this.config);
        this.body.invoke(clientPluginBuilder);
        this.onClose = clientPluginBuilder.getOnClose$ktor_client_core();
        Iterator<T> it = clientPluginBuilder.getHooks$ktor_client_core().iterator();
        while (it.hasNext()) {
            ((HookHandler) it.next()).install(scope);
        }
    }
}
