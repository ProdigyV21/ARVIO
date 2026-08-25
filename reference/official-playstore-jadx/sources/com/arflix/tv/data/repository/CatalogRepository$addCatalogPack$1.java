package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {750, 755, 779, 802}, m = "addCatalogPack-0E7RQCE", v = 2)
public final class CatalogRepository$addCatalogPack$1 extends f7.c {
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$11;
    Object L$12;
    Object L$13;
    Object L$14;
    Object L$15;
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
    final /* synthetic */ CatalogRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CatalogRepository$addCatalogPack$1(CatalogRepository catalogRepository, d7.d<? super CatalogRepository$addCatalogPack$1> dVar) {
        super(dVar);
        this.this$0 = catalogRepository;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM6114addCatalogPack0E7RQCE = this.this$0.m6114addCatalogPack0E7RQCE(null, null, this);
        return objM6114addCatalogPack0E7RQCE == e7.a.f15033i ? objM6114addCatalogPack0E7RQCE : new x6.d0(objM6114addCatalogPack0E7RQCE);
    }
}
