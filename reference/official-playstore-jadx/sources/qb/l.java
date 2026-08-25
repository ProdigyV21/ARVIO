package qb;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Build;
import android.os.Looper;
import android.os.Process;
import android.view.KeyEvent;
import android.view.View;
import androidx.appcompat.app.a1;
import androidx.recyclerview.widget.l1;
import androidx.recyclerview.widget.z0;
import com.google.android.gms.tasks.t;
import com.google.common.util.concurrent.p0;
import com.google.common.util.concurrent.w0;
import gb.u0;
import gb.y;
import h.f0;
import io.sentry.j0;
import j$.util.Objects;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.q0;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.jvm.internal.p;
import t8.e0;
import v9.b1;
import v9.o0;
import v9.q;
import v9.r0;
import v9.w;
import v9.y0;

/* JADX INFO: loaded from: classes5.dex */
public abstract class l implements a6.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static SharedPreferences f21382a;

    public static final Object A(Set set, Enum r22, Enum r32, Enum r4, boolean z) {
        if (!z) {
            if (r4 != null) {
                set = x.g1(q0.c0(set, r4));
            }
            return x.S0(set);
        }
        Enum r1 = set.contains(r22) ? r22 : set.contains(r32) ? r32 : null;
        if (p.a(r1, r22) && p.a(r4, r32)) {
            return null;
        }
        return r4 == null ? r1 : r4;
    }

    public static boolean B(j0 j0Var) {
        return !(io.sentry.hints.e.class.isInstance(j0Var.b("sentry:typeCheckHint")) || io.sentry.hints.c.class.isInstance(j0Var.b("sentry:typeCheckHint"))) || io.sentry.hints.b.class.isInstance(j0Var.b("sentry:typeCheckHint"));
    }

    public static u8.a C(int i10, boolean z, e0 e0Var, int i11) {
        boolean z5 = (i11 & 1) != 0 ? false : z;
        boolean z10 = (i11 & 2) == 0;
        if ((i11 & 4) != 0) {
            e0Var = null;
        }
        return new u8.a(i10, z10, z5, e0Var != null ? Collections.singleton(e0Var) : null, 34);
    }

    public static t D(List list) {
        if (list == null || list.isEmpty()) {
            return n(null);
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((com.google.android.gms.tasks.h) it.next()) == null) {
                throw new NullPointerException("null tasks are not accepted");
            }
        }
        t tVar = new t();
        com.google.android.gms.tasks.m mVar = new com.google.android.gms.tasks.m(list.size(), tVar);
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            com.google.android.gms.tasks.h hVar = (com.google.android.gms.tasks.h) it2.next();
            a1 a1Var = com.google.android.gms.tasks.j.f13857b;
            hVar.c(a1Var, mVar);
            hVar.b(a1Var, mVar);
            t tVar2 = (t) hVar;
            tVar2.f13881b.a(new com.google.android.gms.tasks.p((Executor) a1Var, (com.google.android.gms.tasks.b) mVar));
            tVar2.n();
        }
        return tVar;
    }

    public static SharedPreferences E(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (SharedPreferences.class) {
            try {
                if (f21382a == null) {
                    f21382a = (SharedPreferences) p0.F(new androidx.loader.content.g(context, 2));
                }
                sharedPreferences = f21382a;
            } catch (Throwable th) {
                throw th;
            }
        }
        return sharedPreferences;
    }

    public static Object F(com.google.android.gms.tasks.h hVar) throws ExecutionException {
        if (hVar.h()) {
            return hVar.f();
        }
        if (((t) hVar).f13883d) {
            throw new CancellationException("Task is already canceled");
        }
        throw new ExecutionException(hVar.e());
    }

    public static final String a(Object[] objArr, int i10, int i11, kotlin.collections.j jVar) {
        StringBuilder sb2 = new StringBuilder((i11 * 3) + 2);
        sb2.append("[");
        for (int i12 = 0; i12 < i11; i12++) {
            if (i12 > 0) {
                sb2.append(", ");
            }
            Object obj = objArr[i10 + i12];
            if (obj == jVar) {
                sb2.append("(this Collection)");
            } else {
                sb2.append(obj);
            }
        }
        sb2.append("]");
        return sb2.toString();
    }

    public static final aa.a b(w wVar) {
        aa.d dVar;
        if (wVar.L0() instanceof q) {
            aa.a aVarB = b(v9.c.h(wVar));
            aa.a aVarB2 = b(v9.c.v(wVar));
            return new aa.a(v9.c.x(v9.e.j(v9.c.h((w) aVarB.f771a), v9.c.v((w) aVarB2.f771a)), v9.c.d(wVar)), v9.c.x(v9.e.j(v9.c.h((w) aVarB.f772b), v9.c.v((w) aVarB2.f772b)), v9.c.d(wVar)));
        }
        o0 o0VarI0 = wVar.I0();
        boolean z = true;
        if (wVar.I0() instanceof j9.b) {
            r0 r0VarB = ((j9.b) o0VarI0).b();
            w wVarH = b1.h(r0VarB.getType(), wVar.J0());
            int iC = f0.c(r0VarB.c());
            if (iC == 1) {
                return new aa.a(wVarH, wVar.I0().j().o());
            }
            if (iC == 2) {
                return new aa.a(b1.h(wVar.I0().j().n(), wVar.J0()), wVarH);
            }
            throw new AssertionError("Only nontrivial projections should have been captured, not: " + r0VarB);
        }
        if (wVar.G0().isEmpty() || wVar.G0().size() != o0VarI0.getParameters().size()) {
            return new aa.a(wVar, wVar);
        }
        ArrayList<aa.d> arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (x6.x xVar : x.i1(wVar.G0(), o0VarI0.getParameters())) {
            r0 r0Var = (r0) xVar.f22608i;
            g8.b1 b1Var = (g8.b1) xVar.f22609l;
            int iY = b1Var.y();
            if (iY == 0) {
                y0.a(35);
                throw null;
            }
            if (r0Var == null) {
                y0.a(36);
                throw null;
            }
            y0 y0Var = y0.f22262b;
            int iC2 = f0.c(r0Var.b() ? 3 : y0.b(iY, r0Var.c()));
            if (iC2 == 0) {
                dVar = new aa.d(b1Var, r0Var.getType(), r0Var.getType());
            } else if (iC2 == 1) {
                dVar = new aa.d(b1Var, r0Var.getType(), m9.d.e(b1Var).o());
            } else {
                if (iC2 != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                dVar = new aa.d(b1Var, m9.d.e(b1Var).n(), r0Var.getType());
            }
            if (r0Var.b()) {
                arrayList.add(dVar);
                arrayList2.add(dVar);
            } else {
                aa.a aVarB3 = b(dVar.f775b);
                w wVar2 = (w) aVarB3.f771a;
                w wVar3 = (w) aVarB3.f772b;
                aa.a aVarB4 = b(dVar.f776c);
                w wVar4 = (w) aVarB4.f771a;
                w wVar5 = (w) aVarB4.f772b;
                g8.b1 b1Var2 = dVar.f774a;
                aa.d dVar2 = new aa.d(b1Var2, wVar3, wVar4);
                aa.d dVar3 = new aa.d(b1Var2, wVar2, wVar5);
                arrayList.add(dVar2);
                arrayList2.add(dVar3);
            }
        }
        if (arrayList.isEmpty()) {
            z = false;
        } else {
            for (aa.d dVar4 : arrayList) {
                dVar4.getClass();
                if (!w9.c.f22506a.d(dVar4.f775b, dVar4.f776c)) {
                    break;
                }
            }
            z = false;
        }
        return new aa.a(z ? wVar.I0().j().n() : x(wVar, arrayList), x(wVar, arrayList2));
    }

    public static Object c(com.google.android.gms.tasks.h hVar) throws InterruptedException {
        com.google.android.gms.common.internal.t.h("Must not be called on the main application thread");
        Looper looperMyLooper = Looper.myLooper();
        if (looperMyLooper != null && Objects.equals(looperMyLooper.getThread().getName(), "GoogleApiHandler")) {
            throw new IllegalStateException("Must not be called on GoogleApiHandler thread.");
        }
        com.google.android.gms.common.internal.t.j(hVar, "Task must not be null");
        if (hVar.g()) {
            return F(hVar);
        }
        com.google.android.gms.tasks.k kVar = new com.google.android.gms.tasks.k();
        Executor executor = com.google.android.gms.tasks.j.f13857b;
        hVar.c(executor, kVar);
        hVar.b(executor, kVar);
        t tVar = (t) hVar;
        tVar.f13881b.a(new com.google.android.gms.tasks.p(executor, (com.google.android.gms.tasks.b) kVar));
        tVar.n();
        kVar.f13858i.await();
        return F(hVar);
    }

    public static t d(Executor executor, Callable callable) {
        com.google.android.gms.common.internal.t.j(executor, "Executor must not be null");
        t tVar = new t();
        executor.execute(new w0(tVar, callable, 22));
        return tVar;
    }

    public static int e(Context context, String str) {
        int iC;
        int iMyPid = Process.myPid();
        int iMyUid = Process.myUid();
        String packageName = context.getPackageName();
        if (context.checkPermission(str, iMyPid, iMyUid) != -1) {
            String strD = androidx.core.app.j.d(str);
            if (strD != null) {
                if (packageName == null) {
                    String[] packagesForUid = context.getPackageManager().getPackagesForUid(iMyUid);
                    if (packagesForUid != null && packagesForUid.length > 0) {
                        packageName = packagesForUid[0];
                    }
                }
                int iMyUid2 = Process.myUid();
                String packageName2 = context.getPackageName();
                if (iMyUid2 == iMyUid && Objects.equals(packageName2, packageName) && Build.VERSION.SDK_INT >= 29) {
                    AppOpsManager appOpsManagerC = androidx.core.app.k.c(context);
                    iC = androidx.core.app.k.a(appOpsManagerC, strD, Binder.getCallingUid(), packageName);
                    if (iC == 0) {
                        iC = androidx.core.app.k.a(appOpsManagerC, strD, iMyUid, androidx.core.app.k.b(context));
                    }
                } else {
                    iC = androidx.core.app.j.c((AppOpsManager) androidx.core.app.j.a(context, AppOpsManager.class), strD, packageName);
                }
                if (iC != 0) {
                    return -2;
                }
            }
            return 0;
        }
        return -1;
    }

    public static byte f(long j10) {
        ac.b.g(j10, "out of range: %s", (j10 >> 8) == 0);
        return (byte) j10;
    }

    public static int g(l1 l1Var, androidx.emoji2.text.h hVar, View view, View view2, z0 z0Var, boolean z) {
        if (z0Var.w() == 0 || l1Var.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return Math.abs(z0.L(view) - z0.L(view2)) + 1;
        }
        return Math.min(hVar.l(), hVar.b(view2) - hVar.e(view));
    }

    public static int h(l1 l1Var, androidx.emoji2.text.h hVar, View view, View view2, z0 z0Var, boolean z, boolean z5) {
        if (z0Var.w() == 0 || l1Var.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        int iMax = z5 ? Math.max(0, (l1Var.b() - Math.max(z0.L(view), z0.L(view2))) - 1) : Math.max(0, Math.min(z0.L(view), z0.L(view2)));
        if (z) {
            return Math.round((iMax * (Math.abs(hVar.b(view2) - hVar.e(view)) / (Math.abs(z0.L(view) - z0.L(view2)) + 1))) + (hVar.k() - hVar.e(view)));
        }
        return iMax;
    }

    public static int i(l1 l1Var, androidx.emoji2.text.h hVar, View view, View view2, z0 z0Var, boolean z) {
        if (z0Var.w() == 0 || l1Var.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return l1Var.b();
        }
        return (int) (((hVar.b(view2) - hVar.e(view)) / (Math.abs(z0.L(view) - z0.L(view2)) + 1)) * l1Var.b());
    }

    public static final void j(int i10, int i11) {
        if (i10 > i11) {
            throw new IndexOutOfBoundsException(androidx.compose.foundation.c.s("toIndex (", i10, ") is greater than size (", i11, ")."));
        }
    }

    public static j0 k(Object obj) {
        j0 j0Var = new j0();
        j0Var.d("sentry:typeCheckHint", obj);
        return j0Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x06fc  */
    /* JADX WARN: Removed duplicated region for block: B:394:0x0723  */
    /* JADX WARN: Removed duplicated region for block: B:420:0x0771  */
    /* JADX WARN: Removed duplicated region for block: B:433:0x0795  */
    /* JADX WARN: Removed duplicated region for block: B:435:0x079a  */
    /* JADX WARN: Removed duplicated region for block: B:582:0x079c A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v12, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r9v23 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static b6.b l(int r38, int r39, java.lang.String r40) {
        /*
            Method dump skipped, instruction units count: 2397
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: qb.l.l(int, int, java.lang.String):b6.b");
    }

    public static t m(Exception exc) {
        t tVar = new t();
        tVar.k(exc);
        return tVar;
    }

    public static t n(Object obj) {
        t tVar = new t();
        tVar.j(obj);
        return tVar;
    }

    public static y o(SSLSession sSLSession) throws IOException {
        Certificate[] peerCertificates;
        List listL = z.f19728i;
        String cipherSuite = sSLSession.getCipherSuite();
        if (cipherSuite == null) {
            throw new IllegalStateException("cipherSuite == null");
        }
        if (cipherSuite.equals("TLS_NULL_WITH_NULL_NULL") ? true : cipherSuite.equals("SSL_NULL_WITH_NULL_NULL")) {
            throw new IOException("cipherSuite == ".concat(cipherSuite));
        }
        gb.m mVarE = gb.m.f15739b.e(cipherSuite);
        String protocol = sSLSession.getProtocol();
        if (protocol == null) {
            throw new IllegalStateException("tlsVersion == null");
        }
        if ("NONE".equals(protocol)) {
            throw new IOException("tlsVersion == NONE");
        }
        u0 u0VarM = a.a.M(protocol);
        try {
            peerCertificates = sSLSession.getPeerCertificates();
        } catch (SSLPeerUnverifiedException unused) {
        }
        List listL2 = peerCertificates != null ? ib.c.l(Arrays.copyOf(peerCertificates, peerCertificates.length)) : listL;
        Certificate[] localCertificates = sSLSession.getLocalCertificates();
        if (localCertificates != null) {
            listL = ib.c.l(Arrays.copyOf(localCertificates, localCertificates.length));
        }
        return new y(u0VarM, mVarE, listL, new gb.x(listL2, 1));
    }

    public static String p(KeyEvent.Callback callback) {
        if (callback == null) {
            return null;
        }
        String canonicalName = callback.getClass().getCanonicalName();
        return canonicalName != null ? canonicalName : callback.getClass().getSimpleName();
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.Object, java.util.Map] */
    public static final String q(g8.w wVar) {
        f9.f fVar;
        g8.c cVarR = d8.k.z(wVar) ? r(wVar) : null;
        if (cVarR != null) {
            g8.c cVarI = m9.d.i(cVarR);
            if (cVarI instanceof g8.p0) {
                d8.k.z(cVarI);
                g8.c cVarB = m9.d.b(m9.d.i(cVarI), p8.e.f21128n);
                if (cVarB != null && (fVar = (f9.f) p8.g.f21140a.get(m9.d.g(cVarB))) != null) {
                    return fVar.d();
                }
            } else if (cVarI instanceof g8.u0) {
                int i10 = p8.d.f21123l;
                LinkedHashMap linkedHashMap = p8.j0.f21165i;
                String strG = com.google.common.util.concurrent.r0.g((g8.u0) cVarI);
                f9.f fVar2 = strG == null ? null : (f9.f) linkedHashMap.get(strG);
                if (fVar2 != null) {
                    return fVar2.d();
                }
            }
        }
        return null;
    }

    public static final g8.c r(g8.c cVar) {
        if (!p8.j0.f21166j.contains(cVar.getName()) && !p8.g.f21143d.contains(m9.d.i(cVar).getName())) {
            return null;
        }
        if (cVar instanceof g8.p0 ? true : cVar instanceof g8.o0) {
            return m9.d.b(cVar, p8.e.f21130p);
        }
        if (cVar instanceof g8.u0) {
            return m9.d.b(cVar, p8.e.f21131q);
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x012b, code lost:
    
        if (r6 == null) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0132, code lost:
    
        return !d8.k.z(r12);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean s(g8.f r12, g8.c r13) {
        /*
            Method dump skipped, instruction units count: 341
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: qb.l.s(g8.f, g8.c):boolean");
    }

    public static boolean t(j0 j0Var, Class cls) {
        return cls.isInstance(j0Var.b("sentry:typeCheckHint"));
    }

    public static boolean u(j0 j0Var) {
        return Boolean.TRUE.equals(j0Var.c(Boolean.class, "sentry:isFromHybridSdk"));
    }

    public static final boolean v(String str) {
        return (str.equals("GET") || str.equals("HEAD")) ? false : true;
    }

    public static androidx.emoji2.text.flatbuffer.b w(MappedByteBuffer mappedByteBuffer) throws IOException {
        long j10;
        ByteBuffer byteBufferDuplicate = mappedByteBuffer.duplicate();
        byteBufferDuplicate.order(ByteOrder.BIG_ENDIAN);
        byteBufferDuplicate.position(byteBufferDuplicate.position() + 4);
        int i10 = byteBufferDuplicate.getShort() & 65535;
        if (i10 > 100) {
            throw new IOException("Cannot read metadata.");
        }
        byteBufferDuplicate.position(byteBufferDuplicate.position() + 6);
        int i11 = 0;
        while (true) {
            if (i11 >= i10) {
                j10 = -1;
                break;
            }
            int i12 = byteBufferDuplicate.getInt();
            byteBufferDuplicate.position(byteBufferDuplicate.position() + 4);
            j10 = ((long) byteBufferDuplicate.getInt()) & 4294967295L;
            byteBufferDuplicate.position(byteBufferDuplicate.position() + 4);
            if (1835365473 == i12) {
                break;
            }
            i11++;
        }
        if (j10 != -1) {
            byteBufferDuplicate.position(byteBufferDuplicate.position() + ((int) (j10 - ((long) byteBufferDuplicate.position()))));
            byteBufferDuplicate.position(byteBufferDuplicate.position() + 12);
            long j11 = ((long) byteBufferDuplicate.getInt()) & 4294967295L;
            for (int i13 = 0; i13 < j11; i13++) {
                int i14 = byteBufferDuplicate.getInt();
                long j12 = ((long) byteBufferDuplicate.getInt()) & 4294967295L;
                byteBufferDuplicate.getInt();
                if (1164798569 == i14 || 1701669481 == i14) {
                    byteBufferDuplicate.position((int) (j12 + j10));
                    androidx.emoji2.text.flatbuffer.b bVar = new androidx.emoji2.text.flatbuffer.b();
                    byteBufferDuplicate.order(ByteOrder.LITTLE_ENDIAN);
                    int iPosition = byteBufferDuplicate.position() + byteBufferDuplicate.getInt(byteBufferDuplicate.position());
                    bVar.f2241n = byteBufferDuplicate;
                    bVar.f2238i = iPosition;
                    int i15 = iPosition - byteBufferDuplicate.getInt(iPosition);
                    bVar.f2239l = i15;
                    bVar.f2240m = ((ByteBuffer) bVar.f2241n).getShort(i15);
                    return bVar;
                }
            }
        }
        throw new IOException("Cannot read metadata.");
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x008d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final v9.w x(v9.w r9, java.util.ArrayList r10) {
        /*
            java.util.List r0 = r9.G0()
            r0.size()
            r10.size()
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 10
            int r1 = kotlin.collections.s.U(r10, r1)
            r0.<init>(r1)
            java.util.Iterator r10 = r10.iterator()
        L19:
            boolean r1 = r10.hasNext()
            r2 = 0
            if (r1 == 0) goto L96
            java.lang.Object r1 = r10.next()
            aa.d r1 = (aa.d) r1
            r1.getClass()
            v9.w r3 = r1.f776c
            v9.w r4 = r1.f775b
            g8.b1 r1 = r1.f774a
            w9.l r5 = w9.c.f22506a
            r5.d(r4, r3)
            boolean r5 = kotlin.jvm.internal.p.a(r4, r3)
            if (r5 != 0) goto L8d
            int r5 = r1.y()
            r6 = 2
            if (r5 != r6) goto L42
            goto L8d
        L42:
            boolean r5 = d8.k.E(r4)
            r7 = 1
            r8 = 3
            if (r5 == 0) goto L5e
            int r5 = r1.y()
            if (r5 == r6) goto L5e
            v9.t0 r2 = new v9.t0
            int r1 = r1.y()
            if (r8 != r1) goto L59
            goto L5a
        L59:
            r7 = r8
        L5a:
            r2.<init>(r7, r3)
            goto L92
        L5e:
            if (r3 == 0) goto L87
            boolean r2 = d8.k.x(r3)
            if (r2 == 0) goto L79
            boolean r2 = r3.J0()
            if (r2 == 0) goto L79
            v9.t0 r2 = new v9.t0
            int r1 = r1.y()
            if (r6 != r1) goto L75
            r6 = r7
        L75:
            r2.<init>(r6, r4)
            goto L92
        L79:
            v9.t0 r2 = new v9.t0
            int r1 = r1.y()
            if (r8 != r1) goto L82
            goto L83
        L82:
            r7 = r8
        L83:
            r2.<init>(r7, r3)
            goto L92
        L87:
            r9 = 141(0x8d, float:1.98E-43)
            d8.k.a(r9)
            throw r2
        L8d:
            v9.t0 r2 = new v9.t0
            r2.<init>(r4)
        L92:
            r0.add(r2)
            goto L19
        L96:
            r10 = 6
            v9.w r9 = v9.c.l(r9, r0, r2, r10)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: qb.l.x(v9.w, java.util.ArrayList):v9.w");
    }

    public static final void y(Object[] objArr, int i10, int i11) {
        while (i10 < i11) {
            objArr[i10] = null;
            i10++;
        }
    }

    public static final s8.e z(androidx.core.provider.e eVar, w8.d dVar) {
        return new s8.e(eVar, dVar, false);
    }
}
