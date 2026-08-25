package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.media3.exoplayer.ExoPlayer;
import com.arflix.tv.data.model.IptvProgram;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$37$1", f = "LiveTvScreen.kt", l = {1869}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$37$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ long $catchupUrlAnchorOffsetMs;
    final /* synthetic */ ExoPlayer $exoPlayer;
    final /* synthetic */ MutableLongState $playerDurationMs$delegate;
    final /* synthetic */ MutableState<Boolean> $playerIsBuffering$delegate;
    final /* synthetic */ MutableState<Boolean> $playerIsPlaying$delegate;
    final /* synthetic */ MutableState<Boolean> $playerPlayWhenReady$delegate;
    final /* synthetic */ MutableLongState $playerPositionMs$delegate;
    final /* synthetic */ MutableState<IptvProgram> $playingCatchupProgram$delegate;
    long J$0;
    long J$1;
    long J$2;
    long J$3;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$37$1(ExoPlayer exoPlayer, long j10, MutableState<IptvProgram> mutableState, MutableLongState mutableLongState, MutableLongState mutableLongState2, MutableState<Boolean> mutableState2, MutableState<Boolean> mutableState3, MutableState<Boolean> mutableState4, d7.d<? super LiveTvScreenKt$LiveTvScreen$37$1> dVar) {
        super(2, dVar);
        this.$exoPlayer = exoPlayer;
        this.$catchupUrlAnchorOffsetMs = j10;
        this.$playingCatchupProgram$delegate = mutableState;
        this.$playerDurationMs$delegate = mutableLongState;
        this.$playerPositionMs$delegate = mutableLongState2;
        this.$playerIsPlaying$delegate = mutableState2;
        this.$playerPlayWhenReady$delegate = mutableState3;
        this.$playerIsBuffering$delegate = mutableState4;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$37$1(this.$exoPlayer, this.$catchupUrlAnchorOffsetMs, this.$playingCatchupProgram$delegate, this.$playerDurationMs$delegate, this.$playerPositionMs$delegate, this.$playerIsPlaying$delegate, this.$playerPlayWhenReady$delegate, this.$playerIsBuffering$delegate, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002a  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            r14 = this;
            int r0 = r14.label
            r1 = 1
            if (r0 == 0) goto L10
            if (r0 != r1) goto L8
            goto L10
        L8:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L10:
            k2.c.G(r15)
        L13:
            androidx.compose.runtime.MutableState<com.arflix.tv.data.model.IptvProgram> r15 = r14.$playingCatchupProgram$delegate
            com.arflix.tv.data.model.IptvProgram r15 = com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt.access$LiveTvScreen$lambda$77(r15)
            r2 = 0
            if (r15 == 0) goto L2a
            long r4 = r15.getEndUtcMillis()
            long r6 = r15.getStartUtcMillis()
            long r4 = r4 - r6
            int r15 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r15 >= 0) goto L2b
        L2a:
            r4 = r2
        L2b:
            androidx.media3.exoplayer.ExoPlayer r15 = r14.$exoPlayer
            long r6 = r15.getDuration()
            java.lang.Long r15 = new java.lang.Long
            r15.<init>(r6)
            long r6 = r15.longValue()
            int r0 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r0 <= 0) goto L48
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r0 == 0) goto L48
            goto L49
        L48:
            r15 = 0
        L49:
            if (r15 == 0) goto L50
            long r6 = r15.longValue()
            goto L51
        L50:
            r6 = r2
        L51:
            long r8 = java.lang.Math.max(r4, r6)
            androidx.compose.runtime.MutableLongState r15 = r14.$playerDurationMs$delegate
            com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt.access$LiveTvScreen$lambda$202(r15, r8)
            androidx.compose.runtime.MutableState<com.arflix.tv.data.model.IptvProgram> r15 = r14.$playingCatchupProgram$delegate
            com.arflix.tv.data.model.IptvProgram r15 = com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt.access$LiveTvScreen$lambda$77(r15)
            if (r15 == 0) goto L65
            long r10 = r14.$catchupUrlAnchorOffsetMs
            goto L66
        L65:
            r10 = r2
        L66:
            androidx.compose.runtime.MutableLongState r15 = r14.$playerPositionMs$delegate
            androidx.media3.exoplayer.ExoPlayer r0 = r14.$exoPlayer
            long r12 = r0.getCurrentPosition()
            long r12 = r12 + r10
            int r0 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r0 >= 0) goto L74
            r12 = r2
        L74:
            int r0 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r0 <= 0) goto L7d
            int r0 = (r12 > r8 ? 1 : (r12 == r8 ? 0 : -1))
            if (r0 <= 0) goto L7d
            r12 = r8
        L7d:
            com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt.access$LiveTvScreen$lambda$199(r15, r12)
            androidx.compose.runtime.MutableState<java.lang.Boolean> r15 = r14.$playerIsPlaying$delegate
            androidx.media3.exoplayer.ExoPlayer r0 = r14.$exoPlayer
            boolean r0 = r0.isPlaying()
            com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt.access$LiveTvScreen$lambda$205(r15, r0)
            androidx.compose.runtime.MutableState<java.lang.Boolean> r15 = r14.$playerPlayWhenReady$delegate
            androidx.media3.exoplayer.ExoPlayer r0 = r14.$exoPlayer
            boolean r0 = r0.getPlayWhenReady()
            com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt.access$LiveTvScreen$lambda$208(r15, r0)
            androidx.compose.runtime.MutableState<java.lang.Boolean> r15 = r14.$playerIsBuffering$delegate
            androidx.media3.exoplayer.ExoPlayer r0 = r14.$exoPlayer
            int r0 = r0.getPlaybackState()
            r2 = 2
            if (r0 != r2) goto La3
            r0 = r1
            goto La4
        La3:
            r0 = 0
        La4:
            com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt.access$LiveTvScreen$lambda$211(r15, r0)
            androidx.compose.runtime.MutableState<com.arflix.tv.data.model.IptvProgram> r15 = r14.$playingCatchupProgram$delegate
            com.arflix.tv.data.model.IptvProgram r15 = com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt.access$LiveTvScreen$lambda$77(r15)
            if (r15 == 0) goto Lb2
            r2 = 500(0x1f4, double:2.47E-321)
            goto Lb4
        Lb2:
            r2 = 1500(0x5dc, double:7.41E-321)
        Lb4:
            r14.J$0 = r4
            r14.J$1 = r6
            r14.J$2 = r8
            r14.J$3 = r10
            r14.label = r1
            java.lang.Object r15 = ka.s0.a(r2, r14)
            e7.a r0 = e7.a.f15033i
            if (r15 != r0) goto L13
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$37$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$37$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
