package io.ktor.client.plugins.observer;

import d7.d;
import io.ktor.client.HttpClientConfig;
import io.ktor.client.plugins.observer.ResponseObserver;
import io.ktor.client.statement.HttpResponse;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u001a<\u0010\u0007\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\u00002\"\u0010\u0006\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0001ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b*B\u0010\t\"\u001e\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00012\u001e\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0001\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lio/ktor/client/HttpClientConfig;", "Lkotlin/Function2;", "Lio/ktor/client/statement/HttpResponse;", "Ld7/d;", "Lx6/t0;", "", "block", "ResponseObserver", "(Lio/ktor/client/HttpClientConfig;Lr7/p;)V", "ResponseHandler", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ResponseObserverKt {

    /* JADX INFO: renamed from: io.ktor.client.plugins.observer.ResponseObserverKt$ResponseObserver$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/plugins/observer/ResponseObserver$Config;", "Lx6/t0;", "invoke", "(Lio/ktor/client/plugins/observer/ResponseObserver$Config;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<ResponseObserver.Config, t0> {
        final /* synthetic */ p<HttpResponse, d<? super t0>, Object> $block;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(p<? super HttpResponse, ? super d<? super t0>, ? extends Object> pVar) {
            super(1);
            this.$block = pVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ResponseObserver.Config) obj);
            return t0.f22605a;
        }

        public final void invoke(ResponseObserver.Config config) {
            config.setResponseHandler$ktor_client_core(this.$block);
        }
    }

    public static final void ResponseObserver(HttpClientConfig<?> httpClientConfig, p<? super HttpResponse, ? super d<? super t0>, ? extends Object> pVar) {
        httpClientConfig.install(ResponseObserver.INSTANCE, new AnonymousClass1(pVar));
    }
}
