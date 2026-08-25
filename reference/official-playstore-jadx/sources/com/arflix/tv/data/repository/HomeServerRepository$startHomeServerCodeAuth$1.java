package com.arflix.tv.data.repository;

import com.google.android.gms.cast.MediaError;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository", f = "HomeServerRepository.kt", l = {MediaError.DetailedErrorCode.DASH_INVALID_SEGMENT_INFO}, m = "startHomeServerCodeAuth-gIAlu-s", v = 2)
public final class HomeServerRepository$startHomeServerCodeAuth$1 extends f7.c {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HomeServerRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeServerRepository$startHomeServerCodeAuth$1(HomeServerRepository homeServerRepository, d7.d<? super HomeServerRepository$startHomeServerCodeAuth$1> dVar) {
        super(dVar);
        this.this$0 = homeServerRepository;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM6136startHomeServerCodeAuthgIAlus = this.this$0.m6136startHomeServerCodeAuthgIAlus(null, this);
        return objM6136startHomeServerCodeAuthgIAlus == e7.a.f15033i ? objM6136startHomeServerCodeAuthgIAlus : new x6.d0(objM6136startHomeServerCodeAuthgIAlus);
    }
}
