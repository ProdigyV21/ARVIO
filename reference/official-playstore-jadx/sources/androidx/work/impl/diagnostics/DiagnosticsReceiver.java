package androidx.work.impl.diagnostics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.work.impl.m;
import androidx.work.impl.r;
import androidx.work.impl.workers.DiagnosticsWorker;
import java.util.Collections;
import java.util.List;
import m2.t;
import m2.v;
import m2.w;

/* JADX INFO: loaded from: classes3.dex */
public class DiagnosticsReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f6844a = t.f("DiagnosticsRcvr");

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        t tVarD = t.d();
        String str = f6844a;
        tVarD.a(str, "Requesting diagnostics");
        try {
            r rVarZ = r.Z(context);
            w wVar = (w) new v(DiagnosticsWorker.class, 0).a();
            rVarZ.getClass();
            List listSingletonList = Collections.singletonList(wVar);
            if (listSingletonList.isEmpty()) {
                throw new IllegalArgumentException("enqueue needs at least one WorkRequest.");
            }
            new m(rVarZ, null, 2, listSingletonList, 0).J();
        } catch (IllegalStateException e5) {
            t.d().c(str, "WorkManager is not initialized", e5);
        }
    }
}
