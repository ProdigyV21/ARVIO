package androidx.recyclerview.widget;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public final class c0 implements Parcelable {
    public static final Parcelable.Creator<c0> CREATOR = new android.support.v4.media.d(9);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4900i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f4901l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f4902m;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeInt(this.f4900i);
        parcel.writeInt(this.f4901l);
        parcel.writeInt(this.f4902m ? 1 : 0);
    }
}
