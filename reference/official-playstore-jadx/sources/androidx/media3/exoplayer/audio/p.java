package androidx.media3.exoplayer.audio;

import android.media.AudioDeviceInfo;
import android.media.AudioRouting;
import androidx.media3.exoplayer.CodecParameters;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioTrackAudioOutput;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class p implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3573i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3574l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3575m;

    public /* synthetic */ p(Object obj, Object obj2, int i10) {
        this.f3573i = i10;
        this.f3574l = obj;
        this.f3575m = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3573i) {
            case 0:
                ((AudioTrackAudioOutput.OnRoutingChangedListenerApi24) this.f3574l).lambda$onRoutingChanged$0((AudioDeviceInfo) this.f3575m);
                break;
            case 1:
                ((AudioTrackAudioOutput.OnRoutingChangedListenerApi24) this.f3574l).lambda$onRoutingChanged$1((AudioRouting) this.f3575m);
                break;
            case 2:
                ((AudioRendererEventListener.EventDispatcher) this.f3574l).lambda$audioCodecParametersChanged$13((CodecParameters) this.f3575m);
                break;
            default:
                ((AudioRendererEventListener.EventDispatcher) this.f3574l).lambda$decoderReleased$5((String) this.f3575m);
                break;
        }
    }
}
