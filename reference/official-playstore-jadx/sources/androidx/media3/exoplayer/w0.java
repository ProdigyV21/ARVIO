package androidx.media3.exoplayer;

import androidx.media3.exoplayer.StreamVolumeManager;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class w0 implements com.google.common.base.m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3878a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f3879b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f3880c;

    public /* synthetic */ w0(Object obj, int i10, int i11) {
        this.f3878a = i11;
        this.f3879b = obj;
        this.f3880c = i10;
    }

    @Override // com.google.common.base.m
    public final Object apply(Object obj) {
        switch (this.f3878a) {
            case 0:
                return ((StreamVolumeManager) this.f3879b).lambda$increaseVolume$6(this.f3880c, (StreamVolumeManager.StreamVolumeState) obj);
            case 1:
                return ((StreamVolumeManager) this.f3879b).lambda$decreaseVolume$8(this.f3880c, (StreamVolumeManager.StreamVolumeState) obj);
            case 2:
                return ((StreamVolumeManager) this.f3879b).lambda$setStreamType$2(this.f3880c, (StreamVolumeManager.StreamVolumeState) obj);
            default:
                return ((ExoPlayerImpl) this.f3879b).lambda$setAudioSessionId$14(this.f3880c, (Integer) obj);
        }
    }
}
