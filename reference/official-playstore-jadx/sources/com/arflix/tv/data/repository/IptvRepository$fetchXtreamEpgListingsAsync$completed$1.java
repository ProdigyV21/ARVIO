package com.arflix.tv.data.repository;

import com.arflix.tv.data.repository.IptvRepository;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import ka.x0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.IptvRepository$fetchXtreamEpgListingsAsync$completed$1", f = "IptvRepository.kt", l = {6136}, m = "invokeSuspend", v = 2)
public final class IptvRepository$fetchXtreamEpgListingsAsync$completed$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ boolean $allowUnboundedFallback;
    final /* synthetic */ IptvRepository.XtreamCredentials $creds;
    final /* synthetic */ List<Integer> $distinctStreamIds;
    final /* synthetic */ ua.f $gate;
    final /* synthetic */ int $listingLimit;
    final /* synthetic */ ConcurrentLinkedQueue<IptvRepository.XtreamEpgListing> $listingsResult;
    final /* synthetic */ r7.p<Integer, Boolean, t0> $onStreamProcessed;
    final /* synthetic */ AtomicInteger $simpleFallbacks;
    int label;
    final /* synthetic */ IptvRepository this$0;

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.IptvRepository$fetchXtreamEpgListingsAsync$completed$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.IptvRepository$fetchXtreamEpgListingsAsync$completed$1$1", f = "IptvRepository.kt", l = {6191}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        final /* synthetic */ boolean $allowUnboundedFallback;
        final /* synthetic */ IptvRepository.XtreamCredentials $creds;
        final /* synthetic */ List<Integer> $distinctStreamIds;
        final /* synthetic */ ua.f $gate;
        final /* synthetic */ int $listingLimit;
        final /* synthetic */ ConcurrentLinkedQueue<IptvRepository.XtreamEpgListing> $listingsResult;
        final /* synthetic */ r7.p<Integer, Boolean, t0> $onStreamProcessed;
        final /* synthetic */ AtomicInteger $simpleFallbacks;
        int I$0;
        int I$1;
        int I$2;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        int label;
        final /* synthetic */ IptvRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(List<Integer> list, IptvRepository iptvRepository, ua.f fVar, IptvRepository.XtreamCredentials xtreamCredentials, int i10, boolean z, AtomicInteger atomicInteger, ConcurrentLinkedQueue<IptvRepository.XtreamEpgListing> concurrentLinkedQueue, r7.p<? super Integer, ? super Boolean, t0> pVar, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$distinctStreamIds = list;
            this.this$0 = iptvRepository;
            this.$gate = fVar;
            this.$creds = xtreamCredentials;
            this.$listingLimit = i10;
            this.$allowUnboundedFallback = z;
            this.$simpleFallbacks = atomicInteger;
            this.$listingsResult = concurrentLinkedQueue;
            this.$onStreamProcessed = pVar;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$distinctStreamIds, this.this$0, this.$gate, this.$creds, this.$listingLimit, this.$allowUnboundedFallback, this.$simpleFallbacks, this.$listingsResult, this.$onStreamProcessed, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x009c  */
        /* JADX WARN: Removed duplicated region for block: B:20:0x0146  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0131 -> B:19:0x0142). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r27) {
            /*
                Method dump skipped, instruction units count: 329
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.IptvRepository$fetchXtreamEpgListingsAsync$completed$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public IptvRepository$fetchXtreamEpgListingsAsync$completed$1(IptvRepository iptvRepository, List<Integer> list, ua.f fVar, IptvRepository.XtreamCredentials xtreamCredentials, int i10, boolean z, AtomicInteger atomicInteger, ConcurrentLinkedQueue<IptvRepository.XtreamEpgListing> concurrentLinkedQueue, r7.p<? super Integer, ? super Boolean, t0> pVar, d7.d<? super IptvRepository$fetchXtreamEpgListingsAsync$completed$1> dVar) {
        super(2, dVar);
        this.this$0 = iptvRepository;
        this.$distinctStreamIds = list;
        this.$gate = fVar;
        this.$creds = xtreamCredentials;
        this.$listingLimit = i10;
        this.$allowUnboundedFallback = z;
        this.$simpleFallbacks = atomicInteger;
        this.$listingsResult = concurrentLinkedQueue;
        this.$onStreamProcessed = pVar;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new IptvRepository$fetchXtreamEpgListingsAsync$completed$1(this.this$0, this.$distinctStreamIds, this.$gate, this.$creds, this.$listingLimit, this.$allowUnboundedFallback, this.$simpleFallbacks, this.$listingsResult, this.$onStreamProcessed, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            ra.c cVar = x0.f19655d;
            int i11 = this.this$0.xtreamShortEpgConcurrency;
            cVar.getClass();
            ka.f0 f0VarLimitedParallelism = ra.l.f21440i.limitedParallelism(i11);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$distinctStreamIds, this.this$0, this.$gate, this.$creds, this.$listingLimit, this.$allowUnboundedFallback, this.$simpleFallbacks, this.$listingsResult, this.$onStreamProcessed, null);
            this.label = 1;
            Object objY = ka.m0.y(f0VarLimitedParallelism, anonymousClass1, this);
            e7.a aVar = e7.a.f15033i;
            if (objY == aVar) {
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
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((IptvRepository$fetchXtreamEpgListingsAsync$completed$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
