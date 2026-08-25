package z3;

import android.os.Parcel;
import android.os.Parcelable;
import i4.s0;

/* JADX INFO: loaded from: classes4.dex */
public final class b extends t3.a {
    public static final Parcelable.Creator<b> CREATOR = new s0(12);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f23171i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final a f23172l;

    public b(int i10, a aVar) {
        this.f23171i = i10;
        this.f23172l = aVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(this.f23171i);
        a.a.f0(parcel, 2, this.f23172l, i10, false);
        a.a.n0(iM0, parcel);
    }

    public b(a aVar) {
        this.f23171i = 1;
        this.f23172l = aVar;
    }
}
