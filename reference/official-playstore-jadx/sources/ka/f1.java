package ka;

/* JADX INFO: loaded from: classes5.dex */
public abstract class f1 implements Runnable, Comparable, z0, pa.e0 {
    private volatile Object _heap;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f19585i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f19586l = -1;

    public f1(long j10) {
        this.f19585i = j10;
    }

    @Override // pa.e0
    public final void a(g1 g1Var) {
        if (this._heap == m0.f19612b) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        this._heap = g1Var;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        long j10 = this.f19585i - ((f1) obj).f19585i;
        if (j10 > 0) {
            return 1;
        }
        return j10 < 0 ? -1 : 0;
    }

    public final pa.d0 d() {
        Object obj = this._heap;
        if (obj instanceof pa.d0) {
            return (pa.d0) obj;
        }
        return null;
    }

    @Override // ka.z0
    public final void dispose() {
        synchronized (this) {
            try {
                Object obj = this._heap;
                c2.a aVar = m0.f19612b;
                if (obj == aVar) {
                    return;
                }
                g1 g1Var = obj instanceof g1 ? (g1) obj : null;
                if (g1Var != null) {
                    g1Var.c(this);
                }
                this._heap = aVar;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final int f(long j10, g1 g1Var, h1 h1Var) {
        synchronized (this) {
            if (this._heap == m0.f19612b) {
                return 2;
            }
            synchronized (g1Var) {
                try {
                    pa.e0[] e0VarArr = g1Var.f21261a;
                    f1 f1Var = (f1) (e0VarArr != null ? e0VarArr[0] : null);
                    if (h1.f19597q.get(h1Var) != 0) {
                        return 1;
                    }
                    if (f1Var == null) {
                        g1Var.f19592c = j10;
                    } else {
                        long j11 = f1Var.f19585i;
                        if (j11 - j10 < 0) {
                            j10 = j11;
                        }
                        if (j10 - g1Var.f19592c > 0) {
                            g1Var.f19592c = j10;
                        }
                    }
                    long j12 = this.f19585i;
                    long j13 = g1Var.f19592c;
                    if (j12 - j13 < 0) {
                        this.f19585i = j13;
                    }
                    g1Var.a(this);
                    return 0;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    @Override // pa.e0
    public final void setIndex(int i10) {
        this.f19586l = i10;
    }

    public String toString() {
        return "Delayed[nanos=" + this.f19585i + ']';
    }
}
