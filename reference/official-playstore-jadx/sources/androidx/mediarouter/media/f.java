package androidx.mediarouter.media;

import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import androidx.fragment.app.a2;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public final class f implements z0, l1 {
    public static final /* synthetic */ int F = 0;
    public n A;
    public n B;
    public int C;
    public androidx.appcompat.app.i1 D;
    public final b E;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final m1 f4328c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public i0 f4329d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public u f4330e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public d0 f4331f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public f0 f4332g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Context f4333h;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final n1 f4339o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final b f4340p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f4341q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f4342r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public l f4343s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final y0 f4344t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final l0 f4345u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public o0 f4346v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public i0 f4347w;
    public i0 x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public i0 f4348y;
    public s z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f4326a = new c(this);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap f4327b = new HashMap();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList f4334i = new ArrayList();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final ArrayList f4335j = new ArrayList();
    public final HashMap k = new HashMap();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final HashMap f4336l = new HashMap();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f4337m = new ArrayList();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ArrayList f4338n = new ArrayList();

    static {
        Log.isLoggable("AxMediaRouter", 3);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x008d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public f(android.content.Context r8) {
        /*
            Method dump skipped, instruction units count: 292
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.media.f.<init>(android.content.Context):void");
    }

    public final void a(w wVar, boolean z) {
        if (d(wVar) == null) {
            g0 g0Var = new g0(wVar, z);
            this.f4337m.add(g0Var);
            this.f4326a.b(513, g0Var);
            p(g0Var, wVar.f4499q);
            k0.b();
            wVar.f4496n = this.f4340p;
            wVar.g(this.A);
        }
    }

    public final String b(g0 g0Var, String str) {
        String strFlattenToShortString = ((ComponentName) g0Var.f4365d.f4483b).flattenToShortString();
        boolean z = g0Var.f4364c;
        String strT = z ? str : androidx.compose.foundation.c.t(strFlattenToShortString, ":", str);
        HashMap map = this.f4336l;
        if (!z) {
            ArrayList arrayList = this.f4335j;
            int size = arrayList.size();
            int i10 = 0;
            while (true) {
                if (i10 >= size) {
                    i10 = -1;
                    break;
                }
                if (((i0) arrayList.get(i10)).f4388c.equals(strT)) {
                    break;
                }
                i10++;
            }
            if (i10 >= 0) {
                Log.w("AxMediaRouter", a2.m("Either ", str, " isn't unique in ", strFlattenToShortString, " or we're trying to assign a unique ID for an already added route"));
                int i11 = 2;
                while (true) {
                    Locale locale = Locale.US;
                    String strJ = a2.j(i11, strT, "_");
                    int size2 = arrayList.size();
                    int i12 = 0;
                    while (true) {
                        if (i12 >= size2) {
                            i12 = -1;
                            break;
                        }
                        if (((i0) arrayList.get(i12)).f4388c.equals(strJ)) {
                            break;
                        }
                        i12++;
                    }
                    if (i12 < 0) {
                        map.put(new p0.c(strFlattenToShortString, str), strJ);
                        return strJ;
                    }
                    i11++;
                }
            }
        }
        map.put(new p0.c(strFlattenToShortString, str), strT);
        return strT;
    }

    public final i0 c() {
        for (i0 i0Var : this.f4335j) {
            if (i0Var != this.f4347w && i0Var.a() == this.f4344t && i0Var.h("android.media.intent.category.LIVE_AUDIO") && !i0Var.h("android.media.intent.category.LIVE_VIDEO") && i0Var.d()) {
                return i0Var;
            }
        }
        return this.f4347w;
    }

    public final g0 d(w wVar) {
        for (g0 g0Var : this.f4337m) {
            if (g0Var.f4362a == wVar) {
                return g0Var;
            }
        }
        return null;
    }

    public final ArrayList e() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.k.values().iterator();
        if (!it.hasNext()) {
            return arrayList;
        }
        it.next().getClass();
        throw new ClassCastException();
    }

    public final u f(i0 i0Var) {
        u uVar;
        if (i0Var == this.f4329d && (uVar = this.f4330e) != null) {
            return uVar;
        }
        boolean z = i0Var instanceof c0;
        HashMap map = this.k;
        if (z) {
            k0.b();
            if (k0.c().e().contains((c0) i0Var)) {
                Iterator it = map.values().iterator();
                if (!it.hasNext()) {
                    return null;
                }
                it.next().getClass();
                throw new ClassCastException();
            }
        }
        u uVar2 = (u) this.f4327b.get(i0Var.f4388c);
        if (uVar2 != null) {
            return uVar2;
        }
        Iterator it2 = map.values().iterator();
        if (!it2.hasNext()) {
            return uVar2;
        }
        it2.next().getClass();
        throw new ClassCastException();
    }

    public final i0 g() {
        i0 i0Var = this.f4329d;
        if (i0Var != null) {
            return i0Var;
        }
        throw new IllegalStateException("There is no currently selected route.  The media router has not yet been fully initialized.");
    }

    public final boolean h() {
        if (!this.f4342r) {
            return false;
        }
        o0 o0Var = this.f4346v;
        return o0Var == null || o0Var.f4466a;
    }

    public final void i() {
        u uVarD;
        if (this.f4329d.f4406v.isEmpty()) {
            return;
        }
        List<i0> listUnmodifiableList = Collections.unmodifiableList(this.f4329d.f4406v);
        HashSet hashSet = new HashSet();
        Iterator it = listUnmodifiableList.iterator();
        while (it.hasNext()) {
            hashSet.add(((i0) it.next()).f4388c);
        }
        HashMap map = this.f4327b;
        Iterator it2 = map.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it2.next();
            if (!hashSet.contains(entry.getKey())) {
                u uVar = (u) entry.getValue();
                uVar.h(0);
                uVar.d();
                it2.remove();
            }
        }
        for (i0 i0Var : listUnmodifiableList) {
            if (!map.containsKey(i0Var.f4388c) && (uVarD = i0Var.a().d(i0Var.f4387b, this.f4329d.f4387b)) != null) {
                uVarD.e();
                map.put(i0Var.f4388c, uVarD);
            }
        }
    }

    public final void j(f fVar, i0 i0Var, u uVar, int i10, boolean z, i0 i0Var2, Collection collection) {
        d0 d0Var;
        f0 f0Var = this.f4332g;
        if (f0Var != null) {
            f0Var.a();
            this.f4332g = null;
        }
        f0 f0Var2 = new f0(fVar, i0Var, uVar, i10, z, i0Var2, collection);
        this.f4332g = f0Var2;
        if (f0Var2.f4350b != 3 || (d0Var = this.f4331f) == null) {
            f0Var2.b();
            return;
        }
        e0.k kVarA = d0Var.a(this.f4329d, f0Var2.f4353e);
        f0 f0Var3 = this.f4332g;
        f fVar2 = (f) f0Var3.f4356h.get();
        if (fVar2 == null || fVar2.f4332g != f0Var3) {
            Log.w("AxMediaRouter", "Router is released. Cancel transfer");
            f0Var3.a();
        } else {
            if (f0Var3.f4357i != null) {
                throw new IllegalStateException("future is already set");
            }
            f0Var3.f4357i = kVarA;
            a aVar = new a(f0Var3, 2);
            final c cVar = fVar2.f4326a;
            Objects.requireNonNull(cVar);
            kVarA.f14999l.addListener(aVar, new Executor() { // from class: androidx.mediarouter.media.e0
                @Override // java.util.concurrent.Executor
                public final void execute(Runnable runnable) {
                    cVar.post(runnable);
                }
            });
        }
    }

    public final void k(w wVar) {
        g0 g0VarD = d(wVar);
        if (g0VarD != null) {
            wVar.getClass();
            k0.b();
            wVar.f4496n = null;
            wVar.g(null);
            p(g0VarD, null);
            this.f4326a.b(514, g0VarD);
            this.f4337m.remove(g0VarD);
        }
    }

    public final void l(i0 i0Var, int i10, boolean z) {
        if (!this.f4335j.contains(i0Var)) {
            Log.w("AxMediaRouter", "Ignoring attempt to select removed route: " + i0Var);
        } else {
            if (!i0Var.f4392g) {
                Log.w("AxMediaRouter", "Ignoring attempt to select disabled route: " + i0Var);
                return;
            }
            if (Build.VERSION.SDK_INT >= 30) {
                w wVarA = i0Var.a();
                l lVar = this.f4343s;
                if (wVarA == lVar && this.f4329d != i0Var) {
                    lVar.l(i0Var.f4387b);
                    return;
                }
            }
            m(i0Var, i10, z);
        }
    }

    public final void m(i0 i0Var, int i10, boolean z) {
        x xVar;
        String str;
        if (this.f4329d == i0Var) {
            return;
        }
        boolean z5 = i0Var == this.f4347w;
        if (this.x != null && z5) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            StringBuilder sb2 = new StringBuilder("- Stracktrace: [");
            int i11 = 3;
            while (i11 < stackTrace.length) {
                StackTraceElement stackTraceElement = stackTrace[i11];
                sb2.append(stackTraceElement.getClassName());
                sb2.append(".");
                sb2.append(stackTraceElement.getMethodName());
                sb2.append(":");
                sb2.append(stackTraceElement.getLineNumber());
                i11++;
                if (i11 < stackTrace.length) {
                    sb2.append(", ");
                }
            }
            sb2.append("]");
            i0 i0Var2 = this.f4329d;
            if (i0Var2 != null) {
                Locale locale = Locale.US;
                String str2 = i0Var2.f4389d;
                k0.b();
                str = str2 + "(BT=" + (k0.c().x == i0Var2) + ", syncMediaRoute1Provider=" + z + ")";
            } else {
                str = null;
            }
            StringBuilder sbT = a0.c.t("Changing selection(", str, ") to default while BT is available: pkgName=");
            sbT.append(this.f4333h.getPackageName());
            sbT.append((Object) sb2);
            Log.w("AxMediaRouter", sbT.toString());
        }
        if (this.f4348y != null) {
            this.f4348y = null;
            s sVar = this.z;
            if (sVar != null) {
                sVar.h(3);
                this.z.d();
                this.z = null;
            }
        }
        if (h() && (xVar = i0Var.f4386a.f4366e) != null && xVar.f4507c) {
            w wVarA = i0Var.a();
            String str3 = i0Var.f4387b;
            Bundle bundle = new Bundle();
            bundle.putString("clientPackageName", this.f4333h.getPackageName());
            s sVarA = wVarA.a(str3, new v(bundle));
            if (sVarA != null) {
                Context context = this.f4333h;
                Executor executorA = Build.VERSION.SDK_INT >= 28 ? androidx.core.content.e.a(context) : new androidx.core.os.i(new Handler(context.getMainLooper()));
                b bVar = this.E;
                synchronized (sVarA.f4476a) {
                    try {
                        if (executorA == null) {
                            throw new NullPointerException("Executor shouldn't be null");
                        }
                        if (bVar == null) {
                            throw new NullPointerException("Listener shouldn't be null");
                        }
                        sVarA.f4477b = executorA;
                        sVarA.f4478c = bVar;
                        ArrayList arrayList = sVarA.f4480e;
                        if (arrayList != null && !arrayList.isEmpty()) {
                            m mVar = sVarA.f4479d;
                            ArrayList arrayList2 = sVarA.f4480e;
                            sVarA.f4479d = null;
                            sVarA.f4480e = null;
                            sVarA.f4477b.execute(new o(sVarA, bVar, mVar, arrayList2, 1));
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                this.f4348y = i0Var;
                this.z = sVarA;
                sVarA.e();
                return;
            }
            Log.w("AxMediaRouter", "setSelectedRouteInternal: Failed to create dynamic group route controller. route=" + i0Var);
        }
        w wVarA2 = i0Var.a();
        String str4 = i0Var.f4387b;
        Bundle bundle2 = new Bundle();
        bundle2.putString("clientPackageName", this.f4333h.getPackageName());
        u uVarC = wVarA2.c(str4, new v(bundle2));
        if (uVarC != null) {
            uVarC.e();
        }
        if (this.f4329d != null) {
            j(this, i0Var, uVarC, i10, z, null, null);
            return;
        }
        this.f4329d = i0Var;
        this.f4330e = uVarC;
        c cVar = this.f4326a;
        cVar.getClass();
        Message messageObtainMessage = cVar.obtainMessage(262, new e(null, i0Var, z));
        messageObtainMessage.arg1 = i10;
        messageObtainMessage.sendToTarget();
    }

    /* JADX WARN: Removed duplicated region for block: B:72:0x0154  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void n() {
        /*
            Method dump skipped, instruction units count: 472
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.media.f.n():void");
    }

    public final void o() {
        i0 i0Var = this.f4329d;
        if (i0Var == null) {
            androidx.appcompat.app.i1 i1Var = this.D;
            if (i1Var != null) {
                i1Var.f();
                return;
            }
            return;
        }
        int i10 = i0Var.f4400p;
        n1 n1Var = this.f4339o;
        n1Var.f4456a = i10;
        n1Var.f4457b = i0Var.f4401q;
        n1Var.f4458c = i0Var.b();
        n1Var.f4459d = this.f4329d.f4397m;
        if (h() && this.f4329d.a() == this.f4343s) {
            n1Var.f4460e = l.i(this.f4330e);
        } else {
            n1Var.f4460e = null;
        }
        Iterator it = this.f4338n.iterator();
        if (it.hasNext()) {
            it.next().getClass();
            throw new ClassCastException();
        }
        androidx.appcompat.app.i1 i1Var2 = this.D;
        if (i1Var2 != null) {
            i0 i0Var2 = this.f4329d;
            i0 i0Var3 = this.f4347w;
            if (i0Var3 == null) {
                throw new IllegalStateException("There is no default route.  The media router has not yet been fully initialized.");
            }
            if (i0Var2 == i0Var3 || i0Var2 == this.x) {
                i1Var2.f();
                return;
            }
            int i11 = n1Var.f4458c == 1 ? 2 : 0;
            int i12 = n1Var.f4457b;
            int i13 = n1Var.f4456a;
            String str = n1Var.f4460e;
            android.support.v4.media.session.v vVar = (android.support.v4.media.session.v) i1Var2.f1059l;
            if (vVar != null) {
                androidx.emoji2.text.y yVar = (androidx.emoji2.text.y) i1Var2.f1060m;
                if (yVar != null && i11 == 0 && i12 == 0) {
                    yVar.f2653c = i13;
                    androidx.media.k.a(yVar.a(), i13);
                } else {
                    androidx.emoji2.text.y yVar2 = new androidx.emoji2.text.y(i1Var2, i11, i12, i13, str);
                    i1Var2.f1060m = yVar2;
                    vVar.f897a.f886a.setPlaybackToRemote(yVar2.a());
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0135 A[LOOP:3: B:54:0x012f->B:56:0x0135, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0158  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void p(androidx.mediarouter.media.g0 r20, androidx.mediarouter.media.x r21) {
        /*
            Method dump skipped, instruction units count: 470
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.media.f.p(androidx.mediarouter.media.g0, androidx.mediarouter.media.x):void");
    }

    public final int q(i0 i0Var, m mVar) {
        int iF = i0Var.f(mVar);
        if (iF != 0) {
            int i10 = iF & 1;
            c cVar = this.f4326a;
            if (i10 != 0) {
                cVar.b(259, i0Var);
            }
            if ((iF & 2) != 0) {
                cVar.b(260, i0Var);
            }
            if ((iF & 4) != 0) {
                cVar.b(261, i0Var);
            }
        }
        return iF;
    }

    public final void r(boolean z) {
        i0 i0Var = this.f4347w;
        if (i0Var != null && !i0Var.d()) {
            Log.i("AxMediaRouter", "Clearing the default route because it is no longer selectable: " + this.f4347w);
            this.f4347w = null;
        }
        i0 i0Var2 = this.f4347w;
        y0 y0Var = this.f4344t;
        ArrayList arrayList = this.f4335j;
        if (i0Var2 == null) {
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                i0 i0Var3 = (i0) it.next();
                if (i0Var3.a() == y0Var && i0Var3.f4387b.equals("DEFAULT_ROUTE") && i0Var3.d()) {
                    this.f4347w = i0Var3;
                    Log.i("AxMediaRouter", "Found default route: " + this.f4347w);
                    break;
                }
            }
        }
        i0 i0Var4 = this.x;
        if (i0Var4 != null && !i0Var4.d()) {
            Log.i("AxMediaRouter", "Clearing the bluetooth route because it is no longer selectable: " + this.x);
            this.x = null;
        }
        if (this.x == null) {
            Iterator it2 = arrayList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                i0 i0Var5 = (i0) it2.next();
                if (i0Var5.a() == y0Var && i0Var5.h("android.media.intent.category.LIVE_AUDIO") && !i0Var5.h("android.media.intent.category.LIVE_VIDEO") && i0Var5.d()) {
                    this.x = i0Var5;
                    Log.i("AxMediaRouter", "Found bluetooth route: " + this.x);
                    break;
                }
            }
        }
        i0 i0Var6 = this.f4329d;
        if (i0Var6 == null || !i0Var6.f4392g) {
            Log.i("AxMediaRouter", "Unselecting the current route because it is no longer selectable: " + this.f4329d);
            m(c(), 0, true);
            return;
        }
        if (z) {
            i();
            o();
        }
    }
}
