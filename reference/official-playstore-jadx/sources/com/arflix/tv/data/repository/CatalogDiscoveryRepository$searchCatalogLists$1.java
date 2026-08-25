package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.CatalogDiscoveryRepository", f = "CatalogDiscoveryRepository.kt", l = {25}, m = "searchCatalogLists-gIAlu-s", v = 2)
public final class CatalogDiscoveryRepository$searchCatalogLists$1 extends f7.c {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CatalogDiscoveryRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CatalogDiscoveryRepository$searchCatalogLists$1(CatalogDiscoveryRepository catalogDiscoveryRepository, d7.d<? super CatalogDiscoveryRepository$searchCatalogLists$1> dVar) {
        super(dVar);
        this.this$0 = catalogDiscoveryRepository;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM6112searchCatalogListsgIAlus = this.this$0.m6112searchCatalogListsgIAlus(null, this);
        return objM6112searchCatalogListsgIAlus == e7.a.f15033i ? objM6112searchCatalogListsgIAlus : new x6.d0(objM6112searchCatalogListsgIAlus);
    }
}
