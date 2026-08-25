package io.ktor.server.request;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class RequestCookies$rawCookies$2 extends r implements a<Map<String, ? extends String>> {
    final /* synthetic */ RequestCookies this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RequestCookies$rawCookies$2(RequestCookies requestCookies) {
        super(0);
        this.this$0 = requestCookies;
    }

    @Override // r7.a
    public final Map<String, String> invoke() {
        return this.this$0.fetchCookies();
    }
}
