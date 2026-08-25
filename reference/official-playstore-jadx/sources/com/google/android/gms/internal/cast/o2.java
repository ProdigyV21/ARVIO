package com.google.android.gms.internal.cast;

import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Build;
import com.google.android.gms.cast.internal.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: loaded from: classes4.dex */
public final class o2 implements n2 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final Logger f13509l = new Logger("ConnectivityMonitor");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u9 f13510a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ConnectivityManager f13512c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f13515f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Context f13516g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f13518i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final androidx.appcompat.app.n0 f13519j;
    public final HashSet k;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final androidx.work.impl.constraints.trackers.h f13511b = new androidx.work.impl.constraints.trackers.h(this, 1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashMap f13513d = new HashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f13514e = new ArrayList();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Object f13517h = new Object();

    public o2(Context context, u9 u9Var) {
        this.f13518i = Build.VERSION.SDK_INT >= 31;
        this.f13519j = new androidx.appcompat.app.n0(this, 5);
        this.k = new HashSet();
        this.f13510a = u9Var;
        this.f13516g = context;
        this.f13512c = (ConnectivityManager) context.getSystemService("connectivity");
    }

    public final void a() {
        ConnectivityManager connectivityManager;
        LinkProperties linkProperties;
        f13509l.d("Start monitoring connectivity changes", new Object[0]);
        if (this.f13515f || (connectivityManager = this.f13512c) == null) {
            return;
        }
        Context context = this.f13516g;
        if (qb.d.f(context, "android.permission.ACCESS_NETWORK_STATE") == 0) {
            Network activeNetwork = connectivityManager.getActiveNetwork();
            if (activeNetwork != null && (linkProperties = connectivityManager.getLinkProperties(activeNetwork)) != null) {
                b(activeNetwork, linkProperties);
            }
            NetworkRequest.Builder builderAddTransportType = new NetworkRequest.Builder().addTransportType(1);
            if (this.f13518i) {
                builderAddTransportType.setIncludeOtherUidNetworks(true);
            } else {
                context.registerReceiver(this.f13519j, new IntentFilter("android.net.wifi.STATE_CHANGE"));
            }
            connectivityManager.registerNetworkCallback(builderAddTransportType.build(), this.f13511b);
            this.f13515f = true;
        }
    }

    public final void b(Network network, LinkProperties linkProperties) {
        synchronized (this.f13517h) {
            try {
                f13509l.d("a new network is available", new Object[0]);
                HashMap map = this.f13513d;
                if (map.containsKey(network)) {
                    this.f13514e.remove(network);
                }
                map.put(network, linkProperties);
                this.f13514e.add(network);
            } catch (Throwable th) {
                throw th;
            }
        }
        c();
    }

    public final void c() {
        u9 u9Var = this.f13510a;
        if (u9Var == null) {
            return;
        }
        HashSet<m2> hashSet = this.k;
        synchronized (hashSet) {
            try {
                for (m2 m2Var : hashSet) {
                    if (!u9Var.isShutdown()) {
                        u9Var.execute(new w0(this, m2Var, 1));
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
