package com.arflix.tv.data.repository;

import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.CloudSyncCoordinator$scheduleFlush$1$1", f = "CloudSyncCoordinator.kt", l = {95, 97, 99, 106}, m = "invokeSuspend", v = 2)
public final class CloudSyncCoordinator$scheduleFlush$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ CloudSyncInvalidation $invalidation;
    long J$0;
    Object L$0;
    int label;
    final /* synthetic */ CloudSyncCoordinator this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CloudSyncCoordinator$scheduleFlush$1$1(CloudSyncCoordinator cloudSyncCoordinator, CloudSyncInvalidation cloudSyncInvalidation, d7.d<? super CloudSyncCoordinator$scheduleFlush$1$1> dVar) {
        super(2, dVar);
        this.this$0 = cloudSyncCoordinator;
        this.$invalidation = cloudSyncInvalidation;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new CloudSyncCoordinator$scheduleFlush$1$1(this.this$0, this.$invalidation, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x00d6, code lost:
    
        if (r14.m6125pushToCloudgIAlus(true, r13) == r8) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x018d, code lost:
    
        if (r14.markLocalStateDirtyNow(r13) != r8) goto L56;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            Method dump skipped, instruction units count: 440
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CloudSyncCoordinator$scheduleFlush$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((CloudSyncCoordinator$scheduleFlush$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
