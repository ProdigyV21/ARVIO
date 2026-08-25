package com.arflix.tv.ui.screens.tv.live;

import android.content.Context;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.media3.common.PlaybackException;
import androidx.media3.datasource.okhttp.OkHttpDataSource;
import androidx.media3.exoplayer.ExoPlayer;
import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.data.model.IptvProgram;
import com.arflix.tv.ui.screens.tv.TvViewModel;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$40$1$listener$1$onPlayerError$1", f = "LiveTvScreen.kt", l = {2213, 2216}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$40$1$listener$1$onPlayerError$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ Map<String, String> $baseRequestHeaders;
    final /* synthetic */ int $catchupCandidateCount;
    final /* synthetic */ MutableLongState $catchupPlaybackOffsetMs$delegate;
    final /* synthetic */ long $catchupUrlAnchorOffsetMs;
    final /* synthetic */ Context $context;
    final /* synthetic */ PlaybackException $error;
    final /* synthetic */ ExoPlayer $exoPlayer;
    final /* synthetic */ OkHttpDataSource.Factory $iptvDataSourceFactory;
    final /* synthetic */ MutableLongState $lastPreparedCatchupOffsetMs$delegate;
    final /* synthetic */ MutableState<Map<String, String>> $lastPreparedHeaders$delegate;
    final /* synthetic */ MutableState<Boolean> $lastPreparedIsHls$delegate;
    final /* synthetic */ MutableState<String> $lastPreparedStreamUrl$delegate;
    final /* synthetic */ int $maxRetryCount;
    final /* synthetic */ int $nextAttempt;
    final /* synthetic */ MutableState<PlaybackDiagnostic> $playbackDiagnostic$delegate;
    final /* synthetic */ MutableState<Boolean> $playerIsBuffering$delegate;
    final /* synthetic */ MutableIntState $playerRetryCount$delegate;
    final /* synthetic */ MutableState<IptvProgram> $playingCatchupProgram$delegate;
    final /* synthetic */ EnrichedChannel $playingChannel;
    final /* synthetic */ String $prepared;
    final /* synthetic */ boolean $preparedIsHls;
    final /* synthetic */ IptvChannel $retryChannel;
    final /* synthetic */ Map<String, String> $retryHeaders;
    final /* synthetic */ IptvProgram $retryProgram;
    final /* synthetic */ IptvProgram $retryStreamProgram;
    final /* synthetic */ TvViewModel $viewModel;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$40$1$listener$1$onPlayerError$1(int i10, PlaybackException playbackException, int i11, Context context, int i12, Map<String, String> map, IptvChannel iptvChannel, TvViewModel tvViewModel, IptvProgram iptvProgram, IptvProgram iptvProgram2, String str, boolean z, MutableState<PlaybackDiagnostic> mutableState, MutableLongState mutableLongState, Map<String, String> map2, OkHttpDataSource.Factory factory, long j10, ExoPlayer exoPlayer, EnrichedChannel enrichedChannel, MutableState<String> mutableState2, MutableState<Boolean> mutableState3, MutableState<Map<String, String>> mutableState4, MutableState<IptvProgram> mutableState5, MutableLongState mutableLongState2, MutableState<Boolean> mutableState6, MutableIntState mutableIntState, d7.d<? super LiveTvScreenKt$LiveTvScreen$40$1$listener$1$onPlayerError$1> dVar) {
        super(2, dVar);
        this.$nextAttempt = i10;
        this.$error = playbackException;
        this.$catchupCandidateCount = i11;
        this.$context = context;
        this.$maxRetryCount = i12;
        this.$retryHeaders = map;
        this.$retryChannel = iptvChannel;
        this.$viewModel = tvViewModel;
        this.$retryStreamProgram = iptvProgram;
        this.$retryProgram = iptvProgram2;
        this.$prepared = str;
        this.$preparedIsHls = z;
        this.$playbackDiagnostic$delegate = mutableState;
        this.$catchupPlaybackOffsetMs$delegate = mutableLongState;
        this.$baseRequestHeaders = map2;
        this.$iptvDataSourceFactory = factory;
        this.$catchupUrlAnchorOffsetMs = j10;
        this.$exoPlayer = exoPlayer;
        this.$playingChannel = enrichedChannel;
        this.$lastPreparedStreamUrl$delegate = mutableState2;
        this.$lastPreparedIsHls$delegate = mutableState3;
        this.$lastPreparedHeaders$delegate = mutableState4;
        this.$playingCatchupProgram$delegate = mutableState5;
        this.$lastPreparedCatchupOffsetMs$delegate = mutableLongState2;
        this.$playerIsBuffering$delegate = mutableState6;
        this.$playerRetryCount$delegate = mutableIntState;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        LiveTvScreenKt$LiveTvScreen$40$1$listener$1$onPlayerError$1 liveTvScreenKt$LiveTvScreen$40$1$listener$1$onPlayerError$1 = new LiveTvScreenKt$LiveTvScreen$40$1$listener$1$onPlayerError$1(this.$nextAttempt, this.$error, this.$catchupCandidateCount, this.$context, this.$maxRetryCount, this.$retryHeaders, this.$retryChannel, this.$viewModel, this.$retryStreamProgram, this.$retryProgram, this.$prepared, this.$preparedIsHls, this.$playbackDiagnostic$delegate, this.$catchupPlaybackOffsetMs$delegate, this.$baseRequestHeaders, this.$iptvDataSourceFactory, this.$catchupUrlAnchorOffsetMs, this.$exoPlayer, this.$playingChannel, this.$lastPreparedStreamUrl$delegate, this.$lastPreparedIsHls$delegate, this.$lastPreparedHeaders$delegate, this.$playingCatchupProgram$delegate, this.$lastPreparedCatchupOffsetMs$delegate, this.$playerIsBuffering$delegate, this.$playerRetryCount$delegate, dVar);
        liveTvScreenKt$LiveTvScreen$40$1$listener$1$onPlayerError$1.L$0 = obj;
        return liveTvScreenKt$LiveTvScreen$40$1$listener$1$onPlayerError$1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0066, code lost:
    
        if (r0 == r9) goto L26;
     */
    /* JADX WARN: Removed duplicated region for block: B:33:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x016c  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r36) {
        /*
            Method dump skipped, instruction units count: 441
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$40$1$listener$1$onPlayerError$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$40$1$listener$1$onPlayerError$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
