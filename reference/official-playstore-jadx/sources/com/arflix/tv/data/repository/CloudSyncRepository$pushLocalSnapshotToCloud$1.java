package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.CloudSyncRepository", f = "CloudSyncRepository.kt", l = {1754, 846}, m = "pushLocalSnapshotToCloud-IoAF18A", v = 2)
public final class CloudSyncRepository$pushLocalSnapshotToCloud$1 extends f7.c {
    int I$0;
    int I$1;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CloudSyncRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CloudSyncRepository$pushLocalSnapshotToCloud$1(CloudSyncRepository cloudSyncRepository, d7.d<? super CloudSyncRepository$pushLocalSnapshotToCloud$1> dVar) {
        super(dVar);
        this.this$0 = cloudSyncRepository;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM6124pushLocalSnapshotToCloudIoAF18A = this.this$0.m6124pushLocalSnapshotToCloudIoAF18A(this);
        return objM6124pushLocalSnapshotToCloudIoAF18A == e7.a.f15033i ? objM6124pushLocalSnapshotToCloudIoAF18A : new x6.d0(objM6124pushLocalSnapshotToCloudIoAF18A);
    }
}
