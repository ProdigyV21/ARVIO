package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public final class x0 implements Parcelable {
    public static final Parcelable.Creator<x0> CREATOR = new b(2);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f2865i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f2866l;

    public x0(String str, int i10) {
        this.f2865i = str;
        this.f2866l = i10;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeString(this.f2865i);
        parcel.writeInt(this.f2866l);
    }
}
