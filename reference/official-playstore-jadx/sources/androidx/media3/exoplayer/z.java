package androidx.media3.exoplayer;

import androidx.media3.common.FlagSet;
import androidx.media3.common.Player;
import androidx.media3.common.util.BackgroundThreadStateHandler;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.ExoPlayerImplInternal;
import androidx.media3.exoplayer.SuitableOutputChecker;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class z implements ListenerSet.Event, ListenerSet.IterationFinishedEvent, ExoPlayerImplInternal.PlaybackInfoUpdateListener, BackgroundThreadStateHandler.StateChangeListener, SuitableOutputChecker.Callback {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3890i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ ExoPlayerImpl f3891l;

    public /* synthetic */ z(ExoPlayerImpl exoPlayerImpl, int i10) {
        this.f3890i = i10;
        this.f3891l = exoPlayerImpl;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public void invoke(Object obj) {
        switch (this.f3890i) {
            case 0:
                this.f3891l.lambda$setPlaylistMetadata$11((Player.Listener) obj);
                break;
            default:
                this.f3891l.lambda$updateAvailableCommands$31((Player.Listener) obj);
                break;
        }
    }

    @Override // androidx.media3.exoplayer.ExoPlayerImplInternal.PlaybackInfoUpdateListener
    public void onPlaybackInfoUpdate(ExoPlayerImplInternal.PlaybackInfoUpdate playbackInfoUpdate) {
        this.f3891l.lambda$new$2(playbackInfoUpdate);
    }

    @Override // androidx.media3.exoplayer.SuitableOutputChecker.Callback
    public void onSelectedOutputSuitabilityChanged(boolean z) {
        this.f3891l.onSelectedOutputSuitabilityChanged(z);
    }

    @Override // androidx.media3.common.util.BackgroundThreadStateHandler.StateChangeListener
    public void onStateChanged(Object obj, Object obj2) {
        this.f3891l.onAudioSessionIdChanged(((Integer) obj).intValue(), ((Integer) obj2).intValue());
    }

    @Override // androidx.media3.common.util.ListenerSet.IterationFinishedEvent
    public void invoke(Object obj, FlagSet flagSet) {
        this.f3891l.lambda$new$0((Player.Listener) obj, flagSet);
    }
}
