package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.IptvRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import ka.x0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lx6/t0;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.IptvRepository$fetchXtreamFullEpgListingsAsync$completed$1", f = "IptvRepository.kt", l = {6222}, m = "invokeSuspend", v = 2)
public final class IptvRepository$fetchXtreamFullEpgListingsAsync$completed$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends t0>>, Object> {
    final /* synthetic */ IptvRepository.XtreamCredentials $creds;
    final /* synthetic */ List<Integer> $distinctStreamIds;
    final /* synthetic */ ua.f $gate;
    final /* synthetic */ ConcurrentLinkedQueue<IptvRepository.XtreamEpgListing> $listingsResult;
    final /* synthetic */ r7.p<Integer, Boolean, t0> $onStreamProcessed;
    final /* synthetic */ int $safeParallelism;
    int label;
    final /* synthetic */ IptvRepository this$0;

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.IptvRepository$fetchXtreamFullEpgListingsAsync$completed$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lx6/t0;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.IptvRepository$fetchXtreamFullEpgListingsAsync$completed$1$1", f = "IptvRepository.kt", l = {6249}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends t0>>, Object> {
        final /* synthetic */ IptvRepository.XtreamCredentials $creds;
        final /* synthetic */ List<Integer> $distinctStreamIds;
        final /* synthetic */ ua.f $gate;
        final /* synthetic */ ConcurrentLinkedQueue<IptvRepository.XtreamEpgListing> $listingsResult;
        final /* synthetic */ r7.p<Integer, Boolean, t0> $onStreamProcessed;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ IptvRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(List<Integer> list, ua.f fVar, IptvRepository.XtreamCredentials xtreamCredentials, IptvRepository iptvRepository, ConcurrentLinkedQueue<IptvRepository.XtreamEpgListing> concurrentLinkedQueue, r7.p<? super Integer, ? super Boolean, t0> pVar, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$distinctStreamIds = list;
            this.$gate = fVar;
            this.$creds = xtreamCredentials;
            this.this$0 = iptvRepository;
            this.$listingsResult = concurrentLinkedQueue;
            this.$onStreamProcessed = pVar;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$distinctStreamIds, this.$gate, this.$creds, this.this$0, this.$listingsResult, this.$onStreamProcessed, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            ka.k0 k0Var = (ka.k0) this.L$0;
            int i10 = this.label;
            if (i10 != 0) {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            List<Integer> list = this.$distinctStreamIds;
            ua.f fVar = this.$gate;
            IptvRepository.XtreamCredentials xtreamCredentials = this.$creds;
            IptvRepository iptvRepository = this.this$0;
            ConcurrentLinkedQueue<IptvRepository.XtreamEpgListing> concurrentLinkedQueue = this.$listingsResult;
            r7.p<Integer, Boolean, t0> pVar = this.$onStreamProcessed;
            ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(ka.m0.e(3, null, k0Var, new IptvRepository$fetchXtreamFullEpgListingsAsync$completed$1$1$1$1(fVar, xtreamCredentials, ((Number) it.next()).intValue(), iptvRepository, concurrentLinkedQueue, pVar, null)));
            }
            this.L$0 = null;
            this.label = 1;
            Object objF = ka.m0.f(arrayList, this);
            e7.a aVar = e7.a.f15033i;
            return objF == aVar ? aVar : objF;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super List<t0>> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public IptvRepository$fetchXtreamFullEpgListingsAsync$completed$1(int i10, List<Integer> list, ua.f fVar, IptvRepository.XtreamCredentials xtreamCredentials, IptvRepository iptvRepository, ConcurrentLinkedQueue<IptvRepository.XtreamEpgListing> concurrentLinkedQueue, r7.p<? super Integer, ? super Boolean, t0> pVar, d7.d<? super IptvRepository$fetchXtreamFullEpgListingsAsync$completed$1> dVar) {
        super(2, dVar);
        this.$safeParallelism = i10;
        this.$distinctStreamIds = list;
        this.$gate = fVar;
        this.$creds = xtreamCredentials;
        this.this$0 = iptvRepository;
        this.$listingsResult = concurrentLinkedQueue;
        this.$onStreamProcessed = pVar;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new IptvRepository$fetchXtreamFullEpgListingsAsync$completed$1(this.$safeParallelism, this.$distinctStreamIds, this.$gate, this.$creds, this.this$0, this.$listingsResult, this.$onStreamProcessed, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return obj;
        }
        k2.c.G(obj);
        ra.c cVar = x0.f19655d;
        int i11 = this.$safeParallelism;
        cVar.getClass();
        ka.f0 f0VarLimitedParallelism = ra.l.f21440i.limitedParallelism(i11);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$distinctStreamIds, this.$gate, this.$creds, this.this$0, this.$listingsResult, this.$onStreamProcessed, null);
        this.label = 1;
        Object objY = ka.m0.y(f0VarLimitedParallelism, anonymousClass1, this);
        e7.a aVar = e7.a.f15033i;
        return objY == aVar ? aVar : objY;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<t0>> dVar) {
        return ((IptvRepository$fetchXtreamFullEpgListingsAsync$completed$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
