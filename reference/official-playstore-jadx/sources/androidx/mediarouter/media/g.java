package androidx.mediarouter.media;

import android.media.MediaRouter2;
import android.media.MediaRouter2$ControllerCallback;

/* JADX INFO: loaded from: classes3.dex */
public final class g extends MediaRouter2$ControllerCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ l f4361a;

    public g(l lVar) {
        this.f4361a = lVar;
    }

    public final void onControllerUpdated(MediaRouter2.RoutingController routingController) {
        this.f4361a.k(routingController);
    }
}
