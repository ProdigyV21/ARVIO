package io.ktor.util.logging;

import kotlin.Metadata;
import kotlin.jvm.internal.l0;
import vc.b;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001d\u0010\u0005\u001a\u00020\u0004*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lvc/b;", "Lio/ktor/util/logging/Logger;", "", "exception", "Lx6/t0;", "error", "(Lvc/b;Ljava/lang/Throwable;)V", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LoggerKt {
    public static final void error(b bVar, Throwable th) {
        String message = th.getMessage();
        if (message == null) {
            StringBuilder sb2 = new StringBuilder("Exception of type ");
            message = g8.b.h(l0.f19747a, th.getClass(), sb2);
        }
        bVar.c(message, th);
    }
}
