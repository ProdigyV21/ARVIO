package com.arflix.tv.worker;

import android.content.Context;
import androidx.work.CoroutineWorker;
import androidx.work.WorkerParameters;
import androidx.work.impl.m;
import androidx.work.impl.model.p;
import androidx.work.impl.r;
import com.arflix.tv.data.repository.CloudSyncRepository;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import f7.c;
import f7.e;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import m2.t;
import m2.v;
import m2.w;
import qb.d;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB%\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000b\u001a\u00020\nH\u0096@¢\u0006\u0004\b\u000b\u0010\fR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\rR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u000e¨\u0006\u0010"}, d2 = {"Lcom/arflix/tv/worker/CloudSyncWorker;", "Landroidx/work/CoroutineWorker;", "Landroid/content/Context;", "context", "Landroidx/work/WorkerParameters;", "workerParams", "Lcom/arflix/tv/data/repository/CloudSyncRepository;", "cloudSyncRepository", "<init>", "(Landroid/content/Context;Landroidx/work/WorkerParameters;Lcom/arflix/tv/data/repository/CloudSyncRepository;)V", "Lm2/r;", "doWork", "(Ld7/d;)Ljava/lang/Object;", "Landroid/content/Context;", "Lcom/arflix/tv/data/repository/CloudSyncRepository;", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CloudSyncWorker extends CoroutineWorker {
    private static final String TAG = "CloudSyncWorker";
    private static final String WORK_NAME = "CloudSyncRecoveryWork";
    private final CloudSyncRepository cloudSyncRepository;
    private final Context context;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bR\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\f\u0010\u000b¨\u0006\r"}, d2 = {"Lcom/arflix/tv/worker/CloudSyncWorker$Companion;", "", "<init>", "()V", "Landroid/content/Context;", "context", "Lx6/t0;", "enqueueRecovery", "(Landroid/content/Context;)V", "", "TAG", "Ljava/lang/String;", "WORK_NAME", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final void enqueueRecovery(Context context) {
            v vVar = new v(CloudSyncWorker.class, 0);
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            vVar.f20218a = true;
            p pVar = vVar.f20220c;
            pVar.f6932l = 1;
            long millis = timeUnit.toMillis(10000L);
            String str = p.x;
            if (millis > 18000000) {
                t.d().g(str, "Backoff delay duration exceeds maximum value");
            }
            if (millis < 10000) {
                t.d().g(str, "Backoff delay duration less than minimum value");
            }
            pVar.f6933m = d.o(millis, 10000L, 18000000L);
            w wVar = (w) vVar.a();
            r rVarZ = r.Z(context);
            rVarZ.getClass();
            new m(rVarZ, CloudSyncWorker.WORK_NAME, 1, Collections.singletonList(wVar), 0).J();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.worker.CloudSyncWorker$doWork$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.worker.CloudSyncWorker", f = "CloudSyncWorker.kt", l = {29, 35}, m = "doWork", v = 2)
    public static final class AnonymousClass1 extends c {
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CloudSyncWorker.this.doWork(this);
        }
    }

    @AssistedInject
    public CloudSyncWorker(@Assisted Context context, @Assisted WorkerParameters workerParameters, CloudSyncRepository cloudSyncRepository) {
        super(context, workerParameters);
        this.context = context;
        this.cloudSyncRepository = cloudSyncRepository;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0070, code lost:
    
        if (r7 == r5) goto L28;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // androidx.work.CoroutineWorker
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object doWork(d7.d<? super m2.r> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.arflix.tv.worker.CloudSyncWorker.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            com.arflix.tv.worker.CloudSyncWorker$doWork$1 r0 = (com.arflix.tv.worker.CloudSyncWorker.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.worker.CloudSyncWorker$doWork$1 r0 = new com.arflix.tv.worker.CloudSyncWorker$doWork$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            java.lang.String r4 = "CloudSyncWorker"
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L3e
            if (r1 == r3) goto L3a
            if (r1 != r2) goto L32
            k2.c.G(r7)     // Catch: java.lang.Exception -> L30
            x6.d0 r7 = (x6.d0) r7     // Catch: java.lang.Exception -> L30
            java.lang.Object r7 = r7.f22580i     // Catch: java.lang.Exception -> L30
            goto L73
        L30:
            r7 = move-exception
            goto L8f
        L32:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L3a:
            k2.c.G(r7)
            goto L51
        L3e:
            k2.c.G(r7)
            java.lang.String r7 = "Executing background cloud sync recovery"
            android.util.Log.i(r4, r7)
            com.arflix.tv.data.repository.CloudSyncRepository r7 = r6.cloudSyncRepository
            r0.label = r3
            java.lang.Object r7 = r7.hasPendingLocalChanges(r0)
            if (r7 != r5) goto L51
            goto L72
        L51:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 != 0) goto L66
            java.lang.String r7 = "Cloud state is not dirty. Skipping sync."
            android.util.Log.i(r4, r7)
            m2.q r7 = new m2.q
            m2.h r0 = m2.h.f20202c
            r7.<init>(r0)
            return r7
        L66:
            com.arflix.tv.data.repository.CloudSyncRepository r7 = r6.cloudSyncRepository     // Catch: java.lang.Exception -> L30
            r0.label = r2     // Catch: java.lang.Exception -> L30
            r1 = 0
            r2 = 0
            java.lang.Object r7 = com.arflix.tv.data.repository.CloudSyncRepository.m6121pushToCloudgIAlus$default(r7, r1, r0, r3, r2)     // Catch: java.lang.Exception -> L30
            if (r7 != r5) goto L73
        L72:
            return r5
        L73:
            boolean r7 = r7 instanceof x6.c0     // Catch: java.lang.Exception -> L30
            if (r7 != 0) goto L84
            java.lang.String r7 = "Background cloud sync recovery succeeded"
            android.util.Log.i(r4, r7)     // Catch: java.lang.Exception -> L30
            m2.q r7 = new m2.q     // Catch: java.lang.Exception -> L30
            m2.h r0 = m2.h.f20202c     // Catch: java.lang.Exception -> L30
            r7.<init>(r0)     // Catch: java.lang.Exception -> L30
            return r7
        L84:
            java.lang.String r7 = "Background cloud sync recovery failed, will retry"
            android.util.Log.w(r4, r7)     // Catch: java.lang.Exception -> L30
            m2.p r7 = new m2.p     // Catch: java.lang.Exception -> L30
            r7.<init>()     // Catch: java.lang.Exception -> L30
            return r7
        L8f:
            java.lang.String r0 = r7.getMessage()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Error in background cloud sync: "
            r1.<init>(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            android.util.Log.e(r4, r0, r7)
            m2.p r7 = new m2.p
            r7.<init>()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.worker.CloudSyncWorker.doWork(d7.d):java.lang.Object");
    }
}
