package androidx.lifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/* JADX INFO: loaded from: classes3.dex */
public abstract class c0 extends Service implements y {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final androidx.appcompat.app.i1 f3219i = new androidx.appcompat.app.i1(this);

    @Override // androidx.lifecycle.y
    public final r getLifecycle() {
        return (a0) this.f3219i.f1059l;
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        this.f3219i.a0(p.ON_START);
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        this.f3219i.a0(p.ON_CREATE);
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        p pVar = p.ON_STOP;
        androidx.appcompat.app.i1 i1Var = this.f3219i;
        i1Var.a0(pVar);
        i1Var.a0(p.ON_DESTROY);
        super.onDestroy();
    }

    @Override // android.app.Service
    public final void onStart(Intent intent, int i10) {
        this.f3219i.a0(p.ON_START);
        super.onStart(intent, i10);
    }
}
