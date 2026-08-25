package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {3569, 3578, 3590, 3598, 3609}, m = "loadTraktCatalogRefs$loadFromParsed", v = 2)
public final class MediaRepository$loadTraktCatalogRefs$loadFromParsed$1 extends f7.c {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;

    public MediaRepository$loadTraktCatalogRefs$loadFromParsed$1(d7.d<? super MediaRepository$loadTraktCatalogRefs$loadFromParsed$1> dVar) {
        super(dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return MediaRepository.loadTraktCatalogRefs$loadFromParsed(null, null, this);
    }
}
