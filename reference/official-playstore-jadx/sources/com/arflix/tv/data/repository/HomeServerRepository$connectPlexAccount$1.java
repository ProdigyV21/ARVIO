package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository", f = "HomeServerRepository.kt", l = {387}, m = "connectPlexAccount-BWLJW6A", v = 2)
public final class HomeServerRepository$connectPlexAccount$1 extends f7.c {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HomeServerRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeServerRepository$connectPlexAccount$1(HomeServerRepository homeServerRepository, d7.d<? super HomeServerRepository$connectPlexAccount$1> dVar) {
        super(dVar);
        this.this$0 = homeServerRepository;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM6133connectPlexAccountBWLJW6A = this.this$0.m6133connectPlexAccountBWLJW6A(null, null, null, this);
        return objM6133connectPlexAccountBWLJW6A == e7.a.f15033i ? objM6133connectPlexAccountBWLJW6A : new x6.d0(objM6133connectPlexAccountBWLJW6A);
    }
}
