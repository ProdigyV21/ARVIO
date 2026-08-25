package io.ktor.client;

import io.ktor.client.engine.HttpClientEngineFactory;
import java.util.List;
import java.util.ServiceLoader;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a,\u0010\u0006\u001a\u00020\u00052\u001d\b\u0002\u0010\u0004\u001a\u0017\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0001\u0012\u0004\u0012\u00020\u00020\u0000¢\u0006\u0002\b\u0003¢\u0006\u0004\b\u0006\u0010\u0007\"\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b\"\u0018\u0010\r\u001a\u0006\u0012\u0002\b\u00030\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lkotlin/Function1;", "Lio/ktor/client/HttpClientConfig;", "Lx6/t0;", "Lx6/n;", "block", "Lio/ktor/client/HttpClient;", "HttpClient", "(Lr7/l;)Lio/ktor/client/HttpClient;", "", "Lio/ktor/client/HttpClientEngineContainer;", "engines", "Ljava/util/List;", "Lio/ktor/client/engine/HttpClientEngineFactory;", "FACTORY", "Lio/ktor/client/engine/HttpClientEngineFactory;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpClientJvmKt {
    private static final HttpClientEngineFactory<?> FACTORY;
    private static final List<HttpClientEngineContainer> engines;

    /* JADX INFO: renamed from: io.ktor.client.HttpClientJvmKt$HttpClient$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements l<HttpClientConfig<?>, t0> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        public final void invoke(HttpClientConfig<?> httpClientConfig) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((HttpClientConfig<?>) obj);
            return t0.f22605a;
        }
    }

    static {
        HttpClientEngineFactory<?> factory;
        List<HttpClientEngineContainer> listC1 = x.c1(ServiceLoader.load(HttpClientEngineContainer.class, HttpClientEngineContainer.class.getClassLoader()));
        engines = listC1;
        HttpClientEngineContainer httpClientEngineContainer = (HttpClientEngineContainer) x.o0(listC1);
        if (httpClientEngineContainer == null || (factory = httpClientEngineContainer.getFactory()) == null) {
            throw new IllegalStateException("Failed to find HTTP client engine implementation in the classpath: consider adding client engine dependency. See https://ktor.io/docs/http-client-engines.html");
        }
        FACTORY = factory;
    }

    public static final HttpClient HttpClient(l<? super HttpClientConfig<?>, t0> lVar) {
        return HttpClientKt.HttpClient(FACTORY, lVar);
    }

    public static /* synthetic */ HttpClient HttpClient$default(l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            lVar = AnonymousClass1.INSTANCE;
        }
        return HttpClient(lVar);
    }
}
