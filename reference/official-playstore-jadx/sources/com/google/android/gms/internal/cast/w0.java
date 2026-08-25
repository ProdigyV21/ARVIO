package com.google.android.gms.internal.cast;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class w0 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f13648i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f13649l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f13650m;

    public /* synthetic */ w0(Object obj, Object obj2, int i10) {
        this.f13648i = i10;
        this.f13649l = obj;
        this.f13650m = obj2;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        boolean zIsEmpty;
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        switch (this.f13648i) {
            case 0:
                ((x0) this.f13649l).f0((androidx.mediarouter.media.z) this.f13650m);
                return;
            default:
                o2 o2Var = (o2) this.f13649l;
                m2 m2Var = (m2) this.f13650m;
                synchronized (o2Var.f13517h) {
                    zIsEmpty = o2Var.f13514e.isEmpty();
                    break;
                }
                if (!zIsEmpty && !o2Var.f13518i && (connectivityManager = o2Var.f13512c) != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null) {
                    activeNetworkInfo.isConnected();
                }
                m2Var.zza();
                return;
        }
    }
}
