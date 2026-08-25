package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.appcompat.app.n0;
import m2.t;

/* JADX INFO: loaded from: classes3.dex */
public abstract class d extends f {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final n0 f6825f;

    public d(Context context, androidx.work.impl.utils.taskexecutor.d dVar) {
        super(context, dVar);
        this.f6825f = new n0(this, 3);
    }

    @Override // androidx.work.impl.constraints.trackers.f
    public final void c() {
        t.d().a(e.f6826a, getClass().getSimpleName().concat(": registering receiver"));
        this.f6828b.registerReceiver(this.f6825f, e());
    }

    @Override // androidx.work.impl.constraints.trackers.f
    public final void d() {
        t.d().a(e.f6826a, getClass().getSimpleName().concat(": unregistering receiver"));
        this.f6828b.unregisterReceiver(this.f6825f);
    }

    public abstract IntentFilter e();

    public abstract void f(Intent intent);
}
