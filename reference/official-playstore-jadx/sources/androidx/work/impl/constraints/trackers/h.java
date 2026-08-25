package androidx.work.impl.constraints.trackers;

import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import com.google.android.gms.internal.cast.o2;
import m2.t;

/* JADX INFO: loaded from: classes3.dex */
public final class h extends ConnectivityManager.NetworkCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6833a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f6834b;

    public /* synthetic */ h(Object obj, int i10) {
        this.f6833a = i10;
        this.f6834b = obj;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onAvailable(Network network) {
        switch (this.f6833a) {
            case 1:
                break;
            default:
                super.onAvailable(network);
                break;
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        switch (this.f6833a) {
            case 0:
                t.d().a(j.f6837a, "Network capabilities changed: " + networkCapabilities);
                i iVar = (i) this.f6834b;
                iVar.b(j.a(iVar.f6835f));
                break;
            default:
                super.onCapabilitiesChanged(network, networkCapabilities);
                break;
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
        switch (this.f6833a) {
            case 1:
                ((o2) this.f6834b).b(network, linkProperties);
                break;
            default:
                super.onLinkPropertiesChanged(network, linkProperties);
                break;
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onLost(Network network) {
        switch (this.f6833a) {
            case 0:
                t.d().a(j.f6837a, "Network connection lost");
                i iVar = (i) this.f6834b;
                iVar.b(j.a(iVar.f6835f));
                return;
            default:
                o2 o2Var = (o2) this.f6834b;
                synchronized (o2Var.f13517h) {
                    try {
                        o2.f13509l.d("the network is lost", new Object[0]);
                        if (o2Var.f13514e.remove(network)) {
                            o2Var.f13513d.remove(network);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                    break;
                }
                o2Var.c();
                return;
        }
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public void onUnavailable() {
        switch (this.f6833a) {
            case 1:
                o2 o2Var = (o2) this.f6834b;
                synchronized (o2Var.f13517h) {
                    o2.f13509l.d("all networks are unavailable.", new Object[0]);
                    o2Var.f13513d.clear();
                    o2Var.f13514e.clear();
                    break;
                }
                o2Var.c();
                return;
            default:
                super.onUnavailable();
                return;
        }
    }

    private final void a(Network network) {
    }
}
