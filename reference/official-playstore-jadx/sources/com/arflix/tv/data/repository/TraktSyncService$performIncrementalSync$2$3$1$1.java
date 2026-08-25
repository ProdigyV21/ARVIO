package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.SupabaseApi;
import com.arflix.tv.data.api.WatchHistoryRecord;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "auth", "Lx6/t0;", "<anonymous>", "(Ljava/lang/String;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.TraktSyncService$performIncrementalSync$2$3$1$1", f = "TraktSyncService.kt", l = {461}, m = "invokeSuspend", v = 2)
public final class TraktSyncService$performIncrementalSync$2$3$1$1 extends f7.j implements r7.p<String, d7.d<? super t0>, Object> {
    final /* synthetic */ WatchHistoryRecord $record;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ TraktSyncService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TraktSyncService$performIncrementalSync$2$3$1$1(TraktSyncService traktSyncService, WatchHistoryRecord watchHistoryRecord, d7.d<? super TraktSyncService$performIncrementalSync$2$3$1$1> dVar) {
        super(2, dVar);
        this.this$0 = traktSyncService;
        this.$record = watchHistoryRecord;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        TraktSyncService$performIncrementalSync$2$3$1$1 traktSyncService$performIncrementalSync$2$3$1$1 = new TraktSyncService$performIncrementalSync$2$3$1$1(this.this$0, this.$record, dVar);
        traktSyncService$performIncrementalSync$2$3$1$1.L$0 = obj;
        return traktSyncService$performIncrementalSync$2$3$1$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        String str = (String) this.L$0;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            SupabaseApi supabaseApi = this.this$0.supabaseApi;
            WatchHistoryRecord watchHistoryRecord = this.$record;
            this.L$0 = null;
            this.label = 1;
            Object objX = com.arflix.tv.data.api.f.x(supabaseApi, str, null, null, watchHistoryRecord, this, 6, null);
            e7.a aVar = e7.a.f15033i;
            if (objX == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(String str, d7.d<? super t0> dVar) {
        return ((TraktSyncService$performIncrementalSync$2$3$1$1) create(str, dVar)).invokeSuspend(t0.f22605a);
    }
}
