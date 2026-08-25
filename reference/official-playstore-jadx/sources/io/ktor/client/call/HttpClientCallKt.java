package io.ktor.client.call;

import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import io.ktor.client.statement.HttpResponse;
import io.ktor.util.reflect.TypeInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001f\u0010\u0002\u001a\u00028\u0000\"\u0006\b\u0000\u0010\u0000\u0018\u0001*\u00020\u0001H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u001f\u0010\u0002\u001a\u00028\u0000\"\u0006\b\u0000\u0010\u0000\u0018\u0001*\u00020\u0004H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\u0005\u001a%\u0010\u0002\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"T", "Lio/ktor/client/call/HttpClientCall;", TtmlNode.TAG_BODY, "(Lio/ktor/client/call/HttpClientCall;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/client/statement/HttpResponse;", "(Lio/ktor/client/statement/HttpResponse;Ld7/d;)Ljava/lang/Object;", "Lio/ktor/util/reflect/TypeInfo;", "typeInfo", "(Lio/ktor/client/statement/HttpResponse;Lio/ktor/util/reflect/TypeInfo;Ld7/d;)Ljava/lang/Object;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpClientCallKt {
    public static final <T> Object body(HttpResponse httpResponse, d<? super T> dVar) {
        httpResponse.getCall();
        p.h();
        throw null;
    }

    public static final <T> Object body(HttpResponse httpResponse, TypeInfo typeInfo, d<? super T> dVar) {
        return httpResponse.getCall().bodyNullable(typeInfo, dVar);
    }

    public static final <T> Object body(HttpClientCall httpClientCall, d<? super T> dVar) {
        p.h();
        throw null;
    }
}
