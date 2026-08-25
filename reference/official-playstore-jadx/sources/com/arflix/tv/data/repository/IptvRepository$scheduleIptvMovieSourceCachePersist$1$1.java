package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.IptvRepository;
import ka.s0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.IptvRepository$scheduleIptvMovieSourceCachePersist$1$1", f = "IptvRepository.kt", l = {8584}, m = "invokeSuspend", v = 2)
public final class IptvRepository$scheduleIptvMovieSourceCachePersist$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ IptvRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IptvRepository$scheduleIptvMovieSourceCachePersist$1$1(IptvRepository iptvRepository, d7.d<? super IptvRepository$scheduleIptvMovieSourceCachePersist$1$1> dVar) {
        super(2, dVar);
        this.this$0 = iptvRepository;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        IptvRepository$scheduleIptvMovieSourceCachePersist$1$1 iptvRepository$scheduleIptvMovieSourceCachePersist$1$1 = new IptvRepository$scheduleIptvMovieSourceCachePersist$1$1(this.this$0, dVar);
        iptvRepository$scheduleIptvMovieSourceCachePersist$1$1.L$0 = obj;
        return iptvRepository$scheduleIptvMovieSourceCachePersist$1$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        IptvRepository.PersistedMovieSourceCache persistedMovieSourceCache;
        ka.k0 k0Var = (ka.k0) this.L$0;
        e7.a aVar = e7.a.f15033i;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            this.L$0 = k0Var;
            this.label = 1;
            if (s0.a(2000L, this) == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        Object obj2 = this.this$0.iptvMovieSourceLock;
        IptvRepository iptvRepository = this.this$0;
        synchronized (obj2) {
            persistedMovieSourceCache = new IptvRepository.PersistedMovieSourceCache(kotlin.collections.h0.B0(iptvRepository.iptvMovieSourceMemory));
        }
        IptvRepository iptvRepository2 = this.this$0;
        try {
            iptvRepository2.getIptvMovieSourcePrefs().edit().putString(iptvRepository2.iptvMovieSourcePrefsKey, iptvRepository2.gson.toJson(persistedMovieSourceCache)).apply();
        } catch (Throwable unused) {
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((IptvRepository$scheduleIptvMovieSourceCachePersist$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
