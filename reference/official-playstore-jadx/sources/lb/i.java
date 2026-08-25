package lb;

import androidx.emoji2.text.e0;
import com.google.android.gms.common.api.internal.g0;
import gb.h0;
import gb.k0;
import gb.p0;
import gb.u;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.collections.x;
import kotlin.jvm.internal.p;
import qb.n;

/* JADX INFO: loaded from: classes5.dex */
public final class i implements gb.i {
    public volatile g0 A;
    public volatile k B;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final h0 f20110i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final k0 f20111l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f20112m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final io.sentry.android.core.internal.util.g f20113n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final u f20114o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final h f20115p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final AtomicBoolean f20116q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Object f20117r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public e f20118s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public k f20119t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f20120u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public g0 f20121v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f20122w;
    public boolean x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f20123y;
    public volatile boolean z;

    public i(h0 h0Var, k0 k0Var, boolean z) {
        this.f20110i = h0Var;
        this.f20111l = k0Var;
        this.f20112m = z;
        this.f20113n = h0Var.f15701l.f15761a;
        h0Var.f15704o.getClass();
        this.f20114o = u.f15819c;
        h hVar = new h(this);
        hVar.g(h0Var.G, TimeUnit.MILLISECONDS);
        this.f20115p = hVar;
        this.f20116q = new AtomicBoolean();
        this.f20123y = true;
    }

    public static final String a(i iVar) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(iVar.z ? "canceled " : "");
        sb2.append(iVar.f20112m ? "web socket" : "call");
        sb2.append(" to ");
        sb2.append(iVar.f20111l.f15730a.h());
        return sb2.toString();
    }

    @Override // gb.i
    public final void K0(gb.j jVar) {
        f fVar;
        if (!this.f20116q.compareAndSet(false, true)) {
            throw new IllegalStateException("Already Executed");
        }
        n nVar = n.f21385a;
        this.f20117r = n.f21385a.g();
        this.f20114o.getClass();
        e0 e0Var = this.f20110i.f15700i;
        f fVar2 = new f(this, jVar);
        synchronized (e0Var) {
            ((ArrayDeque) e0Var.f2601m).add(fVar2);
            if (!this.f20112m) {
                String str = this.f20111l.f15730a.f15620d;
                Iterator it = ((ArrayDeque) e0Var.f2602n).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        Iterator it2 = ((ArrayDeque) e0Var.f2601m).iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                fVar = null;
                                break;
                            } else {
                                fVar = (f) it2.next();
                                if (p.a(fVar.f20107m.f20111l.f15730a.f15620d, str)) {
                                    break;
                                }
                            }
                        }
                    } else {
                        fVar = (f) it.next();
                        if (p.a(fVar.f20107m.f20111l.f15730a.f15620d, str)) {
                            break;
                        }
                    }
                }
                if (fVar != null) {
                    fVar2.f20106l = fVar.f20106l;
                }
            }
        }
        e0Var.y();
    }

    public final void b(k kVar) {
        byte[] bArr = ib.c.f16268a;
        if (this.f20119t != null) {
            throw new IllegalStateException("Check failed.");
        }
        this.f20119t = kVar;
        kVar.f20138p.add(new g(this, this.f20117r));
    }

    public final IOException c(IOException iOException) {
        IOException interruptedIOException;
        Socket socketI;
        byte[] bArr = ib.c.f16268a;
        k kVar = this.f20119t;
        if (kVar != null) {
            synchronized (kVar) {
                socketI = i();
            }
            if (this.f20119t == null) {
                if (socketI != null) {
                    ib.c.e(socketI);
                }
                this.f20114o.getClass();
            } else if (socketI != null) {
                throw new IllegalStateException("Check failed.");
            }
        }
        if (!this.f20120u && this.f20115p.j()) {
            interruptedIOException = new InterruptedIOException("timeout");
            if (iOException != null) {
                interruptedIOException.initCause(iOException);
            }
        } else {
            interruptedIOException = iOException;
        }
        if (iOException != null) {
            this.f20114o.getClass();
            return interruptedIOException;
        }
        this.f20114o.getClass();
        return interruptedIOException;
    }

    @Override // gb.i
    public final void cancel() {
        Socket socket;
        if (this.z) {
            return;
        }
        this.z = true;
        g0 g0Var = this.A;
        if (g0Var != null) {
            ((mb.d) g0Var.f12737e).cancel();
        }
        k kVar = this.B;
        if (kVar != null && (socket = kVar.f20126c) != null) {
            ib.c.e(socket);
        }
        this.f20114o.getClass();
    }

    public final Object clone() {
        return new i(this.f20110i, this.f20111l, this.f20112m);
    }

    public final p0 d() {
        if (!this.f20116q.compareAndSet(false, true)) {
            throw new IllegalStateException("Already Executed");
        }
        this.f20115p.i();
        n nVar = n.f21385a;
        this.f20117r = n.f21385a.g();
        this.f20114o.getClass();
        try {
            e0 e0Var = this.f20110i.f15700i;
            synchronized (e0Var) {
                ((ArrayDeque) e0Var.f2603o).add(this);
            }
            return f();
        } finally {
            e0 e0Var2 = this.f20110i.f15700i;
            e0Var2.r((ArrayDeque) e0Var2.f2603o, this);
        }
    }

    public final void e(boolean z) {
        g0 g0Var;
        synchronized (this) {
            if (!this.f20123y) {
                throw new IllegalStateException("released");
            }
        }
        if (z && (g0Var = this.A) != null) {
            ((mb.d) g0Var.f12737e).cancel();
            ((i) g0Var.f12734b).g(g0Var, true, true, null);
        }
        this.f20121v = null;
    }

    public final p0 f() {
        ArrayList arrayList = new ArrayList();
        x.b0(arrayList, this.f20110i.f15702m);
        arrayList.add(new mb.g(this.f20110i));
        arrayList.add(new mb.a(this.f20110i.f15709t));
        arrayList.add(new jb.b(this.f20110i.f15710u));
        arrayList.add(a.f20079a);
        if (!this.f20112m) {
            x.b0(arrayList, this.f20110i.f15703n);
        }
        arrayList.add(new mb.b(this.f20112m));
        k0 k0Var = this.f20111l;
        h0 h0Var = this.f20110i;
        try {
            try {
                p0 p0VarA = new mb.f(this, arrayList, 0, null, k0Var, h0Var.H, h0Var.I, h0Var.J).a(k0Var);
                if (this.z) {
                    ib.c.d(p0VarA);
                    throw new IOException("Canceled");
                }
                h(null);
                return p0VarA;
            } catch (IOException e5) {
                throw h(e5);
            }
        } catch (Throwable th) {
            if (0 == 0) {
                h(null);
            }
            throw th;
        }
    }

    public final IOException g(g0 g0Var, boolean z, boolean z5, IOException iOException) {
        boolean z10;
        boolean z11;
        if (g0Var.equals(this.A)) {
            synchronized (this) {
                z10 = false;
                if (z) {
                    try {
                        if (!this.f20122w) {
                            if (z5 || !this.x) {
                                z11 = false;
                            }
                        }
                        if (z) {
                            this.f20122w = false;
                        }
                        if (z5) {
                            this.x = false;
                        }
                        boolean z12 = this.f20122w;
                        boolean z13 = (z12 || this.x) ? false : true;
                        if (!z12 && !this.x) {
                            if (!this.f20123y) {
                                z10 = true;
                            }
                        }
                        z11 = z10;
                        z10 = z13;
                    } catch (Throwable th) {
                        throw th;
                    }
                } else {
                    if (z5) {
                    }
                    z11 = false;
                }
            }
            if (z10) {
                this.A = null;
                k kVar = this.f20119t;
                if (kVar != null) {
                    kVar.h();
                }
            }
            if (z11) {
                return c(iOException);
            }
        }
        return iOException;
    }

    public final IOException h(IOException iOException) {
        boolean z;
        synchronized (this) {
            z = false;
            if (this.f20123y) {
                this.f20123y = false;
                if (!this.f20122w) {
                    if (!this.x) {
                        z = true;
                    }
                }
            }
        }
        return z ? c(iOException) : iOException;
    }

    public final Socket i() {
        k kVar = this.f20119t;
        byte[] bArr = ib.c.f16268a;
        ArrayList arrayList = kVar.f20138p;
        Iterator it = arrayList.iterator();
        int i10 = 0;
        while (true) {
            if (!it.hasNext()) {
                i10 = -1;
                break;
            }
            if (p.a(((Reference) it.next()).get(), this)) {
                break;
            }
            i10++;
        }
        if (i10 == -1) {
            throw new IllegalStateException("Check failed.");
        }
        arrayList.remove(i10);
        this.f20119t = null;
        if (!arrayList.isEmpty()) {
            return null;
        }
        kVar.f20139q = System.nanoTime();
        io.sentry.android.core.internal.util.g gVar = this.f20113n;
        ConcurrentLinkedQueue concurrentLinkedQueue = (ConcurrentLinkedQueue) gVar.f16609e;
        kb.b bVar = (kb.b) gVar.f16607c;
        byte[] bArr2 = ib.c.f16268a;
        if (!kVar.f20133j && gVar.f16605a != 0) {
            bVar.c((jb.g) gVar.f16608d, 0L);
            return null;
        }
        kVar.f20133j = true;
        concurrentLinkedQueue.remove(kVar);
        if (concurrentLinkedQueue.isEmpty()) {
            bVar.a();
        }
        return kVar.f20127d;
    }

    @Override // gb.i
    public final boolean k() {
        return this.z;
    }

    @Override // gb.i
    public final k0 request() {
        return this.f20111l;
    }
}
