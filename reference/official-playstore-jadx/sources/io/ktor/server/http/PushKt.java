package io.ktor.server.http;

import io.ktor.http.Parameters;
import io.ktor.http.QueryKt;
import io.ktor.http.URLBuilderKt;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.response.ApplicationResponse;
import io.ktor.server.response.DefaultResponsePushBuilder;
import io.ktor.server.response.ResponsePushBuilder;
import io.ktor.server.response.UseHttp2Push;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import kotlin.text.o;
import r7.l;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001b\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\u0004\u0010\u0005\u001a#\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0007H\u0007¢\u0006\u0004\b\u0004\u0010\t\u001a,\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0017\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\n¢\u0006\u0002\b\fH\u0007¢\u0006\u0004\b\u0004\u0010\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/server/application/ApplicationCall;", "", "pathAndQuery", "Lx6/t0;", "push", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/String;)V", "encodedPath", "Lio/ktor/http/Parameters;", "encodedParameters", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/String;Lio/ktor/http/Parameters;)V", "Lkotlin/Function1;", "Lio/ktor/server/response/ResponsePushBuilder;", "Lx6/n;", "block", "(Lio/ktor/server/application/ApplicationCall;Lr7/l;)V", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PushKt {

    /* JADX INFO: renamed from: io.ktor.server.http.PushKt$push$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/response/ResponsePushBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/server/response/ResponsePushBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<ResponsePushBuilder, t0> {
        final /* synthetic */ Parameters $encodedParameters;
        final /* synthetic */ String $encodedPath;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(String str, Parameters parameters) {
            super(1);
            this.$encodedPath = str;
            this.$encodedParameters = parameters;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ResponsePushBuilder) obj);
            return t0.f22605a;
        }

        public final void invoke(ResponsePushBuilder responsePushBuilder) {
            URLBuilderKt.setEncodedPath(responsePushBuilder.getUrl(), this.$encodedPath);
            responsePushBuilder.getUrl().getEncodedParameters().clear();
            responsePushBuilder.getUrl().getEncodedParameters().appendAll(this.$encodedParameters);
        }
    }

    @UseHttp2Push
    public static final void push(ApplicationCall applicationCall, String str, Parameters parameters) {
        push(applicationCall, new AnonymousClass2(str, parameters));
    }

    @UseHttp2Push
    public static final void push(ApplicationCall applicationCall, l<? super ResponsePushBuilder, t0> lVar) {
        ApplicationResponse response = applicationCall.getResponse();
        DefaultResponsePushBuilder defaultResponsePushBuilder = new DefaultResponsePushBuilder(applicationCall);
        lVar.invoke(defaultResponsePushBuilder);
        response.push(defaultResponsePushBuilder);
    }

    @UseHttp2Push
    public static final void push(ApplicationCall applicationCall, String str) {
        x xVar;
        int iF0 = o.f0(str, "?", 0, false, 6);
        if (iF0 == -1) {
            xVar = new x(str, "");
        } else {
            xVar = new x(str.substring(0, iF0), str.substring(iF0 + 1));
        }
        push(applicationCall, (String) xVar.f22608i, QueryKt.parseQueryString$default((String) xVar.f22609l, 0, 0, false, 6, null));
    }
}
