package io.ktor.client.plugins.api;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.plugins.HttpClientPluginKt;
import io.ktor.client.plugins.HttpSend;
import io.ktor.client.request.HttpRequestBuilder;
import k2.c;
import kotlin.Metadata;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002/\u0012+\u0012)\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0002¢\u0006\u0002\b\b0\u0001:\u0001\u0011B\t\b\u0002¢\u0006\u0004\b\t\u0010\nJI\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u000b2-\u0010\r\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0002¢\u0006\u0002\b\bH\u0016ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lio/ktor/client/plugins/api/Send;", "Lio/ktor/client/plugins/api/ClientHook;", "Lkotlin/Function3;", "Lio/ktor/client/plugins/api/Send$Sender;", "Lio/ktor/client/request/HttpRequestBuilder;", "Ld7/d;", "Lio/ktor/client/call/HttpClientCall;", "", "Lx6/n;", "<init>", "()V", "Lio/ktor/client/HttpClient;", "client", "handler", "Lx6/t0;", "install", "(Lio/ktor/client/HttpClient;Lr7/q;)V", "Sender", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Send implements ClientHook<q<? super Sender, ? super HttpRequestBuilder, ? super d<? super HttpClientCall>, ? extends Object>> {
    public static final Send INSTANCE = new Send();

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lio/ktor/client/plugins/api/Send$Sender;", "", "Lio/ktor/client/plugins/Sender;", "httpSendSender", "<init>", "(Lio/ktor/client/plugins/Sender;)V", "Lio/ktor/client/request/HttpRequestBuilder;", "requestBuilder", "Lio/ktor/client/call/HttpClientCall;", "proceed", "(Lio/ktor/client/request/HttpRequestBuilder;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/client/plugins/Sender;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Sender {
        private final io.ktor.client.plugins.Sender httpSendSender;

        public Sender(io.ktor.client.plugins.Sender sender) {
            this.httpSendSender = sender;
        }

        public final Object proceed(HttpRequestBuilder httpRequestBuilder, d<? super HttpClientCall> dVar) {
            return this.httpSendSender.execute(httpRequestBuilder, dVar);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.api.Send$install$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "Lio/ktor/client/call/HttpClientCall;", "Lio/ktor/client/plugins/Sender;", "request", "Lio/ktor/client/request/HttpRequestBuilder;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.plugins.api.Send$install$1", f = "CommonHooks.kt", l = {41}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements q<io.ktor.client.plugins.Sender, HttpRequestBuilder, d<? super HttpClientCall>, Object> {
        final /* synthetic */ q<Sender, HttpRequestBuilder, d<? super HttpClientCall>, Object> $handler;
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(q<? super Sender, ? super HttpRequestBuilder, ? super d<? super HttpClientCall>, ? extends Object> qVar, d<? super AnonymousClass1> dVar) {
            super(3, dVar);
            this.$handler = qVar;
        }

        @Override // r7.q
        public final Object invoke(io.ktor.client.plugins.Sender sender, HttpRequestBuilder httpRequestBuilder, d<? super HttpClientCall> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$handler, dVar);
            anonymousClass1.L$0 = sender;
            anonymousClass1.L$1 = httpRequestBuilder;
            return anonymousClass1.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
                return obj;
            }
            c.G(obj);
            io.ktor.client.plugins.Sender sender = (io.ktor.client.plugins.Sender) this.L$0;
            HttpRequestBuilder httpRequestBuilder = (HttpRequestBuilder) this.L$1;
            q<Sender, HttpRequestBuilder, d<? super HttpClientCall>, Object> qVar = this.$handler;
            Sender sender2 = new Sender(sender);
            this.L$0 = null;
            this.label = 1;
            Object objInvoke = qVar.invoke(sender2, httpRequestBuilder, this);
            a aVar = a.f15033i;
            return objInvoke == aVar ? aVar : objInvoke;
        }
    }

    private Send() {
    }

    @Override // io.ktor.client.plugins.api.ClientHook
    public void install(HttpClient client, q<? super Sender, ? super HttpRequestBuilder, ? super d<? super HttpClientCall>, ? extends Object> handler) {
        ((HttpSend) HttpClientPluginKt.plugin(client, HttpSend.INSTANCE)).intercept(new AnonymousClass1(handler, null));
    }
}
