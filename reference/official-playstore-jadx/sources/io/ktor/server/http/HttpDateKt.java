package io.ktor.server.http;

import io.ktor.http.DateUtilsKt;
import io.ktor.util.date.DateJvmKt;
import kotlin.Metadata;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0007¨\u0006\u0003"}, d2 = {"toHttpDateString", "", "", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpDateKt {
    @e
    public static final String toHttpDateString(long j10) {
        return DateUtilsKt.toHttpDate(DateJvmKt.GMTDate(Long.valueOf(j10)));
    }
}
