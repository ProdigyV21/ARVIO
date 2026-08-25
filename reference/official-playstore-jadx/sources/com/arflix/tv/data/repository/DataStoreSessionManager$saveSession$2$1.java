package com.arflix.tv.data.repository;

import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.DataStoreSessionManager$saveSession$2$1", f = "DataStoreSessionManager.kt", l = {}, m = "invokeSuspend", v = 2)
public final class DataStoreSessionManager$saveSession$2$1 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
    final /* synthetic */ String $payload;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DataStoreSessionManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DataStoreSessionManager$saveSession$2$1(DataStoreSessionManager dataStoreSessionManager, String str, d7.d<? super DataStoreSessionManager$saveSession$2$1> dVar) {
        super(2, dVar);
        this.this$0 = dataStoreSessionManager;
        this.$payload = str;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        DataStoreSessionManager$saveSession$2$1 dataStoreSessionManager$saveSession$2$1 = new DataStoreSessionManager$saveSession$2$1(this.this$0, this.$payload, dVar);
        dataStoreSessionManager$saveSession$2$1.L$0 = obj;
        return dataStoreSessionManager$saveSession$2$1;
    }

    @Override // r7.p
    public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
        return ((DataStoreSessionManager$saveSession$2$1) create(bVar, dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        c1.b bVar = (c1.b) this.L$0;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        bVar.d(this.this$0.sessionKey, this.$payload);
        return t0.f22605a;
    }
}
