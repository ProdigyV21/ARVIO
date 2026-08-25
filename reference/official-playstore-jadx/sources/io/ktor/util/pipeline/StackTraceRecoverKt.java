package io.ktor.util.pipeline;

import d7.d;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a#\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0000¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"", "exception", "Ld7/d;", "continuation", "recoverStackTraceBridge", "(Ljava/lang/Throwable;Ld7/d;)Ljava/lang/Throwable;", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StackTraceRecoverKt {
    public static final Throwable recoverStackTraceBridge(Throwable th, d<?> dVar) {
        try {
            return StackTraceRecoverJvmKt.withCause(th, th.getCause());
        } catch (Throwable unused) {
            return th;
        }
    }
}
