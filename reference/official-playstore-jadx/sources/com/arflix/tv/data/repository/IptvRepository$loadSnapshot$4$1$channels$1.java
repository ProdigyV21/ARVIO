package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.IptvChannel;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/IptvChannel;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.IptvRepository$loadSnapshot$4$1$channels$1", f = "IptvRepository.kt", l = {1687}, m = "invokeSuspend", v = 2)
public final class IptvRepository$loadSnapshot$4$1$channels$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends IptvChannel>>, Object> {
    final /* synthetic */ List<IptvPlaylistEntry> $activePlaylists;
    final /* synthetic */ r7.p<List<IptvChannel>, d7.d<? super t0>, Object> $onChannelsReady;
    final /* synthetic */ r7.l<IptvLoadProgress, t0> $onProgress;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ IptvRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public IptvRepository$loadSnapshot$4$1$channels$1(List<IptvPlaylistEntry> list, IptvRepository iptvRepository, r7.l<? super IptvLoadProgress, t0> lVar, r7.p<? super List<IptvChannel>, ? super d7.d<? super t0>, ? extends Object> pVar, d7.d<? super IptvRepository$loadSnapshot$4$1$channels$1> dVar) {
        super(2, dVar);
        this.$activePlaylists = list;
        this.this$0 = iptvRepository;
        this.$onProgress = lVar;
        this.$onChannelsReady = pVar;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        IptvRepository$loadSnapshot$4$1$channels$1 iptvRepository$loadSnapshot$4$1$channels$1 = new IptvRepository$loadSnapshot$4$1$channels$1(this.$activePlaylists, this.this$0, this.$onProgress, this.$onChannelsReady, dVar);
        iptvRepository$loadSnapshot$4$1$channels$1.L$0 = obj;
        return iptvRepository$loadSnapshot$4$1$channels$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Object obj2;
        List[] listArr;
        ArrayList arrayList;
        ka.k0 k0Var = (ka.k0) this.L$0;
        e7.a aVar = e7.a.f15033i;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            int size = this.$activePlaylists.size();
            List[] listArr2 = new List[size];
            for (int i11 = 0; i11 < size; i11++) {
                listArr2[i11] = null;
            }
            obj2 = new Object();
            List<IptvPlaylistEntry> list = this.$activePlaylists;
            IptvRepository iptvRepository = this.this$0;
            r7.l<IptvLoadProgress, t0> lVar = this.$onProgress;
            r7.p<List<IptvChannel>, d7.d<? super t0>, Object> pVar = this.$onChannelsReady;
            ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(list, 10));
            int i12 = 0;
            for (Object obj3 : list) {
                int i13 = i12 + 1;
                if (i12 < 0) {
                    t7.a.Q();
                    throw null;
                }
                ArrayList arrayList3 = arrayList2;
                arrayList3.add(ka.m0.e(3, null, k0Var, new IptvRepository$loadSnapshot$4$1$channels$1$1$1(iptvRepository, (IptvPlaylistEntry) obj3, lVar, obj2, listArr2, i12, pVar, null)));
                arrayList2 = arrayList3;
                i12 = i13;
            }
            this.L$0 = null;
            this.L$1 = listArr2;
            this.L$2 = obj2;
            this.label = 1;
            if (ka.m0.f(arrayList2, this) == aVar) {
                return aVar;
            }
            listArr = listArr2;
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Object obj4 = this.L$2;
            listArr = (List[]) this.L$1;
            k2.c.G(obj);
            obj2 = obj4;
        }
        synchronized (obj2) {
            try {
                arrayList = new ArrayList();
                for (List list2 : listArr) {
                    if (list2 == null) {
                        list2 = kotlin.collections.z.f19728i;
                    }
                    kotlin.collections.x.b0(arrayList, list2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return arrayList;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<IptvChannel>> dVar) {
        return ((IptvRepository$loadSnapshot$4$1$channels$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
