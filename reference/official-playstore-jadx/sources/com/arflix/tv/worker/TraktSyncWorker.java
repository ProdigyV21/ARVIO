package com.arflix.tv.worker;

import android.content.Context;
import androidx.work.CoroutineWorker;
import androidx.work.WorkerParameters;
import com.arflix.tv.data.repository.TraktRepository;
import com.arflix.tv.data.repository.TraktSyncService;
import d7.d;
import dagger.hilt.android.EntryPointAccessors;
import f7.c;
import f7.e;
import kotlin.Metadata;
import x6.i0;
import x6.s;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000 \u00112\u00020\u0001:\u0002\u0012\u0011B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\t\u001a\u00020\bH\u0096@¢\u0006\u0004\b\t\u0010\nR\u001b\u0010\u0010\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0013"}, d2 = {"Lcom/arflix/tv/worker/TraktSyncWorker;", "Landroidx/work/CoroutineWorker;", "Landroid/content/Context;", "appContext", "Landroidx/work/WorkerParameters;", "params", "<init>", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "Lm2/r;", "doWork", "(Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/worker/TraktSyncWorker$TraktSyncWorkerEntryPoint;", "deps$delegate", "Lx6/s;", "getDeps", "()Lcom/arflix/tv/worker/TraktSyncWorker$TraktSyncWorkerEntryPoint;", "deps", "Companion", "TraktSyncWorkerEntryPoint", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TraktSyncWorker extends CoroutineWorker {
    public static final String INPUT_SYNC_MODE = "sync_mode";
    public static final long SYNC_INTERVAL_HOURS = 6;
    public static final String SYNC_MODE_FULL = "full";
    public static final String SYNC_MODE_INCREMENTAL = "incremental";
    public static final String TAG = "TraktSyncWorker";
    public static final String WORK_NAME = "trakt_sync_worker";
    public static final String WORK_NAME_ON_OPEN = "trakt_sync_on_open";

    /* JADX INFO: renamed from: deps$delegate, reason: from kotlin metadata */
    private final s deps;
    public static final int $stable = 8;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lcom/arflix/tv/worker/TraktSyncWorker$TraktSyncWorkerEntryPoint;", "", "traktRepository", "Lcom/arflix/tv/data/repository/TraktRepository;", "traktSyncService", "Lcom/arflix/tv/data/repository/TraktSyncService;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public interface TraktSyncWorkerEntryPoint {
        TraktRepository traktRepository();

        TraktSyncService traktSyncService();
    }

    /* JADX INFO: renamed from: com.arflix.tv.worker.TraktSyncWorker$doWork$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.worker.TraktSyncWorker", f = "TraktSyncWorker.kt", l = {52, 61, 62}, m = "doWork", v = 2)
    public static final class AnonymousClass1 extends c {
        Object L$0;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TraktSyncWorker.this.doWork(this);
        }
    }

    public TraktSyncWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        this.deps = new i0(new com.arflix.tv.d(this, 3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TraktSyncWorkerEntryPoint deps_delegate$lambda$0(TraktSyncWorker traktSyncWorker) {
        return (TraktSyncWorkerEntryPoint) EntryPointAccessors.fromApplication(traktSyncWorker.getApplicationContext(), TraktSyncWorkerEntryPoint.class);
    }

    private final TraktSyncWorkerEntryPoint getDeps() {
        return (TraktSyncWorkerEntryPoint) this.deps.getValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x00ba, code lost:
    
        if (r8 == r5) goto L43;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // androidx.work.CoroutineWorker
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object doWork(d7.d<? super m2.r> r8) {
        /*
            Method dump skipped, instruction units count: 217
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.worker.TraktSyncWorker.doWork(d7.d):java.lang.Object");
    }
}
