package com.google.android.gms.internal.auth;

import android.os.Parcelable;
import com.google.android.gms.auth.zzd;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class c extends com.google.android.gms.common.api.n implements z2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final com.google.android.gms.common.api.i f13002a = new com.google.android.gms.common.api.i("GoogleAuthService.API", new j4(0), new com.google.android.gms.common.api.h());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final v3.a f13003b = zzd.zza("GoogleAuthServiceClient");

    public static void c(Status status, Parcelable parcelable, com.google.android.gms.tasks.i iVar) {
        if (status.d() ? iVar.d(parcelable) : iVar.c(com.google.android.gms.common.internal.t.m(status))) {
            return;
        }
        f13003b.c("The task is already complete.", new Object[0]);
    }
}
