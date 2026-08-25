package m2;

import android.content.Context;
import android.net.Network;
import android.net.Uri;
import androidx.work.WorkerParameters;
import com.google.common.util.concurrent.d1;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public abstract class s {
    private Context mAppContext;
    private volatile int mStopReason = -256;
    private boolean mUsed;
    private WorkerParameters mWorkerParams;

    public s(Context context, WorkerParameters workerParameters) {
        if (context == null) {
            throw new IllegalArgumentException("Application Context is null");
        }
        if (workerParameters == null) {
            throw new IllegalArgumentException("WorkerParameters is null");
        }
        this.mAppContext = context;
        this.mWorkerParams = workerParameters;
    }

    public final Context getApplicationContext() {
        return this.mAppContext;
    }

    public Executor getBackgroundExecutor() {
        return this.mWorkerParams.f6704f;
    }

    public d1 getForegroundInfoAsync() {
        androidx.work.impl.utils.futures.k kVar = new androidx.work.impl.utils.futures.k();
        kVar.i(new IllegalStateException("Expedited WorkRequests require a ListenableWorker to provide an implementation for `getForegroundInfoAsync()`"));
        return kVar;
    }

    public final UUID getId() {
        return this.mWorkerParams.f6699a;
    }

    public final h getInputData() {
        return this.mWorkerParams.f6700b;
    }

    public final Network getNetwork() {
        return (Network) this.mWorkerParams.f6702d.f1061n;
    }

    public final int getRunAttemptCount() {
        return this.mWorkerParams.f6703e;
    }

    public final int getStopReason() {
        return this.mStopReason;
    }

    public final Set<String> getTags() {
        return this.mWorkerParams.f6701c;
    }

    public androidx.work.impl.utils.taskexecutor.b getTaskExecutor() {
        return this.mWorkerParams.f6705g;
    }

    public final List<String> getTriggeredContentAuthorities() {
        return (List) this.mWorkerParams.f6702d.f1059l;
    }

    public final List<Uri> getTriggeredContentUris() {
        return (List) this.mWorkerParams.f6702d.f1060m;
    }

    public i0 getWorkerFactory() {
        return this.mWorkerParams.f6706h;
    }

    public final boolean isStopped() {
        return this.mStopReason != -256;
    }

    public final boolean isUsed() {
        return this.mUsed;
    }

    public final d1<Void> setForegroundAsync(j jVar) {
        return this.mWorkerParams.f6708j.a(getApplicationContext(), getId(), jVar);
    }

    public d1<Void> setProgressAsync(h hVar) {
        androidx.work.impl.utils.s sVar = this.mWorkerParams.f6707i;
        getApplicationContext();
        UUID id = getId();
        sVar.getClass();
        androidx.work.impl.utils.futures.k kVar = new androidx.work.impl.utils.futures.k();
        sVar.f7073b.d(new androidx.appcompat.view.menu.h(sVar, id, hVar, kVar, 2));
        return kVar;
    }

    public final void setUsed() {
        this.mUsed = true;
    }

    public abstract d1 startWork();

    public final void stop(int i10) {
        this.mStopReason = i10;
        onStopped();
    }

    public void onStopped() {
    }
}
