package io.ktor.utils.io.internal.jvm;

import a0.c;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0001\u001a\u0010\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0001\u001a\u0018\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0001¨\u0006\u0007"}, d2 = {"limitChangeError", "", "negativeShiftError", "delta", "", "wrongBufferPositionChangeError", ContentDisposition.Parameters.Size, "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ErrorsKt {
    public static final Void limitChangeError() {
        throw new IllegalStateException("Limit change is now allowed");
    }

    public static final Void negativeShiftError(int i10) {
        throw new IllegalStateException(c.i(i10, "Wrong buffer position change: negative shift "));
    }

    public static final Void wrongBufferPositionChangeError(int i10, int i11) {
        throw new IllegalStateException(androidx.compose.foundation.c.r("Wrong buffer position change: ", i10, ". Position should be moved forward only by at most size bytes (size = ", i11, ')'));
    }
}
