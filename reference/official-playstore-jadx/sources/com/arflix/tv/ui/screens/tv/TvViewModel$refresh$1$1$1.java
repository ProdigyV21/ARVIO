package com.arflix.tv.ui.screens.tv;

import androidx.media3.exoplayer.analytics.AnalyticsListener;
import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.data.model.IptvNowNext;
import com.arflix.tv.data.model.IptvSnapshot;
import com.arflix.tv.data.repository.IptvLoadProgress;
import com.arflix.tv.data.repository.IptvRepository;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import ka.m0;
import ka.x0;
import kotlin.Metadata;
import na.q0;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/model/IptvSnapshot;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/model/IptvSnapshot;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$refresh$1$1$1", f = "TvViewModel.kt", l = {308}, m = "invokeSuspend", v = 2)
public final class TvViewModel$refresh$1$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super IptvSnapshot>, Object> {
    final /* synthetic */ boolean $force;
    final /* synthetic */ boolean $forceEpg;
    final /* synthetic */ boolean $hasExistingChannels;
    final /* synthetic */ boolean $showLoading;
    int label;
    final /* synthetic */ TvViewModel this$0;

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$refresh$1$1$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u00020\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"", "Lcom/arflix/tv/data/model/IptvChannel;", "channels", "Lx6/t0;", "<anonymous>", "(Ljava/util/List;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$refresh$1$1$1$2", f = "TvViewModel.kt", l = {334}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<List<? extends IptvChannel>, d7.d<? super t0>, Object> {
        /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        final /* synthetic */ TvViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(TvViewModel tvViewModel, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.this$0 = tvViewModel;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objY;
            TvUiState tvUiState;
            IptvSnapshot iptvSnapshot;
            Map map;
            IptvSnapshot iptvSnapshot2;
            Map<String, IptvNowNext> nowNext;
            List list = (List) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                IptvSnapshot snapshot = ((TvUiState) this.this$0._uiState.getValue()).getSnapshot();
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (Object obj2 : list) {
                    String group = ((IptvChannel) obj2).getGroup();
                    if (kotlin.text.o.h0(group)) {
                        group = "Uncategorized";
                    }
                    Object objT = linkedHashMap.get(group);
                    if (objT == null) {
                        objT = androidx.compose.material3.d.t(linkedHashMap, group);
                    }
                    ((List) objT).add(obj2);
                }
                TvUiState tvUiState2 = (TvUiState) this.this$0._uiState.getValue();
                IptvSnapshot iptvSnapshotCopy$default = IptvSnapshot.copy$default(snapshot, list, linkedHashMap, null, null, null, null, null, null, null, null, AnalyticsListener.EVENT_VIDEO_DISABLED, null);
                ra.d dVar = x0.f19653b;
                TvViewModel$refresh$1$1$1$2$cachedNowNext$1 tvViewModel$refresh$1$1$1$2$cachedNowNext$1 = new TvViewModel$refresh$1$1$1$2$cachedNowNext$1(list, tvUiState2, iptvSnapshotCopy$default, linkedHashMap, this.this$0, null);
                this.L$0 = null;
                this.L$1 = snapshot;
                this.L$2 = linkedHashMap;
                this.L$3 = tvUiState2;
                this.L$4 = iptvSnapshotCopy$default;
                this.label = 1;
                objY = m0.y(dVar, tvViewModel$refresh$1$1$1$2$cachedNowNext$1, this);
                e7.a aVar = e7.a.f15033i;
                if (objY == aVar) {
                    return aVar;
                }
                tvUiState = tvUiState2;
                iptvSnapshot = iptvSnapshotCopy$default;
                map = linkedHashMap;
                iptvSnapshot2 = snapshot;
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                IptvSnapshot iptvSnapshot3 = (IptvSnapshot) this.L$4;
                TvUiState tvUiState3 = (TvUiState) this.L$3;
                map = (Map) this.L$2;
                iptvSnapshot2 = (IptvSnapshot) this.L$1;
                k2.c.G(obj);
                tvUiState = tvUiState3;
                iptvSnapshot = iptvSnapshot3;
                objY = obj;
            }
            Map<? extends String, ? extends IptvNowNext> map2 = (Map) objY;
            if (map2.isEmpty()) {
                nowNext = iptvSnapshot2.getNowNext();
            } else {
                nowNext = new LinkedHashMap<>(iptvSnapshot2.getNowNext());
                nowNext.putAll(map2);
            }
            IptvSnapshot iptvSnapshotCopy$default2 = IptvSnapshot.copy$default(iptvSnapshot, null, null, nowNext, null, null, null, null, null, null, null, AnalyticsListener.EVENT_VIDEO_DECODER_RELEASED, null);
            this.this$0.setUiState(TvUiState.copy$default(tvUiState, false, null, null, 0, null, this.this$0.capLargeListGuideSnapshot(iptvSnapshotCopy$default2, map, tvUiState.getTvSession(), map2.keySet()), null, null, null, null, false, false, false, null, null, null, false, 131024, null));
            this.this$0.startFullEpgWarmup();
            TvViewModel.startCompleteEpgBackfill$default(this.this$0, false, null, 3, null);
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(List<IptvChannel> list, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(list, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvViewModel$refresh$1$1$1(TvViewModel tvViewModel, boolean z, boolean z5, boolean z10, boolean z11, d7.d<? super TvViewModel$refresh$1$1$1> dVar) {
        super(2, dVar);
        this.this$0 = tvViewModel;
        this.$force = z;
        this.$forceEpg = z5;
        this.$showLoading = z10;
        this.$hasExistingChannels = z11;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 invokeSuspend$lambda$0(boolean z, boolean z5, TvViewModel tvViewModel, IptvLoadProgress iptvLoadProgress) {
        if (z && !z5) {
            q0 q0Var = tvViewModel._uiState;
            TvUiState tvUiState = (TvUiState) tvViewModel._uiState.getValue();
            String message = iptvLoadProgress.getMessage();
            Integer percent = iptvLoadProgress.getPercent();
            q0Var.setValue(TvUiState.copy$default(tvUiState, true, null, message, percent != null ? percent.intValue() : ((TvUiState) tvViewModel._uiState.getValue()).getLoadingPercent(), null, null, null, null, null, null, false, false, false, null, null, null, false, 131058, null));
        }
        return t0.f22605a;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TvViewModel$refresh$1$1$1(this.this$0, this.$force, this.$forceEpg, this.$showLoading, this.$hasExistingChannels, dVar);
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
        IptvRepository iptvRepository = this.this$0.getIptvRepository();
        boolean z = this.$force;
        boolean z5 = this.$forceEpg;
        final boolean z10 = this.$showLoading;
        final boolean z11 = this.$hasExistingChannels;
        final TvViewModel tvViewModel = this.this$0;
        r7.l lVar = new r7.l() { // from class: com.arflix.tv.ui.screens.tv.i0
            @Override // r7.l
            public final Object invoke(Object obj2) {
                return TvViewModel$refresh$1$1$1.invokeSuspend$lambda$0(z10, z11, tvViewModel, (IptvLoadProgress) obj2);
            }
        };
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(tvViewModel, null);
        this.label = 1;
        Object objLoadSnapshot$default = IptvRepository.loadSnapshot$default(iptvRepository, z, z5, false, false, lVar, anonymousClass2, this, 8, null);
        e7.a aVar = e7.a.f15033i;
        return objLoadSnapshot$default == aVar ? aVar : objLoadSnapshot$default;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super IptvSnapshot> dVar) {
        return ((TvViewModel$refresh$1$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
