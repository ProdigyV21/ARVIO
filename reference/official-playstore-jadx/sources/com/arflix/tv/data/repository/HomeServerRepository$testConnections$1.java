package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository", f = "HomeServerRepository.kt", l = {405}, m = "testConnections-IoAF18A", v = 2)
public final class HomeServerRepository$testConnections$1 extends f7.c {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HomeServerRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeServerRepository$testConnections$1(HomeServerRepository homeServerRepository, d7.d<? super HomeServerRepository$testConnections$1> dVar) {
        super(dVar);
        this.this$0 = homeServerRepository;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM6139testConnectionsIoAF18A = this.this$0.m6139testConnectionsIoAF18A(this);
        return objM6139testConnectionsIoAF18A == e7.a.f15033i ? objM6139testConnectionsIoAF18A : new x6.d0(objM6139testConnectionsIoAF18A);
    }
}
