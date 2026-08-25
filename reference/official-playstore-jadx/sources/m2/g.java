package m2;

import android.util.Log;
import androidx.appcompat.app.a1;
import androidx.work.CoroutineWorker;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import com.google.common.util.concurrent.d1;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CancellationException;
import ka.x1;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class g implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f20196i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f20197l;

    public /* synthetic */ g(Object obj, int i10) {
        this.f20196i = i10;
        this.f20197l = obj;
    }

    @Override // java.lang.Runnable
    public final void run() throws IllegalAccessException, InvocationTargetException {
        switch (this.f20196i) {
            case 0:
                CoroutineWorker.a((CoroutineWorker) this.f20197l);
                return;
            case 1:
                ConstraintTrackingWorker constraintTrackingWorker = (ConstraintTrackingWorker) this.f20197l;
                if (constraintTrackingWorker.f7104n.f7037i instanceof androidx.work.impl.utils.futures.a) {
                    return;
                }
                Object obj = constraintTrackingWorker.getInputData().f20203a.get("androidx.work.impl.workers.ConstraintTrackingWorker.ARGUMENT_CLASS_NAME");
                String str = obj instanceof String ? (String) obj : null;
                t tVarD = t.d();
                if (str == null || str.length() == 0) {
                    tVarD.b(o2.a.f20723a, "No worker to delegate to.");
                    constraintTrackingWorker.f7104n.h(new o());
                    return;
                }
                s sVarB = constraintTrackingWorker.getWorkerFactory().b(constraintTrackingWorker.getApplicationContext(), str, constraintTrackingWorker.f7101i);
                constraintTrackingWorker.f7105o = sVarB;
                if (sVarB == null) {
                    tVarD.a(o2.a.f20723a, "No worker to delegate to.");
                    constraintTrackingWorker.f7104n.h(new o());
                    return;
                }
                androidx.work.impl.r rVarZ = androidx.work.impl.r.Z(constraintTrackingWorker.getApplicationContext());
                androidx.work.impl.model.p pVarK = rVarZ.f6966c.t().k(constraintTrackingWorker.getId().toString());
                if (pVarK == null) {
                    androidx.work.impl.utils.futures.k kVar = constraintTrackingWorker.f7104n;
                    String str2 = o2.a.f20723a;
                    kVar.h(new o());
                    return;
                }
                androidx.work.impl.constraints.j jVar = new androidx.work.impl.constraints.j(rVarZ.f6973j);
                constraintTrackingWorker.f7104n.addListener(new g(androidx.work.impl.constraints.m.a(jVar, pVarK, rVarZ.f6967d.f7077b, constraintTrackingWorker), 2), new a1(1));
                if (!jVar.a(pVarK)) {
                    tVarD.a(o2.a.f20723a, "Constraints not met for delegate " + str + ". Requesting retry.");
                    constraintTrackingWorker.f7104n.h(new p());
                    return;
                }
                tVarD.a(o2.a.f20723a, "Constraints met for delegate ".concat(str));
                try {
                    d1 d1VarStartWork = constraintTrackingWorker.f7105o.startWork();
                    d1VarStartWork.addListener(new io.sentry.android.ndk.b(constraintTrackingWorker, d1VarStartWork, 15), constraintTrackingWorker.getBackgroundExecutor());
                    return;
                } catch (Throwable th) {
                    String str3 = o2.a.f20723a;
                    String strL = a0.c.l("Delegated worker ", str, " threw exception in startWork.");
                    if (tVarD.f20216a <= 3) {
                        Log.d(str3, strL, th);
                    }
                    synchronized (constraintTrackingWorker.f7102l) {
                        try {
                            if (!constraintTrackingWorker.f7103m) {
                                constraintTrackingWorker.f7104n.h(new o());
                                return;
                            } else {
                                tVarD.a(str3, "Constraints were unmet, Retrying.");
                                constraintTrackingWorker.f7104n.h(new p());
                                return;
                            }
                        } catch (Throwable th2) {
                            throw th2;
                        }
                    }
                }
            default:
                ((x1) this.f20197l).cancel((CancellationException) null);
                return;
        }
    }
}
