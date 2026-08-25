package io.ktor.server.http.content;

import d7.d;
import f7.e;
import f7.j;
import io.ktor.http.ContentType;
import io.ktor.http.Headers;
import io.ktor.http.HttpMethod;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.content.OutgoingContent;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.RouteScopedPluginBuilder;
import io.ktor.server.application.hooks.ResponseBodyReadyForSend;
import io.ktor.util.AttributeKey;
import k2.c;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/application/RouteScopedPluginBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/server/application/RouteScopedPluginBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class StaticContentKt$StaticContentAutoHead$1 extends r implements l<RouteScopedPluginBuilder<t0>, t0> {
    public static final StaticContentKt$StaticContentAutoHead$1 INSTANCE = new StaticContentKt$StaticContentAutoHead$1();

    /* JADX INFO: renamed from: io.ktor.server.http.content.StaticContentKt$StaticContentAutoHead$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"Lio/ktor/server/application/hooks/ResponseBodyReadyForSend$Context;", "Lio/ktor/server/application/ApplicationCall;", "call", "Lio/ktor/http/content/OutgoingContent;", "content", "Lx6/t0;", "<anonymous>", "(Lio/ktor/server/application/hooks/ResponseBodyReadyForSend$Context;Lio/ktor/server/application/ApplicationCall;Lio/ktor/http/content/OutgoingContent;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.http.content.StaticContentKt$StaticContentAutoHead$1$1", f = "StaticContent.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements r7.r<ResponseBodyReadyForSend.Context, ApplicationCall, OutgoingContent, d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        /* synthetic */ Object L$2;
        int label;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(4, dVar);
        }

        @Override // r7.r
        public final Object invoke(ResponseBodyReadyForSend.Context context, ApplicationCall applicationCall, OutgoingContent outgoingContent, d<? super t0> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(dVar);
            anonymousClass1.L$0 = context;
            anonymousClass1.L$1 = applicationCall;
            anonymousClass1.L$2 = outgoingContent;
            return anonymousClass1.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            ResponseBodyReadyForSend.Context context = (ResponseBodyReadyForSend.Context) this.L$0;
            ApplicationCall applicationCall = (ApplicationCall) this.L$1;
            OutgoingContent outgoingContent = (OutgoingContent) this.L$2;
            if (!p.a(applicationCall.getRequest().getLocal().getMethod(), HttpMethod.INSTANCE.getHead())) {
                throw new IllegalStateException("Check failed.");
            }
            if (outgoingContent instanceof OutgoingContent.ReadChannelContent) {
                ((OutgoingContent.ReadChannelContent) outgoingContent).readFrom().cancel(null);
            }
            context.transformBodyTo(new HeadResponse(outgoingContent));
            return t0.f22605a;
        }
    }

    @Metadata(d1 = {"\u0000G\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J)\u0010\n\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0007*\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016¢\u0006\u0004\b\n\u0010\u000bJ1\u0010\u000e\u001a\u00020\r\"\b\b\u0000\u0010\u0007*\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b2\b\u0010\f\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0016\u001a\u0004\u0018\u00010\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u001a\u001a\u0004\u0018\u00010\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001e\u001a\u0004\u0018\u00010\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\"\u001a\u00020\u001f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010!¨\u0006#"}, d2 = {"io/ktor/server/http/content/StaticContentKt$StaticContentAutoHead$1$HeadResponse", "Lio/ktor/http/content/OutgoingContent$NoContent;", "Lio/ktor/http/content/OutgoingContent;", "original", "<init>", "(Lio/ktor/http/content/OutgoingContent;)V", "", "T", "Lio/ktor/util/AttributeKey;", "key", "getProperty", "(Lio/ktor/util/AttributeKey;)Ljava/lang/Object;", "value", "Lx6/t0;", "setProperty", "(Lio/ktor/util/AttributeKey;Ljava/lang/Object;)V", "Lio/ktor/http/content/OutgoingContent;", "getOriginal", "()Lio/ktor/http/content/OutgoingContent;", "Lio/ktor/http/HttpStatusCode;", "getStatus", "()Lio/ktor/http/HttpStatusCode;", "status", "Lio/ktor/http/ContentType;", "getContentType", "()Lio/ktor/http/ContentType;", "contentType", "", "getContentLength", "()Ljava/lang/Long;", "contentLength", "Lio/ktor/http/Headers;", "getHeaders", "()Lio/ktor/http/Headers;", "headers", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class HeadResponse extends OutgoingContent.NoContent {
        private final OutgoingContent original;

        public HeadResponse(OutgoingContent outgoingContent) {
            this.original = outgoingContent;
        }

        @Override // io.ktor.http.content.OutgoingContent
        public Long getContentLength() {
            return this.original.getContentLength();
        }

        @Override // io.ktor.http.content.OutgoingContent
        public ContentType getContentType() {
            return this.original.getContentType();
        }

        @Override // io.ktor.http.content.OutgoingContent
        public Headers getHeaders() {
            return this.original.getHeaders();
        }

        public final OutgoingContent getOriginal() {
            return this.original;
        }

        @Override // io.ktor.http.content.OutgoingContent
        public <T> T getProperty(AttributeKey<T> key) {
            return (T) this.original.getProperty(key);
        }

        @Override // io.ktor.http.content.OutgoingContent
        /* JADX INFO: renamed from: getStatus */
        public HttpStatusCode getValue() {
            return this.original.getValue();
        }

        @Override // io.ktor.http.content.OutgoingContent
        public <T> void setProperty(AttributeKey<T> key, T value) {
            this.original.setProperty(key, value);
        }
    }

    public StaticContentKt$StaticContentAutoHead$1() {
        super(1);
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((RouteScopedPluginBuilder<t0>) obj);
        return t0.f22605a;
    }

    public final void invoke(RouteScopedPluginBuilder<t0> routeScopedPluginBuilder) {
        routeScopedPluginBuilder.on(ResponseBodyReadyForSend.INSTANCE, new AnonymousClass1(null));
    }
}
