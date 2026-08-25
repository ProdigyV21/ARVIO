package androidx.work.impl.utils;

import android.net.ConnectivityManager;

/* JADX INFO: loaded from: classes3.dex */
public abstract class i {
    public static final void a(ConnectivityManager connectivityManager, ConnectivityManager.NetworkCallback networkCallback) {
        connectivityManager.registerDefaultNetworkCallback(networkCallback);
    }
}
