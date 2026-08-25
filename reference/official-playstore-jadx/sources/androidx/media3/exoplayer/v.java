package androidx.media3.exoplayer;

import androidx.media3.exoplayer.ExoPlayerImpl;
import androidx.media3.exoplayer.StreamVolumeManager;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class v implements com.google.common.base.m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3846a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f3847b;

    public /* synthetic */ v(int i10, int i11) {
        this.f3846a = i11;
        this.f3847b = i10;
    }

    @Override // com.google.common.base.m
    public final Object apply(Object obj) {
        switch (this.f3846a) {
            case 0:
                return ExoPlayerImpl.lambda$setAudioSessionId$13(this.f3847b, (Integer) obj);
            case 1:
                return ExoPlayerImpl.ComponentListener.lambda$onAudioSessionIdChanged$2(this.f3847b, (Integer) obj);
            case 2:
                return ExoPlayerImpl.ComponentListener.lambda$onAudioSessionIdChanged$3(this.f3847b, (Integer) obj);
            case 3:
                return StreamVolumeManager.lambda$setVolume$3(this.f3847b, (StreamVolumeManager.StreamVolumeState) obj);
            default:
                return StreamVolumeManager.lambda$setStreamType$1(this.f3847b, (StreamVolumeManager.StreamVolumeState) obj);
        }
    }
}
