package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.fragment.app.a2;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.internal.auth.j4;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes4.dex */
public final class e0 implements com.google.android.gms.common.api.o, com.google.android.gms.common.api.p, h1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final com.google.android.gms.common.api.g f12717d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a f12718e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final io.sentry.internal.debugmeta.c f12719f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f12722i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final v0 f12723j;
    public boolean k;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ h f12727o;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LinkedList f12716c = new LinkedList();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final HashSet f12720g = new HashSet();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final HashMap f12721h = new HashMap();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ArrayList f12724l = new ArrayList();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public o3.b f12725m = null;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f12726n = 0;

    public e0(h hVar, com.google.android.gms.common.api.n nVar) {
        this.f12727o = hVar;
        androidx.loader.content.j jVar = hVar.x;
        com.google.android.gms.common.api.g gVarZaa = nVar.zaa(jVar.getLooper(), this);
        this.f12717d = gVarZaa;
        this.f12718e = nVar.getApiKey();
        this.f12719f = new io.sentry.internal.debugmeta.c(14);
        this.f12722i = nVar.zab();
        if (gVarZaa.requiresSignIn()) {
            this.f12723j = nVar.zac(hVar.f12744o, jVar);
        } else {
            this.f12723j = null;
        }
    }

    @Override // com.google.android.gms.common.api.internal.o
    public final void a(o3.b bVar) {
        o(bVar, null);
    }

    public final void b() {
        com.google.android.gms.common.api.g gVar = this.f12717d;
        h hVar = this.f12727o;
        com.google.android.gms.common.internal.t.d(hVar.x);
        this.f12725m = null;
        l(o3.b.f20727p);
        if (this.k) {
            androidx.loader.content.j jVar = hVar.x;
            a aVar = this.f12718e;
            jVar.removeMessages(11, aVar);
            hVar.x.removeMessages(9, aVar);
            this.k = false;
        }
        Iterator it = this.f12721h.values().iterator();
        while (it.hasNext()) {
            q qVar = ((r0) it.next()).f12791a;
            if (m(qVar.f12781b) != null) {
                it.remove();
            } else {
                try {
                    ((t) ((s0) qVar).f12796d.f12787b).accept(gVar, new com.google.android.gms.tasks.i());
                } catch (DeadObjectException unused) {
                    c(3);
                    gVar.disconnect("DeadObjectException thrown while calling register listener method.");
                } catch (RemoteException e5) {
                    e = e5;
                    Log.e("GoogleApiManager", "Failed to register listener on re-connection.", e);
                    it.remove();
                } catch (RuntimeException e6) {
                    e = e6;
                    Log.e("GoogleApiManager", "Failed to register listener on re-connection.", e);
                    it.remove();
                }
            }
        }
        f();
        j();
    }

    @Override // com.google.android.gms.common.api.internal.g
    public final void c(int i10) {
        h hVar = this.f12727o;
        if (Looper.myLooper() == hVar.x.getLooper()) {
            d(i10);
        } else {
            hVar.x.post(new androidx.core.provider.a(this, i10, 3));
        }
    }

    public final void d(int i10) {
        com.google.android.gms.common.internal.t.d(this.f12727o.x);
        this.f12725m = null;
        this.k = true;
        String lastDisconnectMessage = this.f12717d.getLastDisconnectMessage();
        io.sentry.internal.debugmeta.c cVar = this.f12719f;
        cVar.getClass();
        StringBuilder sb2 = new StringBuilder("The connection to Google Play services was lost");
        if (i10 == 1) {
            sb2.append(" due to service disconnection.");
        } else if (i10 == 3) {
            sb2.append(" due to dead object exception.");
        }
        if (lastDisconnectMessage != null) {
            sb2.append(" Last reason for disconnect: ");
            sb2.append(lastDisconnectMessage);
        }
        cVar.N(true, new Status(20, sb2.toString(), null, null));
        a aVar = this.f12718e;
        h hVar = this.f12727o;
        androidx.loader.content.j jVar = hVar.x;
        jVar.sendMessageDelayed(Message.obtain(jVar, 9, aVar), 5000L);
        androidx.loader.content.j jVar2 = hVar.x;
        jVar2.sendMessageDelayed(Message.obtain(jVar2, 11, aVar), 120000L);
        SparseIntArray sparseIntArray = (SparseIntArray) hVar.f12746q.f17367l;
        synchronized (sparseIntArray) {
            sparseIntArray.clear();
        }
        Iterator it = this.f12721h.values().iterator();
        while (it.hasNext()) {
            ((r0) it.next()).f12793c.run();
        }
    }

    public final boolean e(o3.b bVar) {
        synchronized (h.B) {
            try {
                h hVar = this.f12727o;
                if (hVar.f12750u == null || !hVar.f12751v.contains(this.f12718e)) {
                    return false;
                }
                a0 a0Var = hVar.f12750u;
                int i10 = this.f12722i;
                a0Var.getClass();
                e1 e1Var = new e1(bVar, i10);
                AtomicReference atomicReference = a0Var.f12695m;
                while (true) {
                    if (atomicReference.compareAndSet(null, e1Var)) {
                        a0Var.f12696n.post(new com.google.common.util.concurrent.w0(a0Var, e1Var));
                        break;
                    }
                    if (atomicReference.get() != null) {
                        break;
                    }
                }
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void f() {
        LinkedList linkedList = this.f12716c;
        ArrayList arrayList = new ArrayList(linkedList);
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            d1 d1Var = (d1) arrayList.get(i10);
            if (!this.f12717d.isConnected()) {
                return;
            }
            if (g(d1Var)) {
                linkedList.remove(d1Var);
            }
        }
    }

    public final boolean g(d1 d1Var) {
        if (!(d1Var instanceof p0)) {
            io.sentry.internal.debugmeta.c cVar = this.f12719f;
            com.google.android.gms.common.api.g gVar = this.f12717d;
            d1Var.c(cVar, gVar.requiresSignIn());
            try {
                d1Var.d(this);
                return true;
            } catch (DeadObjectException unused) {
                c(1);
                gVar.disconnect("DeadObjectException thrown while running ApiCallRunner.");
                return true;
            }
        }
        p0 p0Var = (p0) d1Var;
        o3.d dVarM = m(p0Var.f(this));
        if (dVarM == null) {
            io.sentry.internal.debugmeta.c cVar2 = this.f12719f;
            com.google.android.gms.common.api.g gVar2 = this.f12717d;
            d1Var.c(cVar2, gVar2.requiresSignIn());
            try {
                d1Var.d(this);
                return true;
            } catch (DeadObjectException unused2) {
                c(1);
                gVar2.disconnect("DeadObjectException thrown while running ApiCallRunner.");
                return true;
            }
        }
        String name = this.f12717d.getClass().getName();
        String str = dVarM.f20736i;
        long jD = dVarM.d();
        int length = name.length();
        StringBuilder sb2 = new StringBuilder(length + 53 + String.valueOf(str).length() + 2 + String.valueOf(jD).length() + 2);
        y.a.i(sb2, name, " could not execute call because it requires feature (", str, ", ");
        sb2.append(jD);
        sb2.append(").");
        Log.w("GoogleApiManager", sb2.toString());
        h hVar = this.f12727o;
        if (!hVar.f12753y || !p0Var.g(this)) {
            p0Var.b(new UnsupportedApiCallException(dVarM));
            return true;
        }
        f0 f0Var = new f0(this.f12718e, dVarM);
        ArrayList arrayList = this.f12724l;
        int iIndexOf = arrayList.indexOf(f0Var);
        if (iIndexOf >= 0) {
            f0 f0Var2 = (f0) arrayList.get(iIndexOf);
            hVar.x.removeMessages(15, f0Var2);
            hVar.x.sendMessageDelayed(Message.obtain(hVar.x, 15, f0Var2), 5000L);
            return false;
        }
        arrayList.add(f0Var);
        hVar.x.sendMessageDelayed(Message.obtain(hVar.x, 15, f0Var), 5000L);
        hVar.x.sendMessageDelayed(Message.obtain(hVar.x, 16, f0Var), 120000L);
        o3.b bVar = new o3.b(2, null, null);
        if (e(bVar)) {
            String str2 = dVarM.f20736i;
            long jD2 = dVarM.d();
            StringBuilder sb3 = new StringBuilder(String.valueOf(str2).length() + 61 + String.valueOf(jD2).length());
            sb3.append("A dialog should be displayed for missing feature: ");
            sb3.append(str2);
            sb3.append(", version: ");
            sb3.append(jD2);
            Log.w("GoogleApiManager", sb3.toString());
            return false;
        }
        if (!hVar.i(bVar, this.f12722i)) {
            return false;
        }
        String str3 = dVarM.f20736i;
        long jD3 = dVarM.d();
        StringBuilder sb4 = new StringBuilder(String.valueOf(str3).length() + 55 + String.valueOf(jD3).length());
        sb4.append("Notification displayed for missing feature: ");
        sb4.append(str3);
        sb4.append(", version: ");
        sb4.append(jD3);
        Log.w("GoogleApiManager", sb4.toString());
        return false;
    }

    public final void h(Status status, Exception exc, boolean z) {
        com.google.android.gms.common.internal.t.d(this.f12727o.x);
        if ((status == null) == (exc == null)) {
            throw new IllegalArgumentException("Status XOR exception should be null");
        }
        Iterator it = this.f12716c.iterator();
        while (it.hasNext()) {
            d1 d1Var = (d1) it.next();
            if (!z || d1Var.f12715a == 2) {
                if (status != null) {
                    d1Var.a(status);
                } else {
                    d1Var.b(exc);
                }
                it.remove();
            }
        }
    }

    public final void i(Status status) {
        com.google.android.gms.common.internal.t.d(this.f12727o.x);
        h(status, null, false);
    }

    public final void j() {
        h hVar = this.f12727o;
        androidx.loader.content.j jVar = hVar.x;
        a aVar = this.f12718e;
        jVar.removeMessages(12, aVar);
        androidx.loader.content.j jVar2 = hVar.x;
        jVar2.sendMessageDelayed(jVar2.obtainMessage(12, aVar), hVar.f12740i);
    }

    public final boolean k(boolean z) {
        com.google.android.gms.common.internal.t.d(this.f12727o.x);
        com.google.android.gms.common.api.g gVar = this.f12717d;
        if (!gVar.isConnected() || !this.f12721h.isEmpty()) {
            return false;
        }
        io.sentry.internal.debugmeta.c cVar = this.f12719f;
        if (((Map) cVar.f17367l).isEmpty() && ((Map) cVar.f17368m).isEmpty()) {
            gVar.disconnect("Timing out service connection.");
            return true;
        }
        if (!z) {
            return false;
        }
        j();
        return false;
    }

    public final void l(o3.b bVar) {
        HashSet hashSet = this.f12720g;
        Iterator it = hashSet.iterator();
        if (!it.hasNext()) {
            hashSet.clear();
        } else {
            if (it.next() != null) {
                throw new ClassCastException();
            }
            if (com.google.android.gms.common.internal.t.l(bVar, o3.b.f20727p)) {
                this.f12717d.getEndpointPackageName();
            }
            throw null;
        }
    }

    public final o3.d m(o3.d[] dVarArr) {
        if (dVarArr != null && dVarArr.length != 0) {
            o3.d[] availableFeatures = this.f12717d.getAvailableFeatures();
            if (availableFeatures == null) {
                availableFeatures = new o3.d[0];
            }
            t.e eVar = new t.e(availableFeatures.length);
            for (o3.d dVar : availableFeatures) {
                eVar.put(dVar.f20736i, Long.valueOf(dVar.d()));
            }
            for (o3.d dVar2 : dVarArr) {
                Long l10 = (Long) eVar.get(dVar2.f20736i);
                if (l10 == null || l10.longValue() < dVar2.d()) {
                    return dVar2;
                }
            }
        }
        return null;
    }

    public final void n(o3.b bVar) {
        com.google.android.gms.common.internal.t.d(this.f12727o.x);
        com.google.android.gms.common.api.g gVar = this.f12717d;
        String name = gVar.getClass().getName();
        String strValueOf = String.valueOf(bVar);
        gVar.disconnect(a2.n(new StringBuilder(name.length() + 25 + strValueOf.length()), "onSignInFailed for ", name, " with ", strValueOf));
        o(bVar, null);
    }

    public final void o(o3.b bVar, RuntimeException runtimeException) {
        t4.c cVar;
        h hVar = this.f12727o;
        com.google.android.gms.common.internal.t.d(hVar.x);
        v0 v0Var = this.f12723j;
        if (v0Var != null && (cVar = v0Var.f12804h) != null) {
            cVar.disconnect();
        }
        com.google.android.gms.common.internal.t.d(this.f12727o.x);
        this.f12725m = null;
        SparseIntArray sparseIntArray = (SparseIntArray) hVar.f12746q.f17367l;
        synchronized (sparseIntArray) {
            sparseIntArray.clear();
        }
        l(bVar);
        if ((this.f12717d instanceof u3.d) && bVar.f20729l != 24) {
            hVar.f12741l = true;
            androidx.loader.content.j jVar = hVar.x;
            jVar.sendMessageDelayed(jVar.obtainMessage(19), 300000L);
        }
        int i10 = bVar.f20729l;
        if (i10 == 4) {
            i(h.A);
            return;
        }
        if (i10 == 25) {
            i(h.d(this.f12718e, bVar));
            return;
        }
        LinkedList linkedList = this.f12716c;
        if (linkedList.isEmpty()) {
            this.f12725m = bVar;
            return;
        }
        if (runtimeException != null) {
            com.google.android.gms.common.internal.t.d(hVar.x);
            h(null, runtimeException, false);
            return;
        }
        if (!hVar.f12753y) {
            i(h.d(this.f12718e, bVar));
            return;
        }
        a aVar = this.f12718e;
        h(h.d(aVar, bVar), null, true);
        if (linkedList.isEmpty() || e(bVar) || hVar.i(bVar, this.f12722i)) {
            return;
        }
        if (bVar.f20729l == 18) {
            this.k = true;
        }
        if (!this.k) {
            i(h.d(aVar, bVar));
        } else {
            androidx.loader.content.j jVar2 = hVar.x;
            jVar2.sendMessageDelayed(Message.obtain(jVar2, 9, aVar), 5000L);
        }
    }

    @Override // com.google.android.gms.common.api.internal.g
    public final void onConnected() {
        h hVar = this.f12727o;
        if (Looper.myLooper() == hVar.x.getLooper()) {
            b();
        } else {
            hVar.x.post(new androidx.leanback.widget.u(this, 2));
        }
    }

    public final void p(d1 d1Var) {
        com.google.android.gms.common.internal.t.d(this.f12727o.x);
        boolean zIsConnected = this.f12717d.isConnected();
        LinkedList linkedList = this.f12716c;
        if (zIsConnected) {
            if (g(d1Var)) {
                j();
                return;
            } else {
                linkedList.add(d1Var);
                return;
            }
        }
        linkedList.add(d1Var);
        o3.b bVar = this.f12725m;
        if (bVar == null || bVar.f20729l == 0 || bVar.f20730m == null) {
            r();
        } else {
            o(bVar, null);
        }
    }

    public final void q() {
        com.google.android.gms.common.internal.t.d(this.f12727o.x);
        Status status = h.z;
        i(status);
        this.f12719f.N(false, status);
        for (l lVar : (l[]) this.f12721h.keySet().toArray(new l[0])) {
            p(new b1(lVar, new com.google.android.gms.tasks.i()));
        }
        l(new o3.b(4, null, null));
        com.google.android.gms.common.api.g gVar = this.f12717d;
        if (gVar.isConnected()) {
            gVar.onUserSignOut(new a8.e(this, 8));
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void r() {
        h hVar = this.f12727o;
        com.google.android.gms.common.internal.t.d(hVar.x);
        com.google.android.gms.common.api.g gVar = this.f12717d;
        if (gVar.isConnected() || gVar.isConnecting()) {
            return;
        }
        try {
            int iK = hVar.f12746q.K(hVar.f12744o, gVar);
            if (iK != 0) {
                o3.b bVar = new o3.b(iK, null, null);
                String name = gVar.getClass().getName();
                String string = bVar.toString();
                StringBuilder sb2 = new StringBuilder(name.length() + 35 + string.length());
                sb2.append("The service for ");
                sb2.append(name);
                sb2.append(" is not available: ");
                sb2.append(string);
                Log.w("GoogleApiManager", sb2.toString());
                o(bVar, null);
                return;
            }
            g0 g0Var = new g0(hVar, gVar, this.f12718e);
            if (gVar.requiresSignIn()) {
                v0 v0Var = this.f12723j;
                com.google.android.gms.common.internal.t.i(v0Var);
                t4.c cVar = v0Var.f12804h;
                if (cVar != null) {
                    cVar.disconnect();
                }
                com.google.android.gms.common.internal.h hVar2 = v0Var.f12803g;
                hVar2.f12852h = Integer.valueOf(System.identityHashCode(v0Var));
                j4 j4Var = v0Var.f12801e;
                Context context = v0Var.f12799c;
                Handler handler = v0Var.f12800d;
                v0Var.f12804h = (t4.c) j4Var.buildClient(context, handler.getLooper(), hVar2, (Object) hVar2.f12851g, (com.google.android.gms.common.api.o) v0Var, (com.google.android.gms.common.api.p) v0Var);
                v0Var.f12805i = g0Var;
                Set set = v0Var.f12802f;
                if (set == null || set.isEmpty()) {
                    handler.post(new androidx.leanback.widget.u(v0Var));
                } else {
                    v0Var.f12804h.a();
                }
            }
            try {
                gVar.connect(g0Var);
            } catch (SecurityException e5) {
                o(new o3.b(10, null, null), e5);
            }
        } catch (IllegalStateException e6) {
            o(new o3.b(10, null, null), e6);
        }
    }
}
