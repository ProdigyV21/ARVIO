package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import ka.v1;
import ka.y;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00000\u0003¨\u0006\u0004"}, d2 = {"Lkotlinx/coroutines/TimeoutCancellationException;", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "Lka/y;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class TimeoutCancellationException extends CancellationException implements y<TimeoutCancellationException> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final transient v1 f19976i;

    public TimeoutCancellationException(v1 v1Var, String str) {
        super(str);
        this.f19976i = v1Var;
    }

    @Override // ka.y
    public final Throwable createCopy() {
        String message = getMessage();
        if (message == null) {
            message = "";
        }
        TimeoutCancellationException timeoutCancellationException = new TimeoutCancellationException(this.f19976i, message);
        timeoutCancellationException.initCause(this);
        return timeoutCancellationException;
    }
}
