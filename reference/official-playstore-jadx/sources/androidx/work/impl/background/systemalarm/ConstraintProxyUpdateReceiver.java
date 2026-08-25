package androidx.work.impl.background.systemalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.provider.o;
import androidx.work.impl.r;
import m2.t;

/* JADX INFO: loaded from: classes3.dex */
public class ConstraintProxyUpdateReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f6728a = t.f("ConstrntProxyUpdtRecvr");

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        String action = intent != null ? intent.getAction() : null;
        if ("androidx.work.impl.background.systemalarm.UpdateProxies".equals(action)) {
            r.Z(context).f6967d.d(new o(intent, context, goAsync(), 1));
            return;
        }
        t.d().a(f6728a, "Ignoring unknown action " + action);
    }
}
