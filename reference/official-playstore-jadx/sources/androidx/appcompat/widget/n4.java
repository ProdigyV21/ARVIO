package androidx.appcompat.widget;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class n4 extends x0.b {
    public static final Parcelable.Creator<n4> CREATOR = new w3(1);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f1671m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f1672n;

    public n4(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.f1671m = parcel.readInt();
        this.f1672n = parcel.readInt() != 0;
    }

    @Override // x0.b, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        super.writeToParcel(parcel, i10);
        parcel.writeInt(this.f1671m);
        parcel.writeInt(this.f1672n ? 1 : 0);
    }
}
