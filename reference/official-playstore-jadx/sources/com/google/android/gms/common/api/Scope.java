package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;

/* JADX INFO: loaded from: classes4.dex */
public final class Scope extends t3.a implements ReflectedParcelable {
    public static final Parcelable.Creator<Scope> CREATOR = new e0(2);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f12668i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f12669l;

    public Scope(int i10, String str) {
        com.google.android.gms.common.internal.t.g(str, "scopeUri must not be null or empty");
        this.f12668i = i10;
        this.f12669l = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Scope)) {
            return false;
        }
        return this.f12669l.equals(((Scope) obj).f12669l);
    }

    public final int hashCode() {
        return this.f12669l.hashCode();
    }

    public final String toString() {
        return this.f12669l;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(this.f12668i);
        a.a.g0(parcel, 2, this.f12669l, false);
        a.a.n0(iM0, parcel);
    }
}
