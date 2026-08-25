package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.TraktApi;
import com.arflix.tv.data.api.TraktShowProgress;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lcom/arflix/tv/data/api/TraktShowProgress;", "auth", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.TraktSyncService$buildWatchedEpisodesFromShowProgress$2$tasks$1$1$1$progress$1", f = "TraktSyncService.kt", l = {1549}, m = "invokeSuspend", v = 2)
public final class TraktSyncService$buildWatchedEpisodesFromShowProgress$2$tasks$1$1$1$progress$1 extends f7.j implements r7.p<String, d7.d<? super TraktShowProgress>, Object> {
    final /* synthetic */ Integer $showTraktId;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ TraktSyncService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TraktSyncService$buildWatchedEpisodesFromShowProgress$2$tasks$1$1$1$progress$1(TraktSyncService traktSyncService, Integer num, d7.d<? super TraktSyncService$buildWatchedEpisodesFromShowProgress$2$tasks$1$1$1$progress$1> dVar) {
        super(2, dVar);
        this.this$0 = traktSyncService;
        this.$showTraktId = num;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        TraktSyncService$buildWatchedEpisodesFromShowProgress$2$tasks$1$1$1$progress$1 traktSyncService$buildWatchedEpisodesFromShowProgress$2$tasks$1$1$1$progress$1 = new TraktSyncService$buildWatchedEpisodesFromShowProgress$2$tasks$1$1$1$progress$1(this.this$0, this.$showTraktId, dVar);
        traktSyncService$buildWatchedEpisodesFromShowProgress$2$tasks$1$1$1$progress$1.L$0 = obj;
        return traktSyncService$buildWatchedEpisodesFromShowProgress$2$tasks$1$1$1$progress$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        String str = (String) this.L$0;
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return obj;
        }
        k2.c.G(obj);
        TraktApi traktApi = this.this$0.traktApi;
        String str2 = this.this$0.clientId;
        String strValueOf = String.valueOf(this.$showTraktId.intValue());
        this.L$0 = null;
        this.label = 1;
        Object objY = com.arflix.tv.data.api.h.y(traktApi, str, str2, "2", strValueOf, null, "false", "false", this, 16, null);
        e7.a aVar = e7.a.f15033i;
        return objY == aVar ? aVar : objY;
    }

    @Override // r7.p
    public final Object invoke(String str, d7.d<? super TraktShowProgress> dVar) {
        return ((TraktSyncService$buildWatchedEpisodesFromShowProgress$2$tasks$1$1$1$progress$1) create(str, dVar)).invokeSuspend(t0.f22605a);
    }
}
