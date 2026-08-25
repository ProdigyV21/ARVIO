package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class j extends t3.a {
    public static final Parcelable.Creator<j> CREATOR = e0.f12684b;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final j f12816n;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final l f12817i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f12818l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f12819m;

    static {
        j jVar = new j(null, false);
        jVar.f12819m = false;
        f12816n = jVar;
    }

    public j(l lVar, boolean z) {
        this.f12817i = lVar;
        this.f12818l = z;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof j)) {
            return false;
        }
        j jVar = (j) obj;
        return com.google.android.gms.common.internal.t.l(this.f12817i, jVar.f12817i) && this.f12819m == jVar.f12819m && this.f12818l == jVar.f12818l;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f12817i, Boolean.valueOf(this.f12819m), Boolean.valueOf(this.f12818l)});
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.f12817i);
        return androidx.compose.material3.d.q(new StringBuilder(strValueOf.length() + 31), "ApiMetadata(complianceOptions=", strValueOf, ")");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        if (this.f12819m) {
            parcel.setDataPosition(parcel.dataPosition() - 4);
            parcel.setDataSize(parcel.dataSize() - 4);
            return;
        }
        parcel.writeInt(-204102970);
        int iM0 = a.a.m0(20293, parcel);
        a.a.f0(parcel, 1, this.f12817i, i10, false);
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(this.f12818l ? 1 : 0);
        a.a.n0(iM0, parcel);
    }
}
