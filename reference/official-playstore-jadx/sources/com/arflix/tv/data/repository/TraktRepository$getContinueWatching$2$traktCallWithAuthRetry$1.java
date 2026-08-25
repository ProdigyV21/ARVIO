package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.TraktRepository;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.TraktRepository$getContinueWatching$2", f = "TraktRepository.kt", l = {1377, 1384, 1386, 1388}, m = "invokeSuspend$traktCallWithAuthRetry", v = 2)
public final class TraktRepository$getContinueWatching$2$traktCallWithAuthRetry$1<T> extends f7.c {
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    /* synthetic */ Object result;

    public TraktRepository$getContinueWatching$2$traktCallWithAuthRetry$1(d7.d<? super TraktRepository$getContinueWatching$2$traktCallWithAuthRetry$1> dVar) {
        super(dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return TraktRepository.C10782.invokeSuspend$traktCallWithAuthRetry(null, null, null, null, this);
    }
}
