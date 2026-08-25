package io.sentry;

/* JADX INFO: loaded from: classes4.dex */
public abstract class z4 implements Comparable {
    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(z4 z4Var) {
        return Long.valueOf(i()).compareTo(Long.valueOf(z4Var.i()));
    }

    public long d(z4 z4Var) {
        return i() - z4Var.i();
    }

    public long f(z4 z4Var) {
        return (z4Var == null || compareTo(z4Var) >= 0) ? i() : z4Var.i();
    }

    public abstract long i();
}
