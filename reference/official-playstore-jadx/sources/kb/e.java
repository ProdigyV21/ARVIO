package kb;

import androidx.leanback.widget.u;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes5.dex */
public final class e {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final e f19674h = new e(new d(new ib.b(a0.c.p(new StringBuilder(), ib.c.f16275h, " TaskRunner"), true)));

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final Logger f19675i = Logger.getLogger(e.class.getName());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f19676a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f19678c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f19679d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f19677b = 10000;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f19680e = new ArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f19681f = new ArrayList();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final u f19682g = new u(this, 13);

    public e(d dVar) {
        this.f19676a = dVar;
    }

    public static final void a(e eVar, a aVar) {
        byte[] bArr = ib.c.f16268a;
        Thread threadCurrentThread = Thread.currentThread();
        String name = threadCurrentThread.getName();
        threadCurrentThread.setName(aVar.f19662a);
        try {
            long jA = aVar.a();
            synchronized (eVar) {
                eVar.b(aVar, jA);
            }
            threadCurrentThread.setName(name);
        } catch (Throwable th) {
            synchronized (eVar) {
                eVar.b(aVar, -1L);
                threadCurrentThread.setName(name);
                throw th;
            }
        }
    }

    public final void b(a aVar, long j10) {
        byte[] bArr = ib.c.f16268a;
        b bVar = aVar.f19664c;
        if (bVar.f19669d != aVar) {
            throw new IllegalStateException("Check failed.");
        }
        boolean z = bVar.f19671f;
        bVar.f19671f = false;
        bVar.f19669d = null;
        this.f19680e.remove(bVar);
        if (j10 != -1 && !z && !bVar.f19668c) {
            bVar.e(aVar, j10, true);
        }
        if (bVar.f19670e.isEmpty()) {
            return;
        }
        this.f19681f.add(bVar);
    }

    public final a c() {
        boolean z;
        byte[] bArr = ib.c.f16268a;
        while (true) {
            ArrayList arrayList = this.f19681f;
            if (arrayList.isEmpty()) {
                break;
            }
            long jNanoTime = System.nanoTime();
            Iterator it = arrayList.iterator();
            long jMin = Long.MAX_VALUE;
            a aVar = null;
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                a aVar2 = (a) ((b) it.next()).f19670e.get(0);
                long jMax = Math.max(0L, aVar2.f19665d - jNanoTime);
                if (jMax > 0) {
                    jMin = Math.min(jMax, jMin);
                } else {
                    if (aVar != null) {
                        z = true;
                        break;
                    }
                    aVar = aVar2;
                }
            }
            ArrayList arrayList2 = this.f19680e;
            if (aVar != null) {
                byte[] bArr2 = ib.c.f16268a;
                aVar.f19665d = -1L;
                b bVar = aVar.f19664c;
                bVar.f19670e.remove(aVar);
                arrayList.remove(bVar);
                bVar.f19669d = aVar;
                arrayList2.add(bVar);
                if (z || (!this.f19678c && !arrayList.isEmpty())) {
                    this.f19676a.j(this.f19682g);
                }
                return aVar;
            }
            if (!this.f19678c) {
                this.f19678c = true;
                this.f19679d = jNanoTime + jMin;
                try {
                    try {
                        long j10 = jMin / 1000000;
                        Long.signum(j10);
                        long j11 = jMin - (1000000 * j10);
                        if (j10 > 0 || jMin > 0) {
                            wait(j10, (int) j11);
                        }
                    } catch (InterruptedException unused) {
                        for (int size = arrayList2.size() - 1; -1 < size; size--) {
                            ((b) arrayList2.get(size)).b();
                        }
                        for (int size2 = arrayList.size() - 1; -1 < size2; size2--) {
                            b bVar2 = (b) arrayList.get(size2);
                            bVar2.b();
                            if (bVar2.f19670e.isEmpty()) {
                                arrayList.remove(size2);
                            }
                        }
                    }
                } finally {
                    this.f19678c = false;
                }
            } else if (jMin < this.f19679d - jNanoTime) {
                notify();
            }
        }
        return null;
    }

    public final void d(b bVar) {
        byte[] bArr = ib.c.f16268a;
        if (bVar.f19669d == null) {
            boolean zIsEmpty = bVar.f19670e.isEmpty();
            ArrayList arrayList = this.f19681f;
            if (zIsEmpty) {
                arrayList.remove(bVar);
            } else if (!arrayList.contains(bVar)) {
                arrayList.add(bVar);
            }
        }
        if (this.f19678c) {
            notify();
        } else {
            this.f19676a.j(this.f19682g);
        }
    }

    public final b e() {
        int i10;
        synchronized (this) {
            i10 = this.f19677b;
            this.f19677b = i10 + 1;
        }
        return new b(this, a0.c.i(i10, "Q"));
    }
}
