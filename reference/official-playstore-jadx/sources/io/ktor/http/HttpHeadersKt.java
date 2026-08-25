package io.ktor.http;

import kotlin.Metadata;
import kotlin.text.o;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002¨\u0006\u0004"}, d2 = {"isDelimiter", "", "ch", "", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpHeadersKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isDelimiter(char c10) {
        return o.U("\"(),/:;<=>?@[\\]{}", c10);
    }
}
