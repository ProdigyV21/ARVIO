package androidx.work;

import ab.h;
import android.content.Context;
import androidx.tv.foundation.lazy.grid.a1;
import androidx.work.impl.constraints.l;
import androidx.work.impl.utils.futures.a;
import androidx.work.impl.utils.futures.k;
import com.google.common.util.concurrent.d1;
import com.google.common.util.concurrent.w0;
import d7.d;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import ka.f0;
import ka.l0;
import ka.m0;
import ka.t;
import ka.x0;
import ka.x1;
import kotlin.Metadata;
import m2.g;
import m2.i;
import m2.j;
import m2.n;
import m2.r;
import m2.s;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\n\u0010\u000bJ\u0013\u0010\r\u001a\u00020\fH\u0096@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u001b\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\fH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\b¢\u0006\u0004\b\u0017\u0010\u000bJ\r\u0010\u0018\u001a\u00020\u0011¢\u0006\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001b\u001a\u00020\u001a8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR \u0010 \u001a\b\u0012\u0004\u0012\u00020\t0\u001f8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R \u0010%\u001a\u00020$8\u0016X\u0097\u0004¢\u0006\u0012\n\u0004\b%\u0010&\u0012\u0004\b)\u0010\u0019\u001a\u0004\b'\u0010(\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006*"}, d2 = {"Landroidx/work/CoroutineWorker;", "Lm2/s;", "Landroid/content/Context;", "appContext", "Landroidx/work/WorkerParameters;", "params", "<init>", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "Lcom/google/common/util/concurrent/d1;", "Lm2/r;", "startWork", "()Lcom/google/common/util/concurrent/d1;", "Lm2/j;", "getForegroundInfo", "(Ld7/d;)Ljava/lang/Object;", "Lm2/h;", "data", "Lx6/t0;", "setProgress", "(Lm2/h;Ld7/d;)Ljava/lang/Object;", "foregroundInfo", "setForeground", "(Lm2/j;Ld7/d;)Ljava/lang/Object;", "getForegroundInfoAsync", "onStopped", "()V", "Lka/t;", "job", "Lka/t;", "getJob$work_runtime_release", "()Lka/t;", "Landroidx/work/impl/utils/futures/k;", "future", "Landroidx/work/impl/utils/futures/k;", "getFuture$work_runtime_release", "()Landroidx/work/impl/utils/futures/k;", "Lka/f0;", "coroutineContext", "Lka/f0;", "getCoroutineContext", "()Lka/f0;", "getCoroutineContext$annotations", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class CoroutineWorker extends s {
    private final f0 coroutineContext;
    private final k future;
    private final t job;

    public CoroutineWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        this.job = m0.c();
        k kVar = new k();
        this.future = kVar;
        kVar.addListener(new g(this, 0), getTaskExecutor().c());
        this.coroutineContext = x0.f19653b;
    }

    public static void a(CoroutineWorker coroutineWorker) {
        if (coroutineWorker.future.f7037i instanceof a) {
            coroutineWorker.job.cancel((CancellationException) null);
        }
    }

    @e
    public static /* synthetic */ void getCoroutineContext$annotations() {
    }

    public static /* synthetic */ Object getForegroundInfo$suspendImpl(CoroutineWorker coroutineWorker, d<? super j> dVar) {
        throw new IllegalStateException("Not implemented");
    }

    public abstract Object doWork(d dVar);

    public f0 getCoroutineContext() {
        return this.coroutineContext;
    }

    public Object getForegroundInfo(d<? super j> dVar) {
        return getForegroundInfo$suspendImpl(this, dVar);
    }

    @Override // m2.s
    public final d1<j> getForegroundInfoAsync() {
        x1 x1VarC = m0.c();
        pa.e eVarA = l0.a(getCoroutineContext().plus(x1VarC));
        n nVar = new n(x1VarC);
        m0.p(eVarA, null, 0, new l(nVar, this, null, 2), 3);
        return nVar;
    }

    /* JADX INFO: renamed from: getFuture$work_runtime_release, reason: from getter */
    public final k getFuture() {
        return this.future;
    }

    /* JADX INFO: renamed from: getJob$work_runtime_release, reason: from getter */
    public final t getJob() {
        return this.job;
    }

    @Override // m2.s
    public final void onStopped() {
        this.future.cancel(false);
    }

    public final Object setForeground(j jVar, d<? super t0> dVar) throws Throwable {
        d1<Void> foregroundAsync = setForegroundAsync(jVar);
        if (foregroundAsync.isDone()) {
            try {
                foregroundAsync.get();
            } catch (ExecutionException e5) {
                Throwable cause = e5.getCause();
                if (cause == null) {
                    throw e5;
                }
                throw cause;
            }
        } else {
            ka.l lVar = new ka.l(1, t7.a.A(dVar));
            lVar.s();
            foregroundAsync.addListener(new w0(lVar, foregroundAsync, 27), i.f20204i);
            lVar.c(new h(foregroundAsync, 25));
            Object objR = lVar.r();
            if (objR == e7.a.f15033i) {
                return objR;
            }
        }
        return t0.f22605a;
    }

    public final Object setProgress(m2.h hVar, d<? super t0> dVar) throws Throwable {
        d1<Void> progressAsync = setProgressAsync(hVar);
        if (progressAsync.isDone()) {
            try {
                progressAsync.get();
            } catch (ExecutionException e5) {
                Throwable cause = e5.getCause();
                if (cause == null) {
                    throw e5;
                }
                throw cause;
            }
        } else {
            ka.l lVar = new ka.l(1, t7.a.A(dVar));
            lVar.s();
            progressAsync.addListener(new w0(lVar, progressAsync, 27), i.f20204i);
            lVar.c(new h(progressAsync, 25));
            Object objR = lVar.r();
            if (objR == e7.a.f15033i) {
                return objR;
            }
        }
        return t0.f22605a;
    }

    @Override // m2.s
    public final d1<r> startWork() {
        m0.p(l0.a(getCoroutineContext().plus(this.job)), null, 0, new a1(this, (d) null, 2), 3);
        return this.future;
    }
}
