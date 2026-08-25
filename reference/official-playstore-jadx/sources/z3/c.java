package z3;

import android.os.Parcel;
import android.os.Parcelable;
import i4.s0;

/* JADX INFO: loaded from: classes4.dex */
public final class c extends t3.a {
    public static final Parcelable.Creator<c> CREATOR = new s0(14);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f23173i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f23174l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f23175m;

    public c(int i10, String str, int i11) {
        this.f23173i = i10;
        this.f23174l = str;
        this.f23175m = i11;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(this.f23173i);
        a.a.g0(parcel, 2, this.f23174l, false);
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(this.f23175m);
        a.a.n0(iM0, parcel);
    }

    public c(String str, int i10) {
        this.f23173i = 1;
        this.f23174l = str;
        this.f23175m = i10;
    }
}
