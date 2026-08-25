package oa;

import java.util.Arrays;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public c[] f20802i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f20803l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f20804m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public z f20805n;

    public final c a() {
        c cVarC;
        z zVar;
        synchronized (this) {
            try {
                c[] cVarArrD = this.f20802i;
                if (cVarArrD == null) {
                    cVarArrD = d();
                    this.f20802i = cVarArrD;
                } else if (this.f20803l >= cVarArrD.length) {
                    Object[] objArrCopyOf = Arrays.copyOf(cVarArrD, cVarArrD.length * 2);
                    this.f20802i = (c[]) objArrCopyOf;
                    cVarArrD = (c[]) objArrCopyOf;
                }
                int i10 = this.f20804m;
                do {
                    cVarC = cVarArrD[i10];
                    if (cVarC == null) {
                        cVarC = c();
                        cVarArrD[i10] = cVarC;
                    }
                    i10++;
                    if (i10 >= cVarArrD.length) {
                        i10 = 0;
                    }
                } while (!cVarC.a(this));
                this.f20804m = i10;
                this.f20803l++;
                zVar = this.f20805n;
            } catch (Throwable th) {
                throw th;
            }
        }
        if (zVar != null) {
            zVar.u(1);
        }
        return cVarC;
    }

    public abstract c c();

    public abstract c[] d();

    public final void f(c cVar) {
        z zVar;
        int i10;
        d7.d[] dVarArrB;
        synchronized (this) {
            try {
                int i11 = this.f20803l - 1;
                this.f20803l = i11;
                zVar = this.f20805n;
                if (i11 == 0) {
                    this.f20804m = 0;
                }
                dVarArrB = cVar.b(this);
            } catch (Throwable th) {
                throw th;
            }
        }
        for (d7.d dVar : dVarArrB) {
            if (dVar != null) {
                dVar.resumeWith(t0.f22605a);
            }
        }
        if (zVar != null) {
            zVar.u(-1);
        }
    }

    public final z g() {
        z zVar;
        synchronized (this) {
            zVar = this.f20805n;
            if (zVar == null) {
                int i10 = this.f20803l;
                zVar = new z(1, Integer.MAX_VALUE, 2);
                zVar.b(Integer.valueOf(i10));
                this.f20805n = zVar;
            }
        }
        return zVar;
    }
}
