package androidx.mediarouter.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class j1 extends w implements ServiceConnection {
    public static final /* synthetic */ int A = 0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final ComponentName f4418s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final androidx.loader.content.j f4419t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final ArrayList f4420u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f4421v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f4422w;
    public c1 x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f4423y;
    public k1 z;

    static {
        Log.isLoggable("MediaRouteProviderProxy", 3);
    }

    public j1(Context context, ComponentName componentName) {
        super(context, new t(componentName));
        this.f4420u = new ArrayList();
        this.f4418s = componentName;
        this.f4419t = new androidx.loader.content.j();
    }

    @Override // androidx.mediarouter.media.w
    public final s a(String str, v vVar) {
        if (str == null) {
            throw new IllegalArgumentException("initialMemberRouteId cannot be null.");
        }
        x xVar = this.f4499q;
        if (xVar == null) {
            return null;
        }
        List list = xVar.f4506b;
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            if (((m) list.get(i10)).d().equals(str)) {
                h1 h1Var = new h1(this, str, vVar);
                this.f4420u.add(h1Var);
                if (this.f4423y) {
                    h1Var.c(this.x);
                }
                l();
                return h1Var;
            }
        }
        return null;
    }

    @Override // androidx.mediarouter.media.w
    public final u c(String str, v vVar) {
        if (str != null) {
            return i(str, null, vVar);
        }
        throw new IllegalArgumentException("routeId cannot be null");
    }

    @Override // androidx.mediarouter.media.w
    public final u d(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("routeId cannot be null");
        }
        if (str2 != null) {
            return i(str, str2, v.f4488b);
        }
        throw new IllegalArgumentException("routeGroupId cannot be null");
    }

    @Override // androidx.mediarouter.media.w
    public final void e(n nVar) {
        if (this.f4423y) {
            c1 c1Var = this.x;
            int i10 = c1Var.f4313d;
            c1Var.f4313d = i10 + 1;
            c1Var.b(10, i10, 0, nVar != null ? nVar.f4454a : null, null);
        }
        l();
    }

    public final void h() {
        if (this.f4422w) {
            return;
        }
        Intent intent = new Intent("android.media.MediaRouteProviderService");
        intent.setComponent(this.f4418s);
        try {
            this.f4422w = this.f4493i.bindService(intent, this, Build.VERSION.SDK_INT >= 29 ? 4097 : 1);
        } catch (SecurityException unused) {
        }
    }

    public final i1 i(String str, String str2, v vVar) {
        x xVar = this.f4499q;
        if (xVar == null) {
            return null;
        }
        List list = xVar.f4506b;
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            if (((m) list.get(i10)).d().equals(str)) {
                i1 i1Var = new i1(this, str, str2, vVar);
                this.f4420u.add(i1Var);
                if (this.f4423y) {
                    i1Var.c(this.x);
                }
                l();
                return i1Var;
            }
        }
        return null;
    }

    public final void j() {
        if (this.x != null) {
            f(null);
            this.f4423y = false;
            ArrayList arrayList = this.f4420u;
            int size = arrayList.size();
            for (int i10 = 0; i10 < size; i10++) {
                ((e1) arrayList.get(i10)).b();
            }
            c1 c1Var = this.x;
            c1Var.b(2, 0, 0, null, null);
            ((WeakReference) c1Var.f4311b.f4360b).clear();
            c1Var.f4310a.getBinder().unlinkToDeath(c1Var, 0);
            c1Var.f4318i.f4419t.post(new b1(c1Var, 0));
            this.x = null;
        }
    }

    public final void k() {
        if (this.f4422w) {
            this.f4422w = false;
            j();
            try {
                this.f4493i.unbindService(this);
            } catch (IllegalArgumentException e5) {
                Log.e("MediaRouteProviderProxy", this + ": unbindService failed", e5);
            }
        }
    }

    public final void l() {
        if (!this.f4421v || (this.f4497o == null && this.f4420u.isEmpty())) {
            k();
        } else {
            h();
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (this.f4422w) {
            j();
            Messenger messenger = iBinder != null ? new Messenger(iBinder) : null;
            if (messenger != null) {
                try {
                    if (messenger.getBinder() != null) {
                        c1 c1Var = new c1(this, messenger);
                        int i10 = c1Var.f4313d;
                        c1Var.f4313d = i10 + 1;
                        c1Var.f4316g = i10;
                        if (c1Var.b(1, i10, 4, null, null)) {
                            try {
                                c1Var.f4310a.getBinder().linkToDeath(c1Var, 0);
                                this.x = c1Var;
                                return;
                            } catch (RemoteException unused) {
                                c1Var.binderDied();
                                return;
                            }
                        }
                        return;
                    }
                } catch (NullPointerException unused2) {
                }
            }
            Log.e("MediaRouteProviderProxy", this + ": Service returned invalid messenger binder");
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        j();
    }

    public final String toString() {
        return "Service connection " + this.f4418s.flattenToShortString();
    }
}
