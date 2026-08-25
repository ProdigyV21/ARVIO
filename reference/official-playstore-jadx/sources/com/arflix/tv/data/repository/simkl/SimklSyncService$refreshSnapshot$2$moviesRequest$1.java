package com.arflix.tv.data.repository.simkl;

import com.arflix.tv.data.api.SimklAllItemsResponse;
import com.arflix.tv.data.api.SimklApi;
import com.arflix.tv.data.repository.simkl.SimklSyncService;
import com.google.gson.JsonElement;
import d7.d;
import f7.e;
import f7.j;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/simkl/SimklSyncService$SnapshotFetch;", "Lcom/arflix/tv/data/api/SimklAllItemsResponse;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/simkl/SimklSyncService$SnapshotFetch;"}, k = 3, mv = {2, 3, 0})
@e(c = "com.arflix.tv.data.repository.simkl.SimklSyncService$refreshSnapshot$2$moviesRequest$1", f = "SimklSyncService.kt", l = {154}, m = "invokeSuspend", v = 2)
public final class SimklSyncService$refreshSnapshot$2$moviesRequest$1 extends j implements p<k0, d<? super SimklSyncService.SnapshotFetch<SimklAllItemsResponse>>, Object> {
    final /* synthetic */ String $authHeader;
    int label;
    final /* synthetic */ SimklSyncService this$0;

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklSyncService$refreshSnapshot$2$moviesRequest$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lcom/arflix/tv/data/api/SimklAllItemsResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.data.repository.simkl.SimklSyncService$refreshSnapshot$2$moviesRequest$1$1", f = "SimklSyncService.kt", l = {155}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends j implements l<d<? super SimklAllItemsResponse>, Object> {
        final /* synthetic */ String $authHeader;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ SimklSyncService this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(SimklSyncService simklSyncService, String str, d<? super AnonymousClass1> dVar) {
            super(1, dVar);
            this.this$0 = simklSyncService;
            this.$authHeader = str;
        }

        @Override // f7.a
        public final d<t0> create(d<?> dVar) {
            return new AnonymousClass1(this.this$0, this.$authHeader, dVar);
        }

        @Override // r7.l
        public final Object invoke(d<? super SimklAllItemsResponse> dVar) {
            return ((AnonymousClass1) create(dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            String str;
            Object objB;
            SimklSyncService simklSyncService;
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                SimklSyncService simklSyncService2 = this.this$0;
                SimklApi simklApi = simklSyncService2.simklApi;
                String str2 = this.$authHeader;
                String clientId = this.this$0.getClientId();
                this.L$0 = simklSyncService2;
                str = "movies";
                this.L$1 = "movies";
                this.label = 1;
                objB = com.arflix.tv.data.api.e.b(simklApi, str2, clientId, "movies", null, null, null, null, null, null, this, 504, null);
                e7.a aVar = e7.a.f15033i;
                if (objB == aVar) {
                    return aVar;
                }
                simklSyncService = simklSyncService2;
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                String str3 = (String) this.L$1;
                simklSyncService = (SimklSyncService) this.L$0;
                c.G(obj);
                str = str3;
                objB = obj;
            }
            return simklSyncService.decodeAllItems(str, (JsonElement) objB);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SimklSyncService$refreshSnapshot$2$moviesRequest$1(SimklSyncService simklSyncService, String str, d<? super SimklSyncService$refreshSnapshot$2$moviesRequest$1> dVar) {
        super(2, dVar);
        this.this$0 = simklSyncService;
        this.$authHeader = str;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new SimklSyncService$refreshSnapshot$2$moviesRequest$1(this.this$0, this.$authHeader, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            return obj;
        }
        c.G(obj);
        SimklSyncService simklSyncService = this.this$0;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(simklSyncService, this.$authHeader, null);
        this.label = 1;
        Object objFetchSnapshotPart = simklSyncService.fetchSnapshotPart("Movies", anonymousClass1, this);
        e7.a aVar = e7.a.f15033i;
        return objFetchSnapshotPart == aVar ? aVar : objFetchSnapshotPart;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super SimklSyncService.SnapshotFetch<SimklAllItemsResponse>> dVar) {
        return ((SimklSyncService$refreshSnapshot$2$moviesRequest$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
