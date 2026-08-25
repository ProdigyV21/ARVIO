package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.TvDeviceAuthRepository", f = "TvDeviceAuthRepository.kt", l = {54}, m = "startSession-IoAF18A", v = 2)
public final class TvDeviceAuthRepository$startSession$1 extends f7.c {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TvDeviceAuthRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvDeviceAuthRepository$startSession$1(TvDeviceAuthRepository tvDeviceAuthRepository, d7.d<? super TvDeviceAuthRepository$startSession$1> dVar) {
        super(dVar);
        this.this$0 = tvDeviceAuthRepository;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM6148startSessionIoAF18A = this.this$0.m6148startSessionIoAF18A(this);
        return objM6148startSessionIoAF18A == e7.a.f15033i ? objM6148startSessionIoAF18A : new x6.d0(objM6148startSessionIoAF18A);
    }
}
