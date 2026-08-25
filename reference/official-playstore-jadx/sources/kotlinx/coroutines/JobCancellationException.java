package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import ka.f2;
import ka.y;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00000\u0003¨\u0006\u0004"}, d2 = {"Lkotlinx/coroutines/JobCancellationException;", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "Lka/y;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class JobCancellationException extends CancellationException implements y<JobCancellationException> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final transient f2 f19975i;

    public JobCancellationException(String str, Throwable th, f2 f2Var) {
        super(str);
        this.f19975i = f2Var;
        if (th != null) {
            initCause(th);
        }
    }

    @Override // ka.y
    public final /* bridge */ /* synthetic */ Throwable createCopy() {
        return null;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof JobCancellationException)) {
            return false;
        }
        JobCancellationException jobCancellationException = (JobCancellationException) obj;
        return p.a(jobCancellationException.getMessage(), getMessage()) && p.a(jobCancellationException.f19975i, this.f19975i) && p.a(jobCancellationException.getCause(), getCause());
    }

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    public final int hashCode() {
        int iHashCode = (this.f19975i.hashCode() + (getMessage().hashCode() * 31)) * 31;
        Throwable cause = getCause();
        return iHashCode + (cause != null ? cause.hashCode() : 0);
    }

    @Override // java.lang.Throwable
    public final String toString() {
        return super.toString() + "; job=" + this.f19975i;
    }
}
