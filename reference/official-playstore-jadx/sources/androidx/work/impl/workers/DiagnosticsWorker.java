package androidx.work.impl.workers;

import android.content.Context;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.h;
import androidx.work.impl.model.l;
import androidx.work.impl.model.t;
import androidx.work.impl.r;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import m2.q;
import o2.b;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/work/impl/workers/DiagnosticsWorker;", "Landroidx/work/Worker;", "Landroid/content/Context;", "context", "Landroidx/work/WorkerParameters;", "parameters", "<init>", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DiagnosticsWorker extends Worker {
    public DiagnosticsWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    @Override // androidx.work.Worker
    public final q a() {
        r rVarZ = r.Z(getApplicationContext());
        WorkDatabase workDatabase = rVarZ.f6966c;
        androidx.work.impl.model.q qVarT = workDatabase.t();
        l lVarR = workDatabase.r();
        t tVarU = workDatabase.u();
        h hVarP = workDatabase.p();
        rVarZ.f6965b.f20175c.getClass();
        ArrayList arrayListD = qVarT.d(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1L));
        ArrayList arrayListT = qVarT.t();
        ArrayList arrayListO = qVarT.o();
        if (!arrayListD.isEmpty()) {
            m2.t tVarD = m2.t.d();
            String str = b.f20724a;
            tVarD.e(str, "Recently completed work:\n\n");
            m2.t.d().e(str, b.a(lVarR, tVarU, hVarP, arrayListD));
        }
        if (!arrayListT.isEmpty()) {
            m2.t tVarD2 = m2.t.d();
            String str2 = b.f20724a;
            tVarD2.e(str2, "Running work:\n\n");
            m2.t.d().e(str2, b.a(lVarR, tVarU, hVarP, arrayListT));
        }
        if (!arrayListO.isEmpty()) {
            m2.t tVarD3 = m2.t.d();
            String str3 = b.f20724a;
            tVarD3.e(str3, "Enqueued work:\n\n");
            m2.t.d().e(str3, b.a(lVarR, tVarU, hVarP, arrayListO));
        }
        return new q(m2.h.f20202c);
    }
}
