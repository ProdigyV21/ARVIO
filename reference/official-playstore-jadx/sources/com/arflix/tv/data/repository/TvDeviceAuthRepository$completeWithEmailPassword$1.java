package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.TvDeviceAuthRepository", f = "TvDeviceAuthRepository.kt", l = {151}, m = "completeWithEmailPassword-yxL6bBk", v = 2)
public final class TvDeviceAuthRepository$completeWithEmailPassword$1 extends f7.c {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TvDeviceAuthRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvDeviceAuthRepository$completeWithEmailPassword$1(TvDeviceAuthRepository tvDeviceAuthRepository, d7.d<? super TvDeviceAuthRepository$completeWithEmailPassword$1> dVar) {
        super(dVar);
        this.this$0 = tvDeviceAuthRepository;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM6146completeWithEmailPasswordyxL6bBk = this.this$0.m6146completeWithEmailPasswordyxL6bBk(null, null, null, null, this);
        return objM6146completeWithEmailPasswordyxL6bBk == e7.a.f15033i ? objM6146completeWithEmailPasswordyxL6bBk : new x6.d0(objM6146completeWithEmailPasswordyxL6bBk);
    }
}
