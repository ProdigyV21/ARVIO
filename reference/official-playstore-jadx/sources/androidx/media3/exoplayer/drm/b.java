package androidx.media3.exoplayer.drm;

import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class b implements Consumer {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3596i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3597l;

    public /* synthetic */ b(Object obj, int i10) {
        this.f3596i = i10;
        this.f3597l = obj;
    }

    @Override // androidx.media3.common.util.Consumer
    public final void accept(Object obj) {
        switch (this.f3596i) {
            case 0:
                ((DrmSessionEventListener.EventDispatcher) obj).drmKeysLoaded((KeyRequestInfo) this.f3597l);
                break;
            default:
                DefaultDrmSession.lambda$onError$2((Exception) this.f3597l, (DrmSessionEventListener.EventDispatcher) obj);
                break;
        }
    }
}
