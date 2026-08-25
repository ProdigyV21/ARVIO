package com.arflix.tv.data.repository.simkl;

import com.arflix.tv.data.api.SimklApi;
import com.arflix.tv.data.api.SimklPlaybackItem;
import com.arflix.tv.data.repository.simkl.SimklSyncService;
import d7.d;
import f7.e;
import f7.j;
import java.util.List;
import k2.c;
import ka.k0;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/simkl/SimklSyncService$SnapshotFetch;", "", "Lcom/arflix/tv/data/api/SimklPlaybackItem;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/simkl/SimklSyncService$SnapshotFetch;"}, k = 3, mv = {2, 3, 0})
@e(c = "com.arflix.tv.data.repository.simkl.SimklSyncService$refreshSnapshot$2$playbackRequest$1", f = "SimklSyncService.kt", l = {169}, m = "invokeSuspend", v = 2)
public final class SimklSyncService$refreshSnapshot$2$playbackRequest$1 extends j implements p<k0, d<? super SimklSyncService.SnapshotFetch<List<? extends SimklPlaybackItem>>>, Object> {
    final /* synthetic */ String $authHeader;
    int label;
    final /* synthetic */ SimklSyncService this$0;

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.simkl.SimklSyncService$refreshSnapshot$2$playbackRequest$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n"}, d2 = {"<anonymous>", "", "Lcom/arflix/tv/data/api/SimklPlaybackItem;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @e(c = "com.arflix.tv.data.repository.simkl.SimklSyncService$refreshSnapshot$2$playbackRequest$1$1", f = "SimklSyncService.kt", l = {169}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends j implements l<d<? super List<? extends SimklPlaybackItem>>, Object> {
        final /* synthetic */ String $authHeader;
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
        public final Object invoke(d<? super List<SimklPlaybackItem>> dVar) {
            return ((AnonymousClass1) create(dVar)).invokeSuspend(t0.f22605a);
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
            SimklApi simklApi = this.this$0.simklApi;
            String str = this.$authHeader;
            String clientId = this.this$0.getClientId();
            this.label = 1;
            Object playback = simklApi.getPlayback(str, clientId, this);
            e7.a aVar = e7.a.f15033i;
            return playback == aVar ? aVar : playback;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SimklSyncService$refreshSnapshot$2$playbackRequest$1(SimklSyncService simklSyncService, String str, d<? super SimklSyncService$refreshSnapshot$2$playbackRequest$1> dVar) {
        super(2, dVar);
        this.this$0 = simklSyncService;
        this.$authHeader = str;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new SimklSyncService$refreshSnapshot$2$playbackRequest$1(this.this$0, this.$authHeader, dVar);
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
        Object objFetchSnapshotPart = simklSyncService.fetchSnapshotPart("Playback", anonymousClass1, this);
        e7.a aVar = e7.a.f15033i;
        return objFetchSnapshotPart == aVar ? aVar : objFetchSnapshotPart;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super SimklSyncService.SnapshotFetch<List<SimklPlaybackItem>>> dVar) {
        return ((SimklSyncService$refreshSnapshot$2$playbackRequest$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
