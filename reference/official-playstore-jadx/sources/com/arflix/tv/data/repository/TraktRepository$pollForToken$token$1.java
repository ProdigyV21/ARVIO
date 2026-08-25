package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.TokenPollRequest;
import com.arflix.tv.data.api.TraktApi;
import com.arflix.tv.data.api.TraktToken;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lcom/arflix/tv/data/api/TraktToken;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.TraktRepository$pollForToken$token$1", f = "TraktRepository.kt", l = {193}, m = "invokeSuspend", v = 2)
public final class TraktRepository$pollForToken$token$1 extends f7.j implements r7.l<d7.d<? super TraktToken>, Object> {
    final /* synthetic */ String $deviceCode;
    int label;
    final /* synthetic */ TraktRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TraktRepository$pollForToken$token$1(TraktRepository traktRepository, String str, d7.d<? super TraktRepository$pollForToken$token$1> dVar) {
        super(1, dVar);
        this.this$0 = traktRepository;
        this.$deviceCode = str;
    }

    @Override // f7.a
    public final d7.d<t0> create(d7.d<?> dVar) {
        return new TraktRepository$pollForToken$token$1(this.this$0, this.$deviceCode, dVar);
    }

    @Override // r7.l
    public final Object invoke(d7.d<? super TraktToken> dVar) {
        return ((TraktRepository$pollForToken$token$1) create(dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
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
        TokenPollRequest tokenPollRequest = new TokenPollRequest(this.$deviceCode, this.this$0.clientId, this.this$0.clientSecret);
        this.label = 1;
        Object objPollToken = traktApi.pollToken(tokenPollRequest, this);
        e7.a aVar = e7.a.f15033i;
        return objPollToken == aVar ? aVar : objPollToken;
    }
}
