package io.sentry.android.core;

import android.net.NetworkCapabilities;
import android.os.Build;

/* JADX INFO: loaded from: classes5.dex */
public final class f1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f16479a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f16480b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f16481c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f16482d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f16483e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f16484f;

    public f1(NetworkCapabilities networkCapabilities, r0 r0Var, long j10) {
        ac.b.V(networkCapabilities, "NetworkCapabilities is required");
        ac.b.V(r0Var, "BuildInfoProvider is required");
        this.f16479a = networkCapabilities.getLinkDownstreamBandwidthKbps();
        this.f16480b = networkCapabilities.getLinkUpstreamBandwidthKbps();
        int signalStrength = Build.VERSION.SDK_INT >= 29 ? networkCapabilities.getSignalStrength() : 0;
        this.f16481c = signalStrength > -100 ? signalStrength : 0;
        this.f16483e = networkCapabilities.hasTransport(4);
        String strX = io.sentry.android.core.internal.util.c.x(networkCapabilities);
        this.f16484f = strX == null ? "" : strX;
        this.f16482d = j10;
    }
}
