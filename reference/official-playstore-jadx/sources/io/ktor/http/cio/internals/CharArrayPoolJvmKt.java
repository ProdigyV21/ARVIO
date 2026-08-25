package io.ktor.http.cio.internals;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0000¨\u0006\u0002"}, d2 = {"isPoolingDisabled", "", "ktor-http-cio"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CharArrayPoolJvmKt {
    public static final boolean isPoolingDisabled() {
        String property = System.getProperty("ktor.internal.cio.disable.chararray.pooling");
        if (property != null) {
            return Boolean.parseBoolean(property);
        }
        return false;
    }
}
