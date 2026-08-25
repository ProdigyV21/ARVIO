package io.ktor.client.engine;

import java.io.Closeable;
import ka.f0;
import ka.j1;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0013\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lka/f0;", "Lx6/t0;", "close", "(Lka/f0;)V", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpClientEngineBaseKt {
    /* JADX WARN: Multi-variable type inference failed */
    private static final void close(f0 f0Var) {
        try {
            if (f0Var instanceof j1) {
                ((j1) f0Var).close();
            } else if (f0Var instanceof Closeable) {
                ((Closeable) f0Var).close();
            }
        } catch (Throwable unused) {
        }
    }
}
