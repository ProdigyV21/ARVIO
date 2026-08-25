package com.arflix.tv.data.repository;

import com.arflix.tv.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "Lx6/d0;", "", "Lcom/arflix/tv/data/repository/HomeServerConnection;", "<anonymous>", "(Lka/k0;)Lx6/d0;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$testConnections$2", f = "HomeServerRepository.kt", l = {407, 410}, m = "invokeSuspend", v = 2)
public final class HomeServerRepository$testConnections$2 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.d0>, Object> {
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ HomeServerRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeServerRepository$testConnections$2(HomeServerRepository homeServerRepository, d7.d<? super HomeServerRepository$testConnections$2> dVar) {
        super(2, dVar);
        this.this$0 = homeServerRepository;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        HomeServerRepository$testConnections$2 homeServerRepository$testConnections$2 = new HomeServerRepository$testConnections$2(this.this$0, dVar);
        homeServerRepository$testConnections$2.L$0 = obj;
        return homeServerRepository$testConnections$2;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Object c0Var;
        int i10;
        HomeServerRepository homeServerRepository;
        int i11 = this.label;
        e7.a aVar = e7.a.f15033i;
        try {
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        if (i11 == 0) {
            k2.c.G(obj);
            HomeServerRepository homeServerRepository2 = this.this$0;
            this.L$0 = null;
            this.L$1 = homeServerRepository2;
            this.L$2 = null;
            i10 = 0;
            this.I$0 = 0;
            this.label = 1;
            Object objCurrentConnections = homeServerRepository2.currentConnections(this);
            if (objCurrentConnections == aVar) {
                return aVar;
            }
            homeServerRepository = homeServerRepository2;
            obj = objCurrentConnections;
        } else {
            if (i11 != 1) {
                if (i11 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c0Var = (List) this.L$3;
                k2.c.G(obj);
                return new x6.d0(c0Var);
            }
            i10 = this.I$0;
            homeServerRepository = (HomeServerRepository) this.L$1;
            k2.c.G(obj);
        }
        List list = (List) obj;
        if (list.isEmpty()) {
            throw new IllegalArgumentException(homeServerRepository.context.getString(R.string.homeserver_none_connected).toString());
        }
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(homeServerRepository.refreshConnection((HomeServerConnection) it.next()));
        }
        this.L$0 = null;
        this.L$1 = null;
        this.L$2 = null;
        this.L$3 = arrayList;
        this.I$0 = i10;
        this.label = 2;
        if (homeServerRepository.saveConnections(arrayList, this) != aVar) {
            c0Var = arrayList;
            return new x6.d0(c0Var);
        }
        return aVar;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.d0> dVar) {
        return ((HomeServerRepository$testConnections$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
