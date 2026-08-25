package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.MediaRepository;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.MediaRepository$getHomeCategoriesInternal$2", f = "MediaRepository.kt", l = {1628, 1631}, m = "invokeSuspend$fetchUpTo40", v = 2)
public final class MediaRepository$getHomeCategoriesInternal$2$fetchUpTo40$1 extends f7.c {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;

    public MediaRepository$getHomeCategoriesInternal$2$fetchUpTo40$1(d7.d<? super MediaRepository$getHomeCategoriesInternal$2$fetchUpTo40$1> dVar) {
        super(dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return MediaRepository.C08892.invokeSuspend$fetchUpTo40(null, null, this);
    }
}
