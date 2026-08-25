package androidx.media3.exoplayer.drm;

import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.drm.ExoMediaDrm;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a implements Consumer, DrmSessionManager.DrmSessionReference, ExoMediaDrm.Provider {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3595i;

    public /* synthetic */ a(int i10) {
        this.f3595i = i10;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        DrmSessionEventListener.EventDispatcher eventDispatcher = (DrmSessionEventListener.EventDispatcher) obj;
        switch (this.f3595i) {
            case 0:
                eventDispatcher.drmSessionAcquired(3);
                break;
            case 1:
                eventDispatcher.drmKeysRemoved();
                break;
            default:
                eventDispatcher.drmKeysRestored();
                break;
        }
    }

    @Override // androidx.media3.exoplayer.drm.ExoMediaDrm.Provider
    public ExoMediaDrm acquireExoMediaDrm(UUID uuid) {
        return FrameworkMediaDrm.lambda$static$0(uuid);
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionManager.DrmSessionReference
    public void release() {
        i.a();
    }
}
