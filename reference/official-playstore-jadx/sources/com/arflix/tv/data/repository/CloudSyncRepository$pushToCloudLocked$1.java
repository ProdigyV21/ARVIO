package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.CloudSyncRepository", f = "CloudSyncRepository.kt", l = {867, 877, 878, 891, 911, 915, 916, 923, 958, 960, 974}, m = "pushToCloudLocked-0E7RQCE", v = 2)
public final class CloudSyncRepository$pushToCloudLocked$1 extends f7.c {
    int I$0;
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    boolean Z$0;
    boolean Z$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CloudSyncRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CloudSyncRepository$pushToCloudLocked$1(CloudSyncRepository cloudSyncRepository, d7.d<? super CloudSyncRepository$pushToCloudLocked$1> dVar) {
        super(dVar);
        this.this$0 = cloudSyncRepository;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM6122pushToCloudLocked0E7RQCE = this.this$0.m6122pushToCloudLocked0E7RQCE(false, false, this);
        return objM6122pushToCloudLocked0E7RQCE == e7.a.f15033i ? objM6122pushToCloudLocked0E7RQCE : new x6.d0(objM6122pushToCloudLocked0E7RQCE);
    }
}
