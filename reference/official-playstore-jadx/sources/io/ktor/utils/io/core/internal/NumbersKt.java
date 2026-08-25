package io.ktor.utils.io.core.internal;

import g8.b;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0001\u001a\u0015\u0010\u0006\u001a\u00020\u0007*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0081\b¨\u0006\b"}, d2 = {"failLongToIntConversion", "", "value", "", ContentDisposition.Parameters.Name, "", "toIntOrFail", "", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NumbersKt {
    public static final Void failLongToIntConversion(long j10, String str) {
        throw new IllegalArgumentException("Long value " + j10 + " of " + str + " doesn't fit into 32-bit integer");
    }

    public static final int toIntOrFail(long j10, String str) {
        if (j10 < 2147483647L) {
            return (int) j10;
        }
        throw b.j(j10, str);
    }
}
