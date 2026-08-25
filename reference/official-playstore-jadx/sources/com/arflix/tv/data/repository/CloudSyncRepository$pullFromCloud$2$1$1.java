package com.arflix.tv.data.repository;

import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "<anonymous>", "()V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.CloudSyncRepository$pullFromCloud$2$1$1", f = "CloudSyncRepository.kt", l = {1148, 1150}, m = "invokeSuspend", v = 2)
public final class CloudSyncRepository$pullFromCloud$2$1$1 extends f7.j implements r7.l<d7.d<? super t0>, Object> {
    final /* synthetic */ String $payload;
    final /* synthetic */ boolean $pushPendingLocalFirst;
    final /* synthetic */ CloudSyncRepository $this_runCatching;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CloudSyncRepository$pullFromCloud$2$1$1(boolean z, CloudSyncRepository cloudSyncRepository, String str, d7.d<? super CloudSyncRepository$pullFromCloud$2$1$1> dVar) {
        super(1, dVar);
        this.$pushPendingLocalFirst = z;
        this.$this_runCatching = cloudSyncRepository;
        this.$payload = str;
    }

    @Override // f7.a
    public final d7.d<t0> create(d7.d<?> dVar) {
        return new CloudSyncRepository$pullFromCloud$2$1$1(this.$pushPendingLocalFirst, this.$this_runCatching, this.$payload, dVar);
    }

    @Override // r7.l
    public final Object invoke(d7.d<? super t0> dVar) {
        return ((CloudSyncRepository$pullFromCloud$2$1$1) create(dVar)).invokeSuspend(t0.f22605a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0038, code lost:
    
        if (r5.applyCloudPayload(r0, r4) == r3) goto L17;
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
            goto L3b
        L10:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L18:
            k2.c.G(r5)
            goto L2e
        L1c:
            k2.c.G(r5)
            boolean r5 = r4.$pushPendingLocalFirst
            if (r5 != 0) goto L2e
            com.arflix.tv.data.repository.CloudSyncRepository r5 = r4.$this_runCatching
            r4.label = r2
            java.lang.Object r5 = com.arflix.tv.data.repository.CloudSyncRepository.access$clearStaleLocalDirtyBeforeRemoteRestore(r5, r4)
            if (r5 != r3) goto L2e
            goto L3a
        L2e:
            com.arflix.tv.data.repository.CloudSyncRepository r5 = r4.$this_runCatching
            java.lang.String r0 = r4.$payload
            r4.label = r1
            java.lang.Object r5 = com.arflix.tv.data.repository.CloudSyncRepository.access$applyCloudPayload(r5, r0, r4)
            if (r5 != r3) goto L3b
        L3a:
            return r3
        L3b:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CloudSyncRepository$pullFromCloud$2$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
