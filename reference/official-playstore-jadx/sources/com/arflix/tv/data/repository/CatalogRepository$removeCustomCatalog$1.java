package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {881, 884, 886, 891, 893, 896}, m = "removeCustomCatalog-gIAlu-s", v = 2)
public final class CatalogRepository$removeCustomCatalog$1 extends f7.c {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CatalogRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CatalogRepository$removeCustomCatalog$1(CatalogRepository catalogRepository, d7.d<? super CatalogRepository$removeCustomCatalog$1> dVar) {
        super(dVar);
        this.this$0 = catalogRepository;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM6118removeCustomCataloggIAlus = this.this$0.m6118removeCustomCataloggIAlus(null, this);
        return objM6118removeCustomCataloggIAlus == e7.a.f15033i ? objM6118removeCustomCataloggIAlus : new x6.d0(objM6118removeCustomCataloggIAlus);
    }
}
