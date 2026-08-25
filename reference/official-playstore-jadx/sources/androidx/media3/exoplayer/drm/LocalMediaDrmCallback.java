package androidx.media3.exoplayer.drm;

import androidx.media3.exoplayer.drm.ExoMediaDrm;
import androidx.media3.exoplayer.drm.MediaDrmCallback;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public final class LocalMediaDrmCallback implements MediaDrmCallback {
    private final MediaDrmCallback.Response keyResponse;

    public LocalMediaDrmCallback(byte[] bArr) {
        bArr.getClass();
        this.keyResponse = new MediaDrmCallback.Response(bArr);
    }

    @Override // androidx.media3.exoplayer.drm.MediaDrmCallback
    public MediaDrmCallback.Response executeKeyRequest(UUID uuid, ExoMediaDrm.KeyRequest keyRequest) {
        return this.keyResponse;
    }

    @Override // androidx.media3.exoplayer.drm.MediaDrmCallback
    public MediaDrmCallback.Response executeProvisionRequest(UUID uuid, ExoMediaDrm.ProvisionRequest provisionRequest) {
        throw new UnsupportedOperationException();
    }
}
