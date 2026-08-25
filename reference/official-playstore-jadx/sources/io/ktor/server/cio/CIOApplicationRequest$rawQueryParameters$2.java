package io.ktor.server.cio;

import io.ktor.http.Parameters;
import io.ktor.http.QueryKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import kotlin.text.o;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/http/Parameters;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class CIOApplicationRequest$rawQueryParameters$2 extends r implements a<Parameters> {
    final /* synthetic */ CIOApplicationRequest this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CIOApplicationRequest$rawQueryParameters$2(CIOApplicationRequest cIOApplicationRequest) {
        super(0);
        this.this$0 = cIOApplicationRequest;
    }

    @Override // r7.a
    public final Parameters invoke() {
        String string = this.this$0.request.getUri().toString();
        int iE0 = o.e0(string, '?', 0, false, 6);
        Integer numValueOf = Integer.valueOf(iE0);
        if (iE0 == -1) {
            numValueOf = null;
        }
        return numValueOf != null ? QueryKt.parseQueryString$default(string, numValueOf.intValue() + 1, 0, false, 4, null) : Parameters.INSTANCE.getEmpty();
    }
}
