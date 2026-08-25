package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.StreamRepository", f = "StreamRepository.kt", l = {803}, m = "addCustomAddon-0E7RQCE", v = 2)
public final class StreamRepository$addCustomAddon$1 extends f7.c {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ StreamRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StreamRepository$addCustomAddon$1(StreamRepository streamRepository, d7.d<? super StreamRepository$addCustomAddon$1> dVar) {
        super(dVar);
        this.this$0 = streamRepository;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM6145addCustomAddon0E7RQCE = this.this$0.m6145addCustomAddon0E7RQCE(null, null, this);
        return objM6145addCustomAddon0E7RQCE == e7.a.f15033i ? objM6145addCustomAddon0E7RQCE : new x6.d0(objM6145addCustomAddon0E7RQCE);
    }
}
