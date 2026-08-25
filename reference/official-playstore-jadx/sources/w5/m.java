package w5;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/* JADX INFO: loaded from: classes4.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z4.g f22470a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final y5.g f22471b;

    public m(z4.g gVar, y5.g gVar2, d7.j jVar) {
        this.f22470a = gVar;
        this.f22471b = gVar2;
        Log.d("FirebaseSessions", "Initializing Firebase Sessions SDK.");
        gVar.a();
        Context applicationContext = gVar.f23184a.getApplicationContext();
        if (applicationContext instanceof Application) {
            ((Application) applicationContext).registerActivityLifecycleCallbacks(o0.f22474i);
            ka.m0.p(ka.l0.a(jVar), null, 0, new androidx.work.impl.constraints.controllers.c(this, jVar, null, 5), 3);
        } else {
            Log.e("FirebaseSessions", "Failed to register lifecycle callbacks, unexpected context " + applicationContext.getClass() + '.');
        }
    }
}
