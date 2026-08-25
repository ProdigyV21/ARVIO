package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.TraktApi;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "auth", "Lx6/t0;", "<anonymous>", "(Ljava/lang/String;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.TraktSyncService$flushOutbox$2$ok$4", f = "TraktSyncService.kt", l = {1831}, m = "invokeSuspend", v = 2)
public final class TraktSyncService$flushOutbox$2$ok$4 extends f7.j implements r7.p<String, d7.d<? super t0>, Object> {
    final /* synthetic */ Long $playbackId;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ TraktSyncService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TraktSyncService$flushOutbox$2$ok$4(TraktSyncService traktSyncService, Long l10, d7.d<? super TraktSyncService$flushOutbox$2$ok$4> dVar) {
        super(2, dVar);
        this.this$0 = traktSyncService;
        this.$playbackId = l10;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        TraktSyncService$flushOutbox$2$ok$4 traktSyncService$flushOutbox$2$ok$4 = new TraktSyncService$flushOutbox$2$ok$4(this.this$0, this.$playbackId, dVar);
        traktSyncService$flushOutbox$2$ok$4.L$0 = obj;
        return traktSyncService$flushOutbox$2$ok$4;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        String str = (String) this.L$0;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            TraktApi traktApi = this.this$0.traktApi;
            String str2 = this.this$0.clientId;
            long jLongValue = this.$playbackId.longValue();
            this.L$0 = null;
            this.label = 1;
            Object objRemovePlaybackItem = traktApi.removePlaybackItem(str, str2, "2", jLongValue, this);
            e7.a aVar = e7.a.f15033i;
            if (objRemovePlaybackItem == aVar) {
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
        return ((TraktSyncService$flushOutbox$2$ok$4) create(str, dVar)).invokeSuspend(t0.f22605a);
    }
}
