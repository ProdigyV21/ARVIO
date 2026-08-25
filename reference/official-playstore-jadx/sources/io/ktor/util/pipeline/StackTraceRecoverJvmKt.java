package io.ktor.util.pipeline;

import io.ktor.utils.io.ExceptionUtilsJvmKt;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0000¨\u0006\u0003"}, d2 = {"withCause", "", "cause", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StackTraceRecoverJvmKt {
    public static final Throwable withCause(Throwable th, Throwable th2) {
        Throwable thTryCopyException;
        if (th2 == null || p.a(th.getCause(), th2) || (thTryCopyException = ExceptionUtilsJvmKt.tryCopyException(th, th2)) == null) {
            return th;
        }
        thTryCopyException.setStackTrace(th.getStackTrace());
        return thTryCopyException;
    }
}
