package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.media3.exoplayer.ExoPlayer;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$29$1", f = "PlayerScreen.kt", l = {1944, 1989, 2032}, m = "invokeSuspend", v = 2)
public final class PlayerScreenKt$PlayerScreen$29$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ ExoPlayer $exoPlayer;
    final /* synthetic */ MutableState<Boolean> $hasPlaybackStarted$delegate;
    final /* synthetic */ State<PlayerUiState> $latestUiState$delegate;
    final /* synthetic */ MutableState<Boolean> $playerReleased$delegate;
    final /* synthetic */ State<PlayerUiState> $uiState$delegate;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    int I$4;
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    boolean Z$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlayerScreen$29$1(ExoPlayer exoPlayer, MutableState<Boolean> mutableState, State<PlayerUiState> state, MutableState<Boolean> mutableState2, State<PlayerUiState> state2, d7.d<? super PlayerScreenKt$PlayerScreen$29$1> dVar) {
        super(2, dVar);
        this.$exoPlayer = exoPlayer;
        this.$playerReleased$delegate = mutableState;
        this.$uiState$delegate = state;
        this.$hasPlaybackStarted$delegate = mutableState2;
        this.$latestUiState$delegate = state2;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new PlayerScreenKt$PlayerScreen$29$1(this.$exoPlayer, this.$playerReleased$delegate, this.$uiState$delegate, this.$hasPlaybackStarted$delegate, this.$latestUiState$delegate, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:127:0x03e1, code lost:
    
        if (ka.s0.a(500, r0) == r12) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x03e3, code lost:
    
        return r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x01fe, code lost:
    
        if (ka.s0.a(500, r0) == r12) goto L128;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x02c5, code lost:
    
        if (ka.s0.a(400, r0) == r12) goto L128;
     */
    /* JADX WARN: Path cross not found for [B:60:0x0193, B:52:0x015f], limit reached: 170 */
    /* JADX WARN: Removed duplicated region for block: B:101:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0304  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0363  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0378  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x03ab  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x03ba  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0220  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:127:0x03e1 -> B:129:0x03e4). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:66:0x01fe -> B:68:0x0202). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:96:0x02c5 -> B:98:0x02c9). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:99:0x02cb -> B:98:0x02c9). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r25) {
        /*
            Method dump skipped, instruction units count: 1159
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$29$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((PlayerScreenKt$PlayerScreen$29$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
