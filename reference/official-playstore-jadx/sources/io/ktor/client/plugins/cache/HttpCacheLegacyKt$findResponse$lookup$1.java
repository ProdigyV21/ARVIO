package io.ktor.client.plugins.cache;

import io.ktor.http.HeadersBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.m;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public /* synthetic */ class HttpCacheLegacyKt$findResponse$lookup$1 extends m implements l<String, String> {
    public HttpCacheLegacyKt$findResponse$lookup$1(Object obj) {
        super(1, 0, HeadersBuilder.class, obj, "get", "get(Ljava/lang/String;)Ljava/lang/String;");
    }

    @Override // r7.l
    public final String invoke(String str) {
        return ((HeadersBuilder) this.receiver).get(str);
    }
}
