package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.TraktApi;
import com.arflix.tv.data.api.TraktHistoryItem;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lcom/arflix/tv/data/api/TraktHistoryItem;", "auth", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.TraktSyncService$fetchAllHistoryMovies$pageItems$1", f = "TraktSyncService.kt", l = {1271}, m = "invokeSuspend", v = 2)
public final class TraktSyncService$fetchAllHistoryMovies$pageItems$1 extends f7.j implements r7.p<String, d7.d<? super List<? extends TraktHistoryItem>>, Object> {
    final /* synthetic */ int $limit;
    final /* synthetic */ kotlin.jvm.internal.i0 $page;
    final /* synthetic */ String $startAt;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ TraktSyncService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TraktSyncService$fetchAllHistoryMovies$pageItems$1(TraktSyncService traktSyncService, kotlin.jvm.internal.i0 i0Var, int i10, String str, d7.d<? super TraktSyncService$fetchAllHistoryMovies$pageItems$1> dVar) {
        super(2, dVar);
        this.this$0 = traktSyncService;
        this.$page = i0Var;
        this.$limit = i10;
        this.$startAt = str;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        TraktSyncService$fetchAllHistoryMovies$pageItems$1 traktSyncService$fetchAllHistoryMovies$pageItems$1 = new TraktSyncService$fetchAllHistoryMovies$pageItems$1(this.this$0, this.$page, this.$limit, this.$startAt, dVar);
        traktSyncService$fetchAllHistoryMovies$pageItems$1.L$0 = obj;
        return traktSyncService$fetchAllHistoryMovies$pageItems$1;
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
        int i11 = this.$page.f19744i;
        int i12 = this.$limit;
        String str3 = this.$startAt;
        this.L$0 = null;
        this.label = 1;
        Object historyMovies = traktApi.getHistoryMovies(str, str2, "2", i11, i12, str3, this);
        e7.a aVar = e7.a.f15033i;
        return historyMovies == aVar ? aVar : historyMovies;
    }

    @Override // r7.p
    public final Object invoke(String str, d7.d<? super List<TraktHistoryItem>> dVar) {
        return ((TraktSyncService$fetchAllHistoryMovies$pageItems$1) create(str, dVar)).invokeSuspend(t0.f22605a);
    }
}
