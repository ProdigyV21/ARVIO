package androidx.work.impl.utils;

import android.net.ConnectivityManager;
import android.net.Network;

/* JADX INFO: loaded from: classes3.dex */
public abstract class h {
    public static final Network a(ConnectivityManager connectivityManager) {
        return connectivityManager.getActiveNetwork();
    }
}
