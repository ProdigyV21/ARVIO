package com.arflix.tv.ui.screens.details;

import android.os.SystemClock;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.arflix.tv.data.model.EpisodeIdentity;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.model.StreamSource;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.details.DetailsScreenKt$DetailsScreen$13$1", f = "DetailsScreen.kt", l = {386}, m = "invokeSuspend", v = 2)
public final class DetailsScreenKt$DetailsScreen$13$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableIntState $autoPlayWaitTick$delegate;
    final /* synthetic */ int $mediaId;
    final /* synthetic */ MediaType $mediaType;
    final /* synthetic */ r7.v<MediaType, Integer, EpisodeIdentity, String, String, String, String, Long, x6.t0> $onNavigateToPlayer;
    final /* synthetic */ MutableState<PendingAutoPlayRequest> $pendingAutoPlayRequest$delegate;
    final /* synthetic */ MutableState<Boolean> $showStreamSelector$delegate;
    final /* synthetic */ State<DetailsUiState> $uiState$delegate;
    final /* synthetic */ DetailsViewModel $viewModel;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    boolean Z$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public DetailsScreenKt$DetailsScreen$13$1(DetailsViewModel detailsViewModel, int i10, r7.v<? super MediaType, ? super Integer, ? super EpisodeIdentity, ? super String, ? super String, ? super String, ? super String, ? super Long, x6.t0> vVar, MediaType mediaType, MutableState<PendingAutoPlayRequest> mutableState, State<DetailsUiState> state, MutableIntState mutableIntState, MutableState<Boolean> mutableState2, d7.d<? super DetailsScreenKt$DetailsScreen$13$1> dVar) {
        super(2, dVar);
        this.$viewModel = detailsViewModel;
        this.$mediaId = i10;
        this.$onNavigateToPlayer = vVar;
        this.$mediaType = mediaType;
        this.$pendingAutoPlayRequest$delegate = mutableState;
        this.$uiState$delegate = state;
        this.$autoPlayWaitTick$delegate = mutableIntState;
        this.$showStreamSelector$delegate = mutableState2;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new DetailsScreenKt$DetailsScreen$13$1(this.$viewModel, this.$mediaId, this.$onNavigateToPlayer, this.$mediaType, this.$pendingAutoPlayRequest$delegate, this.$uiState$delegate, this.$autoPlayWaitTick$delegate, this.$showStreamSelector$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        x6.t0 t0Var = x6.t0.f22605a;
        if (i10 == 0) {
            k2.c.G(obj);
            PendingAutoPlayRequest pendingAutoPlayRequestDetailsScreen$lambda$51 = DetailsScreenKt.DetailsScreen$lambda$51(this.$pendingAutoPlayRequest$delegate);
            if (pendingAutoPlayRequestDetailsScreen$lambda$51 == null) {
                return t0Var;
            }
            List<StreamSource> streams = DetailsScreenKt.DetailsScreen$lambda$7(this.$uiState$delegate).getStreams();
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : streams) {
                if (AutoPlaySourcePlannerKt.isAutoPlayableStream((StreamSource) obj2)) {
                    arrayList.add(obj2);
                }
            }
            int iMinQualityThreshold = AutoPlaySourcePlannerKt.minQualityThreshold(DetailsScreenKt.DetailsScreen$lambda$7(this.$uiState$delegate).getAutoPlayMinQuality());
            StreamSource streamSourceBestAutoPlayStream = AutoPlaySourcePlannerKt.bestAutoPlayStream(arrayList, iMinQualityThreshold);
            boolean zShouldWaitForAutoPlaySources = AutoPlaySourcePlannerKt.shouldWaitForAutoPlaySources(DetailsScreenKt.DetailsScreen$lambda$7(this.$uiState$delegate).isLoadingStreams(), streamSourceBestAutoPlayStream, SystemClock.elapsedRealtime() - pendingAutoPlayRequestDetailsScreen$lambda$51.getRequestedAtMs());
            if (streamSourceBestAutoPlayStream != null && !zShouldWaitForAutoPlaySources) {
                EpisodeIdentity identity = pendingAutoPlayRequestDetailsScreen$lambda$51.getIdentity();
                this.$viewModel.recordPlayedEpisode(this.$mediaId, identity);
                r7.v<MediaType, Integer, EpisodeIdentity, String, String, String, String, Long, x6.t0> vVar = this.$onNavigateToPlayer;
                MediaType mediaType = this.$mediaType;
                Integer num = new Integer(this.$mediaId);
                String imdbId = DetailsScreenKt.DetailsScreen$lambda$7(this.$uiState$delegate).getImdbId();
                String url = streamSourceBestAutoPlayStream.getUrl();
                String str = (url == null || kotlin.text.o.h0(url)) ? null : url;
                String addonId = streamSourceBestAutoPlayStream.getAddonId();
                String str2 = !kotlin.text.o.h0(addonId) ? addonId : null;
                String source = streamSourceBestAutoPlayStream.getSource();
                vVar.invoke(mediaType, num, identity, imdbId, str, str2, !kotlin.text.o.h0(source) ? source : null, pendingAutoPlayRequestDetailsScreen$lambda$51.getStartPositionMs());
                this.$pendingAutoPlayRequest$delegate.setValue(null);
            } else if (zShouldWaitForAutoPlaySources) {
                this.L$0 = null;
                this.L$1 = null;
                this.L$2 = null;
                this.I$0 = iMinQualityThreshold;
                this.Z$0 = zShouldWaitForAutoPlaySources;
                this.label = 1;
                Object objA = ka.s0.a(120L, this);
                e7.a aVar = e7.a.f15033i;
                if (objA == aVar) {
                    return aVar;
                }
            } else if (!DetailsScreenKt.DetailsScreen$lambda$7(this.$uiState$delegate).isLoadingStreams()) {
                if (!arrayList.isEmpty() || DetailsScreenKt.DetailsScreen$lambda$7(this.$uiState$delegate).getStreams().isEmpty()) {
                    DetailsScreenKt.DetailsScreen$lambda$46(this.$showStreamSelector$delegate, true);
                    this.$pendingAutoPlayRequest$delegate.setValue(null);
                } else {
                    DetailsScreenKt.DetailsScreen$lambda$46(this.$showStreamSelector$delegate, true);
                    this.$pendingAutoPlayRequest$delegate.setValue(null);
                }
            }
            return t0Var;
        }
        if (i10 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        MutableIntState mutableIntState = this.$autoPlayWaitTick$delegate;
        mutableIntState.setIntValue(mutableIntState.getIntValue() + 1);
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((DetailsScreenKt$DetailsScreen$13$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
