package com.arflix.tv.data.repository;

import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "<anonymous>", "()V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.CloudSyncRepository$pushToCloudLocked$2$1", f = "CloudSyncRepository.kt", l = {912, 913}, m = "invokeSuspend", v = 2)
public final class CloudSyncRepository$pushToCloudLocked$2$1 extends f7.j implements r7.l<d7.d<? super t0>, Object> {
    final /* synthetic */ String $existingRemotePayload;
    final /* synthetic */ CloudSyncRepository $this_runCatching;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CloudSyncRepository$pushToCloudLocked$2$1(CloudSyncRepository cloudSyncRepository, String str, d7.d<? super CloudSyncRepository$pushToCloudLocked$2$1> dVar) {
        super(1, dVar);
        this.$this_runCatching = cloudSyncRepository;
        this.$existingRemotePayload = str;
    }

    @Override // f7.a
    public final d7.d<t0> create(d7.d<?> dVar) {
        return new CloudSyncRepository$pushToCloudLocked$2$1(this.$this_runCatching, this.$existingRemotePayload, dVar);
    }

    @Override // r7.l
    public final Object invoke(d7.d<? super t0> dVar) {
        return ((CloudSyncRepository$pushToCloudLocked$2$1) create(dVar)).invokeSuspend(t0.f22605a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0034, code lost:
    
        if (r5.applyCloudPayload(r0, r4) == r3) goto L15;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r5) {
        /*
            r4 = this;
            int r0 = r4.label
            r1 = 2
            r2 = 1
            e7.a r3 = e7.a.f15033i
            if (r0 == 0) goto L1c
            if (r0 == r2) goto L18
            if (r0 != r1) goto L10
            k2.c.G(r5)
            goto L37
        L10:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L18:
            k2.c.G(r5)
            goto L2a
        L1c:
            k2.c.G(r5)
            com.arflix.tv.data.repository.CloudSyncRepository r5 = r4.$this_runCatching
            r4.label = r2
            java.lang.Object r5 = com.arflix.tv.data.repository.CloudSyncRepository.access$clearStaleLocalDirtyBeforeRemoteRestore(r5, r4)
            if (r5 != r3) goto L2a
            goto L36
        L2a:
            com.arflix.tv.data.repository.CloudSyncRepository r5 = r4.$this_runCatching
            java.lang.String r0 = r4.$existingRemotePayload
            r4.label = r1
            java.lang.Object r5 = com.arflix.tv.data.repository.CloudSyncRepository.access$applyCloudPayload(r5, r0, r4)
            if (r5 != r3) goto L37
        L36:
            return r3
        L37:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CloudSyncRepository$pushToCloudLocked$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
