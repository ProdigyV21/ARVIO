package androidx.mediarouter.media;

import android.os.Message;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u f4349a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4350b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f4351c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final i0 f4352d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final i0 f4353e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final i0 f4354f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayList f4355g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final WeakReference f4356h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public com.google.common.util.concurrent.d1 f4357i = null;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f4358j = false;
    public boolean k = false;

    public f0(f fVar, i0 i0Var, u uVar, int i10, boolean z, i0 i0Var2, Collection collection) {
        this.f4356h = new WeakReference(fVar);
        this.f4353e = i0Var;
        this.f4349a = uVar;
        this.f4350b = i10;
        this.f4351c = z;
        this.f4352d = fVar.f4329d;
        this.f4354f = i0Var2;
        this.f4355g = collection != null ? new ArrayList(collection) : null;
        fVar.f4326a.postDelayed(new a(this, 2), 15000L);
    }

    public final void a() {
        if (this.f4358j || this.k) {
            return;
        }
        this.k = true;
        u uVar = this.f4349a;
        if (uVar != null) {
            uVar.h(0);
            uVar.d();
        }
    }

    public final void b() {
        com.google.common.util.concurrent.d1 d1Var;
        k0.b();
        if (this.f4358j || this.k) {
            return;
        }
        WeakReference weakReference = this.f4356h;
        f fVar = (f) weakReference.get();
        if (fVar == null || fVar.f4332g != this || ((d1Var = this.f4357i) != null && d1Var.isCancelled())) {
            a();
            return;
        }
        this.f4358j = true;
        fVar.f4332g = null;
        f fVar2 = (f) weakReference.get();
        i0 i0Var = this.f4352d;
        int i10 = this.f4350b;
        if (fVar2 != null) {
            HashMap map = fVar2.f4327b;
            if (fVar2.f4329d == i0Var) {
                Message messageObtainMessage = fVar2.f4326a.obtainMessage(263, i0Var);
                messageObtainMessage.arg1 = i10;
                messageObtainMessage.sendToTarget();
                u uVar = fVar2.f4330e;
                if (uVar != null) {
                    uVar.h(i10);
                    fVar2.f4330e.d();
                }
                if (!map.isEmpty()) {
                    for (u uVar2 : map.values()) {
                        uVar2.h(i10);
                        uVar2.d();
                    }
                    map.clear();
                }
                fVar2.f4330e = null;
            }
        }
        f fVar3 = (f) weakReference.get();
        if (fVar3 == null) {
            return;
        }
        c cVar = fVar3.f4326a;
        i0 i0Var2 = this.f4353e;
        fVar3.f4329d = i0Var2;
        fVar3.f4330e = this.f4349a;
        boolean z = this.f4351c;
        i0 i0Var3 = this.f4354f;
        if (i0Var3 == null) {
            cVar.getClass();
            Message messageObtainMessage2 = cVar.obtainMessage(262, new e(i0Var, i0Var2, z));
            messageObtainMessage2.arg1 = i10;
            messageObtainMessage2.sendToTarget();
        } else {
            cVar.getClass();
            Message messageObtainMessage3 = cVar.obtainMessage(264, new e(i0Var3, i0Var2, z));
            messageObtainMessage3.arg1 = i10;
            messageObtainMessage3.sendToTarget();
        }
        fVar3.f4327b.clear();
        fVar3.i();
        fVar3.o();
        ArrayList arrayList = this.f4355g;
        if (arrayList != null) {
            i0 i0Var4 = fVar3.f4329d;
            i0Var4.getClass();
            c0 c0Var = i0Var4 instanceof c0 ? (c0) i0Var4 : null;
            if (c0Var != null) {
                c0Var.i(arrayList);
            }
        }
    }
}
