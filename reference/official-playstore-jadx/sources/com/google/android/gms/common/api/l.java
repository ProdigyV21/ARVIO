package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class l extends t3.a {
    public static final Parcelable.Creator<l> CREATOR = new e0(1);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f12820i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f12821l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f12822m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f12823n;

    public l(int i10, int i11, int i12, boolean z) {
        this.f12820i = i10;
        this.f12821l = i11;
        this.f12822m = i12;
        this.f12823n = z;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof l)) {
            return false;
        }
        l lVar = (l) obj;
        return this.f12820i == lVar.f12820i && this.f12821l == lVar.f12821l && this.f12822m == lVar.f12822m && this.f12823n == lVar.f12823n;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f12820i), Integer.valueOf(this.f12821l), Integer.valueOf(this.f12822m), Boolean.valueOf(this.f12823n)});
    }

    public final String toString() {
        int i10 = this.f12820i;
        int length = String.valueOf(i10).length();
        int i11 = this.f12821l;
        int length2 = String.valueOf(i11).length();
        int i12 = this.f12822m;
        int length3 = String.valueOf(i12).length();
        boolean z = this.f12823n;
        StringBuilder sb2 = new StringBuilder(length + 55 + length2 + 19 + length3 + 13 + String.valueOf(z).length() + 1);
        sb2.append("ComplianceOptions{callerProductId=");
        sb2.append(i10);
        sb2.append(", dataOwnerProductId=");
        sb2.append(i11);
        sb2.append(", processingReason=");
        sb2.append(i12);
        sb2.append(", isUserData=");
        sb2.append(z);
        sb2.append("}");
        return sb2.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(this.f12820i);
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(this.f12821l);
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(this.f12822m);
        a.a.l0(parcel, 4, 4);
        parcel.writeInt(this.f12823n ? 1 : 0);
        a.a.n0(iM0, parcel);
    }
}
