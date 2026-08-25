package io.ktor.client;

import io.ktor.client.engine.HttpClientEngine;
import io.ktor.client.engine.HttpClientEngineConfig;
import io.ktor.client.engine.HttpClientEngineFactory;
import io.ktor.util.KtorDsl;
import java.io.IOException;
import ka.u1;
import ka.v1;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aH\u0010\n\u001a\u00020\t\"\b\b\u0000\u0010\u0001*\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u001f\b\u0002\u0010\b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0004\b\n\u0010\u000b\u001a4\u0010\n\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\f2\u001b\u0010\b\u001a\u0017\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007H\u0007¢\u0006\u0004\b\n\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/client/engine/HttpClientEngineConfig;", "T", "Lio/ktor/client/engine/HttpClientEngineFactory;", "engineFactory", "Lkotlin/Function1;", "Lio/ktor/client/HttpClientConfig;", "Lx6/t0;", "Lx6/n;", "block", "Lio/ktor/client/HttpClient;", "HttpClient", "(Lio/ktor/client/engine/HttpClientEngineFactory;Lr7/l;)Lio/ktor/client/HttpClient;", "Lio/ktor/client/engine/HttpClientEngine;", "engine", "(Lio/ktor/client/engine/HttpClientEngine;Lr7/l;)Lio/ktor/client/HttpClient;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpClientKt {

    /* JADX INFO: renamed from: io.ktor.client.HttpClientKt$HttpClient$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0002H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lio/ktor/client/engine/HttpClientEngineConfig;", "T", "Lio/ktor/client/HttpClientConfig;", "Lx6/t0;", "invoke", "(Lio/ktor/client/HttpClientConfig;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        public final void invoke(HttpClientConfig httpClientConfig) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpClientConfig) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.HttpClientKt$HttpClient$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004\"\b\b\u0000\u0010\u0001*\u00020\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/client/engine/HttpClientEngineConfig;", "T", "", "it", "Lx6/t0;", "invoke", "(Ljava/lang/Throwable;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<Throwable, t0> {
        final /* synthetic */ HttpClientEngine $engine;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(HttpClientEngine httpClientEngine) {
            super(1);
            this.$engine = httpClientEngine;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) throws IOException {
            invoke((Throwable) obj);
            return t0.f22605a;
        }

        public final void invoke(Throwable th) throws IOException {
            this.$engine.close();
        }
    }

    @KtorDsl
    public static final <T extends HttpClientEngineConfig> HttpClient HttpClient(HttpClientEngineFactory<? extends T> httpClientEngineFactory, l<? super HttpClientConfig<T>, t0> lVar) {
        HttpClientConfig httpClientConfig = new HttpClientConfig();
        lVar.invoke(httpClientConfig);
        HttpClientEngine httpClientEngineCreate = httpClientEngineFactory.create(httpClientConfig.getEngineConfig$ktor_client_core());
        HttpClient httpClient = new HttpClient(httpClientEngineCreate, httpClientConfig, true);
        ((v1) httpClient.getCoroutineContext().get(u1.f19642i)).invokeOnCompletion(new AnonymousClass2(httpClientEngineCreate));
        return httpClient;
    }

    public static /* synthetic */ HttpClient HttpClient$default(HttpClientEngineFactory httpClientEngineFactory, l lVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar = AnonymousClass1.INSTANCE;
        }
        return HttpClient(httpClientEngineFactory, lVar);
    }

    @KtorDsl
    public static final HttpClient HttpClient(HttpClientEngine httpClientEngine, l<? super HttpClientConfig<?>, t0> lVar) {
        HttpClientConfig httpClientConfig = new HttpClientConfig();
        lVar.invoke(httpClientConfig);
        return new HttpClient(httpClientEngine, httpClientConfig, false);
    }
}
