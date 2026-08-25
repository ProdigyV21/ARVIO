package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.StreamRepository;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.StreamRepository$fetchEpisodeStreamsFromAddon$2", f = "StreamRepository.kt", l = {1977}, m = "invokeSuspend$requestEpisodeId", v = 2)
public final class StreamRepository$fetchEpisodeStreamsFromAddon$2$requestEpisodeId$1 extends f7.c {
    long J$0;
    long J$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    boolean Z$0;
    boolean Z$1;
    boolean Z$2;
    int label;
    /* synthetic */ Object result;

    public StreamRepository$fetchEpisodeStreamsFromAddon$2$requestEpisodeId$1(d7.d<? super StreamRepository$fetchEpisodeStreamsFromAddon$2$requestEpisodeId$1> dVar) {
        super(dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return StreamRepository.C09792.invokeSuspend$requestEpisodeId(false, null, false, null, 0L, null, null, null, null, false, this);
    }
}
