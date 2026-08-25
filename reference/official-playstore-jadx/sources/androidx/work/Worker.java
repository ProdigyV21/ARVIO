package androidx.work;

import android.content.Context;
import androidx.leanback.widget.u;
import androidx.work.impl.utils.futures.k;
import com.google.common.util.concurrent.d1;
import com.google.common.util.concurrent.w0;
import m2.q;
import m2.s;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Worker extends s {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public k f6698i;

    public Worker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    public abstract q a();

    @Override // m2.s
    public final d1 getForegroundInfoAsync() {
        k kVar = new k();
        getBackgroundExecutor().execute(new w0(28, this, false, kVar));
        return kVar;
    }

    @Override // m2.s
    public final d1 startWork() {
        this.f6698i = new k();
        getBackgroundExecutor().execute(new u(this, 16));
        return this.f6698i;
    }
}
