package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class e1 implements Parcelable {
    public static final Parcelable.Creator<e1> CREATOR = new b(3);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList f2715i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ArrayList f2716l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public c[] f2717m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f2718n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f2719o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ArrayList f2720p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ArrayList f2721q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ArrayList f2722r;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeStringList(this.f2715i);
        parcel.writeStringList(this.f2716l);
        parcel.writeTypedArray(this.f2717m, i10);
        parcel.writeInt(this.f2718n);
        parcel.writeString(this.f2719o);
        parcel.writeStringList(this.f2720p);
        parcel.writeTypedList(this.f2721q);
        parcel.writeTypedList(this.f2722r);
    }
}
