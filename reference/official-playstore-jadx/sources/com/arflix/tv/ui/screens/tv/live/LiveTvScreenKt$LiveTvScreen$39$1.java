package com.arflix.tv.ui.screens.tv.live;

import android.content.Context;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.media3.datasource.okhttp.OkHttpDataSource;
import androidx.media3.exoplayer.ExoPlayer;
import com.arflix.tv.data.model.IptvProgram;
import com.arflix.tv.ui.screens.tv.TvViewModel;
import java.util.LinkedHashSet;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$39$1", f = "LiveTvScreen.kt", l = {2107}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$39$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ Map<String, String> $baseRequestHeaders;
    final /* synthetic */ long $catchupInSegmentSeekMs;
    final /* synthetic */ MutableLongState $catchupPlaybackOffsetMs$delegate;
    final /* synthetic */ long $catchupUrlAnchorOffsetMs;
    final /* synthetic */ Context $context;
    final /* synthetic */ String $currentStreamUrl;
    final /* synthetic */ ExoPlayer $exoPlayer;
    final /* synthetic */ OkHttpDataSource.Factory $iptvDataSourceFactory;
    final /* synthetic */ MutableLongState $lastPreparedCatchupOffsetMs$delegate;
    final /* synthetic */ MutableState<Map<String, String>> $lastPreparedHeaders$delegate;
    final /* synthetic */ MutableState<Boolean> $lastPreparedIsHls$delegate;
    final /* synthetic */ MutableState<String> $lastPreparedStreamUrl$delegate;
    final /* synthetic */ MutableState<PlaybackDiagnostic> $playbackDiagnostic$delegate;
    final /* synthetic */ MutableState<Boolean> $playerIsBuffering$delegate;
    final /* synthetic */ MutableIntState $playerRetryCount$delegate;
    final /* synthetic */ MutableState<IptvProgram> $playingCatchupProgram$delegate;
    final /* synthetic */ EnrichedChannel $playingChannel;
    final /* synthetic */ MutableState<String> $playingChannelId$delegate;
    final /* synthetic */ MutableState<LinkedHashSet<String>> $recents;
    final /* synthetic */ MutableState<String> $selectedCategoryId$delegate;
    final /* synthetic */ TvViewModel $viewModel;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$39$1(String str, EnrichedChannel enrichedChannel, long j10, long j11, MutableState<IptvProgram> mutableState, TvViewModel tvViewModel, Context context, MutableState<PlaybackDiagnostic> mutableState2, Map<String, String> map, OkHttpDataSource.Factory factory, ExoPlayer exoPlayer, MutableState<String> mutableState3, MutableState<Boolean> mutableState4, MutableState<Map<String, String>> mutableState5, MutableLongState mutableLongState, MutableState<Boolean> mutableState6, MutableIntState mutableIntState, MutableLongState mutableLongState2, MutableState<String> mutableState7, MutableState<LinkedHashSet<String>> mutableState8, MutableState<String> mutableState9, d7.d<? super LiveTvScreenKt$LiveTvScreen$39$1> dVar) {
        super(2, dVar);
        this.$currentStreamUrl = str;
        this.$playingChannel = enrichedChannel;
        this.$catchupUrlAnchorOffsetMs = j10;
        this.$catchupInSegmentSeekMs = j11;
        this.$playingCatchupProgram$delegate = mutableState;
        this.$viewModel = tvViewModel;
        this.$context = context;
        this.$playbackDiagnostic$delegate = mutableState2;
        this.$baseRequestHeaders = map;
        this.$iptvDataSourceFactory = factory;
        this.$exoPlayer = exoPlayer;
        this.$lastPreparedStreamUrl$delegate = mutableState3;
        this.$lastPreparedIsHls$delegate = mutableState4;
        this.$lastPreparedHeaders$delegate = mutableState5;
        this.$lastPreparedCatchupOffsetMs$delegate = mutableLongState;
        this.$playerIsBuffering$delegate = mutableState6;
        this.$playerRetryCount$delegate = mutableIntState;
        this.$catchupPlaybackOffsetMs$delegate = mutableLongState2;
        this.$playingChannelId$delegate = mutableState7;
        this.$recents = mutableState8;
        this.$selectedCategoryId$delegate = mutableState9;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        LiveTvScreenKt$LiveTvScreen$39$1 liveTvScreenKt$LiveTvScreen$39$1 = new LiveTvScreenKt$LiveTvScreen$39$1(this.$currentStreamUrl, this.$playingChannel, this.$catchupUrlAnchorOffsetMs, this.$catchupInSegmentSeekMs, this.$playingCatchupProgram$delegate, this.$viewModel, this.$context, this.$playbackDiagnostic$delegate, this.$baseRequestHeaders, this.$iptvDataSourceFactory, this.$exoPlayer, this.$lastPreparedStreamUrl$delegate, this.$lastPreparedIsHls$delegate, this.$lastPreparedHeaders$delegate, this.$lastPreparedCatchupOffsetMs$delegate, this.$playerIsBuffering$delegate, this.$playerRetryCount$delegate, this.$catchupPlaybackOffsetMs$delegate, this.$playingChannelId$delegate, this.$recents, this.$selectedCategoryId$delegate, dVar);
        liveTvScreenKt$LiveTvScreen$39$1.L$0 = obj;
        return liveTvScreenKt$LiveTvScreen$39$1;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x014d  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r39) {
        /*
            Method dump skipped, instruction units count: 431
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$39$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$39$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
