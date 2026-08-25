package pa;

import com.google.common.util.concurrent.r0;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.internal.DiagnosticCoroutineContextException;
import kotlinx.coroutines.internal.ExceptionSuccessfullyProcessed;

/* JADX INFO: loaded from: classes5.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c2.a f21248a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c2.a f21249b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c2.a f21250c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final c2.a f21251d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final c2.a f21252e;

    static {
        int i10 = 5;
        f21248a = new c2.a("NO_DECISION", i10);
        f21249b = new c2.a("CLOSED", i10);
        f21250c = new c2.a("UNDEFINED", i10);
        f21251d = new c2.a("REUSABLE_CLAIMED", i10);
        f21252e = new c2.a("CONDITION_FALSE", i10);
    }

    public static final void a(int i10) {
        if (i10 < 1) {
            throw new IllegalArgumentException(a0.c.i(i10, "Expected positive parallelism level, but got ").toString());
        }
    }

    public static final Object b(w wVar, long j10, r7.p pVar) {
        while (true) {
            if (wVar.f21298m >= j10 && !wVar.c()) {
                return wVar;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = d.f21258i;
            Object obj = atomicReferenceFieldUpdater.get(wVar);
            c2.a aVar = f21249b;
            if (obj == aVar) {
                return aVar;
            }
            w wVar2 = (w) ((d) obj);
            if (wVar2 == null) {
                wVar2 = (w) pVar.invoke(Long.valueOf(wVar.f21298m + 1), wVar);
                while (!atomicReferenceFieldUpdater.compareAndSet(wVar, null, wVar2)) {
                    if (atomicReferenceFieldUpdater.get(wVar) != null) {
                        break;
                    }
                }
                if (wVar.c()) {
                    wVar.d();
                }
            }
            wVar = wVar2;
        }
    }

    public static final w c(Object obj) {
        if (obj != f21249b) {
            return (w) obj;
        }
        throw new IllegalStateException("Does not contain segment");
    }

    public static final void d(d7.j jVar, Throwable th) throws IllegalAccessException, InvocationTargetException {
        Throwable runtimeException;
        Iterator it = f.f21263a.iterator();
        while (it.hasNext()) {
            try {
                ((CoroutineExceptionHandler) it.next()).handleException(jVar, th);
            } catch (ExceptionSuccessfullyProcessed unused) {
                return;
            } catch (Throwable th2) {
                if (th == th2) {
                    runtimeException = th;
                } else {
                    runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
                    r0.a(runtimeException, th);
                }
                Thread threadCurrentThread = Thread.currentThread();
                threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, runtimeException);
            }
        }
        try {
            r0.a(th, new DiagnosticCoroutineContextException(jVar));
        } catch (Throwable unused2) {
        }
        Thread threadCurrentThread2 = Thread.currentThread();
        threadCurrentThread2.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread2, th);
    }

    public static final boolean e(Object obj) {
        return obj == f21249b;
    }

    public static final Object f(Object obj, Object obj2) {
        if (obj == null) {
            return obj2;
        }
        if (obj instanceof ArrayList) {
            ((ArrayList) obj).add(obj2);
            return obj;
        }
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(obj);
        arrayList.add(obj2);
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x008f A[Catch: all -> 0x006e, DONT_GENERATE, TryCatch #2 {all -> 0x006e, blocks: (B:16:0x004a, B:18:0x0058, B:20:0x005e, B:33:0x0092, B:23:0x0070, B:25:0x007e, B:30:0x0089, B:32:0x008f, B:38:0x009f, B:41:0x00a8, B:40:0x00a5, B:28:0x0084), top: B:54:0x004a, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void g(d7.d r9, java.lang.Object r10) {
        /*
            boolean r0 = r9 instanceof pa.g
            if (r0 == 0) goto Lb3
            pa.g r9 = (pa.g) r9
            ka.f0 r0 = r9.f21269n
            d7.d r1 = r9.f21270o
            java.lang.Throwable r2 = x6.d0.a(r10)
            if (r2 != 0) goto L12
            r3 = r10
            goto L18
        L12:
            ka.v r3 = new ka.v
            r4 = 0
            r3.<init>(r2, r4)
        L18:
            d7.j r2 = r1.getContext()
            boolean r2 = r0.isDispatchNeeded(r2)
            r4 = 1
            if (r2 == 0) goto L2f
            r9.f21271p = r3
            r9.f19650m = r4
            d7.j r10 = r1.getContext()
            r0.dispatch(r10, r9)
            return
        L2f:
            ka.c1 r0 = ka.r2.a()
            long r5 = r0.f19564i
            r7 = 4294967296(0x100000000, double:2.121995791E-314)
            int r2 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r2 < 0) goto L46
            r9.f21271p = r3
            r9.f19650m = r4
            r0.t0(r9)
            goto Lad
        L46:
            r0.u0(r4)
            r2 = 0
            d7.j r5 = r1.getContext()     // Catch: java.lang.Throwable -> L6e
            ka.u1 r6 = ka.u1.f19642i     // Catch: java.lang.Throwable -> L6e
            d7.j$a r5 = r5.get(r6)     // Catch: java.lang.Throwable -> L6e
            ka.v1 r5 = (ka.v1) r5     // Catch: java.lang.Throwable -> L6e
            if (r5 == 0) goto L70
            boolean r6 = r5.isActive()     // Catch: java.lang.Throwable -> L6e
            if (r6 != 0) goto L70
            java.util.concurrent.CancellationException r10 = r5.getCancellationException()     // Catch: java.lang.Throwable -> L6e
            r9.b(r3, r10)     // Catch: java.lang.Throwable -> L6e
            x6.c0 r1 = new x6.c0     // Catch: java.lang.Throwable -> L6e
            r1.<init>(r10)     // Catch: java.lang.Throwable -> L6e
            r9.resumeWith(r1)     // Catch: java.lang.Throwable -> L6e
            goto L92
        L6e:
            r10 = move-exception
            goto La9
        L70:
            java.lang.Object r3 = r9.f21272q     // Catch: java.lang.Throwable -> L6e
            d7.j r5 = r1.getContext()     // Catch: java.lang.Throwable -> L6e
            java.lang.Object r3 = pa.c0.b(r5, r3)     // Catch: java.lang.Throwable -> L6e
            c2.a r6 = pa.c0.f21257a     // Catch: java.lang.Throwable -> L6e
            if (r3 == r6) goto L83
            ka.w2 r6 = ka.c0.c(r1, r5, r3)     // Catch: java.lang.Throwable -> L6e
            goto L84
        L83:
            r6 = r2
        L84:
            r1.resumeWith(r10)     // Catch: java.lang.Throwable -> L9c
            if (r6 == 0) goto L8f
            boolean r10 = r6.U()     // Catch: java.lang.Throwable -> L6e
            if (r10 == 0) goto L92
        L8f:
            pa.c0.a(r5, r3)     // Catch: java.lang.Throwable -> L6e
        L92:
            boolean r10 = r0.J0()     // Catch: java.lang.Throwable -> L6e
            if (r10 != 0) goto L92
        L98:
            r0.q0(r4)
            goto Lad
        L9c:
            r10 = move-exception
            if (r6 == 0) goto La5
            boolean r1 = r6.U()     // Catch: java.lang.Throwable -> L6e
            if (r1 == 0) goto La8
        La5:
            pa.c0.a(r5, r3)     // Catch: java.lang.Throwable -> L6e
        La8:
            throw r10     // Catch: java.lang.Throwable -> L6e
        La9:
            r9.j(r10, r2)     // Catch: java.lang.Throwable -> Lae
            goto L98
        Lad:
            return
        Lae:
            r9 = move-exception
            r0.q0(r4)
            throw r9
        Lb3:
            r9.resumeWith(r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: pa.a.g(d7.d, java.lang.Object):void");
    }

    public static final long i(String str, long j10, long j11, long j12) {
        String property;
        int i10 = y.f21300a;
        try {
            property = System.getProperty(str);
        } catch (SecurityException unused) {
            property = null;
        }
        if (property == null) {
            return j10;
        }
        Long lS = kotlin.text.u.S(property);
        if (lS == null) {
            throw new IllegalStateException(("System property '" + str + "' has unrecognized value '" + property + '\'').toString());
        }
        long jLongValue = lS.longValue();
        if (j11 <= jLongValue && jLongValue <= j12) {
            return jLongValue;
        }
        StringBuilder sb2 = new StringBuilder("System property '");
        sb2.append(str);
        sb2.append("' should be in range ");
        sb2.append(j11);
        androidx.compose.foundation.c.z(sb2, "..", j12, ", but is '");
        sb2.append(jLongValue);
        sb2.append('\'');
        throw new IllegalStateException(sb2.toString().toString());
    }

    public static int j(int i10, int i11, String str) {
        return (int) i(str, i10, 1, (i11 & 8) != 0 ? Integer.MAX_VALUE : 2097150);
    }
}
