package com.arflix.tv.data.repository;

import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lx6/d0;", "Lcom/arflix/tv/data/repository/HomeServerConnection;", "<anonymous>", "(Lka/k0;)Lx6/d0;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$connectPlexAccount$2", f = "HomeServerRepository.kt", l = {396}, m = "invokeSuspend", v = 2)
public final class HomeServerRepository$connectPlexAccount$2 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.d0>, Object> {
    final /* synthetic */ String $accountToken;
    final /* synthetic */ String $displayName;
    final /* synthetic */ String $preferredServerUrl;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ HomeServerRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeServerRepository$connectPlexAccount$2(HomeServerRepository homeServerRepository, String str, String str2, String str3, d7.d<? super HomeServerRepository$connectPlexAccount$2> dVar) {
        super(2, dVar);
        this.this$0 = homeServerRepository;
        this.$accountToken = str;
        this.$preferredServerUrl = str2;
        this.$displayName = str3;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        HomeServerRepository$connectPlexAccount$2 homeServerRepository$connectPlexAccount$2 = new HomeServerRepository$connectPlexAccount$2(this.this$0, this.$accountToken, this.$preferredServerUrl, this.$displayName, dVar);
        homeServerRepository$connectPlexAccount$2.L$0 = obj;
        return homeServerRepository$connectPlexAccount$2;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Object c0Var;
        int i10 = this.label;
        try {
            if (i10 == 0) {
                k2.c.G(obj);
                HomeServerRepository homeServerRepository = this.this$0;
                HomeServerConnection homeServerConnectionBuildPlexConnection = homeServerRepository.buildPlexConnection(this.$accountToken, this.$preferredServerUrl, "", null, kotlin.text.o.L0(this.$displayName).toString());
                this.L$0 = null;
                this.L$1 = null;
                this.L$2 = homeServerConnectionBuildPlexConnection;
                this.I$0 = 0;
                this.label = 1;
                Object objSaveConnection = homeServerRepository.saveConnection(homeServerConnectionBuildPlexConnection, this);
                e7.a aVar = e7.a.f15033i;
                c0Var = homeServerConnectionBuildPlexConnection;
                if (objSaveConnection == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                Object obj2 = (HomeServerConnection) this.L$2;
                k2.c.G(obj);
                c0Var = obj2;
            }
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        return new x6.d0(c0Var);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.d0> dVar) {
        return ((HomeServerRepository$connectPlexAccount$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
