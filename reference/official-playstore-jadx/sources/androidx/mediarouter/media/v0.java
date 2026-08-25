package androidx.mediarouter.media;

import android.media.MediaRouter;

/* JADX INFO: loaded from: classes3.dex */
public final class v0 extends u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MediaRouter.RouteInfo f4490a;

    public v0(MediaRouter.RouteInfo routeInfo) {
        this.f4490a = routeInfo;
    }

    @Override // androidx.mediarouter.media.u
    public final void f(int i10) {
        this.f4490a.requestSetVolume(i10);
    }

    @Override // androidx.mediarouter.media.u
    public final void i(int i10) {
        this.f4490a.requestUpdateVolume(i10);
    }
}
