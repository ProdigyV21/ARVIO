package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {807, 813}, m = "removeCatalogPack-gIAlu-s", v = 2)
public final class CatalogRepository$removeCatalogPack$1 extends f7.c {
    int I$0;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CatalogRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CatalogRepository$removeCatalogPack$1(CatalogRepository catalogRepository, d7.d<? super CatalogRepository$removeCatalogPack$1> dVar) {
        super(dVar);
        this.this$0 = catalogRepository;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM6117removeCatalogPackgIAlus = this.this$0.m6117removeCatalogPackgIAlus(null, this);
        return objM6117removeCatalogPackgIAlus == e7.a.f15033i ? objM6117removeCatalogPackgIAlus : new x6.d0(objM6117removeCatalogPackgIAlus);
    }
}
