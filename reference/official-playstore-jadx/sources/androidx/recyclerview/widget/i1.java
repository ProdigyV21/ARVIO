package androidx.recyclerview.widget;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public final class i1 extends x0.b {
    public static final Parcelable.Creator<i1> CREATOR = new h1(0);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Parcelable f4946m;

    public i1(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.f4946m = parcel.readParcelable(classLoader == null ? z0.class.getClassLoader() : classLoader);
    }

    @Override // x0.b, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        super.writeToParcel(parcel, i10);
        parcel.writeParcelable(this.f4946m, 0);
    }
}
