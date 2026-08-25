package com.arflix.tv.ui.screens.tv;

import android.content.Context;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.hls.HlsMediaSource;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import com.arflix.tv.data.model.IptvChannel;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.TvScreenKt$TvScreen$29$1", f = "TvScreen.kt", l = {713}, m = "invokeSuspend", v = 2)
public final class TvScreenKt$TvScreen$29$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ MutableState<ExoPlayer> $exoPlayer$delegate;
    final /* synthetic */ DefaultMediaSourceFactory $iptvDefaultFactory;
    final /* synthetic */ HlsMediaSource.Factory $iptvHlsFactory;
    final /* synthetic */ MutableState<Boolean> $isPlayerReleased$delegate;
    final /* synthetic */ MutableState<String> $lastPreparedStreamUrl$delegate;
    final /* synthetic */ MutableIntState $playerRetryCount$delegate;
    final /* synthetic */ IptvChannel $playingChannel;
    final /* synthetic */ TvViewModel $viewModel;
    Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvScreenKt$TvScreen$29$1(IptvChannel iptvChannel, TvViewModel tvViewModel, Context context, DefaultMediaSourceFactory defaultMediaSourceFactory, MutableState<Boolean> mutableState, MutableState<String> mutableState2, MutableState<ExoPlayer> mutableState3, MutableIntState mutableIntState, HlsMediaSource.Factory factory, d7.d<? super TvScreenKt$TvScreen$29$1> dVar) {
        super(2, dVar);
        this.$playingChannel = iptvChannel;
        this.$viewModel = tvViewModel;
        this.$context = context;
        this.$iptvDefaultFactory = defaultMediaSourceFactory;
        this.$isPlayerReleased$delegate = mutableState;
        this.$lastPreparedStreamUrl$delegate = mutableState2;
        this.$exoPlayer$delegate = mutableState3;
        this.$playerRetryCount$delegate = mutableIntState;
        this.$iptvHlsFactory = factory;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TvScreenKt$TvScreen$29$1(this.$playingChannel, this.$viewModel, this.$context, this.$iptvDefaultFactory, this.$isPlayerReleased$delegate, this.$lastPreparedStreamUrl$delegate, this.$exoPlayer$delegate, this.$playerRetryCount$delegate, this.$iptvHlsFactory, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x007c  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            int r0 = r6.label
            r1 = 1
            r2 = 0
            x6.t0 r3 = x6.t0.f22605a
            r4 = 0
            if (r0 == 0) goto L1f
            if (r0 != r1) goto L17
            java.lang.Object r0 = r6.L$1
            com.arflix.tv.data.api.StalkerApi r0 = (com.arflix.tv.data.api.StalkerApi) r0
            java.lang.Object r0 = r6.L$0
            java.lang.String r0 = (java.lang.String) r0
            k2.c.G(r7)
            goto L6b
        L17:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L1f:
            k2.c.G(r7)
            com.arflix.tv.data.model.IptvChannel r7 = r6.$playingChannel
            if (r7 == 0) goto Lac
            java.lang.String r7 = r7.getStreamUrl()
            if (r7 != 0) goto L2e
            goto Lac
        L2e:
            androidx.compose.runtime.MutableState<java.lang.Boolean> r0 = r6.$isPlayerReleased$delegate
            boolean r0 = com.arflix.tv.ui.screens.tv.TvScreenKt.access$TvScreen$lambda$91(r0)
            if (r0 == 0) goto L38
            goto Lac
        L38:
            java.lang.String r0 = "ffmpeg"
            boolean r0 = kotlin.text.u.P(r7, r0, r2)
            if (r0 != 0) goto L50
            java.lang.String r0 = "/"
            boolean r0 = kotlin.text.u.P(r7, r0, r2)
            if (r0 == 0) goto L6f
            java.lang.String r0 = "//"
            boolean r0 = kotlin.text.u.P(r7, r0, r2)
            if (r0 != 0) goto L6f
        L50:
            com.arflix.tv.ui.screens.tv.TvViewModel r0 = r6.$viewModel
            com.arflix.tv.data.repository.IptvRepository r0 = r0.getIptvRepository()
            com.arflix.tv.data.api.StalkerApi r0 = r0.getCachedStalkerApi()
            if (r0 == 0) goto L6f
            r6.L$0 = r4
            r6.L$1 = r4
            r6.label = r1
            java.lang.Object r7 = r0.resolveStreamUrl(r7, r6)
            e7.a r0 = e7.a.f15033i
            if (r7 != r0) goto L6b
            return r0
        L6b:
            java.lang.String r7 = (java.lang.String) r7
            if (r7 == 0) goto Lac
        L6f:
            androidx.compose.runtime.MutableState<java.lang.String> r0 = r6.$lastPreparedStreamUrl$delegate
            java.lang.String r0 = com.arflix.tv.ui.screens.tv.TvScreenKt.access$TvScreen$lambda$107(r0)
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L7c
            goto Lac
        L7c:
            androidx.compose.runtime.MutableState<androidx.media3.exoplayer.ExoPlayer> r0 = r6.$exoPlayer$delegate
            androidx.media3.exoplayer.ExoPlayer r0 = com.arflix.tv.ui.screens.tv.TvScreenKt.access$TvScreen$lambda$94(r0)
            if (r0 != 0) goto L91
            androidx.compose.runtime.MutableState<androidx.media3.exoplayer.ExoPlayer> r0 = r6.$exoPlayer$delegate
            android.content.Context r1 = r6.$context
            androidx.media3.exoplayer.source.DefaultMediaSourceFactory r5 = r6.$iptvDefaultFactory
            androidx.media3.exoplayer.ExoPlayer r1 = com.arflix.tv.ui.screens.tv.TvScreenKt.access$createTvExoPlayer(r1, r5)
            com.arflix.tv.ui.screens.tv.TvScreenKt.access$TvScreen$lambda$95(r0, r1)
        L91:
            androidx.compose.runtime.MutableState<java.lang.String> r0 = r6.$lastPreparedStreamUrl$delegate
            com.arflix.tv.ui.screens.tv.TvScreenKt.access$TvScreen$lambda$108(r0, r7)
            androidx.compose.runtime.MutableIntState r0 = r6.$playerRetryCount$delegate
            com.arflix.tv.ui.screens.tv.TvScreenKt.access$TvScreen$lambda$112(r0, r2)
            androidx.media3.exoplayer.hls.HlsMediaSource$Factory r0 = r6.$iptvHlsFactory
            androidx.compose.runtime.MutableState<androidx.media3.exoplayer.ExoPlayer> r1 = r6.$exoPlayer$delegate
            androidx.compose.runtime.MutableState<java.lang.Boolean> r2 = r6.$isPlayerReleased$delegate
            com.arflix.tv.data.model.IptvChannel r5 = r6.$playingChannel
            if (r5 == 0) goto La9
            com.arflix.tv.data.model.DrmInfo r4 = r5.getDrmInfo()
        La9:
            com.arflix.tv.ui.screens.tv.TvScreenKt.access$TvScreen$prepareStream(r0, r1, r2, r7, r4)
        Lac:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvScreenKt$TvScreen$29$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((TvScreenKt$TvScreen$29$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
