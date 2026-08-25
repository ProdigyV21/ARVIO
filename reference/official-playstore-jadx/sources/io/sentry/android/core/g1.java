package io.sentry.android.core;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import io.sentry.a5;
import io.sentry.j4;
import io.sentry.w5;

/* JADX INFO: loaded from: classes5.dex */
public final class g1 extends ConnectivityManager.NetworkCallback {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r0 f16491b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a5 f16494e;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public NetworkCapabilities f16492c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f16493d = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j4 f16490a = j4.f17401a;

    public g1(r0 r0Var, a5 a5Var) {
        ac.b.V(r0Var, "BuildInfoProvider is required");
        this.f16491b = r0Var;
        ac.b.V(a5Var, "SentryDateProvider is required");
        this.f16494e = a5Var;
    }

    public static io.sentry.f a(String str) {
        io.sentry.f fVar = new io.sentry.f();
        fVar.f17247o = "system";
        fVar.f17249q = "network.event";
        fVar.f(str, "action");
        fVar.f17251s = w5.INFO;
        return fVar;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onAvailable(Network network) {
        this.f16490a.k(a("NETWORK_AVAILABLE"));
        this.f16492c = null;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        f1 f1Var;
        long jI = this.f16494e.a().i();
        NetworkCapabilities networkCapabilities2 = this.f16492c;
        long j10 = this.f16493d;
        r0 r0Var = this.f16491b;
        if (networkCapabilities2 == null) {
            f1Var = new f1(networkCapabilities, r0Var, jI);
        } else {
            f1 f1Var2 = new f1(networkCapabilities2, r0Var, j10);
            f1Var = new f1(networkCapabilities, r0Var, jI);
            int iAbs = Math.abs(f1Var2.f16481c - f1Var.f16481c);
            int i10 = f1Var.f16479a;
            int i11 = f1Var2.f16479a;
            int iAbs2 = Math.abs(i11 - i10);
            int i12 = f1Var.f16480b;
            int i13 = f1Var2.f16480b;
            int iAbs3 = Math.abs(i13 - i12);
            boolean z = ((double) Math.abs(f1Var2.f16482d - f1Var.f16482d)) / 1000000.0d < 5000.0d;
            boolean z5 = z || iAbs <= 5;
            boolean z10 = z || ((double) iAbs2) <= Math.max(1000.0d, ((double) Math.abs(i11)) * 0.1d);
            boolean z11 = z || ((double) iAbs3) <= Math.max(1000.0d, ((double) Math.abs(i13)) * 0.1d);
            if (f1Var2.f16483e == f1Var.f16483e && f1Var2.f16484f.equals(f1Var.f16484f) && z5 && z10 && z11) {
                f1Var = null;
            }
        }
        if (f1Var == null) {
            return;
        }
        this.f16492c = networkCapabilities;
        this.f16493d = jI;
        io.sentry.f fVarA = a("NETWORK_CAPABILITIES_CHANGED");
        fVarA.f(Integer.valueOf(f1Var.f16479a), "download_bandwidth");
        fVarA.f(Integer.valueOf(f1Var.f16480b), "upload_bandwidth");
        fVarA.f(Boolean.valueOf(f1Var.f16483e), "vpn_active");
        fVarA.f(f1Var.f16484f, "network_type");
        int i14 = f1Var.f16481c;
        if (i14 != 0) {
            fVarA.f(Integer.valueOf(i14), "signal_strength");
        }
        io.sentry.j0 j0Var = new io.sentry.j0();
        j0Var.d("android:networkCapabilities", f1Var);
        this.f16490a.h(fVarA, j0Var);
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onLost(Network network) {
        this.f16490a.k(a("NETWORK_LOST"));
        this.f16492c = null;
    }
}
