package androidx.work.impl.constraints.trackers;

import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import m2.t;

/* JADX INFO: loaded from: classes3.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f6837a = t.f("NetworkStateTracker");

    public static final androidx.work.impl.constraints.e a(ConnectivityManager connectivityManager) {
        NetworkCapabilities networkCapabilitiesA;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean z = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        try {
            networkCapabilitiesA = androidx.work.impl.utils.g.a(connectivityManager, androidx.work.impl.utils.h.a(connectivityManager));
        } catch (SecurityException e5) {
            t.d().c(f6837a, "Unable to validate active network", e5);
        }
        boolean zB = networkCapabilitiesA != null ? androidx.work.impl.utils.g.b(networkCapabilitiesA, 16) : false;
        return new androidx.work.impl.constraints.e(z, zB, connectivityManager.isActiveNetworkMetered(), (activeNetworkInfo == null || activeNetworkInfo.isRoaming()) ? false : true);
    }
}
