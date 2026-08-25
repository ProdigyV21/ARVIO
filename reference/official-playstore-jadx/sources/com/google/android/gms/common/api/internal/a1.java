package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class a1 extends d1 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f12700b;

    public a1(int i10, d dVar) {
        super(i10);
        com.google.android.gms.common.internal.t.j(dVar, "Null methods are not runnable.");
        this.f12700b = dVar;
    }

    @Override // com.google.android.gms.common.api.internal.d1
    public final void a(Status status) {
        try {
            this.f12700b.setFailedResult(status);
        } catch (IllegalStateException e5) {
            Log.w("ApiCallRunner", "Exception reporting failure", e5);
        }
    }

    @Override // com.google.android.gms.common.api.internal.d1
    public final void b(Exception exc) {
        String simpleName = exc.getClass().getSimpleName();
        String localizedMessage = exc.getLocalizedMessage();
        try {
            this.f12700b.setFailedResult(new Status(10, androidx.compose.material3.d.q(new StringBuilder(simpleName.length() + 2 + String.valueOf(localizedMessage).length()), simpleName, ": ", localizedMessage), null, null));
        } catch (IllegalStateException e5) {
            Log.w("ApiCallRunner", "Exception reporting failure", e5);
        }
    }

    @Override // com.google.android.gms.common.api.internal.d1
    public final void c(io.sentry.internal.debugmeta.c cVar, boolean z) {
        Boolean boolValueOf = Boolean.valueOf(z);
        Map map = (Map) cVar.f17367l;
        d dVar = this.f12700b;
        map.put(dVar, boolValueOf);
        dVar.addStatusListener(new i1(cVar, dVar));
    }

    @Override // com.google.android.gms.common.api.internal.d1
    public final void d(e0 e0Var) throws DeadObjectException {
        try {
            this.f12700b.run(e0Var.f12717d);
        } catch (RuntimeException e5) {
            b(e5);
        }
    }
}
