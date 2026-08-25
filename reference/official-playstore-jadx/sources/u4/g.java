package u4;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.j0;
import i4.s0;

/* JADX INFO: loaded from: classes4.dex */
public final class g extends t3.a {
    public static final Parcelable.Creator<g> CREATOR = new s0(11);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f22053i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final o3.b f22054l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final j0 f22055m;

    public g(int i10, o3.b bVar, j0 j0Var) {
        this.f22053i = i10;
        this.f22054l = bVar;
        this.f22055m = j0Var;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(this.f22053i);
        a.a.f0(parcel, 2, this.f22054l, i10, false);
        a.a.f0(parcel, 3, this.f22055m, i10, false);
        a.a.n0(iM0, parcel);
    }
}
