package androidx.navigation;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public final class j implements Parcelable {
    public static final Parcelable.Creator<j> CREATOR = new android.support.v4.media.d(8);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f4701i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f4702l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Bundle f4703m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Bundle f4704n;

    public j(i iVar) {
        this.f4701i = iVar.f4685p;
        this.f4702l = iVar.f4681l.f4662p;
        this.f4703m = iVar.a();
        Bundle bundle = new Bundle();
        this.f4704n = bundle;
        iVar.f4688s.c(bundle);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeString(this.f4701i);
        parcel.writeInt(this.f4702l);
        parcel.writeBundle(this.f4703m);
        parcel.writeBundle(this.f4704n);
    }

    public j(Parcel parcel) {
        this.f4701i = parcel.readString();
        this.f4702l = parcel.readInt();
        this.f4703m = parcel.readBundle(j.class.getClassLoader());
        this.f4704n = parcel.readBundle(j.class.getClassLoader());
    }
}
