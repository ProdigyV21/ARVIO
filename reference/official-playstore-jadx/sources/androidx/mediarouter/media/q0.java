package androidx.mediarouter.media;

import android.media.MediaRouter;
import android.os.Bundle;
import android.view.Display;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public final class q0 extends MediaRouter.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y0 f4473a;

    public q0(y0 y0Var) {
        this.f4473a = y0Var;
    }

    @Override // android.media.MediaRouter.Callback
    public final void onRouteAdded(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        y0 y0Var = this.f4473a;
        if (y0Var.h(routeInfo)) {
            y0Var.r();
        }
    }

    @Override // android.media.MediaRouter.Callback
    public final void onRouteChanged(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        int i10;
        y0 y0Var = this.f4473a;
        y0Var.getClass();
        if (y0.m(routeInfo) != null || (i10 = y0Var.i(routeInfo)) < 0) {
            return;
        }
        w0 w0Var = (w0) y0Var.A.get(i10);
        androidx.emoji2.text.e0 e0Var = new androidx.emoji2.text.e0(w0Var.f4502b, y0Var.l(w0Var.f4501a));
        y0Var.n(w0Var, e0Var);
        w0Var.f4503c = e0Var.p();
        y0Var.r();
    }

    @Override // android.media.MediaRouter.Callback
    public final void onRouteGrouped(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo, MediaRouter.RouteGroup routeGroup, int i10) {
        this.f4473a.getClass();
    }

    @Override // android.media.MediaRouter.Callback
    public final void onRoutePresentationDisplayChanged(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        y0 y0Var = this.f4473a;
        int i10 = y0Var.i(routeInfo);
        if (i10 >= 0) {
            w0 w0Var = (w0) y0Var.A.get(i10);
            Display presentationDisplay = routeInfo.getPresentationDisplay();
            int displayId = presentationDisplay != null ? presentationDisplay.getDisplayId() : -1;
            if (displayId != w0Var.f4503c.f4440a.getInt("presentationDisplayId", -1)) {
                m mVar = w0Var.f4503c;
                new ArrayList();
                new ArrayList();
                new HashSet();
                if (mVar == null) {
                    throw new IllegalArgumentException("descriptor must not be null");
                }
                Bundle bundle = new Bundle(mVar.f4440a);
                ArrayList arrayListC = mVar.c();
                ArrayList arrayListB = mVar.b();
                HashSet hashSetA = mVar.a();
                bundle.putInt("presentationDisplayId", displayId);
                bundle.putParcelableArrayList("controlFilters", new ArrayList<>(arrayListB));
                bundle.putStringArrayList("groupMemberIds", new ArrayList<>(arrayListC));
                bundle.putStringArrayList("allowedPackages", new ArrayList<>(hashSetA));
                w0Var.f4503c = new m(bundle);
                y0Var.r();
            }
        }
    }

    @Override // android.media.MediaRouter.Callback
    public final void onRouteRemoved(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        int i10;
        y0 y0Var = this.f4473a;
        y0Var.getClass();
        if (y0.m(routeInfo) != null || (i10 = y0Var.i(routeInfo)) < 0) {
            return;
        }
        y0Var.A.remove(i10);
        y0Var.r();
    }

    @Override // android.media.MediaRouter.Callback
    public final void onRouteSelected(MediaRouter mediaRouter, int i10, MediaRouter.RouteInfo routeInfo) {
        i0 i0Var;
        y0 y0Var = this.f4473a;
        if (routeInfo != y0Var.f4511t.getSelectedRoute(8388611)) {
            return;
        }
        x0 x0VarM = y0.m(routeInfo);
        if (x0VarM != null) {
            x0VarM.f4508a.g(false);
            return;
        }
        int i11 = y0Var.i(routeInfo);
        if (i11 >= 0) {
            w0 w0Var = (w0) y0Var.A.get(i11);
            f fVar = y0Var.f4510s;
            String str = w0Var.f4502b;
            fVar.f4326a.removeMessages(262);
            g0 g0VarD = fVar.d(fVar.f4344t);
            if (g0VarD != null) {
                Iterator it = g0VarD.f4363b.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        i0Var = null;
                        break;
                    } else {
                        i0Var = (i0) it.next();
                        if (i0Var.f4387b.equals(str)) {
                            break;
                        }
                    }
                }
                if (i0Var != null) {
                    i0Var.g(false);
                }
            }
        }
    }

    @Override // android.media.MediaRouter.Callback
    public final void onRouteUngrouped(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo, MediaRouter.RouteGroup routeGroup) {
        this.f4473a.getClass();
    }

    @Override // android.media.MediaRouter.Callback
    public final void onRouteUnselected(MediaRouter mediaRouter, int i10, MediaRouter.RouteInfo routeInfo) {
        this.f4473a.getClass();
    }

    @Override // android.media.MediaRouter.Callback
    public final void onRouteVolumeChanged(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        int i10;
        y0 y0Var = this.f4473a;
        y0Var.getClass();
        if (y0.m(routeInfo) != null || (i10 = y0Var.i(routeInfo)) < 0) {
            return;
        }
        w0 w0Var = (w0) y0Var.A.get(i10);
        int volume = routeInfo.getVolume();
        if (volume != w0Var.f4503c.f4440a.getInt("volume")) {
            m mVar = w0Var.f4503c;
            new ArrayList();
            new ArrayList();
            new HashSet();
            if (mVar == null) {
                throw new IllegalArgumentException("descriptor must not be null");
            }
            Bundle bundle = new Bundle(mVar.f4440a);
            ArrayList arrayListC = mVar.c();
            ArrayList arrayListB = mVar.b();
            HashSet hashSetA = mVar.a();
            bundle.putInt("volume", volume);
            bundle.putParcelableArrayList("controlFilters", new ArrayList<>(arrayListB));
            bundle.putStringArrayList("groupMemberIds", new ArrayList<>(arrayListC));
            bundle.putStringArrayList("allowedPackages", new ArrayList<>(hashSetA));
            w0Var.f4503c = new m(bundle);
            y0Var.r();
        }
    }
}
