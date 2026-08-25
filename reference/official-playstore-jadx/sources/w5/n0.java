package w5;

import android.content.Context;
import android.content.Intent;
import android.os.Messenger;
import android.os.Process;
import android.util.Log;
import com.google.firebase.sessions.SessionLifecycleService;

/* JADX INFO: loaded from: classes4.dex */
public final class n0 implements m0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z4.g f22473a;

    public n0(z4.g gVar) {
        this.f22473a = gVar;
    }

    @Override // w5.m0
    public final void a(Messenger messenger, k0 k0Var) {
        z4.g gVar = this.f22473a;
        gVar.a();
        Context applicationContext = gVar.f23184a.getApplicationContext();
        Intent intent = new Intent(applicationContext, (Class<?>) SessionLifecycleService.class);
        Log.d("LifecycleServiceBinder", "Binding service to application.");
        intent.setAction(String.valueOf(Process.myPid()));
        intent.putExtra("ClientCallbackMessenger", messenger);
        applicationContext.bindService(intent, k0Var, 65);
    }
}
