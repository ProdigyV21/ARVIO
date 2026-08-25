package io.ktor.server.http.content;

import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaders;
import io.ktor.util.StringValuesKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/http/Headers;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class PreCompressedResponse$headers$2 extends r implements a<Headers> {
    final /* synthetic */ PreCompressedResponse this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PreCompressedResponse$headers$2(PreCompressedResponse preCompressedResponse) {
        super(0);
        this.this$0 = preCompressedResponse;
    }

    @Override // r7.a
    public final Headers invoke() {
        if (this.this$0.encoding == null) {
            return this.this$0.original.getHeaders();
        }
        Headers.Companion companion = Headers.INSTANCE;
        PreCompressedResponse preCompressedResponse = this.this$0;
        HeadersBuilder headersBuilder = new HeadersBuilder(0, 1, null);
        StringValuesKt.appendFiltered$default(headersBuilder, preCompressedResponse.original.getHeaders(), false, PreCompressedResponse$headers$2$1$1.INSTANCE, 2, null);
        headersBuilder.append(HttpHeaders.INSTANCE.getContentEncoding(), preCompressedResponse.encoding);
        return headersBuilder.build();
    }
}
