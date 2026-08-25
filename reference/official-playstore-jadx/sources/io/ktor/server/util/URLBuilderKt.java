package io.ktor.server.util;

import io.ktor.http.RequestConnectionPoint;
import io.ktor.http.URLBuilder;
import io.ktor.http.URLProtocol;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.plugins.OriginConnectionPointKt;
import io.ktor.server.request.ApplicationRequestPropertiesKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a\u0019\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a&\u0010\u000b\u001a\u00020\n2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\b¢\u0006\u0004\b\u000b\u0010\f\u001a2\u0010\u000b\u001a\u00020\n*\u00020\u00012\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0086\bø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\r\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000e"}, d2 = {"Lio/ktor/http/URLBuilder$Companion;", "Lio/ktor/server/application/ApplicationCall;", "call", "Lio/ktor/http/URLBuilder;", "createFromCall", "(Lio/ktor/http/URLBuilder$Companion;Lio/ktor/server/application/ApplicationCall;)Lio/ktor/http/URLBuilder;", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "block", "", "url", "(Lr7/l;)Ljava/lang/String;", "(Lio/ktor/server/application/ApplicationCall;Lr7/l;)Ljava/lang/String;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class URLBuilderKt {

    /* JADX INFO: renamed from: io.ktor.server.util.URLBuilderKt$url$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/http/URLBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/http/URLBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<URLBuilder, t0> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        public final void invoke(URLBuilder uRLBuilder) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((URLBuilder) obj);
            return t0.f22605a;
        }
    }

    public static final URLBuilder createFromCall(URLBuilder.Companion companion, ApplicationCall applicationCall) {
        RequestConnectionPoint origin = OriginConnectionPointKt.getOrigin(applicationCall.getRequest());
        URLBuilder uRLBuilder = new URLBuilder(null, null, 0, null, null, null, null, null, false, 511, null);
        URLProtocol uRLProtocol = URLProtocol.INSTANCE.getByName().get(origin.getScheme());
        if (uRLProtocol == null) {
            uRLProtocol = new URLProtocol(origin.getScheme(), 0);
        }
        uRLBuilder.setProtocol(uRLProtocol);
        uRLBuilder.setHost(origin.getServerHost());
        uRLBuilder.setPort(origin.getServerPort());
        io.ktor.http.URLBuilderKt.setEncodedPath(uRLBuilder, ApplicationRequestPropertiesKt.path(applicationCall.getRequest()));
        uRLBuilder.getParameters().appendAll(applicationCall.getRequest().getQueryParameters());
        return uRLBuilder;
    }

    public static final String url(l<? super URLBuilder, t0> lVar) {
        URLBuilder uRLBuilder = new URLBuilder(null, null, 0, null, null, null, null, null, false, 511, null);
        lVar.invoke(uRLBuilder);
        return uRLBuilder.buildString();
    }

    public static /* synthetic */ String url$default(ApplicationCall applicationCall, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            lVar = AnonymousClass1.INSTANCE;
        }
        URLBuilder uRLBuilderCreateFromCall = createFromCall(URLBuilder.INSTANCE, applicationCall);
        lVar.invoke(uRLBuilderCreateFromCall);
        return uRLBuilderCreateFromCall.buildString();
    }

    public static final String url(ApplicationCall applicationCall, l<? super URLBuilder, t0> lVar) {
        URLBuilder uRLBuilderCreateFromCall = createFromCall(URLBuilder.INSTANCE, applicationCall);
        lVar.invoke(uRLBuilderCreateFromCall);
        return uRLBuilderCreateFromCall.buildString();
    }
}
