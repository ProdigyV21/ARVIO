package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository", f = "HomeServerRepository.kt", l = {498}, m = "pollPlexPinAuth-gIAlu-s", v = 2)
public final class HomeServerRepository$pollPlexPinAuth$1 extends f7.c {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HomeServerRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeServerRepository$pollPlexPinAuth$1(HomeServerRepository homeServerRepository, d7.d<? super HomeServerRepository$pollPlexPinAuth$1> dVar) {
        super(dVar);
        this.this$0 = homeServerRepository;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM6135pollPlexPinAuthgIAlus = this.this$0.m6135pollPlexPinAuthgIAlus(null, this);
        return objM6135pollPlexPinAuthgIAlus == e7.a.f15033i ? objM6135pollPlexPinAuthgIAlus : new x6.d0(objM6135pollPlexPinAuthgIAlus);
    }
}
