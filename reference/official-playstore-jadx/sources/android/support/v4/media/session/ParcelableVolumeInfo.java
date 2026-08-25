package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ParcelableVolumeInfo implements Parcelable {
    public static final Parcelable.Creator<ParcelableVolumeInfo> CREATOR = new u(3);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f853i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f854l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f855m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f856n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f857o;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeInt(this.f853i);
        parcel.writeInt(this.f855m);
        parcel.writeInt(this.f856n);
        parcel.writeInt(this.f857o);
        parcel.writeInt(this.f854l);
    }
}
