package ka;

import d7.j;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Lka/v1;", "Ld7/j$a;", "ka/u1", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface v1 extends j.a {
    o attachChild(q qVar);

    /* synthetic */ void cancel();

    void cancel(CancellationException cancellationException);

    /* synthetic */ boolean cancel(Throwable th);

    CancellationException getCancellationException();

    ga.m getChildren();

    sa.b getOnJoin();

    v1 getParent();

    z0 invokeOnCompletion(r7.l lVar);

    z0 invokeOnCompletion(boolean z, boolean z5, r7.l lVar);

    boolean isActive();

    boolean isCancelled();

    boolean isCompleted();

    Object join(d7.d dVar);

    v1 plus(v1 v1Var);

    boolean start();
}
