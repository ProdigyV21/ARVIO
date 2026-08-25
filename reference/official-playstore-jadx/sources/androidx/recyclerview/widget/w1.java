package androidx.recyclerview.widget;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class w1 implements Parcelable {
    public static final Parcelable.Creator<w1> CREATOR = new android.support.v4.media.d(11);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f5079i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f5080l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f5081m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int[] f5082n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f5083o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int[] f5084p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ArrayList f5085q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f5086r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f5087s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f5088t;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeInt(this.f5079i);
        parcel.writeInt(this.f5080l);
        parcel.writeInt(this.f5081m);
        if (this.f5081m > 0) {
            parcel.writeIntArray(this.f5082n);
        }
        parcel.writeInt(this.f5083o);
        if (this.f5083o > 0) {
            parcel.writeIntArray(this.f5084p);
        }
        parcel.writeInt(this.f5086r ? 1 : 0);
        parcel.writeInt(this.f5087s ? 1 : 0);
        parcel.writeInt(this.f5088t ? 1 : 0);
        parcel.writeList(this.f5085q);
    }
}
