package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {825, 829, 843}, m = "addCustomCatalog-gIAlu-s", v = 2)
public final class CatalogRepository$addCustomCatalog$1 extends f7.c {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CatalogRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CatalogRepository$addCustomCatalog$1(CatalogRepository catalogRepository, d7.d<? super CatalogRepository$addCustomCatalog$1> dVar) {
        super(dVar);
        this.this$0 = catalogRepository;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM6115addCustomCataloggIAlus = this.this$0.m6115addCustomCataloggIAlus(null, this);
        return objM6115addCustomCataloggIAlus == e7.a.f15033i ? objM6115addCustomCataloggIAlus : new x6.d0(objM6115addCustomCataloggIAlus);
    }
}
