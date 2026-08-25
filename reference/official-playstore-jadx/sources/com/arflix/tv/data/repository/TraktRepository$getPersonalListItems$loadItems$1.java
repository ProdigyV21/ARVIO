package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.TraktRepository", f = "TraktRepository.kt", l = {2901}, m = "getPersonalListItems$loadItems", v = 2)
public final class TraktRepository$getPersonalListItems$loadItems$1 extends f7.c {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;

    public TraktRepository$getPersonalListItems$loadItems$1(d7.d<? super TraktRepository$getPersonalListItems$loadItems$1> dVar) {
        super(dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return TraktRepository.getPersonalListItems$loadItems(null, null, null, this);
    }
}
