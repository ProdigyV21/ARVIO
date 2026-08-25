package com.google.android.gms.internal.cast;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.cast.internal.Logger;

/* JADX INFO: loaded from: classes4.dex */
public final class h1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Logger f13273d = new Logger("AnalyticsConsent");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final com.google.android.gms.internal.auth.f0 f13274a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f13275b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final androidx.loader.content.j f13276c = new androidx.loader.content.j(Looper.getMainLooper(), 2);

    public h1(Context context, long j10) {
        this.f13274a = new com.google.android.gms.internal.auth.f0(context, null, p3.f13539a, new o3(), com.google.android.gms.common.api.m.f12824c);
        this.f13275b = j10;
    }
}
