package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.StreamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1", f = "StreamRepository.kt", l = {3040}, m = "invokeSuspend$attempt", v = 2)
public final class StreamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1$attempt$1 extends f7.c {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    int label;
    /* synthetic */ Object result;

    public StreamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1$attempt$1(d7.d<? super StreamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1$attempt$1> dVar) {
        super(dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return StreamRepository$fetchSubtitlesForSelectedStream$2$jobs$1$1.invokeSuspend$attempt(null, null, null, null, null, null, null, this);
    }
}
