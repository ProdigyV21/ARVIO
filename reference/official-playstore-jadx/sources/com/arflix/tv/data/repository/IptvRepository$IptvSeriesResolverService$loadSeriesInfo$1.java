package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.IptvRepository;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.IptvRepository$IptvSeriesResolverService", f = "IptvRepository.kt", l = {3935}, m = "loadSeriesInfo", v = 2)
public final class IptvRepository$IptvSeriesResolverService$loadSeriesInfo$1 extends f7.c {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ IptvRepository.IptvSeriesResolverService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IptvRepository$IptvSeriesResolverService$loadSeriesInfo$1(IptvRepository.IptvSeriesResolverService iptvSeriesResolverService, d7.d<? super IptvRepository$IptvSeriesResolverService$loadSeriesInfo$1> dVar) {
        super(dVar);
        this.this$0 = iptvSeriesResolverService;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.loadSeriesInfo(null, null, 0, false, this);
    }
}
