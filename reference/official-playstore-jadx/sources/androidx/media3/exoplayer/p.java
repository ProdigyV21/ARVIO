package androidx.media3.exoplayer;

import androidx.media3.common.AudioAttributes;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.Player;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.Cue;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.BackgroundThreadStateHandler;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.ExoPlayerImpl;
import androidx.media3.exoplayer.MediaPeriodHolder;
import androidx.media3.exoplayer.StreamVolumeManager;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class p implements ListenerSet.Event, MediaPeriodHolder.Factory, BackgroundThreadStateHandler.StateChangeListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3726i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3727l;

    public /* synthetic */ p(Object obj, int i10) {
        this.f3726i = i10;
        this.f3727l = obj;
    }

    @Override // androidx.media3.exoplayer.MediaPeriodHolder.Factory
    public MediaPeriodHolder create(MediaPeriodInfo mediaPeriodInfo, long j10) {
        return ((ExoPlayerImplInternal) this.f3727l).createMediaPeriodHolder(mediaPeriodInfo, j10);
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public void invoke(Object obj) {
        switch (this.f3726i) {
            case 0:
                ((Player.Listener) obj).onMediaMetadataChanged((MediaMetadata) this.f3727l);
                break;
            case 1:
                ((Player.Listener) obj).onAudioAttributesChanged((AudioAttributes) this.f3727l);
                break;
            case 2:
                ((Player.Listener) obj).onTrackSelectionParametersChanged((TrackSelectionParameters) this.f3727l);
                break;
            case 3:
                ((Player.Listener) obj).onCues((CueGroup) this.f3727l);
                break;
            case 4:
                ((ExoPlayerImpl.ComponentListener) this.f3727l).lambda$onMetadata$6((Player.Listener) obj);
                break;
            case 5:
                ((Player.Listener) obj).onMetadata((Metadata) this.f3727l);
                break;
            case 6:
                ((Player.Listener) obj).onCues((List<Cue>) this.f3727l);
                break;
            case 7:
                ((Player.Listener) obj).onVideoSizeChanged((VideoSize) this.f3727l);
                break;
            default:
                ((Player.Listener) obj).onDeviceInfoChanged((DeviceInfo) this.f3727l);
                break;
        }
    }

    @Override // androidx.media3.common.util.BackgroundThreadStateHandler.StateChangeListener
    public void onStateChanged(Object obj, Object obj2) {
        ((StreamVolumeManager) this.f3727l).onStreamVolumeStateChanged((StreamVolumeManager.StreamVolumeState) obj, (StreamVolumeManager.StreamVolumeState) obj2);
    }
}
