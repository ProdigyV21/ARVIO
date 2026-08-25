package io.ktor.client.plugins.observer;

import d7.d;
import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.plugins.HttpClientPlugin;
import io.ktor.client.statement.HttpReceivePipeline;
import io.ktor.client.statement.HttpResponse;
import io.ktor.util.AttributeKey;
import io.ktor.util.KtorDsl;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u0000 \u000f2\u00020\u0001:\u0002\u0010\u000fBF\u0012\"\u0010\u0006\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0002\u0012\u0016\b\u0002\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fR3\u0010\u0006\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00028\u0002X\u0082\u0004ø\u0001\u0000¢\u0006\u0006\n\u0004\b\u0006\u0010\rR\"\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lio/ktor/client/plugins/observer/ResponseObserver;", "", "Lkotlin/Function2;", "Lio/ktor/client/statement/HttpResponse;", "Ld7/d;", "Lx6/t0;", "responseHandler", "Lkotlin/Function1;", "Lio/ktor/client/call/HttpClientCall;", "", "filter", "<init>", "(Lr7/p;Lr7/l;)V", "Lr7/p;", "Lr7/l;", "Plugin", "Config", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ResponseObserver {

    /* JADX INFO: renamed from: Plugin, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AttributeKey<ResponseObserver> key = new AttributeKey<>("BodyInterceptor");
    private final l<HttpClientCall, Boolean> filter;
    private final p<HttpResponse, d<? super t0>, Object> responseHandler;

    @KtorDsl
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J4\u0010\t\u001a\u00020\u00072\"\u0010\b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ!\u0010\u000e\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0004\b\u000e\u0010\u000fRA\u0010\u0010\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00048\u0000@\u0000X\u0080\u000eø\u0001\u0000¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\nR0\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u000b8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"Lio/ktor/client/plugins/observer/ResponseObserver$Config;", "", "<init>", "()V", "Lkotlin/Function2;", "Lio/ktor/client/statement/HttpResponse;", "Ld7/d;", "Lx6/t0;", "block", "onResponse", "(Lr7/p;)V", "Lkotlin/Function1;", "Lio/ktor/client/call/HttpClientCall;", "", "filter", "(Lr7/l;)V", "responseHandler", "Lr7/p;", "getResponseHandler$ktor_client_core", "()Lr7/p;", "setResponseHandler$ktor_client_core", "Lr7/l;", "getFilter$ktor_client_core", "()Lr7/l;", "setFilter$ktor_client_core", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Config {
        private l<? super HttpClientCall, Boolean> filter;
        private p<? super HttpResponse, ? super d<? super t0>, ? extends Object> responseHandler = new ResponseObserver$Config$responseHandler$1(null);

        public final void filter(l<? super HttpClientCall, Boolean> block) {
            this.filter = block;
        }

        public final l<HttpClientCall, Boolean> getFilter$ktor_client_core() {
            return this.filter;
        }

        public final p<HttpResponse, d<? super t0>, Object> getResponseHandler$ktor_client_core() {
            return this.responseHandler;
        }

        public final void onResponse(p<? super HttpResponse, ? super d<? super t0>, ? extends Object> block) {
            this.responseHandler = block;
        }

        public final void setFilter$ktor_client_core(l<? super HttpClientCall, Boolean> lVar) {
            this.filter = lVar;
        }

        public final void setResponseHandler$ktor_client_core(p<? super HttpResponse, ? super d<? super t0>, ? extends Object> pVar) {
            this.responseHandler = pVar;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.observer.ResponseObserver$Plugin, reason: from kotlin metadata */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J(\u0010\n\u001a\u00020\u00032\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000f\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u00118\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lio/ktor/client/plugins/observer/ResponseObserver$Plugin;", "Lio/ktor/client/plugins/HttpClientPlugin;", "Lio/ktor/client/plugins/observer/ResponseObserver$Config;", "Lio/ktor/client/plugins/observer/ResponseObserver;", "<init>", "()V", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "block", "prepare", "(Lr7/l;)Lio/ktor/client/plugins/observer/ResponseObserver;", "plugin", "Lio/ktor/client/HttpClient;", "scope", "install", "(Lio/ktor/client/plugins/observer/ResponseObserver;Lio/ktor/client/HttpClient;)V", "Lio/ktor/util/AttributeKey;", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion implements HttpClientPlugin<Config, ResponseObserver> {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public AttributeKey<ResponseObserver> getKey() {
            return ResponseObserver.key;
        }

        private Companion() {
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public void install(ResponseObserver plugin, HttpClient scope) {
            scope.getReceivePipeline().intercept(HttpReceivePipeline.INSTANCE.getAfter(), new ResponseObserver$Plugin$install$1(plugin, scope, null));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.ktor.client.plugins.HttpClientPlugin
        public ResponseObserver prepare(l<? super Config, t0> block) {
            Config config = new Config();
            block.invoke(config);
            return new ResponseObserver(config.getResponseHandler$ktor_client_core(), config.getFilter$ktor_client_core());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ResponseObserver(p<? super HttpResponse, ? super d<? super t0>, ? extends Object> pVar, l<? super HttpClientCall, Boolean> lVar) {
        this.responseHandler = pVar;
        this.filter = lVar;
    }

    public /* synthetic */ ResponseObserver(p pVar, l lVar, int i10, h hVar) {
        this(pVar, (i10 & 2) != 0 ? null : lVar);
    }
}
