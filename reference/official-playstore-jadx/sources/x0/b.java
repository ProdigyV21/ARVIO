package x0;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.recyclerview.widget.h1;

/* JADX INFO: loaded from: classes3.dex */
public abstract class b implements Parcelable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Parcelable f22549i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final a f22548l = new a();
    public static final Parcelable.Creator<b> CREATOR = new h1(1);

    public b() {
        this.f22549i = null;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        parcel.writeParcelable(this.f22549i, i10);
    }

    public b(Parcelable parcelable) {
        if (parcelable != null) {
            this.f22549i = parcelable == f22548l ? null : parcelable;
            return;
        }
        throw new IllegalArgumentException("superState must not be null");
    }

    public b(Parcel parcel, ClassLoader classLoader) {
        Parcelable parcelable = parcel.readParcelable(classLoader);
        this.f22549i = parcelable == null ? f22548l : parcelable;
    }
}
