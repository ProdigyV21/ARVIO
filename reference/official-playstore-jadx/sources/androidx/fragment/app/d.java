package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class d implements Parcelable {
    public static final Parcelable.Creator<d> CREATOR = new b(1);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList f2703i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ArrayList f2704l;

    public d(Parcel parcel) {
        this.f2703i = parcel.createStringArrayList();
        this.f2704l = parcel.createTypedArrayList(c.CREATOR);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeStringList(this.f2703i);
        parcel.writeTypedList(this.f2704l);
    }
}
