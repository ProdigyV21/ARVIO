package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes4.dex */
public final class j0 extends t3.a {
    public static final Parcelable.Creator<j0> CREATOR = new android.support.v4.media.d(15);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f12877i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final IBinder f12878l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final o3.b f12879m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f12880n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final boolean f12881o;

    public j0(int i10, IBinder iBinder, o3.b bVar, boolean z, boolean z5) {
        this.f12877i = i10;
        this.f12878l = iBinder;
        this.f12879m = bVar;
        this.f12880n = z;
        this.f12881o = z5;
    }

    public final boolean equals(Object obj) {
        Object a1Var;
        if (obj == null) {
            return false;
        }
        if (this != obj) {
            if (!(obj instanceof j0)) {
                return false;
            }
            j0 j0Var = (j0) obj;
            if (!this.f12879m.equals(j0Var.f12879m)) {
                return false;
            }
            Object a1Var2 = null;
            IBinder iBinder = this.f12878l;
            if (iBinder == null) {
                a1Var = null;
            } else {
                int i10 = a.f12829d;
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
                a1Var = iInterfaceQueryLocalInterface instanceof n ? (n) iInterfaceQueryLocalInterface : new a1(iBinder, "com.google.android.gms.common.internal.IAccountAccessor", 1);
            }
            IBinder iBinder2 = j0Var.f12878l;
            if (iBinder2 != null) {
                int i11 = a.f12829d;
                IInterface iInterfaceQueryLocalInterface2 = iBinder2.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
                a1Var2 = iInterfaceQueryLocalInterface2 instanceof n ? (n) iInterfaceQueryLocalInterface2 : new a1(iBinder2, "com.google.android.gms.common.internal.IAccountAccessor", 1);
            }
            if (!t.l(a1Var, a1Var2)) {
                return false;
            }
        }
        return true;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(this.f12877i);
        a.a.b0(parcel, 2, this.f12878l);
        a.a.f0(parcel, 3, this.f12879m, i10, false);
        a.a.l0(parcel, 4, 4);
        parcel.writeInt(this.f12880n ? 1 : 0);
        a.a.l0(parcel, 5, 4);
        parcel.writeInt(this.f12881o ? 1 : 0);
        a.a.n0(iM0, parcel);
    }
}
