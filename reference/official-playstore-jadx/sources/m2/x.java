package m2;

import com.google.common.util.concurrent.p0;

/* JADX INFO: loaded from: classes3.dex */
public final class x extends p0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Throwable f20223a;

    public x(Throwable th) {
        this.f20223a = th;
    }

    public final String toString() {
        return "FAILURE (" + this.f20223a.getMessage() + ")";
    }
}
