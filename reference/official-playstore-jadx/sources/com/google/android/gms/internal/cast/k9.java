package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class k9 extends u0 {
    @Override // com.google.android.gms.internal.cast.u0
    public final g9 A(i9 i9Var) {
        g9 g9Var;
        g9 g9Var2 = g9.f13262d;
        synchronized (i9Var) {
            try {
                g9Var = i9Var.f13560n;
                if (g9Var != g9Var2) {
                    i9Var.f13560n = g9Var2;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return g9Var;
    }

    @Override // com.google.android.gms.internal.cast.u0
    public final boolean B(q9 q9Var, Object obj, Object obj2) {
        synchronized (q9Var) {
            try {
                if (q9Var.f13559m != obj) {
                    return false;
                }
                q9Var.f13559m = obj2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.android.gms.internal.cast.u0
    public final void o(p9 p9Var, Thread thread) {
        p9Var.f13544a = thread;
    }

    @Override // com.google.android.gms.internal.cast.u0
    public final void t(p9 p9Var, p9 p9Var2) {
        p9Var.f13545b = p9Var2;
    }

    @Override // com.google.android.gms.internal.cast.u0
    public final boolean v(q9 q9Var, p9 p9Var, p9 p9Var2) {
        synchronized (q9Var) {
            try {
                if (q9Var.f13561o != p9Var) {
                    return false;
                }
                q9Var.f13561o = p9Var2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.android.gms.internal.cast.u0
    public final boolean x(q9 q9Var, g9 g9Var, g9 g9Var2) {
        synchronized (q9Var) {
            try {
                if (q9Var.f13560n != g9Var) {
                    return false;
                }
                q9Var.f13560n = g9Var2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.android.gms.internal.cast.u0
    public final p9 y(i9 i9Var) {
        p9 p9Var;
        p9 p9Var2 = p9.f13543c;
        synchronized (i9Var) {
            try {
                p9Var = i9Var.f13561o;
                if (p9Var != p9Var2) {
                    i9Var.f13561o = p9Var2;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return p9Var;
    }
}
