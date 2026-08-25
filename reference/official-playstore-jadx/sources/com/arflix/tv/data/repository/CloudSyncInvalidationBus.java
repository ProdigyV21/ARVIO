package com.arflix.tv.data.repository;

import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import na.r0;
import na.u0;
import na.x0;
import na.y0;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J+\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\b\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\u000bJ4\u0010\u0010\u001a\u00028\u0000\"\u0004\b\u0000\u0010\f2\u001c\u0010\u000f\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00010\rH\u0086@¢\u0006\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00130\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001f\u001a\u00020\u001e8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 ¨\u0006!"}, d2 = {"Lcom/arflix/tv/data/repository/CloudSyncInvalidationBus;", "", "<init>", "()V", "Lcom/arflix/tv/data/repository/CloudSyncScope;", "scope", "", "profileId", "reason", "Lx6/t0;", "markDirty", "(Lcom/arflix/tv/data/repository/CloudSyncScope;Ljava/lang/String;Ljava/lang/String;)V", "T", "Lkotlin/Function1;", "Ld7/d;", "block", "suppressDuringRemoteApply", "(Lr7/l;Ld7/d;)Ljava/lang/Object;", "Lna/p0;", "Lcom/arflix/tv/data/repository/CloudSyncInvalidation;", "_events", "Lna/p0;", "Lna/u0;", "events", "Lna/u0;", "getEvents", "()Lna/u0;", "Ljava/util/concurrent/atomic/AtomicInteger;", "restoreDepth", "Ljava/util/concurrent/atomic/AtomicInteger;", "", "isApplyingRemoteState", "()Z", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CloudSyncInvalidationBus {
    public static final int $stable = 8;
    private final na.p0<CloudSyncInvalidation> _events;
    private final u0<CloudSyncInvalidation> events;
    private final AtomicInteger restoreDepth;

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CloudSyncInvalidationBus$suppressDuringRemoteApply$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CloudSyncInvalidationBus", f = "CloudSyncInvalidationBus.kt", l = {56}, m = "suppressDuringRemoteApply", v = 2)
    public static final class AnonymousClass1<T> extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CloudSyncInvalidationBus.this.suppressDuringRemoteApply(null, this);
        }
    }

    @Inject
    public CloudSyncInvalidationBus() {
        x0 x0VarA = y0.a(64, 4);
        this._events = x0VarA;
        this.events = new r0(x0VarA);
        this.restoreDepth = new AtomicInteger(0);
    }

    public static /* synthetic */ void markDirty$default(CloudSyncInvalidationBus cloudSyncInvalidationBus, CloudSyncScope cloudSyncScope, String str, String str2, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str = null;
        }
        if ((i10 & 4) != 0) {
            str2 = "";
        }
        cloudSyncInvalidationBus.markDirty(cloudSyncScope, str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int suppressDuringRemoteApply$lambda$0(int i10) {
        int i11 = i10 - 1;
        if (i11 < 0) {
            return 0;
        }
        return i11;
    }

    public final u0<CloudSyncInvalidation> getEvents() {
        return this.events;
    }

    public final boolean isApplyingRemoteState() {
        return this.restoreDepth.get() > 0;
    }

    public final void markDirty(CloudSyncScope scope, String profileId, String reason) {
        String string;
        if (isApplyingRemoteState()) {
            return;
        }
        this._events.b(new CloudSyncInvalidation(scope, (profileId == null || (string = kotlin.text.o.L0(profileId).toString()) == null || kotlin.text.o.h0(string)) ? null : string, reason, 0L, 8, null));
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final <T> java.lang.Object suppressDuringRemoteApply(r7.l<? super d7.d<? super T>, ? extends java.lang.Object> r5, d7.d<? super T> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.arflix.tv.data.repository.CloudSyncInvalidationBus.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            com.arflix.tv.data.repository.CloudSyncInvalidationBus$suppressDuringRemoteApply$1 r0 = (com.arflix.tv.data.repository.CloudSyncInvalidationBus.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.CloudSyncInvalidationBus$suppressDuringRemoteApply$1 r0 = new com.arflix.tv.data.repository.CloudSyncInvalidationBus$suppressDuringRemoteApply$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L33
            if (r1 != r2) goto L2b
            java.lang.Object r5 = r0.L$0
            r7.l r5 = (r7.l) r5
            k2.c.G(r6)     // Catch: java.lang.Throwable -> L29
            goto L49
        L29:
            r5 = move-exception
            goto L54
        L2b:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L33:
            k2.c.G(r6)
            java.util.concurrent.atomic.AtomicInteger r6 = r4.restoreDepth
            r6.incrementAndGet()
            r6 = 0
            r0.L$0 = r6     // Catch: java.lang.Throwable -> L29
            r0.label = r2     // Catch: java.lang.Throwable -> L29
            java.lang.Object r6 = r5.invoke(r0)     // Catch: java.lang.Throwable -> L29
            e7.a r5 = e7.a.f15033i
            if (r6 != r5) goto L49
            return r5
        L49:
            java.util.concurrent.atomic.AtomicInteger r5 = r4.restoreDepth
            com.arflix.tv.data.repository.d r0 = new com.arflix.tv.data.repository.d
            r0.<init>()
            j$.util.concurrent.atomic.DesugarAtomicInteger.updateAndGet(r5, r0)
            return r6
        L54:
            java.util.concurrent.atomic.AtomicInteger r6 = r4.restoreDepth
            com.arflix.tv.data.repository.d r0 = new com.arflix.tv.data.repository.d
            r0.<init>()
            j$.util.concurrent.atomic.DesugarAtomicInteger.updateAndGet(r6, r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CloudSyncInvalidationBus.suppressDuringRemoteApply(r7.l, d7.d):java.lang.Object");
    }
}
