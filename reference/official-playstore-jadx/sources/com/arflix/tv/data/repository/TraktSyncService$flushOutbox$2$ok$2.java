package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.TraktApi;
import com.arflix.tv.data.api.TraktHistoryBody;
import com.arflix.tv.data.api.TraktIds;
import com.arflix.tv.data.api.TraktMovieId;
import com.arflix.tv.data.api.TraktSyncResponse;
import java.util.Collections;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lcom/arflix/tv/data/api/TraktSyncResponse;", "auth", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.TraktSyncService$flushOutbox$2$ok$2", f = "TraktSyncService.kt", l = {1789}, m = "invokeSuspend", v = 2)
public final class TraktSyncService$flushOutbox$2$ok$2 extends f7.j implements r7.p<String, d7.d<? super TraktSyncResponse>, Object> {
    final /* synthetic */ Integer $tmdbId;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ TraktSyncService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TraktSyncService$flushOutbox$2$ok$2(TraktSyncService traktSyncService, Integer num, d7.d<? super TraktSyncService$flushOutbox$2$ok$2> dVar) {
        super(2, dVar);
        this.this$0 = traktSyncService;
        this.$tmdbId = num;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        TraktSyncService$flushOutbox$2$ok$2 traktSyncService$flushOutbox$2$ok$2 = new TraktSyncService$flushOutbox$2$ok$2(this.this$0, this.$tmdbId, dVar);
        traktSyncService$flushOutbox$2$ok$2.L$0 = obj;
        return traktSyncService$flushOutbox$2$ok$2;
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
        TraktHistoryBody traktHistoryBody = new TraktHistoryBody(Collections.singletonList(new TraktMovieId(new TraktIds(null, this.$tmdbId, null, null, null, 29, null))), null, null, 6, null);
        this.L$0 = null;
        this.label = 1;
        Object objAddToHistory = traktApi.addToHistory(str, str2, "2", traktHistoryBody, this);
        e7.a aVar = e7.a.f15033i;
        return objAddToHistory == aVar ? aVar : objAddToHistory;
    }

    @Override // r7.p
    public final Object invoke(String str, d7.d<? super TraktSyncResponse> dVar) {
        return ((TraktSyncService$flushOutbox$2$ok$2) create(str, dVar)).invokeSuspend(t0.f22605a);
    }
}
