package io.ktor.client.plugins.api;

import d7.d;
import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.statement.HttpResponse;
import io.ktor.http.ContentDisposition;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.AttributeKey;
import io.ktor.util.KtorDsl;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.utils.io.ByteReadChannel;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;
import r7.q;
import r7.s;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@KtorDsl
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0001B-\b\u0000\u0012\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00028\u0000¢\u0006\u0004\b\t\u0010\nJc\u0010\u0016\u001a\u00020\u00132Q\u0010\u0015\u001aM\b\u0001\u0012\u0004\u0012\u00020\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000b¢\u0006\u0002\b\u0014ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017JN\u0010\u001c\u001a\u00020\u00132<\u0010\u0015\u001a8\b\u0001\u0012\u0004\u0012\u00020\u0019\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0018¢\u0006\u0002\b\u0014ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ|\u0010#\u001a\u00020\u00132j\u0010\u0015\u001af\b\u0001\u0012\u0004\u0012\u00020\u001f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0011\u0012\u0015\u0012\u0013\u0018\u00010 ¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(!\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\"0\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001e¢\u0006\u0002\b\u0014ø\u0001\u0000¢\u0006\u0004\b#\u0010$Jz\u0010(\u001a\u00020\u00132h\u0010\u0015\u001ad\b\u0001\u0012\u0004\u0012\u00020%\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110&¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110 ¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b('\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001e¢\u0006\u0002\b\u0014ø\u0001\u0000¢\u0006\u0004\b(\u0010$J\u001b\u0010*\u001a\u00020\u00132\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00130)¢\u0006\u0004\b*\u0010+J)\u00100\u001a\u00020\u0013\"\u0004\b\u0001\u0010,2\f\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00010-2\u0006\u0010/\u001a\u00028\u0001¢\u0006\u0004\b0\u00101R&\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u00038\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0005\u00102\u001a\u0004\b3\u00104R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u00105\u001a\u0004\b6\u00107R\u0017\u0010\b\u001a\u00028\u00008\u0006¢\u0006\f\n\u0004\b\b\u00108\u001a\u0004\b9\u0010:R$\u0010=\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030<0;8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b=\u0010>\u001a\u0004\b?\u0010@R(\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00130)8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b*\u0010A\u001a\u0004\bB\u0010C\"\u0004\bD\u0010+\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006E"}, d2 = {"Lio/ktor/client/plugins/api/ClientPluginBuilder;", "", "PluginConfig", "Lio/ktor/util/AttributeKey;", "Lio/ktor/client/plugins/api/ClientPluginInstance;", "key", "Lio/ktor/client/HttpClient;", "client", "pluginConfig", "<init>", "(Lio/ktor/util/AttributeKey;Lio/ktor/client/HttpClient;Ljava/lang/Object;)V", "Lkotlin/Function4;", "Lio/ktor/client/plugins/api/OnRequestContext;", "Lio/ktor/client/request/HttpRequestBuilder;", "Lx6/y;", ContentDisposition.Parameters.Name, "request", "content", "Ld7/d;", "Lx6/t0;", "Lx6/n;", "block", "onRequest", "(Lr7/r;)V", "Lkotlin/Function3;", "Lio/ktor/client/plugins/api/OnResponseContext;", "Lio/ktor/client/statement/HttpResponse;", "response", "onResponse", "(Lr7/q;)V", "Lkotlin/Function5;", "Lio/ktor/client/plugins/api/TransformRequestBodyContext;", "Lio/ktor/util/reflect/TypeInfo;", "bodyType", "Lio/ktor/http/content/OutgoingContent;", "transformRequestBody", "(Lr7/s;)V", "Lio/ktor/client/plugins/api/TransformResponseBodyContext;", "Lio/ktor/utils/io/ByteReadChannel;", "requestedType", "transformResponseBody", "Lkotlin/Function0;", "onClose", "(Lr7/a;)V", "HookHandler", "Lio/ktor/client/plugins/api/ClientHook;", "hook", "handler", "on", "(Lio/ktor/client/plugins/api/ClientHook;Ljava/lang/Object;)V", "Lio/ktor/util/AttributeKey;", "getKey$ktor_client_core", "()Lio/ktor/util/AttributeKey;", "Lio/ktor/client/HttpClient;", "getClient", "()Lio/ktor/client/HttpClient;", "Ljava/lang/Object;", "getPluginConfig", "()Ljava/lang/Object;", "", "Lio/ktor/client/plugins/api/HookHandler;", "hooks", "Ljava/util/List;", "getHooks$ktor_client_core", "()Ljava/util/List;", "Lr7/a;", "getOnClose$ktor_client_core", "()Lr7/a;", "setOnClose$ktor_client_core", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ClientPluginBuilder<PluginConfig> {
    private final HttpClient client;
    private final AttributeKey<ClientPluginInstance<PluginConfig>> key;
    private final PluginConfig pluginConfig;
    private final List<HookHandler<?>> hooks = new ArrayList();
    private a<t0> onClose = AnonymousClass1.INSTANCE;

    /* JADX INFO: renamed from: io.ktor.client.plugins.api.ClientPluginBuilder$onClose$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\b\b\u0000\u0010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "PluginConfig", "Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements a<t0> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(0);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m6665invoke() {
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m6665invoke();
            return t0.f22605a;
        }
    }

    public ClientPluginBuilder(AttributeKey<ClientPluginInstance<PluginConfig>> attributeKey, HttpClient httpClient, PluginConfig pluginconfig) {
        this.key = attributeKey;
        this.client = httpClient;
        this.pluginConfig = pluginconfig;
    }

    public final HttpClient getClient() {
        return this.client;
    }

    public final List<HookHandler<?>> getHooks$ktor_client_core() {
        return this.hooks;
    }

    public final AttributeKey<ClientPluginInstance<PluginConfig>> getKey$ktor_client_core() {
        return this.key;
    }

    public final a<t0> getOnClose$ktor_client_core() {
        return this.onClose;
    }

    public final PluginConfig getPluginConfig() {
        return this.pluginConfig;
    }

    public final <HookHandler> void on(ClientHook<HookHandler> hook, HookHandler handler) {
        this.hooks.add(new HookHandler<>(hook, handler));
    }

    public final void onClose(a<t0> block) {
        this.onClose = block;
    }

    public final void onRequest(r7.r<? super OnRequestContext, ? super HttpRequestBuilder, Object, ? super d<? super t0>, ? extends Object> block) {
        on(RequestHook.INSTANCE, block);
    }

    public final void onResponse(q<? super OnResponseContext, ? super HttpResponse, ? super d<? super t0>, ? extends Object> block) {
        on(ResponseHook.INSTANCE, block);
    }

    public final void setOnClose$ktor_client_core(a<t0> aVar) {
        this.onClose = aVar;
    }

    public final void transformRequestBody(s<? super TransformRequestBodyContext, ? super HttpRequestBuilder, Object, ? super TypeInfo, ? super d<? super OutgoingContent>, ? extends Object> block) {
        on(TransformRequestBodyHook.INSTANCE, block);
    }

    public final void transformResponseBody(s<? super TransformResponseBodyContext, ? super HttpResponse, ? super ByteReadChannel, ? super TypeInfo, ? super d<Object>, ? extends Object> block) {
        on(TransformResponseBodyHook.INSTANCE, block);
    }
}
