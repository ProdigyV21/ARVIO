package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import kotlin.jvm.internal.p;
import m2.t;

/* JADX INFO: loaded from: classes3.dex */
public final class k extends d {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ConnectivityManager f6838g;

    public k(Context context, androidx.work.impl.utils.taskexecutor.d dVar) {
        super(context, dVar);
        this.f6838g = (ConnectivityManager) this.f6828b.getSystemService("connectivity");
    }

    @Override // androidx.work.impl.constraints.trackers.f
    public final Object a() {
        return j.a(this.f6838g);
    }

    @Override // androidx.work.impl.constraints.trackers.d
    public final IntentFilter e() {
        return new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    }

    @Override // androidx.work.impl.constraints.trackers.d
    public final void f(Intent intent) {
        if (p.a(intent.getAction(), "android.net.conn.CONNECTIVITY_CHANGE")) {
            t.d().a(j.f6837a, "Network broadcast received");
            b(j.a(this.f6838g));
        }
    }
}
