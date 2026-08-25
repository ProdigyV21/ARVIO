package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import i4.s0;
import k2.e;
import k2.f;

/* JADX INFO: loaded from: classes3.dex */
public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator<ParcelImpl> CREATOR = new s0(4);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final f f6696i;

    public ParcelImpl(f fVar) {
        this.f6696i = fVar;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        new e(parcel).l(this.f6696i);
    }

    public ParcelImpl(Parcel parcel) {
        this.f6696i = new e(parcel).h();
    }
}
