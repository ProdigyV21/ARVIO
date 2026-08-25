package androidx.leanback.widget;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public final class z implements Parcelable {
    public static final Parcelable.Creator<z> CREATOR = new android.support.v4.media.d(7);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f3182i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Bundle f3183l;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeInt(this.f3182i);
        parcel.writeBundle(this.f3183l);
    }
}
