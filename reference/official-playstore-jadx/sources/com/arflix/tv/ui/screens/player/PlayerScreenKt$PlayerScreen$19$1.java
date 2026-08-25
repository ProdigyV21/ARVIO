package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.arflix.tv.BuildConfig;
import com.arflix.tv.cast.CastManager;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.ui.screens.details.discord.DiscordRpcManager;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$19$1", f = "PlayerScreen.kt", l = {1579}, m = "invokeSuspend", v = 2)
public final class PlayerScreenKt$PlayerScreen$19$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ CastManager $castManager;
    final /* synthetic */ MutableLongState $currentPosition$delegate;
    final /* synthetic */ MutableLongState $duration$delegate;
    final /* synthetic */ Integer $episodeNumber;
    final /* synthetic */ boolean $isCasting;
    final /* synthetic */ MutableState<Boolean> $isPlaying$delegate;
    final /* synthetic */ MutableState<Long> $lastCastPositionMs$delegate;
    final /* synthetic */ State<PlayerUiState> $latestUiState$delegate;
    final /* synthetic */ MediaType $mediaType;
    final /* synthetic */ MutableFloatState $progress$delegate;
    final /* synthetic */ Integer $seasonNumber;
    long J$0;
    long J$1;
    Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlayerScreen$19$1(boolean z, CastManager castManager, MediaType mediaType, Integer num, Integer num2, MutableState<Long> mutableState, MutableLongState mutableLongState, MutableLongState mutableLongState2, MutableFloatState mutableFloatState, MutableState<Boolean> mutableState2, State<PlayerUiState> state, d7.d<? super PlayerScreenKt$PlayerScreen$19$1> dVar) {
        super(2, dVar);
        this.$isCasting = z;
        this.$castManager = castManager;
        this.$mediaType = mediaType;
        this.$seasonNumber = num;
        this.$episodeNumber = num2;
        this.$lastCastPositionMs$delegate = mutableState;
        this.$currentPosition$delegate = mutableLongState;
        this.$duration$delegate = mutableLongState2;
        this.$progress$delegate = mutableFloatState;
        this.$isPlaying$delegate = mutableState2;
        this.$latestUiState$delegate = state;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new PlayerScreenKt$PlayerScreen$19$1(this.$isCasting, this.$castManager, this.$mediaType, this.$seasonNumber, this.$episodeNumber, this.$lastCastPositionMs$delegate, this.$currentPosition$delegate, this.$duration$delegate, this.$progress$delegate, this.$isPlaying$delegate, this.$latestUiState$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        String str;
        Object objA;
        e7.a aVar;
        String strT;
        Integer num;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            if (!this.$isCasting) {
                return x6.t0.f22605a;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        do {
            long approximatePosition = this.$castManager.getApproximatePosition();
            if (approximatePosition > 0) {
                PlayerScreenKt.PlayerScreen$lambda$323(this.$lastCastPositionMs$delegate, approximatePosition);
            }
            this.$currentPosition$delegate.setLongValue(approximatePosition);
            long approximateDuration = this.$castManager.getApproximateDuration();
            if (approximateDuration > 0) {
                this.$duration$delegate.setLongValue(approximateDuration);
            }
            this.$progress$delegate.setFloatValue(this.$duration$delegate.getLongValue() > 0 ? qb.d.m(this.$currentPosition$delegate.getLongValue() / this.$duration$delegate.getLongValue(), 0.0f, 1.0f) : 0.0f);
            PlayerScreenKt.PlayerScreen$lambda$21(this.$isPlaying$delegate, this.$castManager.isRemotePlaying());
            String title = PlayerScreenKt.PlayerScreen$lambda$4(this.$latestUiState$delegate).getTitle();
            if (this.$mediaType == MediaType.TV) {
                Integer num2 = this.$seasonNumber;
                if (num2 == null || (num = this.$episodeNumber) == null) {
                    strT = "";
                } else {
                    strT = "S" + num2 + "E" + num;
                }
                String episodeTitle = PlayerScreenKt.PlayerScreen$lambda$4(this.$latestUiState$delegate).getEpisodeTitle();
                if (episodeTitle == null || kotlin.text.o.h0(episodeTitle)) {
                    str = strT;
                } else if (strT.length() > 0) {
                    strT = androidx.compose.foundation.c.t(strT, " - ", episodeTitle);
                    str = strT;
                } else {
                    str = episodeTitle;
                }
            } else {
                str = "";
            }
            DiscordRpcManager discordRpcManager = DiscordRpcManager.INSTANCE;
            if (title == null) {
                title = BuildConfig.GITHUB_REPO;
            }
            String str2 = title;
            boolean zPlayerScreen$lambda$20 = PlayerScreenKt.PlayerScreen$lambda$20(this.$isPlaying$delegate);
            long longValue = this.$currentPosition$delegate.getLongValue();
            long longValue2 = this.$duration$delegate.getLongValue();
            String posterUrl = PlayerScreenKt.PlayerScreen$lambda$4(this.$latestUiState$delegate).getPosterUrl();
            discordRpcManager.updatePlayback(str2, str, zPlayerScreen$lambda$20, longValue, longValue2, (posterUrl == null && (posterUrl = PlayerScreenKt.PlayerScreen$lambda$4(this.$latestUiState$delegate).getLogoUrl()) == null) ? "" : posterUrl);
            this.L$0 = null;
            this.L$1 = null;
            this.J$0 = approximatePosition;
            this.J$1 = approximateDuration;
            this.label = 1;
            objA = ka.s0.a(500L, this);
            aVar = e7.a.f15033i;
        } while (objA != aVar);
        return aVar;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((PlayerScreenKt$PlayerScreen$19$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
