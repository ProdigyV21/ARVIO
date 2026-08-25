package androidx.mediarouter.media;

import android.media.MediaRoute2Info;
import android.media.MediaRouter2;
import android.media.MediaRouter2$TransferCallback;
import android.text.TextUtils;
import android.util.Log;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class k extends MediaRouter2$TransferCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ l f4424a;

    public k(l lVar) {
        this.f4424a = lVar;
    }

    public final void onStop(MediaRouter2.RoutingController routingController) {
        l lVar = this.f4424a;
        u uVar = (u) lVar.f4431u.remove(routingController);
        if (uVar == null) {
            Log.w("MR2Provider", "onStop: No matching routeController found. routingController=" + routingController);
            return;
        }
        f fVar = lVar.f4430t.f4298a;
        if (uVar != fVar.f4330e) {
            int i10 = f.F;
            return;
        }
        i0 i0VarC = fVar.c();
        if (fVar.g() != i0VarC) {
            fVar.m(i0VarC, 2, true);
        }
    }

    public final void onTransfer(MediaRouter2.RoutingController routingController, MediaRouter2.RoutingController routingController2) {
        i0 i0Var;
        this.f4424a.f4431u.remove(routingController);
        if (routingController2 == this.f4424a.f4429s.getSystemController()) {
            f fVar = this.f4424a.f4430t.f4298a;
            i0 i0VarC = fVar.c();
            if (fVar.g() != i0VarC) {
                fVar.m(i0VarC, 3, true);
                return;
            }
            return;
        }
        List<MediaRoute2Info> selectedRoutes = routingController2.getSelectedRoutes();
        if (selectedRoutes.isEmpty()) {
            Log.w("MR2Provider", "Selected routes are empty. This shouldn't happen.");
            return;
        }
        String id = androidx.media3.exoplayer.w.a(selectedRoutes.get(0)).getId();
        this.f4424a.f4431u.put(routingController2, new h(routingController2, id));
        f fVar2 = this.f4424a.f4430t.f4298a;
        Iterator it = fVar2.f4335j.iterator();
        while (true) {
            if (!it.hasNext()) {
                i0Var = null;
                break;
            }
            i0Var = (i0) it.next();
            if (i0Var.a() == fVar2.f4343s && TextUtils.equals(id, i0Var.f4387b)) {
                break;
            }
        }
        if (i0Var == null) {
            androidx.compose.material3.d.y("onSelectRoute: The target RouteInfo is not found for descriptorId=", id, "AxMediaRouter");
        } else {
            fVar2.m(i0Var, 3, true);
        }
        this.f4424a.k(routingController2);
    }

    public final void onTransferFailure(MediaRoute2Info mediaRoute2Info) {
        Log.w("MR2Provider", "Transfer failed. requestedRoute=" + mediaRoute2Info);
    }
}
