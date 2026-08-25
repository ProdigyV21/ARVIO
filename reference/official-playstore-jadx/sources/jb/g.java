package jb;

import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;
import lb.k;
import ob.o;
import xb.h0;

/* JADX INFO: loaded from: classes5.dex */
public final class g extends kb.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f19433e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f19434f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ g(Object obj, String str, int i10) {
        super(str, true);
        this.f19433e = i10;
        this.f19434f = obj;
    }

    private final long b() {
        io.sentry.android.core.internal.util.g gVar = (io.sentry.android.core.internal.util.g) this.f19434f;
        long jNanoTime = System.nanoTime();
        int i10 = 0;
        long j10 = Long.MIN_VALUE;
        k kVar = null;
        int i11 = 0;
        for (k kVar2 : (ConcurrentLinkedQueue) gVar.f16609e) {
            synchronized (kVar2) {
                if (gVar.c(kVar2, jNanoTime) > 0) {
                    i11++;
                } else {
                    i10++;
                    long j11 = jNanoTime - kVar2.f20139q;
                    if (j11 > j10) {
                        kVar = kVar2;
                        j10 = j11;
                    }
                }
            }
        }
        long j12 = gVar.f16606b;
        if (j10 < j12 && i10 <= gVar.f16605a) {
            if (i10 > 0) {
                return j12 - j10;
            }
            if (i11 > 0) {
                return j12;
            }
            return -1L;
        }
        synchronized (kVar) {
            if (!kVar.f20138p.isEmpty()) {
                return 0L;
            }
            if (kVar.f20139q + j10 != jNanoTime) {
                return 0L;
            }
            kVar.f20133j = true;
            ((ConcurrentLinkedQueue) gVar.f16609e).remove(kVar);
            ib.c.e(kVar.f20127d);
            if (((ConcurrentLinkedQueue) gVar.f16609e).isEmpty()) {
                ((kb.b) gVar.f16607c).a();
            }
            return 0L;
        }
    }

    @Override // kb.a
    public final long a() {
        switch (this.f19433e) {
            case 0:
                h hVar = (h) this.f19434f;
                synchronized (hVar) {
                    if (!hVar.f19445u || hVar.f19446v) {
                        return -1L;
                    }
                    try {
                        hVar.V();
                        break;
                    } catch (IOException unused) {
                        hVar.f19447w = true;
                    }
                    try {
                        if (hVar.x()) {
                            hVar.R();
                            hVar.f19442r = 0;
                        }
                        break;
                    } catch (IOException unused2) {
                        hVar.x = true;
                        hVar.f19440p = new h0(new xb.f());
                        break;
                    }
                    return -1L;
                }
            case 1:
                ((r7.a) this.f19434f).invoke();
                return -1L;
            case 2:
                return b();
            default:
                o oVar = (o) this.f19434f;
                oVar.getClass();
                try {
                    oVar.H.x(2, 0, false);
                    return -1L;
                } catch (IOException e5) {
                    oVar.i(2, 2, e5);
                    return -1L;
                }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ g(String str, Object obj, int i10) {
        super(str, true);
        this.f19433e = i10;
        this.f19434f = obj;
    }
}
