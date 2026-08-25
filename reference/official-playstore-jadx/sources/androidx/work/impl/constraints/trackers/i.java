package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.net.ConnectivityManager;
import m2.t;

/* JADX INFO: loaded from: classes3.dex */
public final class i extends f {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ConnectivityManager f6835f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final h f6836g;

    public i(Context context, androidx.work.impl.utils.taskexecutor.d dVar) {
        super(context, dVar);
        this.f6835f = (ConnectivityManager) this.f6828b.getSystemService("connectivity");
        this.f6836g = new h(this, 0);
    }

    @Override // androidx.work.impl.constraints.trackers.f
    public final Object a() {
        return j.a(this.f6835f);
    }

    @Override // androidx.work.impl.constraints.trackers.f
    public final void c() {
        try {
            t.d().a(j.f6837a, "Registering network callback");
            androidx.work.impl.utils.i.a(this.f6835f, this.f6836g);
        } catch (IllegalArgumentException e5) {
            t.d().c(j.f6837a, "Received exception while registering network callback", e5);
        } catch (SecurityException e6) {
            t.d().c(j.f6837a, "Received exception while registering network callback", e6);
        }
    }

    @Override // androidx.work.impl.constraints.trackers.f
    public final void d() {
        try {
            t.d().a(j.f6837a, "Unregistering network callback");
            androidx.work.impl.utils.g.c(this.f6835f, this.f6836g);
        } catch (IllegalArgumentException e5) {
            t.d().c(j.f6837a, "Received exception while unregistering network callback", e5);
        } catch (SecurityException e6) {
            t.d().c(j.f6837a, "Received exception while unregistering network callback", e6);
        }
    }
}
