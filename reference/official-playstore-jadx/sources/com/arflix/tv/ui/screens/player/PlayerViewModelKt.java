package com.arflix.tv.ui.screens.player;

import androidx.fragment.app.a2;
import com.arflix.tv.data.model.Addon;
import com.arflix.tv.data.model.AddonType;
import com.arflix.tv.data.model.SportsAddonCapabilities;
import com.arflix.tv.data.model.StreamSource;
import com.arflix.tv.data.repository.HomeServerRepository;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0013\u0010\u0006\u001a\u00020\u0002*\u00020\u0005H\u0002¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\f\"\u0014\u0010\r\u001a\u00020\u00028\u0002X\u0082T¢\u0006\u0006\n\u0004\b\r\u0010\u000e\"\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/arflix/tv/data/model/StreamSource;", "stream", "", "isSupplementalStream", "(Lcom/arflix/tv/data/model/StreamSource;)Z", "Lcom/arflix/tv/data/model/Addon;", "isVodStreamingAddon", "(Lcom/arflix/tv/data/model/Addon;)Z", "", "message", "Lx6/t0;", "playbackDiag", "(Ljava/lang/String;)V", "PLAYBACK_DIAGNOSTICS", "Z", "", "MAX_PRELOAD_SUBS", "I", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class PlayerViewModelKt {
    private static final int MAX_PRELOAD_SUBS = 15;
    private static final boolean PLAYBACK_DIAGNOSTICS = true;

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isSupplementalStream(StreamSource streamSource) {
        return kotlin.jvm.internal.p.a(streamSource.getAddonId(), "iptv_xtream_vod") || kotlin.jvm.internal.p.a(streamSource.getAddonId(), HomeServerRepository.ADDON_ID);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isVodStreamingAddon(Addon addon) {
        return (!addon.isEnabled() || addon.getType() == AddonType.SUBTITLE || SportsAddonCapabilities.INSTANCE.isSportsOnlyLiveTvAddon(addon)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void playbackDiag(String str) {
        a2.v("[PlaybackDiag] ", str, System.err);
    }
}
