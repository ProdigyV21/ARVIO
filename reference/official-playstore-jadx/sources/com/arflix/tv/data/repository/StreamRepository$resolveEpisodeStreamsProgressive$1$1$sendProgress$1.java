package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.StreamRepository;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.StreamRepository$resolveEpisodeStreamsProgressive$1$1", f = "StreamRepository.kt", l = {2769}, m = "invokeSuspend$sendProgress", v = 2)
public final class StreamRepository$resolveEpisodeStreamsProgressive$1$1$sendProgress$1 extends f7.c {
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;

    public StreamRepository$resolveEpisodeStreamsProgressive$1$1$sendProgress$1(d7.d<? super StreamRepository$resolveEpisodeStreamsProgressive$1$1$sendProgress$1> dVar) {
        super(dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return StreamRepository.C10191.C01331.invokeSuspend$sendProgress(null, null, null, 0, 0, 0, null, null, this);
    }
}
