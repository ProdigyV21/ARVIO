package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.media3.common.TrackSelectionOverride;
import androidx.media3.common.Tracks;
import androidx.media3.exoplayer.ExoPlayer;
import com.arflix.tv.data.model.Subtitle;
import java.util.Iterator;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$30$1", f = "PlayerScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class PlayerScreenKt$PlayerScreen$30$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ ExoPlayer $exoPlayer;
    final /* synthetic */ MutableState<Boolean> $playerReleased$delegate;
    final /* synthetic */ State<PlayerUiState> $uiState$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlayerScreen$30$1(ExoPlayer exoPlayer, MutableState<Boolean> mutableState, State<PlayerUiState> state, d7.d<? super PlayerScreenKt$PlayerScreen$30$1> dVar) {
        super(2, dVar);
        this.$exoPlayer = exoPlayer;
        this.$playerReleased$delegate = mutableState;
        this.$uiState$delegate = state;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new PlayerScreenKt$PlayerScreen$30$1(this.$exoPlayer, this.$playerReleased$delegate, this.$uiState$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Subtitle selectedSubtitle;
        Object obj2;
        Object next;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        boolean zPlayerScreen$lambda$274 = PlayerScreenKt.PlayerScreen$lambda$274(this.$playerReleased$delegate);
        x6.t0 t0Var = x6.t0.f22605a;
        if (zPlayerScreen$lambda$274 || (selectedSubtitle = PlayerScreenKt.PlayerScreen$lambda$3(this.$uiState$delegate).getSelectedSubtitle()) == null || !selectedSubtitle.isEmbedded()) {
            return t0Var;
        }
        Iterator<T> it = PlayerScreenKt.PlayerScreen$lambda$3(this.$uiState$delegate).getSubtitles().iterator();
        while (true) {
            obj2 = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            Subtitle subtitle = (Subtitle) next;
            if (kotlin.jvm.internal.p.a(subtitle.getId(), selectedSubtitle.getId()) && subtitle.getGroupIndex() != null && subtitle.getTrackIndex() != null) {
                break;
            }
        }
        Subtitle subtitle2 = (Subtitle) next;
        if (subtitle2 == null) {
            Iterator<T> it2 = PlayerScreenKt.PlayerScreen$lambda$3(this.$uiState$delegate).getSubtitles().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Object next2 = it2.next();
                Subtitle subtitle3 = (Subtitle) next2;
                if (subtitle3.isEmbedded() && kotlin.jvm.internal.p.a(subtitle3.getLang(), selectedSubtitle.getLang()) && kotlin.jvm.internal.p.a(subtitle3.getLabel(), selectedSubtitle.getLabel()) && subtitle3.getGroupIndex() != null && subtitle3.getTrackIndex() != null) {
                    obj2 = next2;
                    break;
                }
            }
            subtitle2 = (Subtitle) obj2;
            if (subtitle2 == null) {
                return t0Var;
            }
        }
        com.google.common.collect.h1 groups = this.$exoPlayer.getCurrentTracks().getGroups();
        if (subtitle2.getGroupIndex() != null && subtitle2.getTrackIndex() != null) {
            int size = groups.size();
            int iIntValue = subtitle2.getGroupIndex().intValue();
            if (iIntValue >= 0 && iIntValue < size && ((Tracks.Group) groups.get(subtitle2.getGroupIndex().intValue())).getType() == 3) {
                ExoPlayer exoPlayer = this.$exoPlayer;
                exoPlayer.setTrackSelectionParameters(exoPlayer.getTrackSelectionParameters().buildUpon().clearOverridesOfType(3).setOverrideForType(new TrackSelectionOverride(((Tracks.Group) groups.get(subtitle2.getGroupIndex().intValue())).getMediaTrackGroup(), subtitle2.getTrackIndex().intValue())).setTrackTypeDisabled(3, false).build());
            }
        }
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((PlayerScreenKt$PlayerScreen$30$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
