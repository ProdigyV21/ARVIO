package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.AuthRepository", f = "AuthRepository.kt", l = {1988, 2182, 2211, 2238}, m = "saveAccountSyncPayloadToUserSettings-0E7RQCE", v = 2)
public final class AuthRepository$saveAccountSyncPayloadToUserSettings$1 extends f7.c {
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AuthRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AuthRepository$saveAccountSyncPayloadToUserSettings$1(AuthRepository authRepository, d7.d<? super AuthRepository$saveAccountSyncPayloadToUserSettings$1> dVar) {
        super(dVar);
        this.this$0 = authRepository;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Exception {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM6094saveAccountSyncPayloadToUserSettings0E7RQCE = this.this$0.m6094saveAccountSyncPayloadToUserSettings0E7RQCE(null, null, this);
        return objM6094saveAccountSyncPayloadToUserSettings0E7RQCE == e7.a.f15033i ? objM6094saveAccountSyncPayloadToUserSettings0E7RQCE : new x6.d0(objM6094saveAccountSyncPayloadToUserSettings0E7RQCE);
    }
}
