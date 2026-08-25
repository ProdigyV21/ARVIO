package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.SupabaseApi;
import com.arflix.tv.data.api.WatchHistoryRecord;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lcom/arflix/tv/data/api/WatchHistoryRecord;", "auth", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.TraktSyncService$getInProgressItems$2$records$1", f = "TraktSyncService.kt", l = {1124}, m = "invokeSuspend", v = 2)
public final class TraktSyncService$getInProgressItems$2$records$1 extends f7.j implements r7.p<String, d7.d<? super List<? extends WatchHistoryRecord>>, Object> {
    final /* synthetic */ String $userId;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ TraktSyncService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TraktSyncService$getInProgressItems$2$records$1(TraktSyncService traktSyncService, String str, d7.d<? super TraktSyncService$getInProgressItems$2$records$1> dVar) {
        super(2, dVar);
        this.this$0 = traktSyncService;
        this.$userId = str;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        TraktSyncService$getInProgressItems$2$records$1 traktSyncService$getInProgressItems$2$records$1 = new TraktSyncService$getInProgressItems$2$records$1(this.this$0, this.$userId, dVar);
        traktSyncService$getInProgressItems$2$records$1.L$0 = obj;
        return traktSyncService$getInProgressItems$2$records$1;
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
        SupabaseApi supabaseApi = this.this$0.supabaseApi;
        String strC = androidx.compose.material3.d.C("eq.", this.$userId);
        String strC2 = androidx.compose.material3.d.C("eq.", this.this$0.activeProfileId());
        this.L$0 = null;
        this.label = 1;
        Object objL = com.arflix.tv.data.api.f.l(supabaseApi, str, null, strC, strC2, null, null, null, "updated_at.desc", 500, null, this, 626, null);
        e7.a aVar = e7.a.f15033i;
        return objL == aVar ? aVar : objL;
    }

    @Override // r7.p
    public final Object invoke(String str, d7.d<? super List<WatchHistoryRecord>> dVar) {
        return ((TraktSyncService$getInProgressItems$2$records$1) create(str, dVar)).invokeSuspend(t0.f22605a);
    }
}
