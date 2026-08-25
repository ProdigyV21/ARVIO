package kb;

import java.util.ArrayList;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;

/* JADX INFO: loaded from: classes5.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e f19666a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f19667b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f19668c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a f19669d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f19670e = new ArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f19671f;

    public b(e eVar, String str) {
        this.f19666a = eVar;
        this.f19667b = str;
    }

    public final void a() {
        byte[] bArr = ib.c.f16268a;
        synchronized (this.f19666a) {
            if (b()) {
                this.f19666a.d(this);
            }
        }
    }

    public final boolean b() {
        a aVar = this.f19669d;
        if (aVar != null && aVar.f19663b) {
            this.f19671f = true;
        }
        ArrayList arrayList = this.f19670e;
        boolean z = false;
        for (int size = arrayList.size() - 1; -1 < size; size--) {
            if (((a) arrayList.get(size)).f19663b) {
                a aVar2 = (a) arrayList.get(size);
                if (e.f19675i.isLoggable(Level.FINE)) {
                    qb.d.b(aVar2, this, "canceled");
                }
                arrayList.remove(size);
                z = true;
            }
        }
        return z;
    }

    public final void c(a aVar, long j10) {
        synchronized (this.f19666a) {
            if (!this.f19668c) {
                if (e(aVar, j10, false)) {
                    this.f19666a.d(this);
                }
            } else if (aVar.f19663b) {
                if (e.f19675i.isLoggable(Level.FINE)) {
                    qb.d.b(aVar, this, "schedule canceled (queue is shutdown)");
                }
            } else {
                if (e.f19675i.isLoggable(Level.FINE)) {
                    qb.d.b(aVar, this, "schedule failed (queue is shutdown)");
                }
                throw new RejectedExecutionException();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0082 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0076 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean e(kb.a r11, long r12, boolean r14) {
        /*
            r10 = this;
            kb.b r0 = r11.f19664c
            if (r0 != r10) goto L5
            goto L9
        L5:
            if (r0 != 0) goto L85
            r11.f19664c = r10
        L9:
            long r0 = java.lang.System.nanoTime()
            long r2 = r0 + r12
            java.util.ArrayList r4 = r10.f19670e
            int r5 = r4.indexOf(r11)
            r6 = 0
            r7 = -1
            if (r5 == r7) goto L32
            long r8 = r11.f19665d
            int r8 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r8 > 0) goto L2f
            java.util.logging.Logger r12 = kb.e.f19675i
            java.util.logging.Level r13 = java.util.logging.Level.FINE
            boolean r12 = r12.isLoggable(r13)
            if (r12 == 0) goto L84
            java.lang.String r12 = "already scheduled"
            qb.d.b(r11, r10, r12)
            return r6
        L2f:
            r4.remove(r5)
        L32:
            r11.f19665d = r2
            java.util.logging.Logger r5 = kb.e.f19675i
            java.util.logging.Level r8 = java.util.logging.Level.FINE
            boolean r5 = r5.isLoggable(r8)
            if (r5 == 0) goto L5a
            if (r14 == 0) goto L4c
            long r2 = r2 - r0
            java.lang.String r14 = qb.d.A(r2)
            java.lang.String r2 = "run again after "
            java.lang.String r14 = r2.concat(r14)
            goto L57
        L4c:
            long r2 = r2 - r0
            java.lang.String r14 = qb.d.A(r2)
            java.lang.String r2 = "scheduled after "
            java.lang.String r14 = r2.concat(r14)
        L57:
            qb.d.b(r11, r10, r14)
        L5a:
            java.util.Iterator r14 = r4.iterator()
            r2 = r6
        L5f:
            boolean r3 = r14.hasNext()
            if (r3 == 0) goto L76
            java.lang.Object r3 = r14.next()
            kb.a r3 = (kb.a) r3
            long r8 = r3.f19665d
            long r8 = r8 - r0
            int r3 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r3 <= 0) goto L73
            goto L77
        L73:
            int r2 = r2 + 1
            goto L5f
        L76:
            r2 = r7
        L77:
            if (r2 != r7) goto L7d
            int r2 = r4.size()
        L7d:
            r4.add(r2, r11)
            if (r2 != 0) goto L84
            r11 = 1
            return r11
        L84:
            return r6
        L85:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "task is in multiple queues"
            r11.<init>(r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kb.b.e(kb.a, long, boolean):boolean");
    }

    public final void f() {
        byte[] bArr = ib.c.f16268a;
        synchronized (this.f19666a) {
            this.f19668c = true;
            if (b()) {
                this.f19666a.d(this);
            }
        }
    }

    public final String toString() {
        return this.f19667b;
    }
}
