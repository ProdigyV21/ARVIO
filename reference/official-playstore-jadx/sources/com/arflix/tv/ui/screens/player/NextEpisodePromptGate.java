package com.arflix.tv.ui.screens.player;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\u0007R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/arflix/tv/ui/screens/player/NextEpisodePromptGate;", "", "<init>", "()V", "handledEpisode", "Lcom/arflix/tv/ui/screens/player/PlaybackEpisodeKey;", "tryOpen", "", "episode", "eligible", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class NextEpisodePromptGate {
    public static final int $stable = 8;
    private PlaybackEpisodeKey handledEpisode;

    public final boolean tryOpen(PlaybackEpisodeKey episode, boolean eligible) {
        if (!eligible || episode == null || kotlin.jvm.internal.p.a(this.handledEpisode, episode)) {
            return false;
        }
        this.handledEpisode = episode;
        return true;
    }
}
