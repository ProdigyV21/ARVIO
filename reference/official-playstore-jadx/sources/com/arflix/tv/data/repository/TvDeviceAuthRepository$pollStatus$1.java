package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.TvDeviceAuthRepository", f = "TvDeviceAuthRepository.kt", l = {94}, m = "pollStatus-gIAlu-s", v = 2)
public final class TvDeviceAuthRepository$pollStatus$1 extends f7.c {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TvDeviceAuthRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvDeviceAuthRepository$pollStatus$1(TvDeviceAuthRepository tvDeviceAuthRepository, d7.d<? super TvDeviceAuthRepository$pollStatus$1> dVar) {
        super(dVar);
        this.this$0 = tvDeviceAuthRepository;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM6147pollStatusgIAlus = this.this$0.m6147pollStatusgIAlus(null, this);
        return objM6147pollStatusgIAlus == e7.a.f15033i ? objM6147pollStatusgIAlus : new x6.d0(objM6147pollStatusgIAlus);
    }
}
