package ra;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.jvm.internal.k0;
import q7.w;

/* JADX INFO: loaded from: classes5.dex */
public final class a extends Thread {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f21404s = AtomicIntegerFieldUpdater.newUpdater(a.class, "workerCtl");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final m f21405i;
    private volatile int indexInArray;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final k0 f21406l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f21407m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f21408n;
    private volatile Object nextParkedWorker;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public long f21409o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f21410p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f21411q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ b f21412r;

    @w
    private volatile int workerCtl;

    public a(b bVar, int i10) {
        this.f21412r = bVar;
        setDaemon(true);
        this.f21405i = new m();
        this.f21406l = new k0();
        this.f21407m = 4;
        this.nextParkedWorker = b.f21416u;
        this.f21410p = v7.d.f22178l.d();
        f(i10);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0043, code lost:
    
        r11 = ra.m.f21443d.get(r9);
        r0 = ra.m.f21442c.get(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004f, code lost:
    
        if (r11 == r0) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0057, code lost:
    
        if (ra.m.f21444e.get(r9) != 0) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005a, code lost:
    
        r0 = r0 - 1;
        r1 = r9.c(r0, true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0060, code lost:
    
        if (r1 == null) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0062, code lost:
    
        r7 = r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final ra.h a(boolean r11) {
        /*
            r10 = this;
            int r0 = r10.f21407m
            ra.b r2 = r10.f21412r
            r7 = 0
            r8 = 1
            ra.m r9 = r10.f21405i
            if (r0 != r8) goto Lc
            goto L86
        Lc:
            java.util.concurrent.atomic.AtomicLongFieldUpdater r0 = ra.b.f21414s
        Le:
            long r3 = r0.get(r2)
            r5 = 9223367638808264704(0x7ffffc0000000000, double:NaN)
            long r5 = r5 & r3
            r1 = 42
            long r5 = r5 >> r1
            int r1 = (int) r5
            if (r1 != 0) goto L75
            r9.getClass()
        L21:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r11 = ra.m.f21441b
            java.lang.Object r0 = r11.get(r9)
            ra.h r0 = (ra.h) r0
            if (r0 != 0) goto L2c
            goto L43
        L2c:
            ra.i r1 = r0.f21430l
            int r1 = r1.a()
            if (r1 != r8) goto L43
        L34:
            boolean r1 = r11.compareAndSet(r9, r0, r7)
            if (r1 == 0) goto L3c
            r7 = r0
            goto L63
        L3c:
            java.lang.Object r1 = r11.get(r9)
            if (r1 == r0) goto L34
            goto L21
        L43:
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r11 = ra.m.f21443d
            int r11 = r11.get(r9)
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r0 = ra.m.f21442c
            int r0 = r0.get(r9)
        L4f:
            if (r11 == r0) goto L63
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r1 = ra.m.f21444e
            int r1 = r1.get(r9)
            if (r1 != 0) goto L5a
            goto L63
        L5a:
            int r0 = r0 + (-1)
            ra.h r1 = r9.c(r0, r8)
            if (r1 == 0) goto L4f
            r7 = r1
        L63:
            if (r7 != 0) goto L74
            ra.e r11 = r2.f21422p
            java.lang.Object r11 = r11.d()
            ra.h r11 = (ra.h) r11
            if (r11 != 0) goto L73
            ra.h r11 = r10.i(r8)
        L73:
            return r11
        L74:
            return r7
        L75:
            r5 = 4398046511104(0x40000000000, double:2.1729236899484E-311)
            long r5 = r3 - r5
            java.util.concurrent.atomic.AtomicLongFieldUpdater r1 = ra.b.f21414s
            boolean r1 = r1.compareAndSet(r2, r3, r5)
            if (r1 == 0) goto Le
            r10.f21407m = r8
        L86:
            if (r11 == 0) goto Lba
            int r11 = r2.f21417i
            int r11 = r11 * 2
            int r11 = r10.d(r11)
            if (r11 != 0) goto L93
            goto L94
        L93:
            r8 = 0
        L94:
            if (r8 == 0) goto L9d
            ra.h r11 = r10.e()
            if (r11 == 0) goto L9d
            return r11
        L9d:
            r9.getClass()
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r11 = ra.m.f21441b
            java.lang.Object r11 = r11.getAndSet(r9, r7)
            ra.h r11 = (ra.h) r11
            if (r11 != 0) goto Lae
            ra.h r11 = r9.b()
        Lae:
            if (r11 == 0) goto Lb1
            return r11
        Lb1:
            if (r8 != 0) goto Lc1
            ra.h r11 = r10.e()
            if (r11 == 0) goto Lc1
            return r11
        Lba:
            ra.h r11 = r10.e()
            if (r11 == 0) goto Lc1
            return r11
        Lc1:
            r11 = 3
            ra.h r11 = r10.i(r11)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: ra.a.a(boolean):ra.h");
    }

    public final int b() {
        return this.indexInArray;
    }

    public final Object c() {
        return this.nextParkedWorker;
    }

    public final int d(int i10) {
        int i11 = this.f21410p;
        int i12 = i11 ^ (i11 << 13);
        int i13 = i12 ^ (i12 >> 17);
        int i14 = i13 ^ (i13 << 5);
        this.f21410p = i14;
        int i15 = i10 - 1;
        return (i15 & i10) == 0 ? i14 & i15 : (i14 & Integer.MAX_VALUE) % i10;
    }

    public final h e() {
        int iD = d(2);
        b bVar = this.f21412r;
        if (iD == 0) {
            h hVar = (h) bVar.f21421o.d();
            return hVar != null ? hVar : (h) bVar.f21422p.d();
        }
        h hVar2 = (h) bVar.f21422p.d();
        return hVar2 != null ? hVar2 : (h) bVar.f21421o.d();
    }

    public final void f(int i10) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.f21412r.f21420n);
        sb2.append("-worker-");
        sb2.append(i10 == 0 ? "TERMINATED" : String.valueOf(i10));
        setName(sb2.toString());
        this.indexInArray = i10;
    }

    public final void g(Object obj) {
        this.nextParkedWorker = obj;
    }

    public final boolean h(int i10) {
        int i11 = this.f21407m;
        boolean z = i11 == 1;
        if (z) {
            b.f21414s.addAndGet(this.f21412r, 4398046511104L);
        }
        if (i11 != i10) {
            this.f21407m = i10;
        }
        return z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x006b, code lost:
    
        r7 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00a3, code lost:
    
        r7 = -2;
        r5 = r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final ra.h i(int r26) {
        /*
            Method dump skipped, instruction units count: 263
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ra.a.i(int):ra.h");
    }

    /* JADX WARN: Code restructure failed: missing block: B:119:0x0004, code lost:
    
        continue;
     */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            Method dump skipped, instruction units count: 391
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ra.a.run():void");
    }
}
