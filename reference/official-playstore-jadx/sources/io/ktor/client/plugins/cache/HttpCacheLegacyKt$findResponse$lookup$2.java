package io.ktor.client.plugins.cache;

import io.ktor.http.HeadersBuilder;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.m;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
public /* synthetic */ class HttpCacheLegacyKt$findResponse$lookup$2 extends m implements l<String, List<? extends String>> {
    public HttpCacheLegacyKt$findResponse$lookup$2(Object obj) {
        super(1, 0, HeadersBuilder.class, obj, "getAll", "getAll(Ljava/lang/String;)Ljava/util/List;");
    }

    @Override // r7.l
    public final List<String> invoke(String str) {
        return ((HeadersBuilder) this.receiver).getAll(str);
    }
}
