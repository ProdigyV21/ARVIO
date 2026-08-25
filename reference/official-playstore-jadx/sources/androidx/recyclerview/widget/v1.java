package androidx.recyclerview.widget;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public final class v1 implements Parcelable {
    public static final Parcelable.Creator<v1> CREATOR = new android.support.v4.media.d(10);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f5073i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f5074l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int[] f5075m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f5076n;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return "FullSpanItem{mPosition=" + this.f5073i + ", mGapDir=" + this.f5074l + ", mHasUnwantedGapAfter=" + this.f5076n + ", mGapPerSpan=" + Arrays.toString(this.f5075m) + '}';
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeInt(this.f5073i);
        parcel.writeInt(this.f5074l);
        parcel.writeInt(this.f5076n ? 1 : 0);
        int[] iArr = this.f5075m;
        if (iArr == null || iArr.length <= 0) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(iArr.length);
            parcel.writeIntArray(this.f5075m);
        }
    }
}
