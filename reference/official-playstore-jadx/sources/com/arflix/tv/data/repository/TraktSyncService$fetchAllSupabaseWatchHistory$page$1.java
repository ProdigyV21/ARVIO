package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.SupabaseApi;
import com.arflix.tv.data.api.WatchHistoryRecord;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lcom/arflix/tv/data/api/WatchHistoryRecord;", "auth", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.TraktSyncService$fetchAllSupabaseWatchHistory$page$1", f = "TraktSyncService.kt", l = {1742}, m = "invokeSuspend", v = 2)
public final class TraktSyncService$fetchAllSupabaseWatchHistory$page$1 extends f7.j implements r7.p<String, d7.d<? super List<? extends WatchHistoryRecord>>, Object> {
    final /* synthetic */ int $limit;
    final /* synthetic */ kotlin.jvm.internal.i0 $offset;
    final /* synthetic */ String $source;
    final /* synthetic */ String $userId;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ TraktSyncService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TraktSyncService$fetchAllSupabaseWatchHistory$page$1(TraktSyncService traktSyncService, String str, String str2, int i10, kotlin.jvm.internal.i0 i0Var, d7.d<? super TraktSyncService$fetchAllSupabaseWatchHistory$page$1> dVar) {
        super(2, dVar);
        this.this$0 = traktSyncService;
        this.$userId = str;
        this.$source = str2;
        this.$limit = i10;
        this.$offset = i0Var;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        TraktSyncService$fetchAllSupabaseWatchHistory$page$1 traktSyncService$fetchAllSupabaseWatchHistory$page$1 = new TraktSyncService$fetchAllSupabaseWatchHistory$page$1(this.this$0, this.$userId, this.$source, this.$limit, this.$offset, dVar);
        traktSyncService$fetchAllSupabaseWatchHistory$page$1.L$0 = obj;
        return traktSyncService$fetchAllSupabaseWatchHistory$page$1;
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
        String str2 = this.$source;
        int i11 = this.$limit;
        Integer num = new Integer(this.$offset.f19744i);
        this.L$0 = null;
        this.label = 1;
        Object objL = com.arflix.tv.data.api.f.l(supabaseApi, str, null, strC, null, str2, null, null, "updated_at.desc", i11, num, this, 106, null);
        e7.a aVar = e7.a.f15033i;
        return objL == aVar ? aVar : objL;
    }

    @Override // r7.p
    public final Object invoke(String str, d7.d<? super List<WatchHistoryRecord>> dVar) {
        return ((TraktSyncService$fetchAllSupabaseWatchHistory$page$1) create(str, dVar)).invokeSuspend(t0.f22605a);
    }
}
