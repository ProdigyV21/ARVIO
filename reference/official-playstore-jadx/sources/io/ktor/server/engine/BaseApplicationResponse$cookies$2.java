package io.ktor.server.engine;

import io.ktor.server.plugins.OriginConnectionPointKt;
import io.ktor.server.response.ResponseCookies;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/server/response/ResponseCookies;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class BaseApplicationResponse$cookies$2 extends r implements a<ResponseCookies> {
    final /* synthetic */ BaseApplicationResponse this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseApplicationResponse$cookies$2(BaseApplicationResponse baseApplicationResponse) {
        super(0);
        this.this$0 = baseApplicationResponse;
    }

    @Override // r7.a
    public final ResponseCookies invoke() {
        BaseApplicationResponse baseApplicationResponse = this.this$0;
        return new ResponseCookies(baseApplicationResponse, p.a(OriginConnectionPointKt.getOrigin(baseApplicationResponse.getCall().getRequest()).getScheme(), "https") || p.a(OriginConnectionPointKt.getOrigin(this.this$0.getCall().getRequest()).getScheme(), "wss"));
    }
}
