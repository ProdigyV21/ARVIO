package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Scope;

/* JADX INFO: loaded from: classes4.dex */
public final class j extends t3.a {
    public static final Parcelable.Creator<j> CREATOR = new android.support.v4.media.d(20);

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final Scope[] f12863y = new Scope[0];
    public static final o3.d[] z = new o3.d[0];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f12864i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f12865l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f12866m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f12867n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public IBinder f12868o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Scope[] f12869p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Bundle f12870q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Account f12871r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public o3.d[] f12872s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public o3.d[] f12873t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final boolean f12874u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final int f12875v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f12876w;
    public final String x;

    public j(int i10, int i11, int i12, String str, IBinder iBinder, Scope[] scopeArr, Bundle bundle, Account account, o3.d[] dVarArr, o3.d[] dVarArr2, boolean z5, int i13, boolean z10, String str2) {
        scopeArr = scopeArr == null ? f12863y : scopeArr;
        bundle = bundle == null ? new Bundle() : bundle;
        o3.d[] dVarArr3 = z;
        dVarArr = dVarArr == null ? dVarArr3 : dVarArr;
        dVarArr2 = dVarArr2 == null ? dVarArr3 : dVarArr2;
        this.f12864i = i10;
        this.f12865l = i11;
        this.f12866m = i12;
        if ("com.google.android.gms".equals(str)) {
            this.f12867n = "com.google.android.gms";
        } else {
            this.f12867n = str;
        }
        if (i10 < 2) {
            Account accountZzb = null;
            if (iBinder != null) {
                int i14 = a.f12829d;
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
                n a1Var = iInterfaceQueryLocalInterface instanceof n ? (n) iInterfaceQueryLocalInterface : new a1(iBinder, "com.google.android.gms.common.internal.IAccountAccessor", 1);
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    try {
                        accountZzb = a1Var.zzb();
                    } catch (RemoteException unused) {
                        Log.w("AccountAccessor", "Remote account accessor probably died");
                    }
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                }
            }
            this.f12871r = accountZzb;
        } else {
            this.f12868o = iBinder;
            this.f12871r = account;
        }
        this.f12869p = scopeArr;
        this.f12870q = bundle;
        this.f12872s = dVarArr;
        this.f12873t = dVarArr2;
        this.f12874u = z5;
        this.f12875v = i13;
        this.f12876w = z10;
        this.x = str2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        android.support.v4.media.d.a(this, parcel, i10);
    }
}
