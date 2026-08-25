package androidx.media3.session;

import android.view.KeyEvent;
import androidx.media3.common.MediaItem;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;
import androidx.media3.session.MediaSessionStub;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class s1 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4145i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f4146l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f4147m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f4148n;

    public /* synthetic */ s1(Object obj, Object obj2, Object obj3, int i10) {
        this.f4145i = i10;
        this.f4146l = obj;
        this.f4147m = obj2;
        this.f4148n = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f4145i) {
            case 0:
                ((MediaLibraryServiceLegacyStub) this.f4146l).lambda$onUnsubscribe$2((MediaSession.ControllerInfo) this.f4147m, (String) this.f4148n);
                break;
            case 1:
                MediaLibraryServiceLegacyStub.lambda$createMediaItemToBrowserItemAsyncFunction$14((com.google.common.util.concurrent.d1) this.f4146l, (com.google.common.util.concurrent.q1) this.f4147m, (MediaItem) this.f4148n);
                break;
            case 2:
                ((MediaLibrarySessionImpl) this.f4146l).lambda$onUnsubscribeOnHandler$3((MediaSession.ControllerInfo) this.f4147m, (String) this.f4148n);
                break;
            case 3:
                ((MediaSessionImpl) this.f4146l).lambda$callWithControllerForCurrentRequestSet$3((MediaSession.ControllerInfo) this.f4147m, (Runnable) this.f4148n);
                break;
            case 4:
                ((MediaSessionImpl.MediaPlayPauseKeyHandler) this.f4146l).lambda$setPendingPlayPauseTask$0((MediaSession.ControllerInfo) this.f4147m, (KeyEvent) this.f4148n);
                break;
            default:
                MediaSessionStub.lambda$handleMediaItemsWithStartPositionWhenReady$7((MediaSessionImpl) this.f4146l, (MediaSessionStub.MediaItemsWithStartPositionPlayerTask) this.f4147m, (MediaSession.MediaItemsWithStartPosition) this.f4148n);
                break;
        }
    }
}
