package io.ktor.http.cio.internals;

import io.ktor.http.HttpMethod;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/ktor/http/HttpMethod;", "invoke", "(Lio/ktor/http/HttpMethod;)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class CharsKt$DefaultHttpMethods$1 extends r implements l<HttpMethod, Integer> {
    public static final CharsKt$DefaultHttpMethods$1 INSTANCE = new CharsKt$DefaultHttpMethods$1();

    public CharsKt$DefaultHttpMethods$1() {
        super(1);
    }

    @Override // r7.l
    public final Integer invoke(HttpMethod httpMethod) {
        return Integer.valueOf(httpMethod.getValue().length());
    }
}
