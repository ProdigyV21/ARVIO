package ka;

import kotlinx.coroutines.CoroutineExceptionHandler;

/* JADX INFO: loaded from: classes5.dex */
public abstract class h0 {
    public static final void a(d7.j jVar, Throwable th) {
        try {
            CoroutineExceptionHandler coroutineExceptionHandler = (CoroutineExceptionHandler) jVar.get(g0.f19591i);
            if (coroutineExceptionHandler != null) {
                coroutineExceptionHandler.handleException(jVar, th);
            } else {
                pa.a.d(jVar, th);
            }
        } catch (Throwable th2) {
            if (th != th2) {
                RuntimeException runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
                com.google.common.util.concurrent.r0.a(runtimeException, th);
                th = runtimeException;
            }
            pa.a.d(jVar, th);
        }
    }
}
