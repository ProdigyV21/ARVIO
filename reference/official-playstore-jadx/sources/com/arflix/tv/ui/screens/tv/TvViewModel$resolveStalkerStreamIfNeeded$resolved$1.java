package com.arflix.tv.ui.screens.tv;

import com.arflix.tv.data.api.StalkerApi;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/String;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$resolveStalkerStreamIfNeeded$resolved$1", f = "TvViewModel.kt", l = {1978}, m = "invokeSuspend", v = 2)
public final class TvViewModel$resolveStalkerStreamIfNeeded$resolved$1 extends f7.j implements r7.p<ka.k0, d7.d<? super String>, Object> {
    final /* synthetic */ String $trimmed;
    int label;
    final /* synthetic */ TvViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvViewModel$resolveStalkerStreamIfNeeded$resolved$1(TvViewModel tvViewModel, String str, d7.d<? super TvViewModel$resolveStalkerStreamIfNeeded$resolved$1> dVar) {
        super(2, dVar);
        this.this$0 = tvViewModel;
        this.$trimmed = str;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TvViewModel$resolveStalkerStreamIfNeeded$resolved$1(this.this$0, this.$trimmed, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            StalkerApi cachedStalkerApi = this.this$0.getIptvRepository().getCachedStalkerApi();
            if (cachedStalkerApi == null) {
                return null;
            }
            String str = this.$trimmed;
            this.label = 1;
            obj = cachedStalkerApi.resolveStreamUrl(str, this);
            e7.a aVar = e7.a.f15033i;
            if (obj == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return (String) obj;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super String> dVar) {
        return ((TvViewModel$resolveStalkerStreamIfNeeded$resolved$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
