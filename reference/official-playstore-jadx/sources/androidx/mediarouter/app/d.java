package androidx.mediarouter.app;

import android.os.Bundle;
import androidx.mediarouter.media.a0;
import androidx.mediarouter.media.i0;
import androidx.mediarouter.media.k0;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.internal.cast.l2;

/* JADX INFO: loaded from: classes3.dex */
public final class d extends a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4275a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f4276b;

    public /* synthetic */ d(Object obj, int i10) {
        this.f4275a = i10;
        this.f4276b = obj;
    }

    @Override // androidx.mediarouter.media.a0
    public final void onRouteAdded(k0 k0Var, i0 i0Var) {
        switch (this.f4275a) {
            case 0:
                ((g) this.f4276b).e();
                break;
            default:
                l2.f13396m.d("RemoteConnectionMediaRouterCallback.onRouteAdded.", new Object[0]);
                ((l2) this.f4276b).d(i0Var.f4403s);
                break;
        }
    }

    @Override // androidx.mediarouter.media.a0
    public final void onRouteChanged(k0 k0Var, i0 i0Var) {
        switch (this.f4275a) {
            case 0:
                ((g) this.f4276b).e();
                break;
            default:
                l2.f13396m.d("RemoteConnectionMediaRouterCallback.onRouteChanged.", new Object[0]);
                ((l2) this.f4276b).d(i0Var.f4403s);
                break;
        }
    }

    @Override // androidx.mediarouter.media.a0
    public final void onRouteRemoved(k0 k0Var, i0 i0Var) {
        CastDevice fromBundle;
        switch (this.f4275a) {
            case 0:
                ((g) this.f4276b).e();
                return;
            default:
                l2.f13396m.d("RemoteConnectionMediaRouterCallback.onRouteRemoved.", new Object[0]);
                Bundle bundle = i0Var.f4403s;
                if (bundle == null || (fromBundle = CastDevice.getFromBundle(bundle)) == null) {
                    return;
                }
                bundle.getString("com.google.android.gms.cast.EXTRA_RUNNING_RECEIVER_APP_ID");
                l2 l2Var = (l2) this.f4276b;
                if (l2Var.f13402f.get(fromBundle.getDeviceId()) != null) {
                    throw new ClassCastException();
                }
                l2Var.e(fromBundle);
                return;
        }
    }

    @Override // androidx.mediarouter.media.a0
    public void onRouteSelected(k0 k0Var, i0 i0Var) {
        switch (this.f4275a) {
            case 0:
                ((g) this.f4276b).dismiss();
                break;
            default:
                super.onRouteSelected(k0Var, i0Var);
                break;
        }
    }
}
