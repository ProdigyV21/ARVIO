package androidx.work.impl.workers;

import android.content.Context;
import android.os.Build;
import androidx.work.WorkerParameters;
import androidx.work.impl.constraints.c;
import androidx.work.impl.constraints.d;
import androidx.work.impl.constraints.f;
import androidx.work.impl.model.p;
import androidx.work.impl.utils.futures.k;
import com.google.common.util.concurrent.d1;
import kotlin.Metadata;
import m2.g;
import m2.s;
import m2.t;
import o2.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/work/impl/workers/ConstraintTrackingWorker;", "Lm2/s;", "Landroidx/work/impl/constraints/f;", "Landroid/content/Context;", "appContext", "Landroidx/work/WorkerParameters;", "workerParameters", "<init>", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ConstraintTrackingWorker extends s implements f {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final WorkerParameters f7101i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f7102l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public volatile boolean f7103m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final k f7104n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public s f7105o;

    public ConstraintTrackingWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        this.f7101i = workerParameters;
        this.f7102l = new Object();
        this.f7104n = new k();
    }

    @Override // androidx.work.impl.constraints.f
    public final void e(p pVar, d dVar) {
        t.d().a(a.f20723a, "Constraints changed for " + pVar);
        if (dVar instanceof c) {
            synchronized (this.f7102l) {
                this.f7103m = true;
            }
        }
    }

    @Override // m2.s
    public final void onStopped() {
        s sVar = this.f7105o;
        if (sVar == null || sVar.isStopped()) {
            return;
        }
        sVar.stop(Build.VERSION.SDK_INT >= 31 ? getStopReason() : 0);
    }

    @Override // m2.s
    public final d1 startWork() {
        getBackgroundExecutor().execute(new g(this, 1));
        return this.f7104n;
    }
}
