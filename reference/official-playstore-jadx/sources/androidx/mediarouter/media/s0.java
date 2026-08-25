package androidx.mediarouter.media;

import android.media.MediaRouter;

/* JADX INFO: loaded from: classes3.dex */
public final class s0 extends MediaRouter.VolumeCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y0 f4481a;

    public s0(y0 y0Var) {
        this.f4481a = y0Var;
    }

    @Override // android.media.MediaRouter.VolumeCallback
    public final void onVolumeSetRequest(MediaRouter.RouteInfo routeInfo, int i10) {
        this.f4481a.getClass();
        x0 x0VarM = y0.m(routeInfo);
        if (x0VarM != null) {
            i0 i0Var = x0VarM.f4508a;
            i0Var.getClass();
            k0.b();
            f fVarC = k0.c();
            int iMin = Math.min(i0Var.f4401q, Math.max(0, i10));
            u uVarF = fVarC.f(i0Var);
            if (uVarF != null) {
                uVarF.f(iMin);
            }
        }
    }

    @Override // android.media.MediaRouter.VolumeCallback
    public final void onVolumeUpdateRequest(MediaRouter.RouteInfo routeInfo, int i10) {
        u uVarF;
        this.f4481a.getClass();
        x0 x0VarM = y0.m(routeInfo);
        if (x0VarM != null) {
            i0 i0Var = x0VarM.f4508a;
            i0Var.getClass();
            k0.b();
            if (i10 == 0 || (uVarF = k0.c().f(i0Var)) == null) {
                return;
            }
            uVarF.i(i10);
        }
    }
}
