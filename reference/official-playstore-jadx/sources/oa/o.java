package oa;

import kotlinx.coroutines.flow.internal.ChildCancelledException;

/* JADX INFO: loaded from: classes5.dex */
public final class o extends pa.v {
    @Override // ka.f2
    public final boolean q(Throwable th) {
        if (th instanceof ChildCancelledException) {
            return true;
        }
        return l(th);
    }
}
