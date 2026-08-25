package androidx.mediarouter.media;

import android.content.ComponentName;
import android.content.Context;
import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public abstract class w {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f4493i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final t f4494l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final f1 f4495m = new f1(this);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public b f4496n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public n f4497o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f4498p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public x f4499q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f4500r;

    public w(Context context, t tVar) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        this.f4493i = context;
        if (tVar == null) {
            this.f4494l = new t(new ComponentName(context, getClass()));
        } else {
            this.f4494l = tVar;
        }
    }

    public s a(String str, v vVar) {
        if (str != null) {
            return null;
        }
        throw new IllegalArgumentException("initialMemberRouteId cannot be null.");
    }

    public u b(String str) {
        if (str != null) {
            return null;
        }
        throw new IllegalArgumentException("routeId cannot be null");
    }

    public u c(String str, v vVar) {
        return b(str);
    }

    public u d(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("routeId cannot be null");
        }
        if (str2 != null) {
            return c(str, v.f4488b);
        }
        throw new IllegalArgumentException("routeGroupId cannot be null");
    }

    public abstract void e(n nVar);

    public final void f(x xVar) {
        k0.b();
        if (this.f4499q != xVar) {
            this.f4499q = xVar;
            if (this.f4500r) {
                return;
            }
            this.f4500r = true;
            this.f4495m.sendEmptyMessage(1);
        }
    }

    public final void g(n nVar) {
        k0.b();
        if (Objects.equals(this.f4497o, nVar)) {
            return;
        }
        this.f4497o = nVar;
        if (this.f4498p) {
            return;
        }
        this.f4498p = true;
        this.f4495m.sendEmptyMessage(2);
    }
}
